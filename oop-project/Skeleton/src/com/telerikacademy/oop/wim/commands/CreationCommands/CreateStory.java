package com.telerikacademy.oop.wim.commands.CreationCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.core.factories.CreationFactoryImpl;
import com.telerikacademy.oop.wim.models.StoryImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;
import com.telerikacademy.oop.wim.models.enums.*;

import java.util.List;

public class CreateStory implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 8;

    private final WIMRepositoryImpl wimRepository;
    private final CreationFactoryImpl creationFactory = new CreationFactoryImpl();

    public CreateStory(WIMRepositoryImpl wimRepository) {
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
        ValidationHelper.checkStoryEnums(parameters.get(4), parameters.get(5), parameters.get(6));
        Priority priority = Priority.valueOf(parameters.get(4).toUpperCase());
        Size size = Size.valueOf(parameters.get(5).toUpperCase());
        StoryStatus status = StoryStatus.valueOf(parameters.get(6).toUpperCase());
        String assignee = parameters.get(7);
        if (!wimRepository.getTeams().get(teamName).getAllMembers().containsKey(assignee)) {
            throw new IllegalArgumentException(ErrorMessages.MEMBER_NOT_IN_TEAM_OR_DOES_NOT_EXIST);
        }
        return createStory(teamName, boardName, title, description, priority, size, status, assignee);
    }

    private String createStory(String teamName, String boardName, String title, String description, Priority priority, Size size, StoryStatus status, String assignee) {
        ValidationHelper.checkWorkItemDuplicationInBoard(teamName, boardName, title, wimRepository.getTeams(), "story");
        StoryImpl story = creationFactory.createStory(title, description, priority, size, status, assignee);
        wimRepository.addStoryToBoard(teamName, boardName, story);
        wimRepository.addStoryToMember(assignee, teamName, boardName, title);
        return String.format(ConfirmationMessages.STORY_CREATED, story.getTitle());
    }
}
