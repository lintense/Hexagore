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
:- dynamic(perm_to_move/0).

perm_to_move.


/* Game rule */
rule(move_phase, [perm_to_move,card_played(_,player)], [move_count(move_card)]).
rule(hex_fougue, [perm_to_move,origin(village),not(destination(_))],[move_count(hex_fougue)]).
rule(sir_talbot, [perm_to_move,card_played(sir_talbot,player)],[move_count(card_sir_talbot),attack_count(card_sir_talbot,player)]).
rule(market_closed_event,[],[phase(market),card_played("market.closed",game)]).
rule(dragon_event, [card_played("dragon.event",game)],[retractall_occupant(_,gold(_)),retractall_player_object(_, gold(_))]).
rule(auction_event, [card_played("auction.event",game)],[set_phase("auction.phase")]).
rule(earthquake_event, [card_played("earthquake.event",game)], [player_hex(P, HEX), retract_occupant(HEX, gold(N2)), retract_player_object(P, gold(N1)),add(N1,N2,T), assert(hex_occupant(HEX, gold(T)))]).
rule(earthquake_event, [card_played("earthquake.event",game)], [player_hex(P, HEX), not(hex_occupant(HEX, gold(_))), retract_player_object(P, gold(T)),assert(hex_occupant(HEX, gold(T)))]).
rule(siren_event, [card_played("siren.event",game)], [current_direction(DIR), hex_type_base(HEX,river), retract_occupant(player(P),HEX),hex_height_base(HEX,HEIGHT),hex_limit(NEWHEX,DIR,HEIGHT),insert_occupant(player(P),NEWHEX)]).
rule(mutation_event, [card_played(mutation_event,game)], [hex_occupant(HEX, monster(M)),choose_from_list([hex(HEX),monster(M)])]).
rule(mountain_invasion_event, [card_played(mountain_invasion_event,game)], [member(HEX,[hex_m1,hex_m2,hex_m3]),not(hex_occupant(HEX, monster(_))),missing_mountain_monster(M),choose_from_list([hex(HEX),monster(M)])]).
rule(mountain_invasion_event, [card_played(mountain_invasion_event,game),hex_type(HEX,mountain),not(hex_occupant(HEX, monster(_))),not(member(HEX,[hex_m1,hex_m2,hex_m3]))],
	[member(M,[1,2,3]),choose_from_list([hex(HEX),monster(M)])]).



check_do([]):- !.
check_do([X|_]):- log_debug('check_do:',X),fail.
check_do([not(member(X1,X2))|Y]):- !,\+ member(X1,X2),log_debug(not(member(X1,X2)),'ok'),check_do(Y).
check_do([not(X)|Y]):- !,\+ call(X),log_debug(not(X),'...ok'),check_do(Y).
check_do([member(X1,X2)|Y]):- !,member(X1,X2),log_debug(member(X1,X2),'...ok'),check_do(Y).
check_do([missing_mountain_monster(M)|Y]):- !,missing_mountain_monster(M),log_debug(missing_mountain_monster(M),'ok'),check_do(Y).

check_do([X|Y]):-call(X),log_debug(X,'...ok'),check_do(Y).


missing_mountain_monster(M):- member(M,[1,2,3]),\+ hex_occupant(hex_m1, monster(M)),\+ hex_occupant(hex_m2, monster(M)),\+ hex_occupant(hex_m3, monster(M)).


extreme(HEIGHT,DIR, HEX):- findall(H,hex_height(H,HEIGHT),L), sort(L,LL),(DIR = left -> LL = [HEX|_] ; last(LL,HEX)).

/*
check([]):- !.
check([X|Y]):- clause(X,_),check(Y).

apply_effects([], A, B):- write(A), retract(A), write(' being replaced by '), write(B), assertz(B),!.
apply_effects([X|Y], A, B):- clause(X,_),!,apply_effects(Y, A, B).

*/