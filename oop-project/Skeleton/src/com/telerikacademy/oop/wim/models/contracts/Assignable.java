package com.telerikacademy.oop.wim.models.contracts;

import com.telerikacademy.oop.wim.models.enums.Priority;

public interface Assignable extends BugStoryPriority {

    String getAssignee();

    Priority getPriority();
}
