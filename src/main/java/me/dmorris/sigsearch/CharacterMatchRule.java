package me.dmorris.sigsearch;

/**
 * Created by David on 07/02/2016.
 */
public class CharacterMatchRule implements Rule {

    private final char c;

    public CharacterMatchRule(char c) {
        this.c = c;
    }

    public boolean matches(Token token) {
        return token.type == TokenType.CHARACTER &&
               token.value.equals(String.valueOf(c));
    }
}
