package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Map;

public class ListBoardActivity implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;
    private Map<String, TeamImpl> teams;

    public ListBoardActivity(Map<String, TeamImpl> teams) {
        this.teams = teams;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String teamName = parameters.get(0);
        String boardName = parameters.get(1);
        ValidationHelper.checkExistenceTeamAndBoard(teamName,boardName,teams);
        return ListBoardActivityLog(teamName, boardName);
    }

    private String ListBoardActivityLog(String teamName, String boardName) {
        if (teams.get(teamName).getAllBoards().get(boardName).getAllWorkItemsInOneWorkItemsImplList().isEmpty()){
            return "This board does not contain any work items";
        }
        return teams.get(teamName).getAllBoards().get(boardName).printBoardHistory();
    }
}
