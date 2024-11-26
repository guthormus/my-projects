package Course_Management_System;

import java.io.*;
import java.util.*;
public class FileHandler {
    protected String path;
    private FileWriter fw;
    private Scanner fr;

    public FileHandler() {
    }

    public FileHandler(String path) {
        this.path = path;
    }

    public void createFile() {
        try {
            File file = new File(path);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
    }

    public String readLine(int ID) throws IOException {
        String content;
        this.fr = new Scanner(new File(path));
        while(fr.hasNextLine()) {
            content = fr.nextLine();
            if(content.startsWith(Integer.toString(ID)))
                return content;
        }
        fr.close();
        return "Nothing";
    }

    public String readLineByName(int Index, String name) throws IOException {
        String content;
        this.fr = new Scanner(new File(path));
        while(fr.hasNextLine()) {
            content = fr.nextLine();
            String[] parts = content.split(",");
            if(parts[Index].trim().equals(name.trim())) {
                fr.close();
                return content;
            }
        }
        fr.close();
        return "";
    }


    public boolean delete(String s) {
        try {
            StringBuilder content = new StringBuilder();
            String temp;
            boolean flag = false;
            this.fr = new Scanner(new File(path));
            while(fr.hasNextLine()) {
                temp = fr.nextLine();
                if(!temp.equals(s)){
                    content.append(temp).append("\n");
                }else
                    flag = true;
            }
            fr.close();
            this.fw = new FileWriter(path, false);
            fw.write(content.toString());
            fw.close();
            return flag;
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean update(String line, String updatedRow) {
        try {
            StringBuilder content = new StringBuilder();
            String temp;
            this.fr = new Scanner(new File(path));
            while(fr.hasNextLine()) {
                temp = fr.nextLine();
                if(!temp.equals(line)){
                    content.append(temp).append("\n");
                }
                else {
                    content.append(updatedRow).append("\n");
                }
            }
            fr.close();
            this.fw = new FileWriter(path, false);
            fw.write(content.toString());
            fw.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String readLastLine() {
        String content = "";
        try {
            this.fr = new Scanner(new File(path));
            while(fr.hasNextLine()) {
                content = fr.nextLine();
            }
            fr.close();
            return content;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
        return content;
    }

    public boolean append(String line)  {
        try {
            this.fw = new FileWriter(path, true);
            fw.append(line).append("");
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
            return false ;
        }
        return true;
    }

    public String readFile(){
        StringBuilder content = new StringBuilder();
        try {
            this.fr = new Scanner(new File(path));
            while(fr.hasNextLine()) {
                content.append("\n").append(fr.nextLine());
            }
            fr.close();
        } catch (FileNotFoundException e) {
            //System.out.println("An error occurred." + e.getMessage());
            return "error";
        }
        return content.toString();
    }

    public boolean updateCourseDetails(String courseName, String room, String branch, double price,
                                       String startDate, String endDate) {
        try {
            // Read the existing content of the individual course file
            StringBuilder content = new StringBuilder();
            String temp = "";
            this.fr = new Scanner(new File("CourseVol/CoursePage.txt"));
            while (fr.hasNextLine()) {
                // Update the course details in the file
                content.append(String.format("%s,%s,%s,%.2f,%s,%s\n",courseName,
                        room, branch, price, startDate, endDate));
            }
            fr.close();

            // Write the updated content back to the individual course file
            this.fw = new FileWriter("CourseVol/CoursePage.txt", false);
            fw.write(content.toString());
            fw.close();

            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while updating the course details: " + e.getMessage());
            return false;
        }
    }
}
