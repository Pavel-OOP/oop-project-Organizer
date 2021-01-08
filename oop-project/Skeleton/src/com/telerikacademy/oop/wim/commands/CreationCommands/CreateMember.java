package com.telerikacademy.oop.wim.commands.CreationCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.core.factories.CreationFactoryImpl;
import com.telerikacademy.oop.wim.models.MemberImpl;


import java.util.List;

public class CreateMember implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

    private final WIMRepositoryImpl memberRepository;
    private final CreationFactoryImpl creationFactory = new CreationFactoryImpl();

    public CreateMember(WIMRepositoryImpl memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String memberName = parameters.get(0);
        return createMember(memberName);
    }

    private String createMember(String membername) {
        MemberImpl member = creationFactory.createMember(membername);
        memberRepository.addMember(membername, member);

        return String.format(ConfirmationMessages.MEMBER_CREATED,membername);
    }

}