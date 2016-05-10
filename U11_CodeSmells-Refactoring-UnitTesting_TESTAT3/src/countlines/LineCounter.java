package countlines;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LineCounter {

  LineCounterResults results = new LineCounterResults();
  private static String[] javaOrCExtensions = new String[] {
      // list all file types which follow the '//' and '/*'
      // comment convention.
      ".java", ".h", ".cpp", ".cs", ".hpp", ".c", ".m", ".php" };

  public void countLines(String fileName) {
    try (FileReader fileReader = new FileReader(fileName)) {
      try (BufferedReader reader = new BufferedReader(fileReader)) {
        if (isAJavaOrCFile(fileName)) {
          JavaFile javaFile = new JavaFile();
          javaFile.lineCount(reader, results);
        } else if (fileName.toLowerCase().endsWith(".sql")) {
          SQLFile sqlFile = new SQLFile();
          sqlFile.lineCount(reader, results);
        } else {
          SimpleFile simpleFile = new SimpleFile();
          simpleFile.lineCount(reader, results);
        }
        try {
          reader.close();
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

  private static boolean isAJavaOrCFile(String fileName) {
    for (String ext : javaOrCExtensions) {
      if (fileName.toLowerCase().endsWith(ext.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
}
