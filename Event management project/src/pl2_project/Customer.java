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
 * @author Mohamed Hassan
 */
public class Customer extends Person {


	public Customer(String name, String email, String password, String phone, String role) {
		super(name, email, password, phone, role);
	}

	public String getCustomerId() {
		return this.id;
	}

        
	public void book(Event e, int id) throws IOException {
		writeFile("Customer" + id + "_book", e.toString());
                appendFile("CustomersBooking","Customer ID:"+id+" -> "+"His Event Name: "+ e.name +"\n");
	}

	public int manageBooking(Event e, String s, String newValue, int id) throws IOException {
		if (s.equalsIgnoreCase("name")) {
			e.name = newValue;
		} else if (s.equalsIgnoreCase("place")) {
			e.place = newValue;
		} else if (s.equalsIgnoreCase("date")) {
			e.date = newValue;
		} else if (s.equalsIgnoreCase("numberOfChairs")) {
			e.numberOfChairs = parseInt(newValue);
		} else if (s.equalsIgnoreCase("numberOfTables")) {
			e.numberOfTables = parseInt(newValue);
		} else {
			return -1;
		}
		writeFile("Customer" + id + "_book", e.toString());
		return 0;
	}

	public String checkState(Event e, int id) throws IOException {
		String s = readFile("Customer" + id + "_book");
		return s.split("\n")[5];
	}

	public void contactPM(String message, int id) throws IOException {
		String fileName = "Chat_" + id;
		appendFile(fileName, getName() + " (Customer): " + message + "\n");
	}

	public void objectifyEvent(String fileName, Event ee) throws IOException {
		String s = readFile(fileName);
		String[] arr = s.split("\n");
		ee.name = arr[0].split(":")[1];
		ee.date = arr[1].split(":")[1];
		ee.place = arr[2].split(":")[1];
		ee.numberOfChairs = parseInt(arr[3].split(":")[1]);
		ee.numberOfTables = parseInt(arr[4].split(":")[1]);
	}

	public String viewRespond(int Id) throws IOException {
		return readFile("Customer" + Id + "_book");
	}

	public static int login(String email, String pass) {
		try {
			String s = readFile("Customer");
			String[] lines = s.split("\n");
			for (String line : lines) {
				String[] parts = line.split(",");
				if (parts.length >= 4 && parts[2].equals(email) && parts[3].equals(pass)) {
					return parseInt(parts[0]);
				}
			}
		} catch (IOException ex) {
			Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
		}
		return -1;
	}

}
