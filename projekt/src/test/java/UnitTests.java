import org.junit.jupiter.api.AfterAll;
import projekt.DatabaseActions.Database;
import projekt.InputHandler;
import projekt.Users.User;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class UnitTests {
    InputHandler handler = new InputHandler();
    Scanner sc = new Scanner(System.in);
    static User testUser;

    @Test
    public void testCreateTeacher(){
        testUser = new User.UserBuilder()
                .withName("TestName")
                .withSurname("TestSurname")
                .withBirthday("2000-12-25")
                .withUserType("teacher")
                .buildTeacher();
        User.addNewUser(testUser);

        Assertions.assertTrue(Database.getInstance().checkForDuplicates(testUser));
    }

    @AfterAll
    public static void testCleanupUser(){
        User.deleteUser(Database.getInstance().getLastUserId());
        Assertions.assertFalse(Database.getInstance().checkForDuplicates(testUser));
    }
}
