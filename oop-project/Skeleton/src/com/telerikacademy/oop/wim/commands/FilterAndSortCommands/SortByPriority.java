package com.telerikacademy.oop.wim.commands.FilterAndSortCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.WorkItemsImpl;

import java.util.*;

public class SortByPriority implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private WIMRepositoryImpl wimRepository;
    private Scanner user_input = new Scanner(System.in);

    public SortByPriority(WIMRepositoryImpl wimRepository) {
        this.wimRepository = wimRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }

        System.out.println("Where would you like work items to be sorted by priority from? Team, Board or member?");
        String listFromWhereCommand = user_input.nextLine();
        System.out.println("How would you like for items to be sorted? Type UP for ascending or DOWN for descending");
        String upOrDown = user_input.nextLine();
        if (listFromWhereCommand.equalsIgnoreCase("team")) {
            System.out.println("Please enter team name:");
            String teamName = user_input.nextLine();
            ValidationHelper.checkExistenceTeam(teamName,wimRepository.getTeams());
            return FilterWorkItemsFromTeam(teamName, upOrDown);
        } else if (listFromWhereCommand.equalsIgnoreCase("board")) {
            System.out.println("Please enter team name and board name:");
            String[] teamNameAndBoardName = user_input.nextLine().split(" ");
            String teamName = teamNameAndBoardName[0];
            String boardName = teamNameAndBoardName[1];
            ValidationHelper.checkExistenceTeamAndBoard(teamName,boardName, wimRepository.getTeams());
            return FilterWorkItemsFromBoard(teamName, boardName, upOrDown);
        }
        if (listFromWhereCommand.equalsIgnoreCase("member")) {
            System.out.println("Please enter member name:");
            String memberName = user_input.nextLine();
            ValidationHelper.checkExistenceMember(memberName,wimRepository.getMembers());
            return FilterWorkItemsFromMember(memberName, upOrDown);
        } else {
            return ErrorMessages.INVALID_COMMAND;
        }
    }

    private String FilterWorkItemsFromMember(String memberName, String upOrDown) {
        StringBuilder stringBuilder = new StringBuilder();
        List<WorkItemsImpl> workItemsSorted = new ArrayList<>();
        wimRepository.getMembers().get(memberName).getBugAndStoriesAsWorkItemImplList()
                .stream()
                .sorted(Comparator.comparing(WorkItemsImpl::getPriority))
                .forEach(workItemsSorted::add);
        if (upOrDown.equalsIgnoreCase("down")) {
            workItemsSorted
                    .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append("\n"));
        } else if (upOrDown.equalsIgnoreCase("up")) {
            Collections.reverse(workItemsSorted);
            workItemsSorted
                    .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append("\n"));
        } else {
            return "Up or down command was inputted incorrectly. Please try again.";
        }
        if (stringBuilder.toString().isEmpty()) {
            stringBuilder.append("There are no work items with such priority assigned to this member.");
        }
        return stringBuilder.toString();
    }

    private String FilterWorkItemsFromBoard(String teamName, String boardName, String upOrDown) {
        StringBuilder stringBuilder = new StringBuilder();
        List<WorkItemsImpl> workItemsSorted = new ArrayList<>();
        wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getAllWorkItemsInOneWorkItemsImplList()
                .stream()
                .sorted(Comparator.comparing(WorkItemsImpl::getPriority))
                .forEach(workItemsSorted::add);
        if (upOrDown.equalsIgnoreCase("down")) {
            workItemsSorted
                    .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append("\n"));
        } else if (upOrDown.equalsIgnoreCase("up")) {
            Collections.reverse(workItemsSorted);
            workItemsSorted
                    .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append("\n"));
        } else {
            return "Up or down command was inputted incorrectly. Please try again.";
        }
        if (stringBuilder.toString().isEmpty()) {
            stringBuilder.append("There are no work items with such priority assigned to this member.");
        }
        return stringBuilder.toString();
    }

    private String FilterWorkItemsFromTeam(String teamName, String upOrDown) {
        StringBuilder stringBuilder = new StringBuilder();
        List<WorkItemsImpl> workItemsSortedByBoards = new ArrayList<>();
        List<WorkItemsImpl> workItemsSortedAll = new ArrayList<>();
        wimRepository.getTeams().get(teamName).getAllBoards()
                .forEach((s, board) -> board.getAllWorkItemsInOneWorkItemsImplList()
                        .stream()
                        .sorted(Comparator.comparing(WorkItemsImpl::getPriority))
                        .forEach(workItemsSortedByBoards::add));
        workItemsSortedByBoards
                .stream()
                .sorted(Comparator.comparing(WorkItemsImpl::getPriority))
                .forEach(workItemsSortedAll::add);

        if (upOrDown.equalsIgnoreCase("down")) {
            workItemsSortedAll
                    .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append("\n"));
        } else if (upOrDown.equalsIgnoreCase("up")) {
            Collections.reverse(workItemsSortedAll);
            workItemsSortedAll
                    .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append("\n"));
        } else {
            return "Up or down command was inputted incorrectly. Please try again.";
        }
        if (stringBuilder.toString().isEmpty()) {
            stringBuilder.append("There are no work items with such priority assigned to this member.");
        }
        return stringBuilder.toString();
    }
}