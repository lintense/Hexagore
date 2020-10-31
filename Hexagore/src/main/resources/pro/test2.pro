/*
THIS IS WORKING GREAT!!!
- MISSING NEIGHBORS
- NO NEED TO REPEAT THE WHEN PART IN THE THEN PART!!!

working_directory(CWD,'D:/MY_GIT/Hexagore/Hexagore/src/main/resources/pro').
['test2.pro'].
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



go:- play('',[game]).
play(DD,PHASE):-
	write('starting phase:'),writeln(PHASE),
	last(PHASE,n),all_sub_elem(PHASE,SIBLING),!,play_all(DD,PHASE,SIBLING).
play(DD,PHASE):- 
	all_sub_elem(PHASE,[CHILD|LIST]),play_all(DD,PHASE,[CHILD|LIST]).
play(DD,PHASE):- 
	next_sibling_phase(PHASE,_,SIBLING_PHASE),write(DD),!,play(DD,SIBLING_PHASE).

play_all(DD,PARENT,[CHILD|REST]):-
	string_concat(DD,'  ',DDD),
	append(PARENT,[CHILD],PHASE),
	write(DDD),play(DDD,PHASE),!,
	play_all(DD,PARENT,REST),!.
	
/*
parent_phase([game,setup],P,C).

?- all_sub_elem([game,setup],SUBS).
SUBS = [board, player].

?- next_sibling_phase([game,setup],SIBLING,SIBLING_PHASE).
SIBLING = turn,
SIBLING_PHASE = [game, turn].


next_sibling_phase([game,turn,n,play,event,n,process],SIBLING,SIBLING_PHASE).

-- We want to send the game event to a prolog method the same way Java would send it.
go:- play([game]).
play(PHASE):-
	write('starting phase:'),writeln(PHASE),
	last(PHASE,n),!,all_sub_elem(PHASE,SIBLING),play_all(PHASE,SIBLING).

play_all(PARENT,[CHILD|REST]):-
	play(PARENT|[CHILD]),!,
	play_all(PARENT,REST).

-- OUT=parent,sibling,child
-- 
play(PHASE,OUT):-


?- next_phase([done,game,setup],X).
X = [game, turn].

?- next_phase([game,setup],X).
X = [game, setup, board].

next_phase([game],X),writeln(X),fail.
next_phase([game],_),writeln(X),fail.
*/