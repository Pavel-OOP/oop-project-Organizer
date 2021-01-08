package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Map;

public class ListTeamActivity implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final Map<String, TeamImpl> teams;

    public ListTeamActivity(Map<String, TeamImpl> teams) {
        this.teams = teams;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String teamName = parameters.get(0);
        return ListTeamActivityLog(teamName);
    }

    private String ListTeamActivityLog(String teamName) {
        ValidationHelper.checkExistenceTeam(teamName, teams);
        return teams.get(teamName).printTeamHistory();
    }
}
