package me.dmorris.sigsearch;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static me.dmorris.sigsearch.Token.charToken;
import static me.dmorris.sigsearch.Token.symbolToken;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by David on 07/02/2016.
 */
public class TokenizerTest {

    private Queue<Character> characters;
    private Queue<Token> tokens;
    private Tokenizer tokenizer;

    @Before
    public void setUp() {
        characters = new LinkedList<Character>();
        tokens = new LinkedList<Token>();
        tokenizer = new Tokenizer(characters, tokens);
    }

    @Test
    public void shouldPassThroughSemicolons() {
        characters.add(';');
        tokenizer.lex();
        assertThat(tokens, contains(new Token(TokenType.CHARACTER, ";")));
    }

    @Test
    public void shouldExtractSymbol() {
        addStringChars("if ");
        tokenizer.lexAll();
        assertThat(tokens, contains(symbolToken("if"), charToken(" ")));
    }

    @Test
    public void shouldExtractString() {
        addStringChars("\"foo\"");
        tokenizer.lexAll();
        assertThat(tokens, contains(new Token(TokenType.STRING, "foo")));
    }

    @Test
    public void shouldHandleStringWithEmbeddedQuote() {
        addStringChars("\"foo \\\"bar\\\"\"");
        tokenizer.lexAll();
        assertThat(tokens, contains(new Token(TokenType.STRING, "foo \"bar\"")));
    }

    @Test
    public void shouldDropSingleLineComments() {
        addStringChars("foo // asdfasdf asf\n bar ");
        tokenizer.lexAll();
        assertThat(tokens, contains(symbolToken("foo"),
                                    charToken(" "),
                                    charToken(" "),
                                    symbolToken("bar"),
                                    charToken(" ")));
    }

    @Test
    public void shouldDropMultilineComments() {
        addStringChars("foo /* bar \n baz */ quux ");
        tokenizer.lexAll();
        assertThat(tokens, contains(symbolToken("foo"),
                                    charToken(" "),
                                    charToken(" "),
                                    symbolToken("quux"),
                                    charToken(" ")));
    }

    private void addStringChars(String chars) {
        for (final char c : chars.toCharArray()) {
            characters.add(c);
        }
    }

}