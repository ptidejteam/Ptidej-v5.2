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
// PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
// LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
// EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
// NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
// OF THE POSSIBILITY OF SUCH DAMAGES.
// 
// All Rights Reserved.

[customProblemForAssociationDistanceTestPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("AssociationDistance Pattern Problem", length(listOfEntities), 90),
            entity := makePtidejVar(pb, "Entity", 1, length(listOfEntities), true),
            associatedEntity := makePtidejVar(pb, "AssociatedEntity", 1, length(listOfEntities), true),
            charge := makePtidejBoundVar(pb, "AssociationDistance", 1, length(listOfEntities), true)
		in (

			setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeAssociationDistanceConstraint(
                    "charge(Entity ----> AssociatedEntity)",
                    "throw new RuntimeException(\"charge(Entity ----> AssociatedEntity)\");",
                    entity,
                    associatedEntity,
                    charge),
                 90),
			post(pb,
				 makeGreaterOrEqualPtidejConstraint(
                    "charge(Entity ----> AssociatedEntity)",
                    "throw new RuntimeException(\"charge(Entity ----> AssociatedEntity) >= 2\");",
					charge,
					1),
				90),
            pb
        )
]