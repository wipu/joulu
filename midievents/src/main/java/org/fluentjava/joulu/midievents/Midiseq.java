package org.fluentjava.joulu.midievents;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import org.fluentjava.joulu.midievents.Midievents.Midievent;

public class Midiseq {

	private int ppq = 96;
	private Midievents events;

	public Midiseq(Midievents events) {
		this.events = events;
	}

	public static MidiseqPlease usingDefaults() {
		return new MidiseqPlease();
	}

	public static class MidiseqPlease {

		private Midievents events;

		public Midiseq end() {
			return new Midiseq(events);
		}

		public MidiseqPlease events(Midievents e) {
			this.events = e;
			return this;
		}

	}

	public Sequence toJavaSequence() {
		try {
			Sequence seq = new Sequence(Sequence.PPQ, ppq);
			Track t1 = seq.createTrack();
			for (Midievent e : events.events()) {
				t1.add(e.toJavaEvent());
			}
			return seq;
		} catch (InvalidMidiDataException e) {
			throw new IllegalStateException(e);
		}

	}

	public byte[] asBytes() {
		Sequence seq = toJavaSequence();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			MidiSystem.write(seq, 0, out);
			return out.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return SequencePrettyPrinter.multiLined(toJavaSequence());
	}

}
