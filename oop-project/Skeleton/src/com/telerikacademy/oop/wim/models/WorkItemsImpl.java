package com.telerikacademy.oop.wim.models;

import com.telerikacademy.oop.wim.models.contracts.WorkItems;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class WorkItemsImpl implements WorkItems {

    private static int count;
    private final int ID;
    private String title;
    private String description;
    protected List<String> workItemsHistory;
    protected List<WorkItemsImpl> workItemsList;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public WorkItemsImpl(String title, String description) {
        ID = ++count;
        setTitle(title);
        setDescription(description);
        workItemsHistory = new ArrayList<>();
        workItemsList = new ArrayList<>();
    }

    public List<WorkItemsImpl> workItemsList(){
        return workItemsList;
    }

    public int getID() {
        return this.ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

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

    public abstract String additionalInfo();

    @Override
    public String toString(){
       return String.format(
                "[ Title: %s ] [ID: %s]",
               getTitle(),
               getID());
    }

}
