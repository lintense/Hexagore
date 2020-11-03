/* HEXAGORE - states.pl

The game states.
- These states are initialized at the begining of the game starts and are updated during the game.

- It is better to have all the mutators here so we can log these events.
*/

:- dynamic(deck_cards/1).
:- dynamic(market_cards/1).
:- dynamic(card_played/2).

:- dynamic(hex_occupant/2).
:- dynamic(player_object/2).

:- dynamic(current_player/1).
:- dynamic(this_turn_players/1).
:- dynamic(current_phase/1).
:- dynamic(current_action/1).





current_phase([game]).
card_played(mountain_invasion_event,game).
hex_occupant(hex_m1, monster(1)).

insert_occupant(HEX,OCC):-log_debug("Inserting occupant:",[HEX,OCC]),asserta(hex_occupant(HEX,OCC)),!.
retract_occupant(HEX,OCC):-log_debug("Removing occupant:",[HEX,OCC]),retract(hex_occupant(HEX,OCC)),!.
retractall_occupant(HEX,OCC):-log_debug("Removing all occupants:",[HEX,OCC]),retractall(hex_occupant(HEX,OCC)),!.

set_current_player(PLAYER):-log_debug("Setting current player to:",PLAYER),retractall(current_player(_)),asserta(current_player(PLAYER)),!.

set_this_turn_players(PLAYERS):-log_debug("Setting this_turn_players to:",PLAYERS),retractall(this_turn_players(_)),asserta(this_turn_players(PLAYERS)),!.

set_current_phase(PHASE):-log_debug("Setting current phase to:",PHASE),retractall(current_phase(_)),asserta(current_phase(PHASE)),!.

set_current_action(ACTION):-log_debug("Setting current action to:",ACTION),retractall(current_action(_)),asserta(current_action(ACTION)),!.


insert_card_played(CARD,PLAYER):-log_debug("Inserting card played:",[PLAYER,CARD]),asserta(card_played(CARD,PLAYER)),!.
retract_card_played(CARD,PLAYER):-log_debug("Removing card played:",[PLAYER,CARD]),retract(card_played(CARD,PLAYER)),!.
retractall_card_played:-log_debug("Removing all cards played"),retractall(card_played(_,_)),!.

insert_player_object(PLAYER,OBJ):-log_debug("Inserting player object:",[PLAYER,OBJ]),asserta(player_object(PLAYER,OBJ)),!.
retract_player_object(PLAYER,OBJ):-log_debug("Removing player object:",[PLAYER,OBJ]),retract(player_object(PLAYER,OBJ)),!.


