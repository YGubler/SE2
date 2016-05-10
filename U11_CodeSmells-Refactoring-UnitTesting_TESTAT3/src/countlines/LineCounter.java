package countlines;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LineCounter {

  LineCounterResults results = new LineCounterResults();

  public void countLines(String fileName) {
    try (FileReader fileReader = new FileReader(fileName)) {
      try (BufferedReader reader = new BufferedReader(fileReader)) {
        FileStrategy file = FileFactory.makeFileObject(fileName);
        file.lineCount(reader, results);
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
}
