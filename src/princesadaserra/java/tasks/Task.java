package princesadaserra.java.tasks;

public abstract class Task extends Thread {
    private Thread thread;
    private STATUS status;

    private CallbackList onSuccessCallbackList;
    private CallbackList onFailedCallbackList;
    private CallbackList onCanceledCallbackList;
    private CallbackList onFinishCallbackList;
    private CallbackList onExecutingCallbackList;
    private CallbackList onStatusChangedCallbackList;

    private Callback onSuccessCallback;
    private Callback onFailedCallback;
    private Callback onCanceledCallback;
    private Callback onFinishCallback;
    private Callback onExecutingCallback;
    private Callback onStatusChangedCallback;

    public abstract void execute();

    public Task(){
        this.status = STATUS.INITIAL;

        onSuccessCallbackList = new CallbackList();
        onFailedCallbackList = new CallbackList();
        onCanceledCallbackList = new CallbackList();
        onFinishCallbackList = new CallbackList();
        onExecutingCallbackList = new CallbackList();
        onStatusChangedCallbackList = new CallbackList();

        this.onSuccessCallback = () -> this.onSuccessCallbackList.executeAll();
        this.onFailedCallback = () -> this.onFailedCallbackList.executeAll();
        this.onCanceledCallback = () -> this.onCanceledCallbackList.executeAll();
        this.onFinishCallback = () -> this.onFinishCallbackList.executeAll();
        this.onExecutingCallback = () -> this.onExecutingCallbackList.executeAll();
        this.onStatusChangedCallback = () -> this.onStatusChangedCallbackList.executeAll();

    }

    public void start(){
        this.thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run(){
        setExecuting();
        onExecutingCallback.execute();

        execute();

        if (status == STATUS.EXECUTING) setSuccess();

        switch (status){
            case SUCCESS:
                onSuccessCallback.execute();
                break;
            case FAILED:
                onFailedCallback.execute();
                break;
            case CANCELED:
                onCanceledCallback.execute();
                break;
        }

        onFinishCallback.execute();
    }

    public boolean isExecuting(){
        return getStatus() == STATUS.EXECUTING;
    }

    public void setOnStatusChangedCallback(Callback onStatusChangedCallback) {
        this.onStatusChangedCallback = onStatusChangedCallback;
    }

    private void setStatus(STATUS status){
        this.status = status;
        onStatusChangedCallback.execute();
    }

    public STATUS getStatus(){
        return this.status;
    }

    public void setFailed(){
        setStatus(STATUS.FAILED);
    }

    public void setSuccess(){
        setStatus(STATUS.SUCCESS);
    }

    public void setCanceled(){
        setStatus(STATUS.CANCELED);
    }

    public void setExecuting(){
        setStatus(STATUS.EXECUTING);
    }

    public void setOnSuccessCallback(Callback onSuccessCallback) {
        this.onSuccessCallback = onSuccessCallback;
    }

    public void setOnFailedCallback(Callback onFailedCallback) {
        this.onFailedCallback = onFailedCallback;
    }

    public void setOnCanceledCallback(Callback onCanceledCallback) {
        this.onCanceledCallback = onCanceledCallback;
    }

    public void setOnFinishCallback(Callback onFinishCallback) {
        this.onFinishCallback = onFinishCallback;
    }

    public void setOnExecutingCallback(Callback onExecutingCallback) {
        this.onExecutingCallback = onExecutingCallback;
    }

    public void addOnFinishCallback(Callback callback){
        this.onFinishCallbackList.addCallback(callback);
    }

    public void addOnSuccessCallback(Callback callback){
        this.onSuccessCallbackList.addCallback(callback);
    }

    public void addOnFailedCallback(Callback callback){
        this.onFailedCallbackList.addCallback(callback);
    }

    public void addOnCanceledCallback(Callback callback){
        this.onCanceledCallbackList.addCallback(callback);
    }

    public void addOnExecutingCallback(Callback callback){
        this.onExecutingCallbackList.addCallback(callback);
    }

    public void addOnStatusChangedCallback(Callback callback){
        this.onStatusChangedCallbackList.addCallback(callback);
    }

    enum STATUS {
        SUCCESS,
        FAILED,
        EXECUTING,
        INITIAL,
        CANCELED
    }
}



