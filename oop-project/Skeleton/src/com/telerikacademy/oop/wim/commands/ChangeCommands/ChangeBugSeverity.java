package com.telerikacademy.oop.wim.commands.ChangeCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.enums.Severity;

import java.util.List;
import java.util.Map;

public class ChangeBugSeverity implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;
    private final Map<String, TeamImpl> teams;
    private static final String pass = "AlongPassWordWithMultiple#4!2#F)E#)M#W)FICPI#)";

    public ChangeBugSeverity(Map<String, TeamImpl> teams) {
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
        ValidationHelper.checkBugEnums("HIGH", parameters.get(3), "ACTIVE");
        Severity severity = Severity.valueOf(parameters.get(3).toUpperCase());
        Severity previous = teams.get(teamName).getAllBoards().get(boardName).getBug(bugName).getSeverity();
        return ChangeBugSeverityConsole(teamName, boardName, bugName, severity, previous);
    }

    private String ChangeBugSeverityConsole(String teamName, String boardName, String bugName, Severity severity, Severity previous) {
        ValidationHelper.checkExistenceTeamAndBoard(teamName, boardName, teams);
        ValidationHelper.checkExistenceWorkItemInBoard(teamName, boardName, bugName, teams, "bug");
        teams.get(teamName).getAllBoards().get(boardName).getBug(bugName).setSeverity(severity, pass);
        return String.format(ConfirmationMessages.BUG_SEVERITY_CHANGED, previous, severity);
    }
}