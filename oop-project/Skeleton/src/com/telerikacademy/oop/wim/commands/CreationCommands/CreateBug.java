package com.telerikacademy.oop.wim.commands.CreationCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.core.factories.CreationFactoryImpl;
import com.telerikacademy.oop.wim.models.BugImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.enums.BugStatus;
import com.telerikacademy.oop.wim.models.enums.Priority;
import com.telerikacademy.oop.wim.models.enums.Severity;

import java.util.Arrays;
import java.util.List;

public class CreateBug implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 9;

    private final WIMRepositoryImpl wimRepository;
    private final CreationFactoryImpl creationFactory = new CreationFactoryImpl();

    public CreateBug(WIMRepositoryImpl wimRepository) {
        this.wimRepository = wimRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String teamName = parameters.get(0);
        String boardName = parameters.get(1);
        String title = parameters.get(2);
        String description = parameters.get(3);
        String[] stepsToReproduceArray = parameters.get(4).split("!@#");
        List<String> stepsToReproduce = Arrays.asList(stepsToReproduceArray);
        ValidationHelper.checkBugEnums(parameters.get(5), parameters.get(6), parameters.get(7));
        Priority priority = Priority.valueOf(parameters.get(5).toUpperCase());
        Severity severity = Severity.valueOf(parameters.get(6).toUpperCase());
        BugStatus status = BugStatus.valueOf(parameters.get(7).toUpperCase());
        String assignee = parameters.get(8);
        if (!wimRepository.getTeams().get(teamName).getAllMembers().containsKey(assignee)) {
            throw new IllegalArgumentException(ErrorMessages.MEMBER_NOT_IN_TEAM_OR_DOES_NOT_EXIST);
        }
        return createBug(teamName, boardName, title, description, stepsToReproduce, priority, severity, status, assignee);
    }

    private String createBug(String teamName, String boardName, String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity, BugStatus status, String assignee) {
        ValidationHelper.checkWorkItemDuplicationInBoard(teamName, boardName, title, wimRepository.getTeams(), "bug");
        BugImpl bug = creationFactory.createBug(title, description, stepsToReproduce, priority, severity, status, assignee);
        wimRepository.addBugToBoard(teamName, boardName, bug);
        wimRepository.addBugToMember(assignee, teamName, boardName, title);
        return String.format(ConfirmationMessages.BUG_CREATED, bug.getTitle());
    }
}
