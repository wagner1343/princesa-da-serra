package princesadaserra.java.util.threading;

import com.sun.istack.internal.Nullable;

public abstract class Task<ArgumentType, ResultType> implements Runnable{
    ArgumentType argument;
    ResultType result;

    private Thread thread;
    private TASKSTATUS TASKSTATUS;

    private CallbackWithArgumentList<ResultType> onSuccessCallbackList;
    private CallbackWithArgumentList<ResultType> onFailedCallbackList;
    private CallbackWithArgumentList<ResultType> onCanceledCallbackList;
    private CallbackWithArgumentList<ResultType> onFinishCallbackList;
    private CallbackList onExecutingCallbackList;
    private CallbackWithArgumentList<TASKSTATUS> onStatusChangedCallbackList;

    private CallbackWithArgument<ResultType> onSuccessCallback;
    private CallbackWithArgument<ResultType> onFailedCallback;
    private CallbackWithArgument<ResultType> onCanceledCallback;
    private CallbackWithArgument<ResultType> onFinishCallback;
    private Callback onExecutingCallback;
    private CallbackWithArgument<TASKSTATUS> onStatusChangedCallback;

    protected abstract @Nullable ResultType execute(@Nullable ArgumentType argument);

    public Task(){
        this.TASKSTATUS = TASKSTATUS.INITIAL;

        onSuccessCallbackList = new CallbackWithArgumentList<>();
        onFailedCallbackList = new CallbackWithArgumentList<>();
        onCanceledCallbackList = new CallbackWithArgumentList<>();
        onFinishCallbackList = new CallbackWithArgumentList<>();
        onExecutingCallbackList = new CallbackList();
        onStatusChangedCallbackList = new CallbackWithArgumentList<>();

        this.onSuccessCallback = (arg) -> this.onSuccessCallbackList.executeAll(arg);
        this.onFailedCallback = (arg) -> this.onFailedCallbackList.executeAll(arg);
        this.onCanceledCallback = (arg) -> this.onCanceledCallbackList.executeAll(arg);
        this.onFinishCallback = (arg) -> this.onFinishCallbackList.executeAll(arg);
        this.onExecutingCallback = () -> this.onExecutingCallbackList.executeAll();
        this.onStatusChangedCallback = (arg) -> this.onStatusChangedCallbackList.executeAll(arg);

    }

    public void start(@Nullable ArgumentType argument){
        this.argument = argument;
        this.thread = new Thread(this);
        thread.start();

    }

    public void start(){
        start(null);
    }

    @Override
    public void run(){
        setExecuting();
        onExecutingCallback.execute();

        result = execute(argument);

        if (TASKSTATUS == TASKSTATUS.EXECUTING) setSuccess();

        switch (TASKSTATUS){
            case SUCCESS:
                onSuccessCallback.execute(result);
                break;
            case FAILED:
                onFailedCallback.execute(result);
                break;
            case CANCELED:
                onCanceledCallback.execute(result);
                break;
        }

        onFinishCallback.execute(result);
    }

    public boolean isExecuting(){
        return getTASKSTATUS() == TASKSTATUS.EXECUTING;
    }

    public void setOnStatusChangedCallback(Callback onStatusChangedCallback) {
        this.onStatusChangedCallback = (arg) -> onStatusChangedCallback.execute();
    }

    private void setTASKSTATUS(TASKSTATUS TASKSTATUS){
        this.TASKSTATUS = TASKSTATUS;
        onStatusChangedCallback.execute(TASKSTATUS);
    }

    public TASKSTATUS getTASKSTATUS(){
        return this.TASKSTATUS;
    }

    public void setFailed(){
        setTASKSTATUS(TASKSTATUS.FAILED);
    }

    public void setSuccess(){
        setTASKSTATUS(TASKSTATUS.SUCCESS);
    }

    public void setCanceled(){
        setTASKSTATUS(TASKSTATUS.CANCELED);
    }

    public void setExecuting(){
        setTASKSTATUS(TASKSTATUS.EXECUTING);
    }

    public void setOnSuccessCallback(Callback onSuccessCallback) {
        this.onSuccessCallback = (arg) -> onSuccessCallback.execute();
    }

    public void setOnFailedCallback(Callback onFailedCallback) {
        this.onFailedCallback = (arg) -> onFailedCallback.execute();
    }

    public void setOnCanceledCallback(Callback onCanceledCallback) {
        this.onCanceledCallback = (arg) -> onCanceledCallback.execute();
    }

    public void setOnExecutingCallback(Callback onExecutingCallback) {
        this.onExecutingCallback = onExecutingCallback;
    }



    public void addOnFinishCallback(CallbackWithArgument<ResultType> callback){
        this.onFinishCallbackList.addCallback(callback);
    }

    public void addOnFinishCallback(Callback callback){
        this.onFinishCallbackList.addCallback((arg) -> callback.execute());
    }

    public void addOnSuccessCallback(CallbackWithArgument<ResultType> callback){
        this.onSuccessCallbackList.addCallback(callback);
    }

    public void addOnFailedCallback(CallbackWithArgument<ResultType> callback){
        this.onFailedCallbackList.addCallback(callback);
    }

    public void addOnCanceledCallback(CallbackWithArgument<ResultType> callback){
        this.onCanceledCallbackList.addCallback(callback);
    }

    public void addOnStatusChangedCallback(CallbackWithArgument<TASKSTATUS> callback){
        this.onStatusChangedCallbackList.addCallback(callback);
    }

    public void addOnSuccessCallback(Callback callback){
        this.onSuccessCallbackList.addCallback((arg) -> callback.execute());
    }

    public void addOnFailedCallback(Callback callback){
        this.onFailedCallbackList.addCallback((arg) -> callback.execute());
    }

    public void addOnCanceledCallback(Callback callback){
        this.onCanceledCallbackList.addCallback((arg) -> callback.execute());
    }

    public void addOnStatusChangedCallback(Callback callback){
        this.onStatusChangedCallbackList.addCallback((arg) -> callback.execute());
    }

    public void addOnExecutingCallback(Callback callback){
        this.onExecutingCallbackList.addCallback(callback);
    }

}



