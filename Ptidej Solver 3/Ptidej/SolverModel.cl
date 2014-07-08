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



// *********************************************
// * Part 0: Problem definitions and accessors *
// *********************************************

PtidejProblem <: PalmProblem(
	private/globalWeight:integer = 0)

[getGlobalWeight(p:PtidejProblem) : integer
	->	p.globalWeight
]
[setGlobalWeight(p:PtidejProblem, gw:integer) : void
	->	// [0] gw = ~S // gw,
		p.globalWeight := gw
]


// ************************************************
// * Part 1: Constraint definitions and accessors *
// ************************************************

PtidejBinConstraint <: PalmBinIntConstraint(
	private/name:string,
	private/command:string,
	private/thisConstraint:any = unknown,
	private/nextConstraint:any = unknown)

[getName(c:PtidejBinConstraint) : string
	->	c.name
]
[getXCommand(c:PtidejBinConstraint) : string
	->	c.command
]
[getThisConstraint(c:PtidejBinConstraint) : any
	->	c.thisConstraint
]
[getNextConstraint(c:PtidejBinConstraint) : any
	->	c.nextConstraint
]
[weight(c:PtidejBinConstraint) : integer
	->	c.hook.weight
]



PtidejLargeConstraint <: PalmLargeIntConstraint(
	private/name:string,
	private/command:string,
	private/thisConstraint:any = unknown,
	private/nextConstraint:any = unknown)

[getName(c:PtidejLargeConstraint) : string
	->	c.name
]
[getXCommand(c:PtidejLargeConstraint) : string
	->	c.command
]
[getThisConstraint(c:PtidejLargeConstraint) : any
	->	c.thisConstraint
]
[getNextConstraint(c:PtidejLargeConstraint) : any
	->	c.nextConstraint
]
[weight(c:PtidejLargeConstraint) : integer
	->	c.hook.weight
]



PtidejAC4Constraint <: PalmAC4BinConstraint(
	private/name:string,
	private/command:string,
	private/thisConstraint:any = unknown,
	private/nextConstraint:any = unknown)

[getName(c:PtidejAC4Constraint) : string
	->	c.name
]
[getXCommand(c:PtidejAC4Constraint) : string
	->	c.command
]
[getThisConstraint(c:PtidejAC4Constraint) : any
	->	c.thisConstraint
]
[getNextConstraint(c:PtidejAC4Constraint) : any
	->	c.nextConstraint
]
[weight(c:PtidejAC4Constraint) : integer
	->	c.hook.weight
]



// ********************************
// * Part 2: Variable definitions *
// ********************************

// Yann 2001/07/24: Improvements
// The class PtidejVar now contains a new attribute
// toBeEnumerated. This is a boolean value, that is at
// true to indicate that the corresponding variable is
// to be enumerated and at false when the corresponding
// variable should not be enumerated (list of PEntities).
// See also the declaration of the class PtidejAssignVar
// and of the method selectBranchingObject(b:PtidejAssignVar).

PtidejVar <: PalmIntVar(toBeEnumerated:boolean = true)
PtidejBoundVar <: PtidejVar()

[setVarsToShow(
	globalSearchSolver:GlobalSearchSolver, 
	vars:list<choco/PtidejVar>) : void
	->	globalSearchSolver.choco/varsToShow :=
			set!(list<choco/IntVar>{(vars[i] as choco/IntVar) | i in 1 .. length(vars)})
]
[setVarsToShow(
	globalSearchSolver:GlobalSearchSolver, 
	vars:list<choco/IntVar>) : void
	->	globalSearchSolver.choco/varsToShow := set!(copy(vars))
]
