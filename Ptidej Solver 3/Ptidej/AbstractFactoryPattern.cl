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

[customProblemForAbstractFactoryPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb              := makePtidejProblem("AbstractFactory Pattern Problem", length(listOfEntities), 90),
            creator         := makePtidejVar(pb, "Creator",         1, length(listOfEntities)),
            concreteCreator := makePtidejVar(pb, "ConcreteCreator", 1, length(listOfEntities)),
            product         := makePtidejVar(pb, "Product",         1, length(listOfEntities)),
            concreteProduct := makePtidejVar(pb, "ConcreteProduct", 1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeInheritancePathConstraint(
                    "ConcreteCreator -|>- ... -|>- Creator",
                    "throw new RuntimeException(\"ConcreteCreator -|>- ... -|>- Creator\");",
                    concreteCreator,
                    creator),
                 90),
            post(pb,
                 makeInheritancePathConstraint(
                    "ConcreteProduct -|>- ... -|>- Product",
                    "throw new RuntimeException(\"ConcreteProduct -|>- ... -|>- Product\");",
                    concreteProduct,
                    product),
                 90),
            post(pb,
                 makeUseConstraint(
                    "Creator ----> Product",
                    "throw new RuntimeException(\"Creator ----> Product\");",
                    creator,
                    product),
                 30),
            post(pb,
                 makeCreationConstraint(
                    "ConcreteCreator -*--> ConcreteProduct",
                    "throw new RuntimeException(\"ConcreteCreator -*--> ConcreteProduct\");",
                    concreteCreator,
                    concreteProduct),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Creator <> Product",
                    "throw new RuntimeException(\"Creator <> Product\");",
                    creator,
                    product),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Creator <> ConcreteCreator",
                    "throw new RuntimeException(\"Creator <> ConcreteCreator\");",
                    creator,
                    concreteCreator),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Creator <> ConcreteProduct",
                    "throw new RuntimeException(\"Creator <> ConcreteProduct\");",
                    creator,
                    concreteProduct),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Product <> ConcreteProduct",
                    "throw new RuntimeException(\"Product <> ConcreteProduct\");",
                    product,
                    concreteProduct),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteCreator <> ConcreteProduct",
                    "throw new RuntimeException(\"ConcreteCreator <> ConcreteProduct\");",
                    concreteCreator,
                    concreteProduct),
                 90),
            pb
        )
]
[ac4ProblemForAbstractFactoryPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb              := makePtidejProblem("AbstractFactory Pattern Problem", length(listOfEntities), 90),
            creator         := makePtidejVar(pb, "Creator",         1, length(listOfEntities)),
            concreteCreator := makePtidejVar(pb, "ConcreteCreator", 1, length(listOfEntities)),
            product         := makePtidejVar(pb, "Product",         1, length(listOfEntities)),
            concreteProduct := makePtidejVar(pb, "ConcreteProduct", 1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeInheritancePathAC4Constraint(
                    "ConcreteCreator -|>- ... -|>- Creator",
                    "throw new RuntimeException(\"ConcreteCreator -|>- ... -|>- Creator\");",
                    concreteCreator,
                    creator),
                 90),
            post(pb,
                 makeInheritancePathAC4Constraint(
                    "ConcreteProduct -|>- ... -|>- Product",
                    "throw new RuntimeException(\"ConcreteProduct -|>- ... -|>- Product\");",
                    concreteProduct,
                    product),
                 90),
            post(pb,
                 makeUseAC4Constraint(
                    "Creator ----> Product",
                    "throw new RuntimeException(\"Creator ----> Product\");",
                    creator,
                    product),
                 30),
            post(pb,
                 makeCreationAC4Constraint(
                    "ConcreteCreator -*--> ConcreteProduct",
                    "throw new RuntimeException(\"ConcreteCreator -*--> ConcreteProduct\");",
                    concreteCreator,
                    concreteProduct),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Creator <> Product",
                    "throw new RuntimeException(\"Creator <> Product\");",
                    creator,
                    product),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Creator <> ConcreteCreator",
                    "throw new RuntimeException(\"Creator <> ConcreteCreator\");",
                    creator,
                    concreteCreator),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Creator <> ConcreteProduct",
                    "throw new RuntimeException(\"Creator <> ConcreteProduct\");",
                    creator,
                    concreteProduct),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Product <> ConcreteProduct",
                    "throw new RuntimeException(\"Product <> ConcreteProduct\");",
                    product,
                    concreteProduct),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteCreator <> ConcreteProduct",
                    "throw new RuntimeException(\"ConcreteCreator <> ConcreteProduct\");",
                    concreteCreator,
                    concreteProduct),
                 90),
            pb
        )
]