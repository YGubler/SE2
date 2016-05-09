package countlines;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LineCounter {

  private static final char EOL = '\n';
  private static final char EOF = '\0';

  private static int nextCharIndex = 0;
  private static String currentLine = null;
  private int lineCount = 0;
  private int emptyLineCount = 0;
  private int commentLineCount = 0;
  private static boolean countBracketAsEmpty = true;
  private static boolean atEOF = false;
  private static String[] javaOrCExtensions = new String[] {
      // list all file types which follow the '//' and '/*'
      // comment convention.
      ".java", ".h", ".cpp", ".cs", ".hpp", ".c", ".m", ".php" };

  public void countLines(String fileName) {
    try (FileReader fileReader = new FileReader(fileName)) {
      try (BufferedReader theReader = new BufferedReader(fileReader)) {
        if (isAJavaOrCFile(fileName)) {
          doJavaLineCount(theReader);
        } else if (fileName.toLowerCase().endsWith(".sql")) {
          doSQLLineCount(theReader);
        } else {
          doSimpleLineCount(theReader);
        }
        try {
          theReader.close();
        } catch (IOException ioe) {
          System.out.println("Cannot close " + fileName + " : " + ioe.toString());
        }
      } catch (FileNotFoundException fnfe) {
        System.out.println("File " + fileName + " not found.");
      }
    } catch (FileNotFoundException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  private void readNextLine(BufferedReader reader) {
    nextCharIndex = 0;
    try {
      currentLine = reader.readLine();
      if (currentLine != null) {
        lineCount++;
      } else {
        atEOF = true;
      }
      // System.out.println( currentLine );
    } catch (IOException ioe) {
      System.out.println("Error reading file. " + ioe.toString());
    }
  }

  private char getChar(BufferedReader reader) {
    char c = ' ';
    if (!atEOF) {
      if (nextCharIndex < currentLine.length()) {
        c = currentLine.charAt(nextCharIndex);
        nextCharIndex++;
      } else {
        c = EOL;
        readNextLine(reader);
      }
    } else {
      c = EOF;
    }
    return c;
  }

  public void doJavaLineCount(BufferedReader reader) {
    char ch = ' ';
    char lastCh = ' ';
    boolean commentLine = false;
    boolean inSlashStarComment = false;
    boolean stillEmptyLine = true;
    initCountingVariables();
    readNextLine(reader);
    ch = getChar(reader);
    while (ch != EOF) {

      if (ch == EOL) {
        if (stillEmptyLine) {
          emptyLineCount++;
        } else if (commentLine || inSlashStarComment) {
          commentLineCount++;
        }
        stillEmptyLine = true;
        commentLine = false;
      } else {
        switch (ch) {
        case '*':
          if ((lastCh == '/') && (!commentLine)) {
            inSlashStarComment = true;
            if (stillEmptyLine) {
              commentLine = true;
            }
          }
          break;
        case '/':
          if (lastCh == '*') {
            inSlashStarComment = false;
            if (stillEmptyLine) {
              commentLine = true;
            }
          } else if ((lastCh == '/') && stillEmptyLine) {
            commentLine = true;
          }
          break;
        default:
          // all other characters are consumed without counting
          ;
        }
        if (stillEmptyLine) {
          stillEmptyLine = checkIfLineIsStillEmpty(ch);
        }
      }
      lastCh = ch;
      ch = getChar(reader);
    }
  }

  public void doSQLLineCount(BufferedReader reader) {
    char ch = ' ';
    char lastCh = ' ';
    boolean commentLine = false;
    boolean inSlashStarComment = false;
    boolean stillEmptyLine = true;

    initCountingVariables();
    readNextLine(reader);
    ch = getChar(reader);
    while (ch != EOF) {

      if (ch == EOL) {
        if (stillEmptyLine) {
          emptyLineCount++;
        } else if (commentLine || inSlashStarComment) {
          commentLineCount++;
        }
        stillEmptyLine = true;
        commentLine = false;
      } else {
        switch (ch) {
        case '*':
          if ((lastCh == '/') && (!commentLine)) {
            inSlashStarComment = true;
            if (stillEmptyLine) {
              commentLine = true;
            }
          }
          break;
        case '/':
          if (lastCh == '*') {
            inSlashStarComment = false;
            if (stillEmptyLine) {
              commentLine = true;
            }
          }
          break;
        case '-':
          if ((lastCh == '-') && stillEmptyLine) {
            commentLine = true;
          }
          break;
        default:
          // all other characters are consumed without counting
          ;
        }
        if (stillEmptyLine) {
          stillEmptyLine = checkIfLineIsStillEmpty(ch);
        }
      }
      lastCh = ch;
      ch = getChar(reader);
    }
  }

  public void doSimpleLineCount(BufferedReader reader) {
    char ch = ' ';
    boolean stillEmptyLine = true;

    initCountingVariables();
    readNextLine(reader);
    ch = getChar(reader);
    while (ch != EOF) {

      if (ch == EOL) {
        if (stillEmptyLine) {
          emptyLineCount++;
        }
        stillEmptyLine = true;
      }
      if (stillEmptyLine) {
        if (ch > ' ') {
          stillEmptyLine = false;
        }
      }
      ch = getChar(reader);
    }
  }

  private static boolean checkIfLineIsStillEmpty(char ch) {
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

  private static boolean isAJavaOrCFile(String fileName) {
    for (String ext : javaOrCExtensions) {
      if (fileName.toLowerCase().endsWith(ext.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  private void initCountingVariables() {
    lineCount = 0;
    emptyLineCount = 0;
    commentLineCount = 0;
    atEOF = false;
  }

  public int getNetLineCount() {
    return lineCount - emptyLineCount - commentLineCount;
  }

  public int getTotalLineCount() {
    return lineCount;
  }

  public int getEmptyLineCount() {
    return emptyLineCount;
  }

  public int getCommentLineCount() {
    return commentLineCount;
  }
}
