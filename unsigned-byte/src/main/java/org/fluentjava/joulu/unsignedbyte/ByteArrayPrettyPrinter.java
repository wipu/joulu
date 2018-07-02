package org.fluentjava.joulu.unsignedbyte;

public class ByteArrayPrettyPrinter {

	public static String spaceSeparatedHex(byte... bytes) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			append(out, b);
			if (i < bytes.length - 1) {
				out.append(' ');
			}
		}
		return out.toString();
	}

	public static void append(StringBuilder out, byte b) {
		int u = b & 0xFF;
		if (u < 0x10) {
			out.append('0');
		}
		out.append(Integer.toHexString(u).toUpperCase());
	}

}
