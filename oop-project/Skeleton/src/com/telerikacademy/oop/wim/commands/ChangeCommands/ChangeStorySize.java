package com.telerikacademy.oop.wim.commands.ChangeCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.enums.Size;

import java.util.List;
import java.util.Map;

public class ChangeStorySize implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;
    private final Map<String, TeamImpl> teams;
    private static final String pass = "AlongPassWordWithMultiple$(&@#(T##RUEWEJF";
    public ChangeStorySize(Map<String, TeamImpl> teams) {
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
        String storyName = parameters.get(2);
        ValidationHelper.checkStoryEnums("HIGH", parameters.get(3), "DONE");
        Size size = Size.valueOf(parameters.get(3).toUpperCase());
        Size previous = teams.get(teamName).getAllBoards().get(boardName).getStory(storyName).getSize();
        return ChangeStorySizeConsole(teamName, boardName, storyName, size, previous);
    }

    private String ChangeStorySizeConsole(String teamName, String boardName, String storyName, Size size, Size previous) {
        ValidationHelper.checkExistenceTeamAndBoard(teamName, boardName, teams);
        ValidationHelper.checkExistenceWorkItemInBoard(teamName, boardName, storyName, teams, "story");
        teams.get(teamName).getAllBoards().get(boardName).getStory(storyName).setSize(size, pass);
        return String.format(ConfirmationMessages.STORY_PRIORITY_CHANGED, previous, size);
    }
}
