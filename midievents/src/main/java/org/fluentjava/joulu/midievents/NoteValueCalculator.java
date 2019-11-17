package org.fluentjava.joulu.midievents;

public class NoteValueCalculator {

	public static int valueOfNoteAndOct(int note, int oct) {
		return oct * 12 + note;
	}

}
