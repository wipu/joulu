package org.fluentjava.joulu.midievents;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fluentjava.joulu.midievents.Midievents.MidieventsPlease;
import org.junit.jupiter.api.Test;

public class MidieventsTest {

	@Test
	public void someEvents() {
		MidieventsPlease e = Midievents.usingDefaults();
		e.channelPrefix(2);
		e.on_().c__().oct(5).end();
		e.after(72);
		e.off().c__().oct(5);
		e.after(24).oct(4);
		e.on_().cis().vel(40);

		assertEquals(
				"[(@0 ON  ch:0 n:60 v:64), (@0 CHANNELPREFIX ch:2),"
						+ " (@72 OFF ch:0 n:60), (@96 ON  ch:0 n:49 v:40)]",
				e.end().toString());
	}

	@Test
	public void twoSimultaneousNotes() {
		MidieventsPlease e = Midievents.usingDefaults();
		e.on_().c__();
		e.on_().cis();
		e.after(10);
		e.off().c__();
		e.off().cis();

		assertEquals(
				"[(@0 ON  ch:0 n:60 v:64), (@0 ON  ch:0 n:61 v:64), (@10 OFF ch:0 n:60), (@10 OFF ch:0 n:61)]",
				e.end().toString());
	}

	@Test
	public void transposed() {
		MidieventsPlease e = Midievents.usingDefaults();
		e.prg(1);
		e.after(10);
		e.on_().c__();
		e.on_().cis();
		e.after(10);
		e.off().c__();
		e.off().cis();

		Midievents tr = e.end().transposed(7);

		assertEquals(
				"[(@0 PRG ch:0 n:1), (@10 ON  ch:0 n:67 v:64), (@10 ON  ch:0 n:68 v:64), (@20 OFF ch:0 n:67), (@20 OFF ch:0 n:68)]",
				tr.toString());
	}

	@Test
	public void delayed() {
		MidieventsPlease e = Midievents.usingDefaults();
		e.prg(1);
		e.after(10);
		e.on_().c__();
		e.on_().cis();
		e.after(10);
		e.off().c__();
		e.off().cis();

		Midievents tr = e.end().delayed(10);

		assertEquals(
				"[(@10 PRG ch:0 n:1), (@20 ON  ch:0 n:60 v:64), (@20 ON  ch:0 n:61 v:64), (@30 OFF ch:0 n:60), (@30 OFF ch:0 n:61)]",
				tr.toString());
	}

	@Test
	public void combinedWith() {
		MidieventsPlease e1 = Midievents.usingDefaults();
		e1.prg(1);
		e1.after(10);
		e1.on_().c__();
		e1.after(10);
		e1.off().c__();

		MidieventsPlease e2 = Midievents.usingDefaults();
		e2.prg(1);
		e2.after(10);
		e2.on_().cis();
		e2.after(10);
		e2.off().cis();

		Midievents tr = e1.end().combinedWith(e2.end());

		assertEquals(
				"[(@0 PRG ch:0 n:1), (@10 ON  ch:0 n:60 v:64), (@10 ON  ch:0 n:61 v:64), (@20 OFF ch:0 n:60), (@20 OFF ch:0 n:61)]",
				tr.toString());
	}

	@Test
	public void repeatedNTimesEveryDTicks() {
		MidieventsPlease e = Midievents.usingDefaults();
		e.prg(1);
		e.after(10);
		e.on_().c__();
		e.after(10);
		e.off().c__();

		Midievents tr = e.end().repeatedNTimesEveryDTicks(3, 100);

		assertEquals(
				"[(@0 PRG ch:0 n:1), (@10 ON  ch:0 n:60 v:64), (@20 OFF ch:0 n:60), "
						+ "(@100 PRG ch:0 n:1), (@110 ON  ch:0 n:60 v:64), (@120 OFF ch:0 n:60), "
						+ "(@200 PRG ch:0 n:1), (@210 ON  ch:0 n:60 v:64), (@220 OFF ch:0 n:60)]",
				tr.toString());
	}

	@Test
	public void atCanBeUsedToDefineEventsForSpecificTicks() {
		MidieventsPlease e = Midievents.usingDefaults();
		e.at(10);
		e.on_().c__();
		e.at(30);
		e.off().cis();
		e.at(15);
		e.off().c__();
		e.at(40);
		e.off().cis();

		assertEquals(
				"[(@10 ON  ch:0 n:60 v:64), (@15 OFF ch:0 n:60), (@30 OFF ch:0 n:61), (@40 OFF ch:0 n:61)]",
				e.end().toString());
	}

	@Test
	public void sysexEvents() {
		MidieventsPlease e = Midievents.usingDefaults();
		e.at(72);
		e.sysex((byte) 0xF0, (byte) 1, (byte) 2, (byte) 0xF7);
		e.after(72);
		e.sysex((byte) 0xF0, (byte) 3, (byte) 4, (byte) 0xF7);

		assertEquals("[(@72 SYX F0 01 02 F7), (@144 SYX F0 03 04 F7)]",
				e.end().toString());
	}

}
