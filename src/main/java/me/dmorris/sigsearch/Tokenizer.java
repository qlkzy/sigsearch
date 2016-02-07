package me.dmorris.sigsearch;

import javax.xml.stream.events.Characters;
import java.util.Queue;

import static me.dmorris.sigsearch.Token.charToken;
import static me.dmorris.sigsearch.Token.stringToken;
import static me.dmorris.sigsearch.Token.symbolToken;

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
        COMMENT_START,
        LINE_COMMENT,
        COMMENT,
        COMMENT_END,
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

        boolean done;

        do {
            done = true;
            switch (state) {
                case START:
                    if (Character.isJavaIdentifierStart(c)) {
                        state = State.SYMBOL;
                        currentToken += c;
                    } else if (c == '"') {
                        state = State.STRING;
                    } else if (c == '/') {
                        state = State.COMMENT_START;
                    } else {
                        emit(charToken(c));
                    }
                    break;
                case SYMBOL:
                    if (Character.isJavaIdentifierPart(c)) {
                        currentToken += c;
                    } else {
                        emit(symbolToken(currentToken));
                        done = false;
                    }
                    break;
                case STRING:
                    if (c == '\\') {
                        state = State.STRING_ESCAPE;
                    } else if (c == '"') {
                        emit(stringToken(currentToken));
                    } else {
                        currentToken += c;
                    }
                    break;
                case STRING_ESCAPE:
                    currentToken += c;
                    state = State.STRING;
                    break;
                case COMMENT_START:
                    if (c == '/') {
                        state = State.LINE_COMMENT;
                    } else if (c == '*') {
                        state = State.COMMENT;
                    } else {
                        emit(charToken('/'));
                        done = false;
                    }
                    break;
                case LINE_COMMENT:
                    if (c == '\n') {
                        state = State.START;
                    }
                    break;
                case COMMENT:
                    if (c == '*') {
                        state = State.COMMENT_END;
                    }
                    break;
                case COMMENT_END:
                    if (c == '/') {
                        state = State.START;
                    } else {
                        state = State.COMMENT;
                    }
                    break;
            }
        } while (!done);
    }

    public void lexAll() {
        while (!input.isEmpty()) {
            lex();
        }
    }

    private void emit(Token token) {
        output.add(token);
        currentToken = "";
        state = State.START;
    }
}
