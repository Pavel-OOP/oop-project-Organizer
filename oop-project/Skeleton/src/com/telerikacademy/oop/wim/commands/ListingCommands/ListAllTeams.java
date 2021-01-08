package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.TeamImpl;

import java.util.List;
import java.util.Map;

public class ListAllTeams implements Command {

    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private final Map<String, TeamImpl> teams;

    public ListAllTeams(Map<String, TeamImpl> teams) {
        this.teams = teams;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        return listAllTeams();
    }

    private String listAllTeams() {
        if (teams.isEmpty()) {
            return ErrorMessages.NO_TEAMS_TO_LIST;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Teams found: %d\n", teams.size()));
        teams.forEach((name, team) -> stringBuilder.append(String.format("[ %s ] ", name)));
        return stringBuilder.toString();
    }
}
