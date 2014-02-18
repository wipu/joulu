package joulu.collections;

import joulu.equivalence.Equivalence;
import joulu.equivalence.Filter;

public class ImmutableSet<T> implements Set<T> {

	private final Equivalence<T> eq;
	private final T[] values;

	private static class Empty<T> implements Set<T> {

		@Override
		public int size() {
			return 0;
		}

		@Override
		public boolean contains(T value) {
			return false;
		}

		@Override
		public boolean contains(Equivalence<T> eq, T value) {
			return false;
		}

		@Override
		public boolean contains(Filter<T> filter) {
			return false;
		}

	}

	public ImmutableSet(Equivalence<T> eq, T[] values) {
		this.eq = eq;
		this.values = values;
	}

	public static <T> Set<T> empty() {
		return new Empty<T>();
	}

	public static <T> Set<T> of(Equivalence<T> eq, T... values) {
		return new ImmutableSet<T>(eq, values);
	}

	@Override
	public int size() {
		return values.length;
	}

	@Override
	public boolean contains(T value) {
		return contains(eq, value);
	}

	@Override
	public boolean contains(Equivalence<T> eq, T value) {
		for (T candidate : values) {
			if (eq.areEquivalent(candidate, value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(Filter<T> filter) {
		for (T candidate : values) {
			if (filter.matches(candidate)) {
				return true;
			}
		}
		return false;
	}
}
