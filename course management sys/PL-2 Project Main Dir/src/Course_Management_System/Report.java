package Course_Management_System;

import java.io.IOException;
import java.text.*;
import java.util.*;
public class Report extends Course{
    private final FileHandler CPF = new FileHandler("CourseVol/CoursePage.txt");
    private final FileHandler CF = new FileHandler("CourseVol/courses.txt");
    public Date getDate(int index,String courseName) throws IOException, ParseException
    {
        String line = CPF.readLineByName(0,courseName);
        String[] userData = line.split(",");
//       System.out.println(userData.length);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(userData[index]);
    }
    private boolean hasStarted(String courseName) throws IOException, ParseException {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis > getDate(4,courseName).getTime();
    }

    private boolean isNearStartDate(String courseName) throws IOException, ParseException {
        if (hasStarted(courseName)) {
            return false;
        }
        long millisecondsUntilStart = getDate(4,courseName).getTime() - System.currentTimeMillis();
        long daysUntilStart = millisecondsUntilStart / (24 * 60 * 60 * 1000);
        int daysThreshold = 7;
        return daysUntilStart <= daysThreshold;
    }

    public String displayNearStartCourses() throws IOException, ParseException {
        String courses = CF.readFile();
        String[] userData = courses.split("\n");
        StringBuilder C = new StringBuilder();
        for(int i = 1 ; i < userData.length ; i++)
        {
            if(isNearStartDate(userData[i]))
                C.append(userData[i]);
        }
        return C.toString();
    }

    private boolean hasEnded(String courseName) throws IOException, ParseException {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis > getDate(5,courseName).getTime();
    }

    private boolean isNearEndDate(String courseName) throws IOException, ParseException {
        if (hasEnded(courseName)) {
            return false;
        }
        long millisecondsUntilStart = getDate(5,courseName).getTime() - System.currentTimeMillis();
        long daysUntilStart = millisecondsUntilStart / (24 * 60 * 60 * 1000);
        int daysThreshold = 7;
        return daysUntilStart <= daysThreshold;
    }

    public String displayNearEndCourses() throws IOException, ParseException {
        String courses = CF.readFile();
        String[] userData = courses.split("\n");
        StringBuilder C = new StringBuilder();
        for(int i = 1 ; i < userData.length ; i++)
        {
            if(isNearEndDate(userData[i]))
                C.append(userData[i]);
        }
        return C.toString();
    }
}
