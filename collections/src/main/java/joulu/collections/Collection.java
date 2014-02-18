package joulu.collections;

import joulu.equivalence.Equivalence;
import joulu.equivalence.Filter;
import joulu.optional.Optional;

public interface Collection<T> {

	int size();

	boolean contains(T value);

	boolean contains(Equivalence<T> eq, T value);

	boolean contains(Filter<T> filter);

	Optional<T> findOne(Filter<T> filter);
}
