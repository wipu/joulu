package org.fluentjava.joulu.midievents;

import java.util.HashMap;
import java.util.Map;

public class ProgramChange {

	private static final Map<Integer, String> instrNames = new HashMap<>();

	static {
		instr(10, "Glockenspiel");
	}

	private static void instr(int value, String name) {
		instrNames.put(value, name);
	}

	public static String gmInstrumentNameOf(int value) {
		String name = instrNames.get(value + 1);
		if (name != null) {
			return value + ":" + name;
		}
		return value + ":?";
	}

}
