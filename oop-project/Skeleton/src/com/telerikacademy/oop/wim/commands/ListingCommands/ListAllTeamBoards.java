package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.sun.java.accessibility.util.SwingEventMonitor;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Map;

public class ListAllTeamBoards implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final Map<String, TeamImpl> teams;

    public ListAllTeamBoards(Map<String, TeamImpl> teams) {
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
        return ListAllBoards(teamName);
    }

    private String ListAllBoards(String teamName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Boards found: %d\n", teams.get(teamName).getAllBoards().size()));
        teams.get(teamName).getAllBoards().forEach((name, team) -> stringBuilder.append(String.format("[ %s ] ", name)));
        return stringBuilder.toString();
    }
}
