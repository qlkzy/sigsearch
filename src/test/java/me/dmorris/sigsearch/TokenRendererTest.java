package me.dmorris.sigsearch;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static me.dmorris.sigsearch.Token.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

/**
 * Created by David on 07/02/2016.
 */
public class TokenRendererTest {

    private Queue<Token> input;
    private Queue<String> output;
    private TokenRenderer renderer;

    @Before
    public void setUp() {
        input = new LinkedList<Token>();
        output = new LinkedList<String>();
        renderer = new TokenRenderer(input, output);
    }

    @Test
    public void shouldEmptyInput() {
        addCharTokens("foo bar baz");
        renderer.renderAll();
        assertEquals(0, input.size());
    }

    @Test
    public void renderBasicSignatures() {
        renderer.addRule(new CharacterMatchRule("{};"));
        addCharTokens("foo(); bar() { baz; quux; }");
        renderer.renderAll();
        assertThat(output, contains(";", "{", ";", ";", "}"));
    }

    @Test
    public void renderFancySignatures() {
        renderer.addRule(new CharacterMatchRule("{};"));
        renderer.addRule(new SymbolEqualityRule("n", "new"));
        addCharTokens("foo(); bar() { ");
        addSymbolToken("new");
        addSymbolToken(" quux");
        addCharTokens("(); }");
        renderer.renderAll();
        assertThat(output, contains(";", "{", "n", ";", "}"));
    }

    private void addCharTokens(String s) {
        for (final char c : s.toCharArray()) {
            input.add(charToken(c));
        }
    }

    private void addSymbolToken(String s) {
        input.add(symbolToken(s));
    }

    private void addStringToken(String s) {
        input.add(stringToken(s));
    }
}