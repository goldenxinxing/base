package my.execute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class EventHandlerGroup<Event> {
    private final Script<Event> script;
    private List<EventHandler<Event>> eventHandlers;

    EventHandlerGroup(Script<Event> script, List<EventHandler<Event>> eventHandlers) {
        synchronized(script) {
            if (script.isReady()) {
                throw new IllegalStateException("script is ready, cannot be edit any more.");
            } else {
                this.script = script;
                this.eventHandlers = new ArrayList(eventHandlers.size());
                this.copyList(eventHandlers, this.eventHandlers);
                Iterator i$ = eventHandlers.iterator();

                while(i$.hasNext()) {
                    EventHandler<Event> handler = (EventHandler)i$.next();
                    script.addDependency(handler, (EventHandler)null);
                }

            }
        }
    }

    public EventHandlerGroup<Event> then(EventHandler... eventHandlers) {
        Script var2 = this.script;
        synchronized(this.script) {
            if (this.script.isReady()) {
                throw new IllegalStateException("script is ready, cannot be edit any more.");
            } else {
                Iterator it = this.eventHandlers.iterator();

                while(it.hasNext()) {
                    EventHandler<Event> from = (EventHandler)it.next();
                    EventHandler[] arr$ = eventHandlers;
                    int len$ = eventHandlers.length;

                    for(int i$ = 0; i$ < len$; ++i$) {
                        EventHandler<Event> to = arr$[i$];
                        this.script.addDependency(from, to);
                    }
                }

                EventHandler[] arr$ = eventHandlers;
                int len$ = eventHandlers.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    EventHandler<Event> to = arr$[i$];
                    this.script.addDependency(to, (EventHandler)null);
                }

                this.eventHandlers = new ArrayList(eventHandlers.length);
                this.copyList(Arrays.asList(eventHandlers), this.eventHandlers);
                return this;
            }
        }
    }

    private void copyList(List<EventHandler<Event>> src, List<EventHandler<Event>> dest) {
        Iterator i$ = src.iterator();

        while(i$.hasNext()) {
            EventHandler<Event> s = (EventHandler)i$.next();
            dest.add(s);
        }

    }
}
