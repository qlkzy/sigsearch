package me.dmorris.sigsearch;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by David on 07/02/2016.
 */
public class SymbolRegexRuleTest {

    @Test
    public void shouldNotMatchString() {
        Rule rule = new SymbolRegexRule("foo");
        assertFalse(rule.matches(new Token(TokenType.STRING, "foo")));
    }

    @Test
    public void shouldMatchLiteral() {
        Rule rule = new SymbolRegexRule("foo");
        assertTrue(rule.matches(new Token(TokenType.SYMBOL, "foo")));
    }

    @Test
    public void shouldBeUnanchored() {
        Rule rule = new SymbolRegexRule("foo");
        assertTrue(rule.matches(new Token(TokenType.SYMBOL, "asfooasf")));
    }

    @Test
    public void shouldNotMatchWrongSymbol() {
        Rule rule = new SymbolRegexRule("bar");
        assertFalse(rule.matches(new Token(TokenType.SYMBOL, "foo")));
    }
}