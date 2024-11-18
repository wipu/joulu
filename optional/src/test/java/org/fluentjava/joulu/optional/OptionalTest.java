package org.fluentjava.joulu.optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class OptionalTest {

	@Test
	public void createAndGetPresentOptional() {
		Optional<String> optional = Optional.of("foo");
		assertTrue(optional.isPresent());
		assertFalse(optional.isAbsent());
		assertEquals("foo", optional.value());
	}

	@Test
	public void createAndGetAbsentOptional() {
		Optional<String> optional = Optional.absent();
		assertFalse(optional.isPresent());
		assertTrue(optional.isAbsent());
		try {
			optional.value();
			fail();
		} catch (NullPointerException e) {
			assertEquals("Absent object has no value", e.getMessage());
		}
	}

	@Test
	public void nullOptionalIsAllowedAndIsAlwaysTheSameInstance() {
		Optional<String> absent1 = Optional.of(null);
		Optional<Integer> absent2 = Optional.of(null);
		Optional<Float> absent3 = Optional.absent();

		assertSame(absent1, absent2);
		assertSame(absent1, absent3);
	}

	@Test
	public void createAbsentOptional() {
		Optional<Integer> absent = Optional.absent();
		assertTrue(absent.isAbsent());
		assertFalse(absent.isPresent());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void presentOptionalEqualsAndHashcode() {
		Optional<Integer> a1 = Optional.of(100);
		Optional<Integer> a2 = Optional.of(100);
		Optional<Integer> b = Optional.of(50);
		String s = "s";

		assertEquals(a1, a2);
		assertFalse(b.equals(a2));
		assertFalse(b.equals(s));

		assertEquals(a1.hashCode(), a2.hashCode());
	}

	@Test
	public void absentOptionalEquality() {
		Optional<Integer> a1 = Optional.absent();
		Optional<Integer> a2 = Optional.absent();
		Optional<String> b = Optional.absent();

		assertEquals(a1, a2);
		assertEquals(b, a2);

		assertEquals(a1.hashCode(), a2.hashCode());
	}

	@Test
	public void absentAndPresentOptionalEquality() {
		Optional<Integer> absent = Optional.absent();
		Optional<Integer> present = Optional.of(100);
		assertFalse(absent.equals(present));
		assertFalse(present.equals(absent));
		assertFalse(present.equals(null));
	}

	@Test
	public void presentOptionalOr() {
		Optional<Integer> present = Optional.of(10);
		assertEquals(Integer.valueOf(10), present.or(5));
	}

	@Test
	public void absentOptionalOr() {
		Optional<Integer> absent = Optional.absent();
		assertEquals(Integer.valueOf(5), absent.or(5));
	}
}
