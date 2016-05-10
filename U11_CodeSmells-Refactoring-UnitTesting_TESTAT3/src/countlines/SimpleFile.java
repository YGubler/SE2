package countlines;

import java.io.BufferedReader;

public class SimpleFile extends FileType {

  @Override
  public void lineCount(BufferedReader reader, LineCounterResults results) {
    char ch = ' ';
    boolean stillEmptyLine = true;

    readNextLine(reader, results);
    ch = getChar(reader, results);
    while (ch != EOF) {

      if (ch == EOL) {
        if (stillEmptyLine) {
          results.emptyLineCount++;
        }
        stillEmptyLine = true;
      }
      if (stillEmptyLine) {
        if (ch > ' ') {
          stillEmptyLine = false;
        }
      }
      ch = getChar(reader, results);
    }
  }
}
