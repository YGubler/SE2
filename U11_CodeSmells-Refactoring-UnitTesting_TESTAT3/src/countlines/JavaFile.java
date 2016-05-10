package countlines;

import java.io.BufferedReader;

public class JavaFile implements FileType {
  public void doJavaLineCount(BufferedReader reader, LineCounter lineCounter) {
    char ch = ' ';
    char lastCh = ' ';
    boolean commentLine = false;
    boolean inSlashStarComment = false;
    boolean stillEmptyLine = true;
    lineCounter.initCountingVariables();
    lineCounter.readNextLine(reader);
    ch = lineCounter.getChar(reader);
    while (ch != lineCounter.EOF) {

      if (ch == lineCounter.EOL) {
        if (stillEmptyLine) {
          lineCounter.emptyLineCount++;
        } else if (commentLine || inSlashStarComment) {
          lineCounter.commentLineCount++;
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
          stillEmptyLine = lineCounter.checkIfLineIsStillEmpty(ch);
        }
      }
      lastCh = ch;
      ch = lineCounter.getChar(reader);
    }
  }

  @Override
  public void lineCount(BufferedReader reader) {
    // TODO Auto-generated method stub
    
  }
}
