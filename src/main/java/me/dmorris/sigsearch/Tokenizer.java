package me.dmorris.sigsearch;

import javax.xml.stream.events.Characters;
import java.util.Queue;

/**
 * Basic stupid tokeniser for Java
 *
 * Created by David on 07/02/2016.
 */
public class Tokenizer {

    private enum State {
        START,
        SYMBOL,
        STRING,
        STRING_ESCAPE,
    }

    private final Queue<Character> input;
    private final Queue<Token> output;

    private String currentToken;

    private State state;

    public Tokenizer(Queue<Character> input, Queue<Token> output) {
        this.input = input;
        this.output = output;
        this.currentToken = "";
        this.state = State.START;
    }

    public void lex() {
        if (input.isEmpty()) {
            return;
        }
        char c = input.remove();

        switch (state) {
            case START:
                if (Character.isJavaIdentifierStart(c)) {
                    state = State.SYMBOL;
                    currentToken += c;
                } else if (c == '"') {
                    state = State.STRING;
                } else {
                    output.add(new Token(TokenType.CHARACTER, String.valueOf(c)));
                }
                break;
            case SYMBOL:
                if (Character.isJavaIdentifierPart(c)) {
                    currentToken += c;
                } else {
                    output.add(new Token(TokenType.SYMBOL, currentToken));
                    currentToken = "";
                }
                break;
            case STRING:
                if (c == '\\') {
                    state = State.STRING_ESCAPE;
                } else if (c == '"') {
                    output.add(new Token(TokenType.STRING, currentToken));
                } else {
                    currentToken += c;
                }
                break;
            case STRING_ESCAPE:
                currentToken += c;
                state = State.STRING;
                break;
        }
    }

    public void lexAll() {
        while (!input.isEmpty()) {
            lex();
        }
    }
}
