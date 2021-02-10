package models;

import com.telerikacademy.oop.wim.models.*;
import com.telerikacademy.oop.wim.models.enums.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class BoardImpl_Test {

    public BoardImpl boardTest;
    public static BugImpl bugTest;
    public static StoryImpl storyTest;
    public static FeedbackImpl feedbackTest;
    public static MemberImpl memberTest;
    public static TeamImpl teamTest;

    @BeforeAll
    public static void beforeAll(){
        memberTest = new MemberImpl("Monika");
        teamTest = new TeamImpl("TeamRocket");
        teamTest.addMemberToTeam("Monika", memberTest);

        bugTest = new BugImpl("bugTitle", "Some Random Bug Description", new ArrayList<>(), Priority.LOW,
                Severity.MINOR, BugStatus.ACTIVE, memberTest.getName());

        storyTest = new StoryImpl("storyTitle", "Some Random Story Description", Priority.LOW,
                Size.MEDIUM, StoryStatus.INPROGRESS, memberTest.getName());

        feedbackTest = new FeedbackImpl("feedbackTitle", "Some Random Feedback Description", 10,
                FeedbackStatus.SCHEDULED);
    }

    @Test
    public void checkIf_BoardImpl_isCreated_withValidArguments(){
        boardTest = new BoardImpl("TestBoard");

        Assertions.assertEquals("TestBoard", boardTest.getName());
    }

    @Test
    public void checkIf_BoardImpl_isNotCreated_withInvalidArguments(){
        //board name cannot be null
        Assertions.assertThrows(NullPointerException.class,
                () -> boardTest = new BoardImpl(null));

        //board name should be between 5 and 15 characters
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> boardTest = new BoardImpl("a".repeat(4)));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> boardTest = new BoardImpl("a".repeat(16)));
    }

    @Test
    public void checkIf_BoardImpl_addsBug(){
        boardTest = new BoardImpl("ValidTest");

        boardTest.addBug(bugTest);
        Assertions.assertEquals(boardTest.getBug("bugTitle"), bugTest);
        //check if returns null with invalid name
        Assertions.assertNull(boardTest.getBug("WrongTitle"));
    }

    @Test
    public void checkIf_BoardImpl_addsStory(){
        boardTest = new BoardImpl("ValidTest");

        boardTest.addStory(storyTest);
        Assertions.assertEquals(boardTest.getStory("storyTitle"), storyTest);
        //check if returns null with invalid name
        Assertions.assertNull(boardTest.getStory("WrongTitle"));
    }

    @Test
    public void checkIf_BoardImpl_addsFeedback(){
        boardTest = new BoardImpl("ValidTest");

        boardTest.addFeedback(feedbackTest);
        Assertions.assertEquals(boardTest.getFeedback("feedbackTitle"), feedbackTest);
        //check if returns null with invalid name
        Assertions.assertNull(boardTest.getFeedback("WrongTitle"));
    }

    @Test
    public void checkIf_BoardImpl_addsWorkItems(){
        boardTest = new BoardImpl("ValidTest");

        boardTest.addBug(bugTest);
        boardTest.addStory(storyTest);
        boardTest.addFeedback(feedbackTest);

        //All work items
        Assertions.assertEquals(boardTest.getAllWorkItemsInOneList().size(), 3);

        //All work items with assignees
        Assertions.assertEquals(boardTest.getAllWorkItemsWithAssignees().size(), 2);
    }



}
