package me.dmorris.sigsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by David on 17/02/2016.
 */
public class PatternSet {

    private final List<Pattern> patterns;


    public PatternSet() {
        patterns = new ArrayList<>();
    }

    public void add(String regex) {
        patterns.add(Pattern.compile(regex));
    }

    public boolean isEmpty() {
        return patterns.isEmpty();
    }

    public boolean matchesAny(String s) {
        for (final Pattern pattern : patterns) {
            if (pattern.matcher(s).find()) {
                return true;
            }
        }
        return false;
    }

    public boolean matchesNone(String s) {
        return !matchesAny(s);
    }
}
