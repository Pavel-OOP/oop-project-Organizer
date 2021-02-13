# oop-project-Organizer<br/>
Creating the back-end logic of an Organizer, similar to Trello<br/>
<br/>
These are some example commands(commands are case insensitive and written in the console:)<br/>

createteam TeamRocket<br/>
createboard TeamRocket board1 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (Board must be assigned to an existing team)<br/>
createmember Pavel<br/>
createmember Monika<br/>
addMemberToTeam TeamRocket Pavel &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (An existing member should be added in an existing team)<br/>
addmembertoteam TeamRocket Monika<br/>
createbug TeamRocket board1 video_player_crashing<br/>
(here the program will ask for description, write it on one line please, it should be between 10 and 500 characters then press enter. Example below)<br/>
When I try to open a video on the site, I get an error!<br/>
(now the program will ask for steps to reproduce, enter each step on new line and after you are finished type "finish". Example below)<br/>
enter the site<br/>
click on the first video<br/>
click play<br/>
finish<br/>
(you will be asked for priority, severity, status and assignee for the bug. Priority:HIGH, MEDIUM, LOW || Severity:CRITICAL, MAJOR, MINOR || Status: ACTIVE, FIXED)
high critical active Monika<br/>
createstory TeamRocket board1 fix_the_crashing_video<br/>
(story is like a task that must be assigned to someone. You will be asked for description. Example below)<br/>
Pavel, go check and test if the video player is fixed!<br/>
(you will be asked for priority, size, status and assignee, Priority:HIGH, MEDIUM, LOW || Size:LARGE, MEDIUM, SMALL || Status:NOTDONE, INPROGRESS, DONE)<br/>
high medium notdone Pavel<br/>
createfeedback TeamRocket board1 screen_issue_feeback<br/>
(you will be asked for desription)<br/>
There was a bug issued to Monika and after an hour the bug still persists, waiting for Pavel to say when the tests are successful and the bug is resolved!- from Managment<br/>
(you will be asked for rating and status. Rating should be between 0 and 10, Status:NEW, UNSCHEDULED, SCHEDULED, DONE. Example below)<br/>
2 new<br/>
addcommenttobug TeamRocket board1 video_player_crashing<br/>
(you will be asked for a comment)<br/>
It has been 2 hours now I still get the bug! What a stupid site, I'm unsubscribing!<br/>
(now you will be asked for the name of the author if this comment, it can be whatever pseoudonim the user wants)<br/>
ImpatientUser<br/>


