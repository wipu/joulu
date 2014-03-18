package joulu.unsignedbyte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class UnsignedByteTest {

	@Test
	public void fromByteAndToByte() {
		assertEquals((byte) 0x00, UnsignedByte.from((byte) 0x00).signedByte());
		assertEquals((byte) 0x01, UnsignedByte.from((byte) 0x01).signedByte());
		assertEquals((byte) 0xFF, UnsignedByte.from((byte) 0xFF).signedByte());
	}

	@Test
	public void fromSignedInt() {
		assertEquals(UnsignedByte.$00, UnsignedByte.from(0));
		assertEquals(UnsignedByte.$01, UnsignedByte.from(1));
		assertEquals(UnsignedByte.$7F, UnsignedByte.from(127));
		assertEquals(UnsignedByte.$80, UnsignedByte.from(128));
		assertEquals(UnsignedByte.$80, UnsignedByte.from(-128));
		assertEquals(UnsignedByte.$7F, UnsignedByte.from(-129));
		assertEquals(UnsignedByte.$FF, UnsignedByte.from(-1));
	}

	@Test
	public void fromLsbOf() {
		assertEquals(UnsignedByte.$00, UnsignedByte.fromLsbOf(0));
		assertEquals(UnsignedByte.$00, UnsignedByte.fromLsbOf(256));

		assertEquals(UnsignedByte.$7F, UnsignedByte.fromLsbOf(127));
		assertEquals(UnsignedByte.$7F, UnsignedByte.fromLsbOf(-129));

		assertEquals(UnsignedByte.$80, UnsignedByte.fromLsbOf(128));
		assertEquals(UnsignedByte.$80, UnsignedByte.fromLsbOf(-128));

		assertEquals(UnsignedByte.$FF, UnsignedByte.fromLsbOf(255));
		assertEquals(UnsignedByte.$FF, UnsignedByte.fromLsbOf(-1));

		assertEquals(UnsignedByte.$01, UnsignedByte.fromLsbOf(513));
	}

	@Test
	public void toUnsignedInt() {
		assertEquals(0, UnsignedByte.from((byte) 0).uInt());
		assertEquals(255, UnsignedByte.from((byte) 0xFF).uInt());
	}

	@Test
	public void withBit0() {
		assertEquals(0x01, UnsignedByte.from(0x00).withBit0(true).uInt());
		assertEquals(0xFE, UnsignedByte.from(0xFF).withBit0(false).uInt());
	}

	@Test
	public void isBit0() {
		assertTrue(UnsignedByte.$01.isBit0());
		assertFalse(UnsignedByte.$FE.isBit0());
	}

	@Test
	public void withBit1() {
		assertEquals(0x02, UnsignedByte.from(0x00).withBit1(true).uInt());
		assertEquals(0xFD, UnsignedByte.from(0xFF).withBit1(false).uInt());
	}

	@Test
	public void isBit1() {
		assertTrue(UnsignedByte.$02.isBit1());
		assertFalse(UnsignedByte.$FD.isBit1());
	}

	@Test
	public void withBit2() {
		assertEquals(0x02, UnsignedByte.from(0x00).withBit2(true).uInt());
		assertEquals(0xFD, UnsignedByte.from(0xFF).withBit2(false).uInt());
	}

	@Test
	public void isBit2() {
		assertTrue(UnsignedByte.$04.isBit2());
		assertFalse(UnsignedByte.$FB.isBit2());
	}

	@Test
	public void isBit3() {
		assertTrue(UnsignedByte.$08.isBit3());
		assertFalse(UnsignedByte.$F7.isBit3());
	}

	@Test
	public void isBit4() {
		assertTrue(UnsignedByte.$10.isBit4());
		assertFalse(UnsignedByte.$EF.isBit4());
	}

	@Test
	public void isBit5() {
		assertTrue(UnsignedByte.$20.isBit5());
		assertFalse(UnsignedByte.$DF.isBit5());
	}

	@Test
	public void withBit6() {
		assertEquals(0x40, UnsignedByte.$00.withBit6(true).uInt());
		assertEquals(0xBF, UnsignedByte.$FF.withBit6(false).uInt());
	}

	@Test
	public void isBit6() {
		assertTrue(UnsignedByte.$40.isBit6());
		assertFalse(UnsignedByte.$BF.isBit6());
	}

	@Test
	public void withBit7() {
		assertEquals(0x80, UnsignedByte.$00.withBit7(true).uInt());
		assertEquals(0x7F, UnsignedByte.$FF.withBit7(false).uInt());
	}

	@Test
	public void isBit7() {
		assertTrue(UnsignedByte.$80.isBit7());
		assertFalse(UnsignedByte.$7F.isBit7());
	}

	@Test
	public void toStringIsHex() {
		assertEquals("#$00", UnsignedByte.$00.toString());
		assertEquals("#$8C", UnsignedByte.$8C.toString());
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
		assertEquals(10, UnsignedByte.$0A.hashCode());
		assertEquals(240, UnsignedByte.$F0.hashCode());
	}

	@Test
	public void and() {
		assertEquals(UnsignedByte.$00, UnsignedByte.$F0.and(UnsignedByte.$0F));
		assertEquals(UnsignedByte.$21, UnsignedByte.$F1.and(UnsignedByte.$2F));
	}

	@Test
	public void or() {
		assertEquals(UnsignedByte.$AB, UnsignedByte.$A0.or(UnsignedByte.$0B));
		assertEquals(UnsignedByte.$3E, UnsignedByte.$1E.or(UnsignedByte.$20));
	}

	@Test
	public void eor() {
		assertEquals(UnsignedByte.$AB, UnsignedByte.$A0.eor(UnsignedByte.$0B));
		assertEquals(UnsignedByte.$EE, UnsignedByte.$F1.eor(UnsignedByte.$1F));
		assertEquals(UnsignedByte.$12, UnsignedByte.$01.eor(UnsignedByte.$13));
	}

	@Test
	public void plus() {
		assertEquals(UnsignedByte.$01, UnsignedByte.$00.plus(UnsignedByte.$01));
		assertEquals(UnsignedByte.$FF, UnsignedByte.$00.plus(UnsignedByte.$FF));
		assertEquals(UnsignedByte.$81, UnsignedByte.$7F.plus(UnsignedByte.$02));
		assertEquals(UnsignedByte.$93, UnsignedByte.$12.plus(UnsignedByte.$81));
	}

	@Test
	public void plusSignedInt() {
		assertEquals(UnsignedByte.$01, UnsignedByte.$00.plusSigned(1));
		assertEquals(UnsignedByte.$FF, UnsignedByte.$00.plusSigned(-1));
		assertEquals(UnsignedByte.$81, UnsignedByte.$7F.plusSigned(2));
		assertEquals(UnsignedByte.$02, UnsignedByte.$01.plusSigned(513));
	}

	@Test
	public void not() {
		assertEquals(UnsignedByte.$FF, UnsignedByte.$00.not());
		assertEquals(UnsignedByte.$FE, UnsignedByte.$01.not());
		assertEquals(UnsignedByte.$0C, UnsignedByte.$F3.not());
	}

	@Test
	public void isZero() {
		assertTrue(UnsignedByte.$00.isZero());
		assertFalse(UnsignedByte.$01.isZero());
	}

	@Test
	public void isNegative() {
		assertFalse(UnsignedByte.$00.isNegative());
		assertFalse(UnsignedByte.$01.isNegative());
		assertFalse(UnsignedByte.$7F.isNegative());
		assertTrue(UnsignedByte.$80.isNegative());
		assertTrue(UnsignedByte.$FF.isNegative());
	}

	/**
	 * Unignore to get an error message to copy new code from, if needed.
	 */
	@Test
	@Ignore
	public void constantDef() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < 256; i++) {
			String hex = ByteArrayPrettyPrinter.spaceSeparatedHex((byte) i);
			b.append("	public static final UnsignedByte $" + hex
					+ " = UnsignedByte.from(" + i + ");\n");
		}
		assertEquals("copy this to UnsignedByte.java", b.toString());
	}

}
