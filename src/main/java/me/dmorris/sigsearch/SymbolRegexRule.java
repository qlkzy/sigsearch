package me.dmorris.sigsearch;

import java.util.regex.Pattern;

/**
 * Created by David on 07/02/2016.
 */
public class SymbolRegexRule implements Rule {

    private final Pattern pattern;

    public SymbolRegexRule(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public boolean matches(Token token) {
        return token.type == TokenType.SYMBOL &&
               pattern.matcher(token.value).find();
    }
}
