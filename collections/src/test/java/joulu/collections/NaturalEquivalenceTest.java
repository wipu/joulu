package joulu.collections;

import joulu.equivalence.Equivalence;

import static org.junit.Assert.*;
import org.junit.Test;

public class NaturalEquivalenceTest {
	@Test
	public void createAndTest() {
		Equivalence<String> e = new NaturalEquivalence<String>();
		assertTrue(e.areEquivalent("foo", "foo"));
		assertFalse(e.areEquivalent("foo", "bar"));
	}
}
