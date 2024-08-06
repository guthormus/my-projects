
package pl2_project;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pl2_project.fileHandling.readFile;
import static pl2_project.fileHandling.writeFile;

/**
 *
 * @author Mohamed Hassan
 */
public class SP extends Person {

  public SP(String name, String email, String password, String phone, String role) {
    super(name, email, password, phone, role);
  }

  public void objectifyEventSp(String fileName, Event ee) throws IOException {
    String s = readFile(fileName);
    String[] arr = s.split("\n");
    ee.name = arr[0].split(":")[1];
    ee.date = arr[1].split(":")[1];
    ee.place = arr[2].split(":")[1];
    ee.numberOfChairs = parseInt(arr[3].split(":")[1]);
    ee.numberOfTables = parseInt(arr[4].split(":")[1]);
    ee.state = parseInt(arr[5].split(":")[1]);
  }

  public String updateState(int Id, int respond) throws IOException {
    String f = readFile("Manage" + Id + "SP");
    String state = f.split("\n")[5];
    switch (respond) {
      case 1:
        f = f.replace(state, "state:1");
        break;
      case 2:
        f = f.replace(state, "state:0");
        break;
      default:
        return "Invalid input";
    }
    writeFile("Manage" + Id + "SP", f);
    return readFile("Manage" + Id + "SP");
  }

  public String SetPrice(Event ee, int Id) throws IOException {
    objectifyEventSp("Manage" + Id + "SP", ee);
    double price = 10 * ee.numberOfChairs + 20 *ee.numberOfTables;
    writeFile("Manage" + Id + "SP", ee.toString() + "\nTotal Price = "+price);
    return "The Price has been setted";
  }

  public static int login(String email, String pass) {
    try {
      String s = readFile("SP");
      String[] lines = s.split("\n");
      for (String line : lines) {
        String[] parts = line.split(",");
        if (parts.length >= 4 && parts[2].equals(email) && parts[3].equals(pass)) {
          return parseInt(parts[0]);
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(SP.class.getName()).log(Level.SEVERE, null, ex);
    }
    return -1;
  }

}
