package projekt;

import projekt.DatabaseActions.Database;
import projekt.Users.Student;
import projekt.Users.Teacher;
import projekt.Users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {

    //insert new
    public void createNewUser(Scanner sc){
        User userToCreate = User.createUserFromInput(sc);
        int teacherId=0;
        if (!Database.getInstance().checkForDuplicates(userToCreate)){
            User.addNewUser(userToCreate);
            if (userToCreate instanceof Student){
                    System.out.println("Student musi mit ucitele - zadejte id ucitele");
                    printTeacherIds();
                    do {
                        teacherId = inputExistingUserId(sc);
                    }while (!Teacher.isTeacher(teacherId) || !User.exists(teacherId));

                    Database.getInstance().insertNewClass(teacherId,Database.getInstance().getLastUserId());
            }
        }else {
            System.out.println("Uzivatel jiz existuje");
        }
    }
    private int inputExistingUserId(Scanner sc){
        int valId = 0;
        System.out.println("Zadejte id");

        do{
            valId = sc.nextInt();sc.nextLine();
        }while (valId < 0 || !User.exists(valId));

        return valId;
    }
    public void addMark(Scanner sc){
        printStudentIds();
        int userId = inputExistingUserId(sc);
        if (Student.isStudent(userId)){
            Student.addMark(userId,sc);
        }
        else {
            System.out.println("UZIVATEL NENI STUDENT");
        }
    }
    public void addClass(Scanner sc){
        System.out.println("Zadejte id ucitele");
        printTeacherIds();

        int teacherId = inputExistingUserId(sc);

        if (Teacher.isTeacher(teacherId)){
            System.out.println("Zadejte id studenta");
            printStudentIds();
            int studentId = inputExistingUserId(sc);
            if (Student.isStudent(studentId)){
                if (!Database.getInstance().checkIfClassExists(teacherId,studentId)){
                    Database.getInstance().insertNewClass(teacherId,studentId);
                }
                else {
                    System.out.println("STUDENT JE JIZ REGISTROVAN POD TIMTO UCITELEM");
                }
            }
            else {
                System.out.println("UZIVATEL NENI STUDENT");
            }
        }
        else {
            System.out.println("UZIVATEL NENI UCITEL");
        }
    }
    //delete
    public void deleteUser(Scanner sc){
        User.deleteUser(inputExistingUserId(sc));
    }
    public void deleteClass(Scanner sc){
        System.out.println("Vyberte ucitele");
        printTeacherIds();
        int teacherId = inputExistingUserId(sc);

        if (Teacher.isTeacher(teacherId)){
            System.out.println("Vyberte studenta");
            printStudentIds();
            int studentId = inputExistingUserId(sc);
            if (Student.isStudent(studentId)){
                if (Database.getInstance().checkIfClassExists(teacherId,studentId)){
                    Database.getInstance().deleteClass(teacherId,studentId);
                }
                else {
                    System.out.println("STUDENT NENI REGISTROVAN POD TIMTO UCITELEM");
                }
            }
            else {
                System.out.println("UZIVATEL NENI STUDENT");
            }
        }
        else {
            System.out.println("UZIVATEL NENI UCITEL");
        }
    }
    //prints
    public void getUserInfo(Scanner sc){
         User.printUserInfo(inputExistingUserId(sc));
    }
    public void printTeachersSortedByStudentCount(){
        Teacher.printTeachersSortedByStudentCount();
    }
    public void printSortedStudents(Scanner sc){
        System.out.println("Zadejte id ucitele");
        int teacherId = inputExistingUserId(sc);
        if (Teacher.isTeacher(teacherId)){
            Teacher.printSortedStudents(teacherId);
        }else {
            System.out.println("OSOBA NENI UCITEL");
        }
    }
    public void printAlphabeticallySortedUsers(){
        User.printSortedUsers();
    }
    public void printFinances(){
        double totalFinaces=0;
        List<User> users = User.getSortedUsers();
        for (User user: users){
            totalFinaces += user.getFinances();
            //Can print students and Teachers separately
            /*if (user instanceof Student){
                System.out.println("Stipendium: "+user.getFinances());
            }
            else {
                System.out.println("Ucitelsky plat: "+user.getFinances());
            }*/
        }
        System.out.println("Kompletni finance na mesic jsou: "+totalFinaces);
    }
    public void printStudentsTeachers(Scanner sc){
        int userId = inputExistingUserId(sc);
        if (Student.isStudent(userId)){
            Student.getAllTeachers(userId);
        }
        else {
            System.out.println("UZIVATEL NENI STUDENT");
        }
    }
    public void printStudentIds(){
        System.out.println("Studenti maji tyto ID");
        List<Integer> studentIds = Database.getInstance().getStudentIds();
        for (int studentId:studentIds){
            System.out.print(studentId+" ");
        }
    }
    public void printTeacherIds(){
        System.out.println("Ucitele maji tyto ID");
        List<Integer> teacherIds = Database.getInstance().getTeacherIds();
        for (int teacherId:teacherIds){
            System.out.print(teacherId+" ");
        }
    }
    //file Operations
    public void connectWithFile(){

        BufferedReader reader;
        List<String> connection = new ArrayList();

        try {
            reader = new BufferedReader(new FileReader("db/login.txt"));
            String line = reader.readLine();

            while (line != null){
                connection.add(line.split("=",2)[1]);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Database.setDatabaseConnection(connection.get(0),connection.get(1),connection.get(2));
    }
    public void exportDatabase(){
        Database.dumpDatabase("backups");
    }
    public void importDatabase(){
        Database.restoreDatabase("backups");
    }
    public void importRecord(){
        BufferedReader reader;
        List<String> user = new ArrayList();

        try {
            reader = new BufferedReader(new FileReader("db/userRecord.txt"));
            String line = reader.readLine();

            while (line != null){
                user.add(line.split("=",2)[1]);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //username,surname,birthday,usertype-ifstudent teacherId
        //Database.setDatabaseConnection(user.get(0),connection.get(1),connection.get(2));
    }

}