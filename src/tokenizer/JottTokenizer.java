package tokenizer;

import parser.ContextUnawareSyntaxException;
import utils.Token;
import utils.TokenType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for tokenizing Jott code.
 *
 * @author Ghislaine Nyagatare, Adam Yarris
 **/

public class JottTokenizer {

  private static final String ROOT_PATH = "src/";
  private static final List<String> MATH_OPS = List.of("/", "+", "*", "-");
  private static final List<String> REL_OPS = List.of("<", ">");

  private static boolean belongsInQuotes(List<Character> jottChars, int index) {
    return Character.isAlphabetic(jottChars.get(index)) ||
            Character.isDigit(jottChars.get(index)) ||
            jottChars.get(index) == ' ';
  }

  private static boolean belongsInString(List<Character> jottChars, int index) {
    return Character.isAlphabetic(jottChars.get(index)) ||
            Character.isDigit(jottChars.get(index));
  }

  /**
   * Takes in a filename and tokenizes that file into Tokens
   * based on the rules of the Jott Language
   *
   * @param filename the name of the file to tokenize; can be relative or absolute
   *                 path
   * @return an ArrayList of Jott Tokens
   */
  public static ArrayList<Token> tokenize(String filename) {
    String validPath = /*ROOT_PATH */ filename;
    ArrayList<Character> jottChars = new ArrayList<>();
    try (BufferedReader bfr = new BufferedReader(new FileReader(validPath))) {
      int ch;
      while ((ch = bfr.read()) != -1) {
        jottChars.add((char) ch);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    ArrayList<Token> tokens = new ArrayList<>();
    int lineNum = 1;
    try {
      for (int i = 0; i < jottChars.size(); i++) {
        String currentChar = String.valueOf(jottChars.get(i));
        if (currentChar.equals("[")) {
          tokens.add(new Token(currentChar, filename, lineNum, TokenType.L_BRACKET));
        } else if (currentChar.equals("]")) {
          tokens.add(new Token(currentChar, filename, lineNum, TokenType.R_BRACKET));
        } else if (currentChar.equals("{")) {
          tokens.add(new Token(currentChar, filename, lineNum, TokenType.L_BRACE));
        } else if (currentChar.equals("}")) {
          tokens.add(new Token(currentChar, filename, lineNum, TokenType.R_BRACE));
        } else if (currentChar.equals(",")) {
          tokens.add(new Token(currentChar, filename, lineNum, TokenType.COMMA));
        } else if (currentChar.equals(":")) {
          tokens.add(new Token(currentChar, filename, lineNum, TokenType.COLON));
        } else if (currentChar.equals(";")) {
          tokens.add(new Token(currentChar, filename, lineNum, TokenType.SEMICOLON));
        } else if (MATH_OPS.contains(currentChar)) {
          tokens.add(new Token(currentChar, filename, lineNum, TokenType.MATH_OP));
        } else if (currentChar.equals("\n")) {
          lineNum++;
        }

        else if (currentChar.equals("#")) {
          while (i < jottChars.size() && jottChars.get(i) != '\n') {
            i++;
          }
          i--;
        }

        else if (currentChar.equals("=")) {
          if (i + 1 < jottChars.size() && jottChars.get(i + 1).toString().equals("=")) {
            tokens.add(
                    new Token(currentChar + jottChars.get(i + 1).toString(), filename, lineNum, TokenType.REL_OP));
            i++;
          } else {
            tokens.add(new Token(currentChar, filename, lineNum, TokenType.ASSIGN));
          }
        }

        else if (REL_OPS.contains(currentChar)) {
          if (jottChars.get(i + 1).toString().equals("=")) {
            tokens.add(
                    new Token(currentChar + jottChars.get(i + 1).toString(), filename, lineNum, TokenType.REL_OP));
            i++;
          } else {
            tokens.add(new Token(currentChar, filename, lineNum, TokenType.REL_OP));
          }
        }

        else if (currentChar.equals(".")) {
          i++;
          if (i < jottChars.size() && Character.isDigit(jottChars.get(i))) {
            String number = ".";
            while (i < jottChars.size() && Character.isDigit(jottChars.get(i))) {
              number += jottChars.get(i);
              i++;
            }
            i--;
            tokens.add(new Token(number, filename, lineNum, TokenType.NUMBER));
          } else {
            throw new ContextUnawareSyntaxException("Unexpected character '.'");
          }
        }

        else if (Character.isDigit(jottChars.get(i))) {
          String number = "";
          while (i < jottChars.size() && Character.isDigit(jottChars.get(i))) {
            number += jottChars.get(i);
            i++;
          }
          if (jottChars.get(i) == '.') { // potential errors: .489AA, .abc, .4.5
            number += jottChars.get(i);
            i++;
            while (i < jottChars.size() && Character.isDigit(jottChars.get(i))) {
              number += jottChars.get(i);
              i++;
            }
          }
          i--;
          tokens.add(new Token(number, filename, lineNum, TokenType.NUMBER));
        }

        else if (Character.isAlphabetic(jottChars.get(i))) {
          String string = "";
          while (i < jottChars.size() && belongsInString(jottChars, i)) {
            string += jottChars.get(i);
            i++;
          }
          i--;
          tokens.add(new Token(string, filename, lineNum, TokenType.ID_KEYWORD));
        }

        else if (currentChar.equals("!")) {
          if (i + 1 < jottChars.size() && jottChars.get(i + 1).toString().equals("=")) {
            tokens.add(new Token(currentChar + jottChars.get(i + 1), filename, lineNum, TokenType.REL_OP));
            i++;
          } else {
            throw new ContextUnawareSyntaxException("Unexpected character '!'");
          }
        }

        else if (currentChar.equals("\"")) {
          String string = "\"";
          i++;
          while (i < jottChars.size() && belongsInQuotes(jottChars, i)) {
            string += jottChars.get(i);
            i++;
          }
          if (jottChars.get(i).toString().equals("\"")) {
            string += "\"";
            tokens.add(new Token(string, filename, lineNum, TokenType.STRING));
          } else {
            throw new ContextUnawareSyntaxException("Unexpected character '\"'");
          }
        }
      }
    } catch (ContextUnawareSyntaxException e) {
      e.report(filename, lineNum);
      return null;
    }
    return tokens;
  }

//  public static void main(String[] args) {
//    tokenize("src/tokenizer/ZZ_WHILELOOP_TEST.txt");
//    tokenize("src/tokenizer/ZZ_PRINT_TEST.txt");
//    tokenize("src/tokenizer/ZZ_ASSIGN_TEST.txt");
//    tokenize("testing/tokenizerTestCases/strings.jott");
//    tokenize("testing/tokenizerTestCases/errorTokens1.jott");
//    tokenize("testing/tokenizerTestCases/idKeywords.jott");
//  }
}