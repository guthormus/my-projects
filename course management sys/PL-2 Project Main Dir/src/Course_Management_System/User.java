package Course_Management_System;

import java.io.*;

public abstract class User {

    private String name;
    private String password;

    public User(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    // We will HAVE TO implement getID() and setID() in every class that inherits User Class.

    protected abstract void setID(int id); // This will be implemented.

    public abstract int getID(); // This will be implemented.


    // will be used when User want to update his information.
    public void setName(String name){
        this.name = name;
    }


    // will be used when User want to update his information.
    protected void setPassword(String password){
        this.password = password;
    }

    // We WON'T HAVE TO make getName() in any other class that inherits User Class
    // because I used POLYMORPHISM.
    public String getName(User u){ // Polymorphism
        return u.name;
    }

    public boolean login(int type, User u) // Polymorphism
    {
        return switch (type) {
            case 1 ->
                // login as a Student.
                    fileConnForLogin("StudentVol", "students.txt", u);
            case 2 ->
                // login as an Instructor.
                    fileConnForLogin("InstructorVol", "instructors.txt", u);
            case 3 ->
                // login as an Admin.
                    fileConnForLogin("AdminVol", "admins.txt", u);
            default -> false;
        };
    }

    // A User class method that handles all the connections for the (login) method.
    private boolean fileConnForLogin(String VolName, String fileName, User u) throws NumberFormatException
    {
        try(BufferedReader br = new BufferedReader(new FileReader(VolName+"/"+fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                int id_Val = Integer.parseInt(userData[0]);
                if (userData.length >= 3 && userData[1].equals(u.name) && userData[2].equals(u.password)) {
                    u.setID(id_Val);
                    return true; // logging in succeeded.
                }
            }
        }catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage()); // file is NOT found.
            return false;
        }
        return false; // logging in failed.
    }
}
