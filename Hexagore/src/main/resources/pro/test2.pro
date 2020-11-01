/*
THIS IS WORKING GREAT!!!
- MISSING NEIGHBORS
- NO NEED TO REPEAT THE WHEN PART IN THE THEN PART!!!

working_directory(CWD,'D:/MY_GIT/Hexagore/Hexagore/src/main/resources/pro').
[parms,random,hexes,phases,cards,states,rules].
['test2.pro'].
go.

swipl -l states.pl phases.pl test2.pro -g go -g halt

rule(X,Y,Z),check_do(Y),write(Z),check_do(Z),fail.


TODO:

Prolog should be able to simulate a game without Java.
But Prolog must give control to Java when a choice or decision can be done.
- Java will invoke Play() giving it full control.

log_debug
log_info
log_story

current_phase(market).
:- dynamic(current_phase/1).

*/
:- dynamic(current_loops/1).

current_loops([]).
set_current_loops(LOOPS):-retractall(current_loops(_)),asserta(current_loops(LOOPS)),!.

go:-
	set_current_phase([game]),
	set_current_loops([]),
	go(1).
go.

go(20):-!.
go(X):-
	current_phase(PHASE),
	log_info("Playing phase: ",PHASE),
	!,next_looped_phase(PHASE,NEXT),
	set_current_phase(NEXT),
	Y is X+1,
	go(Y).

/*
next_looped_phase(PHASE,NEXT):-write("->"),write(PHASE),fail.
*/
next_looped_phase(PHASE,NEXT):-
	current_loops([CURRENT_LOOP|_]),
	next_phase(PHASE,NEXT),
	append(CURRENT_LOOP,_,NEXT).
next_looped_phase(PHASE,NEXT):-
	next_phase(PHASE,NEXT),
	last(NEXT,n),
	current_loops(LOOPS),
	set_current_loops([NEXT|LOOPS]),
	!,
	phase(NEXT,COMMAND),
	(check_do(COMMAND);
	set_current_loops(LOOPS),fail).
next_looped_phase(PHASE,NEXT):-
	current_loops([]),
	next_phase(PHASE,NEXT).
	
	
next_phase(PHASE,NEXT):-all_sub_phases(PHASE,[SUB|PHASES]),!,member(NEXT,[SUB|PHASES]).
	
	/*
next_phase(PHASE,NEXT):-
	next_sibling_phase(PHASE,_,NEXT),write('y'),!.

go:- play('',[game]).
play(DD,PHASE):-
	write('starting phase:'),writeln(PHASE),set_phase(PHASE),
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
	*/
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

choose_from_list(X):-write(X),fail.
*/