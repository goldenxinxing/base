package my.execute;

import java.util.concurrent.ExecutorService;

public class Executor<Event> {
    private final ExecutorService executorService;
    private final Script<Event> script;
    private ScriptRuntimeBuilder<Event> builder;
    private volatile boolean ready = false;

    public Executor(ExecutorService executorService) {
        this.executorService = executorService;
        this.script = new Script();
    }

    public EventHandlerGroup<Event> begin(EventHandler... eventHandlers) {
        return this.script.start(eventHandlers);
    }

    public EventHandlerGroup<Event> after(EventHandler... eventHandlers) {
        return this.script.after(eventHandlers);
    }

    public Event publish(Event event, long timeout) {
        if (!this.ready) {
            throw new IllegalStateException("sirector not started.");
        } else {
            return this.builder.build(event, timeout).run();
        }
    }

    public Event publish(Event event) {
        return this.publish(event, 0L);
    }

    public void publish(Event event, Callback<Event> callback) {
        if (!this.ready) {
            throw new IllegalStateException("sirector not started.");
        } else if (callback == null) {
            throw new IllegalArgumentException("callback can not be null");
        } else {
            this.builder.build(event, 0L, callback).run();
        }
    }

    public boolean isReady() {
        return this.ready;
    }

    public synchronized void ready() {
        if (!this.ready) {
            this.script.ready();
            this.builder = new ScriptRuntimeBuilder(this.script, this.executorService);
            this.ready = true;
        }

    }
}
