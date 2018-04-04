package joulu.stronglytyped;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TypedStringTest {

	class MyString extends TypedString {
		public MyString(String value) {
			super(value);
		}
	}

	class MyString2 extends TypedString {
		public MyString2(String value) {
			super(value);
		}
	}

	@SuppressWarnings("unused")
	@Test
	public void nullIsInvalidValue() {
		try {
			new MyString(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("Value must not be null", e.getMessage());
		}
	}

	@Test
	public void valueIsGivenByValueAndToStringMethods() {
		TypedString a = new MyString("a");
		assertEquals("a", a.value());
		assertEquals("a", a.toString());

		TypedString b = new MyString("b");
		assertEquals("b", b.value());
		assertEquals("b", b.toString());
	}

	@Test
	public void equalsAndHashcodeUsesValue() {
		TypedString a1 = new MyString("a");
		TypedString a2 = new MyString("a");
		TypedString b = new MyString("b");

		assertTrue(a1.equals(a2));
		assertTrue(a1.equals(a1));
		assertFalse(a1.equals(b));
		assertFalse(a1.equals(null));

		assertEquals(a1.hashCode(), a2.hashCode());
	}

	@Test
	public void subclassValue() {
		MyString m = new MyString("m");
		assertEquals("m", m.value());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void stringsOfDifferentConcreteTypeAreNotEqual() {
		MyString m1 = new MyString("m");
		MyString2 m2 = new MyString2("m");

		assertFalse(m1.equals(m2));
	}

}
