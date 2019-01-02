package my.execute;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class EventProcess<Event> implements Runnable, Cloneable {
    protected ScriptRuntime<Event> runtime;
    private final EventHandler<Event> eventHandler;
    private volatile int unsatisfiedDepdendings;
    private final List<EventHandler<Event>> dependedEventHandlers;
    private Event event;
    private Lock lock;

    EventProcess(EventHandler<Event> eventHandler, int depdending, List<EventHandler<Event>> dependedEventHandlers) {
        this.eventHandler = eventHandler;
        this.unsatisfiedDepdendings = depdending;
        this.dependedEventHandlers = dependedEventHandlers;
    }

    void init(ScriptRuntime<Event> runtime, Event t) {
        this.runtime = runtime;
        this.event = t;
    }

    public void run() {
        try {
            this.eventHandler.onEvent(this.event);
            if (this.dependedEventHandlers != null) {
                boolean hasUsedSynRunTimeOnce = false;

                for(int i = 0; i < this.dependedEventHandlers.size(); ++i) {
                    EventProcess<Event> process = this.runtime.getProcess((EventHandler)this.dependedEventHandlers.get(i));
                    if (process.decreaseUnsatisfiedDependcies() == 0) {
                        if (!hasUsedSynRunTimeOnce) {
                            hasUsedSynRunTimeOnce = true;
                            process.run();
                        } else {
                            this.runtime.startProcess(process);
                        }
                    }
                }
            }
        } catch (Exception var4) {
            this.runtime.markAsError(var4);
        }

    }

    boolean hasUnsatisfiedDependcies() {
        this.lock.lock();

        boolean var1;
        try {
            var1 = this.unsatisfiedDepdendings != 0;
        } finally {
            this.lock.unlock();
        }

        return var1;
    }

    private int decreaseUnsatisfiedDependcies() {
        this.lock.lock();

        int var1;
        try {
            var1 = --this.unsatisfiedDepdendings;
        } finally {
            this.lock.unlock();
        }

        return var1;
    }

    public Object clone() {
        try {
            EventProcess<Event> cloned = (EventProcess)super.clone();
            cloned.lock = new ReentrantLock();
            return cloned;
        } catch (Exception var2) {
            throw new InternalError();
        }
    }
}
