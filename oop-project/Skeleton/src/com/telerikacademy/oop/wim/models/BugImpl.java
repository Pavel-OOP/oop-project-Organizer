package com.telerikacademy.oop.wim.models;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.models.contracts.Bug;
import com.telerikacademy.oop.wim.models.contracts.BugStoryPriority;
import com.telerikacademy.oop.wim.models.contracts.CommentsInterface;
import com.telerikacademy.oop.wim.models.enums.BugStatus;
import com.telerikacademy.oop.wim.models.enums.Priority;
import com.telerikacademy.oop.wim.models.enums.Severity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BugImpl extends WorkItemsImpl implements Bug, CommentsInterface, BugStoryPriority {

    private List<String> stepsToReproduce;
    private Severity severity;
    private BugStatus bugStatus;
    private Priority priority;
    private List<String> bugComments;

    public BugImpl(String title,
                   String description,
                   List<String> stepsToReproduce,
                   Priority priority,
                   Severity severity,
                   BugStatus bugStatus,
                   String assignee) {

        super(title, description, assignee);

        this.priority = priority;
        this.stepsToReproduce = stepsToReproduce;
        this.severity = severity;
        this.bugStatus = bugStatus;
        bugComments = new ArrayList<>();
        super.workItemsHistory = new ArrayList<>();
        workItemsHistory.add(String.format("Bug created. - %s", LocalDateTime.now().format(formatter)));
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority, String pass) {
        if (pass.equals("AlongPassWordWithMultiple#4!2#F)E#)M#W)FICPI#)")) {
            String previous = String.valueOf(this.priority);
            this.priority = priority;
            workItemsHistory.add(String.format("Bug priority was changed from %s to %s. - %s",
                    previous, priority, LocalDateTime.now().format(formatter)));
        } else {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_PASS_NOT_ALLOWED);
        }
    }

    public BugStatus getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(BugStatus status, String pass) {
        if (pass.equals("AlongPassWordWithMultiple#4!2#F)E#)M#W)FICPI#)")) {
            String previous = String.valueOf(this.bugStatus);
            this.bugStatus = status;
            workItemsHistory.add(String.format("Bug status was changed from %s to %s. - %s",
                    previous, status, LocalDateTime.now().format(formatter)));
        } else {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_PASS_NOT_ALLOWED);
        }
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity, String pass) {
        if (pass.equals("AlongPassWordWithMultiple#4!2#F)E#)M#W)FICPI#)")) {
            String previous = String.valueOf(this.severity);

            this.severity = severity;
            workItemsHistory.add(String.format("Bug severity was changed from %s to %s. - %s",
                    previous, severity, LocalDateTime.now().format(formatter)));
        } else {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_PASS_NOT_ALLOWED);
        }
    }

    public List<String> getStepsToReproduce() {
        return stepsToReproduce;
    }

    public void addComment(String commentWithAuthor) {
        this.bugComments.add(commentWithAuthor);
        this.workItemsHistory.add(String.format("Comment was added. - %s", LocalDateTime.now().format(formatter)));
    }

    public List<String> getBugComments() {
        return new ArrayList<>(bugComments);
    }

    public String additionalInfo() {
        return String.format(
                "%s [Severity: %s] [Status: %s] [Priority: %s] [Assignee: %s]%n" +
                        "Description: %n%s%n" +
                        "Steps to reproduce: %n%s%n" +
                        "%s",
                super.toString(),
                getSeverity(), getBugStatus(), getPriority(), getAssignee(),
                getDescription(),
                printStepsToReproduceOnEachLine(),
                printComments());
    }

    public String printHistory() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : workItemsHistory) {
            stringBuilder.append(s);
            stringBuilder.append("%n");
        }
        return stringBuilder.toString();
    }

    public String printComments() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Comments found: %d%n", this.bugComments.size()));
        this.bugComments.forEach(comment -> stringBuilder.append(comment).append(String.format("%n")));
        return stringBuilder.toString();
    }

    private String printStepsToReproduceOnEachLine(){
        StringBuilder stringBuilder = new StringBuilder();
        stepsToReproduce.forEach(s -> stringBuilder.append(s).append("\n"));
        return stringBuilder.toString();
    }

}
