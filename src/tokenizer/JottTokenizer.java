package tokenizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author Ghislaine Nyagatare,
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import utils.Token;
import utils.TokenType;

public class JottTokenizer {

  private static final char HASHTAG = '#';

  private static void removeComments(List<Character> jottChars) {
    List<Character> result = new ArrayList<>();
    for (int i = 0; i < jottChars.size(); i++) {
      if (jottChars.get(i).equals(HASHTAG)) {
        while (!jottChars.get(i).equals('\n')) {
          i++;
        }
        i++;
      }
      result.add(jottChars.get(i));
    }
    // System.out.println(result);
    jottChars = result;
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
    ArrayList<Character> jottChars = new ArrayList<>();
    try (BufferedReader bfr = new BufferedReader(new FileReader(new File(filename)))) {
      int ch;
      while ((ch = bfr.read()) != -1) {
        jottChars.add((char) ch);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    // PASS 0: get rid of spaces
    List<Character> filteredJottChars = jottChars
        .stream()
        .filter(ch -> !ch.equals(' '))
        .collect(Collectors.toList());

    // PASS 1: get rid of comments
    removeComments(filteredJottChars);
    // PASS 2: convert to tokens
    List<Token> tokens = new ArrayList<>();
    int lineNum = 0;
    for (int i = 0; i < filteredJottChars.size(); i++) {
      String currentChar = String.valueOf(filteredJottChars.get(i));
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
      }

      else if (currentChar.equals("=")) {
        if (filteredJottChars.get(i + 1).toString().equals("=")) {
          tokens.add(
              new Token(currentChar + filteredJottChars.get(i + 1).toString(), filename, lineNum, TokenType.REL_OP));
          i++;
        } else {
          tokens.add(new Token(currentChar, filename, lineNum, TokenType.ASSIGN));
        }
      }

      else if (currentChar.equals("<")) {
        if (filteredJottChars.get(i + 1).toString().equals("=")) {
          tokens.add(
              new Token(currentChar + filteredJottChars.get(i + 1).toString(), filename, lineNum, TokenType.REL_OP));
          i++;
        } else {
          tokens.add(new Token(currentChar, filename, lineNum, TokenType.REL_OP));
        }
      }

      else if (currentChar.equals("/") || currentChar.equals("+") || currentChar.equals("*")) {
        tokens.add(new Token(currentChar, filename, lineNum, TokenType.MATH_OP));
      } else if (currentChar.equals(";")) {
        tokens.add(new Token(currentChar, filename, lineNum, TokenType.SEMICOLON));
        lineNum++; // line numbers are determined by ;
      }

      else if (currentChar.equals(".")) {
        i++;
        if (Character.isDigit(filteredJottChars.get(i))) {
          String number = "";
          while (Character.isDigit(filteredJottChars.get(i))) {
            number += filteredJottChars.get(i);
            i++;
          }
          tokens.add(new Token(number, filename, lineNum, TokenType.NUMBER));
        } else {
          // report error
        }
      }

      else if (Character.isDigit(filteredJottChars.get(i))) {
        String number = "";
        while (Character.isDigit(filteredJottChars.get(i))) {
          number += filteredJottChars.get(i);
          i++;
        }
        if (filteredJottChars.get(i) == '.') {
          while (Character.isDigit(filteredJottChars.get(i))) {
            number += filteredJottChars.get(i);
            i++;
          }
        }
        tokens.add(new Token(number, filename, lineNum, TokenType.NUMBER));
      }

      else if (Character.isAlphabetic(filteredJottChars.get(i))) {
        String string = "";
        while (Character.isAlphabetic(filteredJottChars.get(i)) || Character.isDigit(filteredJottChars.get(i))) {
          string += filteredJottChars.get(i);
          i++;
        }
        tokens.add(new Token(string, filename, lineNum, TokenType.ID_KEYWORD));
      }

      else if (currentChar.equals(":")) {
        tokens.add(new Token(currentChar, filename, lineNum, TokenType.COLON));
      }

      else if (currentChar.equals("!")) {
        if (filteredJottChars.get(i + 1).toString().equals("=")) {
          tokens.add(new Token(currentChar, filename, lineNum, TokenType.REL_OP));
          // NOT EQUALS TO BE SPECIFIC (not supported by TokenType, might add later)
        } else {
          // report error
        }
      }

      else if (currentChar.equals("\"")) {
        String string = "";
        while (Character.isAlphabetic(filteredJottChars.get(i)) || Character.isDigit(filteredJottChars.get(i))
            || Character.isSpaceChar(filteredJottChars.get(i))) {
          string += filteredJottChars.get(i);
          i++;
        }
        if (filteredJottChars.get(i).toString().equals("\"")) {
          tokens.add(new Token(string, filename, lineNum, TokenType.STRING));
        } else {
          // report error
        }
      }
    }
    return (ArrayList<Token>) tokens;
  }

  public static void main(String[] args) {
    tokenize("src/tokenizer/testing.txt");
  }
}