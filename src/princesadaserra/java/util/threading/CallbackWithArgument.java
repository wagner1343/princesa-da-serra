package princesadaserra.java.util.threading;

public interface CallbackWithArgument<ArgumentType> {
    void execute(ArgumentType argument);
}
