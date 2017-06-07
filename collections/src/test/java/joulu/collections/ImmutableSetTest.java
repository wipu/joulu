package joulu.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import joulu.equivalence.Equivalence;
import joulu.equivalence.Filter;
import joulu.optional.Optional;

public class ImmutableSetTest {

	@Test
	public void emptySet() {
		Set<String> empty = ImmutableSet.empty();

		assertEquals(0, empty.size());

		assertFalse(empty.contains("1"));

	}

	@Test
	public void emptySetWithOf() {
		Set<String> empty = ImmutableSet.of();

		assertEquals(0, empty.size());

		assertFalse(empty.contains("1"));
	}

	@Test
	public void emptySetWithEquivalence() {
		Set<String> empty = ImmutableSet.empty();
		Equivalence<String> eq = new Equivalence<String>() {
			@Override
			public boolean areEquivalent(String value1, String value2) {
				return true;
			}
		};

		assertFalse(empty.contains(eq, "foo"));
	}

	@Test
	public void emptySetWithFilter() {
		Set<String> empty = ImmutableSet.empty();
		Filter<String> filter = new Filter<String>() {
			@Override
			public boolean matches(String t) {
				return true;
			}
		};
		assertFalse(empty.contains(filter));

		Optional<String> elem = empty.findOne(filter);
		assertTrue(elem.isAbsent());
	}

	@Test
	public void emptySetIterator() {
		Set<String> empty = ImmutableSet.empty();
		Iterator<String> iterator = empty.iterator();
		assertFalse(iterator.hasNext());
		try {
			iterator.next();
			fail();
		} catch (NoSuchElementException e) {
			// empty
		}
		try {
			iterator.remove();
			fail();
		} catch (UnsupportedOperationException e) {
			// empty
		}
	}

	@Test
	public void containsWithConstructionTimeEquivalence() {
		Equivalence<String> eq = new Equivalence<String>() {
			@Override
			public boolean areEquivalent(String value1, String value2) {
				return value1.trim().equals(value2.trim());
			}
		};

		Set<String> set = ImmutableSet.of(eq, "1", "2");

		assertTrue(set.contains("1"));
		assertTrue(set.contains(" 1 "));
		assertTrue(set.contains(" 2 "));
		assertFalse(set.contains("3"));
	}

	@Test
	public void containsWithOverriddenEquivalence() {
		Equivalence<String> eq = new Equivalence<String>() {
			@Override
			public boolean areEquivalent(String value1, String value2) {
				return value1.equals(value2);
			}
		};
		Set<String> set = ImmutableSet.of(eq, "Foo", "Bar");
		Equivalence<String> laxEq = new Equivalence<String>() {

			@Override
			public boolean areEquivalent(String value1, String value2) {
				return value1.equalsIgnoreCase(value2);
			}

		};
		assertFalse(set.contains("foo"));
		assertTrue(set.contains(laxEq, "foo"));

	}

	@Test
	public void sizeOfNonEmpty() {
		assertEquals(2, ImmutableSet.of("1", "2").size());
		assertEquals(3, ImmutableSet.of("1", "2", "3").size());
	}

	@Test
	public void withMatchingFilter() {
		Set<String> set = ImmutableSet.of("1", "2");
		final String value = "2";

		Filter<String> filter = new Filter<String>() {

			@Override
			public boolean matches(String other) {
				return value.equals(other);
			}
		};

		assertTrue(set.contains(filter));
	}

	@Test
	public void withNotMatchingFilter() {
		Set<String> set = ImmutableSet.of("1", "2");

		Filter<String> filter = new Filter<String>() {

			@Override
			public boolean matches(String other) {
				return false;
			}
		};

		assertFalse(set.contains(filter));
	}

	@Test
	public void setWithNaturalEquivalence() {
		Set<Integer> set = ImmutableSet.of(1, 2);
		assertTrue(set.contains(2));
		assertFalse(set.contains(0));
	}

	@Test
	public void findOne() {
		Set<Integer> set = ImmutableSet.of(1, 3, 5);
		final int limit = 2;

		Filter<Integer> greaterThan3 = new Filter<Integer>() {

			@Override
			public boolean matches(Integer value) {
				return value > limit;
			}

		};
		assertEquals(Integer.valueOf(3), set.findOne(greaterThan3).value());

		final int anotherLimit = 5;
		Filter<Integer> greaterThan5 = new Filter<Integer>() {

			@Override
			public boolean matches(Integer value) {
				return value > anotherLimit;
			}

		};
		assertEquals(Optional.absent(), set.findOne(greaterThan5));
	}

	@Test
	public void repeatedValuesNotAllowed() {
		Set<Integer> set = ImmutableSet.of(1, 1);
		assertEquals(1, set.size());
		assertTrue(set.contains(1));

		set = ImmutableSet.of(1, 2);
		assertEquals(2, set.size());
		assertTrue(set.contains(1));
		assertTrue(set.contains(2));

		set = ImmutableSet.of(1, 2, 2, 1, 3);
		assertEquals(3, set.size());
		assertTrue(set.contains(1));
		assertTrue(set.contains(2));
		assertTrue(set.contains(3));

		set = ImmutableSet.of(1, 1, 2, 2, 5, 5, 5);
		assertEquals(3, set.size());
		assertTrue(set.contains(1));
		assertTrue(set.contains(2));
		assertTrue(set.contains(5));
	}

	@Test
	public void setIsIterable() {
		Set<Integer> set = ImmutableSet.of();
		assertFalse(set.iterator().hasNext());

		set = ImmutableSet.of(1);
		assertTrue(set.iterator().hasNext());
		for (Integer i : set) {
			assertEquals(Integer.valueOf(1), i);
		}

		set = ImmutableSet.of(1, 2, 3, 4);
		int expected = 1;
		for (Integer i : set) {
			assertEquals(Integer.valueOf(expected), i);
			expected++;
		}
		assertEquals(5, expected);
	}

	@Test
	public void iteratorRemoveThrowsException() {
		Iterator<Integer> iterator = ImmutableSet.of(100).iterator();
		try {
			iterator.remove();
			fail();
		} catch (UnsupportedOperationException e) {
			// empty
		}
	}

	@Test
	public void iteratorNextThrowsExceptionWhenSizeExceeded() {
		Iterator<Integer> iterator = ImmutableSet.of(100).iterator();
		iterator.next();
		try {
			iterator.next();
			fail();
		} catch (NoSuchElementException e) {
			// empty
		}
	}
}
