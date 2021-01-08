package com.telerikacademy.oop.wim.commands.ChangeCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.enums.BugStatus;

import java.util.List;
import java.util.Map;

public class ChangeBugStatus implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;
    private final Map<String, TeamImpl> teams;
    private static final String pass = "AlongPassWordWithMultiple#4!2#F)E#)M#W)FICPI#)";

    public ChangeBugStatus(Map<String, TeamImpl> teams) {
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
        String bugName = parameters.get(2);
        BugStatus status = BugStatus.valueOf(parameters.get(3).toUpperCase());
        ValidationHelper.checkBugEnums("HIGH", "CRITICAL", parameters.get(3));
        BugStatus previous = teams.get(teamName).getAllBoards().get(boardName).getBug(bugName).getBugStatus();
        return ChangeBugStatusConsole(teamName, boardName, bugName, status, previous);
    }

    private String ChangeBugStatusConsole(String teamName, String boardName, String bugName, BugStatus status, BugStatus previous) {
        ValidationHelper.checkExistenceTeamAndBoard(teamName, boardName, teams);
        ValidationHelper.checkExistenceWorkItemInBoard(teamName, boardName, bugName, teams, "bug");
        teams.get(teamName).getAllBoards().get(boardName).getBug(bugName).setBugStatus(status, pass);
        return String.format(ConfirmationMessages.BUG_STATUS_CHANGED, previous, status);
    }
}
