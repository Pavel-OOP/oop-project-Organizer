package com.telerikacademy.oop.wim.commands.ListingCommands;

import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.models.MemberImpl;
import com.telerikacademy.oop.wim.models.TeamImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Map;

public class ListAllWorkItemsFromMember implements Command {

    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private Map<String, MemberImpl> members;

    public ListAllWorkItemsFromMember(Map<String, MemberImpl> members) {
        this.members = members;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String memberName = parameters.get(0);
        ValidationHelper.checkExistenceMember(memberName,members);
        return ListAllWorkItems(memberName);
    }

    private String ListAllWorkItems(String memberName){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Bugs found that member is assigned to: %d%n",
                members.get(memberName).getBugs().size()));
        for (int i = 0; i < members.get(memberName).getBugs().size(); i++) {
           stringBuilder.append(members.get(memberName).getBugs().get(i).additionalInfo());
           stringBuilder.append(String.format("%n"));
        }
        stringBuilder.append(String.format("Stories found that member is assigned to: %d%n",
                members.get(memberName).getStories().size()));
        for (int i = 0; i < members.get(memberName).getStories().size(); i++) {
            stringBuilder.append(members.get(memberName).getStories().get(i).additionalInfo());
            stringBuilder.append(String.format("%n"));
        }
        return stringBuilder.toString();
    }

}
