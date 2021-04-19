package Users;

import DatabaseActions.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class User{
    private int userId;
    private final String userName;
    private final String userSurname;
    private final String userBirthday;
    private final String userType;
    //Constructor
    public User(UserBuilder<?> builder){
        this.userId = builder.userId;
        this.userName = builder.userName==null?"":builder.userName;
        this.userSurname = builder.userSurname==null?"":builder.userSurname;
        this.userBirthday = builder.userBirthday==null?"":builder.userBirthday;
        this.userType = builder.userType==null?"":builder.userType;
    }
    //Getters
    public int getUserId() {
        return this.userId;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getUserSurname(){
        return this.userSurname;
    }
    public String getUserBirthday(){
        return this.userBirthday;
    }
    public String getUserType(){
        return userType;
    }
    //Methods for general userType operations
    //Create & delete
    public static void addNewUser(User userToCreate){
        Database.getInstance().insertNewUser(userToCreate);
    }
    public static User createUserFromInput(Scanner sc){
        UserBuilder userBuilder = new UserBuilder();
        //vlozeni uzivatele
        System.out.println("Zadej krestni jmeno - bez mezer ");
        userBuilder.withName(sc.nextLine());

        System.out.println("Zadej prijmeni - pocita se jmeno bez mezer");
        userBuilder.withSurname(sc.nextLine());

        System.out.println("Zadej rok narozeni <1920;2002>");
        int birthYear = 0;
        boolean leapYear = false;
        do {
            birthYear = sc.nextInt();sc.nextLine();

        }while (birthYear < 1920 || birthYear > 2002);

        if((birthYear % 400 == 0) || ((birthYear % 4 == 0)&& (birthYear % 100 != 0))) {
            leapYear = true;
        }

        System.out.println("Zadej mesic narozeni <1;12>");
        int birthMonth= 0;
        do {
            birthMonth = sc.nextInt();sc.nextLine();

        }while (birthMonth < 1 || birthMonth > 12);



        boolean longMonth = false;
        switch (birthMonth){
            case 1:case 3:case 5:case 7:case 8:case 10:case 12: longMonth = true;
        }

        int birthDay = 0;
        System.out.println("Zadej den narozeni ");
        do {
            birthDay = sc.nextInt();sc.nextLine();

        }while (birthDay < 1 || (longMonth && birthDay > 31) || (!longMonth && birthDay > 30) || (birthMonth == 2 && !leapYear && birthDay >28) || (birthMonth == 2 && leapYear && birthDay >29));

        userBuilder.withBirthday(birthYear+"-"+birthMonth+"-"+birthDay);

        System.out.println("Zadej typ uzivatele ve stringovem tvaru 'student' || 'ucitel' ");
        userBuilder.withUserType( sc.nextLine().equals("ucitel")?"teacher":"student");//prejmenovani do anglictiny

        if (userBuilder.getUserType().equals("teacher")){
            return userBuilder.buildTeacher();
        }
        else {

            return userBuilder.buildStudent();

        }
    }
    public static void deleteUser(int userId){
        Database.getInstance().deleteUser(userId);
    }
    //User utilities
    public static boolean exists(int userId){
        return Database.getInstance().checkIfUserExists(userId);
    }
    public static int getUserCount(){
        return Database.getInstance().getUserCount();
    }
    public static void printUserInfo(int userId){
        User user = Database.getInstance().fetchUser(userId,
                UserDataTypes.userName,
                UserDataTypes.userSurname,
                UserDataTypes.userBirthday,
                UserDataTypes.userType);

        if (user.userType.equals("teacher")){
            Teacher teacher = (Teacher) user;
            System.out.println(teacher.getInfo(true));
        }
        else{
            Student student = (Student) user;
            System.out.println(student.getInfo(true));
        }
    }
    //Sorts
    public static List<User> getSortedUsers(){
        List<User> users = new ArrayList<>();
        List<Integer> userIds = Database.getInstance().getUsersAlphabeticallySorted();
        for (int userId:userIds){
            users.add(Database.getInstance().fetchUser(userId,
                    UserDataTypes.userId,
                    UserDataTypes.userName,
                    UserDataTypes.userSurname,
                    UserDataTypes.userBirthday,
                    UserDataTypes.userType));
        }
        return  users;
    }

    public static void printSortedUsers(){
        List<User> users = getSortedUsers();

        System.out.println("-------UCITELE------");
        for (User user:users){
            if (user.getUserType().equals("teacher")){
                System.out.println( user.getInfo(true));
            }
        }
        System.out.println("-------STUDENTI------");
        for (User user:users){
            if (user.getUserType().equals("student")){
                System.out.println(user.getInfo(true));
            }
        }
    }
    //Abstract classes thats needs to be implemented differently
    protected abstract Object getInfo();
    protected abstract Object getInfo(boolean restricted);
    public abstract double getFinances();
    //UserBUILDER CLASS for easier creation
    public static class UserBuilder <T extends UserBuilder<T>>{

        private int userId;
        private String userName;
        private String userSurname;
        private String userBirthday;
        private String userType; //teacher-student

        public String getUserType() {
            return userType;
        }
        //vlastni builder pattern
        public UserBuilder<T> withId(int passedId) {
            this.userId = passedId;
            return (T)this;
        }

        public UserBuilder<T> withName(String passedName) {
            this.userName = passedName;
            return (T)this;
        }

        public UserBuilder<T> withSurname(String passedSurname) {
            this.userSurname = passedSurname;
            return (T)this;
        }

        public UserBuilder<T> withBirthday(String passedBirthday) {
            this.userBirthday = passedBirthday;
            return (T)this;
        }

        public UserBuilder<T> withUserType(String passedUserType) {
            this.userType = passedUserType;
            return (T) this;
        }
        public Student buildStudent() {
            return new Student(this);
        }
        public Teacher buildTeacher(){
            return new Teacher(this);
        }
    }
}
