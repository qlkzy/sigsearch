package me.dmorris.sigsearch;

import java.util.regex.Pattern;

/**
 * Created by David on 07/02/2016.
 */
public class SymbolRegexRule implements Rule {

    private final Pattern pattern;
    private final String rendering;

    public SymbolRegexRule(String rendering, String regex) {
        this.rendering = rendering;
        this.pattern = Pattern.compile(regex);
    }

    public String rendering(Token token) {
        return rendering;
    }

    public boolean matches(Token token) {
        return token.type == TokenType.SYMBOL &&
               pattern.matcher(token.value).find();
    }
}
