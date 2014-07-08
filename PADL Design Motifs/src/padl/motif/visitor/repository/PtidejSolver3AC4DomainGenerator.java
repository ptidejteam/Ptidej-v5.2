/*
 * (c) Copyright 2001, 2002 Hervé Albin-Amiot and Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * Soft-Maint S.A.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.motif.visitor.repository;

import padl.motif.visitor.IMotifWalker;

public class PtidejSolver3AC4DomainGenerator extends
		PtidejSolver2AC4DomainGenerator implements IMotifWalker {

	protected String getCoupleDeclaration() {
		return "list<tuple(integer,integer)>(";
	}
	protected String getCouplePrefix() {
		return "tuple(";
	}
	protected String getListDeclaration() {
		return "list<Entity>";
	}
	protected String getListPrefix() {
		return "list<Entity>(";
	}
	protected String getListSuffix() {
		return ")";
	}
	public String getName() {
		return "PtidejSolver 3 AC4 Domain";
	}
}
