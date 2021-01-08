package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ListAllBugs implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private WIMRepositoryImpl wimRepository;
    private Scanner user_input = new Scanner(System.in);

    public ListAllBugs(WIMRepositoryImpl wimRepository) {
        this.wimRepository = wimRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        System.out.println("Where would you like bugs to be listed from? Team, Board or member?");
        String listFromWhereCommand = user_input.nextLine();
        if (listFromWhereCommand.equalsIgnoreCase("team")) {
            System.out.println("Please enter team name:");
            String teamName = user_input.nextLine();
            ValidationHelper.checkExistenceTeam(teamName, wimRepository.getTeams());
            return ListAllBugsFromTeam(teamName);
        } else if (listFromWhereCommand.equalsIgnoreCase("board")) {
            System.out.println("Please enter team name and board name:");
            String[] teamNameAndBoardName = user_input.nextLine().split(" ");
            String teamName = teamNameAndBoardName[0];
            String boardName = teamNameAndBoardName[1];
            ValidationHelper.checkExistenceTeamAndBoard(teamName, boardName, wimRepository.getTeams());
            return ListAllBugsFromBoard(teamName, boardName);
        } else if (listFromWhereCommand.equalsIgnoreCase("member")) {
            System.out.println("Please enter member name:");
            String memberName = user_input.nextLine();
            ValidationHelper.checkExistenceMember(memberName, wimRepository.getMembers());
            return ListAllBugFromMember(memberName);
        } else {
            return ErrorMessages.INVALID_COMMAND;
        }
    }

    private String ListAllBugFromMember(String memberName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Bugs found that member is assigned to: %d%n",
                wimRepository.getMembers().get(memberName).getBugs().size()));
        for (int i = 0; i < wimRepository.getMembers().get(memberName).getBugs().size(); i++) {
            stringBuilder.append(wimRepository.getMembers().get(memberName).getBugs().get(i).additionalInfo());
            stringBuilder.append(String.format("%n"));
        }
        if (stringBuilder.toString().isEmpty()) {
            return "This member has no work items assigned.";
        }
        return stringBuilder.toString();
    }

    private String ListAllBugsFromBoard(String teamName, String boardName) {
        if (wimRepository.getTeams().get(teamName).getBoard(boardName).getAllBugs().isEmpty()) {
            return "This board has no work items in it.";
        }
        return wimRepository.getTeams().get(teamName).getBoard(boardName).listBugs();
    }

    private String ListAllBugsFromTeam(String teamName) {
        StringBuilder stringBuilder = new StringBuilder();
        wimRepository.getTeams().get(teamName).getAllBoards()
                .forEach((s, board) -> board.getAllBugs()
                        .forEach(bug -> stringBuilder.append(bug.additionalInfo()).append("\n")));
        if (stringBuilder.toString().isEmpty()) {
            return "This team has no work items in it.";
        }
        return stringBuilder.toString();
    }
}