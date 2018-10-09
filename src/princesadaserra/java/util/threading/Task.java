package princesadaserra.java.util.threading;

public abstract class Task<ArgumentType, ResultType, ProgressType> implements Runnable{
    private ArgumentType argument;
    private ResultType result;
    private ProgressType progress;

    private Thread thread;
    private TASKSTATUS STATUS;

    private CallbackWithArgumentList<ResultType> onSuccessCallbackList;
    private CallbackWithArgumentList<ResultType> onFailedCallbackList;
    private CallbackWithArgumentList<ResultType> onCanceledCallbackList;
    private CallbackWithArgumentList<ResultType> onFinishCallbackList;
    private CallbackWithArgumentList<TASKSTATUS> onStatusChangedCallbackList;
    private CallbackWithArgumentList<ProgressType> onProgressChangedCallbackList;
    private CallbackList onExecutingCallbackList;

    private CallbackWithArgument<ResultType> onSuccessCallback;
    private CallbackWithArgument<ResultType> onFailedCallback;
    private CallbackWithArgument<ResultType> onCanceledCallback;
    private CallbackWithArgument<ResultType> onFinishCallback;
    private CallbackWithArgument<TASKSTATUS> onStatusChangedCallback;
    private CallbackWithArgument<ProgressType> onProgressChangedCallback;
    private Callback onExecutingCallback;

    protected abstract ResultType execute(ArgumentType argument);

    public Task(){
        this.STATUS = TASKSTATUS.INITIAL;

        onSuccessCallbackList = new CallbackWithArgumentList<>();
        onFailedCallbackList = new CallbackWithArgumentList<>();
        onCanceledCallbackList = new CallbackWithArgumentList<>();
        onFinishCallbackList = new CallbackWithArgumentList<>();
        onStatusChangedCallbackList = new CallbackWithArgumentList<>();
        onProgressChangedCallbackList = new CallbackWithArgumentList<>();
        onExecutingCallbackList = new CallbackList();

        this.onSuccessCallback = (arg) -> this.onSuccessCallbackList.executeAll(arg);
        this.onFailedCallback = (arg) -> this.onFailedCallbackList.executeAll(arg);
        this.onCanceledCallback = (arg) -> this.onCanceledCallbackList.executeAll(arg);
        this.onFinishCallback = (arg) -> this.onFinishCallbackList.executeAll(arg);
        this.onStatusChangedCallback = (arg) -> this.onStatusChangedCallbackList.executeAll(arg);
        this.onProgressChangedCallback = (arg) -> this.onProgressChangedCallbackList.executeAll(arg);
        this.onExecutingCallback = () -> this.onExecutingCallbackList.executeAll();

    }

    public void start(ArgumentType argument){
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

        if (STATUS == TASKSTATUS.EXECUTING) setSuccess();

        switch (STATUS){
            case SUCCESS:
                onSuccessCallback.execute(result);
                break;
            case FAILED:
                onFailedCallback.execute(result);
                break;
            case CANCELED:
                onCanceledCallback.execute(result);
                break;
            default:
                break;
        }

        onFinishCallback.execute(result);
    }

    public boolean isExecuting(){
        return getSTATUS() == TASKSTATUS.EXECUTING;
    }

    public void setOnStatusChangedCallback(Callback onStatusChangedCallback) {
        this.onStatusChangedCallback = (arg) -> onStatusChangedCallback.execute();
    }

    private void setSTATUS(TASKSTATUS STATUS){
        this.STATUS = STATUS;
        onStatusChangedCallback.execute(STATUS);
    }

    public TASKSTATUS getSTATUS(){
        return this.STATUS;
    }

    public void setFailed(){
        setSTATUS(STATUS.FAILED);
    }

    public void setSuccess(){
        setSTATUS(STATUS.SUCCESS);
    }

    public void setCanceled(){
        setSTATUS(STATUS.CANCELED);
    }

    public void setExecuting(){
        setSTATUS(STATUS.EXECUTING);
    }

    public void setOnSuccessCallback(Callback onSuccessCallback) {
        this.onSuccessCallback = (arg) -> onSuccessCallback.execute();
    }

    public void setOnSuccessCallback(CallbackWithArgument<ResultType> onSuccessCallback) {
        this.onSuccessCallback = onSuccessCallback;
    }

    public void setOnFailedCallback(Callback onFailedCallback) {
        this.onFailedCallback = (arg) -> onFailedCallback.execute();
    }

    public void setOnFailedCallback(CallbackWithArgument<ResultType> onFailedCallback) {
        this.onFailedCallback = onFailedCallback;
    }

    public void setOnCanceledCallback(Callback onCanceledCallback) {
        this.onCanceledCallback = (arg) -> onCanceledCallback.execute();
    }

    public void setOnCanceledCallback(CallbackWithArgument<ResultType> onCanceledCallback) {
        this.onCanceledCallback = onCanceledCallback;
    }

    public void setOnExecutingCallback(Callback onExecutingCallback) {
        this.onExecutingCallback = onExecutingCallback;
    }

    public void setOnProgressChangedCallback(CallbackWithArgument<ProgressType> onProgressChangedCallback) {
        this.onProgressChangedCallback = onProgressChangedCallback;
    }

    public void setOnProgressChangedCallback(Callback onProgressChangedCallback) {
        this.onProgressChangedCallback = (arg) -> onProgressChangedCallback.execute();
    }

    public void addOnProgressChangedCallback(CallbackWithArgument<ProgressType> onProgressChangedCallback){
        this.onProgressChangedCallbackList.addCallback(onProgressChangedCallback);
    }

    public void addOnProgressChangedCallback(Callback<ProgressType> onProgressChangedCallback){
        this.onProgressChangedCallbackList.addCallback((arg) -> onProgressChangedCallback.execute());
    }

    public void addOnFinishCallback(CallbackWithArgument<ResultType> callback){
        this.onFinishCallbackList.addCallback(callback);
    }

    public void addOnFinishCallback(Callback callback){
        this.onFinishCallbackList.addCallback((arg) -> callback.execute());
    }

    public void addOnCanceledCallback(CallbackWithArgument<ResultType> callback){
        this.onCanceledCallbackList.addCallback(callback);
    }

    public void addOnCanceledCallback(Callback callback){
        this.onCanceledCallbackList.addCallback((arg) -> callback.execute());
    }

    public void addOnStatusChangedCallback(CallbackWithArgument<TASKSTATUS> callback){
        this.onStatusChangedCallbackList.addCallback(callback);
    }

    public void addOnStatusChangedCallback(Callback callback){
        this.onStatusChangedCallbackList.addCallback((arg) -> callback.execute());
    }

    public void addOnSuccessCallback(Callback callback){
        this.onSuccessCallbackList.addCallback((arg) -> callback.execute());
    }

    public void addOnSuccessCallback(CallbackWithArgument<ResultType> callback){
        this.onSuccessCallbackList.addCallback(callback);
    }

    public void addOnFailedCallback(Callback callback){
        this.onFailedCallbackList.addCallback((arg) -> callback.execute());
    }

    public void addOnFailedCallback(CallbackWithArgument<ResultType> callback){
        this.onFailedCallbackList.addCallback(callback);
    }

    public void addOnExecutingCallback(Callback callback){
        this.onExecutingCallbackList.addCallback(callback);
    }

    public ProgressType getProgress() {
        return progress;
    }

    public void setProgress(ProgressType progress) {
        this.progress = progress;
        this.onProgressChangedCallback.execute(progress);
    }
}



