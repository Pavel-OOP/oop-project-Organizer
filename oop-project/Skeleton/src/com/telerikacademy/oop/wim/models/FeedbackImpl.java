package com.telerikacademy.oop.wim.models;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.models.contracts.CommentsInterface;
import com.telerikacademy.oop.wim.models.contracts.Feedback;
import com.telerikacademy.oop.wim.models.enums.FeedbackStatus;
import com.telerikacademy.oop.wim.models.enums.Priority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeedbackImpl extends WorkItemsImpl implements Feedback, CommentsInterface {

    private Integer rating;
    private FeedbackStatus feedbackStatus;
    private List<String> feedbackComments;

    public FeedbackImpl(String title,
                        String description,
                        Integer rating,
                        FeedbackStatus feedbackStatus) {
        super(title, description);


        this.feedbackStatus = feedbackStatus;
        setRating(rating);
        feedbackComments = new ArrayList<>();
        super.workItemsHistory = new ArrayList<>();
        workItemsHistory.add(String.format("Feedback created. - %s", LocalDateTime.now().format(formatter)));
    }

    public int getRating() {
        return rating;
    }

    public FeedbackStatus FeedbackStatus() {
        return feedbackStatus;
    }

    public FeedbackStatus getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setStatus(FeedbackStatus status, String pass) {
        if (pass.equals("AlongPassWordWithMultipleFHWE#*)RJE@)$*#(U(URW#UWOU#")) {
        String previous = String.valueOf(this.feedbackStatus);
        this.feedbackStatus = status;
        workItemsHistory.add(String.format("Feedback status was changed from %s to %s. - %s",
                previous, status, LocalDateTime.now().format(formatter)));
    } else {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_PASS_NOT_ALLOWED);
        }
    }

    public String additionalInfo() {
        return String.format(
                "%s [Rating: %s] [Status: %s]%n" +
                        "Description: %n%s%n" +
                        "%s",
                super.toString(),
                getRating(), getFeedbackStatus(), getDescription(),
                printComments());

    }

    public void changeRating(Integer rating, String pass) {
        if (pass.equals("AlongPassWordWithMultipleFHWE#*)RJE@)$*#(U(URW#UWOU#")) {
            int previous = this.rating;
            setRating(rating);
            workItemsHistory.add(String.format("Feedback rating was changed from %d to %d. - %s",
                    previous, rating, LocalDateTime.now().format(formatter)));
        } else {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_PASS_NOT_ALLOWED);
        }
    }

    private void setRating(Integer rating) {
        ValidationHelper.checkRatingConstraints(rating);
        this.rating = rating;
    }

    public String printHistory() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : workItemsHistory) {
            stringBuilder.append(s);
            stringBuilder.append("%n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void addComment(String commentWithAuthor) {
        this.feedbackComments.add(commentWithAuthor);
        this.workItemsHistory.add(String.format("Comment was added. - %s", LocalDateTime.now().format(formatter)));
    }

    public List<String> getFeedbackComments() {
        return new ArrayList<>(feedbackComments);
    }

    @Override
    public String printComments() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Comments found: %d%n", this.feedbackComments.size()));
        this.feedbackComments.forEach(comment -> stringBuilder.append(comment).append(String.format("%n")));
        return stringBuilder.toString();
    }

}
