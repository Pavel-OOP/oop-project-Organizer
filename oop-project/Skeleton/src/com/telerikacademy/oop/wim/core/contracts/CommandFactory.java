package com.telerikacademy.oop.wim.core.contracts;

import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.core.factories.CommandFactoryImpl;

public interface CommandFactory {
    
    Command createCommand(String commandTypeAsString, CommandFactoryImpl commandFactory, WIMRepositoryImpl teamRepository);
}
