package com.telerikacademy.oop.wim.commands.AddCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.core.factories.CreationFactoryImpl;
import com.telerikacademy.oop.wim.models.MemberImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;

public class AddMemberToTeam implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

    private final WIMRepositoryImpl wimRepository;

    public AddMemberToTeam(WIMRepositoryImpl wimRepository) {
        this.wimRepository = wimRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String teamName = parameters.get(0);
        String memberName = parameters.get(1);
        ValidationHelper.checkExistenceTeam(teamName, wimRepository.getTeams());
        ValidationHelper.checkExistenceMember(memberName, wimRepository.getMembers());
        MemberImpl member = wimRepository.getMembers().get(memberName);
        wimRepository.addMemberToTeam(teamName, memberName, member);

        return String.format(ConfirmationMessages.MEMBER_ADDED_TO_TEAM, memberName, teamName);
    }
}
