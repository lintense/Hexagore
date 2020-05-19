/*
THIS IS WORKING GREAT!!!
- MISSING NEIGHBORS
- NO NEED TO REPEAT THE WHEN PART IN THE THEN PART!!!

working_directory(CWD,'D:/MY_GIT/Hexagore/doc').
consult(test1).
rule(X,Y,Z),check_do(Y),write(Z),check_do(Z),fail.


check_do([member(HEX,[hex_m1,hex_m2,hex_m3])]).

	[hex_type(HEX,mountain), not(hex_occupant(HEX, monster(_))),member(M,[1,2,3]), hex(HEX),monster(M)]).
	
	,not(hex_occupant(HEX, monster(_))),not(member(HEX,[hex_m1,hex_m2,hex_m3])),member(M,[1,2,3]),choose_from_list([hex(HEX),monster(M)])
	
	check_do([choose_from_list(X)|_]):- write(choose_from_list(X)),fail_
	goal(Q):- rule(X,Y,Z),check_do(Y),check_do(Z),Q=choose_from_list([hex(HEX),monster(M)]), member(Q,Z).
*/
:- dynamic(card_played/2).
:- dynamic(choose_from_list/1).
:- dynamic(current_phase/1).

current_phase(market).

set_phase(X):-retract(current_phase(_)),asserta(current_phase(X)).

card_played(mountain_invasion_event,game).

choose_from_list(X):-write(X),fail.

