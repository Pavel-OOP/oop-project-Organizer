package com.telerikacademy.oop.wim.commands.FilterAndSortCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.AssigneeImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.contracts.Assignee;

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
            return FilterAssigneesFromTeam(teamName, upOrDown);
        } else if (listFromWhereCommand.equalsIgnoreCase("board")) {
            System.out.println("Please enter team name and board name:");
            String[] teamNameAndBoardName = user_input.nextLine().split(" ");
            String teamName = teamNameAndBoardName[0];
            String boardName = teamNameAndBoardName[1];
            ValidationHelper.checkExistenceTeamAndBoard(teamName,boardName, wimRepository.getTeams());
            return FilterAssigneesFromBoard(teamName, boardName, upOrDown);
        }
        if (listFromWhereCommand.equalsIgnoreCase("member")) {
            System.out.println("Please enter member name:");
            String memberName = user_input.nextLine();
            ValidationHelper.checkExistenceMember(memberName,wimRepository.getMembers());
            return FilterAssigneesFromMember(memberName, upOrDown);
        } else {
            return ErrorMessages.INVALID_COMMAND;
        }
    }

    private String FilterAssigneesFromMember(String memberName, String upOrDown) {
        StringBuilder stringBuilder = new StringBuilder();
        List<AssigneeImpl> assigneesSorted = new ArrayList<>();
        wimRepository.getMembers().get(memberName).getBugAndStoriesAsWorkItemImplList()
                .stream()
                .sorted(Comparator.comparing(Assignee::getPriority))
                .forEach(assigneesSorted::add);
        if (upOrDown.equalsIgnoreCase("down")) {
            assigneesSorted
                    .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append("\n"));
        } else if (upOrDown.equalsIgnoreCase("up")) {
            Collections.reverse(assigneesSorted);
            assigneesSorted
                    .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append("\n"));
        } else {
            return "Up or down command was inputted incorrectly. Please try again.";
        }
        if (stringBuilder.toString().isEmpty()) {
            stringBuilder.append("There are no work items with such priority assigned to this member.");
        }
        return stringBuilder.toString();
    }

    private String FilterAssigneesFromBoard(String teamName, String boardName, String upOrDown) {
        StringBuilder stringBuilder = new StringBuilder();
        List<AssigneeImpl> assigneesSorted = new ArrayList<>();
        wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getAllAssigneesInOneAssigneeList()
                .stream()
                .sorted(Comparator.comparing(AssigneeImpl::getPriority))
                .forEach(assigneesSorted::add);
        if (upOrDown.equalsIgnoreCase("down")) {
            assigneesSorted
                    .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append("\n"));
        } else if (upOrDown.equalsIgnoreCase("up")) {
            Collections.reverse(assigneesSorted);
            assigneesSorted
                    .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append("\n"));
        } else {
            return "Up or down command was inputted incorrectly. Please try again.";
        }
        if (stringBuilder.toString().isEmpty()) {
            stringBuilder.append("There are no work items with such priority assigned to this member.");
        }
        return stringBuilder.toString();
    }

    private String FilterAssigneesFromTeam(String teamName, String upOrDown) {
        StringBuilder stringBuilder = new StringBuilder();
        List<AssigneeImpl> assigneesSortedByBoards = new ArrayList<>();
        List<AssigneeImpl> assigneesSortedAll = new ArrayList<>();
        wimRepository.getTeams().get(teamName).getAllBoards()
                .forEach((s, board) -> board.getAllAssigneesInOneAssigneeList()
                        .stream()
                        .sorted(Comparator.comparing(AssigneeImpl::getPriority))
                        .forEach(assigneesSortedByBoards::add));
        assigneesSortedByBoards
                .stream()
                .sorted(Comparator.comparing(AssigneeImpl::getPriority))
                .forEach(assigneesSortedAll::add);

        if (upOrDown.equalsIgnoreCase("down")) {
            assigneesSortedAll
                    .forEach(workItem -> stringBuilder.append(workItem.additionalInfo()).append("\n"));
        } else if (upOrDown.equalsIgnoreCase("up")) {
            Collections.reverse(assigneesSortedAll);
            assigneesSortedAll
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