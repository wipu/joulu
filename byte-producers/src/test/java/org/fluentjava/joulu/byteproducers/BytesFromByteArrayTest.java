package org.fluentjava.joulu.byteproducers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.fluentjava.joulu.byteproducer.ByteProducer;
import org.junit.Test;

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
