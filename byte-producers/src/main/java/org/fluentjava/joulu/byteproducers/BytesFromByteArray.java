package org.fluentjava.joulu.byteproducers;

import org.fluentjava.joulu.byteproducer.ByteProducer;

public class BytesFromByteArray implements ByteProducer {

	private final byte[] bytes;

	public BytesFromByteArray(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public boolean hasBytesImmediatelyAvailable() {
		return bytes.length > 0;
	}

}
