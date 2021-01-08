package com.telerikacademy.oop.wim.core.factories;

import com.telerikacademy.oop.wim.core.contracts.CreationFactory;
import com.telerikacademy.oop.wim.models.*;
import com.telerikacademy.oop.wim.models.contracts.Bug;
import com.telerikacademy.oop.wim.models.contracts.Feedback;
import com.telerikacademy.oop.wim.models.contracts.Team;
import com.telerikacademy.oop.wim.models.enums.*;

import java.util.List;

public class CreationFactoryImpl implements CreationFactory {


    public TeamImpl createTeam(String name) {
        return new TeamImpl(name);
    }

    public MemberImpl createMember(String name){
        return new MemberImpl(name);
    }

    public BoardImpl createBoard(String name){
        return new BoardImpl(name);
    }

    public BugImpl createBug(String title, String description, List<String> stepsToReporoduce, Priority priority, Severity severity, BugStatus status, String assignee){
        return new BugImpl(title, description, stepsToReporoduce, priority, severity, status, assignee);
    }

    public StoryImpl createStory(String title, String description, Priority priority, Size size, StoryStatus status, String assignee){
        return new StoryImpl(title, description, priority,size, status, assignee);
    }

    public FeedbackImpl createFeedback(String title, String description, Integer rating, FeedbackStatus status){
        return new FeedbackImpl(title, description, rating, status);
    }



    // This class is for the actual creation of objects.
    // this is the bridge between the the engine and the implementation classes.
    // it is called at the end of the engine cycles to fill information in the already created object in the WIMRepository from CommandFactoryImpl.

}
