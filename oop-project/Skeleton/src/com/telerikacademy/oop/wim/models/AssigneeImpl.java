package com.telerikacademy.oop.wim.models;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.models.contracts.BugStoryPriority;
import com.telerikacademy.oop.wim.models.contracts.WorkItems;
import com.telerikacademy.oop.wim.models.enums.Priority;

import java.time.LocalDateTime;

public abstract class AssigneeImpl extends WorkItemsImpl implements com.telerikacademy.oop.wim.models.contracts.Assignee, BugStoryPriority, WorkItems {

    private String assignee;
    private Priority priority;
    private String pass = "AlongPassWordWithMultiple#4!2#F)E#)M#W)FICPI#)";

    public AssigneeImpl(String title, String description, String assignee, Priority priority) {
        super(title, description);
        this.assignee = assignee;
        setPriority(priority, pass);
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssignee(){
        return assignee;
    }

    public Priority getPriority(){
        return priority;
    }

    public void setPriority(Priority priority, String pass) {
        if (pass.equals("AlongPassWordWithMultiple#4!2#F)E#)M#W)FICPI#)")) {
            String previous = String.valueOf(this.priority);
            this.priority = priority;
            workItemsHistory.add(String.format("Bug priority was changed from %s to %s. - %s",
                    previous, priority, LocalDateTime.now().format(formatter)));
        } else {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_PASS_NOT_ALLOWED);
        }
    }
}
