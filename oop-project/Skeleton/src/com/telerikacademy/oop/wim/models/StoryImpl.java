package com.telerikacademy.oop.wim.models;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.models.contracts.BugStoryPriority;
import com.telerikacademy.oop.wim.models.contracts.CommentsInterface;
import com.telerikacademy.oop.wim.models.contracts.Story;
import com.telerikacademy.oop.wim.models.enums.Priority;
import com.telerikacademy.oop.wim.models.enums.Size;
import com.telerikacademy.oop.wim.models.enums.StoryStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StoryImpl extends WorkItemsImpl implements Story, CommentsInterface, BugStoryPriority {

    private Priority priority;
    private Size size;
    private StoryStatus storyStatus;
    private List<String> storyComments;

    public StoryImpl(String title,
                     String description,
                     Priority priority,
                     Size size,
                     StoryStatus storyStatus,
                     String assignee) {

        super(title, description, assignee);

        this.storyStatus = storyStatus;
        this.size = size;
        this.priority = priority;
        storyComments = new ArrayList<>();
        super.workItemsHistory = new ArrayList<>();
        workItemsHistory.add(String.format("Story created. - %s", LocalDateTime.now().format(formatter)));
    }

    public Priority getPriority() {
        return priority;
    }

    public Size getSize() {
        return size;
    }

    public StoryStatus getStoryStatus() {
        return storyStatus;
    }

    public void setPriority(Priority priority, String pass) {
        if (pass.equals("AlongPassWordWithMultiple$(&@#(T##RUEWEJF")) {
        String previous = String.valueOf(this.priority);
        this.priority = priority;
        workItemsHistory.add(String.format("Story priority was changed from %s to %s. - %s",
                previous, priority, LocalDateTime.now().format(formatter)));
    } else {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_PASS_NOT_ALLOWED);
        }
    }

    public void setSize(Size size, String pass) {
        if (pass.equals("AlongPassWordWithMultiple$(&@#(T##RUEWEJF")) {
        String previous = String.valueOf(this.size);
        this.size = size;
        workItemsHistory.add(String.format("Story size was changed from %s to %s. - %s",
                previous, size, LocalDateTime.now().format(formatter)));
    }else {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_PASS_NOT_ALLOWED);
        }
    }
    
    public void setStoryStatus(StoryStatus storyStatus, String pass) {
        if (pass.equals("AlongPassWordWithMultiple$(&@#(T##RUEWEJF")) {
        String previous = String.valueOf(this.storyStatus);
        this.storyStatus = storyStatus;
        workItemsHistory.add(String.format("Bug severity was changed from %s to %s. - %s",
                previous, storyStatus, LocalDateTime.now().format(formatter)));
    } else {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_PASS_NOT_ALLOWED);
        }
    }

    public String additionalInfo(){
        return String.format(
                "%s [Size: %s] [Status: %s] [Priority: %s] [Assignee: %s]%n" +
                        "Description: %n%s%n" +
                        "%s",
                super.toString(),
                getSize(),getStoryStatus(),getPriority(),getAssignee(),
                getDescription(),
                printComments());
    }

    public String printHistory(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : workItemsHistory) {
            stringBuilder.append(s);
            stringBuilder.append("%n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void addComment(String commentWithAuthor) {
        this.storyComments.add(commentWithAuthor);
        this.workItemsHistory.add(String.format("Comment was added. - %s",LocalDateTime.now().format(formatter)));
    }

    public List<String> getStoryComments(){
        return new ArrayList<>(storyComments);
    }

    @Override
    public String printComments() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Comments found: %d%n",this.storyComments.size()));
        this.storyComments.forEach(comment -> stringBuilder.append(comment).append(String.format("%n")));
        return stringBuilder.toString();
    }
}
