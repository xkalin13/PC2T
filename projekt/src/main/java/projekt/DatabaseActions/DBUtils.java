package projekt.DatabaseActions;

import projekt.Users.Student;
import projekt.Users.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface DBUtils {
    //DB

    public void createTables();
    public void insertDefaultValues();
    public void deleteAllTables();
    public void deleteAllRecords();
    public void getAllMarkRecords();
    public void getAllClassesRecords();

    public void deleteClass(int teacherId, int studentId);
    public void insertNewClass(int teacherId, int studentId);

    public ResultSet getResultFromQuery(String sqlQuery);
    public void executeQuery(String sqlQuery);

    public static void endConnection() {    }

    public static void setDatabaseConnection(String url,String user,String password){ }

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

    //User
    public void insertNewUser(User user);
    public void deleteUser(int userId);

    public int getLastUserId();
    public int getUserCount();
    public int getUserCount(String passedUserType, String tableName);
    public boolean checkForDuplicates(User user);
    public boolean isUserType(int userId, String passedUserType);
    public User fetchUser(int userId, Enum... userDataTypes);
    public boolean checkIfClassExists(int teacherId,int studentId);
    public boolean checkIfUserExists(int userId);
    public void getAllUserRecords();

    //Teacher
    public List<Integer> getTeacherIds();

    public List<Integer> getTeacherWithStudentsIds(int studentId);
    public List<Integer> getTeacherWithStudentsIds(boolean sortByStudentCount);
    public List<Integer> getTeacherWithStudentsIds(int studentId,boolean sortByStudentCount);
    public List<Integer> getTeacherWithoutStudentsIds();

    //Student
    public void insertNewMark(int userId, double markValue);
    public List<Double> getMarks(int studentId);
    public List<Student> fetchStudents(int teacherId);
    public List<Integer> getUsersAlphabeticallySorted();
    public List<Integer>getStudentIds();
    public List<Integer> getStudentIds(int teacherId);
}
