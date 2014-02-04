package joulu.optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

public class OptionalTest {

	@Test
	public void createAndGetPresentOptional() {
		Optional<String> optional = Optional.of("foo");
		Assert.assertTrue(optional.isPresent());
		Assert.assertFalse(optional.isAbsent());
		Assert.assertEquals("foo", optional.value());
	}

	@Test
	public void createAndGetAbsentOptional() {
		Optional<String> optional = Optional.absent();
		Assert.assertFalse(optional.isPresent());
		Assert.assertTrue(optional.isAbsent());
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
		Assert.assertTrue(absent.isAbsent());
		Assert.assertFalse(absent.isPresent());
	}

	@Test
	public void presentOptionalEqualsAndHashcode() {
		Optional<Integer> a1 = Optional.of(100);
		Optional<Integer> a2 = Optional.of(100);
		Optional<Integer> b = Optional.of(50);

		Assert.assertEquals(a1, a2);
		Assert.assertFalse(b.equals(a2));

		assertEquals(a1.hashCode(), a2.hashCode());
	}

	@Test
	public void absentOptionalEquality() {
		Optional<Integer> a1 = Optional.absent();
		Optional<Integer> a2 = Optional.absent();
		Optional<String> b = Optional.absent();

		Assert.assertEquals(a1, a2);
		Assert.assertEquals(b, a2);

		assertEquals(a1.hashCode(), a2.hashCode());
	}
}
