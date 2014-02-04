package joulu.stronglytyped;

import org.junit.Assert;
import org.junit.Test;

public class TypedFloatTest {
	class MyFloat extends TypedFloat {

		public MyFloat(Float value) {
			super(value);
		}
	}

	@Test
	public void equalsAndHashcodeUsesValue() {
		TypedFloat a1 = new MyFloat(1.0f);
		TypedFloat a2 = new MyFloat(1.0f);
		TypedFloat b = new MyFloat(2.0f);

		Assert.assertTrue(a1.equals(a2));
		Assert.assertFalse(a2.equals(b));
	}
}
