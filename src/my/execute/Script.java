package my.execute;

import java.util.*;

class Script<Event> {

    private boolean ready = false;
    private final IdentityHashMap<EventHandler<Event>, List<EventHandler<Event>>> denpendedHandlers = new IdentityHashMap();

    Script() {
    }

    synchronized EventHandlerGroup<Event> after(EventHandler... handlers) {
        if (this.ready) {
            throw new IllegalStateException("script is ready, cannot be edit any more.");
        } else {
            EventHandler[] arr$ = handlers;
            int len$ = handlers.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                EventHandler<Event> handler = arr$[i$];
                if (!this.denpendedHandlers.containsKey(handler)) {
                    throw new IllegalStateException("event handler is not in script yet.");
                }
            }

            return this.start(handlers);
        }
    }

    synchronized EventHandlerGroup<Event> start(EventHandler... handlers) {
        if (this.ready) {
            throw new IllegalStateException("script is ready, cannot be edit any more.");
        } else {
            EventHandlerGroup<Event> clips = new EventHandlerGroup(this, Arrays.asList(handlers));
            return clips;
        }
    }

    synchronized Map<EventHandler<Event>, List<EventHandler<Event>>> getdenpendedEventHandlers() {
        return this.denpendedHandlers;
    }

    synchronized void ready() {
        this.ready = true;
    }

    synchronized boolean isReady() {
        return this.ready;
    }

    void addDependency(EventHandler<Event> from, EventHandler<Event> to) {
        if (!this.denpendedHandlers.containsKey(from)) {
            this.denpendedHandlers.put(from, new ArrayList(0));
        }

        if (to != null && !((List)this.denpendedHandlers.get(from)).contains(to)) {
            ((List)this.denpendedHandlers.get(from)).add(to);
        }

    }
}
