package me.dmorris.sigsearch;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by David on 17/02/2016.
 */
public class PatternSetTest {

    PatternSet patternSet;

    @Before
    public void setUp() {
        patternSet = new PatternSet();
    }

    @Test
    public void shouldAcceptSingleLiteral() {
        patternSet.add("foo");
        assertTrue(patternSet.matchesAny("foo"));
    }

    @Test
    public void shouldAcceptUnanchoredMatch() {
        patternSet.add("ll");
        assertTrue(patternSet.matchesAny("hello"));
    }

    @Test
    public void shouldRequireAnchoredMatch() {
        patternSet.add("ll$");
        assertFalse(patternSet.matchesAny("hello"));
    }

    @Test
    public void shouldMatchOneOfSeveral() {
        patternSet.add("foo");
        patternSet.add("bar");
        assertTrue(patternSet.matchesAny("foo"));
    }

    @Test
    public void shouldRejectWithNone() {
        assertFalse(patternSet.matchesAny("quux"));
    }

    @Test
    public void shouldMatchNoneWithOnePattern() {
        patternSet.add("foo");
        assertTrue(patternSet.matchesNone("bar"));
    }
}