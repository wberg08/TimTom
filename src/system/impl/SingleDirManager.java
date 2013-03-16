package system.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import system.FileManager;

public class SingleDirManager implements FileManager {
  private static final String fileDirectory =
      System.getProperty("user.dir") +
      File.separator + 
      "res" + File.separator;
  
  @Override
  public File getFile(String fileName) {
//    Don't abuse this plz
//    if(fileName.contains("..")) {
//      System.err.println("Tried to get a file with a path with '..' in it");
//      return new File(fileDirectory + "wrong.wrn");
//    }
    
    File f = new File(fileDirectory + fileName);
    if(!f.exists())
      try {
        f.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
    return f;
  }

  @Override
  public void setFile(String fileName) {
    // hard write
    File f = new File(fileDirectory + fileName);
    f.delete();
    new File(fileDirectory + fileName);
  }

  @Override
  public Object getFromFile(String fileName) throws Exception {
    File f = getFile(fileName);
    FileInputStream fin = null;
    ObjectInputStream oin = null;
    try {
      fin = new FileInputStream(f);
      oin = new ObjectInputStream(fin);
      return oin.readObject();
    }
    finally {
      oin.close();
    }
  }

  @Override
  public void setToFile(String fileName, Object object) {
    File f = getFile(fileName);
    FileOutputStream fout = null;
    ObjectOutputStream oout = null;
    try {
      fout = new FileOutputStream(f);
      oout = new ObjectOutputStream(fout);
      oout.writeObject(object);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
