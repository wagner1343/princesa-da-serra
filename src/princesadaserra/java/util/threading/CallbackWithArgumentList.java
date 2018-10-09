package princesadaserra.java.util.threading;

import java.util.LinkedList;

public class CallbackWithArgumentList<ArgumentType> {
    private LinkedList<CallbackWithArgument<ArgumentType>> listeners;

    public CallbackWithArgumentList(){
        listeners = new LinkedList<>();
    }

    public void executeAll(ArgumentType argument){
        for(CallbackWithArgument f : listeners){
            f.execute(argument);
        }
    }

    public void addCallback(CallbackWithArgument<ArgumentType> callback){
        listeners.add(callback);
    }
}
