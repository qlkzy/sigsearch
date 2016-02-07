package me.dmorris.sigsearch;

import java.util.regex.Pattern;

/**
 * Created by David on 07/02/2016.
 */
public class StringRegexRule implements Rule {

    private final Pattern pattern;

    public StringRegexRule(String regex) {
        pattern = Pattern.compile(regex);
    }

    public boolean matches(Token token) {
        return token.type == TokenType.STRING &&
               pattern.matcher(token.value).find();
    }
}
