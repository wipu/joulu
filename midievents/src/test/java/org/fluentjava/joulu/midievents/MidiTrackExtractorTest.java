package org.fluentjava.joulu.midievents;

import static org.junit.Assert.assertEquals;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

import org.fluentjava.joulu.midievents.Midievents.MidieventsPlease;
import org.junit.Test;

public class MidiTrackExtractorTest {

	@Test
	public void startOfPrettyPrintOfConquestTrack0() throws Exception {
		Sequence seq = MidiSystem.getSequence(getClass()
				.getResource("Movie_Themes_-_1492_Conquest_of_Paradise.mid"));

		Sequence extracted = MidiTrackExtractor.seqWithOnlyTrack(seq, 0, null);

		assertEquals(
				"(seq\n" + " division:PPQ\n" + " resolution:480\n"
						+ "  (track\n"
						+ "    (ev @0 (seq/track-name Conquest Of Paradise))\n"
						+ "    (ev @0 (time-signature 03 02 18 08))\n"
						+ "    (ev @0 (tempo 833333))\n"
						+ "    (ev @168480 (end-of-track))\n" + "  ) # track\n"
						+ ") # seq\n" + "",
				SequencePrettyPrinter.multiLined(extracted));
	}

	@Test
	public void startOfPrettyPrintOfConquestTrack1() throws Exception {
		Sequence seq = MidiSystem.getSequence(getClass()
				.getResource("Movie_Themes_-_1492_Conquest_of_Paradise.mid"));

		Sequence extracted = MidiTrackExtractor.seqWithOnlyTrack(seq, 1, null);

		assertEquals("(seq\n" + " division:PPQ\n" + " resolution:480\n"
				+ "  (track\n" + "    (ev @0 (seq/track-name Synth Br.1))\n"
				+ "    (ev @0 (midi-channel-prefix 0))\n"
				+ "    (ev @0 (instrument-name Synth Br.1))\n"
				+ "    (ev @0 (key-signature 00 00))\n" + "    (",
				SequencePrettyPrinter.multiLined(extracted).substring(0, 200));
	}

	@Test
	public void forcedChannelOfChannelContainingVariousEvents()
			throws InvalidMidiDataException {
		MidieventsPlease e = Midievents.usingDefaults();
		e.channel(2);
		e.channelPrefix(2);
		e.prg(9);
		e.after(10);
		e.on_().c__();
		e.after(96).off().c__();
		Midiseq seq = Midiseq.usingDefaults().events(e.end()).end();

		Sequence extracted = MidiTrackExtractor
				.seqWithOnlyTrack(seq.toJavaSequence(), 0, 3);

		assertEquals(
				"(seq\n" + " division:PPQ\n" + " resolution:96\n" + "  (track\n"
						+ "    (ev @0 (midi-channel-prefix 3))\n"
						+ "    (ev @0 (ch:3 PRG 9:Glockenspiel 0))\n"
						+ "    (ev @10 (ch:3 ON  C--5 64))\n"
						+ "    (ev @106 (ch:3 OFF C--5 0))\n"
						+ "    (ev @106 (end-of-track))\n" + "  ) # track\n"
						+ ") # seq\n" + "",
				SequencePrettyPrinter.multiLined(extracted));
	}

}
