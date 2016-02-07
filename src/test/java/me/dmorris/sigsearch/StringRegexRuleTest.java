package me.dmorris.sigsearch;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by David on 07/02/2016.
 */
public class StringRegexRuleTest {

    @Test
    public void shouldNotMatchCharacter() {
        Rule rule = new StringRegexRule("", ";");
        assertFalse(rule.matches(new Token(TokenType.CHARACTER, ";")));
    }

    @Test
    public void shouldMatchEquivalentString() {
        Rule rule = new StringRegexRule("", "foo");
        assertTrue(rule.matches(new Token(TokenType.STRING, "foo")));
    }

    @Test
    public void shouldBeUnanchored() {
        Rule rule = new StringRegexRule("", "foo");
        assertTrue(rule.matches(new Token(TokenType.STRING, "asdfafoooasdfasdf")));
    }

    @Test
    public void shouldNotMatchWrongString() {
        Rule rule = new StringRegexRule("", "bar");
        assertFalse(rule.matches(new Token(TokenType.STRING, "foo")));
    }
}