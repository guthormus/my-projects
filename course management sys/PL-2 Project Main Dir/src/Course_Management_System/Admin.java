package Course_Management_System;

import java.io.*;
import java.util.*;
import java.io.File;



public class Admin extends User {
    private int id;
    public Admin(String name, String password) {
        super(name, password);
    }

    protected void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return 0;
    }

    public boolean addUser(String name , String pass) throws IOException {
        FileHandler F = new FileHandler("StudentVol/students.txt");
        String lastLine = F.readLastLine();
        String[] user = lastLine.split(",");
        String userId = user[0].trim();
        int userID;
        if(F.readFile().isEmpty()){
            userID = 1;
        }else{
            userID = Integer.parseInt(userId);
            userID++;
        }
        if(isNameAlreadyExists(F, name))
            return false;
        return F.append(userID + "," + name + "," + pass + "\n");
    }
    public boolean addUser(String name , String pass , String Course) throws IOException {
        FileHandler F = new FileHandler("InstructorVol/instructors.txt");
        FileHandler F2 = new FileHandler("CourseVol/CoursePage.txt");
        String cLine = F2.readLineByName(0, Course);
        StringBuilder uLine = new StringBuilder(cLine + "," + name);
        String cLine2 = new String(uLine);
        String lastLine = F.readLastLine();
        String[] user = lastLine.split(",");
        String userId = user[0].trim();
        int userID;
        if(F.readFile().isEmpty()){
            userID = 1;
        }else{
            userID = Integer.parseInt(userId);
            userID++;
        }
        if(isNameAlreadyExists(F, name))
            return false;
        F2.update(cLine, cLine2);
        return F.append(userID + "," + name + "," + pass + "," + Course + "\n");
    }

    private boolean isNameAlreadyExists(FileHandler F,String name) throws IOException {
        Scanner scanner = new Scanner(new File(F.path));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length > 1 && parts[1].trim().equals(name.trim())) {
                scanner.close();
                return true;  // Name already exists
            }
        }
        scanner.close();
        return false;  // Name does not exist
    }

    private boolean updateUserPass(String VolName, String fileName, int id, String newPass) throws IOException {
        FileHandler F = new FileHandler(VolName + "/" + fileName);
        String line = F.readLine(id);
        String[] userData = line.split(",");
        userData[2] = newPass;
        String line2 = String.join(",", userData);
        return F.update(line, line2);
    }
    public boolean updateStudentPass(int id, String newPass) throws IOException {
        return updateUserPass("StudentVol", "students.txt", id , newPass);
    }
    public boolean updateInstructorPass(int id, String newPass) throws IOException {
        return updateUserPass("InstructorVol", "instructors.txt", id , newPass);
    }

    // General method to delete user information from a file
    public boolean deleteUserInfo( int id, String Path ) throws IOException {
        FileHandler F = new FileHandler(Path);
        return F.delete(F.readLine(id));
    }
    public boolean deleteStudentInfo(int id) throws IOException {
        return deleteUserInfo(id,"StudentVol/students.txt");
    }
    public boolean deleteInstructorInfo(int id) throws IOException {
        return deleteUserInfo(id,"InstructorVol/instructors.txt");
    }


    protected static boolean isCourseAlreadyExists(FileHandler F,String name) throws IOException {
        Scanner scanner = new Scanner(new File(F.path));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().contains(name.trim())) {
                scanner.close();
                return true;  // Course already exists
            }
        }
        scanner.close();
        return false;  // Course does not exist
    }

    public boolean deleteCourse(int Index, String courseName) throws IOException {
        FileHandler coursePage = new FileHandler("CourseVol/CoursePage.txt");
        FileHandler courses = new FileHandler("CourseVol/courses.txt");
        return coursePage.delete(coursePage.readLineByName(Index, courseName)) &&
                courses.delete(courses.readLineByName(Index, courseName));
    }

    public boolean updateCourseDetails(String courseName, String room, String branch, double price,
                                        String startDate, String endDate) {
        FileHandler fileHandler = new FileHandler();
        return fileHandler.updateCourseDetails(courseName, room, branch, price,
                startDate, endDate);
    }

    public boolean addCourses(String courseName, String room, String branch, double price,
                           String startDate, String endDate) {

        // Save course details to the CoursesVol directory
        FileHandler courseFileHandler = new FileHandler("CourseVol/CoursePage.txt");
        FileHandler coursesListFileHandler = new FileHandler("CourseVol/courses.txt");
        courseFileHandler.createFile();
        return courseFileHandler.append(String.format("%s,%s,%s,%.2f,%s,%s",courseName, room, branch,
                                                                    price, startDate, endDate)) &&
                coursesListFileHandler.append(courseName);
    }
}
