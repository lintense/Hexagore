/*
	working_directory(CWD,'D:/Projects/swipl/test/src/main/resources').
	consult('test3.pro').
	next_phase([game],P).
	
	Let's focus on what needs to be done...
	
	- Count allowed moves (acoording to position, cards, etc)
	- Some phase are gatering info (where to store?)
	- Some phase are taking decisions
	- 
	
	
	
*/

phase([game],[]).
phase([game,setup],[]).
phase([game,setup,board],[]).
phase([game,setup,board,init],[]).
phase([game,setup,board,init,draw],[]).
phase([game,setup,board,init,market],[]).
phase([game,setup,board,init,trash],[]).
phase([game,setup,player],[]).
phase([game,setup,player,n],[]).
phase([game,setup,player,n,get],[]).
phase([game,setup,player,n,get,name],[]).
phase([game,setup,player,n,get,crest],[]).
phase([game,setup,player,n,get,village],[]).
phase([game,setup,player,n,init],[]).
phase([game,setup,player,n,init,draw],[]).
phase([game,setup,player,n,init,hand],[]).
phase([game,setup,player,n,init,trash],[]).
phase([game,setup,player,first],[]).
phase([game,turn],[]).
phase([game,turn,n],[]).
phase([game,turn,n,init],[]).
phase([game,turn,n,init,player],[]).
phase([game,turn,n,init,player,reset],[]).
phase([game,turn,n,init,player,reset,hand],[]).
phase([game,turn,n,init,board],[]).
phase([game,turn,n,init,board,reset],[]).
phase([game,turn,n,init,board,reset,market],[]).
phase([game,turn,n,play],[]).
phase([game,turn,n,play,check],[]).
phase([game,turn,n,play,event],[]).
phase([game,turn,n,play,event,n],[]).
phase([game,turn,n,play,event,n,process],[]).
phase([game,turn,n,play,player],[]).
phase([game,turn,n,play,player,first],[]).
phase([game,turn,n,play,player,n],[]).
phase([game,turn,n,play,player,n,action],[]).
phase([game,turn,n,play,player,n,action,a],[sub_phase(P),choose_from_list(action(P))]).
phase([game,turn,n,play,player,n,action,a,buy],[]).
phase([game,turn,n,play,player,n,action,a,move],[]).
phase([game,turn,n,play,player,n,action,a,move,out],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,check],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a],[sub_phase(P),choose_from_list(action(P))]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,round],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,round,n],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,round,n,bonus],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,round,n,bonus,card],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,round,n,bonus,card,n],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,round,n,malus],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,round,n,malus,card],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,round,n,malus,card,n],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,count],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,count,add],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,count,sub],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,count,select],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,count,select,lose],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,count,select,lose,drop],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,count,select,win],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,attack,count,select,win,pick],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,round],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,round,n],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,round,n,bonus],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,round,n,bonus,card],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,round,n,bonus,card,n],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,round,n,malus],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,round,n,malus,card],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,round,n,malus,card,n],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,count],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,outcome],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,outcome,a],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,outcome,a,lose],[]).
phase([game,turn,n,play,player,n,action,a,move,out,monster,a,bribe,outcome,a,win],[]).
phase([game,turn,n,play,player,n,action,a,move,in],[]).
phase([game,turn,n,play,player,n,action,a,trash],[]).
phase([game,turn,n,play,player,n,action,a,pick],[]).
phase([game,turn,n,play,player,n,action,a,drop],[]).
phase([game,turn,n,play,player,n,action,a,attack],[]).
phase([game,turn,n,play,player,n,action,a,attack,a],[sub_phase(P),choose_from_list(entity(P))]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,round],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,round,n],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,round,n,bonus],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,round,n,bonus,card],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,round,n,bonus,card,n],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,round,n,malus],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,round,n,malus,card],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,round,n,malus,card,n],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,count],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,outcome],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,outcome,lose],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,outcome,lose,drop],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,outcome,win],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,opponent,outcome,win,pick],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,round],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,round,n],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,round,n,bonus],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,round,n,bonus,card],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,round,n,bonus,card,n],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,round,n,malus],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,round,n,malus,card],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,round,n,malus,card,n],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,count],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,outcome],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,outcome,a],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,outcome,a,lose],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,outcome,a,lose,drop],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,outcome,a,win],[]).
phase([game,turn,n,play,player,n,action,a,attack,a,monster,outcome,a,win,pick],[]).
phase([game,turn,n,play,player,n,action,a,card],[]).
phase([game,turn,n,play,player,n,action,a,hero],[]).
phase([game,turn,n,play,player,n,action,a,pass],[]).
phase([game,turn,n,play,player,done],[]).
phase([game,turn,n,play,market],[]).
phase([game,turn,n,play,market,monster],[]).
phase([game,turn,n,play,market,monster,n],[]).
phase([game,turn,n,play,market,monster,n,place],[]).
phase([game,turn,n,reset],[]).
phase([game,turn,n,reset,first],[]).

next_elem(LIST,ELEM,NEXT):-nth0(N,LIST,ELEM),NN is N+1,nth0(NN,LIST,NEXT).
sub_phase(PHASE,CHILD,CHILD_PHASE):-append([PHASE,[CHILD]],CHILD_PHASE),phase(CHILD_PHASE,_).
all_sub_elem(PHASE,SUBS):-findall(SUB, sub_phase(PHASE,SUB,_), SUBS),!.

parent_phase(PHASE,PARENT,CHILD):-append([PARENT,[CHILD]],PHASE),!.
next_sibling_phase(PHASE,SIBLING,SIBLING_PHASE):-parent_phase(PHASE,PARENT,CHILD),all_sub_elem(PARENT,SUBS),nth0(N,SUBS,CHILD),NN is N+1,nth0(NN,SUBS,SIBLING),!,append([PARENT,[SIBLING]],SIBLING_PHASE).

next_phase([done|PHASE],NEXT_PHASE):-next_sibling_phase(PHASE,_,NEXT_PHASE),!;parent_phase(PHASE,PARENT,_),NEXT_PHASE = [done|PARENT],!.
next_phase(PHASE,NEXT_PHASE):-sub_phase(PHASE,_,NEXT_PHASE),!;next_sibling_phase(PHASE,_,NEXT_PHASE),!;parent_phase(PHASE,PARENT,_),NEXT_PHASE = [done|PARENT],!.

/*
next_sub_phase([done|PHASE],NEXT,NEXT_PHASE)
next_sub_phase(PHASE,NEXT,NEXT_PHASE):-parent_phase(PHASE,PARENT,CHILD),all_sub_elem(PARENT,SUBS),next_elem(SUBS,CHILD,NEXT),parent_phase(NEXT_PHASE,PARENT,NEXT),!.

 next_phase(PHASE,NEXT):-append([PARENT,[SUB]],PHASE)
 findall(Templ, Goal, Bag)
*/
