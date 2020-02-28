# SRCDS-Easy-start
A basic open-source Java application built for starting nearly any Source Engine game automatically, as a console.

###### Function

Starts a basic SRCDS instance with the arguments provided from the user. Can be started silently with command line arguments itself stated below
All arguments must be separated with a semi-colon after completed, but they do not have to be in any particular order.
A complete argument, unless it has no sub-arguments, looks like ex:text;
Currently, the only argument which does not need any text is silent;

Silent command line start argument; Should the server be started automatically by java?
This is required if you want to use the arguments below. This does not have to be the first argument.
silent;

Supports optional arguments for ALL games, not required to start silently or with the GUI.
oargs:<extra arguments for SRCDS itself>;

Required arguments (In no particular order) :
Game folder name: Where all the game content is stored (ex: hl2mp, garrysmod, tf2)
gt:<FOLDER name>;

Server name itself; What will the server be called?
nm:<name>;

Max player count; How many players can connect at a time?
mp:<number>;

Name of map; What map shall the server start on?
map:<name>;

Required for Garry's Mod to start.
Do not include if you are starting any other game. (They'll just waste memory)

Gamemode name; What gamemode will Garry's Mod start on?
gm:<Gamemode FOLDER name>;
(Ex: terrortown = ttt, sandbox = Sandbox)

Workshop ID; the ID of the collection which Garry's Mod will download and start.
ws:<ID, is a long number>;

