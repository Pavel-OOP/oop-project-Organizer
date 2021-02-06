package com.telerikacademy.oop.wim.models;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.models.contracts.Team;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TeamImpl implements Team {

    private String name;
    private Map<String, MemberImpl> members;
    private Map<String, BoardImpl> boards;
    private List<String> activityHistoryTeam;
    private List<String> activityHistoryMember;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    public TeamImpl(String name) {
        setName(name);
        this.members = new HashMap<>();
        this.boards = new HashMap<>();
        this.activityHistoryMember = new ArrayList<>();
        this.activityHistoryTeam = new ArrayList<>();
        activityHistoryTeam.add(String.format("Team created. - %s",LocalDateTime.now().format(formatter)));
    }

    private void setName(String name) {
        ValidationHelper.checkNull("Team Name", name);
        ValidationHelper.checkNameLength(name);
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void addBoard(String boardName, BoardImpl board){
        if (boards.containsKey(boardName)){
            throw new IllegalArgumentException(ErrorMessages.BOARD_ALREADY_EXISTS);
        }
        this.boards.put(boardName, board);
        activityHistoryTeam.add(String.format("Board with name [ %s ] added. - %s", boardName, LocalDateTime.now().format(formatter)));
    }

    public void addBugToBoard (String BoardName, BugImpl bug){
        boards.get(BoardName).addBug(bug);
        activityHistoryTeam.add(String.format("Bug with name [ %s ] added. - %s", bug.getTitle(), LocalDateTime.now().format(formatter)));
    }

    public void addStoryToBoard(String BoardName, StoryImpl story){
        boards.get(BoardName).addStory(story);
        activityHistoryTeam.add(String.format("Story with name [ %s ] added. - %s", story.getTitle(), LocalDateTime.now().format(formatter)));
    }

    public void addFeedbackToBoard(String BoardName, FeedbackImpl feedback){
        boards.get(BoardName).addFeedback(feedback);
        activityHistoryTeam.add(String.format("Feedback with name [ %s ] added. - %s", feedback.getTitle(), LocalDateTime.now().format(formatter)));
    }

    public void addMemberToTeam(String memberName, MemberImpl member){
        if (members.containsKey(memberName)){
            throw new IllegalArgumentException(ErrorMessages.MEMBER_ALREADY_IN_THIS_TEAM);
        }
        this.members.put(memberName, member);
        activityHistoryTeam.add(String.format("Member with name [ %s ] added to this team. - %s",memberName, LocalDateTime.now().format(formatter)));
        activityHistoryMember.add(String.format("This member was added to team with name [ %s ]. - %s",this.name, LocalDateTime.now().format(formatter)));
    }

    public BoardImpl getBoard(String boardName){
        return boards.get(boardName);
    }

    public Map<String, BoardImpl> getAllBoards() {return boards;}

    public Map<String, MemberImpl> getAllMembers() {return members;}

    public void addBugToMember(String assignee, String boardName, String title) {
        try {
            BugImpl bug = boards.get(boardName).getBug(title);
            this.members.get(assignee).getWorkItemsList().add(bug);
            this.members.get(assignee).getBugs().add(bug);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Member with this name was not found in this team.");
        }
        activityHistoryTeam.add(String.format("Bug with name [ %s ] was added to member with name [ %s ]. - %s",
                boards.get(boardName).getBug(title), assignee, LocalDateTime.now().format(formatter)));
        activityHistoryMember.add(String.format("Bug with name [ %s ] was added to member with name [ %s ]. - %s",
                boards.get(boardName).getBug(title), assignee, LocalDateTime.now().format(formatter)));
    }

    public void addStoryToMember(String assignee, String boardName, String title){
        try {
            StoryImpl story = boards.get(boardName).getStory(title);
            this.members.get(assignee).getWorkItemsList().add(story);
            this.members.get(assignee).getStories().add(story);
        } catch (NullPointerException e){
            throw new IllegalArgumentException("Member with this name was not found in this team.");
        }
        activityHistoryTeam.add(String.format("Story with name [ %s ] was added to member with name [ %s ]. - %s",
                boards.get(boardName).getStory(title), assignee, LocalDateTime.now().format(formatter)));
        activityHistoryMember.add(String.format("Story with name [ %s ] was added to member with name [ %s ]. - %s",
                boards.get(boardName).getStory(title), assignee, LocalDateTime.now().format(formatter)));
    }

    public String printTeamHistory(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : activityHistoryTeam) {
            stringBuilder.append(s);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String printMemberHistory(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : activityHistoryMember) {
            stringBuilder.append(s);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
