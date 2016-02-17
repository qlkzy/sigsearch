package me.dmorris.sigsearch;

/**
 * Created by David on 17/02/2016.
 */
public class RuleFactory {

    public static Rule ruleFor(String text) {
        String[] parts = text.split("/");
        String cmd = parts[0];
        switch (cmd) {
            case "c":
                requireMatchPart(text, parts);
                return new CharacterMatchRule(parts[1]);
            case "i":
                requireMatchPart(text, parts);
                requireReplacementPart(text, parts);
                return new SymbolEqualityRule(parts[2], parts[1]);
            case "r":
                requireMatchPart(text, parts);
                requireReplacementPart(text, parts);
                return new SymbolRegexRule(parts[2], parts[1]);
            case "s":
                requireMatchPart(text, parts);
                requireReplacementPart(text, parts);
                return new StringRegexRule(parts[2], parts[1]);
        }
        throw new InvalidRuleException("Unable to parse rule " + text);
    }

    public static void requireMatchPart(String text, String parts[]) {
        if (parts.length < 2) {
            throw new InvalidRuleException("rule '" + text + "' invalid: no match part");
        }
    }

    public static void requireReplacementPart(String text, String parts[]) {
        if (parts.length < 3) {
            throw new InvalidRuleException("rule '" + text + "' invalid: no replacement part");
        }
    }
}
