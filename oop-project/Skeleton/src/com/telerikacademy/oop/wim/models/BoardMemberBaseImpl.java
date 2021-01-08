package com.telerikacademy.oop.wim.models;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.models.contracts.BoardMember;
import com.telerikacademy.oop.wim.models.contracts.WorkItems;

import java.util.ArrayList;
import java.util.List;

abstract class BoardMemberBaseImpl implements BoardMember {
    private String name;
    private List<WorkItemsImpl> workItemsList;


    public BoardMemberBaseImpl(String name) {
        setName(name);
        this.workItemsList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<WorkItemsImpl> getWorkItemsList() {
        return workItemsList;
    }

    public void setName(String name) {
        ValidationHelper.checkNull("Name", name);
        ValidationHelper.checkNameLength(name);
        this.name = name;
    }

    protected abstract void activityHistory();
}
