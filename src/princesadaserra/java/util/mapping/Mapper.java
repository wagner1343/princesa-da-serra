package princesadaserra.java.util.mapping;

public interface Mapper<In, Out> {
    Out map(In in);
}
