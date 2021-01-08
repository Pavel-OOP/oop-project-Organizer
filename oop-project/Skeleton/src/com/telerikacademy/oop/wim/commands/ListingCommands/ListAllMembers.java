package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.MemberImpl;

import java.util.List;
import java.util.Map;

public class ListAllMembers implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private final Map<String, MemberImpl> members;

    public ListAllMembers(Map<String, MemberImpl> members) {
        this.members = members;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        return ListAllMembersConsole();
    }

    private String ListAllMembersConsole() {
        if (members.isEmpty()) {
            return ErrorMessages.NO_MEMBERS_TO_SHOW;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Members found: %d\n", members.size()));
        members.forEach((name, team) -> stringBuilder.append(String.format("[ %s ] ",name)));
        return stringBuilder.toString();
    }
}
