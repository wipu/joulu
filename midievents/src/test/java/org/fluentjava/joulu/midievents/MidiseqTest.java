package org.fluentjava.joulu.midievents;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

import org.fluentjava.joulu.midievents.Midievents.MidieventsPlease;
import org.junit.Test;

public class MidiseqTest {

	private void assertSeq(String expected, Midiseq s) {
		Sequence js = s.toJavaSequence();
		String caller = new Exception().getStackTrace()[1].getMethodName();
		File file = new File("/tmp",
				getClass().getCanonicalName() + "." + caller + ".mid");
		System.err.println("Writing " + file);
		try (OutputStream out = new FileOutputStream(file)) {
			MidiSystem.write(js, 0, out);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		assertEquals(expected, SequencePrettyPrinter.multiLined(js));
	}

	@Test
	public void twoNotes() {
		MidieventsPlease e = Midievents.usingDefaults();
		e.on_().c__().oct(5).end();
		e.after(72);
		e.off().c__().oct(5);
		e.after(24).oct(4);
		e.on_().cis().vel(40);
		e.after(24).off().cis();
		Midiseq s = Midiseq.usingDefaults().events(e.end()).end();

		assertSeq("(seq\n" + " division:PPQ\n" + " resolution:96\n"
				+ "  (track\n" + "    (ev @0 (ch:0 ON  C--5 64))\n"
				+ "    (ev @72 (ch:0 OFF C--5 0))\n"
				+ "    (ev @96 (ch:0 ON  C#-4 40))\n"
				+ "    (ev @120 (ch:0 OFF C#-4 0))\n"
				+ "    (ev @120 (end-of-track))\n" + "  ) # track\n"
				+ ") # seq\n" + "", s);
	}

	@Test
	public void programChange() {
		MidieventsPlease e = Midievents.usingDefaults();
		e.prg(9);
		e.after(10);
		e.on_().c__();
		e.after(96).off().c__();
		Midiseq s = Midiseq.usingDefaults().events(e.end()).end();

		assertSeq("(seq\n" + " division:PPQ\n" + " resolution:96\n"
				+ "  (track\n" + "    (ev @0 (ch:0 PRG 9:Glockenspiel 0))\n"
				+ "    (ev @10 (ch:0 ON  C--5 64))\n"
				+ "    (ev @106 (ch:0 OFF C--5 0))\n"
				+ "    (ev @106 (end-of-track))\n" + "  ) # track\n"
				+ ") # seq\n" + "", s);
	}

	@Test
	public void variousMetaMessages() {
		MidieventsPlease e = Midievents.usingDefaults();
		e.after(10);
		e.channelPrefix(2);
		e.prg(10);
		Midiseq s = Midiseq.usingDefaults().events(e.end()).end();

		assertSeq("(seq\n" + " division:PPQ\n" + " resolution:96\n"
				+ "  (track\n" + "    (ev @10 (ch:0 PRG 10:? 0))\n"
				+ "    (ev @10 (midi-channel-prefix 2))\n"
				+ "    (ev @10 (end-of-track))\n" + "  ) # track\n"
				+ ") # seq\n" + "", s);
	}

	@Test
	public void sysex() {
		MidieventsPlease e = Midievents.usingDefaults();
		e.at(72);
		// manufacturer 0x44 = Casio
		e.sysex((byte) 0xF0, (byte) 0x44, (byte) 2, (byte) 0xF7);
		Midiseq s = Midiseq.usingDefaults().events(e.end()).end();

		assertSeq("(seq\n" + " division:PPQ\n" + " resolution:96\n"
				+ "  (track\n" + "    (ev @72 (sysex 44 02 F7))\n"
				+ "    (ev @72 (end-of-track))\n" + "  ) # track\n"
				+ ") # seq\n" + "", s);
	}

}
