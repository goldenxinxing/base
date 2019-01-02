package my.execute;

import java.util.*;
import java.util.concurrent.ExecutorService;

class ScriptRuntimeBuilder<Event> {
    private final Script<Event> script;
    private final ExecutorService executorService;
    private IdentityHashMap<EventHandler<Event>, EventProcess<Event>> processPrototypeMap = new IdentityHashMap();

    ScriptRuntimeBuilder(Script<Event> script, ExecutorService executorService) {
        this.script = script;
        this.executorService = executorService;
        this.preparePrototypes();
    }

    private void preparePrototypes() {
        Map<EventHandler<Event>, List<EventHandler<Event>>> dependedHandlerMap = this.copyEventHandlerMap(this.script.getdenpendedEventHandlers());
        Map<EventHandler<Event>, List<EventHandler<Event>>> dependingHandlerMap = new HashMap();
        Iterator i$ = dependedHandlerMap.keySet().iterator();

        EventHandler eventHandler;
        while(i$.hasNext()) {
            eventHandler = (EventHandler)i$.next();
            dependingHandlerMap.put(eventHandler, new ArrayList(1));
        }

        i$ = dependedHandlerMap.keySet().iterator();

        //Iterator i$;  remove
        EventHandler handler;
        while(i$.hasNext()) {
            eventHandler = (EventHandler)i$.next();
            i$ = ((List)dependedHandlerMap.get(eventHandler)).iterator();

            while(i$.hasNext()) {
                handler = (EventHandler)i$.next();
                ((List)dependingHandlerMap.get(handler)).add(eventHandler);
            }
        }

        ScriptRuntimeBuilder<Event>.ScriptEndEventHandler scriptEndEventHandler = new ScriptRuntimeBuilder.ScriptEndEventHandler();
        List<EventHandler<Event>> scriptEndDependingHandlers = new ArrayList(1);
        i$ = dependedHandlerMap.keySet().iterator();

        while(i$.hasNext()) {
            handler = (EventHandler)i$.next();
            List<EventHandler<Event>> dependedHandlers = (List)dependedHandlerMap.get(handler);
            if (dependedHandlers.isEmpty()) {
                scriptEndDependingHandlers.add(handler);
                ((List)dependedHandlerMap.get(handler)).add(scriptEndEventHandler);
            }
        }

        dependedHandlerMap.put(scriptEndEventHandler, new ArrayList(0));
        dependingHandlerMap.put(scriptEndEventHandler, scriptEndDependingHandlers);

        //Object process; replace
        EventProcess process;
        for(i$ = dependedHandlerMap.keySet().iterator(); i$.hasNext(); this.processPrototypeMap.put(handler, process)) {
            handler = (EventHandler)i$.next();
            if (handler != scriptEndEventHandler) {
                process = new EventProcess(handler, ((List)dependingHandlerMap.get(handler)).size(), (List)dependedHandlerMap.get(handler));
            } else {
                process = new ScriptRuntimeBuilder.ScriptEndEventProcess(handler, ((List)dependingHandlerMap.get(handler)).size(), (List)dependedHandlerMap.get(handler));
            }
        }

    }

    private Map<EventHandler<Event>, List<EventHandler<Event>>> copyEventHandlerMap(Map<EventHandler<Event>, List<EventHandler<Event>>> handlerMap) {
        IdentityHashMap<EventHandler<Event>, List<EventHandler<Event>>> rt = new IdentityHashMap();
        Iterator i$ = handlerMap.keySet().iterator();

        while(i$.hasNext()) {
            EventHandler<Event> eventHandler = (EventHandler)i$.next();
            rt.put(eventHandler, new ArrayList((Collection)handlerMap.get(eventHandler)));
        }

        return rt;
    }

    ScriptRuntime<Event> build(Event event, long timeout) {
        return this.build(event, timeout, (Callback)null);
    }

    ScriptRuntime<Event> build(Event event, long timeout, Callback<Event> callback) {
        IdentityHashMap<EventHandler<Event>, EventProcess<Event>> newProcessMap = new IdentityHashMap(this.processPrototypeMap.size());
        ScriptRuntime<Event> runtime = new ScriptRuntime(event, this.executorService, newProcessMap, callback, timeout);
        Iterator i$ = this.processPrototypeMap.keySet().iterator();

        while(i$.hasNext()) {
            EventHandler<Event> handler = (EventHandler)i$.next();
            EventProcess<Event> newProcess = (EventProcess)((EventProcess)this.processPrototypeMap.get(handler)).clone();
            newProcess.init(runtime, event);
            newProcessMap.put(handler, newProcess);
        }

        return runtime;
    }

    private class ScriptEndEventHandler implements EventHandler<Event> {
        private ScriptEndEventHandler() {
        }

        public void onEvent(Event event) {
        }
    }

    private class ScriptEndEventProcess extends EventProcess<Event> {
        ScriptEndEventProcess(EventHandler<Event> handler, int depdending, List<EventHandler<Event>> dependedEventHandlers) {
            super(handler, depdending, dependedEventHandlers);
        }

        public void run() {
            this.runtime.markAsCompleted();
        }
    }
}
