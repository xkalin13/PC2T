package DatabaseActions;

import QueryBuilders.CreateBuilder;
import QueryBuilders.DeleteBuilder;
import QueryBuilders.InsertBuilder;
import QueryBuilders.SelectBuilder;
import Users.Student;
import Users.User;
import Users.UserDataTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database {
    // vlastni singleton
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static String DB_URL = "jdbc:sqlite:db/databaze.db";
    private static String DB_USER = "";
    private static String DB_PASS = "";


    private static Connection dbConnection;
    private static Database dbInstance;


    public Database() {
        if (dbConnection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    public static Database getInstance() {
        try {
            if (dbInstance == null || dbInstance.getDbConnection().isClosed()) {
                dbInstance = new Database();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbInstance;
    }

    public static void endConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setDatabaseConnection(String url,String user,String password){
        DB_URL = url;
        DB_USER = user;
        DB_PASS = password;
    }
    public static void dumpDatabase(String databaseName){
        try {
            Statement statement = Database.getInstance().getDbConnection().createStatement();
            statement.executeUpdate("BACKUP TO db/"+databaseName+".db");

        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public static void restoreDatabase(String databaseName){
        try {
            Statement statement = Database.getInstance().getDbConnection().createStatement();
            statement.executeUpdate("RESTORE FROM db/"+databaseName+".db");
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public boolean checkForDuplicates(User user){
        String statement = new SelectBuilder("users")
                .column("userId")
                .whereEquals("userName",user.getUserName())
                .whereEquals("userSurname",user.getUserSurname())
                .whereEquals("userBirthday",user.getUserBirthday())
                .build();

        try{
            ResultSet result = getResultFromQuery(statement);
            while (result.next()){
                if (result.getInt("userId") > 0){
                    return true;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkIfUserExists(int userId){
        String statement = new SelectBuilder("users").column("userId").whereEquals("userId",userId).build();

        try{
            ResultSet result = getResultFromQuery(statement);
            if (result.getString(1) == null ){
                return false;
            }
            else {
                return true;
            }

        }catch (Exception e){
            return false;
        }
    }
    public boolean checkIfClassExists(int teacherId,int studentId){
        String statement = new SelectBuilder("classes")
                .column("classId")
                .whereEquals("studentId",studentId)
                .whereEquals("teacherId",teacherId)
                .build();

        try{
            ResultSet result = getResultFromQuery(statement);
            while (result.next()){
                if (result.getInt("classId") > 0){
                    return true;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void executeQuery(String sqlQuery) {
        try {
            Statement statement = Database.getInstance().getDbConnection().createStatement();
            statement.execute(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ResultSet getResultFromQuery(String sqlQuery) {
        ResultSet result = null;
        try {
            Statement statement = Database.getInstance().getDbConnection().createStatement();
            result = statement.executeQuery(sqlQuery);
            //statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void createTables() {
        //Users
        String userTable = new CreateBuilder("users")
                .newColumn("userId")
                .withDataType("int")
                .primaryKey()
                .autoIncrement()
                .notNull()
                .endColumn()

                .newColumn("userName")
                .withDataType("string")
                .notNull()
                .endColumn()

                .newColumn("userSurname")
                .withDataType("string")
                .notNull()
                .endColumn()

                .newColumn("userBirthday")
                .withDataType("date")
                .notNull()
                .endColumn()

                .newColumn("userType")
                .withDataType("string")
                .notNull()
                .endColumn()

                .build();
        executeQuery(userTable);

        String classesTable = new CreateBuilder("classes")
                .newColumn("classId")
                .withDataType("int")
                .primaryKey()
                .autoIncrement()
                .notNull()
                .endColumn()

                .newColumn("teacherId")
                .withDataType("int")
                .notNull()
                .endColumn()

                .newColumn("studentId")
                .withDataType("int")
                .notNull()
                .endColumn()

                .build();
        executeQuery(classesTable);

        String marksTable = new CreateBuilder("marks")
                .newColumn("markId")
                .withDataType("int")
                .primaryKey()
                .autoIncrement()
                .notNull()
                .endColumn()

                .newColumn("studentId")
                .withDataType("int")
                .notNull()
                .endColumn()

                .newColumn("markValue")
                .withDataType("double")
                .notNull()
                .endColumn()

                .build();
        executeQuery(marksTable);
    }

    public void insertNewClass(int teacherId, int studentId) {
        String statement = new InsertBuilder("classes")
                .set("teacherId", teacherId)
                .set("studentId", studentId)
                .build();
        executeQuery(statement);
        System.out.println("-----TRIDA PRIDANA-----");
    }
    public void deleteClass(int teacherId, int studentId) {
        String statement = new DeleteBuilder("classes")
                .whereEquals("teacherId",teacherId)
                .whereEquals("studentId",studentId)
                .build();
        executeQuery(statement);
        System.out.println("-----TRIDA ODEBRANA-----");
    }
    public void insertNewMark(int userId, double markValue) {
        String statement = new InsertBuilder("marks")
                .set("studentId", userId)
                .set("markValue",markValue)
        .build();

        executeQuery(statement);
        System.out.println("-----ZNAMKA PRIDANA-----");
    }

    public void insertNewUser(User user) {
        String statement = new InsertBuilder("users")
                .set("userName",user.getUserName())
                .set("userSurname",user.getUserSurname())
                .set("userBirthday",user.getUserBirthday())
                .set("userType",user.getUserType())
        .build();

        executeQuery(statement);
        System.out.println("-----OSOBA PRIDANA-----");
    }

    public void deleteUser(int userId) {
        String  statement = new DeleteBuilder("users")
                .whereEquals("userId",userId)
        .build();
        executeQuery(statement);
        System.out.println("-----OSOBA ODSTRANENA-----");
    }

    public int getUserCount() {
        return getUserCount("", "users");
    }
    public boolean isUserType(int userId, String passedUserType){
        String selectStatement = new SelectBuilder("users")
                .column("userId")
                .whereEquals("userType",passedUserType)
                .build();

        try {
            ResultSet result = getResultFromQuery(selectStatement);
            while (result.next()){
                if ( result.getInt("userId") == userId){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getUserCount(String passedUserType, String tableName) {
        int count = 0;
        SelectBuilder selectStatement = new SelectBuilder(tableName).column("COUNT(*)");

        if (passedUserType != "") {
                selectStatement.whereEquals("userType",passedUserType);
        }

        try {
            count = getResultFromQuery(selectStatement.build()).getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public int getLastUserId(){
        int userId = 0;
        String selectStatement = new SelectBuilder("users")
                .column("MAX(`userId`)")
                .build();
        try {
            userId = getResultFromQuery(selectStatement).getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
    public void getAllUserRecords() {

        String statement = new SelectBuilder("users")
                .column("*")
        .build();

        try {
            ResultSet result = getResultFromQuery(statement);
            while (result.next()) {
                System.out.print("userID: "+result.getString("userId")+" ");
                System.out.print(", userName: "+result.getString("userName")+" ");
                System.out.print(", userSurname: "+result.getString("userSurname")+" ");
                System.out.print(", userBirthday: "+result.getString("userBirthday")+" ");
                System.out.print(", userType: "+result.getString("userType") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void getAllClassesRecords() {

        String statement = new SelectBuilder("classes")
                .column("*")
                .build();

        try {
            ResultSet result = getResultFromQuery(statement);
            while (result.next()) {
                System.out.print("ID: "+result.getInt("classId"));
                System.out.print(", teacher: "+result.getInt("teacherId"));
                System.out.print(", student: "+result.getInt("studentId")+"\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void getAllMarkRecords() {

        String statement = new SelectBuilder("marks")
                .column("*")
                .build();

        try {
            ResultSet result = getResultFromQuery(statement);
            while (result.next()) {
                System.out.println("STUDENT: "+result.getString("studentId"));
                System.out.println("ZNAMKA: "+result.getString("markValue"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteAllRecords() {
        executeQuery("DELETE FROM users");
        executeQuery("DELETE FROM marks");
        executeQuery("DELETE FROM classes");
    }

    public void deleteAllTables() {
        executeQuery("DROP TABLE users");
        executeQuery("DROP TABLE marks");
        executeQuery("DROP TABLE classes");
    }

    public void insertDefaultValues() {
        insertNewUser(new User.UserBuilder()
                .withName("Petr")
                .withSurname("Student")
                .withBirthday("2001-12-24")
                .withUserType("student")
                .buildStudent());
        insertNewUser(new User.UserBuilder()
                .withName("Petr")
                .withSurname("Student")
                .withBirthday("2001-12-24")
                .withUserType("student")
                .buildStudent());
        insertNewUser(new User.UserBuilder()
                .withName("Petr")
                .withSurname("Student")
                .withBirthday("2001-12-24")
                .withUserType("student")
                .buildStudent());
        //teacher
        insertNewUser(new User.UserBuilder()
                .withName("Jan")
                .withSurname("Ucitel")
                .withBirthday("2001-12-24")
                .withUserType("teacher")
                .buildTeacher());

        insertNewUser(new User.UserBuilder()
                .withName("Karel")
                .withSurname("Ucitel")
                .withBirthday("2001-12-23")
                .withUserType("teacher")
                .buildTeacher());
        //student


        //marks
        insertNewMark(1, 1.1);
        //classes
        insertNewClass(4, 1);
        insertNewClass(4, 2);
        insertNewClass(4, 3);
        insertNewClass(5, 1);
    }

    public User fetchUser(int userId, Enum... userDataTypes) {
        String wantedColumns = "";

        for (Enum column : userDataTypes) {
            wantedColumns += column.name() + ",";
        }
        wantedColumns = wantedColumns.substring(0, wantedColumns.length() - 1); //odstrani posledni carku


        User.UserBuilder userBuilder = new User.UserBuilder();

        String statement = new SelectBuilder("users")
                .column(wantedColumns)
                .whereEquals("userId",userId)
        .build();
        try {
            ResultSet rs = getResultFromQuery(statement);

            userBuilder.withId(userId);

            if (wantedColumns.contains("userName")) {
                userBuilder.withName(rs.getString("userName"));
            }
            if (wantedColumns.contains("userSurname")) {
                userBuilder.withSurname(rs.getString("userSurname"));
            }
            if (wantedColumns.contains("userBirthday")) {
                userBuilder.withBirthday(rs.getString("userBirthday"));
            }
            if (wantedColumns.contains("userType")) {
                userBuilder.withUserType(rs.getString("userType"));
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userBuilder.getUserType() != null && userBuilder.getUserType().equals("teacher")) {
            return userBuilder.buildTeacher();
        } else {
            return userBuilder.buildStudent();
        }

    }

    public List<Student> fetchStudents(int teacherId) {

        List<Student> students = new ArrayList<>();
        String statement = new SelectBuilder("classes")
                .column("studentId")
                .whereEquals("teacherId", teacherId)
        .build();

        try {
            ResultSet rs = getResultFromQuery(statement);
            while (rs.next()) {//projede vsechny studenty
                students.add((Student) fetchUser(rs.getInt("studentId"), UserDataTypes.userId));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Double> getMarks(int studentId) {
        List<Double> studyMarks = new ArrayList();

        String statement = new SelectBuilder("marks")
                .column("markValue")
                .whereEquals("studentId",studentId)
        .build();

        try {
            ResultSet queryResult = getResultFromQuery(statement);
            while (queryResult.next()) {//projede vsechny znamky
                studyMarks.add(queryResult.getDouble("markValue"));
            }
            queryResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studyMarks;
    }
    public List<Integer> getTeacherIds(){
        List<Integer> teacherIds =getTeacherWithStudentsIds(false);
        teacherIds.addAll(getTeacherWithoutStudentsIds());

        return teacherIds;
    }
    public List<Integer> getTeacherWithStudentsIds(int studentId) {//neserazeni ucitele jednoho zaka
        return getTeacherWithStudentsIds(studentId,false);
    }
    public List<Integer> getTeacherWithStudentsIds(boolean sortByStudentCount){//serazeni
        return getTeacherWithStudentsIds(0,sortByStudentCount);
    }
    public List<Integer> getTeacherWithStudentsIds(int studentId,boolean sortByStudentCount) {//vrati id ucitelu serazenych podle zaku nebo vrati vsechny ucitele zaka

        List<Integer> teacherIds = new ArrayList<>();

        SelectBuilder statement = new SelectBuilder("classes")
                .column("teacherId").distinct();
        if (studentId > 0){//pokud je zadano ID studenta, hledam ucitele tohoto studenta
            statement.whereEquals("studentId",studentId);
        }
        if (sortByStudentCount){
            statement
                    .column("COUNT(teacherId) AS teacherCount")
                    .groupBy("teacherId")
                    .orderBy("teacherCount");
        }


        try {
            ResultSet result = getResultFromQuery(statement.build());

            while (result.next()) {
                teacherIds.add(result.getInt("teacherId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherIds;
    }

    public List<Integer> getTeacherWithoutStudentsIds() {
        List<Integer> teacherIds = new ArrayList<>();

        String statement = new SelectBuilder("users")
                .column("userId")
                .whereEquals("userType","teacher")
                .where("userId NOT IN ("+ new SelectBuilder("classes").column("teacherId").build()+")")
                .build();

        try {
            ResultSet result = getResultFromQuery(statement);

            while (result.next()) {
                teacherIds.add(result.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherIds;
    }
    public List<Integer> getUsersAlphabeticallySorted(){
        List<Integer> userIds = new ArrayList<>();

        String statement = new SelectBuilder("users")
                .column("userId")
                .column("userSurname")
                .orderBy("userSurname",true)
                .build();

        try {
            ResultSet result = getResultFromQuery(statement);

            while (result.next()) {
                userIds.add(result.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIds;
    }
    public List<Integer>getStudentIds(){//vrati vsechny  id studentu
        return  getStudentIds(0);
    }
    public List<Integer>getStudentIds(int teacherId){
        List<Integer> userIds = new ArrayList<>();

        SelectBuilder statement = new SelectBuilder("classes").column("studentId");

        if (teacherId > 0) {//When teacher id is present, get only teacher's students
            statement.whereEquals("teacherId",teacherId);
        }

        try {
            ResultSet result = getResultFromQuery(statement.build());

            while (result.next()) {
                if (result.getInt("studentId") >0){
                    userIds.add(result.getInt("studentId"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIds;
    }
}
