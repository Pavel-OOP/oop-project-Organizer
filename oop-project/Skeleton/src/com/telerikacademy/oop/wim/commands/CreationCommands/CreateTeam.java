package com.telerikacademy.oop.wim.commands.CreationCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.core.factories.CreationFactoryImpl;
import com.telerikacademy.oop.wim.models.TeamImpl;
import java.util.List;

public class CreateTeam implements Command {

    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

    private final WIMRepositoryImpl teamRepository;
    private final CreationFactoryImpl creationFactory = new CreationFactoryImpl();

    public CreateTeam(WIMRepositoryImpl teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
        public String execute(List<String> parameters) {
            if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
                throw new IllegalArgumentException
                        (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
            }
            String teamName = parameters.get(0);
            return createTeam(teamName);
        }

    private String createTeam(String teamName) {
        TeamImpl team = creationFactory.createTeam(teamName);
                teamRepository.addTeam(teamName, team);

        return String.format(ConfirmationMessages.TEAM_CREATED,teamName);
    }

}
