package me.dmorris.sigsearch;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static java.util.Arrays.asList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws IOException {
        LinkedList<Character> chars = new LinkedList<>();
        LinkedList<Token> tokens = new LinkedList<>();
        LinkedList<String> output = new LinkedList<>();

        Tokenizer tokenizer = new Tokenizer(chars, tokens);
        TokenRenderer tokenRenderer = new TokenRenderer(tokens, output);

        ArrayList<String> filenames = new ArrayList<>();

        for (final String arg : args) {
            if (arg.startsWith("-c")) {
                tokenRenderer.addRule(new CharacterMatchRule(arg.substring(2)));
            } else if (arg.startsWith("-i")) {
                String pat = arg.substring(2);
                String[] parts = pat.split("=");
                tokenRenderer.addRule(new SymbolEqualityRule(parts[1], parts[0]));
            } else if (arg.startsWith("-r")) {
                String pat = arg.substring(2);
                String[] parts = pat.split("=");
                tokenRenderer.addRule(new SymbolRegexRule(parts[1], parts[0]));
            } else if (arg.startsWith("-s")) {
                String pat = arg.substring(2);
                String[] parts = pat.split("=");
                tokenRenderer.addRule(new StringRegexRule(parts[1], parts[0]));
            } else {
                filenames.add(arg);
            }
        }

        while (!filenames.isEmpty()) {
            String filename = filenames.remove(0);
            File file = new File(filename);
            if (file.isDirectory()) {
                Arrays.stream(file.listFiles((dir, name) -> name.endsWith(".java")))
                        .map(f -> f.getPath())
                        .forEach(f -> filenames.add(f));
                continue;
            }
            System.out.print(filename + ": ");
            FileReader reader = new FileReader(filename);
            int c;
            while ((c = reader.read()) != -1) {
                chars.add((char)c);
            }
            tokenizer.lexAll();
            tokenRenderer.renderAll();
            for (final String s : output) {
                System.out.print(s);
            }
            System.out.println("");
        }
    }
}
