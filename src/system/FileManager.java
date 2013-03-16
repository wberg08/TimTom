package system;

import java.io.File;

public interface FileManager {
  /*
   * Keep files somewhere. Durh, repeated file names?
   */
  public File getFile(String fileName);

  public void setFile(String fileName);

  /*
   * @throws Probably the file wasn't found.
   */
  public Object getFromFile(String fileName) throws Exception;

  public void setToFile(String fileName, Object object);
}
