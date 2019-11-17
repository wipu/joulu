package org.fluentjava.joulu.midievents;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

public class SequencePrettyPrinterMain {

	public static void main(String[] args) throws Exception {
		File file = new File(args[0]);
		Sequence seq = MidiSystem.getSequence(file);
		System.out.println(SequencePrettyPrinter.multiLined(seq));
	}

}
