import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class file {
    public static boolean admincheck;
    //Create file
   /* public static String createFile(String name) {
        try {
            // define object
            File myObj = new File(name + ".txt");
            // if condition to create and check in the same time
            if (myObj.createNewFile()) {
                return "file created successfully";
            } else {
                return "file already exist";
            }
        } catch (IOException e) {
            return "error occurred";
        }
    }*/

    public static boolean adminCheck() {
        String user = "";
        String pass = "";
        Scanner adminCheckScan = new Scanner(System.in);
        System.out.print("Username: ");
        user += adminCheckScan.nextLine();
        System.out.print("Password: ");
        pass += adminCheckScan.nextLine();
        try {
            int singleLoginCheck = 0;
            File myObj = new File("Admin.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals(user)) {
                    singleLoginCheck++;
                    if (myReader.hasNextLine()) {
                        String nextLine = myReader.nextLine();

                        if (nextLine.equals(pass)) {
                            singleLoginCheck++;
                            admincheck = true;
                        }
                    }
                }
            }
            if (singleLoginCheck == 0 || singleLoginCheck == 1)
                admincheck = false;
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
        return admincheck;
    }
    //read file
    public static int readFile(String name) {

        try {
            // Create obj with the file name
            File myObj = new File(name + ".txt");


            Scanner myReader = new Scanner(myObj);
                 int data = myReader.nextInt();
                 return data;


            //myReader.close();
        } catch (Exception e) {
            return -2;

        }

    }
    //writeSpot in the file
    public static String writeFile(String name, int slot) {
        try {
            //name of the file you will write in
            FileWriter write = new FileWriter(name + ".txt");
            // Scanner writer = new Scanner(System.in);
            //String text = writer.nextLine();
            write.write(String.valueOf(slot));
            write.close();

            return "data added successfully";

        } catch (IOException e) {
            return "data couldn't be added";

        }

    }

    //append in the file
    public static String appendFile(String name, String infoToAppend) {
        try {
            if (!dataExists(name, infoToAppend)) {
                FileWriter writer = new FileWriter(name + ".txt", true);
                writer.write("\n" + infoToAppend);
                writer.close();
                return "Information appended successfully.";
            } else {
                return "Data already exists in the file. No changes made.";
            }
        } catch (IOException e) {
            return "An error occurred.";
        }
    }

    //edit data in file
    public static String editFile(String name, String oldUsername, String oldPassword) {
        try {
            File file = new File(name + ".txt");
            Scanner scanner = new Scanner(file);

            File tempFile = new File(name + "_temp.txt");
            FileWriter tempWriter = new FileWriter(tempFile);

            boolean found = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains(oldUsername)) {

                    if (scanner.hasNextLine()) {
                        String passwordLine = scanner.nextLine();

                        if (passwordLine.contains(oldPassword)) {
                            Scanner newLoginInfo = new Scanner(System.in);
                            System.out.print("Enter new Username: ");
                            String newUsername = newLoginInfo.nextLine();
                            System.out.print("Enter new Password: ");
                            String newPassword = newLoginInfo.nextLine();
                            tempWriter.write(newUsername + "\n");
                            tempWriter.write(newPassword + "\n");

                            found = true;
                        } else {
                            tempWriter.write(line + "\n");
                            tempWriter.write(passwordLine + "\n");
                        }
                    } else {
                        tempWriter.write(line + "\n");
                    }
                } else {
                    tempWriter.write(line + "\n");
                }
            }

            scanner.close();
            tempWriter.close();

            if (found) {
                file.delete();
                tempFile.renameTo(file);
                return "data updated successfully.";
            } else {
                tempFile.delete();
                return "Old username and password not found. No changes made.";
            }

        } catch (IOException e) {
           return "An error occurred.";
        }
    }

    //search in file
    public static String searchInFile(String name, String searchString) {
        try {
            File file = new File(name + ".txt");
            Scanner scanner = new Scanner(file);

            boolean found = false;
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();

                if (line.contains(searchString)) {
                    found = true;
                    String S = "Found at line " + lineNumber + ": " + line;
                    return S;
                }
            }

            scanner.close();

            if (!found) {
                return "Search string not found in the file.";
            }

        } catch (FileNotFoundException e) {
            return "An error occurred.";
        }
        return "1";
    }
    //delete a line in the file
    public static String deleteSpecificLine(String name, String lineToDelete) {
        try {
            File myObj = new File(name + ".txt");

            File tempFile = new File(name + "_temp.txt");
            FileWriter tempWriter = new FileWriter(tempFile);

            boolean found = false;

            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String currentLine = myReader.nextLine();
                    if (currentLine.contains(lineToDelete)) {
                        found = true;
                        if (myReader.hasNextLine()) {
                            myReader.nextLine();
                        }
                    } else {
                        tempWriter.write(currentLine + System.getProperty("line.separator"));
                    }
                }
            }

            tempWriter.close();

            tempFile.renameTo(new File(name + ".txt"));

            if (found) {
                return "Specific line and the next line deleted successfully.";
            } else {
                return "Specified content not found in the file. No changes made.";
            }

        } catch (IOException e) {
            return "An error occurred.";
        }
    }
    //check if data exists in the file
    private static boolean dataExists(String name, String infoToCheck) {
        try {
            File file = new File(name + ".txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals(infoToCheck)) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while checking data existence.");
        }
        return false;
    }
    //insert data at a specific line
    //overloading

    public static String appendFile(String name, int lineNumber, String dataToInsert) {
        try {
            File myObj = new File(name + ".txt");

            File tempFile = new File(name + "_temp.txt");
            FileWriter tempWriter = new FileWriter(tempFile);

            boolean inserted = false;

            try (Scanner myReader = new Scanner(myObj)) {
                int currentLineNumber = 1;
                while (myReader.hasNextLine()) {
                    String currentLine = myReader.nextLine();
                    if (currentLineNumber == lineNumber) {
                        tempWriter.write(dataToInsert + System.getProperty("line.separator"));
                        inserted = true;
                    }

                    tempWriter.write(currentLine + System.getProperty("line.separator"));
                    currentLineNumber++;
                }

                if (lineNumber > currentLineNumber && !inserted) {
                    tempWriter.write(dataToInsert + System.getProperty("line.separator"));
                }
            }

            tempWriter.close();

            tempFile.renameTo(new File(name + ".txt"));

            if (inserted) {
                String S = "Data inserted at line " + lineNumber + " successfully.";
                return S;
            } else {
                return "Specified line number is out of range. No changes made.";
            }

        } catch (IOException e) {
            return "An error occurred.";
        }
    }
}