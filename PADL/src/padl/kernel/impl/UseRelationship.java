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

import padl.kernel.IElementMarker;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IUseRelationship;

class UseRelationship extends Relationship implements IElementMarker,
		IUseRelationship {

	private static final long serialVersionUID = -6218449758442734567L;

	public UseRelationship(
		final char[] anID,
		final IFirstClassEntity aTargetEntity,
		final int aCardinality) {

		super(anID);

		// The order of the following lines is very fragile!
		this.setTargetEntity(aTargetEntity);
		this.setCardinality(aCardinality);
		// Yann 2004/12/19: Name and ID.
		// I set the name of the relationship according to its
		// type, to its target entity, and to its cardinaly
		// because I want to distinguish similar relationships
		// across models (i.e., with different IDs).
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.getClass().getName());
		buffer.append(':');
		buffer.append(aTargetEntity.getID());
		buffer.append(':');
		buffer.append(aCardinality);

		// Yann 2009/04/28: Unique ID!
		// I don't forget to set the ID of the relationship 
		// to a unique name... its name...
		//	this.setID(buffer.toString());
		this.setName(buffer.toString().toCharArray());
	}
}
