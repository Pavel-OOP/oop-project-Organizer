package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Map;

public class ListAllTeamMembers implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final Map<String, TeamImpl> teams;

    public ListAllTeamMembers(Map<String, TeamImpl> teams) {
        this.teams = teams;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String teamName = parameters.get(0);
        ValidationHelper.checkExistenceTeam(teamName, teams);
        return ListAllTeamMembersConsole(teamName);
    }

    private String ListAllTeamMembersConsole(String teamName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Members found: %d\n", teams.get(teamName).getAllMembers().size()));
        teams.get(teamName).getAllMembers().forEach((name, team) -> stringBuilder.append(String.format("[ %s ] ", name)));
        return stringBuilder.toString();
    }
}
