package com.telerikacademy.oop.wim.commands.CreationCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.core.factories.CreationFactoryImpl;
import com.telerikacademy.oop.wim.models.BoardImpl;
import com.telerikacademy.oop.wim.models.BugImpl;
import com.telerikacademy.oop.wim.models.MemberImpl;

import java.util.List;

public class CreateBoard implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;

    private final WIMRepositoryImpl boardRepository;
    private final CreationFactoryImpl creationFactory = new CreationFactoryImpl();

    public CreateBoard(WIMRepositoryImpl boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String teamName = parameters.get(0);
        String boardName = parameters.get(1);
        return createBoard(boardName, teamName);
    }

    private String createBoard(String boardName, String teamName) {
        BoardImpl board = creationFactory.createBoard(boardName);
        boardRepository.addBoardToTeam(boardName, teamName, board);

        return String.format(ConfirmationMessages.BOARD_CREATED,boardName);
    }
}