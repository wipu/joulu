package org.fluentjava.joulu.unsignedbyte;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ByteUtilTest {

	/**
	 * TODO find a way to tell emma to ignore default constructors
	 */
	@Test
	public void itCanBeInstantiatedAlthoughItIsJustAStaticUtil() {
		@SuppressWarnings("unused")
		ByteUtil u = new ByteUtil();
	}

	@Test
	public void emptyIntsToBytes() {
		assertEquals(0, ByteUtil.byteArray().length);
	}

	@Test
	public void someIntsToBytes() {
		byte[] bytes = ByteUtil.byteArray(-1, 0, 10);
		assertEquals(3, bytes.length);
		assertEquals(-1, bytes[0]);
		assertEquals(0, bytes[1]);
		assertEquals(10, bytes[2]);
	}

	@Test
	public void overflowIsAllowed() {
		assertEquals("[127, -128, 24, -24]",
				Arrays.toString(ByteUtil.byteArray(-129, 128, -1000, 1000)));
	}

}
