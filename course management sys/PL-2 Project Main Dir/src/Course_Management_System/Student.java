package Course_Management_System;

import java.io.*;

public class Student extends User {
    private static int stdCounter = 0;
    private int stdID;
    private final FileHandler CM = new FileHandler("StudentVol/students.txt");
    public Student(String name, String password)
    {
        super(name, password);
        this.stdID = ++stdCounter;
    }

    @Override
    protected void setID(int id) {
        this.stdID = id;
    }

    @Override
    public int getID() {
        return stdID;
    }

    public String showGrade(String Course, User u) throws IOException{
        int i = 3 ;
        String line = CM.readLine(u.getID()) , CourseGrade = "";
        String[] userData = line.split(",");
        while(!(userData[i].equals(Course))) i+=2;
        return userData[i+1];
    }


    public boolean updateStudentPassword(String newInfo , User u)throws IOException{
        return updateStudentInfo(2,newInfo, u);
    }

    public boolean updateStudentInfo(int index ,String newInfo, User u)throws IOException{
        String line = CM.readLine(u.getID());
        String[] userData = line.split(",");
        userData[index] = newInfo;
        String line2 = String.join(",", userData);
        return CM.update(line, line2);
    }

    public String showCourses(User u)throws IOException{
        int i;
        StringBuilder Content = new StringBuilder();
        String line = CM.readLine(u.getID());
        String[] userData = line.split(",");
        for(i = 3 ; i < userData.length;i+=2) Content.append(userData[i]).append("\n");
        return Content.toString();
    }

    //Overloading
    public String showCourses() {
        FileHandler Courses = new FileHandler("CourseVol/courses.txt");
        return Courses.readFile();
    }

    public boolean feedback(User u, String subjectName, String feedback) throws IOException {
        FileHandler F = new FileHandler("StudentVol/feedbacks.txt");
        FileHandler F2 = new FileHandler("StudentVol/students.txt");
        if(Admin.isCourseAlreadyExists(F2, subjectName))
            return F.append(u.getID() + "," + u.getName(u) + "," + subjectName + "," + feedback + "\n");
        else
            return false;
    }

    public String addCourse(User u , String courseName)throws IOException
    {
        String line = CM.readLine(u.getID());
        FileHandler CF = new FileHandler("CourseVol/courses.txt");
        FileHandler CF2 = new FileHandler("StudentVol/students.txt");
        String line2 = CF.readFile();
        String[] userData = line.split(",");
        if(userData.length == 11){
            return "You can't add more courses.";
        }
        else{
            if(!line2.contains(courseName))
            {
                return "This isn't a valid course.";
            } else if (Admin.isCourseAlreadyExists(CF2, courseName)) {
                return "Course already added!";
            } else
            {
                CM.update(line,line+","+courseName+","+"-1");
                return "Added.";
            }
        }
    }
}
