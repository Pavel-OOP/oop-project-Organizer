package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Scanner;

public class ListAllStories implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private WIMRepositoryImpl wimRepository;
    private Scanner user_input = new Scanner(System.in);

    public ListAllStories(WIMRepositoryImpl wimRepository) {
        this.wimRepository = wimRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        System.out.println("Where would you like stories to be listed from? Team, Board or member?");
        String listFromWhereCommand = user_input.nextLine();
        if (listFromWhereCommand.equalsIgnoreCase("team")) {
            System.out.println("Please enter team name:");
            String teamName = user_input.nextLine();
            ValidationHelper.checkExistenceTeam(teamName, wimRepository.getTeams());
            return ListAllStoriesFromTeam(teamName);
        } else if (listFromWhereCommand.equalsIgnoreCase("board")) {
            System.out.println("Please enter team name and board name:");
            String[] teamNameAndBoardName = user_input.nextLine().split(" ");
            String teamName = teamNameAndBoardName[0];
            String boardName = teamNameAndBoardName[1];
            ValidationHelper.checkExistenceTeamAndBoard(teamName, boardName, wimRepository.getTeams());
            return ListAllStoriesFromBoard(teamName, boardName);
        } else if (listFromWhereCommand.equalsIgnoreCase("member")) {
            System.out.println("Please enter member name:");
            String memberName = user_input.nextLine();
            ValidationHelper.checkExistenceMember(memberName, wimRepository.getMembers());
            return ListAllStoriesFromMember(memberName);
        } else {
            return ErrorMessages.INVALID_COMMAND;
        }
    }

    private String ListAllStoriesFromMember(String memberName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Stories found that member is assigned to: %d%n",
                wimRepository.getMembers().get(memberName).getStories().size()));
        for (int i = 0; i < wimRepository.getMembers().get(memberName).getStories().size(); i++) {
            stringBuilder.append(wimRepository.getMembers().get(memberName).getStories().get(i).additionalInfo());
            stringBuilder.append(String.format("%n"));
        }
        if (stringBuilder.toString().isEmpty()) {
            return "This member has no work items assigned.";
        }
        return stringBuilder.toString();
    }

    private String ListAllStoriesFromBoard(String teamName, String boardName) {
        if (wimRepository.getTeams().get(teamName).getBoard(boardName).getAllStories().isEmpty()) {
            return "This board has no work items in it.";
        }
        return wimRepository.getTeams().get(teamName).getBoard(boardName).listStories();
    }

    private String ListAllStoriesFromTeam(String teamName) {
        StringBuilder stringBuilder = new StringBuilder();
        wimRepository.getTeams().get(teamName).getAllBoards()
                .forEach((s, board) -> board.getAllStories()
                        .forEach(story -> stringBuilder.append(story.additionalInfo()).append("\n")));
        if (stringBuilder.toString().isEmpty()) {
            return "This team has no work items in it.";
        }
        return stringBuilder.toString();
    }
}