package com.telerikacademy.oop.wim.core.contracts;

import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;

import java.util.List;

public interface CommandParser {
    
    String parseCommand(String fullCommand);
    
    List<String> parseParameters(String fullCommand);

    List<String> parseParameterIfBug(String fullCommand, WIMRepositoryImpl wimRepository);

    List<String> parseParametersIfStory(String fullCommand, WIMRepositoryImpl wimRepository);

    List<String> parseParametersIfFeedback(String fullCommand, WIMRepositoryImpl wimRepository);

}
