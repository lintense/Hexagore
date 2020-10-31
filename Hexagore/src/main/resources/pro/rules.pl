/*
THIS IS WORKING GREAT!!!
- MISSING NEIGHBORS
- NO NEED TO REPEAT THE WHEN PART IN THE THEN PART!!!

-- Set Notepad language to Visual Prolog
working_directory(CWD,'D:/MY_GIT/Hexagore/Hexagore/src/main/resources/pro').

consult(test1).
rule(X,Y,Z),check_do(Y),write(Z),check_do(Z),fail.


check_do([member(HEX,[hex_m1,hex_m2,hex_m3])]).

	[hex_type(HEX,mountain), not(hex_occupant(HEX, monster(_))),member(M,[1,2,3]), hex(HEX),monster(M)]).
	
	,not(hex_occupant(HEX, monster(_))),not(member(HEX,[hex_m1,hex_m2,hex_m3])),member(M,[1,2,3]),choose_from_list([hex(HEX),monster(M)])
	
	check_do([choose_from_list(X)|_]):- write(choose_from_list(X)),fail_
	
// all phases	
	
	
*/
:- dynamic(hex_occupant/2).

perm_to_move().

hex_occupant(hex_m1, monster(1)).

// Game rule
rule(move_phase, [perm_to_move(),card_played(_,player)], [move_count(move_card)]).
rule(hex_fougue, [perm_to_move(),origin(village),not(destination(_))],[move_count(hex_fougue)]).


// Game companion cards
rule(sir_talbot, [perm_to_move(),card_played(sir_talbot,player)],[move_count(card_sir_talbot),attack_count(card_sir_talbot,player)]).

// Game event cards
rule(market_closed_event,[phase(market),card_played("market.closed",game)]).
rule(dragon_event, [card_played("dragon.event",game)],[retractall(hex_occupant(_,gold(_)),retractall(player_object(_, gold(_)))]).
rule(auction_event, [card_played("auction.event",game)],[retract(phase(_)),insert_phase("auction.phase")]).

rule(earthquake_event, [card_played("earthquake.event",game)], [player_hex(P, HEX), retract(hex_occupant(HEX, gold(N2))), retract(player_object(P, gold(N1))),add(N1,N2,T), assert(hex_occupant(HEX, gold(T)))]).
rule(earthquake_event, [card_played("earthquake.event",game)], [player_hex(P, HEX), not(hex_occupant(HEX, gold(_))), retract(player_object(P, gold(T))),assert(hex_occupant(HEX, gold(T)))]).
rule(siren_event, [card_played("siren.event",game)], [current_direction(DIR), retract(player_hex(P,HEX)),hex_height(HEX,H),hex_limit(HEX,DIR,LIMIT),assert(player_hex(P,LIMIT))]).




rule(mutation_event, [card_played(mutation_event,game)], [hex_occupant(HEX, monster(M)),choose_from_list([hex(HEX),monster(M)])]).
rule(mountain_invasion_event, [card_played(mountain_invasion_event,game)], [member(HEX,[hex_m1,hex_m2,hex_m3]),not(hex_occupant(HEX, monster(_))),missing_mountain_monster(M),choose_from_list([hex(HEX),monster(M)])]).
rule(mountain_invasion_event, [card_played(mountain_invasion_event,game),hex_type(HEX,mountain),not(hex_occupant(HEX, monster(_))),not(member(HEX,[hex_m1,hex_m2,hex_m3]))],
	[member(M,[1,2,3]),choose_from_list([hex(HEX),monster(M)])]).


check([]):- !.
check([X|Y]):- clause(X,_),check(Y).

apply_effects([], A, B):- write(A), retract(A), write(' being replaced by '), write(B), assertz(B),!.
apply_effects([X|Y], A, B):- clause(X,_),!,apply_effects(Y, A, B).

check_do([]):- !.
check_do([X|_]):- write('check_do:'),write(X),fail.
check_do([not(member(X1,X2))|Y]):- !,\+ member(X1,X2),write(not(member(X1,X2))),write('ok'),nl,check_do(Y).
check_do([not(X)|Y]):- !,\+ clause(X,_),write(not(X)),write('ok'),nl,check_do(Y).
check_do([member(X1,X2)|Y]):- !,member(X1,X2),write(member(X1,X2)),write('ok'),nl,check_do(Y).
check_do([missing_mountain_monster(M)|Y]):- !,missing_mountain_monster(M),write(missing_mountain_monster(M)),write('ok'),nl,check_do(Y).

check_do([X|Y]):-clause(X,_),write(X),write('ok'),nl,check_do(Y).

missing_mountain_monster(M):- member(M,[1,2,3]),\+ hex_occupant(hex_m1, monster(M)),\+ hex_occupant(hex_m2, monster(M)),\+ hex_occupant(hex_m3, monster(M)).


extreme(HEIGHT,DIR, HEX):- findall(H,hex_height(H,HEIGHT),L), sort(L,LL),(DIR = left -> LL = [HEX|_] ; last(LL,HEX)).
