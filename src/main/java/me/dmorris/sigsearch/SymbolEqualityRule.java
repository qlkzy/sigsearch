package me.dmorris.sigsearch;

/**
 * Created by David on 07/02/2016.
 */
public class SymbolEqualityRule implements Rule {

    private final String symbol;

    public SymbolEqualityRule(String symbol) {
        this.symbol = symbol;
    }

    public boolean matches(Token token) {
        return token.type == TokenType.SYMBOL &&
               token.value.equals(symbol);
    }
}
