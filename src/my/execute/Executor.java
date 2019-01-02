package my.execute;

import java.util.concurrent.ExecutorService;
/**
 * The main entry class of the framework. It will compose event handlers into a
 * {@link EventHandlerGroup} type which implies the dependencies between event
 * handlers. The compose related methods include
 * {@link Executor#begin(EventHandler...)},
 * {@link Executor#after(EventHandler...)} and
 * {@link EventHandlerGroup#then(EventHandler...)}. After the transaction is
 * ready by called {@link Executor#ready()}, We can publish event synchronously
 * by {@link Executor#publish(Object)} or asynchronously by
 * {@link Executor#publish(Object, Callback)} to invoke a transaction.
 *
 * Following is a simple example:
 *
 * <pre>
 *
 * ExecutorService executor = Executors.newFixedThreadPool(4);
 * Director sirector = new Director(executor);
 * HandlerA a = new HandlerA();
 * HandlerB b = new HandlerB();
 * HandlerC c = new HandlerC();
 * sirector.begin(a, b);
 * sirector.after(a,b).then(c);
 * sirector.ready();
 * Event e = new Event();
 * Event result = sirector.publish(e);
 *
 * class Event{
 * 	   ....
 * }
 *
 * class HandlerA implement EventHandler<Event>{
 *
 * }
 *
 * class handlerB implement EventHandler<event>{
 *
 * }
 *
 * </pre>
 *
 *
 * @author
 *
 * @param <Event>
 *            event type
 */
public class Executor<Event> {
    private final ExecutorService executorService;

    private final Script<Event> script;

    private ScriptRuntimeBuilder<Event> builder;

    private volatile boolean ready = false;

    /**
     * Director constructor
     *
     * @param executorService
     *            the executor service instance used by sirector.
     */
    public Executor(ExecutorService executorService) {
        this.executorService = executorService;
        this.script = new Script<Event>();
    }

    /**
     * Add event handlers with no dependencies to the transaction type of
     * current sirector instance.
     *
     * <pre>
     * sirector.begin(eventHandlerA).then(eventHandlerB);
     * </pre>
     *
     * means eventHandlerA must be called before eventHandlerB in the
     * transaction.
     *
     * <p>
     * The methods can be called many times.
     * </p>
     *
     *
     * @param eventHandlers
     * @return
     * @throws IllegalStateException
     *             if event handler in the parameter is not in the transaction
     *             yet.
     *
     * @see Executor#after(EventHandler...)
     * @see EventHandlerGroup#then(EventHandler...)
     */
    public EventHandlerGroup<Event> begin(EventHandler<Event>... eventHandlers) {
        return script.start(eventHandlers);
    }

    /**
     * Used with {@linkplain EventHandlerGroup#then} class to imply the
     * dependency between event handlers. It does not add event handler to the
     * transaction type of current sirector instance.
     *
     * The following example:
     *
     * <pre>
     * sirector.after(eventHandlerA).then(eventHandlerB);
     * </pre>
     *
     * means eventHandlerA must be called before eventHandlerB in the
     * transaction type.
     *
     * <p>
     * The method can be called many times.
     * </p>
     *
     * @param eventHandlers
     *            event handlers to be depended by other handlers
     * @return the event transaction being composed
     * @throws IllegalStateException
     *             if event handler in the parameter is not added to the
     *             transaction yet or <tt>ready()</tt> method of the same
     *             sirector instance has been called.
     *
     *
     * @see EventHandlerGroup#then(EventHandler...)
     * @see Executor#begin(EventHandler...)
     */
    public EventHandlerGroup<Event> after(EventHandler<Event>... eventHandlers) {
        return script.after(eventHandlers);
    }

    /**
     * Publish event synchronously.
     *
     * @param event
     *            request context
     * @param timeout
     *            timeout in millisecond
     * @return request context
     * @throws IllegalStateException
     *             if <tt>ready()</tt> of the same instance is not called.
     * @throws TimeoutException
     *             if request is not completed in millisecond of timeout.
     * @see Executor#ready()
     */
    public Event publish(Event event, long timeout) {
        if (!ready) {
            throw new IllegalStateException("sirector not started.");
        }
        return builder.build(event, timeout).run();
    }

    public Event publish(Event event) {
        return publish(event, (long) 0);
    }

    /**
     * Publish event asynchronously.
     *
     * @param event
     *            the handled event
     * @param callback
     *            called when the whole event transaction has been completed or
     *            error has occurred.
     * @throws IllegalStateException
     *             if <tt>ready()</tt> of the same instance is not called.
     */
    public void publish(Event event, Callback<Event> callback) {
        if (!ready) {
            throw new IllegalStateException("sirector not started.");
        }
        if (callback == null) {
            throw new IllegalArgumentException("callback can not be null");
        }
        builder.build(event, 0, callback).run();
    }

    /**
     * Return the status of event transaction type of the current sirector
     * instance.
     *
     * @return true if the event transaction of the current sirector instance is
     *         ready by call {@link Executor#ready()}.
     */
    public boolean isReady() {
        return ready;
    }

    /**
     * Mark the event transaction type of the sirector instance as ready. This
     * method should be called before publish any events. And we should not
     * called transaction type building methods any more after this method
     * called. otherwise {@link IllegalStateException} will be throwed.
     *
     * @see Executor#isReady()
     */
    public synchronized void ready() {
        if (!ready) {
            script.ready();
            builder = new ScriptRuntimeBuilder<Event>(script, executorService);
            ready = true;
        }
    }
}
