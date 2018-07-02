package org.fluentjava.joulu.equivalence;

public interface Filter<T> {
	boolean matches(T candidate);
}
