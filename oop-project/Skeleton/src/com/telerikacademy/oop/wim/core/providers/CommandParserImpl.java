package com.telerikacademy.oop.wim.core.providers;

import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.core.contracts.CommandParser;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandParserImpl implements CommandParser {

    private static final String END_COMMAND = "FINISH";

    Scanner user_input = new Scanner(System.in);

    public String parseCommand(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    public List<String> parseParameters(String fullCommand) {
        String[] commandParts = fullCommand.split(" ");
        ArrayList<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }

    public List<String> parseParameterIfBug(String fullCommand, WIMRepositoryImpl wimRepository) {
        String[] commandParts = fullCommand.split(" ");
        ArrayList<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        ValidationHelper.checkExistenceTeamAndBoard(parameters.get(0), parameters.get(1), wimRepository.getTeams());
        ValidationHelper.checkWorkItemDuplicationInBoard
                (parameters.get(0), parameters.get(1), parameters.get(2), wimRepository.getTeams(), "bug");
        System.out.println("Please enter Bug description:");
        parameters.add(user_input.nextLine());
        ValidationHelper.checkDescriptionLength(parameters.get(3));
        System.out.println(String.format("Please enter steps to reproduce. Type %s when you are done.", END_COMMAND));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String nextLine = user_input.nextLine();
                if (END_COMMAND.equalsIgnoreCase(nextLine)) {
                    parameters.add(stringBuilder.toString());
                    break;
                } else {
                    stringBuilder.append(nextLine);
                    stringBuilder.append("!@#");
                }
            } catch (Exception ignored) {
            }
        }
        System.out.println("Please priority, severity, status and assignee");
        String[] nextParameters = user_input.nextLine().split(" ");
        for (int i = 0; i < nextParameters.length; i++) {
            parameters.add(nextParameters[i]);
        }
        ValidationHelper.checkBugEnums(parameters.get(5),parameters.get(6),parameters.get(7));
        ValidationHelper.checkExistenceMember(parameters.get(8),wimRepository.getMembers());
        return parameters;
    }

    public List<String> parseParametersIfStory(String fullCommand, WIMRepositoryImpl wimRepository) {
        String[] commandParts = fullCommand.split(" ");
        ArrayList<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        ValidationHelper.checkExistenceTeamAndBoard(parameters.get(0), parameters.get(1), wimRepository.getTeams());
        ValidationHelper.checkWorkItemDuplicationInBoard
                (parameters.get(0), parameters.get(1), parameters.get(2), wimRepository.getTeams(), "story");
        System.out.println("Please enter Story description:");
        parameters.add(user_input.nextLine());
        ValidationHelper.checkDescriptionLength(parameters.get(3));
        System.out.println("Please priority, size, status and assignee");
        String[] nextParameters = user_input.nextLine().split(" ");
        for (int i = 0; i < nextParameters.length; i++) {
            parameters.add(nextParameters[i]);
        }
        ValidationHelper.checkStoryEnums(parameters.get(4),parameters.get(5),parameters.get(6));
        ValidationHelper.checkExistenceMember(parameters.get(7),wimRepository.getMembers());
        return parameters;
    }

    public List<String> parseParametersIfFeedback(String fullCommand, WIMRepositoryImpl wimRepository) {
        String[] commandParts = fullCommand.split(" ");
        ArrayList<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        ValidationHelper.checkExistenceTeamAndBoard(parameters.get(0), parameters.get(1), wimRepository.getTeams());
        ValidationHelper.checkWorkItemDuplicationInBoard
                (parameters.get(0), parameters.get(1), parameters.get(2), wimRepository.getTeams(), "feedback");
        System.out.println("Please enter Feedback description:");
        parameters.add(user_input.nextLine());
        ValidationHelper.checkDescriptionLength(parameters.get(3));
        System.out.println("Please rating and status");
        String[] nextParameters = user_input.nextLine().split(" ");
        for (int i = 0; i < nextParameters.length; i++) {
            parameters.add(nextParameters[i]);
        }
        ValidationHelper.checkFeedbackEnums(parameters.get(5));
        return parameters;
    }
}
