package me.dmorris.sigsearch;

/**
 * Created by David on 07/02/2016.
 */
public class CharacterMatchRule implements Rule {

    private final String chars;

    public CharacterMatchRule(String chars) {
        this.chars = chars;
    }

    public String rendering(Token token) {
        return token.value;
    }

    public boolean matches(Token token) {
        return token.type == TokenType.CHARACTER &&
               chars.contains(token.value);
    }
}
