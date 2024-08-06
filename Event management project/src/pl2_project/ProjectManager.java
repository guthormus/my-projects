/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2_project;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pl2_project.fileHandling.appendFile;
import static pl2_project.fileHandling.readFile;
import static pl2_project.fileHandling.writeFile;

/**
 *
 * @author DELL
 */
class ProjectManager extends Person {

  public ProjectManager(String name, String email, String password, String phone, String role) {
    super(name, email, password, phone, role);
  }


  public void contactCustomer(int Id, String message) throws IOException {
    String fileName = "Chat_" + Id;
    appendFile(fileName, "(PM): " + message + "\n");
  }

  public String receiveRequest(int CId) throws IOException {
    String f = readFile("Customer" + CId + "_book");
    writeFile("Manage" + CId + "SP",  f);
    return f;
  }

  public void objectifyEventPm(String fileName, Event ee) throws IOException {
    String s = readFile(fileName);
    String[] arr = s.split("\n");
    ee.name = arr[0].split(":")[1];
    ee.date = arr[1].split(":")[1];
    ee.place = arr[2].split(":")[1];
    ee.numberOfChairs = parseInt(arr[3].split(":")[1]);
    ee.numberOfTables = parseInt(arr[4].split(":")[1]);
    ee.state = parseInt(arr[5].split(":")[1]);
  }
  
  public void showBill(Event e22,int Id11) throws IOException{
            String f = readFile("Manage" + Id11 + "SP");
            writeFile("Customer" + Id11 + "_book", f);
            Bill b = new Bill(e22.numberOfChairs, e22.numberOfTables);
            appendFile("Customer" + Id11 + "_book", "\n" + b.toString());
                }

  public static int login(String email, String pass) {
    try {
      String s = readFile("PM");
      String[] lines = s.split("\n");
      for (String line : lines) {
        String[] parts = line.split(",");
        if (parts.length >= 4 && parts[2].equals(email) && parts[3].equals(pass)) {
          return parseInt(parts[0]);
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex.toString());
    }
    return -1;
  }

}