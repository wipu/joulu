package joulu.collections;

import joulu.equivalence.Equivalence;

public interface Collection<T> {

	int size();

	boolean contains(T value);

	boolean contains(Equivalence<T> eq, T value);
}
