package org.fluentjava.joulu.collections;

import org.fluentjava.joulu.equivalence.Equivalence;
import org.fluentjava.joulu.equivalence.Filter;
import org.fluentjava.joulu.optional.Optional;

public interface Collection<T> extends Iterable<T> {

	int size();

	boolean contains(T value);

	boolean contains(Equivalence<T> eq, T value);

	boolean contains(Filter<T> filter);

	Optional<T> findOne(Filter<T> filter);
}
