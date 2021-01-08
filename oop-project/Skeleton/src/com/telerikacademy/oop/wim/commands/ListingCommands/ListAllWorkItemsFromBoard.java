package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Map;

public class ListAllWorkItemsFromBoard implements Command {


    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;
    private Map<String, TeamImpl> teams;

    public ListAllWorkItemsFromBoard(Map<String, TeamImpl> teams) {
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
        ValidationHelper.checkExistenceTeamAndBoard(teamName, boardName, teams);
        return ListAllWorkItems(teamName, boardName);
    }

    private String ListAllWorkItems(String teamName, String boardName){
        return teams.get(teamName).getBoard(boardName).toString();
    }
}
