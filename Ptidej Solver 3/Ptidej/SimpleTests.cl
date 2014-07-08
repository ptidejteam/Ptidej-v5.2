// (c) Copyright 2000-2002 Yann-Gaël Guéhéneuc,
// Ecole des Mines de Nantes and Object Technology International, Inc.
// 
// Use and copying of this software and preparation of derivative works
// based upon this software are permitted. Any copy of this software or
// of any derivative work must include the above copyright notice of
// Yann-Gaël Guéhéneuc, this paragraph and the one after it.
// 
// This software is made available AS IS, and THE AUTHOR DISCLAIMS
// ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
// PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
// ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
// EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
// NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
// OF THE POSSIBILITY OF SUCH DAMAGES.
// 
// All Rights Reserved.



// These tests are inspired from the PaLM tutorial-1.cl file!
// Thanks to Narendra Jussien.

[classicalPtidejProblem() : void
	->	let 
			// A Ptidej problem...
			pb := makePtidejProblem("Demo constraints", 5, 100),
			// A few variables...
	        a  := makePtidejBoundVar(pb, "a", 1, 3),
	        b  := makePtidejBoundVar(pb, "b", 1, 3),
	        c  := makePtidejBoundVar(pb, "c", 1, 3)
		in (
		    printf("Variables: ~S\n", list(a,b,c)),

		    printf("posting a > b \n"),
		    post(pb, a > b), propagate(pb),
		    printf("Variables: ~S\n", list(a,b,c)),

		    printf("posting a > c \n"),
		    post(pb, a > c), propagate(pb),
		    printf("Variables: ~S\n", list(a,b,c)),

		    printf("posting b > c \n"),
		    post(pb, b > c), propagate(pb),
		    printf("Variables: ~S\n", list(a,b,c))
		)
]

[inconsistentPtidejProblem() : void
	->	let pb := makePtidejProblem("Demo explanations", 5, 100),
			a  := makePtidejBoundVar(pb, "a", 1, 3),
			b  := makePtidejBoundVar(pb, "b", 1, 3),
			c  := makePtidejBoundVar(pb, "c", 1, 3),
			d  := makePtidejBoundVar(pb, "d", 1, 3),
			e  := makePtidejBoundVar(pb, "e", 1, 3)
		in (
			printf("\nVariables: ~S\n", list(a,b,c,d,e)),
	
			post(pb, a > c),
			post(pb, b > d),
			post(pb, c > e),
			post(pb, d > e),
	
			propagate(pb),
			printf("Variables: ~S\n", list(a,b,c,d,e)),
	
			post(pb, e > a),
			propagate(pb)
		)
]

[overConstrainedPtidejProblem() : void
	->	let pb := makePtidejProblem("Demo over-constrained problem", 3, 5),
	        a  := makePtidejBoundVar(pb, "a", 1, 3),
	        b  := makePtidejBoundVar(pb, "b", 1, 3),
	        c  := makePtidejBoundVar(pb, "c", 1, 3),
			ct1 := (a > b),
			ct2 := (b > c),
			ct3 := (c > a)
	    in (
	        try (
	            printf("Variables: ~S\n", list(a,b,c)),
	
	            printf("posting ~S \n", ct1),
	            post(pb, ct1, 2),
	            propagate(pb),
	            printf("Variables: ~S\n", list(a,b,c)),
	
	            printf("posting ~S \n", ct2),
	            post(pb, ct2, 5),
	            propagate(pb),
	            printf("Variables: ~S\n", list(a,b,c)),
	
	            printf("posting ~S \n", ct3),
	            post(pb, ct3, 3),
	            propagate(pb),
	            printf("Variables: ~S\n", list(a,b,c))
	        )
	        catch PalmContradiction (
	            repair(pb),
	            printf("Variables: ~S\n", list(a,b,c))
	        )
	    )
]