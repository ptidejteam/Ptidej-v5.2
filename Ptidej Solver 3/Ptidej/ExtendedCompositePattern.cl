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

[customProblemForExtendedCompositePattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Extended Composite Pattern Problem", length(listOfEntities), 90),
            compositeRoot := makePtidejVar(pb, "CompositeRoot", 1, length(listOfEntities),true),
            componente     := makePtidejVar(pb, "Component", 1, length(listOfEntities),true),
            composite     := makePtidejVar(pb, "Composite", 1, length(listOfEntities), true),
            leaf          := makePtidejVar(pb, "Leaf", 1, length(listOfEntities), false) 
        in (

			setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeCompositionConstraint(
                    "CompositeRoot <>--> Component",
                    "throw new RuntimeException(\"Composite <>--> Component\");",
                    compositeRoot,
                    componente),
                 90),
            post(pb,
                 makeStrictInheritanceConstraint(
                    "CompositeRoot -|>- Component",
                    "Composite, Component |\\n\\t\\t\\tjavaXL.XClass c1, javaXL.XClass c2 |\\n\\t\\t\\tc1.setSuperclass(c2.getName());",
                    compositeRoot,
                    componente),
                 60),
            post(pb,
                 makeStrictInheritanceConstraint(
                    "Composite -|>- CompositeRoot",
                    "Composite, Component |\\n\\t\\t\\tjavaXL.XClass c1, javaXL.XClass c2 |\\n\\t\\t\\tc1.setSuperclass(c2.getName());",
                    composite,
                    compositeRoot),
                 90),
            post(pb,
                 makeStrictInheritancePathConstraint(
                    "Leaf -|>- ... -|>- Component",
                    "Leaf, Component |\\n\\t\\t\\tjavaXL.XClass c1, javaXL.XClass c2 |\\n\\t\\t\\tc1.setSuperclass(c2.getName());",
                    leaf,
                    componente),
                 90),
            post(pb,
                 makeIgnoranceConstraint(
                    "Component -/--> Leaf",
                    "throw new RuntimeException(\"Component should not ----> Leaf\");",
                    componente,
                    leaf),
                 10),
            post(pb,
                 makeIgnoranceConstraint(
                    "Leaf -/--> Composite",
                    "throw new RuntimeException(\"Leaf should not ----> Composite\");",
                    leaf,
                    composite),
                 30),
            post(pb,
                 makeNotEqualConstraint(
                    "Component <> Composite",
                    "throw new RuntimeException(\"Component <> Composite\");",
                    componente,
                    composite),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Component <> Leaf",
                    "throw new RuntimeException(\"Component <> Leaf\");",
                    componente,
                    leaf),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Composite <> Leaf",
                    "throw new RuntimeException(\"Composite <> Leaf\");",
                    composite,
                    leaf),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "CompositeRoot <> Leaf",
                    "throw new RuntimeException(\"compositeRoot <> Leaf\");",
                    compositeRoot,
                    leaf),
                 100),
            pb
        )
]

[ac4ProblemForExtendedCompositePattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Extended Composite Pattern Problem", length(listOfEntities), 90),
            compositeRoot := makePtidejVar(pb, "CompositeRoot", 1, length(listOfEntities),true),
            componente     := makePtidejVar(pb, "Component", 1, length(listOfEntities),true),
            composite     := makePtidejVar(pb, "Composite", 1, length(listOfEntities), false),
            leaf          := makePtidejVar(pb, "Leaf", 1, length(listOfEntities), false) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeCompositionAC4Constraint(
                    "CompositeRoot <>--> Component",
                    "throw new RuntimeException(\"Composite <>--> Component\");",
                    compositeRoot,
                    componente),
                 90),
            post(pb,
                 makeInheritanceAC4Constraint(
                    "CompositeRoot -|>- Component",
                    "Composite, Component |\\n\\t\\t\\tjavaXL.XClass c1, javaXL.XClass c2 |\\n\\t\\t\\tc1.setSuperclass(c2.getName());",
                    compositeRoot,
                    componente),
                 60),
            post(pb,
                 makeInheritanceAC4Constraint(
                    "Composite -|>- CompositeRoot",
                    "Composite, Component |\\n\\t\\t\\tjavaXL.XClass c1, javaXL.XClass c2 |\\n\\t\\t\\tc1.setSuperclass(c2.getName());",
                    composite,
                    compositeRoot),
                 90),
            post(pb,
                 makeInheritancePathAC4Constraint(
                    "Leaf -|>- ... -|>- Component",
                    "Leaf, Component |\\n\\t\\t\\tjavaXL.XClass c1, javaXL.XClass c2 |\\n\\t\\t\\tc1.setSuperclass(c2.getName());",
                    leaf,
                    componente),
                 90),
            post(pb,
                 makeIgnoranceAC4Constraint(
                    "Component -/--> Leaf",
                    "throw new RuntimeException(\"Component should not ----> Leaf\");",
                    componente,
                    leaf),
                 10),
            post(pb,
                 makeIgnoranceAC4Constraint(
                    "Leaf -/--> Composite",
                    "throw new RuntimeException(\"Leaf should not ----> Composite\");",
                    leaf,
                    composite),
                 30),
            post(pb,
                 makeNotEqualConstraint(
                    "Component <> Composite",
                    "throw new RuntimeException(\"Component <> Composite\");",
                    componente,
                    composite),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Component <> Leaf",
                    "throw new RuntimeException(\"Component <> Leaf\");",
                    componente,
                    leaf),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Composite <> Leaf",
                    "throw new RuntimeException(\"Composite <> Leaf\");",
                    composite,
                    leaf),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "CompositeRoot <> Leaf",
                    "throw new RuntimeException(\"compositeRoot <> Leaf\");",
                    compositeRoot,
                    leaf),
                 100),
            pb
        )
]