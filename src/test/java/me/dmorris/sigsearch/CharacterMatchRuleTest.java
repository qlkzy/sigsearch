package me.dmorris.sigsearch;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by David on 07/02/2016.
 */
public class CharacterMatchRuleTest {
    @Test
    public void shouldNotMatchSymbol() {
        Rule rule = new CharacterMatchRule(';');
        assertFalse(rule.matches(new Token(TokenType.SYMBOL, ";")));
    }

    @Test
    public void shouldMatchSameChar() {
        Rule rule = new CharacterMatchRule(';');
        assertTrue(rule.matches(new Token(TokenType.CHARACTER, ";")));
    }

    @Test
    public void shouldNotMatchDifferentChar() {
        Rule rule = new CharacterMatchRule('{');
        assertFalse(rule.matches(new Token(TokenType.CHARACTER, ";")));
    }
}