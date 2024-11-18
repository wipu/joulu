package org.fluentjava.joulu.byteproducers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.fluentjava.joulu.byteproducer.ByteProducer;
import org.junit.jupiter.api.Test;

public class BytesFromByteArrayTest {

	@Test
	public void emptyArrayHasNothingImmediatelyAvailable() {
		byte[] src = new byte[0];
		ByteProducer producer = new BytesFromByteArray(src);

		assertFalse(producer.hasBytesImmediatelyAvailable());
	}

	@Test
	public void oneByteArrayHasBytesImmediatelyAvailable() {
		byte[] src = new byte[] { 0x01, };
		ByteProducer producer = new BytesFromByteArray(src);

		assertTrue(producer.hasBytesImmediatelyAvailable());
	}

}
