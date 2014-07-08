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

[customProblemForIteratorPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb                := makePtidejProblem("Iterator Pattern Problem", length(listOfEntities), 90),
            iterator          := makePtidejVar(pb, "Iterator",          1, length(listOfEntities)),
            concreteIterator  := makePtidejVar(pb, "ConcreteIterator",  1, length(listOfEntities), false),
            aggregate         := makePtidejVar(pb, "Aggregate",         1, length(listOfEntities)),
            concreteAggregate := makePtidejVar(pb, "ConcreteAggregate", 1, length(listOfEntities), false) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeInheritancePathConstraint(
                    "ConcreteIterator -|>- ... -|>- Iterator",
                    "throw new RuntimeException(\"ConcreteIterator -|>- ... -|>- Iterator\");",
                    concreteIterator,
                    iterator),
                 90),
            post(pb,
                 makeInheritancePathConstraint(
                    "ConcreteAggregate -|>- ... -|>- Aggregate",
                    "throw new RuntimeException(\"ConcreteAggregate -|>- ... -|>- Aggregate\");",
                    concreteAggregate,
                    aggregate),
                 90),
            post(pb,
                 makeCreationConstraint(
                    "ConcreteAggregate -*--> ConcreteIterator",
                    "throw new RuntimeException(\"ConcreteAggregate -*--> ConcreteIterator\");",
                    concreteAggregate,
                    concreteIterator),
                 90),
            post(pb,
                 makeUseConstraint(
                    "ConcreteIterator ----> ConcreteAggregate",
                    "throw new RuntimeException(\"ConcreteIterator ----> ConcreteAggregate\");",
                    concreteIterator,
                    concreteAggregate),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Creator <> ConcreteCreator",
                    "throw new RuntimeException(\"Creator <> ConcreteCreator\");",
                    iterator,
                    concreteIterator),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Aggregate <> ConcreteAggregate",
                    "throw new RuntimeException(\"Aggregate <> ConcreteAggregate\");",
                    aggregate,
                    concreteAggregate),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Iterator <> Aggregate",
                    "throw new RuntimeException(\"Iterator <> Aggregate\");",
                    iterator,
                    aggregate),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Iterator <> ConcreteAggregate",
                    "throw new RuntimeException(\"Iterator <> ConcreteAggregate\");",
                    iterator,
                    concreteAggregate),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Aggregate <> ConcreteAggregate",
                    "throw new RuntimeException(\"Aggregate <> ConcreteAggregate\");",
                    aggregate,
                    concreteIterator),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteIterator <> ConcreteAggregate",
                    "throw new RuntimeException(\"ConcreteIterator <> ConcreteAggregate\");",
                    concreteIterator,
                    concreteAggregate),
                 100),
            pb
        )
]
[ac4ProblemForIteratorPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb                := makePtidejProblem("Iterator Pattern Problem", length(listOfEntities), 90),
            iterator          := makePtidejVar(pb, "Iterator",          1, length(listOfEntities)),
            concreteIterator  := makePtidejVar(pb, "ConcreteIterator",  1, length(listOfEntities), false),
            aggregate         := makePtidejVar(pb, "Aggregate",         1, length(listOfEntities)),
            concreteAggregate := makePtidejVar(pb, "ConcreteAggregate", 1, length(listOfEntities), false) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeInheritancePathAC4Constraint(
                    "ConcreteIterator -|>- ... -|>- Iterator",
                    "throw new RuntimeException(\"ConcreteIterator -|>- ... -|>- Iterator\");",
                    concreteIterator,
                    iterator),
                 90),
            post(pb,
                 makeInheritancePathAC4Constraint(
                    "ConcreteAggregate -|>- ... -|>- Aggregate",
                    "throw new RuntimeException(\"ConcreteAggregate -|>- ... -|>- Aggregate\");",
                    concreteAggregate,
                    aggregate),
                 90),
            post(pb,
                 makeCreationAC4Constraint(
                    "ConcreteAggregate -*--> ConcreteIterator",
                    "throw new RuntimeException(\"ConcreteAggregate -*--> ConcreteIterator\");",
                    concreteAggregate,
                    concreteIterator),
                 90),
            post(pb,
                 makeUseAC4Constraint(
                    "ConcreteIterator ----> ConcreteAggregate",
                    "throw new RuntimeException(\"ConcreteIterator ----> ConcreteAggregate\");",
                    concreteIterator,
                    concreteAggregate),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Creator <> ConcreteCreator",
                    "throw new RuntimeException(\"Creator <> ConcreteCreator\");",
                    iterator,
                    concreteIterator),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Aggregate <> ConcreteAggregate",
                    "throw new RuntimeException(\"Aggregate <> ConcreteAggregate\");",
                    aggregate,
                    concreteAggregate),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Iterator <> Aggregate",
                    "throw new RuntimeException(\"Iterator <> Aggregate\");",
                    iterator,
                    aggregate),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Iterator <> ConcreteAggregate",
                    "throw new RuntimeException(\"Iterator <> ConcreteAggregate\");",
                    iterator,
                    concreteAggregate),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Aggregate <> ConcreteAggregate",
                    "throw new RuntimeException(\"Aggregate <> ConcreteAggregate\");",
                    aggregate,
                    concreteIterator),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteIterator <> ConcreteAggregate",
                    "throw new RuntimeException(\"ConcreteIterator <> ConcreteAggregate\");",
                    concreteIterator,
                    concreteAggregate),
                 100),
            pb
        )
]