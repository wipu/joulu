package org.fluentjava.joulu.collections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.fluentjava.joulu.equivalence.Equivalence;
import org.fluentjava.joulu.equivalence.Filter;
import org.fluentjava.joulu.optional.Optional;

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

		@Override
		public Iterator<T> iterator() {
			return new Iterator<T>() {

				@Override
				public boolean hasNext() {
					return false;
				}

				@Override
				public T next() {
					throw new NoSuchElementException();
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
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
		T[] distinct = (T[]) Array.newInstance(values[0].getClass(),
				values.length);
		// distinct[0] = values[0];
		int distinctCount = 0;
		for (int i = 0; i < values.length; i++) {
			for (T dv : distinct) {
				if (dv == null) {
					distinct[distinctCount] = values[i];
					distinctCount++;
					break;
				}
				if (eq.areEquivalent(values[i], dv)) {
					break;
				}
			}
		}
		return Arrays.copyOf(distinct, distinctCount);
	}

	public static <T> Set<T> empty() {
		return new Empty<>();
	}

	@SafeVarargs
	public static <T> Set<T> of(Equivalence<T> eq, T... values) {
		return new ImmutableSet<>(eq, values);
	}

	@SafeVarargs
	public static <T> Set<T> of(T... values) {
		return new ImmutableSet<>(new NaturalEquivalence<T>(), values);
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

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator<>(values);
	}

	private static class ArrayIterator<T> implements Iterator<T> {
		private T[] values;
		private int index = 0;

		private ArrayIterator(T[] values) {
			this.values = values;
		}

		@Override
		public boolean hasNext() {
			return values.length > index;
		}

		@Override
		public T next() {
			try {
				T value = values[index];
				index++;
				return value;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
