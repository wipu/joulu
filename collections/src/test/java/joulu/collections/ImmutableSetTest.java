package joulu.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import joulu.equivalence.Equivalence;
import joulu.equivalence.Filter;

import org.junit.Test;

public class ImmutableSetTest {

	@Test
	public void emptySet() {
		Set<String> empty = ImmutableSet.empty();

		assertEquals(0, empty.size());

		assertFalse(empty.contains("1"));

		Filter<String> filter = new Filter<String>() {
			@Override
			public boolean matches(String t) {
				return true;
			}
		};
		assertFalse(empty.contains(filter));
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
		Equivalence<String> eq = null;

		assertEquals(2, ImmutableSet.of(eq, "1", "2").size());
		assertEquals(3, ImmutableSet.of(eq, "1", "2", "3").size());
	}

	@Test
	public void containsWithFilter() {
		Equivalence<String> eq = new Equivalence<String>() {

			@Override
			public boolean areEquivalent(String value1, String value2) {
				return value1.equals(value2);
			}

		};

		Set<String> set = ImmutableSet.of(eq, "1", "2");
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
	public void setWithNaturalEquivalence() {
		Set<Integer> set = ImmutableSet.of(1, 2);
		assertTrue(set.contains(2));
	}
}
