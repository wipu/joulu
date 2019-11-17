package org.fluentjava.joulu.midievents;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import org.junit.Test;

public class SequencePrettyPrinterTest {

	@Test
	public void empty() throws InvalidMidiDataException {
		Sequence seq = new Sequence(Sequence.PPQ, 2);
		seq.createTrack();

		assertEquals(
				"(seq\n" + " division:PPQ\n" + " resolution:2\n" + "  (track\n"
						+ "    (ev @0 (end-of-track))\n" + "  ) # track\n"
						+ ") # seq\n" + "",
				SequencePrettyPrinter.multiLined(seq));
	}

	@Test
	public void noteOnAndOff() throws InvalidMidiDataException {
		Sequence seq = new Sequence(Sequence.PPQ, 2);
		Track t1 = seq.createTrack();
		t1.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 1, 61, 65),
				10));
		t1.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 2, 62, 0),
				21));

		assertEquals(
				"(seq\n" + " division:PPQ\n" + " resolution:2\n" + "  (track\n"
						+ "    (ev @10 (ch:1 ON  C#-5 65))\n"
						+ "    (ev @21 (ch:2 OFF D--5 0))\n"
						+ "    (ev @21 (end-of-track))\n" + "  ) # track\n"
						+ ") # seq\n" + "",
				SequencePrettyPrinter.multiLined(seq));
	}

	@Test
	public void printBiggerMidifile()
			throws InvalidMidiDataException, IOException {
		Sequence seq = MidiSystem.getSequence(getClass()
				.getResource("Movie_Themes_-_1492_Conquest_of_Paradise.mid"));

		System.out.println(SequencePrettyPrinter.multiLined(seq));
	}

}
