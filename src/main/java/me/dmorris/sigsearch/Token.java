package me.dmorris.sigsearch;

import java.util.Objects;

/**
 * Represents a single parsed token
 *
 * Created by David on 07/02/2016.
 */
public class Token {
    public final TokenType type;
    public final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Token token = (Token) o;
        return type == token.type &&
               Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return "Token{" +
               "type=" + type +
               ", value='" + value + '\'' +
               '}';
    }

    public static Token charToken(String c) {
        return new Token(TokenType.CHARACTER, c);
    }

    public static Token charToken(char c) {
        return new Token(TokenType.CHARACTER, String.valueOf(c));
    }

    public static Token symbolToken(String s) {
        return new Token(TokenType.SYMBOL, s);
    }

    public static Token stringToken(String s) {
        return new Token(TokenType.STRING, s);
    }
}
