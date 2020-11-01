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
:- dynamic(current_phase/1).

current_phase([game]).
card_played(mountain_invasion_event,game).
hex_occupant(hex_m1, monster(1)).

insert_occupant(HEX,OCC):-asserta(hex_occupant(HEX,OCC)),!.
retract_occupant(HEX,OCC):-retract(hex_occupant(HEX,OCC)),!.
retractall_occupant(HEX,OCC):-retractall(hex_occupant(HEX,OCC)),!.

set_current_player(PLAYER):-log_debug("Setting current player to:",PLAYER),retractall(current_player(_)),asserta(current_player(PLAYER)),!.

set_current_phase(PHASE):-log_debug("Setting current phase to:",PHASE),retractall(current_phase(_)),asserta(current_phase(PHASE)),!.

insert_card_played(CARD,PLAYER):-asserta(card_played(CARD,PLAYER)),!.
retract_card_played(CARD,PLAYER):-retract(card_played(CARD,PLAYER)),!.
retractall_card_played:-retractall(card_played(_,_)),!.

insert_player_object(PLAYER,OBJ):-asserta(player_object(PLAYER,OBJ)),!.
retract_player_object(PLAYER,OBJ):-retract(player_object(PLAYER,OBJ)),!.
