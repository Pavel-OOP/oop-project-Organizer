package com.telerikacademy.oop.wim.commands.RemoveCommands;

import com.telerikacademy.oop.wim.commands.ChangeCommands.UnassignWorkItemFromMember;
import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.core.factories.CommandFactoryImpl;
import com.telerikacademy.oop.wim.models.TeamImpl;

import java.util.List;
import java.util.Scanner;

public class RemoveMember implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final WIMRepositoryImpl wimRepository;
    private Scanner user_input = new Scanner(System.in);

    public RemoveMember(WIMRepositoryImpl wimRepository) {
        this.wimRepository = wimRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String memberName = parameters.get(0);
        return removeMember(memberName);
    }

    private String removeMember(String memberName) {
        String workItemName = "";
        String boardName = "";
        String teamName = "";
        if (wimRepository.getMembers().isEmpty()) {
            return ErrorMessages.NO_MEMBERS_TO_SHOW;
        }
        System.out.println(String.format
                ("You are about to permanently delete a member.%n" +
                        "All work items that the member is assigned to must be reassigned to another member from the same team%n" +
                        "You can delete the member with all of their work items and skip this process by typing DELETEALL%n" +
                        "Would you like to assign the work items now? y/n%n" +
                        "Typing n will abort the process."));
        String confirmation = user_input.nextLine();
        if (confirmation.equalsIgnoreCase("n")) {
            return ConfirmationMessages.MEMBER_NOT_DELETED;
        } else if (confirmation.equalsIgnoreCase("y")) {
            return ErrorMessages.NOT_IMPLEMENTED_YET;
        } else if (confirmation.equalsIgnoreCase("deleteall")) {
            return ErrorMessages.NOT_IMPLEMENTED_YET;
        }
        else return ErrorMessages.INVALID_COMMAND;
    }
}
//            while (wimRepository.getMembers().get(memberName).getStories().size() > 0
//                   && wimRepository.getMembers().get(memberName).getBugs().size() > 0){
//                if (wimRepository.getMembers().get(memberName).getStories().size() > 0){
//                    for (int i = 0; i < 1; i++) {
//                        workItemName = wimRepository.getMembers().get(memberName).getStories().get(i).getTitle();
//                    }
//                    for (int i = 0; i < wimRepository.getTeams().size(); i++) {
//                        if (wimRepository.getTeams().entrySet().forEach(name ->, TeamImpl -> TeamImpl.);
//                            teamName = wimRepository.getTeams().get(i).getName();
//                            break;
//
//                        }
//                    }
//                    for (int i = 0; i < wimRepository.getTeams().get(teamName).getAllBoards().size(); i++) {
//                        for (int j = 0; j < wimRepository.getTeams().get(teamName).getAllBoards().get(i).getAllStories().size(); j++) {
//                            if (wimRepository.getTeams().get(teamName).getAllBoards().get(i).getAllStories().get(j).getAssignee().equals(memberName)) {
//                                boardName =
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//  }