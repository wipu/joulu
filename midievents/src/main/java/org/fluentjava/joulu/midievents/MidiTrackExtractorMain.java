package org.fluentjava.joulu.midievents;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

public class MidiTrackExtractorMain {

	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			throw new IllegalArgumentException(
					"Usage: $0 MIDIFILE TRACK_NUMBER FORCED_CHANNEL"
							+ " (will print extracted seq to stdout; FORCED_CHANNEL='' means don't force)");
		}
		File from = new File(args[0]);
		int trackNr = Integer.parseInt(args[1]);
		String forcedChannelStr = args[2];
		Integer forcedChannel = forcedChannelStr.length() > 0
				? Integer.parseInt(forcedChannelStr)
				: null;

		Sequence seq = MidiSystem.getSequence(from);
		Sequence extracted = MidiTrackExtractor.seqWithOnlyTrack(seq, trackNr,
				forcedChannel);

		MidiSystem.write(extracted, 0, System.out);
	}

}
