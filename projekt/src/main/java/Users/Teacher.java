package Users;

import DatabaseActions.Database;


import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Teacher extends User{
    private double finance = 0;
    public Teacher(UserBuilder builder) {
        super(builder);
    }
    public double getFinances(){
        double finances = 0;
        //fetch poctu studentu
        List<Student> studentsList = Database.getInstance().fetchStudents(this.getUserId());

        for (Student actualStudent:studentsList) {
            //finance za studenta
            finances += 875;
            //dostane za prospech studenta bonus?
            double stipendium = actualStudent.getStudyAverage();
            finances +=  (stipendium< 1.5 && stipendium >=1.0)? 2526 : 0;
        }
        return finances;
    }

    private double getSalary(){
        double deposit;

        this.finance = getFinances();

        //15% zaloha na dan z prijmu zavisle cinosti
        deposit = this.finance*(0.15);
        //uplatneni slevy poplatnika na dani-2320
        deposit -= 2320;
        //pro jednoduchost uplatnuje 1 dite-1267
        deposit -= 1267;
        //pokud je zaloha minus diky odecteni slevy poplatnika, zaloha-0
        if (deposit < 0 ){
            deposit = 0;
        }
        //zdravotni a socialni zamestnanec 4,5 a 6,5%
        //cista mzda = mzda-socialni a zdravotni-zaloha na dani
        this.finance = finance - finance*(0.11) - deposit;

        return this.finance;
    }
    public static void printTeachersSortedByStudentCount(){
        //ziskat list id podle vyskytu ucitelu
        List<Integer> teachers = Database.getInstance().getTeacherWithStudentsIds(true);//ucitele se studenty
        teachers.addAll(Database.getInstance().getTeacherWithoutStudentsIds());//ucitele bez studentu
        //fetch a vypis uzivatelu
        for (int teacherId : teachers){
            printUserInfo(teacherId);
        }



    }
    public static boolean isTeacher(int userId){
        return Database.getInstance().isUserType(userId,"teacher");
    }
    public static void printSortedStudents(int teacherId){
        SortedMap<Double, String> students = new TreeMap();
        List<Integer> studentsIds = Database.getInstance().getStudentIds(teacherId);

        for (int studentId:studentsIds){
            Student student = (Student)Database.getInstance()
                .fetchUser(
                    studentId,
                    UserDataTypes.userName,
                    UserDataTypes.userSurname,
                    UserDataTypes.userType
                );
            students.put(student.getStudyAverage(),student.getInfo());
        }
        for (String student: students.values()) {
            System.out.println(student);
        }

    }
    public String getInfo() {
        return getInfo(false);
    }
    public String getInfo(boolean restricted) {
        String info ="Ucitel " + getUserName() +" "+ getUserSurname();
        if (restricted){
            info +=  ", id = " + getUserId() +
                    ", datum narozeni = " + getUserBirthday() +
                    ", plat = " + getSalary() +",- Kc ";
        }
        return info;
    }
}
