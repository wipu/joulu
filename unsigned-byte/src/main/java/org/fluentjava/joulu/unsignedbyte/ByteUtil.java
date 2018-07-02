package org.fluentjava.joulu.unsignedbyte;

public class ByteUtil {

	public static byte[] byteArray(int... bytes) {
		byte[] out = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			out[i] = (byte) bytes[i];
		}
		return out;
	}

}
