package me.dmorris.sigsearch;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by David on 07/02/2016.
 */
public class SymbolEqualityRuleTest {

    @Test
    public void shouldNotMatchString() {
        Rule rule = new SymbolEqualityRule("foo");
        assertFalse(rule.matches(new Token(TokenType.STRING, "foo")));
    }

    @Test
    public void shouldMatchCorrectSymbol() {
        Rule rule = new SymbolEqualityRule("foo");
        assertTrue(rule.matches(new Token(TokenType.SYMBOL, "foo")));
    }

    @Test
    public void shouldNotMatchWrongSymbol() {
        Rule rule = new SymbolEqualityRule("bar");
        assertFalse(rule.matches(new Token(TokenType.SYMBOL, "foo")));
    }

}