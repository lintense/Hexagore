/*
THIS IS WORKING GREAT!!!
- MISSING NEIGHBORS
- NO NEED TO REPEAT THE WHEN PART IN THE THEN PART!!!

working_directory(CWD,'D:/MY_GIT/Hexagore/Hexagore/src/main/resources/pro').
[parms,random,hexes,phases,cards,states,rules,'test2.pro'].
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
:- dynamic(counter/2).

current_loops([]).
set_current_loops(LOOPS):-log_debug("Setting current loops to:",LOOPS),retractall(current_loops(_)),asserta(current_loops(LOOPS)),!.
set_counter(PHASE,COUNT):-log_debug("Setting counter to:",[PHASE,COUNT]),retractall(counter(PHASE,_)),asserta(counter(PHASE,COUNT)),!.
increase_counter(PHASE):-counter(PHASE,COUNT),NEW_COUNT is COUNT+1,set_counter(PHASE,NEW_COUNT),!.


go:-
	set_current_phase([game]),
	set_current_loops([]),
	players(PLAYERS),
	set_this_turn_players(PLAYERS),
	go(1).
go.


go(200):-!.
go(X):-
	current_phase(PHASE),
	log_info("Playing phase: ",PHASE),
	!,compute_next_phase(PHASE,NEXT),
	set_current_phase(NEXT),
	Y is X+1,
	go(Y),!.


compute_next_phase(PHASE,_):-log_trace("Get phase after:",PHASE),fail.
compute_next_phase(_,_):-current_loops(X),log_trace("Current loops:",X),fail.
compute_next_phase(PHASE,NEXT):-
	current_loops([PARENT|LOOPS]),write(1),
	any_parent(PHASE,PARENT),write(2),
	next_phase_d(PHASE,NOT_NEXT),write(3),
	not(any_parent(NOT_NEXT,PARENT)),write(4),
	!,(
		current_action(ACTION),
		last(PARENT,ACTION),
		set_current_loops(LOOPS),
		immediate_parent_d(PARENT,GRAND_PARENT),
		next_sibling_d(GRAND_PARENT,NEXT),
		log_tiny_trace(cnp1)
	;
		last(PARENT,n),
		phase(PARENT,COMMAND),
		(
			increase_counter(PARENT),
			check_do(COMMAND),
			next_phase_d(PARENT,NEXT),
			log_tiny_trace(cnp2)
		;
			set_current_loops(LOOPS),
			NEXT = NOT_NEXT,
			log_tiny_trace(cnp3)
		)
	),!.
compute_next_phase(PHASE,NEXT):-
	current_loops([PARENT|LOOPS]),write(1),
	any_parent(PHASE,PARENT),write(2),
	not(next_phase_d(PHASE,_)),
	!,(
		last(PARENT,n),
		phase(PARENT,COMMAND),
		(
			increase_counter(PARENT),
			check_do(COMMAND),
			next_phase_d(PARENT,NEXT),
			log_tiny_trace(cnp11)
		;
			set_current_loops(LOOPS),
			log_tiny_trace(cnp12),
			!,fail
		)
	),!.
compute_next_phase(PHASE,NEXT):-	
	next_phase_d(PHASE,NEXT),
	current_loops(LOOPS),
	(
		last(NEXT,n),
		set_current_loops([NEXT|LOOPS]),
		set_counter(NEXT,0),
		log_tiny_trace(cnp4)
	;
		last(NEXT,a),
		set_current_loops([NEXT|LOOPS]),
		log_tiny_trace(cnp5)
	),!.
compute_next_phase(PHASE,NEXT):-
	current_loops([PHASE|LOOPS]),
	last(PHASE,n),
	phase(PHASE,COMMAND),
	(
		increase_counter(PHASE),
		check_do(COMMAND),
		next_phase_d(PHASE,NEXT),
		log_tiny_trace(cnp6)
	;
		set_current_loops(LOOPS),
		immediate_parent_d(PHASE,GRAND_PARENT),
		next_sibling_d(GRAND_PARENT,NEXT),
		log_tiny_trace(cnp7)
	),!.
compute_next_phase(PHASE,NEXT):-
	current_loops([PHASE|LOOPS]),
	last(PHASE,a),
	phase(PHASE,COMMAND),
	(
		check_do(COMMAND),
		current_action(ACTION),
		log_trace("Current action:",ACTION),
		append(PHASE,[ACTION],NEXT),
		phase(NEXT,_),
		set_current_loops([NEXT|LOOPS]),
		log_tiny_trace(cnp8)
	;
		log_error("Failed command for phase:", PHASE),
		log_info("Player passes!"),
		immediate_parent_d(PHASE,GRAND_PARENT),
		next_sibling_d(GRAND_PARENT,NEXT),
		log_tiny_trace(cnp9)
	),!.
compute_next_phase(PHASE,NEXT):-next_phase_d(PHASE,NEXT),log_tiny_trace(cnp10),!.


/*
next_looped_phase(PHASE,NEXT):-write("->"),write(PHASE),fail.
*/
next_looped_phase(_,_):-current_loops(X),log_trace("Current loops:",X),fail.
next_looped_phase(PHASE,_):-log_trace("Get phase after:",PHASE),fail.


next_looped_phase(PHASE,NEXT):-write(x1),
	next_phase_d(PHASE,NEXT),
	current_loops(LOOPS),
	phase(NEXT,COMMAND),
	(
	last(NEXT,n),
	set_current_loops([NEXT|LOOPS]),
	check_do(COMMAND),
	!;
	last(NEXT,a),
	append(NEXT,COMMAND,NEXT_ACTION),write(NEXT_ACTION),
	set_current_loops([NEXT_ACTION|LOOPS]),
	!;
	LOOPS = [CURRENT_LOOP|_],
	append(CURRENT_LOOP,_,NEXT),
	!;
	set_current_loops(LOOPS),
	!).
	
next_looped_phase(PHASE,NEXT):-
	current_loops([]),
	next_phase_d(PHASE,NEXT),!.
	

	/*
	
next_phase(PHASE,NEXT):-all_sub_phases(PHASE,[SUB|PHASES]),!,member(NEXT,[SUB|PHASES]).
	

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

next_looped_phase(PHASE,_):-
	next_phase(PHASE,NEXT),
	current_loops([CURRENT_LOOP|LOOPS]),
	not(append(CURRENT_LOOP,_,NEXT)),
	set_current_loops(LOOPS),
	fail.

:- dynamic(current_actions/1).
current_actions([]).
set_current_actions(ACTIONS):-retractall(current_actions(_)),asserta(current_actions(ACTIONS)),!.

next_looped_phase(PHASE,NEXT):-write(x1),
	next_phase(PHASE,NEXT),
	last(NEXT,n),
	current_loops(LOOPS),
	set_current_loops([NEXT|LOOPS]),
	phase(NEXT,COMMAND),
	!,check_do(COMMAND).
	
next_looped_phase(PHASE,NEXT):-write(x2),
	next_phase(PHASE,NEXT),
	last(NEXT,a),
	current_loops(LOOPS),
	phase(NEXT,[ACTION]),
	append(NEXT,[ACTION],NEXT_ACTION),write(NEXT_ACTION),
	set_current_loops([NEXT_ACTION|LOOPS]),
	!.
	
next_looped_phase(PHASE,NEXT):-write(x3),
	current_loops([CURRENT_LOOP|LOOPS]),
	next_phase(PHASE,NEXT),
	last(NEXT,LAST),not(member(LAST,[a,n])),
	(append(CURRENT_LOOP,_,NEXT);
	not(append(CURRENT_LOOP,_,NEXT)),
	write(NEXT),
	set_current_loops(LOOPS)).
	

next_looped_phase(PHASE,NEXT):-write(x4),
	current_loops([]),
	next_phase(PHASE,NEXT).

*/