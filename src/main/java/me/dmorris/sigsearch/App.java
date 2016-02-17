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

        try {

            for (final String arg : args) {
                if (arg.startsWith("-") && "cirs".contains(arg.substring(1, 2))) {
                    String ruleSpec = arg.substring(1);
                    tokenRenderer.addRule(RuleFactory.ruleFor(ruleSpec));
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
                    chars.add((char) c);
                }
                tokenizer.lexAll();
                tokenRenderer.renderAll();
                for (final String s : output) {
                    System.out.print(s);
                }
                output.clear();
                reader.close();
                System.out.println("");
            }

        } catch (InvalidRuleException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
