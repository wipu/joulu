package org.fluentjava.joulu.collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.fluentjava.joulu.equivalence.Equivalence;
import org.junit.jupiter.api.Test;

public class NaturalEquivalenceTest {
	@Test
	public void createAndTest() {
		Equivalence<String> e = new NaturalEquivalence<>();
		assertTrue(e.areEquivalent("foo", "foo"));
		assertFalse(e.areEquivalent("foo", "bar"));
	}
}
