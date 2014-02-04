package joulu.equivalence;

public interface Equivalence<T> {

	boolean areEquivalent(T value1, T value2);

}
