package countlines;

import java.io.BufferedReader;

public class JavaFile extends FileStrategy {

  @Override
  public void lineCount(BufferedReader reader, LineCounterResults results) {
    char ch = ' ';
    char lastCh = ' ';
    boolean commentLine = false;
    boolean inSlashStarComment = false;
    boolean stillEmptyLine = true;
    readNextLine(reader, results);
    ch = getChar(reader, results);
    while (ch != EOF) {

      if (ch == EOL) {
        if (stillEmptyLine) {
          results.emptyLineCount++;
        } else if (commentLine || inSlashStarComment) {
          results.commentLineCount++;
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
      ch = getChar(reader, results);
    }
  }
}
