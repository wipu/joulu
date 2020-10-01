package org.fluentjava.joulu.unsignedbyte;

public class UnsignedByte {

	private static final UnsignedByte[] values = new UnsignedByte[256];
	private final int value;
	private final String toString;

	static {
		for (int i = 0; i < 256; i++) {
			values[i] = new UnsignedByte(i,
					"#$" + ByteArrayPrettyPrinter.spaceSeparatedHex((byte) i));
		}
	}

	public static final UnsignedByte x00 = UnsignedByte.from(0);
	public static final UnsignedByte x01 = UnsignedByte.from(1);
	public static final UnsignedByte x02 = UnsignedByte.from(2);
	public static final UnsignedByte x03 = UnsignedByte.from(3);
	public static final UnsignedByte x04 = UnsignedByte.from(4);
	public static final UnsignedByte x05 = UnsignedByte.from(5);
	public static final UnsignedByte x06 = UnsignedByte.from(6);
	public static final UnsignedByte x07 = UnsignedByte.from(7);
	public static final UnsignedByte x08 = UnsignedByte.from(8);
	public static final UnsignedByte x09 = UnsignedByte.from(9);
	public static final UnsignedByte x0A = UnsignedByte.from(10);
	public static final UnsignedByte x0B = UnsignedByte.from(11);
	public static final UnsignedByte x0C = UnsignedByte.from(12);
	public static final UnsignedByte x0D = UnsignedByte.from(13);
	public static final UnsignedByte x0E = UnsignedByte.from(14);
	public static final UnsignedByte x0F = UnsignedByte.from(15);
	public static final UnsignedByte x10 = UnsignedByte.from(16);
	public static final UnsignedByte x11 = UnsignedByte.from(17);
	public static final UnsignedByte x12 = UnsignedByte.from(18);
	public static final UnsignedByte x13 = UnsignedByte.from(19);
	public static final UnsignedByte x14 = UnsignedByte.from(20);
	public static final UnsignedByte x15 = UnsignedByte.from(21);
	public static final UnsignedByte x16 = UnsignedByte.from(22);
	public static final UnsignedByte x17 = UnsignedByte.from(23);
	public static final UnsignedByte x18 = UnsignedByte.from(24);
	public static final UnsignedByte x19 = UnsignedByte.from(25);
	public static final UnsignedByte x1A = UnsignedByte.from(26);
	public static final UnsignedByte x1B = UnsignedByte.from(27);
	public static final UnsignedByte x1C = UnsignedByte.from(28);
	public static final UnsignedByte x1D = UnsignedByte.from(29);
	public static final UnsignedByte x1E = UnsignedByte.from(30);
	public static final UnsignedByte x1F = UnsignedByte.from(31);
	public static final UnsignedByte x20 = UnsignedByte.from(32);
	public static final UnsignedByte x21 = UnsignedByte.from(33);
	public static final UnsignedByte x22 = UnsignedByte.from(34);
	public static final UnsignedByte x23 = UnsignedByte.from(35);
	public static final UnsignedByte x24 = UnsignedByte.from(36);
	public static final UnsignedByte x25 = UnsignedByte.from(37);
	public static final UnsignedByte x26 = UnsignedByte.from(38);
	public static final UnsignedByte x27 = UnsignedByte.from(39);
	public static final UnsignedByte x28 = UnsignedByte.from(40);
	public static final UnsignedByte x29 = UnsignedByte.from(41);
	public static final UnsignedByte x2A = UnsignedByte.from(42);
	public static final UnsignedByte x2B = UnsignedByte.from(43);
	public static final UnsignedByte x2C = UnsignedByte.from(44);
	public static final UnsignedByte x2D = UnsignedByte.from(45);
	public static final UnsignedByte x2E = UnsignedByte.from(46);
	public static final UnsignedByte x2F = UnsignedByte.from(47);
	public static final UnsignedByte x30 = UnsignedByte.from(48);
	public static final UnsignedByte x31 = UnsignedByte.from(49);
	public static final UnsignedByte x32 = UnsignedByte.from(50);
	public static final UnsignedByte x33 = UnsignedByte.from(51);
	public static final UnsignedByte x34 = UnsignedByte.from(52);
	public static final UnsignedByte x35 = UnsignedByte.from(53);
	public static final UnsignedByte x36 = UnsignedByte.from(54);
	public static final UnsignedByte x37 = UnsignedByte.from(55);
	public static final UnsignedByte x38 = UnsignedByte.from(56);
	public static final UnsignedByte x39 = UnsignedByte.from(57);
	public static final UnsignedByte x3A = UnsignedByte.from(58);
	public static final UnsignedByte x3B = UnsignedByte.from(59);
	public static final UnsignedByte x3C = UnsignedByte.from(60);
	public static final UnsignedByte x3D = UnsignedByte.from(61);
	public static final UnsignedByte x3E = UnsignedByte.from(62);
	public static final UnsignedByte x3F = UnsignedByte.from(63);
	public static final UnsignedByte x40 = UnsignedByte.from(64);
	public static final UnsignedByte x41 = UnsignedByte.from(65);
	public static final UnsignedByte x42 = UnsignedByte.from(66);
	public static final UnsignedByte x43 = UnsignedByte.from(67);
	public static final UnsignedByte x44 = UnsignedByte.from(68);
	public static final UnsignedByte x45 = UnsignedByte.from(69);
	public static final UnsignedByte x46 = UnsignedByte.from(70);
	public static final UnsignedByte x47 = UnsignedByte.from(71);
	public static final UnsignedByte x48 = UnsignedByte.from(72);
	public static final UnsignedByte x49 = UnsignedByte.from(73);
	public static final UnsignedByte x4A = UnsignedByte.from(74);
	public static final UnsignedByte x4B = UnsignedByte.from(75);
	public static final UnsignedByte x4C = UnsignedByte.from(76);
	public static final UnsignedByte x4D = UnsignedByte.from(77);
	public static final UnsignedByte x4E = UnsignedByte.from(78);
	public static final UnsignedByte x4F = UnsignedByte.from(79);
	public static final UnsignedByte x50 = UnsignedByte.from(80);
	public static final UnsignedByte x51 = UnsignedByte.from(81);
	public static final UnsignedByte x52 = UnsignedByte.from(82);
	public static final UnsignedByte x53 = UnsignedByte.from(83);
	public static final UnsignedByte x54 = UnsignedByte.from(84);
	public static final UnsignedByte x55 = UnsignedByte.from(85);
	public static final UnsignedByte x56 = UnsignedByte.from(86);
	public static final UnsignedByte x57 = UnsignedByte.from(87);
	public static final UnsignedByte x58 = UnsignedByte.from(88);
	public static final UnsignedByte x59 = UnsignedByte.from(89);
	public static final UnsignedByte x5A = UnsignedByte.from(90);
	public static final UnsignedByte x5B = UnsignedByte.from(91);
	public static final UnsignedByte x5C = UnsignedByte.from(92);
	public static final UnsignedByte x5D = UnsignedByte.from(93);
	public static final UnsignedByte x5E = UnsignedByte.from(94);
	public static final UnsignedByte x5F = UnsignedByte.from(95);
	public static final UnsignedByte x60 = UnsignedByte.from(96);
	public static final UnsignedByte x61 = UnsignedByte.from(97);
	public static final UnsignedByte x62 = UnsignedByte.from(98);
	public static final UnsignedByte x63 = UnsignedByte.from(99);
	public static final UnsignedByte x64 = UnsignedByte.from(100);
	public static final UnsignedByte x65 = UnsignedByte.from(101);
	public static final UnsignedByte x66 = UnsignedByte.from(102);
	public static final UnsignedByte x67 = UnsignedByte.from(103);
	public static final UnsignedByte x68 = UnsignedByte.from(104);
	public static final UnsignedByte x69 = UnsignedByte.from(105);
	public static final UnsignedByte x6A = UnsignedByte.from(106);
	public static final UnsignedByte x6B = UnsignedByte.from(107);
	public static final UnsignedByte x6C = UnsignedByte.from(108);
	public static final UnsignedByte x6D = UnsignedByte.from(109);
	public static final UnsignedByte x6E = UnsignedByte.from(110);
	public static final UnsignedByte x6F = UnsignedByte.from(111);
	public static final UnsignedByte x70 = UnsignedByte.from(112);
	public static final UnsignedByte x71 = UnsignedByte.from(113);
	public static final UnsignedByte x72 = UnsignedByte.from(114);
	public static final UnsignedByte x73 = UnsignedByte.from(115);
	public static final UnsignedByte x74 = UnsignedByte.from(116);
	public static final UnsignedByte x75 = UnsignedByte.from(117);
	public static final UnsignedByte x76 = UnsignedByte.from(118);
	public static final UnsignedByte x77 = UnsignedByte.from(119);
	public static final UnsignedByte x78 = UnsignedByte.from(120);
	public static final UnsignedByte x79 = UnsignedByte.from(121);
	public static final UnsignedByte x7A = UnsignedByte.from(122);
	public static final UnsignedByte x7B = UnsignedByte.from(123);
	public static final UnsignedByte x7C = UnsignedByte.from(124);
	public static final UnsignedByte x7D = UnsignedByte.from(125);
	public static final UnsignedByte x7E = UnsignedByte.from(126);
	public static final UnsignedByte x7F = UnsignedByte.from(127);
	public static final UnsignedByte x80 = UnsignedByte.from(128);
	public static final UnsignedByte x81 = UnsignedByte.from(129);
	public static final UnsignedByte x82 = UnsignedByte.from(130);
	public static final UnsignedByte x83 = UnsignedByte.from(131);
	public static final UnsignedByte x84 = UnsignedByte.from(132);
	public static final UnsignedByte x85 = UnsignedByte.from(133);
	public static final UnsignedByte x86 = UnsignedByte.from(134);
	public static final UnsignedByte x87 = UnsignedByte.from(135);
	public static final UnsignedByte x88 = UnsignedByte.from(136);
	public static final UnsignedByte x89 = UnsignedByte.from(137);
	public static final UnsignedByte x8A = UnsignedByte.from(138);
	public static final UnsignedByte x8B = UnsignedByte.from(139);
	public static final UnsignedByte x8C = UnsignedByte.from(140);
	public static final UnsignedByte x8D = UnsignedByte.from(141);
	public static final UnsignedByte x8E = UnsignedByte.from(142);
	public static final UnsignedByte x8F = UnsignedByte.from(143);
	public static final UnsignedByte x90 = UnsignedByte.from(144);
	public static final UnsignedByte x91 = UnsignedByte.from(145);
	public static final UnsignedByte x92 = UnsignedByte.from(146);
	public static final UnsignedByte x93 = UnsignedByte.from(147);
	public static final UnsignedByte x94 = UnsignedByte.from(148);
	public static final UnsignedByte x95 = UnsignedByte.from(149);
	public static final UnsignedByte x96 = UnsignedByte.from(150);
	public static final UnsignedByte x97 = UnsignedByte.from(151);
	public static final UnsignedByte x98 = UnsignedByte.from(152);
	public static final UnsignedByte x99 = UnsignedByte.from(153);
	public static final UnsignedByte x9A = UnsignedByte.from(154);
	public static final UnsignedByte x9B = UnsignedByte.from(155);
	public static final UnsignedByte x9C = UnsignedByte.from(156);
	public static final UnsignedByte x9D = UnsignedByte.from(157);
	public static final UnsignedByte x9E = UnsignedByte.from(158);
	public static final UnsignedByte x9F = UnsignedByte.from(159);
	public static final UnsignedByte xA0 = UnsignedByte.from(160);
	public static final UnsignedByte xA1 = UnsignedByte.from(161);
	public static final UnsignedByte xA2 = UnsignedByte.from(162);
	public static final UnsignedByte xA3 = UnsignedByte.from(163);
	public static final UnsignedByte xA4 = UnsignedByte.from(164);
	public static final UnsignedByte xA5 = UnsignedByte.from(165);
	public static final UnsignedByte xA6 = UnsignedByte.from(166);
	public static final UnsignedByte xA7 = UnsignedByte.from(167);
	public static final UnsignedByte xA8 = UnsignedByte.from(168);
	public static final UnsignedByte xA9 = UnsignedByte.from(169);
	public static final UnsignedByte xAA = UnsignedByte.from(170);
	public static final UnsignedByte xAB = UnsignedByte.from(171);
	public static final UnsignedByte xAC = UnsignedByte.from(172);
	public static final UnsignedByte xAD = UnsignedByte.from(173);
	public static final UnsignedByte xAE = UnsignedByte.from(174);
	public static final UnsignedByte xAF = UnsignedByte.from(175);
	public static final UnsignedByte xB0 = UnsignedByte.from(176);
	public static final UnsignedByte xB1 = UnsignedByte.from(177);
	public static final UnsignedByte xB2 = UnsignedByte.from(178);
	public static final UnsignedByte xB3 = UnsignedByte.from(179);
	public static final UnsignedByte xB4 = UnsignedByte.from(180);
	public static final UnsignedByte xB5 = UnsignedByte.from(181);
	public static final UnsignedByte xB6 = UnsignedByte.from(182);
	public static final UnsignedByte xB7 = UnsignedByte.from(183);
	public static final UnsignedByte xB8 = UnsignedByte.from(184);
	public static final UnsignedByte xB9 = UnsignedByte.from(185);
	public static final UnsignedByte xBA = UnsignedByte.from(186);
	public static final UnsignedByte xBB = UnsignedByte.from(187);
	public static final UnsignedByte xBC = UnsignedByte.from(188);
	public static final UnsignedByte xBD = UnsignedByte.from(189);
	public static final UnsignedByte xBE = UnsignedByte.from(190);
	public static final UnsignedByte xBF = UnsignedByte.from(191);
	public static final UnsignedByte xC0 = UnsignedByte.from(192);
	public static final UnsignedByte xC1 = UnsignedByte.from(193);
	public static final UnsignedByte xC2 = UnsignedByte.from(194);
	public static final UnsignedByte xC3 = UnsignedByte.from(195);
	public static final UnsignedByte xC4 = UnsignedByte.from(196);
	public static final UnsignedByte xC5 = UnsignedByte.from(197);
	public static final UnsignedByte xC6 = UnsignedByte.from(198);
	public static final UnsignedByte xC7 = UnsignedByte.from(199);
	public static final UnsignedByte xC8 = UnsignedByte.from(200);
	public static final UnsignedByte xC9 = UnsignedByte.from(201);
	public static final UnsignedByte xCA = UnsignedByte.from(202);
	public static final UnsignedByte xCB = UnsignedByte.from(203);
	public static final UnsignedByte xCC = UnsignedByte.from(204);
	public static final UnsignedByte xCD = UnsignedByte.from(205);
	public static final UnsignedByte xCE = UnsignedByte.from(206);
	public static final UnsignedByte xCF = UnsignedByte.from(207);
	public static final UnsignedByte xD0 = UnsignedByte.from(208);
	public static final UnsignedByte xD1 = UnsignedByte.from(209);
	public static final UnsignedByte xD2 = UnsignedByte.from(210);
	public static final UnsignedByte xD3 = UnsignedByte.from(211);
	public static final UnsignedByte xD4 = UnsignedByte.from(212);
	public static final UnsignedByte xD5 = UnsignedByte.from(213);
	public static final UnsignedByte xD6 = UnsignedByte.from(214);
	public static final UnsignedByte xD7 = UnsignedByte.from(215);
	public static final UnsignedByte xD8 = UnsignedByte.from(216);
	public static final UnsignedByte xD9 = UnsignedByte.from(217);
	public static final UnsignedByte xDA = UnsignedByte.from(218);
	public static final UnsignedByte xDB = UnsignedByte.from(219);
	public static final UnsignedByte xDC = UnsignedByte.from(220);
	public static final UnsignedByte xDD = UnsignedByte.from(221);
	public static final UnsignedByte xDE = UnsignedByte.from(222);
	public static final UnsignedByte xDF = UnsignedByte.from(223);
	public static final UnsignedByte xE0 = UnsignedByte.from(224);
	public static final UnsignedByte xE1 = UnsignedByte.from(225);
	public static final UnsignedByte xE2 = UnsignedByte.from(226);
	public static final UnsignedByte xE3 = UnsignedByte.from(227);
	public static final UnsignedByte xE4 = UnsignedByte.from(228);
	public static final UnsignedByte xE5 = UnsignedByte.from(229);
	public static final UnsignedByte xE6 = UnsignedByte.from(230);
	public static final UnsignedByte xE7 = UnsignedByte.from(231);
	public static final UnsignedByte xE8 = UnsignedByte.from(232);
	public static final UnsignedByte xE9 = UnsignedByte.from(233);
	public static final UnsignedByte xEA = UnsignedByte.from(234);
	public static final UnsignedByte xEB = UnsignedByte.from(235);
	public static final UnsignedByte xEC = UnsignedByte.from(236);
	public static final UnsignedByte xED = UnsignedByte.from(237);
	public static final UnsignedByte xEE = UnsignedByte.from(238);
	public static final UnsignedByte xEF = UnsignedByte.from(239);
	public static final UnsignedByte xF0 = UnsignedByte.from(240);
	public static final UnsignedByte xF1 = UnsignedByte.from(241);
	public static final UnsignedByte xF2 = UnsignedByte.from(242);
	public static final UnsignedByte xF3 = UnsignedByte.from(243);
	public static final UnsignedByte xF4 = UnsignedByte.from(244);
	public static final UnsignedByte xF5 = UnsignedByte.from(245);
	public static final UnsignedByte xF6 = UnsignedByte.from(246);
	public static final UnsignedByte xF7 = UnsignedByte.from(247);
	public static final UnsignedByte xF8 = UnsignedByte.from(248);
	public static final UnsignedByte xF9 = UnsignedByte.from(249);
	public static final UnsignedByte xFA = UnsignedByte.from(250);
	public static final UnsignedByte xFB = UnsignedByte.from(251);
	public static final UnsignedByte xFC = UnsignedByte.from(252);
	public static final UnsignedByte xFD = UnsignedByte.from(253);
	public static final UnsignedByte xFE = UnsignedByte.from(254);
	public static final UnsignedByte xFF = UnsignedByte.from(255);

	public UnsignedByte(int value, String toString) {
		this.value = value;
		this.toString = toString;
	}

	public static UnsignedByte from(int value) {
		return values[unsigned(value)];
	}

	public static UnsignedByte fromLsbOf(int value) {
		return values[unsigned(value)];
	}

	private static int unsigned(int value) {
		return 0xFF & value;
	}

	public int uInt() {
		return unsigned(value);
	}

	public byte signedByte() {
		return (byte) value;
	}

	public UnsignedByte withBit0(boolean value) {
		return withBit(value, x01);
	}

	public boolean isBit0() {
		return !and(x01).isZero();
	}

	public UnsignedByte withBit1(boolean value) {
		return withBit(value, x02);
	}

	public boolean isBit1() {
		return !and(x02).isZero();
	}

	public UnsignedByte withBit2(boolean value) {
		return withBit(value, x02);
	}

	public boolean isBit2() {
		return !and(x04).isZero();
	}

	public boolean isBit3() {
		return !and(x08).isZero();
	}

	public boolean isBit4() {
		return !and(x10).isZero();
	}

	public boolean isBit5() {
		return !and(x20).isZero();
	}

	public UnsignedByte withBit6(boolean value) {
		return withBit(value, x40);
	}

	public boolean isBit6() {
		return !and(x40).isZero();
	}

	public UnsignedByte withBit7(boolean value) {
		return withBit(value, x80);
	}

	public boolean isBit7() {
		return !and(x80).isZero();
	}

	private UnsignedByte withBit(boolean value, UnsignedByte orMask) {
		if (value) {
			return or(orMask);
		} else {
			return and(orMask.not());
		}
	}

	@Override
	public String toString() {
		return toString;
	}

	@Override
	public int hashCode() {
		return value;
	}

	public UnsignedByte and(UnsignedByte other) {
		return UnsignedByte.from(uInt() & other.uInt());
	}

	public UnsignedByte or(UnsignedByte other) {
		return UnsignedByte.from(uInt() | other.uInt());
	}

	public UnsignedByte eor(UnsignedByte other) {
		return UnsignedByte.from(uInt() ^ other.uInt());
	}

	public UnsignedByte plus(UnsignedByte other) {
		return from(signedByte() + other.signedByte());
	}

	public UnsignedByte plusSigned(int value) {
		return plus(UnsignedByte.from(value));
	}

	public UnsignedByte not() {
		return UnsignedByte.from(uInt() ^ 0xFF);
	}

	public boolean isZero() {
		return equals(x00);
	}

	public boolean isNegative() {
		return !(and(x80).isZero());
	}

}
