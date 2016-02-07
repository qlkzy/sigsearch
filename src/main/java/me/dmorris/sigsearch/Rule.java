package me.dmorris.sigsearch;

/**
 * Created by David on 07/02/2016.
 */
public interface Rule {
    boolean matches(Token token);
}
