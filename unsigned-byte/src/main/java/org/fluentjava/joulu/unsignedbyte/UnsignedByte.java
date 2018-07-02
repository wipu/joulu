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

	public static final UnsignedByte $00 = UnsignedByte.from(0);
	public static final UnsignedByte $01 = UnsignedByte.from(1);
	public static final UnsignedByte $02 = UnsignedByte.from(2);
	public static final UnsignedByte $03 = UnsignedByte.from(3);
	public static final UnsignedByte $04 = UnsignedByte.from(4);
	public static final UnsignedByte $05 = UnsignedByte.from(5);
	public static final UnsignedByte $06 = UnsignedByte.from(6);
	public static final UnsignedByte $07 = UnsignedByte.from(7);
	public static final UnsignedByte $08 = UnsignedByte.from(8);
	public static final UnsignedByte $09 = UnsignedByte.from(9);
	public static final UnsignedByte $0A = UnsignedByte.from(10);
	public static final UnsignedByte $0B = UnsignedByte.from(11);
	public static final UnsignedByte $0C = UnsignedByte.from(12);
	public static final UnsignedByte $0D = UnsignedByte.from(13);
	public static final UnsignedByte $0E = UnsignedByte.from(14);
	public static final UnsignedByte $0F = UnsignedByte.from(15);
	public static final UnsignedByte $10 = UnsignedByte.from(16);
	public static final UnsignedByte $11 = UnsignedByte.from(17);
	public static final UnsignedByte $12 = UnsignedByte.from(18);
	public static final UnsignedByte $13 = UnsignedByte.from(19);
	public static final UnsignedByte $14 = UnsignedByte.from(20);
	public static final UnsignedByte $15 = UnsignedByte.from(21);
	public static final UnsignedByte $16 = UnsignedByte.from(22);
	public static final UnsignedByte $17 = UnsignedByte.from(23);
	public static final UnsignedByte $18 = UnsignedByte.from(24);
	public static final UnsignedByte $19 = UnsignedByte.from(25);
	public static final UnsignedByte $1A = UnsignedByte.from(26);
	public static final UnsignedByte $1B = UnsignedByte.from(27);
	public static final UnsignedByte $1C = UnsignedByte.from(28);
	public static final UnsignedByte $1D = UnsignedByte.from(29);
	public static final UnsignedByte $1E = UnsignedByte.from(30);
	public static final UnsignedByte $1F = UnsignedByte.from(31);
	public static final UnsignedByte $20 = UnsignedByte.from(32);
	public static final UnsignedByte $21 = UnsignedByte.from(33);
	public static final UnsignedByte $22 = UnsignedByte.from(34);
	public static final UnsignedByte $23 = UnsignedByte.from(35);
	public static final UnsignedByte $24 = UnsignedByte.from(36);
	public static final UnsignedByte $25 = UnsignedByte.from(37);
	public static final UnsignedByte $26 = UnsignedByte.from(38);
	public static final UnsignedByte $27 = UnsignedByte.from(39);
	public static final UnsignedByte $28 = UnsignedByte.from(40);
	public static final UnsignedByte $29 = UnsignedByte.from(41);
	public static final UnsignedByte $2A = UnsignedByte.from(42);
	public static final UnsignedByte $2B = UnsignedByte.from(43);
	public static final UnsignedByte $2C = UnsignedByte.from(44);
	public static final UnsignedByte $2D = UnsignedByte.from(45);
	public static final UnsignedByte $2E = UnsignedByte.from(46);
	public static final UnsignedByte $2F = UnsignedByte.from(47);
	public static final UnsignedByte $30 = UnsignedByte.from(48);
	public static final UnsignedByte $31 = UnsignedByte.from(49);
	public static final UnsignedByte $32 = UnsignedByte.from(50);
	public static final UnsignedByte $33 = UnsignedByte.from(51);
	public static final UnsignedByte $34 = UnsignedByte.from(52);
	public static final UnsignedByte $35 = UnsignedByte.from(53);
	public static final UnsignedByte $36 = UnsignedByte.from(54);
	public static final UnsignedByte $37 = UnsignedByte.from(55);
	public static final UnsignedByte $38 = UnsignedByte.from(56);
	public static final UnsignedByte $39 = UnsignedByte.from(57);
	public static final UnsignedByte $3A = UnsignedByte.from(58);
	public static final UnsignedByte $3B = UnsignedByte.from(59);
	public static final UnsignedByte $3C = UnsignedByte.from(60);
	public static final UnsignedByte $3D = UnsignedByte.from(61);
	public static final UnsignedByte $3E = UnsignedByte.from(62);
	public static final UnsignedByte $3F = UnsignedByte.from(63);
	public static final UnsignedByte $40 = UnsignedByte.from(64);
	public static final UnsignedByte $41 = UnsignedByte.from(65);
	public static final UnsignedByte $42 = UnsignedByte.from(66);
	public static final UnsignedByte $43 = UnsignedByte.from(67);
	public static final UnsignedByte $44 = UnsignedByte.from(68);
	public static final UnsignedByte $45 = UnsignedByte.from(69);
	public static final UnsignedByte $46 = UnsignedByte.from(70);
	public static final UnsignedByte $47 = UnsignedByte.from(71);
	public static final UnsignedByte $48 = UnsignedByte.from(72);
	public static final UnsignedByte $49 = UnsignedByte.from(73);
	public static final UnsignedByte $4A = UnsignedByte.from(74);
	public static final UnsignedByte $4B = UnsignedByte.from(75);
	public static final UnsignedByte $4C = UnsignedByte.from(76);
	public static final UnsignedByte $4D = UnsignedByte.from(77);
	public static final UnsignedByte $4E = UnsignedByte.from(78);
	public static final UnsignedByte $4F = UnsignedByte.from(79);
	public static final UnsignedByte $50 = UnsignedByte.from(80);
	public static final UnsignedByte $51 = UnsignedByte.from(81);
	public static final UnsignedByte $52 = UnsignedByte.from(82);
	public static final UnsignedByte $53 = UnsignedByte.from(83);
	public static final UnsignedByte $54 = UnsignedByte.from(84);
	public static final UnsignedByte $55 = UnsignedByte.from(85);
	public static final UnsignedByte $56 = UnsignedByte.from(86);
	public static final UnsignedByte $57 = UnsignedByte.from(87);
	public static final UnsignedByte $58 = UnsignedByte.from(88);
	public static final UnsignedByte $59 = UnsignedByte.from(89);
	public static final UnsignedByte $5A = UnsignedByte.from(90);
	public static final UnsignedByte $5B = UnsignedByte.from(91);
	public static final UnsignedByte $5C = UnsignedByte.from(92);
	public static final UnsignedByte $5D = UnsignedByte.from(93);
	public static final UnsignedByte $5E = UnsignedByte.from(94);
	public static final UnsignedByte $5F = UnsignedByte.from(95);
	public static final UnsignedByte $60 = UnsignedByte.from(96);
	public static final UnsignedByte $61 = UnsignedByte.from(97);
	public static final UnsignedByte $62 = UnsignedByte.from(98);
	public static final UnsignedByte $63 = UnsignedByte.from(99);
	public static final UnsignedByte $64 = UnsignedByte.from(100);
	public static final UnsignedByte $65 = UnsignedByte.from(101);
	public static final UnsignedByte $66 = UnsignedByte.from(102);
	public static final UnsignedByte $67 = UnsignedByte.from(103);
	public static final UnsignedByte $68 = UnsignedByte.from(104);
	public static final UnsignedByte $69 = UnsignedByte.from(105);
	public static final UnsignedByte $6A = UnsignedByte.from(106);
	public static final UnsignedByte $6B = UnsignedByte.from(107);
	public static final UnsignedByte $6C = UnsignedByte.from(108);
	public static final UnsignedByte $6D = UnsignedByte.from(109);
	public static final UnsignedByte $6E = UnsignedByte.from(110);
	public static final UnsignedByte $6F = UnsignedByte.from(111);
	public static final UnsignedByte $70 = UnsignedByte.from(112);
	public static final UnsignedByte $71 = UnsignedByte.from(113);
	public static final UnsignedByte $72 = UnsignedByte.from(114);
	public static final UnsignedByte $73 = UnsignedByte.from(115);
	public static final UnsignedByte $74 = UnsignedByte.from(116);
	public static final UnsignedByte $75 = UnsignedByte.from(117);
	public static final UnsignedByte $76 = UnsignedByte.from(118);
	public static final UnsignedByte $77 = UnsignedByte.from(119);
	public static final UnsignedByte $78 = UnsignedByte.from(120);
	public static final UnsignedByte $79 = UnsignedByte.from(121);
	public static final UnsignedByte $7A = UnsignedByte.from(122);
	public static final UnsignedByte $7B = UnsignedByte.from(123);
	public static final UnsignedByte $7C = UnsignedByte.from(124);
	public static final UnsignedByte $7D = UnsignedByte.from(125);
	public static final UnsignedByte $7E = UnsignedByte.from(126);
	public static final UnsignedByte $7F = UnsignedByte.from(127);
	public static final UnsignedByte $80 = UnsignedByte.from(128);
	public static final UnsignedByte $81 = UnsignedByte.from(129);
	public static final UnsignedByte $82 = UnsignedByte.from(130);
	public static final UnsignedByte $83 = UnsignedByte.from(131);
	public static final UnsignedByte $84 = UnsignedByte.from(132);
	public static final UnsignedByte $85 = UnsignedByte.from(133);
	public static final UnsignedByte $86 = UnsignedByte.from(134);
	public static final UnsignedByte $87 = UnsignedByte.from(135);
	public static final UnsignedByte $88 = UnsignedByte.from(136);
	public static final UnsignedByte $89 = UnsignedByte.from(137);
	public static final UnsignedByte $8A = UnsignedByte.from(138);
	public static final UnsignedByte $8B = UnsignedByte.from(139);
	public static final UnsignedByte $8C = UnsignedByte.from(140);
	public static final UnsignedByte $8D = UnsignedByte.from(141);
	public static final UnsignedByte $8E = UnsignedByte.from(142);
	public static final UnsignedByte $8F = UnsignedByte.from(143);
	public static final UnsignedByte $90 = UnsignedByte.from(144);
	public static final UnsignedByte $91 = UnsignedByte.from(145);
	public static final UnsignedByte $92 = UnsignedByte.from(146);
	public static final UnsignedByte $93 = UnsignedByte.from(147);
	public static final UnsignedByte $94 = UnsignedByte.from(148);
	public static final UnsignedByte $95 = UnsignedByte.from(149);
	public static final UnsignedByte $96 = UnsignedByte.from(150);
	public static final UnsignedByte $97 = UnsignedByte.from(151);
	public static final UnsignedByte $98 = UnsignedByte.from(152);
	public static final UnsignedByte $99 = UnsignedByte.from(153);
	public static final UnsignedByte $9A = UnsignedByte.from(154);
	public static final UnsignedByte $9B = UnsignedByte.from(155);
	public static final UnsignedByte $9C = UnsignedByte.from(156);
	public static final UnsignedByte $9D = UnsignedByte.from(157);
	public static final UnsignedByte $9E = UnsignedByte.from(158);
	public static final UnsignedByte $9F = UnsignedByte.from(159);
	public static final UnsignedByte $A0 = UnsignedByte.from(160);
	public static final UnsignedByte $A1 = UnsignedByte.from(161);
	public static final UnsignedByte $A2 = UnsignedByte.from(162);
	public static final UnsignedByte $A3 = UnsignedByte.from(163);
	public static final UnsignedByte $A4 = UnsignedByte.from(164);
	public static final UnsignedByte $A5 = UnsignedByte.from(165);
	public static final UnsignedByte $A6 = UnsignedByte.from(166);
	public static final UnsignedByte $A7 = UnsignedByte.from(167);
	public static final UnsignedByte $A8 = UnsignedByte.from(168);
	public static final UnsignedByte $A9 = UnsignedByte.from(169);
	public static final UnsignedByte $AA = UnsignedByte.from(170);
	public static final UnsignedByte $AB = UnsignedByte.from(171);
	public static final UnsignedByte $AC = UnsignedByte.from(172);
	public static final UnsignedByte $AD = UnsignedByte.from(173);
	public static final UnsignedByte $AE = UnsignedByte.from(174);
	public static final UnsignedByte $AF = UnsignedByte.from(175);
	public static final UnsignedByte $B0 = UnsignedByte.from(176);
	public static final UnsignedByte $B1 = UnsignedByte.from(177);
	public static final UnsignedByte $B2 = UnsignedByte.from(178);
	public static final UnsignedByte $B3 = UnsignedByte.from(179);
	public static final UnsignedByte $B4 = UnsignedByte.from(180);
	public static final UnsignedByte $B5 = UnsignedByte.from(181);
	public static final UnsignedByte $B6 = UnsignedByte.from(182);
	public static final UnsignedByte $B7 = UnsignedByte.from(183);
	public static final UnsignedByte $B8 = UnsignedByte.from(184);
	public static final UnsignedByte $B9 = UnsignedByte.from(185);
	public static final UnsignedByte $BA = UnsignedByte.from(186);
	public static final UnsignedByte $BB = UnsignedByte.from(187);
	public static final UnsignedByte $BC = UnsignedByte.from(188);
	public static final UnsignedByte $BD = UnsignedByte.from(189);
	public static final UnsignedByte $BE = UnsignedByte.from(190);
	public static final UnsignedByte $BF = UnsignedByte.from(191);
	public static final UnsignedByte $C0 = UnsignedByte.from(192);
	public static final UnsignedByte $C1 = UnsignedByte.from(193);
	public static final UnsignedByte $C2 = UnsignedByte.from(194);
	public static final UnsignedByte $C3 = UnsignedByte.from(195);
	public static final UnsignedByte $C4 = UnsignedByte.from(196);
	public static final UnsignedByte $C5 = UnsignedByte.from(197);
	public static final UnsignedByte $C6 = UnsignedByte.from(198);
	public static final UnsignedByte $C7 = UnsignedByte.from(199);
	public static final UnsignedByte $C8 = UnsignedByte.from(200);
	public static final UnsignedByte $C9 = UnsignedByte.from(201);
	public static final UnsignedByte $CA = UnsignedByte.from(202);
	public static final UnsignedByte $CB = UnsignedByte.from(203);
	public static final UnsignedByte $CC = UnsignedByte.from(204);
	public static final UnsignedByte $CD = UnsignedByte.from(205);
	public static final UnsignedByte $CE = UnsignedByte.from(206);
	public static final UnsignedByte $CF = UnsignedByte.from(207);
	public static final UnsignedByte $D0 = UnsignedByte.from(208);
	public static final UnsignedByte $D1 = UnsignedByte.from(209);
	public static final UnsignedByte $D2 = UnsignedByte.from(210);
	public static final UnsignedByte $D3 = UnsignedByte.from(211);
	public static final UnsignedByte $D4 = UnsignedByte.from(212);
	public static final UnsignedByte $D5 = UnsignedByte.from(213);
	public static final UnsignedByte $D6 = UnsignedByte.from(214);
	public static final UnsignedByte $D7 = UnsignedByte.from(215);
	public static final UnsignedByte $D8 = UnsignedByte.from(216);
	public static final UnsignedByte $D9 = UnsignedByte.from(217);
	public static final UnsignedByte $DA = UnsignedByte.from(218);
	public static final UnsignedByte $DB = UnsignedByte.from(219);
	public static final UnsignedByte $DC = UnsignedByte.from(220);
	public static final UnsignedByte $DD = UnsignedByte.from(221);
	public static final UnsignedByte $DE = UnsignedByte.from(222);
	public static final UnsignedByte $DF = UnsignedByte.from(223);
	public static final UnsignedByte $E0 = UnsignedByte.from(224);
	public static final UnsignedByte $E1 = UnsignedByte.from(225);
	public static final UnsignedByte $E2 = UnsignedByte.from(226);
	public static final UnsignedByte $E3 = UnsignedByte.from(227);
	public static final UnsignedByte $E4 = UnsignedByte.from(228);
	public static final UnsignedByte $E5 = UnsignedByte.from(229);
	public static final UnsignedByte $E6 = UnsignedByte.from(230);
	public static final UnsignedByte $E7 = UnsignedByte.from(231);
	public static final UnsignedByte $E8 = UnsignedByte.from(232);
	public static final UnsignedByte $E9 = UnsignedByte.from(233);
	public static final UnsignedByte $EA = UnsignedByte.from(234);
	public static final UnsignedByte $EB = UnsignedByte.from(235);
	public static final UnsignedByte $EC = UnsignedByte.from(236);
	public static final UnsignedByte $ED = UnsignedByte.from(237);
	public static final UnsignedByte $EE = UnsignedByte.from(238);
	public static final UnsignedByte $EF = UnsignedByte.from(239);
	public static final UnsignedByte $F0 = UnsignedByte.from(240);
	public static final UnsignedByte $F1 = UnsignedByte.from(241);
	public static final UnsignedByte $F2 = UnsignedByte.from(242);
	public static final UnsignedByte $F3 = UnsignedByte.from(243);
	public static final UnsignedByte $F4 = UnsignedByte.from(244);
	public static final UnsignedByte $F5 = UnsignedByte.from(245);
	public static final UnsignedByte $F6 = UnsignedByte.from(246);
	public static final UnsignedByte $F7 = UnsignedByte.from(247);
	public static final UnsignedByte $F8 = UnsignedByte.from(248);
	public static final UnsignedByte $F9 = UnsignedByte.from(249);
	public static final UnsignedByte $FA = UnsignedByte.from(250);
	public static final UnsignedByte $FB = UnsignedByte.from(251);
	public static final UnsignedByte $FC = UnsignedByte.from(252);
	public static final UnsignedByte $FD = UnsignedByte.from(253);
	public static final UnsignedByte $FE = UnsignedByte.from(254);
	public static final UnsignedByte $FF = UnsignedByte.from(255);

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
		return withBit(value, $01);
	}

	public boolean isBit0() {
		return !and($01).isZero();
	}

	public UnsignedByte withBit1(boolean value) {
		return withBit(value, $02);
	}

	public boolean isBit1() {
		return !and($02).isZero();
	}

	public UnsignedByte withBit2(boolean value) {
		return withBit(value, $02);
	}

	public boolean isBit2() {
		return !and($04).isZero();
	}

	public boolean isBit3() {
		return !and($08).isZero();
	}

	public boolean isBit4() {
		return !and($10).isZero();
	}

	public boolean isBit5() {
		return !and($20).isZero();
	}

	public UnsignedByte withBit6(boolean value) {
		return withBit(value, $40);
	}

	public boolean isBit6() {
		return !and($40).isZero();
	}

	public UnsignedByte withBit7(boolean value) {
		return withBit(value, $80);
	}

	public boolean isBit7() {
		return !and($80).isZero();
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
		return equals($00);
	}

	public boolean isNegative() {
		return !(and($80).isZero());
	}

}
