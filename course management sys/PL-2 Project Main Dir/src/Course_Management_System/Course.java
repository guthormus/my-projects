package Course_Management_System;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class Course {
    private String courseName;
    private String room;
    private String branch;
    private double price;
    private String startDate;
    private String endDate;
    private int days;
    private final FileHandler CF = new FileHandler("CourseVol/CoursePage.txt");

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseName() {
        return courseName;
    }


    public void setRoom(String room) {
        this.room = room;
    }
    public String getRoom() {
        return room;
    }


    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getBranch() {
        return branch;
    }


    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getStartDate() {
        return startDate;
    }


    public void setDaysOfCourse(int days) {
        this.days = days;
    }
    public long getDaysOfCourse(String startDate,String endDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startD = dateFormat.parse(startDate);
        Date endD = dateFormat.parse(endDate);
        long daysInMili = endD.getTime()-startD.getTime();
        return daysInMili / (24 * 60 * 60 * 1000);
    }


    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString(){
        String courses = CF.readFile();
        StringBuilder content = new StringBuilder();
        String courseDetails = "";
        String[] userData = courses.split("\n");
        String[] courseInfo;
        for(int i = 1 ; i < userData.length ; i++)
        {
            //content = content + userData[i]+"\n";
            courseInfo = userData[i].split(",");
            //System.out.println(courseInfo.length);
            try {
                content.append("\nCourse Name : ").append(courseInfo[0])
                        .append("\n").append("Course Room : ").append(courseInfo[1])
                        .append("\n").append("Course Branch : ").append(courseInfo[2])
                        .append("\n").append("Course Price : ").append(courseInfo[3]).append("\n")
                        .append("Course Start date : ").append(courseInfo[4]).append("\n").append("Course End date : ")
                        .append(courseInfo[5]).append("\n").append("Course Days : ").append(getDaysOfCourse(courseInfo[4], courseInfo[5]))
                        .append("\n").append("Course Instructor : ").append(courseInfo[6]).append("\n").append("---------------------------------------------------").append("\n");
            } catch (ParseException ex) {
                return "";
            }

        }
        return content.toString();
    }
}
