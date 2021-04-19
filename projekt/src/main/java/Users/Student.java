package Users;

import DatabaseActions.Database;

import java.util.List;
import java.util.Scanner;

public class Student extends User {
    private double studyAverage = 0;
    private double finance = 0;
    //Constructor
    public Student(UserBuilder builder) {
        super(builder);
    }
    //Student utilities
    public void calculateStudyAverage(List<Double> marksList){
        if (marksList.size() != 0){
            for (double actualMark:marksList) {
                this.studyAverage += actualMark;
            }
            this.studyAverage /= marksList.size();
        }
    }
    public double getStudyAverage() {
        if (this.studyAverage == 0){
            calculateStudyAverage(Database.getInstance().getMarks(this.getUserId()));
        }
        return this.studyAverage;
    }
    //Static for general Student actions
    public static boolean isStudent(int userId){
        return Database.getInstance().isUserType(userId,"student");
    }
    public static void addMark(int userId,Scanner sc){
        double tempMark;
        System.out.println("Zadejte znamku <1;5>");

        do {
            tempMark = sc.nextInt();sc.nextLine();
        }while (tempMark > 5 || tempMark < 1);

        Database.getInstance().insertNewMark(userId,tempMark);
    }
    public static void getAllTeachers(int studentId){
        List<Integer> teacherIds = Database.getInstance().getTeacherWithStudentsIds(studentId);

        for (int teacherId:teacherIds){
            System.out.println(
                    ((Teacher)Database.getInstance().fetchUser(teacherId,
                            UserDataTypes.userId,
                            UserDataTypes.userName,
                            UserDataTypes.userSurname,
                            UserDataTypes.userType
                    )).getInfo()
            );
        }
    }
    //Modified abstract methods
    @Override
    public double getFinances() {
        if (getStudyAverage() < 1.5 && getStudyAverage() >= 1 ){
            this.finance= 1400;
        }
        return this.finance;
    }
    @Override
    protected String getInfo() {
        return getInfo(false);
    }
    @Override
    protected String getInfo(boolean restricted) {
        String info = "Student id = " + getUserId() +
                "," + getUserName() +
                " " + getUserSurname();
        if (restricted){
            info +=  ", datum narozeni= " + getUserBirthday() +
                    ", studijni prumer= " + getStudyAverage() +
                    ", stipendium= "+ getFinances()+
                    '}';
        }
        return info;
    }
}