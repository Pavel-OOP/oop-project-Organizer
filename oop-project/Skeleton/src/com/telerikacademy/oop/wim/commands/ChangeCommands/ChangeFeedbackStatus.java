package com.telerikacademy.oop.wim.commands.ChangeCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.enums.BugStatus;
import com.telerikacademy.oop.wim.models.enums.FeedbackStatus;

import java.util.List;
import java.util.Map;

public class ChangeFeedbackStatus implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;
    private final Map<String, TeamImpl> teams;
    private static final String pass = "AlongPassWordWithMultipleFHWE#*)RJE@)$*#(U(URW#UWOU#";

    public ChangeFeedbackStatus(Map<String, TeamImpl> teams) {
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
        String feedbackName = parameters.get(2);
        ValidationHelper.checkFeedbackEnums(parameters.get(3));
        FeedbackStatus status = FeedbackStatus.valueOf(parameters.get(3).toUpperCase());
        FeedbackStatus previous = teams.get(teamName).getAllBoards().get(boardName).getFeedback(feedbackName).getFeedbackStatus();
        return ChangeFeedbackStatusConsole(teamName, boardName, feedbackName, status, previous);
    }

    private String ChangeFeedbackStatusConsole(String teamName, String boardName, String feedbackName, FeedbackStatus status, FeedbackStatus previous) {
        ValidationHelper.checkExistenceTeamAndBoard(teamName, boardName, teams);
        ValidationHelper.checkExistenceWorkItemInBoard(teamName, boardName, feedbackName, teams, "feedback");
        teams.get(teamName).getAllBoards().get(boardName).getFeedback(feedbackName).setStatus(status, pass);
        return String.format(ConfirmationMessages.BUG_STATUS_CHANGED, previous, status);
    }
}