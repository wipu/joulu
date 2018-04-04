package joulu.stronglytyped;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TypedIntegerTest {

	class MyInteger extends TypedInteger {
		public MyInteger(Integer value) {
			super(value);
		}
	}

	class MyInteger2 extends TypedInteger {
		public MyInteger2(Integer value) {
			super(value);
		}
	}

	@SuppressWarnings("unused")
	@Test
	public void nullIsInvalidValue() {
		try {
			new MyInteger(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("Value must not be null", e.getMessage());
		}
	}

	@Test
	public void valueIsGivenByValueAndToStringMethods() {
		TypedInteger one = new MyInteger(1);
		assertEquals(Integer.valueOf(1), one.value());
		assertEquals("1", one.toString());

		TypedInteger two = new MyInteger(2);
		assertEquals(Integer.valueOf(2), two.value());
		assertEquals("2", two.toString());
	}

	@Test
	public void equalsAndHashcodeUsesValue() {
		TypedInteger a1 = new MyInteger(1);
		TypedInteger a2 = new MyInteger(1);
		TypedInteger b = new MyInteger(2);

		assertTrue(a1.equals(a2));
		assertFalse(a1.equals(b));

		assertEquals(a1.hashCode(), a2.hashCode());
	}

	@Test
	public void subclassValue() {
		MyInteger m = new MyInteger(1);
		assertEquals(Integer.valueOf(1), m.value());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void objectsOfDifferentConcreteTypeAreNotEqual() {
		MyInteger m1 = new MyInteger(1);
		MyInteger2 m2 = new MyInteger2(1);

		assertFalse(m1.equals(m2));
	}

}
