package me.dmorris.sigsearch;

/**
 * Created by David on 07/02/2016.
 */
public class SymbolEqualityRule implements Rule {

    private final String rendering;
    private final String symbol;

    public SymbolEqualityRule(String rendering, String symbol) {
        this.rendering = rendering;
        this.symbol = symbol;
    }

    public String rendering(Token token) {
        return rendering;
    }

    public boolean matches(Token token) {
        return token.type == TokenType.SYMBOL &&
               token.value.equals(symbol);
    }
}
