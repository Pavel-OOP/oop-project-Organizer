package core;

import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.*;
import models.BoardImpl_Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WIMRepositoryImpl_Test {

    public WIMRepositoryImpl wimRepository;
    public TeamImpl teamTest;
    public MemberImpl memberTest;
    public BoardImpl boardTest;

    public BugImpl bug;
    public StoryImpl story;
    public FeedbackImpl feedback;

    @BeforeEach
    public void beforeEach(){
        wimRepository = new WIMRepositoryImpl();
        teamTest = new TeamImpl("TestTeam");
        memberTest = new MemberImpl("Monika");
        boardTest = new BoardImpl("TestBoard");

        bug = BoardImpl_Test.bugTest;
        story = BoardImpl_Test.storyTest;
        feedback = BoardImpl_Test.feedbackTest;
    }

    @Test
    public void checkIf_WIMRepository_adds_TeamImpl_withValidArguments(){
        wimRepository.addTeam("TestTeam", teamTest);

        Assertions.assertEquals(wimRepository.getTeams().get("TestTeam"), teamTest);
    }

    @Test
    public void checkIf_WIMRepository_adds_MemberImpl_withValidArguments(){
        wimRepository.addMember("Monika", memberTest);

        Assertions.assertEquals(wimRepository.getMembers().get("Monika"), memberTest);
    }

    @Test
    public void checkIf_WIMRepository_adds_TeamImpl_withInvalidArguments(){
        wimRepository.addTeam("TestTeam", teamTest);

        //wimRepository should add teams with unique name
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> wimRepository.addTeam("TestTeam", teamTest));
    }


}
