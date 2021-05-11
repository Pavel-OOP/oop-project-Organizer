package com.telerikacademy.oop.wim.commands.AddCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.enums.FeedbackStatus;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddCommentToFeedback implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 3;
    private final Map<String, TeamImpl> teams;
    private Scanner user_input = new Scanner(System.in);

    public AddCommentToFeedback(Map<String, TeamImpl> teams) {
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
        return AddCommentToFeedbackConsole(teamName, boardName, feedbackName);
    }

    private String AddCommentToFeedbackConsole(String teamName, String boardName, String feedbackName) {
        ValidationHelper.checkExistenceTeamAndBoard(teamName, boardName, teams);
        ValidationHelper.checkExistenceWorkItemInBoard(teamName, boardName, feedbackName, teams, "feedback");
        System.out.println("Enter your comment:");
        String comment = user_input.nextLine();
        System.out.println("Enter the author of this comment:");
        String author = user_input.nextLine();
        String commentWithAuthor = String.format("%s - %s", comment, author);
        teams.get(teamName).getAllBoards().get(boardName).getFeedback(feedbackName).addComment(commentWithAuthor);
        return ConfirmationMessages.COMMENT_ADDED;
    }
}
