package org.fluentjava.joulu.midievents;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NoteValueCalculatorTest {

	@Test
	public void someValuesFromNoteAndOct() {
		assertEquals(60, NoteValueCalculator.valueOfNoteAndOct(0, 5));
		assertEquals(61, NoteValueCalculator.valueOfNoteAndOct(1, 5));
		assertEquals(48, NoteValueCalculator.valueOfNoteAndOct(0, 4));
	}

}
