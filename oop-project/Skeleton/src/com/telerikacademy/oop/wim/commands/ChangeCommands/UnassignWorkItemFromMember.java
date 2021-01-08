package com.telerikacademy.oop.wim.commands.ChangeCommands;

import com.telerikacademy.oop.wim.commands.Messages.ConfirmationMessages;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.models.BugImpl;
import com.telerikacademy.oop.wim.models.StoryImpl;
import com.telerikacademy.oop.wim.models.ValidationHelper;

import java.util.List;
import java.util.Scanner;

public class UnassignWorkItemFromMember implements Command {

    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 3;
    private final WIMRepositoryImpl wimRepository;
    private Scanner user_input = new Scanner(System.in);

    public UnassignWorkItemFromMember(WIMRepositoryImpl wimRepository) {
        this.wimRepository = wimRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException
                    (String.format(ErrorMessages.INVALID_NUMBER_OF_ARGUMENTS, CORRECT_NUMBER_OF_ARGUMENTS));
        }
        String teamName = parameters.get(0);
        String boardName = parameters.get(1);
        String workItemName = parameters.get(2);
        return AssignWorkItemToMemberConsole(teamName, boardName, workItemName);
    }

    public String AssignWorkItemToMemberConsole(String teamName, String boardName, String workItemName) {
        System.out.println(String.format("Work items of type Bug and Story need to have Assignee at all times! %n" +
                "The system will prompt you to choose new Assignee from the current team."));
        ValidationHelper.checkExistenceTeamAndBoard(teamName, boardName, wimRepository.getTeams());
        if (searchStories(workItemName, teamName, boardName) && searchBugs(workItemName, teamName, boardName)) {
            System.out.println("2 Work Items with this name found. 1 bug and 1 story.");
            System.out.println("Which one would you like to unassign?");
            System.out.println("Type bug or story.");
            String choice = user_input.nextLine();
            if (choice.equalsIgnoreCase("bug")) {
                System.out.println("Choose new assignee for this bug.");
                StringBuilder stringBuilder = new StringBuilder();
                wimRepository.getTeams().get(teamName).getAllMembers().forEach((name, team) -> stringBuilder.append(String.format("[ %s ] ", name)));
                System.out.println(stringBuilder.toString());
                String newAssignee = user_input.nextLine();
                ValidationHelper.checkExistenceMember(newAssignee, wimRepository.getMembers());
                copyAndAddBugToNewOwnersList(workItemName, teamName, boardName, newAssignee);
                findBugAndRemoveFromListFromPreviousOwner(workItemName, teamName, boardName);
                wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getBug(workItemName).setAssignee(newAssignee);
            } else if (choice.equalsIgnoreCase("story")) {
                System.out.println("Choose new assignee for this story.");
                StringBuilder stringBuilder = new StringBuilder();
                wimRepository.getTeams().get(teamName).getAllMembers().forEach((name, team) -> stringBuilder.append(String.format("[ %s ] ", name)));
                System.out.println(stringBuilder.toString());
                String newAssignee = user_input.nextLine();
                ValidationHelper.checkExistenceMember(newAssignee, wimRepository.getMembers());
                copyAndAddStoryToNewOwnersList(workItemName, teamName, boardName, newAssignee);
                findStoryAndRemoveFromListFromPreviousOwner(workItemName, teamName, boardName);
                wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getStory(workItemName).setAssignee(newAssignee);
            }
        }
        if (searchBugs(workItemName, teamName, boardName)) {
            System.out.println("Choose new assignee for this bug.");
            StringBuilder stringBuilder = new StringBuilder();
            wimRepository.getTeams().get(teamName).getAllMembers().forEach((name, team) -> stringBuilder.append(String.format("[ %s ] ", name)));
            System.out.println(stringBuilder.toString());
            String newAssignee = user_input.nextLine();
            ValidationHelper.checkExistenceMember(newAssignee, wimRepository.getMembers());
            copyAndAddBugToNewOwnersList(workItemName, teamName, boardName, newAssignee);
            findBugAndRemoveFromListFromPreviousOwner(workItemName, teamName, boardName);
            wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getBug(workItemName).setAssignee(newAssignee);
        } else if (searchStories(workItemName, teamName, boardName)) {
            System.out.println("Choose new assignee for this story.");
            StringBuilder stringBuilder = new StringBuilder();
            wimRepository.getTeams().get(teamName).getAllMembers().forEach((name, team) -> stringBuilder.append(String.format("[ %s ] ", name)));
            System.out.println(stringBuilder.toString());
            String newAssignee = user_input.nextLine();
            ValidationHelper.checkExistenceMember(newAssignee, wimRepository.getMembers());
            copyAndAddStoryToNewOwnersList(workItemName, teamName, boardName, newAssignee);
            findStoryAndRemoveFromListFromPreviousOwner(workItemName, teamName, boardName);
        } else {
            throw new IllegalArgumentException("Something went wrong. Please try again.");
        }
        return String.format(ConfirmationMessages.WORK_ITEM_REASSIGNED);
    }


    private boolean searchStories(String workItemName, String teamName, String boardName) {
        for (int i = 0; i < wimRepository.getTeams().get(teamName).getBoard(boardName).getAllStories().size(); i++) {
            if (workItemName.equalsIgnoreCase(
                    wimRepository.getTeams().get(teamName).getBoard(boardName).getAllStories().get(i).getTitle())) {
                return true;
            }
        }
        return false;
    }

    private boolean searchBugs(String workItemName, String teamName, String boardName) {
        for (int i = 0; i < wimRepository.getTeams().get(teamName).getBoard(boardName).getAllBugs().size(); i++) {
            if (workItemName.equalsIgnoreCase
                    (wimRepository.getTeams().get(teamName).getBoard(boardName).getAllBugs().get(i).getTitle())) {
                return true;
            }
        }
        return false;
    }

    private void findBugAndRemoveFromListFromPreviousOwner(String workItemName, String teamName, String boardName) {
        String previousOwner = wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getBug(workItemName).getAssignee();
        for (int i = 0; i < wimRepository.getTeams().get(teamName).getAllMembers().get(previousOwner).getBugs().size(); i++) {
            if (wimRepository.getTeams().get(teamName).getAllMembers().get(previousOwner).getBugs().get(i).getTitle().equals(workItemName)) {
                wimRepository.getTeams().get(teamName).getAllMembers().get(previousOwner).getBugs().remove(i);
            }
        }


    }

    private void findStoryAndRemoveFromListFromPreviousOwner(String workItemName, String teamName, String boardName) {
        String previousOwner = wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getStory(workItemName).getAssignee();
        for (int i = 0; i < wimRepository.getTeams().get(teamName).getAllMembers().get(previousOwner).getStories().size(); i++) {
            if (wimRepository.getTeams().get(teamName).getAllMembers().get(previousOwner).getStories().get(i).getTitle().equals(workItemName)) {
                wimRepository.getTeams().get(teamName).getAllMembers().get(previousOwner).getStories().remove(i);
            }
        }
    }

    private void copyAndAddBugToNewOwnersList(String workItemName, String teamName, String boardName, String memberName) {
        BugImpl bug = wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getBug(workItemName);
        wimRepository.getTeams().get(teamName).getAllMembers().get(memberName).getBugs().add(bug);
    }

    private void copyAndAddStoryToNewOwnersList(String workItemName, String teamName, String boardName, String memberName) {
        StoryImpl story = wimRepository.getTeams().get(teamName).getAllBoards().get(boardName).getStory(workItemName);
        wimRepository.getTeams().get(teamName).getAllMembers().get(memberName).getStories().add(story);
    }
}
