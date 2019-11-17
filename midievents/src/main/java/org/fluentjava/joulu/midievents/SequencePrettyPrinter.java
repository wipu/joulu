package org.fluentjava.joulu.midievents;

import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Track;

import org.apache.commons.codec.binary.StringUtils;
import org.fluentjava.joulu.unsignedbyte.ByteArrayPrettyPrinter;

public class SequencePrettyPrinter {

	private static final List<String> NOTE_NAMES = Arrays.asList("C-", "C#",
			"D-", "D#", "E-", "F-", "F#", "G-", "G#", "A-", "A#", "B-");
	private final StringBuilder b;

	private SequencePrettyPrinter() {
		b = new StringBuilder();
	}

	public static String multiLined(Sequence seq) {
		SequencePrettyPrinter p = new SequencePrettyPrinter();
		p.seq(seq);
		return p.b.toString();
	}

	private void line(Object line) {
		b.append(line).append("\n");
	}

	private void seq(Sequence seq) {
		line("(seq");
		line(" division:" + division(seq));
		line(" resolution:" + seq.getResolution());
		for (Track track : seq.getTracks()) {
			line("  (track");
			int size = track.size();
			for (int i = 0; i < size; i++) {
				MidiEvent ev = track.get(i);
				event(ev);
			}
			line("  ) # track");
		}
		line(") # seq");
	}

	private void event(MidiEvent ev) {
		b.append("    (ev @" + ev.getTick()).append(" ");
		MidiMessage msg = ev.getMessage();
		message(msg);
		line(")");
	}

	private void message(MidiMessage msg) {
		if (msg instanceof ShortMessage) {
			shortMessage((ShortMessage) msg);
		} else if (msg instanceof MetaMessage) {
			metaMessage((MetaMessage) msg);
		} else if (msg instanceof SysexMessage) {
			sysexMessage((SysexMessage) msg);
		} else {
			wtf(msg.getMessage());
		}
	}

	private void wtf(byte[] data) {
		raw("?", data);
	}

	private void raw(String typeToPrint, byte[] data) {
		b.append("(" + typeToPrint + " "
				+ ByteArrayPrettyPrinter.spaceSeparatedHex(data)).append(")");
	}

	private void metaMessage(MetaMessage msg) {
		int type = msg.getType();
		if (0x03 == type) {
			seqOrTrackName(msg);
		} else if (0x04 == type) {
			instrumentName(msg);
		} else if (0x20 == type) {
			midiChannelPrefix(msg);
		} else if (0x2F == type) {
			endOfTrack();
		} else if (0x51 == type) {
			tempo(msg);
		} else if (0x58 == type) {
			timeSignature(msg);
		} else if (0x59 == type) {
			keySignature(msg);
		} else {
			raw("meta?", msg.getMessage());
		}
	}

	private void sysexMessage(SysexMessage msg) {
		b.append("(sysex ");
		b.append(ByteArrayPrettyPrinter.spaceSeparatedHex(msg.getData()));
		b.append(")");
	}

	private void keySignature(MetaMessage msg) {
		b.append("(key-signature ");
		b.append(ByteArrayPrettyPrinter.spaceSeparatedHex(msg.getData()));
		b.append(")");
	}

	private void instrumentName(MetaMessage msg) {
		b.append("(instrument-name ");
		b.append(StringUtils.newStringUtf8(msg.getData()));
		b.append(")");
	}

	private void midiChannelPrefix(MetaMessage msg) {
		b.append("(midi-channel-prefix ");
		b.append(msg.getData()[0]);
		b.append(")");
	}

	private void endOfTrack() {
		b.append("(end-of-track)");
	}

	private void tempo(MetaMessage msg) {
		b.append("(tempo ");

		byte[] d = msg.getData();
		int tt = (0xFF & d[0]) << 16 | (0xFF & d[1]) << 8 | (0xFF & d[2]);

		b.append(tt);
		b.append(")");
	}

	private void seqOrTrackName(MetaMessage msg) {
		b.append("(seq/track-name ");
		b.append(StringUtils.newStringUtf8(msg.getData()));
		b.append(")");
	}

	private void timeSignature(MetaMessage msg) {
		b.append("(time-signature ");
		b.append(ByteArrayPrettyPrinter.spaceSeparatedHex(msg.getData()));
		b.append(")");
	}

	private void shortMessage(ShortMessage msg) {
		b.append("(ch:").append(msg.getChannel()).append(" ");
		int cmd = msg.getCommand();
		if (ShortMessage.NOTE_ON == cmd) {
			noteOn(msg);
		} else if (ShortMessage.NOTE_OFF == cmd) {
			noteOff(msg);
		} else if (ShortMessage.PROGRAM_CHANGE == cmd) {
			programChange(msg);
		} else if (ShortMessage.CONTROL_CHANGE == cmd) {
			controlChange(msg);
		} else if (ShortMessage.PITCH_BEND == cmd) {
			pitchBend(msg);
		} else if (ShortMessage.TIMING_CLOCK == cmd) {
			timingClock(msg);
		} else {
			wtf(msg.getMessage());
		}
		b.append(")");
	}

	private void timingClock(ShortMessage msg) {
		b.append("TIM ");
		b.append(msg.getData1());
		b.append(" ");
		b.append(msg.getData2());
	}

	private void pitchBend(ShortMessage msg) {
		b.append("PB  ");
		b.append(msg.getData1());
		b.append(" ");
		b.append(msg.getData2());
	}

	private void controlChange(ShortMessage msg) {
		b.append("CC  ");
		b.append(ControlChange.parameterNameOf(msg.getData1()));
		b.append(" ");
		b.append(msg.getData2());
	}

	private void programChange(ShortMessage msg) {
		b.append("PRG ");
		b.append(ProgramChange.gmInstrumentNameOf(msg.getData1()));
		b.append(" ");
		b.append(msg.getData2());
	}

	private void noteOn(ShortMessage msg) {
		b.append("ON  ");
		b.append(note(msg.getData1()));
		b.append(" ");
		b.append(msg.getData2());
	}

	private void noteOff(ShortMessage msg) {
		b.append("OFF ");
		b.append(note(msg.getData1()));
		b.append(" ");
		b.append(msg.getData2());
	}

	private static String note(int data) {
		int note = data % 12;
		int octave = data / 12;
		String noteName = NOTE_NAMES.get(note);
		return noteName + "-" + octave;
	}

	private static String division(Sequence seq) {
		float t = seq.getDivisionType();
		if (Sequence.PPQ == t) {
			return "PPQ";
		}
		return "?:" + t;
	}

}
