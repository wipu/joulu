package org.fluentjava.joulu.midievents;

import java.util.Arrays;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;

import org.fluentjava.joulu.unsignedbyte.ByteArrayPrettyPrinter;

public class Midievents {

	private final SortedSet<Midievent> events;

	public Midievents(SortedSet<Midievent> events) {
		this.events = new TreeSet<>(events);
	}

	public static MidieventsPlease usingDefaults() {
		return new MidieventsPlease();
	}

	public SortedSet<Midievent> events() {
		return Collections.unmodifiableSortedSet(events);
	}

	@Override
	public String toString() {
		return events.toString();
	}

	public static class MidieventsPlease {

		private final SortedSet<Midievent> events = new TreeSet<>();
		private int channel;
		private int tick = 0;
		private int oct = 5;
		private int vel = 64;
		private Endable<?> unfinishedSubsentence;

		public Midievents end() {
			finishUnfinishedSubsentence();
			return new Midievents(events);
		}

		private void finishUnfinishedSubsentence() {
			if (unfinishedSubsentence != null) {
				unfinishedSubsentence.end();
				unfinishedSubsentence = null;
			}
		}

		public MidieventsPlease channel(int channel) {
			finishUnfinishedSubsentence();
			this.channel = channel;
			return this;
		}

		public MidieventsPlease after(int tickDelta) {
			return at(tick + tickDelta);
		}

		public MidieventsPlease at(int tick) {
			finishUnfinishedSubsentence();
			this.tick = tick;
			return this;
		}

		public MidieventsPlease oct(int oct) {
			finishUnfinishedSubsentence();
			this.oct = oct;
			return this;
		}

		public MidieventsPlease vel(int vel) {
			finishUnfinishedSubsentence();
			this.vel = vel;
			return this;
		}

		public MidieventsPlease event(Midievent e) {
			finishUnfinishedSubsentence();
			this.events.add(e);
			return this;
		}

		private MidieventsPlease eventFromSub(Midievent e) {
			unfinishedSubsentence = null;
			return event(e);
		}

		private <T extends Endable<?>> T sub(T sub) {
			unfinishedSubsentence = sub;
			return sub;
		}

		public OnPlease<MidieventsPlease> on_() {
			finishUnfinishedSubsentence();
			return sub(new OnPlease<>(e -> eventFromSub(e)).channel(channel)
					.at(tick).oct(oct).vel(vel));
		}

		public OffPlease<MidieventsPlease> off() {
			finishUnfinishedSubsentence();
			return sub(new OffPlease<>(e -> eventFromSub(e)).channel(channel)
					.at(tick).oct(oct));
		}

		public MidieventsPlease prg(int value) {
			return event(new Prg(channel, tick, value));
		}

		public MidieventsPlease channelPrefix(int value) {
			return event(new ChannelPrefix(value, tick));
		}

		public MidieventsPlease sysex(byte... value) {
			return event(new Sysex(tick, value));
		}

	}

	@FunctionalInterface
	public interface MidieventReceiver<T> {
		T event(Midievent e);
	}

	public interface Endable<T> {
		T end();
	}

	public static class OnPlease<AFTEREND> implements Endable<AFTEREND> {

		private final MidieventReceiver<AFTEREND> receiver;
		private int channel;
		private int tick;
		private int note;
		private int oct;
		private int vel;

		public OnPlease(MidieventReceiver<AFTEREND> receiver) {
			this.receiver = receiver;
		}

		@Override
		public AFTEREND end() {
			return receiver.event(new On(channel, tick,
					NoteValueCalculator.valueOfNoteAndOct(note, oct), vel));
		}

		public OnPlease<AFTEREND> channel(int channel) {
			this.channel = channel;
			return this;
		}

		public OnPlease<AFTEREND> at(int tick) {
			this.tick = tick;
			return this;
		}

		public OnPlease<AFTEREND> note(int note) {
			this.note = note;
			return this;
		}

		public OnPlease<AFTEREND> oct(int oct) {
			this.oct = oct;
			return this;
		}

		public OnPlease<AFTEREND> vel(int vel) {
			this.vel = vel;
			return this;
		}

		public OnPlease<AFTEREND> c__() {
			return note(0);
		}

		public OnPlease<AFTEREND> cis() {
			return note(1);
		}

		public OnPlease<AFTEREND> des() {
			return cis();
		}

	}

	public static class OffPlease<AFTEREND> implements Endable<AFTEREND> {

		private final MidieventReceiver<AFTEREND> receiver;
		private int channel;
		private int tick;
		private int note;
		private int oct;

		public OffPlease(MidieventReceiver<AFTEREND> receiver) {
			this.receiver = receiver;
		}

		@Override
		public AFTEREND end() {
			return receiver.event(new Off(channel, tick,
					NoteValueCalculator.valueOfNoteAndOct(note, oct)));
		}

		public OffPlease<AFTEREND> channel(int channel) {
			this.channel = channel;
			return this;
		}

		public OffPlease<AFTEREND> at(int tick) {
			this.tick = tick;
			return this;
		}

		public OffPlease<AFTEREND> note(int note) {
			this.note = note;
			return this;
		}

		public OffPlease<AFTEREND> oct(int oct) {
			this.oct = oct;
			return this;
		}

		public OffPlease<AFTEREND> c__() {
			return note(0);
		}

		public OffPlease<AFTEREND> cis() {
			return note(1);
		}

		public OffPlease<AFTEREND> des() {
			return cis();
		}

	}

	public static abstract class Midievent implements Comparable<Midievent> {

		public final int channel;
		public final int tick;

		public Midievent(int channel, int tick) {
			this.channel = channel;
			this.tick = tick;

		}

		public abstract EventType type();

		public abstract MidiEvent toJavaEvent();

		public abstract int noteOrMinus1();

		@Override
		public final int compareTo(Midievent o) {
			int diff = Integer.compare(tick, o.tick);
			if (diff != 0) {
				return diff;
			}
			diff = Integer.compare(channel, o.channel);
			if (diff != 0) {
				return diff;
			}
			diff = type().compareTo(o.type());
			if (diff != 0) {
				return diff;
			}
			diff = noteOrMinus1() - o.noteOrMinus1();
			if (diff != 0) {
				return diff;
			}
			MidiMessage jm = toJavaEvent().getMessage();
			MidiMessage ojm = o.toJavaEvent().getMessage();
			// TODO more type-specific comparison
			return Arrays.toString(jm.getMessage())
					.compareTo(Arrays.toString(ojm.getMessage()));
		}

		public abstract Midievent transposed(int semitones);

		public abstract Midievent delayed(int delay);

	}

	public enum EventType {
		OFF, ON, PRG, SYSEX,
	}

	public static class On extends Midievent {

		private final int note;
		private final int vel;

		public On(int channel, int tick, int note, int vel) {
			super(channel, tick);
			this.note = note;
			this.vel = vel;
		}

		@Override
		public EventType type() {
			return EventType.ON;
		}

		@Override
		public MidiEvent toJavaEvent() {
			try {
				return new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON,
						channel, note, vel), tick);
			} catch (InvalidMidiDataException e) {
				throw new IllegalStateException(e);
			}
		}

		@Override
		public String toString() {
			return "(@" + tick + " ON  ch:" + channel + " n:" + note + " v:"
					+ vel + ")";
		}

		@Override
		public Midievent transposed(int semitones) {
			return new On(channel, tick, note + semitones, vel);
		}

		@Override
		public int noteOrMinus1() {
			return note;
		}

		@Override
		public Midievent delayed(int delay) {
			return new On(channel, tick + delay, note, vel);
		}

	}

	public static class Off extends Midievent {

		private int note;

		public Off(int channel, int tick, int note) {
			super(channel, tick);
			this.note = note;
		}

		@Override
		public EventType type() {
			return EventType.OFF;
		}

		@Override
		public MidiEvent toJavaEvent() {
			try {
				return new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF,
						channel, note, 0), tick);
			} catch (InvalidMidiDataException e) {
				throw new IllegalStateException(e);
			}
		}

		@Override
		public String toString() {
			return "(@" + tick + " OFF ch:" + channel + " n:" + note + ")";
		}

		@Override
		public Midievent transposed(int semitones) {
			return new Off(channel, tick, note + semitones);
		}

		@Override
		public int noteOrMinus1() {
			return note;
		}

		@Override
		public Midievent delayed(int delay) {
			return new Off(channel, tick + delay, note);
		}

	}

	public static class Prg extends Midievent {

		private int value;

		public Prg(int channel, int tick, int value) {
			super(channel, tick);
			this.value = value;
		}

		@Override
		public EventType type() {
			return EventType.PRG;
		}

		@Override
		public MidiEvent toJavaEvent() {
			try {
				return new MidiEvent(new ShortMessage(
						ShortMessage.PROGRAM_CHANGE, channel, value, 0), tick);
			} catch (InvalidMidiDataException e) {
				throw new IllegalStateException(e);
			}
		}

		@Override
		public String toString() {
			return "(@" + tick + " PRG ch:" + channel + " n:" + value + ")";
		}

		@Override
		public Midievent transposed(int semitones) {
			return this;
		}

		@Override
		public int noteOrMinus1() {
			return -1;
		}

		@Override
		public Midievent delayed(int delay) {
			return new Prg(channel, tick + delay, value);
		}

	}

	public static class ChannelPrefix extends Midievent {

		public ChannelPrefix(int channel, int tick) {
			super(channel, tick);
		}

		@Override
		public EventType type() {
			return EventType.PRG;
		}

		@Override
		public MidiEvent toJavaEvent() {
			try {
				byte[] data = new byte[] { (byte) channel };
				MidiMessage msg = new MetaMessage(0x20, data, data.length);
				return new MidiEvent(msg, tick);
			} catch (InvalidMidiDataException e) {
				throw new IllegalStateException(e);
			}
		}

		@Override
		public String toString() {
			return "(@" + tick + " CHANNELPREFIX ch:" + channel + ")";
		}

		@Override
		public Midievent transposed(int semitones) {
			return this;
		}

		@Override
		public int noteOrMinus1() {
			return -1;
		}

		@Override
		public Midievent delayed(int delay) {
			return new ChannelPrefix(channel, tick + delay);
		}

	}

	public Midievents transposed(int semitones) {
		SortedSet<Midievent> tr = new TreeSet<>();
		for (Midievent e : events) {
			tr.add(e.transposed(semitones));
		}
		return new Midievents(tr);
	}

	public Midievents delayed(int delay) {
		SortedSet<Midievent> tr = new TreeSet<>();
		for (Midievent e : events) {
			tr.add(e.delayed(delay));
		}
		return new Midievents(tr);
	}

	public Midievents combinedWith(Midievents o) {
		SortedSet<Midievent> tr = new TreeSet<>();
		tr.addAll(events);
		tr.addAll(o.events);
		return new Midievents(tr);
	}

	public Midievents repeatedNTimesEveryDTicks(int n, int d) {
		Midievents tr = new Midievents(new TreeSet<>());
		for (int i = 0; i < n; i++) {
			int delay = i * d;
			Midievents delayed = delayed(delay);
			tr = tr.combinedWith(delayed);
		}
		return tr;
	}

	public static class Sysex extends Midievent {

		private final byte[] value;

		public Sysex(int tick, byte[] value) {
			super(-1, tick);
			this.value = Arrays.copyOf(value, value.length);
		}

		@Override
		public EventType type() {
			return EventType.SYSEX;
		}

		@Override
		public MidiEvent toJavaEvent() {
			try {
				return new MidiEvent(new SysexMessage(value, value.length),
						tick);
			} catch (InvalidMidiDataException e) {
				throw new IllegalStateException(e);
			}
		}

		@Override
		public String toString() {
			return "(@" + tick + " SYX "
					+ ByteArrayPrettyPrinter.spaceSeparatedHex(value) + ")";
		}

		@Override
		public Midievent transposed(int semitones) {
			return this;
		}

		@Override
		public int noteOrMinus1() {
			return -1;
		}

		@Override
		public Midievent delayed(int delay) {
			return new Sysex(tick + delay, value);
		}

	}

}
