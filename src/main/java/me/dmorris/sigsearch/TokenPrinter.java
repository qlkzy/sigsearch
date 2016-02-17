package me.dmorris.sigsearch;

import java.io.PrintStream;
import java.util.Queue;

/**
 * Created by David on 17/02/2016.
 */
public class TokenPrinter {

    private final Queue<String> input;
    private final PrintStream out;

    public TokenPrinter(Queue<String> input, PrintStream out) {
        this.input = input;
        this.out = out;
    }

    public void print() {
        if (input.isEmpty()) return;

        out.print(input.remove());
    }

    public void printAll() {
        while (!input.isEmpty()) {
            print();
        }
    }
}
