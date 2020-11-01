/* HEXAGORE - parms.pl

The game parameters.
- These parameter are setup before the game starts and stay the same during the whole game.


- fiery: Player may move an extra hex when leaving its village
- ambush: Player has a +1 when attacking from a mountain hex it started its turn on.
- camouflage: Player has a +1 when defending from a forest hex it started its turn on.
- swiming: Player must to take an extra turn when traversing the river.
- many_bags: Player may pick extra gold bags when not in the mine (+1 card required per move per extra bag).
- shopping: Player must be in the village to buy cards.
- welcome: Player receive an extra card when arriving in its village.

- chaos_level: Determine which event cards should be included in the game.
*/

game_option(fiery).
game_option(ambush).
game_option(camouflage).
game_option(swiming).
game_option(gold_multiple_bags).

log_level(3).
chaos_level(3).
events_per_turn(1).

market_basic_cards(20).
market_player_cards(10).
player_basic_cards(5).

players([a,b,c,d]).