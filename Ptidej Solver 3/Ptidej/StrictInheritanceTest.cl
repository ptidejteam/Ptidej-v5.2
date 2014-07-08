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

[customProblemForStrictInheritanceTestPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("StrictInheritanceTestPattern Pattern Problem", length(listOfEntities), 90),
            superEntity := makePtidejVar(pb, "SuperEntity", 1, length(listOfEntities), true),
            subEntity := makePtidejVar(pb, "SubEntity", 1, length(listOfEntities), true) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeStrictInheritanceConstraint(
                    "Sub-entity -|>- Super-entity",
                    "SubEntity, SuperEntity |\n\t\t\tjavaXL.XClass c1, javaXL.XClass c2 |\n\t\t\tc1.setSuperclass(c2.getName());",
                    subEntity,
                    superEntity),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Super-entity <> Sub-entity",
                    "throw new RuntimeException(\"Super-entity <> Sub-entity\");",
                    superEntity,
                    subEntity),
                 100),
            pb
        )
]
[ac4ProblemForStrictInheritanceTestPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("StrictInheritanceTestPattern Pattern Problem", length(listOfEntities), 90),
            superEntity := makePtidejVar(pb, "SuperEntity", 1, length(listOfEntities), true),
            subEntity := makePtidejVar(pb, "SubEntity", 1, length(listOfEntities), true) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeStrictInheritanceAC4Constraint(
                    "Sub-entity -|>- Super-entity",
                    "SubEntity, SuperEntity |\n\t\t\tjavaXL.XClass c1, javaXL.XClass c2 |\n\t\t\tc1.setSuperclass(c2.getName());",
                    subEntity,
                    superEntity),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Super-entity <> Sub-entity",
                    "throw new RuntimeException(\"Super-entity <> Sub-entity\");",
                    superEntity,
                    subEntity),
                 100),
            pb
        )
]