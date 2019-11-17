package org.fluentjava.joulu.midievents;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MidiTrackExtractor {

	public static Sequence seqWithOnlyTrack(Sequence seq, int trackNumber,
			Integer forcedChannel) throws InvalidMidiDataException {
		Track track = seq.getTracks()[trackNumber];
		Sequence extracted = new Sequence(seq.getDivisionType(),
				seq.getResolution());
		Track newTrack = extracted.createTrack();
		for (int i = 0; i < track.size(); i++) {
			MidiEvent e = track.get(i);
			newTrack.add(withForcedChannel(e, forcedChannel));
		}
		return extracted;
	}

	private static MidiEvent withForcedChannel(MidiEvent e,
			Integer forcedChannel) throws InvalidMidiDataException {
		if (forcedChannel == null) {
			return e;
		}
		MidiMessage msg = e.getMessage();
		if (msg instanceof ShortMessage) {
			ShortMessage sm = (ShortMessage) msg;
			ShortMessage modSm = new ShortMessage(sm.getCommand(),
					forcedChannel, sm.getData1(), sm.getData2());
			return new MidiEvent(modSm, e.getTick());
		}
		if (msg instanceof MetaMessage) {
			MetaMessage mm = (MetaMessage) msg;
			if (mm.getType() == 0x20) {
				// channel prefix
				byte[] data = new byte[] { (byte) forcedChannel.intValue() };
				MetaMessage modMm = new MetaMessage(mm.getType(), data,
						data.length);
				return new MidiEvent(modMm, e.getTick());
			}
		}
		// not to be forced
		return e;
	}

}
