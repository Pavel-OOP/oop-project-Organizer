package com.telerikacademy.oop.wim.core;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.core.contracts.WIMRepository;
import com.telerikacademy.oop.wim.models.*;
import com.telerikacademy.oop.wim.models.contracts.Feedback;
import com.telerikacademy.oop.wim.models.contracts.Member;

import java.util.HashMap;
import java.util.Map;

public class WIMRepositoryImpl implements WIMRepository {

    // this class creates the repository and holds all.

    private Map<String, MemberImpl> members;
    private Map<String, TeamImpl> teams;

    public WIMRepositoryImpl() {
        this.members = new HashMap<>();
        this.teams = new HashMap<>();
    }

    public void addTeam(String teamName, TeamImpl team) {
        if (teams.containsKey(teamName)) {
            throw new IllegalArgumentException(ErrorMessages.TEAM_ALREADY_EXISTS);
        }
        this.teams.put(teamName, team);
    }

    public void addMember(String memberName, MemberImpl member) {
        if (members.containsKey(memberName)) {
            throw new IllegalArgumentException(String.format(ErrorMessages.MEMBER_ALREADY_EXISTS, memberName));
        }
        this.members.put(memberName, member);
    }

    public void addBoardToTeam(String boardName, String teamName, BoardImpl board) {
        if (!teams.containsKey(teamName)) {
            throw new IllegalArgumentException(ErrorMessages.NO_TEAM_WITH_THIS_NAME);
        }
        teams.get(teamName).addBoard(boardName, board);
    }

    public void addBugToBoard(String teamName, String boardName, BugImpl bug) {
        teams.get(teamName).addBugToBoard(boardName, bug);
    }

    public void addStoryToBoard(String teamName, String boardName, StoryImpl story) {
        teams.get(teamName).addStoryToBoard(boardName, story);
    }

    public void addFeedbackToBoard(String teamName, String boardName, FeedbackImpl feedback) {
        teams.get(teamName).addFeedbackToBoard(boardName, feedback);
    }

    public void addMemberToTeam(String teamName, String memberName, MemberImpl member) {
        if (!teams.containsKey(teamName)) {
            throw new IllegalArgumentException(ErrorMessages.THIS_TEAM_DOES_NOT_EXIST);
        }
        if (!members.containsKey(memberName)) {
            throw new IllegalArgumentException(ErrorMessages.THIS_MEMBER_DOES_NOT_EXIST);
        }
        teams.get(teamName).addMemberToTeam(memberName, member);
    }

    public void addBugToMember(String assignee, String teamName, String boardName, String title) {
        teams.get(teamName).addBugToMember(assignee, boardName, title);
    }

    public void addStoryToMember(String assignee, String teamName, String boardName, String title) {
        teams.get(teamName).addStoryToMember(assignee, boardName, title);
    }

    public Map<String, TeamImpl> getTeams() {
        return teams;
    }

    public Map<String, MemberImpl> getMembers() {
        return members;
    }


}
