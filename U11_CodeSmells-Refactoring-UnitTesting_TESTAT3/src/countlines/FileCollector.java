package countlines;

import java.io.File;
import java.util.Vector;

public class FileCollector {
  private Vector allFiles = new Vector();
  
  Vector collectFiles(String directory, Vector fileExtensions) {
    if (!directory.endsWith("/") && !directory.endsWith("\\")) {
      directory += "/";
    }
    File currentdir = new File(directory);
    String[] theFiles = currentdir.list();
    if (theFiles != null) {
      for (String aFile : theFiles) {
        String fileName = directory + aFile;
        File f = new File(fileName);
        if (f.isDirectory()) {
          allFiles = collectFiles(fileName + '/', fileExtensions);
        } else if (f.isFile() && f.canRead()) {
          if (hasRightExtension(fileName, fileExtensions)) {
            allFiles.add(fileName);
          }
        } else {
          System.out.println("?????" + fileName);
        }
      }
    }
    return allFiles;
  }

  private boolean hasRightExtension(String fileName, Vector fileExtensions) {
    for (int i = 0; i < fileExtensions.size(); i++) {
      if (fileName.toLowerCase().endsWith(fileExtensions.elementAt(i).toString())) {
        return true;
      }
    }
    return false;
  }
}