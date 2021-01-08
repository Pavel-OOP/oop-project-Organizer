package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.contracts.Team;

import java.util.List;
import java.util.Map;

public class ListMemberActivity implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final WIMRepositoryImpl wimRepository;

    public ListMemberActivity(WIMRepositoryImpl wimRepository) {
        this.wimRepository = wimRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String memberName = parameters.get(0);
        return ListMemberHistory(memberName);
    }

    private String ListMemberHistory(String memberName) {
        ValidationHelper.checkExistenceMember(memberName, wimRepository.getMembers());
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : wimRepository.getTeams().keySet()) {
            if (wimRepository.getTeams().get(name).getAllMembers().containsKey(memberName)) {
                stringBuilder.append(wimRepository.getTeams().get(name).printMemberHistory());
            }
        }
        return stringBuilder.toString();
    }
}
