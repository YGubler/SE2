package tests;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import countlines.JavaFile;
import countlines.LineCounter;
import countlines.LineCounterResults;
import countlines.SQLFile;
import countlines.SimpleFile;

public class TestLineCounter {
  private static final int DEFAULT_TIMEOUT = 2000;
  
  @Test(timeout = DEFAULT_TIMEOUT)
  public void testDoJavaLineCount() {
    int expTotalLine = 35;
    int expCommentLine = 1;
    int expEmptyLine = 10;
    LineCounterResults results = new LineCounterResults();
    try(FileReader fr = new FileReader("testFiles/JavaTestFile.txt")){
      try(BufferedReader br = new BufferedReader(fr)){
        JavaFile javaFile = new JavaFile();
        javaFile.lineCount(br, results);
      } 
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(expTotalLine, results.getLineCount());
    assertEquals(expCommentLine, results.getCommentLineCount());
    assertEquals(expEmptyLine, results.getEmptyLineCount());
  }
  
  @Test(timeout = DEFAULT_TIMEOUT)
  public void testDoSQLLineCount() {
    int expTotalLine = 87;
    int expCommentLine = 1;
    int expEmptyLine = 11;
    LineCounterResults results = new LineCounterResults();
    try(FileReader fr = new FileReader("testFiles/SQLTestFile.txt")){
      try(BufferedReader br = new BufferedReader(fr)){
        SQLFile sqlFile = new SQLFile();
        sqlFile.lineCount(br, results);
      } 
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(expTotalLine, results.getLineCount());
    assertEquals(expCommentLine, results.getCommentLineCount());
    assertEquals(expEmptyLine, results.getEmptyLineCount());
  }
  
  @Test(timeout = DEFAULT_TIMEOUT)
  public void testDoSimpleineCount() {
    int expTotalLine = 45;
    int expEmptyLine = 19;
    LineCounterResults results = new LineCounterResults();
    try(FileReader fr = new FileReader("testFiles/SimpleTestFile.pas")){
      try(BufferedReader br = new BufferedReader(fr)){
        SimpleFile simpleFile = new SimpleFile();
        simpleFile.lineCount(br, results);
      } 
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(expTotalLine, results.getLineCount());
    assertEquals(expEmptyLine, results.getEmptyLineCount());
  }
}
