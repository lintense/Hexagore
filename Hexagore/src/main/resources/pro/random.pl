/* HEXAGORE - random.pl

Support functions
- logging verbosity set via parms.log_level(LEVEL). 1=Story, 2=Info, 3=Debug (maximum)


*/


log_story(MESSAGE):- log_level(LL),LL>0, write("1> "),writeln(MESSAGE),!;!.
log_story(M1,M2):- log_level(LL),LL>0, write("1> "),write(M1),write(' '),writeln(M2),!;!.
log_info(MESSAGE):- log_level(LL),LL>1, write("2> "),writeln(MESSAGE),!;!.
log_info(M1,M2):- log_level(LL),LL>1, write("2> "),write(M1),write(' '),writeln(M2),!;!.
log_debug(MESSAGE):- log_level(LL),LL>2, write("3> "),writeln(MESSAGE),!;!.
log_debug(M1,M2):- log_level(LL),LL>2, write("3> "),write(M1),write(' '),writeln(M2),!;!.
log_tiny_trace(M):- log_level(LL),LL>3, write("4> "),write(M),!;!.
log_trace(MESSAGE):- log_level(LL),LL>3, write("4> "),writeln(MESSAGE),!;!.
log_trace(M1,M2):- log_level(LL),LL>3, write("4> "),write(M1),write(' '),writeln(M2),!;!.
log_error(MESSAGE):- write("ERROR> "),writeln(MESSAGE),!.
log_error(M1,M2):- write("ERROR> "),write(M1),write(' '),writeln(M2),!.

/*
next_phase_d([game,setup,board,init,trash],X).
X = [game, setup, player].
*/

next_phase_d(PHASE,NEXT):-phase(PHASE,_),all_sub_phases_d(PHASE,[NEXT|_]),!.
next_phase_d(PHASE,NEXT):-phase(PHASE,_),next_sibling_d(PHASE,NEXT),!.

next_sibling_d(PHASE,SIBLING):-
	immediate_parent_d(PHASE,PARENT),
	(all_sub_phases_d(PARENT,SUBS),next_elem_d(PHASE,SUBS,SIBLING),!;
	next_sibling_d(PARENT,SIBLING),!).

immediate_parent_d(PHASE,PARENT):-append(PARENT,[_],PHASE),!.
any_parent(PHASE,PARENT):-append(PARENT,_,PHASE),!.


/* next_elem_d(1,[1,2,3,4],X). X=2 */
next_elem_d(ELEM,[ELEM,NEXT_ELEM|_],NEXT_ELEM):-!.
next_elem_d(ELEM,[_|REST],NEXT_ELEM):-next_elem_d(ELEM,REST,NEXT_ELEM),!.

	
all_sub_phases_d(PHASE,SUBS):-findall(SUB_PHASE, (phase(SUB_PHASE,_), append(PHASE,[_],SUB_PHASE)), SUBS),!.


/*

all_sub_phases_d(PHASE,SUBS):-findall(SUB_PHASE, sub_phase(PHASE,_,SUB_PHASE), SUBS),!.
sub_phase(PHASE,CHILD,CHILD_PHASE):-append([PHASE,[CHILD]],CHILD_PHASE),phase(CHILD_PHASE,_).

all_sub_elem(PHASE,SUBS):-findall(SUB, sub_phase(PHASE,SUB,_), SUBS),!.
parent_phase(PHASE,PARENT,CHILD):-append([PARENT,[CHILD]],PHASE),!.
next_sibling_phase(PHASE,SIBLING,SIBLING_PHASE):-parent_phase(PHASE,PARENT,CHILD),all_sub_elem(PARENT,SUBS),nth0(N,SUBS,CHILD),NN is N+1,nth0(NN,SUBS,SIBLING),!,append([PARENT,[SIBLING]],SIBLING_PHASE).
*/