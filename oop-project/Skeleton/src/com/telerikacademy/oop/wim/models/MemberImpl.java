package com.telerikacademy.oop.wim.models;


import com.telerikacademy.oop.wim.models.contracts.Member;
import com.telerikacademy.oop.wim.models.contracts.WorkItems;
import com.telerikacademy.oop.wim.models.enums.Priority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberImpl extends BoardMemberBaseImpl implements Member {

    private List<BugImpl> bugs = new ArrayList<>();
    private List<StoryImpl> stories = new ArrayList<>();

    public MemberImpl(String name) {
        super(name);
    }

    public List<BugImpl> getBugs(){
        return this.bugs;
    }

    public List<StoryImpl> getStories(){
        return this.stories;
    }


    /*public Map<String, List<WorkItemsImpl>> getWorkItems(){
        return workItems;
    }*/

    /*public List<WorkItemsImpl> testMethod(String member){
       return workItems.get(member);
    }*/

    @Override
    protected void activityHistory() {

    }

    public List<Assignee> getBugAndStoriesAsWorkItemImplList(){
        List<Assignee> bugAndStoriesList = new ArrayList<>();
        bugAndStoriesList.addAll(getStories());
        bugAndStoriesList.addAll(getBugs());
        return bugAndStoriesList;
    }

    public List<BugImpl> getBugAsWorkItemImplList(){
        return new ArrayList<>(getBugs());
    }

    public List<StoryImpl> getStoryAsWorkItemImplList(){
        return new ArrayList<>(getStories());
    }

}
