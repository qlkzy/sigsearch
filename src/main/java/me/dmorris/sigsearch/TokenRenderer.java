package me.dmorris.sigsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by David on 07/02/2016.
 */
public class TokenRenderer {

    private final Queue<Token> input;
    private final Queue<String> output;

    private final List<Rule> rules;

    public TokenRenderer(Queue<Token> input, Queue<String> output) {
        this.input = input;
        this.output = output;
        this.rules = new ArrayList<Rule>();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void render() {
        if (input.isEmpty()) {
            return;
        }

        Token token = input.remove();

        for (final Rule rule : rules) {
            if (rule.matches(token)) {
                output.add(rule.rendering(token));
                break;
            }
        }
    }

    public void renderAll() {
        while (!input.isEmpty()) {
            render();
        }
    }
}
