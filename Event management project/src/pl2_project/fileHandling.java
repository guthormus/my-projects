package pl2_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class fileHandling {

  // create file
  public static int createFile(String filePath) throws IOException {
    File file = new File(filePath + ".txt");
    if (file.createNewFile()) {
      return 1;
    } else {
      return 2;
    }
  }

  // read file
  public static String readFile(String filePath) throws IOException {
    File file = new File(filePath + ".txt");
    if (file.exists()) {
      StringBuilder text = new StringBuilder();
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
          text.append(line).append("\n");
        }
      }
      return text.toString().trim();
    } else {
      return "";
    }
  }

  // append to file
  public static int appendFile(String filePath, String text) throws IOException {
    File file = new File(filePath + ".txt");
    try (FileWriter writer = new FileWriter(file, true)) {
      writer.append(text);
      writer.close();
    }
    return 1;
  }

  // write to file
  public static int writeFile(String filePath, String content) throws IOException {
    File file = new File(filePath + ".txt");
    try (FileWriter writer = new FileWriter(file, false)) {
      writer.write(content);
      writer.close();
    }
    return 1;
  }

}
