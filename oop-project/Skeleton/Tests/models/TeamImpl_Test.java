package models;

import com.telerikacademy.oop.wim.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamImpl_Test {

    public TeamImpl teamImplTest;
    public BoardImpl board;


    @BeforeEach
    public void beforeEach(){
         this.board = new BoardImpl("board1");
         this.teamImplTest = new TeamImpl("ValidTeam");
    }

    @Test
    public void checkIf_TeamImpl_throwsException_WithInvalidNameLengthOrNull(){
        //team name should be between 5 and 15 characters long including and NOT null
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> new TeamImpl("a".repeat(16)));

        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> new TeamImpl("a".repeat(4)));

        Assertions.assertThrows(NullPointerException.class,
                ()-> new TeamImpl(null));
    }

    @Test
    public void checkIf_TeamImpl_adds_board_withValidName(){
        teamImplTest.addBoard("board1", board);

        Assertions.assertEquals(board, teamImplTest.getBoard("board1"));
    }

    @Test
    public void checkIf_TeamImpl_doesnt_add_board_withSameName(){
        //name should be unique
        teamImplTest.addBoard("board1", board);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> teamImplTest.addBoard("board1", board));
    }

    @Test
    public void checkIf_TeamImpl_hasValidListOfBoards(){
        teamImplTest.addBoard("board1", board);

        Assertions.assertEquals(1, teamImplTest.getAllBoards().size());

        Assertions.assertEquals(board, teamImplTest.getAllBoards().get("board1"));
    }


}
