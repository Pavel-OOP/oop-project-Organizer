package com.telerikacademy.oop.wim.core.factories;

import com.telerikacademy.oop.wim.commands.AddCommands.AddCommentToBug;
import com.telerikacademy.oop.wim.commands.AddCommands.AddCommentToFeedback;
import com.telerikacademy.oop.wim.commands.AddCommands.AddCommentToStory;
import com.telerikacademy.oop.wim.commands.AddCommands.AddMemberToTeam;
import com.telerikacademy.oop.wim.commands.ChangeCommands.*;
import com.telerikacademy.oop.wim.commands.CreationCommands.*;
import com.telerikacademy.oop.wim.commands.FilterAndSortCommands.*;
import com.telerikacademy.oop.wim.commands.ListingCommands.*;
import com.telerikacademy.oop.wim.commands.Messages.ErrorMessages;
import com.telerikacademy.oop.wim.commands.contracts.Command;
import com.telerikacademy.oop.wim.commands.enums.CommandType;
import com.telerikacademy.oop.wim.core.WIMRepositoryImpl;
import com.telerikacademy.oop.wim.core.contracts.CommandFactory;


public class CommandFactoryImpl implements CommandFactory {

    /* This is where all commands will be. Some will go to next class and stop there
     after completing their task (example: ListAllTeams, it lists all teams and stops action after that)
     Some commands will go to a class
     */

    /* Some classes will move past that point to call CreationFactoryImpl where all needed objects will be created.
    Needed objects are team,member,board,bug,story,feedback.
     */

    /* CommandFactory does create objects but it is done for the sake of being able to use methods.
    (example: ListAllTeams class creates an instance in order to be able to read wimRepository.)
    CommandFactory sends wimRepository to ListAllTeams class */

    /* When data is sent from this class it is not always necessary to send the whole repository.
    It can use the methods from WIMRepository interface or the subclass(WimRepositoryImpl) to send specific data.
    For example it ListAllTeams(wimRepository.getTeams()) will send only the teams list to the ListAllTeams class.
     */

    @Override
    public Command createCommand(String commandTypeAsString, CommandFactoryImpl commandFactory, WIMRepositoryImpl wimRepository) {
        try {
            CommandType commandType = CommandType.valueOf(commandTypeAsString.toUpperCase());
            switch (commandType) {
                case CREATETEAM:
                    return new CreateTeam(wimRepository);
                case LISTALLTEAMS:
                    return new ListAllTeams(wimRepository.getTeams());
                case CREATEMEMBER:
                    return new CreateMember(wimRepository);
                case CREATEBOARD:
                    return new CreateBoard(wimRepository);
                case CREATEBUG:
                    return new CreateBug(wimRepository);
                case CREATESTORY:
                    return new CreateStory(wimRepository);
                case CREATEFEEDBACK:
                    return new CreateFeedback(wimRepository);
                case ADDMEMBERTOTEAM:
                    return new AddMemberToTeam(wimRepository);
                case LISTALLWORKITEMSFROMBOARD:
                    return new ListAllWorkItemsFromBoard(wimRepository.getTeams());
                case LISTALLWORKITEMSFROMMEMBER:
                    return new ListAllWorkItemsFromMember(wimRepository.getMembers());
                case LISTALLMEMBERS:
                    return new ListAllMembers(wimRepository.getMembers());
                case LISTMEMBERACTIVITY:
                    return new ListMemberActivity(wimRepository);
                case LISTTEAMACTIVITY:
                    return new ListTeamActivity(wimRepository.getTeams());
                case LISTALLTEAMBOARDS:
                    return new ListAllTeamBoards(wimRepository.getTeams());
                case LISTALLTEAMMEMBERS:
                    return new ListAllTeamMembers(wimRepository.getTeams());
                case LISTBOARDACTIVITY:
                    return new ListBoardActivity(wimRepository.getTeams());
                case CHANGEBUGPRIORITY:
                    return new ChangeBugPriority(wimRepository.getTeams());
                case CHANGEBUGSEVERITY:
                    return new ChangeBugSeverity(wimRepository.getTeams());
                case CHANGEBUGSTATUS:
                    return new ChangeBugStatus(wimRepository.getTeams());
                case CHANGESTORYPRIORITY:
                    return new ChangeStoryPriority(wimRepository.getTeams());
                case CHANGESTORYSIZE:
                    return new ChangeStorySize(wimRepository.getTeams());
                case CHANGESTORYSTATUS:
                    return new ChangeStoryStatus(wimRepository.getTeams());
                case CHANGEFEEDBACKSTATUS:
                    return new ChangeFeedbackStatus(wimRepository.getTeams());
                case CHANGEFEEDBACKRATING:
                    return new ChangeFeedbackRating(wimRepository.getTeams());
                case ADDCOMMENTTOFEEDBACK:
                    return new AddCommentToFeedback(wimRepository.getTeams());
                case ADDCOMMENTTOBUG:
                    return new AddCommentToBug(wimRepository.getTeams());
                case ADDCOMMENTTOSTORY:
                    return new AddCommentToStory(wimRepository.getTeams());
                case ASSIGNWORKITEMTOMEMBER:
                    return new AssignWorkItemToMember(wimRepository);
                case UNASSIGNWORKITEMFROMMEMBER:
                    return new UnassignWorkItemFromMember(wimRepository);
                case LISTALLBUGS:
                    return new ListAllBugs(wimRepository);
                case LISTALLSTORIES:
                    return new ListAllStories(wimRepository);
                case LISTALLFEEDBACK:
                    return new ListAllFeedback(wimRepository);
                case FILTERBYSTATUS:
                    return new FilterByStatus(wimRepository);
                case FILTERBYASSIGNEE:
                    return new FilterByAssignee(wimRepository.getMembers());
                case FILTERBYASSIGNEEANDSTATUS:
                    return new FilterByAssigneeAndStatus(wimRepository.getMembers());
                case SORTBYTITLE:
                    return new SortByTitle(wimRepository);
                case SORTBYPRIORITY:
                    return new SortByPriority(wimRepository);
                case SORTBYSEVERITY:
                    return new SortBySeverity(wimRepository);
                case SORTBYSIZE:
                    return new SortBySize(wimRepository);
                case SORTBYRATING:
                    return new SortByRating(wimRepository);
            }
        }catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(ErrorMessages.INVALID_COMMAND, commandTypeAsString));
        }
        throw new IllegalArgumentException(String.format(ErrorMessages.INVALID_COMMAND, commandTypeAsString));
    }
}
