package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Scanner;

public class ListAllFeedback implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private WIMRepositoryImpl wimRepository;
    private Scanner user_input = new Scanner(System.in);

    public ListAllFeedback(WIMRepositoryImpl wimRepository) {
        this.wimRepository = wimRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        System.out.println("Where would you like feedbacks to be listed from? Team or Board?");
        String listFromWhereCommand = user_input.nextLine();
        if (listFromWhereCommand.equalsIgnoreCase("team")){
            System.out.println("Please enter team name:");
            String teamName = user_input.nextLine();
            ValidationHelper.checkExistenceTeam(teamName,wimRepository.getTeams());
            return ListAllFeedbackFromTeam(teamName);
        } else if (listFromWhereCommand.equalsIgnoreCase("board")){
            System.out.println("Please enter team name and board name:");
            String[] teamNameAndBoardName = user_input.nextLine().split(" ");
            String teamName = teamNameAndBoardName[0];
            String boardName = teamNameAndBoardName[1];
            ValidationHelper.checkExistenceTeamAndBoard(teamName,boardName,wimRepository.getTeams());
            return ListAllFeedbackFromBoard(teamName, boardName);
        } else {
            return ErrorMessages.INVALID_COMMAND;
        }
    }

    private String ListAllFeedbackFromBoard(String teamName, String boardName) {
        if (wimRepository.getTeams().get(teamName).getBoard(boardName).getAllFeedbacks().isEmpty()){
            return "This board has no work items in it.";
        }
        return wimRepository.getTeams().get(teamName).getBoard(boardName).listFeedbacks();
    }

    private String ListAllFeedbackFromTeam(String teamName){
        StringBuilder stringBuilder = new StringBuilder();
        wimRepository.getTeams().get(teamName).getAllBoards()
                .forEach((s,board) -> board.getAllFeedbacks()
                        .forEach(feedback -> stringBuilder.append(feedback.additionalInfo()).append("\n")));
        if (stringBuilder.toString().isEmpty()){
            return "This team has no work items in it.";
        }
        return stringBuilder.toString();
    }
}