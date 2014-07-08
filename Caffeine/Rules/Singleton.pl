% (c) Copyright 2001 Yann-Gaël Guéhéneuc
% Ecole des Mines de Nantes and Object Technology Internationl, Inc.
% 
% Use and copying of this software and preparation of derivative works
% based upon this software are permitted. Any copy of this software or
% of any derivative work must include the above copyright notice of
% Yann-Gaël Guéhéneuc, this paragraph and the one after it.
% 
% This software is made available AS IS, and THE AUTHOR DISCLAIMS
% ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
% IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
% PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
% LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
% EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
% NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
% OF THE POSSIBILITY OF SUCH DAMAGES.
% 
% All Rights Reserved.

%-----------------------------------------------------------------------------

findSingletons(S, NS) :-
	constructorsNewClasses([], NC),
	sortOutSingletons(NC, S, NS).

constructorsNewClasses(C, NNC) :-
	nextEvent(
		[generateClassLoadEvent,
		 generateConstructorEntryEvent],
		E),
	isInstantiation(E, C, NC),
	constructorsNewClasses(NC, NNC).
constructorsNewClasses(C, C).

isInstantiation(classLoad(_, CLASSNAME), C, NC) :-
	isInstantiation0(CLASSNAME, C, NC).
isInstantiation(constructorEntry(_, CLASSNAME, _), C, NC) :-
	isInstantiation0(CLASSNAME, C, NC).
isInstantiation(_, C, C).

isInstantiation0(
	N,
	[instantiation(N, NUM) | C],
	[instantiation(N, NNUM) | C]) :-
		NNUM is NUM + 1.
isInstantiation0(N, [CAR | C], [CAR | NC]) :-
	isInstantiation0(N, C, NC).
isInstantiation0(N, [], [instantiation(N, 0)]).

sortOutSingletons(
	[instantiation(N, NUM) | REST],
	[instantiation(N, NUM) | SREST], NSREST) :-
		NUM < 2,
		sortOutSingletons(REST, SREST, NSREST).
sortOutSingletons([C | REST], SREST, [C | NSREST]) :-
	sortOutSingletons(REST, SREST, NSREST).
sortOutSingletons([], [], []).


display([]) :-
	nl.
display([N | CDR]) :-
	write(N), nl,
	display(CDR).
display(TITLE, L) :-
	write('------------------------------'), nl,
	write(TITLE), nl,
	write('------------------------------'), nl,
	display(L).


main(N1, N2) :-
	ms(findSingletons(S, NS), TIME),
	display('List of singletons', S),
	display('List of non-singletons', NS),
	write('Computations running time: '),
	write(TIME),
	write(' ms.'),
	nl.
main(N1, N1).
