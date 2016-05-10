package countlines;

public abstract class FileFactory {
  private static String[] javaOrCExtensions = new String[] {
      // list all file types which follow the '//' and '/*'
      // comment convention.
      ".java", ".h", ".cpp", ".cs", ".hpp", ".c", ".m", ".php" };
  
  public static FileStrategy makeFileObject(String fileName){
    if (isAJavaOrCFile(fileName)) {
      JavaFile javaFile = new JavaFile();
      return javaFile;
    } else if (fileName.toLowerCase().endsWith(".sql")) {
      SQLFile sqlFile = new SQLFile();
      return sqlFile;
    } else {
      SimpleFile simpleFile = new SimpleFile();
      return simpleFile;
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
