/*
THIS IS WORKING GREAT!!!
- MISSING NEIGHBORS
- NO NEED TO REPEAT THE WHEN PART IN THE THEN PART!!!

working_directory(CWD,'D:/MY_GIT/Hexagore/doc').
consult(test1).

rule(X,Y,Z),check_do(Y),writeln(Z),check_do(Z),fail.

check_do([member(HEX,["hex.m1","hex.m2","hex.m3"])]).

	[hex_type(HEX,mountain), not(hex_occupant(HEX, monster(_))),member(M,[1,2,3]), hex(HEX),monster(M)]).
	
	,not(hex_occupant(HEX, monster(_))),not(member(HEX,["hex.m1","hex.m2","hex.m3"])),member(M,[1,2,3]),choose_from_list([hex(HEX),monster(M)])
	
	check_do([choose_from_list(X)|_]):- writeln(choose_from_list(X)),fail.
*/
:- dynamic card_played/2.
card_played("mountain.invasion.event",game).

hex_type("hex.m1",mountain).
hex_type("hex.m2",mountain).
hex_type("hex.m3",mountain).
hex_type("hex.f3",mountain).

hex_neb("hex.m1","hex.m2").
hex_neb("hex.m2","hex.m3").
hex_neb("hex.m2","hex.f3").


hex_occupant("hex.m1", monster(1)).

rule("mutation.event", [card_played("mutation.event",game)], [hex_occupant(HEX, monster(M)),choose_from_list([hex(HEX),monster(M)])]).
rule("mountain.invasion.event", [card_played("mountain.invasion.event",game)], [member(HEX,["hex.m1","hex.m2","hex.m3"]),not(hex_occupant(HEX, monster(_))),missing_mountain_monster(M),choose_from_list([hex(HEX),monster(M)])]).
rule("mountain.invasion.event", [card_played("mountain.invasion.event",game),hex_type(HEX,mountain),not(hex_occupant(HEX, monster(_))),not(member(HEX,["hex.m1","hex.m2","hex.m3"]))],
	[member(M,[1,2,3]),choose_from_list([hex(HEX),monster(M)])]).


check([]):- !.
check([X|Y]):- clause(X,_),check(Y).

apply_effects([], A, B):- write(A), retract(A), write(' being replaced by '), writeln(B), assertz(B),!.
apply_effects([X|Y], A, B):- clause(X,_),!,apply_effects(Y, A, B).

check_do([]):- !.
check_do([X|_]):- write('check_do:'),writeln(X),fail.
check_do([not(member(X1,X2))|Y]):- !,not(member(X1,X2)),write(not(member(X1,X2))),writeln('ok'),check_do(Y).
check_do([not(X)|Y]):- !,not(clause(X,_)),write(not(X)),writeln('ok'),check_do(Y).
check_do([member(X1,X2)|Y]):- !,member(X1,X2),write(member(X1,X2)),writeln('ok'),check_do(Y).
check_do([missing_mountain_monster(M)|Y]):- !,missing_mountain_monster(M),write(missing_mountain_monster(M)),writeln('ok'),check_do(Y).

check_do([X|Y]):-clause(X,_),write(X),writeln('ok'),check_do(Y).

choose_from_list(X):-writeln(X),fail.

missing_mountain_monster(M):- member(M,[1,2,3]),not(hex_occupant("hex.m1", monster(M))),not(hex_occupant("hex.m2", monster(M))),not(hex_occupant("hex.m3", monster(M))).


extreme(HEIGHT,DIR, HEX):- findall(H,hex_height(H,HEIGHT),L), sort(L,LL),(DIR = left -> LL = [HEX|_] ; last(LL,HEX)).