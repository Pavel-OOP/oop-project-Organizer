package com.telerikacademy.oop.wim.models;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.models.contracts.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BoardImpl extends BoardMemberBaseImpl implements Board {

    private List<BugImpl> bugs;
    private List<StoryImpl> stories;
    private List<FeedbackImpl> feedbacks;
    private List<String> activityHistoryBoard;
    private List<WorkItemsImpl> workItemsList;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public BoardImpl(String name) {
        super(name);
        bugs = new ArrayList<>();
        stories = new ArrayList<>();
        feedbacks = new ArrayList<>();
        activityHistoryBoard = new ArrayList<>();
        workItemsList = new ArrayList<>();
    }

    public void addBug(BugImpl bugName){
        if (bugs.contains(bugName)){
            throw new IllegalArgumentException(ErrorMessages.BUG_ALREADY_EXIST);
        }
        this.bugs.add(bugName);
        this.workItemsList.add(bugName);
        activityHistoryBoard.add(String.format("Bug with tittle [ %s ] was added. - %s "
                ,bugName.getTitle(), LocalDateTime.now().format(formatter)));
    }

    public void addStory(StoryImpl story ){
        if (stories.contains(story)){
            throw new IllegalArgumentException(ErrorMessages.STORY_ALREADY_EXIST);
        }
        this.stories.add(story);
        this.workItemsList.add(story);
        activityHistoryBoard.add(String.format("Story with title [ %s ] was added. - %s "
                ,story.getTitle(), LocalDateTime.now().format(formatter)));
    }

    public void addFeedback(FeedbackImpl feedbackName){
        if (feedbacks.contains(feedbackName)){
            throw new IllegalArgumentException(ErrorMessages.FEEDBACK_ALREADY_EXISTS);
        }
        this.feedbacks.add(feedbackName);
        this.workItemsList.add(feedbackName);
        activityHistoryBoard.add(String.format("Feedback with title [ %s ] was added. - %s "
                ,feedbackName.getTitle(), LocalDateTime.now().format(formatter)));
    }

    public BugImpl getBug(String title){
        for (int i = 0; i < bugs.size(); i++) {
            if (bugs.get(i).getTitle().equals(title)){
                return bugs.get(i);
            }
        }
        return null;
    }

    public StoryImpl getStory(String title){
        for (int i = 0; i < stories.size(); i++) {
            if (stories.get(i).getTitle().equals(title)){
                return stories.get(i);
            }
        }
        return null;
    }

    public FeedbackImpl getFeedback(String title){
        for (int i = 0; i < feedbacks.size(); i++) {
            if (feedbacks.get(i).getTitle().equals(title)){
                return feedbacks.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Bugs found: %d %n", bugs.size()));

        for (int i = 0; i < bugs.size(); i++) {
            stringBuilder.append(bugs.get(i).additionalInfo());
            stringBuilder.append(String.format("%n"));
        }
        stringBuilder.append(String.format("Stories found: %d %n", stories.size()));

        for (int i = 0; i < stories.size(); i++) {
            stringBuilder.append(stories.get(i).additionalInfo());
            stringBuilder.append(String.format("%n"));
        }

        stringBuilder.append(String.format("Feedbacks found: %d %n", feedbacks.size()));

        for (int i = 0; i < feedbacks.size(); i++) {
            stringBuilder.append(feedbacks.get(i).additionalInfo());
            stringBuilder.append(String.format("%n"));
        }

        return stringBuilder.toString();

    }

    public List<BugImpl> getAllBugs() {
        return this.bugs;
    }

    public List<StoryImpl> getAllStories() {
        return this.stories;
    }

    public List<FeedbackImpl> getAllFeedbacks() { return this.feedbacks;}

    public String printBoardHistory(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : activityHistoryBoard) {
            stringBuilder.append(s);
            stringBuilder.append(String.format("%n"));
        }
        return stringBuilder.toString();
    }

    public String listStories(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Stories found: %d %n", stories.size()));
        for (int i = 0; i < stories.size(); i++) {
            stringBuilder.append(stories.get(i).additionalInfo());
            stringBuilder.append(String.format("%n"));
        }
        return stringBuilder.toString();
    }

    public String listBugs(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Bugs found: %d %n", bugs.size()));
        for (int i = 0; i < bugs.size(); i++) {
            stringBuilder.append(bugs.get(i).additionalInfo());
            stringBuilder.append(String.format("%n"));
        }
        return stringBuilder.toString();
    }

    public String listFeedbacks(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Feedbacks found: %d %n", feedbacks.size()));
        for (int i = 0; i < feedbacks.size(); i++) {
            stringBuilder.append(feedbacks.get(i).additionalInfo());
            stringBuilder.append(String.format("%n"));
        }
        return stringBuilder.toString();
    }

    public List<WorkItemsImpl> getAllWorkItemsInOneList(){
        return new ArrayList<>(workItemsList);
    }

    public List<AssigneeImpl> getAllWorkItemsWithAssignees(){
        List<AssigneeImpl> assignees = new ArrayList<>();
        assignees.addAll(getAllStories());
        assignees.addAll(getAllBugs());
        return assignees;
    }
    public List<BugImpl> getAllWorkItemsInOneBugImplList(){
        return new ArrayList<>(getAllBugs());
    }

    public List<StoryImpl> getAllWorkItemsInOneStoryImplList(){
        return new ArrayList<>(getAllStories());
    }
}
