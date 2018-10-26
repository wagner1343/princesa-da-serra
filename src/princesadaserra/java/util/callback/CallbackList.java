package princesadaserra.java.util.callback;

import java.util.LinkedList;

public class CallbackList {
    private LinkedList<Callback> listeners;

    public CallbackList(){
        listeners = new LinkedList<>();
    }

    public void executeAll(){
        for(Callback f : listeners){
            f.execute();
        }
    }

    public void addCallback(Callback callback){
        listeners.add(callback);
    }
}
