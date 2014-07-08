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

[customProblemForProxyPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Proxy Pattern Problem", length(listOfEntities), 90),
            subject := makePtidejVar(pb, "Subject", 1, length(listOfEntities)),
            realSubject := makePtidejVar(pb, "RealSubject", 1, length(listOfEntities)),
            proxy := makePtidejVar(pb, "Proxy", 1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeStrictInheritanceConstraint(
                    "RealSubject -|>- Subject",
                    "RealSubject, Subject | javaXL.XClass c1, javaXL.XClass c2 | c1.setSuperclass(c2.getName());",
                    realSubject,
                    subject),
                 50),
            post(pb,
                 makeStrictInheritanceConstraint(
                    "Proxy -|>- Subject",
                    "Proxy, Subject | javaXL.XClass c1, javaXL.XClass c2 | c1.setSuperclass(c2.getName());",
                    proxy,
                    subject),
                 50),
            post(pb,
                 makeUseConstraint(
                    "Proxy ----> RealSubject",
                    "throw new RuntimeException(\"Composite <>--> Component\");",
                    proxy,
                    realSubject),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Subject <> RealSubject",
                    "throw new RuntimeException(\"Subject <> RealSubject\");",
                    subject,
                    realSubject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Subject <> Proxy",
                    "throw new RuntimeException(\"Subject <> Proxy\");",
                    subject,
                    proxy),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "RealSubject <> Subject",
                    "throw new RuntimeException(\"RealSubject <> Subject\");",
                    realSubject,
                    subject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "RealSubject <> Proxy",
                    "throw new RuntimeException(\"RealSubject <> Proxy\");",
                    realSubject,
                    proxy),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Proxy <> Subject",
                    "throw new RuntimeException(\"Proxy <> Subject\");",
                    proxy,
                    subject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Proxy <> RealSubject",
                    "throw new RuntimeException(\"Proxy <> RealSubject\");",
                    proxy,
                    realSubject),
                 100),
            pb
        )
]
[ac4ProblemForProxyPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Proxy Pattern Problem", length(listOfEntities), 90),
            subject := makePtidejVar(pb, "Subject", 1, length(listOfEntities)),
            realSubject := makePtidejVar(pb, "RealSubject", 1, length(listOfEntities)),
            proxy := makePtidejVar(pb, "Proxy", 1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeStrictInheritanceAC4Constraint(
                    "RealSubject -|>- Subject",
                    "RealSubject, Subject | javaXL.XClass c1, javaXL.XClass c2 | c1.setSuperclass(c2.getName());",
                    realSubject,
                    subject),
                 50),
            post(pb,
                 makeStrictInheritanceAC4Constraint(
                    "Proxy -|>- Subject",
                    "Proxy, Subject | javaXL.XClass c1, javaXL.XClass c2 | c1.setSuperclass(c2.getName());",
                    proxy,
                    subject),
                 50),
            post(pb,
                 makeUseAC4Constraint(
                    "Proxy ----> RealSubject",
                    "throw new RuntimeException(\"Composite <>--> Component\");",
                    proxy,
                    realSubject),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Subject <> RealSubject",
                    "throw new RuntimeException(\"Subject <> RealSubject\");",
                    subject,
                    realSubject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Subject <> Proxy",
                    "throw new RuntimeException(\"Subject <> Proxy\");",
                    subject,
                    proxy),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "RealSubject <> Subject",
                    "throw new RuntimeException(\"RealSubject <> Subject\");",
                    realSubject,
                    subject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "RealSubject <> Proxy",
                    "throw new RuntimeException(\"RealSubject <> Proxy\");",
                    realSubject,
                    proxy),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Proxy <> Subject",
                    "throw new RuntimeException(\"Proxy <> Subject\");",
                    proxy,
                    subject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Proxy <> RealSubject",
                    "throw new RuntimeException(\"Proxy <> RealSubject\");",
                    proxy,
                    realSubject),
                 100),
            pb
        )
]