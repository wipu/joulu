package org.fluentjava.joulu.unsignedbyte;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ByteArrayPrettyPrinterTest {

	private static String toHex(int... bytes) {
		byte[] reallyBytes = ByteUtil.byteArray(bytes);
		return ByteArrayPrettyPrinter.spaceSeparatedHex(reallyBytes);
	}

	@Test
	public void emptyAsHex() {
		assertEquals("", toHex());
	}

	@Test
	public void oneAsHex() {
		assertEquals("01", toHex(1));
	}

	@Test
	public void manyAsHex() {
		assertEquals("80 FF 00 0A 7F", toHex(-128, -1, 0, 10, 127));
	}

}
