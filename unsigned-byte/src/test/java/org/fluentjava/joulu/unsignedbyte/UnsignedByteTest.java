package org.fluentjava.joulu.unsignedbyte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class UnsignedByteTest {

	@Test
	public void fromByteAndToByte() {
		assertEquals((byte) 0x00, UnsignedByte.from((byte) 0x00).signedByte());
		assertEquals((byte) 0x01, UnsignedByte.from((byte) 0x01).signedByte());
		assertEquals((byte) 0xFF, UnsignedByte.from((byte) 0xFF).signedByte());
	}

	@Test
	public void fromSignedInt() {
		assertEquals(UnsignedByte.x00, UnsignedByte.from(0));
		assertEquals(UnsignedByte.x01, UnsignedByte.from(1));
		assertEquals(UnsignedByte.x7F, UnsignedByte.from(127));
		assertEquals(UnsignedByte.x80, UnsignedByte.from(128));
		assertEquals(UnsignedByte.x80, UnsignedByte.from(-128));
		assertEquals(UnsignedByte.x7F, UnsignedByte.from(-129));
		assertEquals(UnsignedByte.xFF, UnsignedByte.from(-1));

		// overflow:
		assertEquals(UnsignedByte.x00, UnsignedByte.from(256));
		assertEquals(UnsignedByte.x01, UnsignedByte.from(257));
	}

	@Test
	public void fromSignedLong() {
		assertEquals(UnsignedByte.x00, UnsignedByte.from(0L));
		assertEquals(UnsignedByte.x01, UnsignedByte.from(1L));
		assertEquals(UnsignedByte.x7F, UnsignedByte.from(127L));
		assertEquals(UnsignedByte.x80, UnsignedByte.from(128L));
		assertEquals(UnsignedByte.x80, UnsignedByte.from(-128L));
		assertEquals(UnsignedByte.x7F, UnsignedByte.from(-129L));
		assertEquals(UnsignedByte.xFF, UnsignedByte.from(-1L));

		// overflow:
		assertEquals(UnsignedByte.x00, UnsignedByte.from(256L));
		assertEquals(UnsignedByte.x01, UnsignedByte.from(257L));
	}

	@Test
	public void fromLsbOf() {
		assertEquals(UnsignedByte.x00, UnsignedByte.fromLsbOf(0));
		assertEquals(UnsignedByte.x00, UnsignedByte.fromLsbOf(256));

		assertEquals(UnsignedByte.x7F, UnsignedByte.fromLsbOf(127));
		assertEquals(UnsignedByte.x7F, UnsignedByte.fromLsbOf(-129));

		assertEquals(UnsignedByte.x80, UnsignedByte.fromLsbOf(128));
		assertEquals(UnsignedByte.x80, UnsignedByte.fromLsbOf(-128));

		assertEquals(UnsignedByte.xFF, UnsignedByte.fromLsbOf(255));
		assertEquals(UnsignedByte.xFF, UnsignedByte.fromLsbOf(-1));

		assertEquals(UnsignedByte.x01, UnsignedByte.fromLsbOf(513));
	}

	@Test
	public void toUnsignedInt() {
		assertEquals(0, UnsignedByte.x00.uInt());
		assertEquals(255, UnsignedByte.xFF.uInt());
	}

	@Test
	public void toUnsignedLong() {
		assertEquals(0L, UnsignedByte.x00.uLong());
		assertEquals(255L, UnsignedByte.xFF.uLong());
	}

	@Test
	public void withBit0() {
		assertEquals(0x01, UnsignedByte.from(0x00).withBit0(true).uInt());
		assertEquals(0xFE, UnsignedByte.from(0xFF).withBit0(false).uInt());
	}

	@Test
	public void isBit0() {
		assertTrue(UnsignedByte.x01.isBit0());
		assertFalse(UnsignedByte.xFE.isBit0());
	}

	@Test
	public void withBit1() {
		assertEquals(0x02, UnsignedByte.from(0x00).withBit1(true).uInt());
		assertEquals(0xFD, UnsignedByte.from(0xFF).withBit1(false).uInt());
	}

	@Test
	public void isBit1() {
		assertTrue(UnsignedByte.x02.isBit1());
		assertFalse(UnsignedByte.xFD.isBit1());
	}

	@Test
	public void withBit2() {
		assertEquals(0x02, UnsignedByte.from(0x00).withBit2(true).uInt());
		assertEquals(0xFD, UnsignedByte.from(0xFF).withBit2(false).uInt());
	}

	@Test
	public void isBit2() {
		assertTrue(UnsignedByte.x04.isBit2());
		assertFalse(UnsignedByte.xFB.isBit2());
	}

	@Test
	public void isBit3() {
		assertTrue(UnsignedByte.x08.isBit3());
		assertFalse(UnsignedByte.xF7.isBit3());
	}

	@Test
	public void isBit4() {
		assertTrue(UnsignedByte.x10.isBit4());
		assertFalse(UnsignedByte.xEF.isBit4());
	}

	@Test
	public void isBit5() {
		assertTrue(UnsignedByte.x20.isBit5());
		assertFalse(UnsignedByte.xDF.isBit5());
	}

	@Test
	public void withBit6() {
		assertEquals(0x40, UnsignedByte.x00.withBit6(true).uInt());
		assertEquals(0xBF, UnsignedByte.xFF.withBit6(false).uInt());
	}

	@Test
	public void isBit6() {
		assertTrue(UnsignedByte.x40.isBit6());
		assertFalse(UnsignedByte.xBF.isBit6());
	}

	@Test
	public void withBit7() {
		assertEquals(0x80, UnsignedByte.x00.withBit7(true).uInt());
		assertEquals(0x7F, UnsignedByte.xFF.withBit7(false).uInt());
	}

	@Test
	public void isBit7() {
		assertTrue(UnsignedByte.x80.isBit7());
		assertFalse(UnsignedByte.x7F.isBit7());
	}

	@Test
	public void toStringIsHex() {
		assertEquals("#$00", UnsignedByte.x00.toString());
		assertEquals("#$8C", UnsignedByte.x8C.toString());
	}

	@Test
	public void equalsUsesValue() {
		assertTrue(UnsignedByte.from(1).equals(UnsignedByte.from(1)));
		assertFalse(UnsignedByte.from(-1).equals(UnsignedByte.from(1)));
	}

	@Test
	public void equalsWorksLikeForPrimitives() {
		assertTrue(UnsignedByte.from(1) == UnsignedByte.from(1));
		assertFalse(UnsignedByte.from(-1) == UnsignedByte.from(1));
	}

	@Test
	public void hashCodeIsValue() {
		assertEquals(10, UnsignedByte.x0A.hashCode());
		assertEquals(240, UnsignedByte.xF0.hashCode());
	}

	@Test
	public void and() {
		assertEquals(UnsignedByte.x00, UnsignedByte.xF0.and(UnsignedByte.x0F));
		assertEquals(UnsignedByte.x21, UnsignedByte.xF1.and(UnsignedByte.x2F));
	}

	@Test
	public void or() {
		assertEquals(UnsignedByte.xAB, UnsignedByte.xA0.or(UnsignedByte.x0B));
		assertEquals(UnsignedByte.x3E, UnsignedByte.x1E.or(UnsignedByte.x20));
	}

	@Test
	public void eor() {
		assertEquals(UnsignedByte.xAB, UnsignedByte.xA0.eor(UnsignedByte.x0B));
		assertEquals(UnsignedByte.xEE, UnsignedByte.xF1.eor(UnsignedByte.x1F));
		assertEquals(UnsignedByte.x12, UnsignedByte.x01.eor(UnsignedByte.x13));
	}

	@Test
	public void plus() {
		assertEquals(UnsignedByte.x01, UnsignedByte.x00.plus(UnsignedByte.x01));
		assertEquals(UnsignedByte.xFF, UnsignedByte.x00.plus(UnsignedByte.xFF));
		assertEquals(UnsignedByte.x81, UnsignedByte.x7F.plus(UnsignedByte.x02));
		assertEquals(UnsignedByte.x93, UnsignedByte.x12.plus(UnsignedByte.x81));
	}

	@Test
	public void plusSignedInt() {
		assertEquals(UnsignedByte.x01, UnsignedByte.x00.plusSigned(1));
		assertEquals(UnsignedByte.xFF, UnsignedByte.x00.plusSigned(-1));
		assertEquals(UnsignedByte.x81, UnsignedByte.x7F.plusSigned(2));
		assertEquals(UnsignedByte.x02, UnsignedByte.x01.plusSigned(513));
	}

	@Test
	public void not() {
		assertEquals(UnsignedByte.xFF, UnsignedByte.x00.not());
		assertEquals(UnsignedByte.xFE, UnsignedByte.x01.not());
		assertEquals(UnsignedByte.x0C, UnsignedByte.xF3.not());
	}

	@Test
	public void isZero() {
		assertTrue(UnsignedByte.x00.isZero());
		assertFalse(UnsignedByte.x01.isZero());
	}

	@Test
	public void isNegative() {
		assertFalse(UnsignedByte.x00.isNegative());
		assertFalse(UnsignedByte.x01.isNegative());
		assertFalse(UnsignedByte.x7F.isNegative());
		assertTrue(UnsignedByte.x80.isNegative());
		assertTrue(UnsignedByte.xFF.isNegative());
	}

	/**
	 * Unignore to get an error message to copy new code from, if needed.
	 */
	@Test
	@Disabled
	public void constantDef() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < 256; i++) {
			String hex = ByteArrayPrettyPrinter.spaceSeparatedHex((byte) i);
			b.append("	public static final UnsignedByte x" + hex
					+ " = UnsignedByte.from(" + i + ");\n");
		}
		assertEquals("copy this to UnsignedByte.java", b.toString());
	}

}
