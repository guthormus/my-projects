package Course_Management_System;


import java.io.*;
import java.util.*;

public class Instructor extends User {
    private static int id;

    Instructor(String name, String password) {
        super(name, password);
        id++;
    }

    protected void setID(int id) {
        Instructor.id = id;
    }

    public int getID() {
        return id;
    }

    public String getInstructorCourseName() {
        try (BufferedReader br = new BufferedReader(new FileReader("InstructorVol/instructors.txt"))) {
            String courseName = "";
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                int id_val = Integer.parseInt(userData[0]);
                if (id_val == this.getID())
                    courseName = userData[3];
            }
            return courseName;

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public boolean addGrade(String idVal, String grade, String courseNameToCheck) {
        FileHandler fileHandler = new FileHandler("StudentVol/students.txt");
        boolean isInstructorAuthorized = false;
        boolean isStudentRegistered = false;

        // Check if the instructor has the authority for this course
        String instructorCourseName = this.getInstructorCourseName();
        if (instructorCourseName != null && instructorCourseName.equals(courseNameToCheck)) {
            isInstructorAuthorized = true;
        }

        if (isInstructorAuthorized) {
            // Check if the student is registered for the course
            String studentsFileContent = fileHandler.readFile();
            String[] studentLines = studentsFileContent.split("\n");

            for (String studentInfo : studentLines) {
                String[] studentData = studentInfo.split(",");

                if (studentData[0].equals(idVal)) {
                    // Assuming the course information for the student is in a specific field (you can modify accordingly)
                    // Check if the student is registered for the course
                    // For example, assuming the course name is in index 2 in the student's data
                    Arrays.sort(studentData);
                    if ((studentData.length > 3) && studentData[Arrays.binarySearch(studentData, courseNameToCheck)].equals(courseNameToCheck)) {
                        isStudentRegistered = true;

                        // Update the grade - Assuming the grade field is the next one after the course field
                        // If the course field is found, the next field will be the grade field to update
                        if(this.updateFieldInLine(idVal, courseNameToCheck, grade))
                            break;
                    }
                }
            }
        }

        return isStudentRegistered;
    }

    private boolean updateFieldInLine(String keyword, String courseName, String newData) {
        File file = new File("StudentVol/students.txt");
        if (!file.exists() || !file.isFile()) {
            System.err.println("File not found or not a valid file.");
            return false;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder fileContent = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(keyword)) {
                    int fieldToUpdate = -1; // Default value if not found
                    int index = line.indexOf(courseName);
                    if (index != -1) {
                        // Increment the index by the length of the field + 1 for the comma
                        fieldToUpdate = index + courseName.length() + 1;
                    }

                    if (fieldToUpdate != -1 && fieldToUpdate < line.length()) {
                        // Update the field if found within the line's length
                        line = line.substring(0, fieldToUpdate) + newData + line.substring(fieldToUpdate + 2);
                    }
                }
                fileContent.append(line).append("\n");
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(fileContent.toString());
            bw.close();
            return true; // Updated successfully
        } catch (IOException e) {
            System.err.println("Error updating file: " + e.getMessage());
            return false;
        }
    }
}
