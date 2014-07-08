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

componentsType :: property()
[problemForStarredCompositePattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Starred Composite Pattern Problem", length(listOfEntities), 90),
            leavesType := makePtidejVar(pb, "LeavesType", 1, length(listOfEntities), false),
            leaf       := makePtidejVar(pb, "Leaf",       1, length(listOfEntities), false),
            composite  := makePtidejVar(pb, "Composite",  1, length(listOfEntities)),
            component  := makePtidejVar(pb, "Component",  1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            printf("Setting constraints for starred Composite pattern...\n"),
            post(pb, makeStrictInheritanceConstraint(
                "Composite -|>- Component",
                "Composite, Component |\\n\\t\\t\\tjavaXL.XClass c1, javaXL.XClass c2 |\\n\\t\\t\\tc1.setSuperclass(c2.getName());",
                composite, component), 100),
            post(pb, makeStrictInheritanceConstraint(
                "Leaf -|>- LeavesType",
                "Leaf, LeavesType |\\n\\t\\t\\tjavaXL.XClass l1, javaXL.XClass l2 |\\n\\t\\t\\tl1.setSuperclass(l2.getName());",
                leaf, leavesType), 100),
            post(pb, makeInheritanceConstraint(
                "LeavesType ---- Components OR LeavesType -|>- Component",
                "LeavesType, Component |\\n\\t\\t\\tSystem.out.println(\"LeavesType -|>- Component\");",
                leavesType, component), 90),
            post(pb, makeCompositionConstraint(
                "Composite <>-- Leaf",
                "throw new RuntimeException(\"Composite <>-- Leaf\");",
                composite, leaf), 90),
            post(pb, makeListPropertyTypeConstraint(
                "Composite.componentsType contains type ComponentsType",
                "throw new RuntimeException(\"Composite.componentsType contains type ComponentsType\");",
                composite, leavesType, componentsType), 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Composite != Leaf",
                    "throw new RuntimeException(\"Composite != Leaf\");",
                    composite,
                    leaf),
                 100),
            pb
        )
]
