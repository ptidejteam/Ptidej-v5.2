% (c) Copyright 2001 Yann-Gaël Guéhéneuc, Narendra Jussien
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
% PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
% ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
% EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
% NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS
% ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
% 
% All Rights Reserved.

%-----------------------------------------------------------------------------

nextRelationCheck :-
	nextEvent(E),
	E = methodEntry(_, METHODNAME, _, _, CLASSNAME),
	METHODNAME = check,
	CLASSNAME = 'fr.emn.cacao.Relation',
	% write(CLASSNAME),
	% write('.'),
	% write(METHODNAME),
	% write('()'), nl,
	true.

nextVariableRevise :-
	nextEvent(E),
	E = methodEntry(_, METHODNAME, _, _, CLASSNAME),
	METHODNAME = revise,
	CLASSNAME = 'fr.emn.cacao.Variable',
	% write(CLASSNAME),
	% write('.'),
	% write(METHODNAME),
	% write('()'), nl,
	true.

query(N, M) :-
	nextRelationCheck,
	nextVariableRevise,
	N1 is N + 1,
	write(N1), nl,
	query(N1, M).
query(N, N).
	

main(N, M) :-
	ms(query(N, M), TIME),
	write(TIME),
	write(' ms.'),
	nl.
main(N, N).
