package me.dmorris.sigsearch;

import java.io.*;
import java.nio.file.Files;
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

        PatternSet includeFilenames = new PatternSet();
        PatternSet excludeFilenames = new PatternSet();

        try {

            for (final String arg : args) {
                if (arg.startsWith("-") && "cirs".contains(arg.substring(1, 2))) {
                    String ruleSpec = arg.substring(1);
                    tokenRenderer.addRule(RuleFactory.ruleFor(ruleSpec));
                } else if (arg.startsWith("-") && "mx".contains(arg.substring(1, 2))) {
                    if (arg.substring(1, 2).equals("m")) {
                        includeFilenames.add(arg.substring(2));
                    } else if (arg.substring(1, 2).equals("x")) {
                        excludeFilenames.add(arg.substring(2));
                    }
                } else{
                    filenames.add(arg);
                }
            }

        } catch (InvalidRuleException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        // default patterns
        if (includeFilenames.isEmpty() && excludeFilenames.isEmpty()) {
            for (final String s : (new String[]{"java", "c", "cpp", "cc", "h", "hpp"})) {
                includeFilenames.add("\\." + s + "$");
            }
        }

        // current directory by default
        if (filenames.isEmpty()) {
            filenames.add(".");
        }

        while (!filenames.isEmpty()) {
            String filename = filenames.remove(0);
            File file = new File(filename);
            if (file.isDirectory()) {
                Files.walk(file.toPath())
                        .map(p -> p.toString())
                        .filter(n -> includeFilenames.matchesAny(n))
                        .filter(n -> excludeFilenames.matchesNone(n))
                        .forEach(n -> filenames.add(n));
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

    }
}
