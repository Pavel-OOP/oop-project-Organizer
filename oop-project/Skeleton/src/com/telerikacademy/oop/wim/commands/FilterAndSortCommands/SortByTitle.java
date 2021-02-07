package com.telerikacademy.oop.wim.commands.FilterAndSortCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.WorkItemsImpl;
import com.telerikacademy.oop.wim.models.contracts.WorkItems;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortByTitle implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private WIMRepositoryImpl wimRepository;
    private Scanner user_input = new Scanner(System.in);

    public SortByTitle(WIMRepositoryImpl wimRepository) {
        this.wimRepository = wimRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }

        System.out.println("Where would you like work items to be sorted by title from? Team, Board or member?");
        String listFromWhereCommand = user_input.nextLine();
        if (listFromWhereCommand.equalsIgnoreCase("team")) {
            System.out.println("Please enter team name:");
            String teamName = user_input.nextLine();
            ValidationHelper.checkExistenceTeam(teamName, wimRepository.getTeams());
            return FilterWorkItemsFromTeam(teamName);
        } else if (listFromWhereCommand.equalsIgnoreCase("board")) {
            System.out.println("Please enter team name and board name:");
            String[] teamNameAndBoardName = user_input.nextLine().split(" ");
            String teamName = teamNameAndBoardName[0];
            String boardName = teamNameAndBoardName[1];
            ValidationHelper.checkExistenceTeamAndBoard(teamName, boardName, wimRepository.getTeams());
            return FilterWorkItemsFromBoard(teamName, boardName);
        }
        if (listFromWhereCommand.equalsIgnoreCase("member")) {
            System.out.println("Please enter member name:");
            String memberName = user_input.nextLine();
            ValidationHelper.checkExistenceMember(memberName, wimRepository.getMembers());
            return FilterWorkItemsFromMember(memberName);
        } else {
            return ErrorMessages.INVALID_COMMAND;
        }
    }

    private String FilterWorkItemsFromMember(String memberName) {
        StringBuilder stringBuilder = new StringBuilder();
        wimRepository.getMembers().get(memberName).getWorkItemsList()
                .stream()
                .sorted(Comparator.comparing(WorkItems::getTitle))
                .forEach(item -> stringBuilder.append(item.additionalInfo()).append(System.lineSeparator()));
        if (stringBuilder.toString().isEmpty()) {
            stringBuilder.append("There are no work items with such priority assigned to this member.");
        }
        return stringBuilder.toString();
    }

    private String FilterWorkItemsFromBoard(String teamName, String boardName) {
        StringBuilder stringBuilder = new StringBuilder();
        wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getAllAssigneesInOneAssigneeList()
                .stream()
                .sorted(Comparator.comparing(WorkItemsImpl::getTitle))
                .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append(System.lineSeparator()));
        if (stringBuilder.toString().isEmpty()) {
            stringBuilder.append("There are no work items in this board.");
        }
        return stringBuilder.toString();
    }

    private String FilterWorkItemsFromTeam(String teamName) {
        StringBuilder stringBuilder = new StringBuilder();
        wimRepository.getTeams().get(teamName).getAllBoards()
                .forEach((s, board) -> board.getAllAssigneesInOneAssigneeList()
                        .stream()
                        .sorted(Comparator.comparing(WorkItemsImpl::getTitle))
                        .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append(System.lineSeparator())));
        if (stringBuilder.toString().isEmpty()) {
            stringBuilder.append("There are no work items with such priority in this board.");
        }
        return stringBuilder.toString();
    }
}