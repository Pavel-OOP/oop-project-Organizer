package models;


import com.telerikacademy.oop.wim.models.BugImpl;
import com.telerikacademy.oop.wim.models.GlobalConstants.GlobalConstants;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.contracts.Bug;
import com.telerikacademy.oop.wim.models.enums.BugStatus;
import com.telerikacademy.oop.wim.models.enums.Priority;
import com.telerikacademy.oop.wim.models.enums.Severity;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BugImpl_Test {
    public static List<String> stepsToReproduce = new ArrayList<>();
    public static BugImpl bugTest;


    @BeforeAll
    public static void beforeAll(){
         bugTest = new BugImpl("bugTitleTest", "Some Random Bug Description", stepsToReproduce, Priority.LOW,
                Severity.MINOR, BugStatus.ACTIVE, "Monika");
    }

    @Test
    public void checkIf_BugImpl_isCreated_withValidArguments(){
        //check if is created and returns valid arguments
        Assertions.assertEquals(bugTest.getTitle(), "bugTitleTest");
        Assertions.assertEquals(bugTest.getDescription(), "Some Random Bug Description");
        Assertions.assertEquals(bugTest.getStepsToReproduce(), stepsToReproduce);
        Assertions.assertEquals(bugTest.getPriority(), Priority.LOW);
        Assertions.assertEquals(bugTest.getSeverity(), Severity.MINOR);
        Assertions.assertEquals(bugTest.getBugStatus(), BugStatus.ACTIVE);
        Assertions.assertEquals(bugTest.getAssignee(), "Monika");

        /*for my program to work properly I had to make the setter for Severity and BugStatus public, so I set a
        password that returns IllegalArgumentException if wrong.
         */
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> bugTest.setSeverity(Severity.CRITICAL, "Wrong Password"));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> bugTest.setBugStatus(BugStatus.FIXED, "Wrong Password"));

    }


    @Test
    public void checkIf_BugImpl_isCreated_withInvalidTitle(){
        Throwable exception =  assertThrows(IllegalArgumentException.class,
                () -> {
                    new BugImpl("a".repeat(GlobalConstants.TITTLE_MIN_LENGTH-1), "Some Random Bug Description", stepsToReproduce, Priority.LOW,
                            Severity.MINOR, BugStatus.ACTIVE, "Monika");
                });
        assertEquals("Title must be between 10 and 50 symbols long.", exception.getMessage());

        Throwable exception2 =  assertThrows(IllegalArgumentException.class,
                () -> {
                    new BugImpl("a".repeat(GlobalConstants.TITTLE_MAX_LENGTH+1), "Some Random Bug Description", stepsToReproduce, Priority.LOW,
                            Severity.MINOR, BugStatus.ACTIVE, "Monika");
                });
        assertEquals("Title must be between 10 and 50 symbols long.", exception2.getMessage());
    }

    @Test
    public void checkIf_BugImpl_isCreated_withInvalidDescription(){
        Throwable exception =  assertThrows(IllegalArgumentException.class,
                () -> {
                    new BugImpl("bugTitleTest", "a".repeat(GlobalConstants.DESCRIPTION_MIN_LENGTH-1), stepsToReproduce, Priority.LOW,
                            Severity.MINOR, BugStatus.ACTIVE, "Monika");
                });
        assertEquals("Description must be between 10 and 500 symbols.", exception.getMessage());

        Throwable exception2 =  assertThrows(IllegalArgumentException.class,
                () -> {
                    new BugImpl("bugTitleTest", "a".repeat(GlobalConstants.DESCRIPTION_MAX_LENGTH+1), stepsToReproduce, Priority.LOW,
                            Severity.MINOR, BugStatus.ACTIVE, "Monika");
                });
        assertEquals("Description must be between 10 and 500 symbols.", exception2.getMessage());
    }
}
