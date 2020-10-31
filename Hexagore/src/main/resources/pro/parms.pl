/* HEXAGORE

The game parameters.
- These parameter are setup before the game starts and stay the same during the whole game.


- fiery: Player may move an extra hex when leaving its village
- ambush: Player has a +1 when attacking from a mountain hex it started its turn on.
- camouflage: Player has a +1 when defending from a forest hex it started its turn on.
- swiming: Player must to take an extra turn when traversing the river.
- many_bags: Player may pick extra gold bags when not in the mine (+1 card required per move per extra bag).
- shopping: Player must be in the village to buy cards.
- welcome: Player receive an extra card when arriving in its village.

- chaos_level: Determine which event card should be included in the game.
*/

game_options([fiery,ambush,camouflage,crawling,gold_multiple_bags]).
chaos_level(3).
events_per_turn(1).

nb_cards_basic(20).
nb_cards_per_players(10).