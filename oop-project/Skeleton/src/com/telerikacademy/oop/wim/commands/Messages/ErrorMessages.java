package com.telerikacademy.oop.wim.commands.Messages;

import java.util.Locale;

public class ErrorMessages {

    public static final String TEAM_ALREADY_EXISTS =
            "Team name should be unique. Team with such name already exists!";
    public static final String THIS_TEAM_DOES_NOT_EXIST =
            "This team does not exist!";
    public static final String BOARD_ALREADY_EXISTS =
            "Board name should be unique. Board witch such name in this team already exists!";
    public static final String BOARD_DOESNT_EXIST =
            "This board does not exist!";
    public static final String MEMBER_ALREADY_EXISTS =
            "Member name should be unique. Member with name (%s) already exists!";
    public static final String THIS_MEMBER_DOES_NOT_EXIST =
            "Member does not exist!";
    public static final String WORKITEM_ALREADY_EXISTS =
            "Work item name should be unique. Work item with such name in this board already exists!";
    public static final String THIS_WORKITEM_DOES_NOT_EXIST =
            "This WorkItem does not exist!";
    public static final String NO_MEMBERS_TO_SHOW =
            "There are no members to show! Please create a member first.";
    public static final String MEMBER_IS_NOT_FROM_THIS_TEAM = "" +
            "This member is not from this team!";
    public static final String INVALID_NUMBER_OF_ARGUMENTS =
            "Invalid number of arguments. Command should contain %s parameter/s.";
    public static final String CANNOT_BE_NULL =
            "%s cannot be null.";
    public static final String NAME_LENGTH =
            "Name should be between %d and %d symbols long.";
    public static final String TITTLE_LENGTH =
            "Title must be between %d and %d symbols long.";
    public static final String DESCRIPTION_LENGTH =
            "Description must be between %d and %d symbols.";
    public static final String BELOW_ZERO =
            "%s cannot be less or equal to zero.";
    public static final String ABOVE_TEN =
            "%s cannot be more than ten.";
    public static final String EMPTY_COMMAND =
            "Command cannot be null or empty.";
    public static final String NO_TEAMS_TO_LIST =
            "No teams were created to show.Please create a team first.";
    public static final String NO_TEAM_WITH_THIS_NAME =
            "Invalid team name. No team with such name was found.";
    public static final String INVALID_COMMAND =
            "Invalid command name: %s. Type Help to see all commands.";
    public static final String BUG_ALREADY_EXIST =
            "Bug with such name in this board already exists.";
    public static final String UNIQUE_NAME =
            "%s is not unique. Please enter a different name.";
    public static final String STORY_ALREADY_EXIST =
            "Story with such name in this board already exists.";
    public static final String FEEDBACK_ALREADY_EXISTS =
            "Feedback with such name in this board already exists.";
    public static final String MEMBER_ALREADY_IN_THIS_TEAM =
            "This member is already in this team.";
    public static final String NO_BOARDS_TO_LIST =
            "There are no boards in this team";
    public static final String NO_BUGS_FOUND =
            "There are no bugs in this board";
    public static final String BUG_DOES_NOT_EXIST =
            "Bug with name [ %s ] does not exist in this board. " +
                    "Use ListAllWorkItemsFromBoard command to see all work items in this board.";
    public static final String THIS_WORKITEM_ALREADY_EXISTS_IN_THIS_BOARD =
            "Work item with [ %s ] name already exists in this board!";
    public static final String NO_STORIES_FOUND =
            "No stories found in this board.";
    public static final String STORY_DOES_NOT_EXIST =
            "Story with name [ %s ] does not exist in this board. " +
                    "Use ListAllWorkItemsFromBoard command to see all work items in this board.";
    public static final String NO_FEEDBACKS_FOUND =
            "No feedbacks found in this board.";
    public static final String FEEDBACK_NOT_FOUND =
            "Feedback with name [ %s ] does not exist in this board. " +
                    "Use ListAllWorkItemsFromBoard command to see all work items in this board.";
    public static final String MEMBER_NOT_IN_TEAM_OR_DOES_NOT_EXIST =
            "This member is either not in this team or does not exist!";
    public static final String NOT_IMPLEMENTED_YET =
            "This feature is yet to be implemented. Sorry for the inconvenience.";
    public static final String INVALID_FEEDBACK_RATING =
            "Feedback rating should be between %d and %d.";
    public static final String INCORRECT_PRIORITY_ENUM =
            "Incorrect priority entered. Correct priorities are LOW,MEDIUM or HIGH";
    public static final String INCORRECT_SEVERITY_ENUM =
            "Incorrect severity entered. Correct severities are CRITICAL, MAJOR or MINOR";
    public static final String INCORRECT_BUGSTATUS_ENUM =
            "Incorrect status for bug entered. Correct statuses for bug are ACTIVE or FIXED";
    public static final String INCORRECT_SIZE_ENUM =
            "Incorrect size entered. Correct size are LARGE,MEDIUM and SMALL";
    public static final String INCORRECT_STORYSTATUS_ENUM =
            "Incorrect status for story entered. Correct statuses for story are NOTDONE, INPROGRESS and DONE";
    public static final String INCORRECT_FEEDBACKSTATUS_ENUM =
            "Incorrect status for feedback entered. Correct statuses for feedback are NEW, UNSCHEDULED, SCHEDULED and DONE";
    public static final String INCORRECT_PASS_NOT_ALLOWED =
            "You are not allowed to call this method. Please use the 'getter'.";
}
