package pl.parser.nbp.bussiness.currency.control;

public interface EventHandler<T> {
    void handle(T value);
}
