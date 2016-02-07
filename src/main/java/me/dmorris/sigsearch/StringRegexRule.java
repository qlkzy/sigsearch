package me.dmorris.sigsearch;

import java.util.regex.Pattern;

/**
 * Created by David on 07/02/2016.
 */
public class StringRegexRule implements Rule {

    private final String rendering;
    private final Pattern pattern;

    public StringRegexRule(String rendering, String regex) {
        this.rendering = rendering;
        this.pattern = Pattern.compile(regex);
    }

    public String rendering(Token token) {
        return rendering;
    }

    public boolean matches(Token token) {
        return token.type == TokenType.STRING &&
               pattern.matcher(token.value).find();
    }
}
