package models;


import com.telerikacademy.oop.wim.models.BugImpl;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.enums.BugStatus;
import com.telerikacademy.oop.wim.models.enums.Priority;
import com.telerikacademy.oop.wim.models.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BugImpl_Test {
    public static List<String> stepsToReproduce = new ArrayList<>();
    public static Severity severity;
    public static BugStatus bugStatus;
    public static List<String> bugComments;
    public static BugImpl bugTest;


    @BeforeAll
    public static void beforeEach(){
         bugTest = new BugImpl("bugTitle", "Some Random Bug Description", stepsToReproduce, Priority.LOW,
                Severity.MINOR, BugStatus.ACTIVE, "Monika");
    }

    @Test
    public void checkIf_BugImpl_isCreated_withValidArguments(){
        //check if is created and returns valid arguments
        Assertions.assertEquals(bugTest.getTitle(), "bugTitle");
        Assertions.assertEquals(bugTest.getDescription(), "Some Random Bug Description");
        Assertions.assertEquals(bugTest.getStepsToReproduce(), stepsToReproduce);
        Assertions.assertEquals(bugTest.getPriority(), Priority.LOW);

        //for my program to work properly I had to make the setter for Severity and BugStatus public, so I set a password
        Assertions.assertEquals(bugTest.getSeverity(), Severity.MINOR);
        bugTest.setSeverity(Severity.CRITICAL, "Wrong Password");
        Assertions.assertNotEquals(bugTest.getSeverity(), Severity.CRITICAL);

        Assertions.assertEquals(bugTest.getBugStatus(), BugStatus.ACTIVE);
        bugTest.setBugStatus(BugStatus.FIXED, "Wrong Password");
        Assertions.assertNotEquals(bugTest.getBugStatus(), BugStatus.FIXED);
        //continue

        Assertions.assertEquals(bugTest.getAssignee(), "Monika");

    }
}
