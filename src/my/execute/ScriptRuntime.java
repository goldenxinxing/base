package my.execute;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ScriptRuntime<Event> {
    private final Event t;
    private final ExecutorService executorService;
    private final CountDownLatch completeLatch;
    private final Map<EventHandler<Event>, EventProcess<Event>> processMap;
    private final Callback<Event> callback;
    private final long timeout;
    private volatile Throwable error;
    private final Lock lock = new ReentrantLock();
    private volatile boolean canceled = false;
    private final ArrayList<Future<?>> futures;
    private static final ArrayList<Future<?>> EmptyFutures = new ArrayList(0);

    ScriptRuntime(Event t, ExecutorService executorService, Map<EventHandler<Event>, EventProcess<Event>> processMap, Callback<Event> callback, long timeout) {
        this.t = t;
        this.executorService = executorService;
        this.processMap = processMap;
        this.callback = callback;
        if (callback == null) {
            this.completeLatch = new CountDownLatch(1);
        } else {
            this.completeLatch = null;
        }

        this.timeout = timeout;
        if (this.timeout > 0L) {
            this.futures = new ArrayList(1);
        } else {
            this.futures = EmptyFutures;
        }

    }

    Event run() {
        ArrayList<EventProcess<Event>> processesWithNoDependencies = this.getProcessedWithNoDependencies();
        Iterator i$ = processesWithNoDependencies.iterator();

        while(i$.hasNext()) {
            EventProcess<Event> process = (EventProcess)i$.next();
            this.startProcess(process);
        }

        this.waitIfNecessary();
        return this.t;
    }

    private ArrayList<EventProcess<Event>> getProcessedWithNoDependencies() {
        ArrayList<EventProcess<Event>> processesWithNoDependencies = new ArrayList(1);
        Iterator i$ = this.processMap.values().iterator();

        while(i$.hasNext()) {
            EventProcess<Event> process = (EventProcess)i$.next();
            if (!process.hasUnsatisfiedDependcies()) {
                processesWithNoDependencies.add(process);
            }
        }

        return processesWithNoDependencies;
    }

    void markAsCompleted() {
        if (this.callback == null) {
            this.completeLatch.countDown();
        } else {
            this.callback.onSuccess(this.t);
        }

    }

    void markAsError(Throwable error) {
        if (this.callback == null) {
            this.error = error;
            this.completeLatch.countDown();
        } else {
            this.callback.onError(this.t, error);
        }

    }

    private void cancel() {
        this.lock.lock();

        try {
            this.canceled = true;
            Iterator i$ = this.futures.iterator();

            while(i$.hasNext()) {
                Future<?> future = (Future)i$.next();
                future.cancel(true);
            }
        } finally {
            this.lock.unlock();
        }

    }

    private void waitIfNecessary() {
        if (this.callback == null) {
            try {
                if (this.timeout > 0L) {
                    if (!this.completeLatch.await(this.timeout, TimeUnit.MILLISECONDS)) {
                        this.cancel();
                        throw new TimeoutException();
                    }
                } else {
                    this.completeLatch.await();
                }

                if (this.error != null) {
                    throw new SirectorException(this.error);
                }
            } catch (InterruptedException var2) {
                throw new SirectorException(var2);
            }
        }

    }

    Callback<Event> getCallback() {
        return this.callback;
    }

    EventProcess<Event> getProcess(EventHandler<Event> eventHandler) {
        return (EventProcess)this.processMap.get(eventHandler);
    }

    void startProcess(EventProcess<Event> process) {
        if (this.timeout > 0L) {
            this.lock.lock();

            try {
                if (!this.canceled) {
                    this.futures.add(this.executorService.submit(process));
                }
            } finally {
                this.lock.unlock();
            }
        } else {
            this.executorService.submit(process);
        }

    }
}
