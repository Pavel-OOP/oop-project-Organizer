package com.telerikacademy.oop.wim.commands.CreationCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.core.factories.CreationFactoryImpl;
import com.telerikacademy.oop.wim.models.FeedbackImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.enums.FeedbackStatus;

import java.util.List;

public class CreateFeedback implements Command {

    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 6;

    private final WIMRepositoryImpl wimRepository;
    private final CreationFactoryImpl creationFactory = new CreationFactoryImpl();

    public CreateFeedback(WIMRepositoryImpl wimRepository) {
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
        Integer rating;
        try {
            rating = Integer.parseInt(parameters.get(4));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rating must be an Integer!");
        }
        ValidationHelper.checkFeedbackEnums(parameters.get(5));
        FeedbackStatus status = FeedbackStatus.valueOf(parameters.get(5).toUpperCase());
        return createFeedback(teamName, boardName, title, description, rating, status);
    }

    private String createFeedback(String teamName, String boardName, String title, String description, Integer rating, FeedbackStatus status) {
        ValidationHelper.checkWorkItemDuplicationInBoard(teamName, boardName, title, wimRepository.getTeams(), "feedback");
        FeedbackImpl feedback = creationFactory.createFeedback(title, description, rating, status);
        wimRepository.addFeedbackToBoard(teamName, boardName, feedback);
        return String.format(ConfirmationMessages.FEEDBACK_CREATED, feedback.getTitle());
    }
}
