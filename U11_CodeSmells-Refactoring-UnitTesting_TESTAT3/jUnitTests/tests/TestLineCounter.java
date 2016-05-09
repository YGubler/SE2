package tests;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import countlines.LineCounter;

public class TestLineCounter {
  private static final int DEFAULT_TIMEOUT = 2000;
  
  @Test(timeout = DEFAULT_TIMEOUT)
  public void testDoJavaLineCount() {
    LineCounter lineCounter = new LineCounter();
    int expTotalLine = 35;
    int expCommentLine = 1;
    int expEmptyLine = 10;
    try(FileReader fr = new FileReader("testFiles/JavaTestFile.txt")){
      try(BufferedReader br = new BufferedReader(fr)){
        lineCounter.doJavaLineCount(br);
      } 
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(expTotalLine, lineCounter.getTotalLineCount());
    assertEquals(expCommentLine, lineCounter.getCommentLineCount());
    assertEquals(expEmptyLine, lineCounter.getEmptyLineCount());
  }
  
  @Test(timeout = DEFAULT_TIMEOUT)
  public void testDoSQLLineCount() {
    LineCounter lineCounter = new LineCounter();
    int expTotalLine = 87;
    int expCommentLine = 1;
    int expEmptyLine = 11;
    try(FileReader fr = new FileReader("testFiles/SQLTestFile.txt")){
      try(BufferedReader br = new BufferedReader(fr)){
        lineCounter.doSQLLineCount(br);
      } 
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(expTotalLine, lineCounter.getTotalLineCount());
    assertEquals(expCommentLine, lineCounter.getCommentLineCount());
    assertEquals(expEmptyLine, lineCounter.getEmptyLineCount());
  }
  
  @Test(timeout = DEFAULT_TIMEOUT)
  public void testDoSimpleineCount() {
    LineCounter lineCounter = new LineCounter();
    int expTotalLine = 45;
    int expEmptyLine = 19;
    try(FileReader fr = new FileReader("testFiles/SimpleTestFile.pas")){
      try(BufferedReader br = new BufferedReader(fr)){
        lineCounter.doSimpleLineCount(br);
      } 
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(expTotalLine, lineCounter.getTotalLineCount());
    assertEquals(expEmptyLine, lineCounter.getEmptyLineCount());
  }
}
