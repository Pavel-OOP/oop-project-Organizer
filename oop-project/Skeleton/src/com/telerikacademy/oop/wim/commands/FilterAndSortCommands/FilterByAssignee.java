package com.telerikacademy.oop.wim.commands.FilterAndSortCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.MemberImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Map;

public class FilterByAssignee implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final Map<String, MemberImpl> members;

    public FilterByAssignee(Map<String, MemberImpl> members) {
        this.members = members;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String memberName = parameters.get(0);
        return FilterWorkItemsByAssignee(memberName);
    }

    private String FilterWorkItemsByAssignee(String memberName){
        ValidationHelper.checkExistenceMember(memberName, members);
        StringBuilder stringBuilder = new StringBuilder();
        members.get(memberName).getBugs()
                .forEach(bug -> {
                    stringBuilder.append(bug.additionalInfo());
                });
        members.get(memberName).getStories()
                .forEach(story -> {
                    stringBuilder.append(story.additionalInfo());
                });
        if (stringBuilder.toString().isEmpty()){
            return String.format("This member has no work items assigned.");
        }
        return stringBuilder.toString();
    }
}





