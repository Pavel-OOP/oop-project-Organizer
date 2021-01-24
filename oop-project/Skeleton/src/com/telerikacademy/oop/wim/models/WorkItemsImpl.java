package com.telerikacademy.oop.wim.models;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.contracts.Assignable;
import com.telerikacademy.oop.wim.models.contracts.WorkItems;
import com.telerikacademy.oop.wim.models.enums.Priority;
import com.telerikacademy.oop.wim.models.enums.Severity;
import com.telerikacademy.oop.wim.models.enums.Size;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class WorkItemsImpl implements WorkItems {

    private static int count;
    private final int ID;
    private String title;
    private String description;
    //private String assignee; // member
    private String comments;
    //private Priority priority;
    //private Severity severity;
    //private Size size;
    protected List<String> workItemsHistory;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public WorkItemsImpl(String title, String description) {
        ID = ++count;
        setTitle(title);
        setDescription(description);
        workItemsHistory = new ArrayList<>();
    }

    /*public WorkItemsImpl(String title, String description, String assignee) {
        ID = ++count;
        setTitle(title);
        setDescription(description);
        setAssignee(assignee);
        workItemsHistory = new ArrayList<>();
    }*/

    public int getID() {
        return this.ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    //public String getAssignee() {
    //    return assignee;
    //}


    public String getComments() {
        return comments;
    }

    //public abstract Priority getPriority();

    //public Severity getSeverity(){ return severity;}

    //public Size getSize() {return size;}

    private void setTitle(String title) {
        ValidationHelper.checkNull("Title", title);
        ValidationHelper.checkTitleLength(title);

        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelper.checkNull("Description", description);
        ValidationHelper.checkDescriptionLength(description);

        this.description = description;
    }

    //public void setAssignee(String assignee) {
    //    this.assignee = assignee;
    //}


    private void setComments(String comments) {
        this.comments = comments;
    }

    public abstract String additionalInfo();

    @Override
    public String toString(){
       return String.format(
                "[ Title: %s ] [ID: %s]",
               getTitle(),
               getID());
    }

}
