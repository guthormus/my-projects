package pl2_project;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pl2_project.fileHandling.createFile;
import static pl2_project.fileHandling.appendFile;
import static pl2_project.fileHandling.readFile;
import static pl2_project.fileHandling.writeFile;

/**
 *
 * @author Mohamed Hassan
 */
public class Admin extends Person {

    String users = readFile("Customer");
    String pm = readFile("PM");
    String sp = readFile("SP");
    String admin = readFile("Admin");

    public Admin(String name, String email, String password, String phone, String role) throws IOException {
        super(name, email, password, phone, role);
    }

    // add user
    public int addUser(Person user, String userId) throws IOException {
        String s = new String();
        if (null == user.role) {
            return -1;
        } else {
            switch (user.role) {
                case "Admin" ->
                    s = admin;
                case "Customer" ->
                    s = users;
                case "SP" ->
                    s = sp;
                case "PM" ->
                    s = pm;
                default -> {
                    return -1;
                }
            }
        }
        String newUserEntry = userId + "," + user.name + "," + user.email + "," + user.password + "," + user.phone + "," + user.role;
        if (!s.contains(user.name + "," + user.email + "," + user.password + "," + user.phone + "," + user.role)) {

            if (createFile(user.role) == 2) {
                appendFile(user.role, newUserEntry + "\n");
            } else {
                createFile(user.role);
                writeFile(user.role, newUserEntry + "\n");
            }
            return 0;
        } else {
            return 1;
        }
    }

    // update
    public int updateUserById(String userId, Person updatedUser) throws IOException {
        String s = new String();
        if (null == updatedUser.role) {
            return -1;
        } else {
            switch (updatedUser.role) {
                case "Admin" ->
                    s = admin;
                case "Customer" ->
                    s = users;
                case "SP" ->
                    s = sp;
                case "PM" ->
                    s = pm;
                default -> {
                    return -1;
                }
            }
        }

        // Check if the user with the given ID exists
        if (s.contains(userId)) {
            String[] lines = s.split("\n");
            StringBuilder updatedData = new StringBuilder();

            for (String line : lines) {
                if (line.startsWith(userId + ",")) {
                    String updatedUserEntry = userId + "," + updatedUser.name + "," + updatedUser.email + ","
                            + updatedUser.password + "," + updatedUser.phone + "," + updatedUser.role;
                    updatedData.append(updatedUserEntry).append("\n");
                } else {
                    updatedData.append(line).append("\n");
                }
            }

            writeFile(updatedUser.role, updatedData.toString());
            return 0; // User updated successfully
        } else {
            return 1; // User with the given ID does not exist
        }
    }

    // delete
    public void deleteUserById(String idToDelete, String currentRole) throws IOException {
        String fileName = "";
        switch (currentRole) {
            case "Admin" ->
                fileName = "Admin";
            case "Customer" ->
                fileName = "Customer";
            case "PM" ->
                fileName = "PM";
            case "SP" ->
                fileName = "SP";
            default -> {
            }
        }

        String fileContent = readFile(fileName);
        String[] lines = fileContent.split("\n");

        StringBuilder updatedFileContent = new StringBuilder();

        for (String line : lines) {
            String[] userData = line.split(",");
            if (userData.length >= 6 && userData[0].equals(idToDelete)) {
                // Skip the line for the user to delete
                continue;
            }

            // Rebuild the line without the user to delete
            StringBuilder updatedLine = new StringBuilder();
            for (String data : userData) {
                updatedLine.append(data).append(",");
            }
            updatedLine.deleteCharAt(updatedLine.length() - 1); // Remove the trailing comma
            updatedFileContent.append(updatedLine).append("\n");
        }

        // Write the updated content back to the file
        writeFile(fileName, updatedFileContent.toString());
    }

    public static int login(String email, String pass) {
        try {
            String s = readFile("Admin");
            String[] lines = s.split("\n");
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[2].equals(email) && parts[3].equals(pass)) {
                    return parseInt(parts[0]);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
