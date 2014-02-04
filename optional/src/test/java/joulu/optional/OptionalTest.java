package joulu.optional;

import org.junit.Assert;
import org.junit.Test;

public class OptionalTest {

	@Test
	public void createAndGetOptional() {
		Optional<String> optional = Optional.of("foo");
		Assert.assertTrue(optional.isPresent());
		Assert.assertFalse(optional.isAbsent());
		Assert.assertEquals("foo", optional.get());
	}

	@Test
	public void createNullOptional() {
		try {
			Optional.of(null);
			Assert.fail();
		} catch (NullPointerException npe) {
			Assert.assertEquals("Value must not be null", npe.getMessage());
		}
	}

	@Test
	public void createAbsentOptional() {
		Optional<Integer> absent = Optional.absent();
		Assert.assertTrue(absent.isAbsent());
		Assert.assertFalse(absent.isPresent());
	}

	@Test
	public void presentOptionalEquality() {
		Optional<Integer> a1 = Optional.of(100);
		Optional<Integer> a2 = Optional.of(100);
		Optional<Integer> b = Optional.of(50);

		Assert.assertEquals(a1, a2);
		Assert.assertFalse(b.equals(a2));
	}

	@Test
	public void absentOptionalEquality() {
		Optional<Integer> a1 = Optional.absent();
		Optional<Integer> a2 = Optional.absent();
		Optional<String> b = Optional.absent();

		Assert.assertEquals(a1, a2);
		Assert.assertEquals(b, a2);
	}
}
