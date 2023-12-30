package consumer;

public interface Consumer {
    default void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
