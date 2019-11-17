package org.fluentjava.joulu.midievents;

import java.util.HashMap;
import java.util.Map;

public class ControlChange {

	private static final Map<Integer, String> parameterNames = new HashMap<>();

	static {
		param(0, "Bank Select");
		param(6, "Data Entry MSB");
		param(7, "Channel Volume (formerly Main Volume)");
		param(10, "Pan");
		param(32, "LSB for Control 0 (Bank Select)");
		param(91, "Effects 1 Depth (default: Reverb Send Level)");
		param(93, "Effects 3 Depth (default: Chorus Send Level)");
		param(100, "RPN LSB");
		param(101, "RPN MSB");
	}

	private static void param(int value, String name) {
		parameterNames.put(value, name);
	}

	public static String parameterNameOf(int value) {
		String name = parameterNames.get(value);
		if (name != null) {
			return value + ":" + name;
		}
		return value + ":?";
	}

}
