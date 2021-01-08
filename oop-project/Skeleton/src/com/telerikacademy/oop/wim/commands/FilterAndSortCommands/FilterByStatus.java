package com.telerikacademy.oop.wim.commands.FilterAndSortCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Scanner;

public class FilterByStatus implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private WIMRepositoryImpl wimRepository;
    private Scanner user_input = new Scanner(System.in);

    public FilterByStatus(WIMRepositoryImpl wimRepository) {
        this.wimRepository = wimRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String enumAsString = parameters.get(0);
        System.out.println("Where would you like work items to be filtered by status from? Team, Board or member?");
        String listFromWhereCommand = user_input.nextLine();
        if (listFromWhereCommand.equalsIgnoreCase("team")){
            System.out.println("Please enter team name:");
            String teamName = user_input.nextLine();
            ValidationHelper.checkExistenceTeam(teamName,wimRepository.getTeams());
            return FilterWorkItemsFromTeam(teamName, enumAsString);
        } else if (listFromWhereCommand.equalsIgnoreCase("board")){
            System.out.println("Please enter team name and board name:");
            String[] teamNameAndBoardName = user_input.nextLine().split(" ");
            String teamName = teamNameAndBoardName[0];
            String boardName = teamNameAndBoardName[1];
            ValidationHelper.checkExistenceTeamAndBoard(teamName,boardName,wimRepository.getTeams());
            return FilterWorkItemsFromBoard(teamName, boardName, enumAsString);
        } else if (listFromWhereCommand.equalsIgnoreCase("member")){
            System.out.println("Please enter member name:");
            String memberName = user_input.nextLine();
            ValidationHelper.checkExistenceMember(memberName, wimRepository.getMembers());
            return FilterWorkItemsFromMember(memberName, enumAsString);
        } else {
            return ErrorMessages.INVALID_COMMAND;
        }
    }

    private String FilterWorkItemsFromMember(String memberName, String enumAsString) {
        StringBuilder stringBuilder = new StringBuilder();
        wimRepository.getMembers().get(memberName).getStories()
                .forEach(story -> {
                            if (String.valueOf(story.getStoryStatus()).equalsIgnoreCase(enumAsString)) {
                                stringBuilder.append(story.additionalInfo());
                            }
                        });
        wimRepository.getMembers().get(memberName).getBugs()
                .forEach(bug -> {
                    if (String.valueOf(bug.getBugStatus()).equalsIgnoreCase(enumAsString)) {
                        stringBuilder.append(bug.additionalInfo());
                    }
                });
        if (stringBuilder.toString().isEmpty()){
            stringBuilder.append("There are no work items with such priority assigned to this member.");
        }
        return stringBuilder.toString();
    }

    private String FilterWorkItemsFromBoard(String teamName, String boardName, String enumAsString) {
        StringBuilder stringBuilder = new StringBuilder();
        wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getAllStories()
                .forEach(story -> {
                    if (story.getStoryStatus().toString().equalsIgnoreCase(enumAsString)){
                        stringBuilder.append(story.additionalInfo());
                    }
                });
        wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getAllBugs()
                .forEach(bug -> {
                    if (bug.getBugStatus().toString().equalsIgnoreCase(enumAsString)){
                        stringBuilder.append(bug.additionalInfo());
                    }
                });
        wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getAllFeedbacks()
                .forEach(feedback -> {
                    if (feedback.getFeedbackStatus().toString().equalsIgnoreCase(enumAsString)){
                        stringBuilder.append(feedback.additionalInfo());
                    }
                });
        if (stringBuilder.toString().isEmpty()){
            stringBuilder.append("There are no work items with such priority in this board.");
        }
        return stringBuilder.toString();
    }

    private String FilterWorkItemsFromTeam(String teamName, String enumAsString){
        StringBuilder stringBuilder = new StringBuilder();
        wimRepository.getTeams().get(teamName).getAllBoards()
                .forEach((s, board) -> board.getAllFeedbacks()
                .forEach(feedback -> {
                    if (feedback.getFeedbackStatus().toString().equalsIgnoreCase(enumAsString)){
                        stringBuilder.append(feedback.additionalInfo());
                    }
                }));
        wimRepository.getTeams().get(teamName).getAllBoards()
                .forEach((s, board) -> board.getAllStories()
                .forEach(story -> {
                    if (story.getStoryStatus().toString().equalsIgnoreCase(enumAsString)){
                        stringBuilder.append(story.additionalInfo());
                    }
                }));
        wimRepository.getTeams().get(teamName).getAllBoards()
                .forEach((s, board) -> board.getAllBugs()
                .forEach(bug -> {
                    if (bug.getBugStatus().toString().equalsIgnoreCase(enumAsString)){
                        stringBuilder.append(bug.additionalInfo());
                    }
                }));
        if (stringBuilder.toString().isEmpty()){
            stringBuilder.append("There are no work items with such priority in this board.");
        }
        return stringBuilder.toString();
    }
}