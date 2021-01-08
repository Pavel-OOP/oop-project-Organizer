package com.telerikacademy.oop.wim.models;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.models.GlobalConstants.GlobalConstants;
import com.telerikacademy.oop.wim.models.enums.BugStatus;
import com.telerikacademy.oop.wim.models.enums.Priority;
import com.telerikacademy.oop.wim.models.enums.Severity;

import java.util.*;

public class ValidationHelper {
    private ValidationHelper() {
    }

    public static void checkNull(String model, String input){
        if(input == null){
            throw new NullPointerException(String.format(ErrorMessages.CANNOT_BE_NULL, model));
        }
    }

    public static void checkNameLength(String input) {
        if (input.length() < GlobalConstants.NAME_MIN_LENGTH || input.length() > GlobalConstants.NAME_MAX_LENGTH) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.NAME_LENGTH,
                            GlobalConstants.NAME_MIN_LENGTH, GlobalConstants.NAME_MAX_LENGTH));
        }
    }

    public static void checkTitleLength(String input) {
        if (input.length() < GlobalConstants.NAME_MIN_LENGTH || input.length() > GlobalConstants.TITTLE_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(ErrorMessages.TITTLE_LENGTH,
                    GlobalConstants.TITTLE_MIN_LENGTH, GlobalConstants.TITTLE_MAX_LENGTH));
        }
    }

    public static void checkDescriptionLength(String input) {
        if (input.length() < GlobalConstants.DESCRIPTION_MIN_LENGTH || input.length() > GlobalConstants.DESCRIPTION_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(ErrorMessages.DESCRIPTION_LENGTH,
                    GlobalConstants.DESCRIPTION_MIN_LENGTH, GlobalConstants.DESCRIPTION_MAX_LENGTH));
        }
    }


    public static void isBelowZero(String whatWeMeasure, double input) {
        if (input <= 0.00) {
            throw new IllegalArgumentException(String.format(ErrorMessages.BELOW_ZERO, whatWeMeasure));
        }
    }

    public static void checkRatingConstraints(int input) {
        if (input < GlobalConstants.FEEDBACK_RATING_MIN_VALUE || input > GlobalConstants.FEEDBACK_RATING_MAX_VALUE) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_FEEDBACK_RATING,
                            GlobalConstants.FEEDBACK_RATING_MIN_VALUE,
                            GlobalConstants.FEEDBACK_RATING_MAX_VALUE));
        }
    }

    public static void checkExistenceTeam(String teamName, Map<String, TeamImpl> teams) {
        if (teams.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.NO_TEAMS_TO_LIST);
        } else if (!teams.containsKey(teamName)) {
            throw new IllegalArgumentException(ErrorMessages.THIS_TEAM_DOES_NOT_EXIST);
        }
    }

    public static void checkExistenceBoard(String teamName, String boardName, Map<String, TeamImpl> teams) {
        if (teams.get(teamName).getAllBoards().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.NO_BOARDS_TO_LIST);
        } else if (!teams.get(teamName).getAllBoards().containsKey(boardName)) {
            throw new IllegalArgumentException(ErrorMessages.BOARD_DOESNT_EXIST);
        }
    }

    public static void checkExistenceMember(String memberName, Map<String, MemberImpl> members){
        if (members.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.NO_MEMBERS_TO_SHOW);
        } else if (!members.containsKey(memberName)){
            throw new IllegalArgumentException(ErrorMessages.THIS_MEMBER_DOES_NOT_EXIST);
        }
    }

    public static void checkExistenceTeamAndBoard(String teamName, String boardName, Map<String, TeamImpl> teams){
        if (teams.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.NO_TEAMS_TO_LIST);
        } else if (!teams.containsKey(teamName)) {
            throw new IllegalArgumentException(ErrorMessages.THIS_TEAM_DOES_NOT_EXIST);
        } else if (teams.get(teamName).getAllBoards().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.NO_BOARDS_TO_LIST);
        } else if (!teams.get(teamName).getAllBoards().containsKey(boardName)) {
            throw new IllegalArgumentException(ErrorMessages.BOARD_DOESNT_EXIST);
        }
    }

    public static void checkExistenceWorkItemInBoard(String teamName,String boardName,String workItemName, Map<String, TeamImpl> teams, String type){
        if (type.equalsIgnoreCase("bug")){
            if (teams.get(teamName).getAllBoards().get(boardName).getAllBugs().isEmpty()) {
                throw new IllegalArgumentException(ErrorMessages.NO_BUGS_FOUND);
            }
            teams.get(teamName).getAllBoards().get(boardName).getAllBugs()
                        .forEach(bug -> {
                            if (!bug.getTitle().equalsIgnoreCase(workItemName)) {
                                throw new IllegalArgumentException(String.format(ErrorMessages.BUG_DOES_NOT_EXIST,workItemName));
                            }
                        });
        } else if (type.equalsIgnoreCase("story")){
            if (teams.get(teamName).getAllBoards().get(boardName).getAllStories().isEmpty()){
                throw new IllegalArgumentException(ErrorMessages.NO_STORIES_FOUND);
            }
            teams.get(teamName).getAllBoards().get(boardName).getAllStories()
                    .forEach(story -> {
                        if (!story.getTitle().equalsIgnoreCase(workItemName)) {
                            throw new IllegalArgumentException(String.format(ErrorMessages.BUG_DOES_NOT_EXIST,workItemName));
                        }
                    });
        } else if (type.equalsIgnoreCase("feedback")){
            if (teams.get(teamName).getAllBoards().get(boardName).getAllFeedbacks().isEmpty()){
                throw new IllegalArgumentException(ErrorMessages.NO_FEEDBACKS_FOUND);
            }
            teams.get(teamName).getAllBoards().get(boardName).getAllFeedbacks()
                    .forEach(feedback -> {
                        if (!feedback.getTitle().equalsIgnoreCase(workItemName)) {
                            throw new IllegalArgumentException(String.format(ErrorMessages.BUG_DOES_NOT_EXIST,workItemName));
                        }
                    });
        }
    }

    public static void checkBugEnums(String priority, String severity, String status){
        List<String> priorities = new ArrayList<>();
        priorities.add("HIGH"); priorities.add("MEDIUM"); priorities.add("LOW");
        List<String> severities = new ArrayList<>();
        severities.add("CRITICAL");severities.add("MAJOR");severities.add("MINOR");
        List<String> bugStatuses = new ArrayList<>();
        bugStatuses.add("ACTIVE");bugStatuses.add("FIXED");
        if (!priorities.contains(priority.toUpperCase())) {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_PRIORITY_ENUM);
        } else if (!severities.contains(severity.toUpperCase())){
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_SEVERITY_ENUM);
        } else if (!bugStatuses.contains(status.toUpperCase())){
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_BUGSTATUS_ENUM);
        }
    }

    public static void checkStoryEnums(String priority, String size, String status){
        List<String> priorities = new ArrayList<>();
        priorities.add("HIGH"); priorities.add("MEDIUM"); priorities.add("LOW");
        List<String> sizes = new ArrayList<>();
        sizes.add("LARGE");sizes.add("MEDIUM");sizes.add("SMALL");
        List<String> storyStatuses = new ArrayList<>();
        storyStatuses.add("DONE");storyStatuses.add("INPROGRESS");storyStatuses.add("NOTDONE");
        if (!priorities.contains(priority.toUpperCase())) {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_PRIORITY_ENUM);
        } else if (!sizes.contains(size.toUpperCase())){
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_SIZE_ENUM);
        } else if (!storyStatuses.contains(status.toUpperCase())){
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_STORYSTATUS_ENUM);
        }
    }

    public static void checkFeedbackEnums(String status) {
        List<String> feedbackStatuses = new ArrayList<>();
        feedbackStatuses.add("NEW");feedbackStatuses.add("UNSCHEDULED");
        feedbackStatuses.add("SCHEDULED");feedbackStatuses.add("DONE");
        if (!feedbackStatuses.contains(status.toUpperCase())) {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_FEEDBACKSTATUS_ENUM);
        }
    }

    public static void checkWorkItemDuplicationInBoard(String teamName, String boardName, String workItemName, Map<String, TeamImpl> teams, String type){
        if (type.equalsIgnoreCase("bug")){
            teams.get(teamName).getAllBoards().get(boardName).getAllBugs()
                    .forEach(bug -> {
                        if (bug.getTitle().equalsIgnoreCase(workItemName)) {
                            throw new IllegalArgumentException(ErrorMessages.BUG_ALREADY_EXIST);
                        }
                    });
        } else if (type.equalsIgnoreCase("story")){
            teams.get(teamName).getAllBoards().get(boardName).getAllStories()
                    .forEach(story -> {
                        if (story.getTitle().equalsIgnoreCase(workItemName)) {
                            throw new IllegalArgumentException(ErrorMessages.STORY_ALREADY_EXIST);
                        }
                    });
        } else if (type.equalsIgnoreCase("feedback")){
            teams.get(teamName).getAllBoards().get(boardName).getAllFeedbacks()
                    .forEach(feedback -> {
                        if (feedback.getTitle().equalsIgnoreCase(workItemName)) {
                            throw new IllegalArgumentException(ErrorMessages.FEEDBACK_ALREADY_EXISTS);
                        }
                    });
        }
    }
}
