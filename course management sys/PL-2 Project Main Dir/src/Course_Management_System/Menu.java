package Course_Management_System;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Menu {
    public static void Main_Menu() throws IOException, ParseException {
        System.out.println("    --WELCOME TO YOUR COURSE MANAGEMENT SYSTEM!--   \n");
        System.out.println("1. Login.");
        System.out.println("2. Show Courses Available and its details.");
        System.out.println("0. Exit.\n");
        System.out.print("Enter your choice: ");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice){
            case 1:
                System.out.println("\n1. Login as a Student.");
                System.out.println("2. Login as an Instructor.");
                System.out.println("3. Login as an Admin.");
                System.out.println("0. Return to the Main Menu.\n");
                System.out.print("Enter your choice: ");
                choice = in.nextInt();
                switch (choice){
                    case 1:
                        Login_Menu(1);
                        break;
                    case 2:
                        Login_Menu(2);
                        break;
                    case 3:
                        Login_Menu(3);
                        break;
                    case 0:
                        Main_Menu();
                        break;
                    default:
                        System.out.println("Wrong Choice !\n");
                        Main_Menu();
                }
                break;
            case 2:
                Course c = new Course();
                System.out.println(c);
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Wrong Choice !\n");
                Main_Menu();
        }
    }

    private static void Login_Menu(int type) throws IOException, ParseException {
        Scanner inn = new Scanner(System.in);
        System.out.print("\nEnter your name: ");
        String name = inn.nextLine().trim();
        while (name.isEmpty())
        {
            System.out.println("Invalid name format, Try again.\n");
            System.out.print("Enter your name: ");
            name = inn.nextLine().trim();
        }
        System.out.print("Enter your password: ");

        String password = inn.nextLine().trim();
        while (password.isEmpty())
        {
            System.out.print("Invalid password format, Try again.\n");
            System.out.print("Enter your password: ");
            password = inn.nextLine().trim();
        }

        if(type == 1) {
            Student st = new Student(name, password);

            if(st.login(type, st)){
                System.out.println("\nLogged in successfully !\n");
                Student_Menu(st);
            }else{
                System.out.println("Wrong Name or Password ! Try again.\n");
                Main_Menu();
            }
        } else if (type == 2) {

            Instructor inst = new Instructor(name, password);

            if(inst.login(type, inst)) {

                System.out.println("\nLogged in successfully !\n");
                Instructor_Menu(inst);

            }else{
                System.out.println("Wrong Name or Password ! Try again.\n");
                Main_Menu();
            }
        } else if (type == 3) {
            Admin ad = new Admin(name, password);
            if(ad.login(type, ad)){
                System.out.println("\nLogged in successfully !\n");
                Admin_Menu(ad);
            }else{
                System.out.println("Wrong Name or Password ! Try again.\n");
                Main_Menu();
            }
        }else {
            System.out.println("Invalid Choice !\n");
            Main_Menu();
        }
    }

    private static void Student_Menu(Student st) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        Scanner input4 = new Scanner(System.in);
        Scanner input5 = new Scanner(System.in);
        System.out.println("\n1. Show all courses you are enrolled in.");
        System.out.println("2. Show grade of a specific course.");
        System.out.println("3. Change your password.");
        System.out.println("4. Add a feedback to a specific course.");
        System.out.println("5. Enroll in a course.");
        System.out.println("0. Logout.\n");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();

        if(choice == 2){
            System.out.print("\nEnter the course name: ");
            String cName = input2.next().trim();
            if(st.showGrade(cName, st) != null) {
                System.out.println("Your grade in \"" + cName + "\" is: " + st.showGrade(cName, st) + "\n");
                Student_Menu(st);
            }
            else {
                System.out.println("You are not enrolled in this course or Invalid course name.\n");
                Student_Menu(st);
            }
        } else if (choice == 1) {
            String[] stInfo = st.showCourses(st).split("\n");
            String stCourses = String.join(",", stInfo);
            if(!stCourses.isEmpty()) {
                System.out.println("\nYour courses are : " + stCourses + "\n");
                Student_Menu(st);
            }else{
                System.out.println("\nYou are not enrolled in any courses yet !\n");
                Student_Menu(st);
            }
            Student_Menu(st);

        } else if (choice == 3) {
            System.out.print("\nEnter your new password: ");
            String password = input2.nextLine().trim();
            while (password.isEmpty())
            {
                System.out.print("Invalid password format, Try again.\n");
                System.out.print("Enter your new password: ");
                password = input2.nextLine().trim();
            }
            if(st.updateStudentPassword(password, st)) {
                System.out.println("\nYour password changed successfully.\n");
                Student_Menu(st);
            }
            else {
                System.out.println("\nFailed to change your password, Try again.\n");
                Student_Menu(st);
            }
        } else if (choice == 4) {
            System.out.print("\nEnter the course name that you want to give a feedback about: ");
            String cName = input3.next().trim();
            System.out.print("Enter your feedback: ");
            String feedback = input4.nextLine().trim();
            if(st.feedback(st, cName, feedback)){
                System.out.println("Feedback added successfully.\n");
                Student_Menu(st);
            }else{
                System.out.println("Failed to add the feedback or you are not enrolled in this course, Try again.\n");
                Student_Menu(st);
            }
        } else if (choice == 0) {
            Main_Menu();
        } else if (choice == 5) {
            String[] stInfo = st.showCourses().split("\n");
            String stCourses = String.join(",", stInfo);
            System.out.println("The Courses available to enroll into: " + stCourses + "\n");
            System.out.print("\nEnter the course name you want to enroll into: ");
            String cName = input5.next().trim();
            System.out.println("\n" + st.addCourse(st, cName));
            Student_Menu(st);
        }else {
            System.out.println("Wrong choice, Try again.\n");
            Student_Menu(st);
        }
    }

    private static void Instructor_Menu(Instructor inst) throws IOException, ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Add grades to a specific student.");
        System.out.println("0. Logout.\n");
        System.out.print("Enter your choice: ");
        int choice = in.nextInt();

        if(choice == 1){
            System.out.print("\nEnter Student ID: ");
            String id = in.next();
            System.out.print("Enter the course name you want to add the grade to: ");
            String cName = in.next();
            System.out.print("Enter the grade: ");
            String grade = in.next();
            if(inst.addGrade(id, grade, cName)){
                System.out.println("\nGrade added successfully.\n");
                Instructor_Menu(inst);
            }else {
                System.out.println("You do not teach this course or invalid course name ! Try again.\n");
                Instructor_Menu(inst);
            }
        }else if (choice == 0) {
            Main_Menu();
        }else {
            System.out.println("Wrong Choice ! Try again.\n");
            Instructor_Menu(inst);
        }
    }

    private static void Admin_Menu(Admin ad) throws IOException, ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("\n1. Add a User.");
        System.out.println("2. Add a Course.");
        System.out.println("3. Update a User.");
        System.out.println("4. Update a Course.");
        System.out.println("5. Delete a User.");
        System.out.println("6. Delete a Course.");
        System.out.println("7. Report of courses near to start.");
        System.out.println("8. Report of courses near to end.");
        System.out.println("0. Logout.\n");
        System.out.print("Enter your choice: ");
        int choice = in.nextInt();
        if(choice == 1){
            System.out.println("1. Add a Student.");
            System.out.println("2. Add an Instructor.");
            System.out.print("\nEnter your choice: ");
            int choice2 = in.nextInt();
            if(choice2 == 1){
                Scanner nameIn = new Scanner(System.in);
                Scanner passIn = new Scanner(System.in);
                System.out.print("\nEnter student name: ");
                String name = nameIn.nextLine().trim();
                System.out.print("Enter student password: ");
                String pass = passIn.nextLine().trim();
                if(ad.addUser(name, pass)){
                    System.out.println("Student added successfully.\n");
                    Admin_Menu(ad);
                }else {
                    System.out.println("\nFailed to add student, Name may be already taken.");
                    Admin_Menu(ad);
                }
            } else if (choice2 == 2) {
                FileHandler F = new FileHandler("CourseVol/courses.txt");
                Scanner nameIn = new Scanner(System.in);
                Scanner passIn = new Scanner(System.in);
                Scanner courseIn = new Scanner(System.in);
                System.out.print("\nEnter instructor name: ");
                String name = nameIn.nextLine().trim();
                System.out.print("Enter instructor password: ");
                String pass = passIn.nextLine().trim();
                System.out.print("Enter instructor course: ");
                String course = courseIn.nextLine().trim();
                while(!Admin.isCourseAlreadyExists(F, course)){
                    System.out.print("Course name doesn't exist, Try again: ");
                    course = courseIn.nextLine().trim();
                }
                if(ad.addUser(name, pass, course)){
                    System.out.println("Instructor added successfully.\n");
                    Admin_Menu(ad);
                }else {
                    System.out.println("\nFailed to add instructor, Name may be already taken.");
                    Admin_Menu(ad);
                }
            }else {
                System.out.println("Wrong Choice, Try again.\n");
                Admin_Menu(ad);
            }

        } else if (choice == 2) {
            Scanner s = new Scanner(System.in);
            Scanner s2 = new Scanner(System.in);
            Scanner s3 = new Scanner(System.in);
            Scanner s4 = new Scanner(System.in);
            Scanner s5 = new Scanner(System.in);
            Scanner p = new Scanner(System.in);
            System.out.print("\nEnter course name: ");
            String cName = s.next().trim();
            System.out.print("Enter course room: ");
            String room = s2.next().trim();
            System.out.print("Enter course branch: ");
            String branch = s3.nextLine().trim();
            System.out.print("Enter course price: ");
            float price = p.nextFloat();
            System.out.print("Enter course start date in this format(yyyy-mm-dd): ");
            String startDate = s4.next().trim();
            System.out.print("Enter course end date in this format(yyyy-mm-dd): ");
            String endDate = s5.next().trim();
            if(ad.addCourses(cName,room,branch,price,startDate,endDate)){
                System.out.println("Course has been added successfully.\n");
                Admin_Menu(ad);
            }else {
                System.out.println("Failed to add this course to the system.\n");
                Admin_Menu(ad);
            }
        } else if (choice == 3) {
            Scanner passIn = new Scanner(System.in);
            Scanner choiceIn = new Scanner(System.in);
            System.out.println("1. Change a specific student password.");
            System.out.println("2. Change a specific instructor password.");
            System.out.print("\nEnter your choice: ");
            int choice2 = choiceIn.nextInt();
            if(choice2 == 1){
                System.out.print("\nEnter student new password: ");
                String pass = passIn.next().trim();
                System.out.println("\nPassword changed successfully.\n");
                Admin_Menu(ad);
            } else if (choice2 == 2) {
                System.out.print("\nEnter instructor new password: ");
                String pass = passIn.next().trim();
                System.out.println("\nPassword changed successfully.\n");
                Admin_Menu(ad);
            }else {
                System.out.println("Wrong Choice, Try again.\n");
                Admin_Menu(ad);
            }
        } else if (choice == 4) {
            Scanner s2 = new Scanner(System.in);
            Scanner s3 = new Scanner(System.in);
            Scanner s4 = new Scanner(System.in);
            Scanner s5 = new Scanner(System.in);
            Scanner p = new Scanner(System.in);
            Scanner cName = new Scanner(System.in);
            FileHandler F = new FileHandler("CourseVol/courses.txt");
            System.out.print("\nEnter course name you want to update: ");
            String courseName = cName.next().trim();
            if(Admin.isCourseAlreadyExists(F, courseName)){
                System.out.print("Enter course room: ");
                String room = s2.next().trim();
                System.out.print("Enter course branch: ");
                String branch = s3.nextLine().trim();
                System.out.print("Enter course price: ");
                float price = p.nextFloat();
                System.out.print("Enter course start date in this format(yyyy-mm-dd): ");
                String startDate = s4.next().trim();
                System.out.print("Enter course end date in this format(yyyy-mm-dd): ");
                String endDate = s5.next().trim();
                if(ad.updateCourseDetails(courseName, room, branch, price, startDate, endDate)){
                    System.out.println("Course info Updated successfully.\n");
                    Admin_Menu(ad);
                }else {
                    System.out.println("Failed to update course info!\n");
                    Admin_Menu(ad);
                }
            }else {
                System.out.println("Course doesn't exist in the system.\n");
                Admin_Menu(ad);
            }
        } else if (choice == 5) {
            Scanner choiceIn = new Scanner(System.in);
            System.out.println("\n1. Delete a student from the system.");
            System.out.println("2. Delete an instructor from the system.\n");
            System.out.print("\nEnter your choice: ");
            int choice2 = choiceIn.nextInt();
            if(choice2 == 1){
                System.out.print("\nEnter student ID that you want to delete: ");
                int id = choiceIn.nextInt();
                if(ad.deleteStudentInfo(id)){
                    System.out.println("Student deleted successfully.\n");
                    Admin_Menu(ad);
                }else {
                    System.out.println("Student ID doesn't exist, Try again.\n");
                    Admin_Menu(ad);
                }
            } else if (choice2 == 2) {
                System.out.print("\nEnter instructor ID that you want to delete: ");
                int id = choiceIn.nextInt();
                if(ad.deleteInstructorInfo(id)){
                    System.out.println("Instructor deleted successfully.\n");
                    Admin_Menu(ad);
                }else {
                    System.out.println("Instructor ID doesn't exist, Try again.\n");
                    Admin_Menu(ad);
                }
            }else{
                System.out.println("\nWrong choice, Try again.\n");
            }

        } else if (choice == 6) {
            Scanner courseIn = new Scanner(System.in);
            System.out.print("Enter course name you want to delete: ");
            String cName = courseIn.next().trim();
            if(ad.deleteCourse(0,cName)){
                System.out.println("\nCourse has been deleted.\n");
                Admin_Menu(ad);
            }else {
                System.out.println("\nCourse doesn't exist.");
                Admin_Menu(ad);
            }
        } else if (choice == 0) {
            Main_Menu();
        } else if (choice == 7) {
            Report rt = new Report();
            System.out.println("\nCourses near to start -> " + rt.displayNearStartCourses());
            System.out.println();
            Admin_Menu(ad);
        } else if (choice == 8) {
            Report rt = new Report();
            System.out.println("\nCourses near to end -> " + rt.displayNearEndCourses());
            System.out.println();
            Admin_Menu(ad);
        } else {
            System.out.println("Wrong choice, Try again.\n");
            Admin_Menu(ad);
        }
    }
}
