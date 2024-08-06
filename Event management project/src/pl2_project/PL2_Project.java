package pl2_project;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Mohamed Hassan
 */
public class PL2_Project {
	public static void main(String[] args) throws IOException {
		Admin u1 = new Admin("admin", "admin@gmail", "1234", "021565262", "Admin");
                u1.addUser(u1,"0");
           while(true){
		System.out.println("login as 1->Admin , 2->Customer , 3->Project Manager , 4->Service Provider");
		Scanner input = new Scanner(System.in);
		byte choise = input.nextByte();
		switch (choise) {
			case 1 -> Mains.AdminMain();
			case 2 -> Mains.CustomerMain();
			case 3 -> Mains.PmMain();
			case 4 -> Mains.SpMain();
			default -> System.out.println("Invalid input");
		}
           }
	}
}
