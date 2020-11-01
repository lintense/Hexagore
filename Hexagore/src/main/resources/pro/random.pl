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