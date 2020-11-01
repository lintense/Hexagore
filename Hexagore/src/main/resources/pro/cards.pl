/* HEXAGORE - cards.pl

Defines all the cards that exist. 
Some may not be available during a given game according to the game parms.

card(id,money,name,1).
card(id,monster,name,strength,)
card(id,follower,name,strength,)

card(id,event,name,chaos_level,)

- chaos_level: a number from 1 to 5.
	1 - Low and scattered impact. (All player must do something)
	5 - High and targeted impact. (One player lose a card or gold)
	
*/



card(cb10,money,bronze,1).

card(cf10,follower,squire,1).
card(cf20,follower,sir_talbot,2).

card(cm10,monster,goblin,1).

card(ce10,event,earthquake,1).