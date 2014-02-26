package joulu.collections;

import java.lang.reflect.Array;
import java.util.Arrays;

import joulu.equivalence.Equivalence;
import joulu.equivalence.Filter;
import joulu.optional.Optional;

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

		@Override
		public Optional<T> findOne(Filter<T> filter) {
			return Optional.absent();
		}

	}

	public ImmutableSet(Equivalence<T> eq, T[] values) {
		this.eq = eq;
		this.values = distinctValues(values);
	}
	
	private T[] distinctValues(T[] values) {
		if (values.length == 0 || values.length == 1) {
			return values;
		}
		@SuppressWarnings("unchecked")
		T[] distinct = (T[]) Array.newInstance(values[0].getClass(), values.length);
		distinct[0] = values[0];
		int index = 1;
		for (int i = 1; i < values.length; i++) {
			for (T dv : distinct) {
				if (dv == null) {
					distinct[index] = values[i];
					index++;
					break;
				}
				if (eq.areEquivalent(values[i], dv)) {
					break;
				}
			}
		}
		return Arrays.copyOf(distinct, index);
	}

	public static <T> Set<T> empty() {
		return new Empty<T>();
	}

	public static <T> Set<T> of(Equivalence<T> eq, T... values) {
		return new ImmutableSet<T>(eq, values);
	}

	public static <T> Set<T> of(T... values) {
		return new ImmutableSet<T>(new NaturalEquivalence<T>(), values);
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

	@Override
	public Optional<T> findOne(Filter<T> filter) {
		for (T candidate : values) {
			if (filter.matches(candidate)) {
				return Optional.of(candidate);
			}
		}
		return Optional.absent();
	}
}
