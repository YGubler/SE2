package countlines;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class FileStrategy {
  static final char EOL = '\n';
  static final char EOF = '\0';

  int nextCharIndex = 0;
  static String currentLine = null;
  private static boolean countBracketAsEmpty = true;
  private static String[] javaOrCExtensions = new String[] {
      // list all file types which follow the '//' and '/*'
      // comment convention.
      ".java", ".h", ".cpp", ".cs", ".hpp", ".c", ".m", ".php" };
  
  static boolean checkIfLineIsStillEmpty(char ch) {
    if ((ch > ' ') && (ch != '/') && (ch != '*') && (ch != '-') && (ch != '+') && (ch != '#')) {
      if ((ch == '{') && countBracketAsEmpty) {
        // do nothing, ignore a single opening curly bracket
      } else {
        return false;
      }
    } else {
      // All characters lower than SPACE do not count;
      // slashes, stars, minuses etc. do not count because they are
      // (usually) the comment delimiters or used to create a separating
      // line like: /*************************************
    }
    return true;
  }
  
  char getChar(BufferedReader reader, LineCounterResults results) {
    char c = ' ';
    if (!results.atEOF) {
      if (nextCharIndex < currentLine.length()) {
        c = currentLine.charAt(nextCharIndex);
        nextCharIndex++;
      } else {
        c = EOL;
        readNextLine(reader, results);
      }
    } else {
      c = EOF;
    }
    return c;
  }
  
  void readNextLine(BufferedReader reader, LineCounterResults results) {
    nextCharIndex = 0;
    try {
      currentLine = reader.readLine();
      if (currentLine != null) {
        results.lineCount++;
      } else {
        results.atEOF = true;
      }
    } catch (IOException ioe) {
      System.out.println("Error reading file. " + ioe.toString());
    }
  }
  
  public abstract void lineCount(BufferedReader reader, LineCounterResults results);
}
