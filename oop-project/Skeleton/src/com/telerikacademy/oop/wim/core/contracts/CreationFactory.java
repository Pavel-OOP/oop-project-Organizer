package com.telerikacademy.oop.wim.core.contracts;

import com.telerikacademy.oop.wim.models.contracts.Team;

public interface CreationFactory {

    // all necessary object creations will be here. Members, bugs, tasks, etc.
    
    Team createTeam(String name);

    
}
