package countlines;

public class LineCounterResults {

  int lineCount = 0;
  int emptyLineCount = 0;
  int commentLineCount = 0;
  boolean atEOF = false;
  
  
  public int getLineCount() {
    return lineCount;
  }
  public void setLineCount(int lineCount) {
    this.lineCount = lineCount;
  }
  public int getEmptyLineCount() {
    return emptyLineCount;
  }
  public void setEmptyLineCount(int emptyLineCount) {
    this.emptyLineCount = emptyLineCount;
  }
  public int getCommentLineCount() {
    return commentLineCount;
  }
  public void setCommentLineCount(int commentLineCount) {
    this.commentLineCount = commentLineCount;
  }
  public boolean isAtEOF() {
    return this.atEOF;
  }
  public void setAtEOF(boolean atEOF) {
    this.atEOF = atEOF;
  }
  public int getNetLineCount() {
    return lineCount - emptyLineCount - commentLineCount;
  }
}
