package com.telerikacademy.oop.wim.core;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.contracts.*;
import com.telerikacademy.oop.wim.core.factories.CommandFactoryImpl;
import com.telerikacademy.oop.wim.core.providers.CommandParserImpl;
import com.telerikacademy.oop.wim.core.providers.ConsoleReader;
import com.telerikacademy.oop.wim.core.providers.ConsoleWriter;
import com.telerikacademy.oop.wim.models.GlobalConstants.GlobalConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class WIMEngineImpl implements Engine {

    private static final String TERMINATION_COMMAND = "Exit";

    private final CommandParser commandParser;
    private final WIMRepositoryImpl wimRepository;
    private final Writer writer;
    private final Reader reader;
    private final CommandFactoryImpl commandFactory;

    public WIMEngineImpl() {
        commandParser = new CommandParserImpl();
        writer = new ConsoleWriter();
        reader = new ConsoleReader();
        commandFactory = new CommandFactoryImpl();
        wimRepository = new WIMRepositoryImpl();
    }

    @Override
    public void start() {
        while (true) {
            try {
                String commandAsString = reader.readLine();
                if (commandAsString.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(commandAsString);
            } catch (Exception ex) {
                writer.writeLine(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : ex.toString());
            }
        }
    }

    @Override
    public void testStart() {
        File f = new File("actions.txt");
        while (true) {
            try {
                Scanner scanner = new Scanner(f);
                Scanner user_input = new Scanner(System.in);
                while (user_input.nextLine().equalsIgnoreCase("")) {
                    String commandAsString = scanner.nextLine();
                    System.out.println(GlobalConstants.ANSI_GREEN + commandAsString + GlobalConstants.ANSI_RESET);
                    if (commandAsString.equalsIgnoreCase(TERMINATION_COMMAND)) {
                        break;
                    }
                    processCommand(commandAsString);
                    // Thread.sleep(500);
                }
                String commandAsString = reader.readLine();
                if (commandAsString.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(commandAsString);

            } catch (Exception ex) {
                writer.writeLine(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : ex.toString());
            }

        }
    }

    private void processCommand(String commandAsString) {
        if (commandAsString == null || commandAsString.trim().equals("")) {
            throw new IllegalArgumentException(ErrorMessages.EMPTY_COMMAND);
        }

        String commandName = commandParser.parseCommand(commandAsString);
        if (commandName.equalsIgnoreCase("CreateBug")) {
            Command command = commandFactory.createCommand(commandName, commandFactory, wimRepository);
            List<String> parameters = commandParser.parseParameterIfBug(commandAsString, wimRepository);
            String executionResult = command.execute(parameters);
            writer.writeLine(executionResult);
        } else if (commandName.equalsIgnoreCase("CreateStory")){
            Command command = commandFactory.createCommand(commandName, commandFactory, wimRepository);
            List<String> parameters = commandParser.parseParametersIfStory(commandAsString, wimRepository);
            String executionResult = command.execute(parameters);
            writer.writeLine(executionResult);
        }else if (commandName.equalsIgnoreCase("CreateFeedback")) {
            Command command = commandFactory.createCommand(commandName, commandFactory, wimRepository);
            List<String> parameters = commandParser.parseParametersIfFeedback(commandAsString, wimRepository);
            String executionResult = command.execute(parameters);
            writer.writeLine(executionResult);
        } else {
            Command command = commandFactory.createCommand(commandName, commandFactory, wimRepository);
            List<String> parameters = commandParser.parseParameters(commandAsString);
            String executionResult = command.execute(parameters);
            writer.writeLine(executionResult);
        }
    }
}
