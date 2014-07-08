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
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.kernel.impl;

import padl.kernel.IAssociation;
import padl.kernel.IContainerComposition;
import padl.kernel.IElementMarker;
import padl.kernel.IFirstClassEntity;

class ContainerComposition extends ContainerAggregation implements
		IElementMarker, IContainerComposition {

	private static final long serialVersionUID = 3675667508613729100L;
	public ContainerComposition(final IAssociation pAssociation) {
		this(pAssociation.getID(), pAssociation.getTargetEntity(), pAssociation
			.getCardinality());
	}
	public ContainerComposition(
		final char[] anID,
		final IFirstClassEntity aTargetEntity,
		final int cardinality) {

		super(anID, aTargetEntity, cardinality);
	}
}
