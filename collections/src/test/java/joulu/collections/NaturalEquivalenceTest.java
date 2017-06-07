package joulu.collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import joulu.equivalence.Equivalence;

public class NaturalEquivalenceTest {
	@Test
	public void createAndTest() {
		Equivalence<String> e = new NaturalEquivalence<>();
		assertTrue(e.areEquivalent("foo", "foo"));
		assertFalse(e.areEquivalent("foo", "bar"));
	}
}
