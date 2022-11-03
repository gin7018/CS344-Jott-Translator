import parser.JottParser;
import parser.nodes.JottTree;
import tokenizer.JottTokenizer;
import utils.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Jott {

    public static void main(String[] args) {

        if (args.length != 4) {
            System.out.println("usage: java Jott <input> <output> <language>");
        }
        else {
            String inputFilename = args[1];
            String outputFilename = args[2];
            String language = args[3];

            ArrayList<Token> tokens = JottTokenizer.tokenize(inputFilename);
            assert tokens != null;
            JottTree parseTree = JottParser.parse(tokens);
            assert parseTree != null;
            parseTree.validateTree(null);

            try {
                Path path = Path.of(outputFilename);
                switch (language) {
                    case "Jott" -> {
                        String result = parseTree.convertToJott();
                        Files.writeString(path, result);
                    }
                    case "Python" -> {
                        String result = parseTree.convertToPython();
                        Files.writeString(path, result);
                    }
                    case "Java" -> {
                        String result = parseTree.convertToJava();
                        Files.writeString(path, result);
                    }
                    case "C" -> {
                        String result = parseTree.convertToC();
                        Files.writeString(path, result);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
