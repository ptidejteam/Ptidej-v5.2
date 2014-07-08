/*
 * (c) Copyright Yann-Gaël Guéhéneuc,
 * University of Montréal.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.kernel.impl;

import org.apache.commons.lang.ArrayUtils;
import padl.event.IEvent;
import padl.kernel.IElement;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IRelationship;
import padl.path.IConstants;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/04/08
 */
public class DummyRelationship extends Constituent implements IRelationship {
	private static final long serialVersionUID = 8246146842271467729L;
	private static char[] DEFAULT_ID = "dm".toCharArray();
	private static int UniqueID = 0;

	private final IFirstClassEntity targetEntity;

	public DummyRelationship(final IFirstClassEntity aTargetEntity) {
		super(ArrayUtils.addAll(
			DummyRelationship.DEFAULT_ID,
			Integer.toString(DummyRelationship.UniqueID++).toCharArray()));
		this.targetEntity = aTargetEntity;
	}
	public void attachTo(final IElement anElement) {
	}
	public void detach() {
	}
	public void fireModelChange(final String anEventType, final IEvent anEvent) {
	}
	public IElement getAttachedElement() {
		return null;
	}
	public int getCardinality() {
		return 0;
	}
	protected char getPathSymbol() {
		return IConstants.ELEMENT_SYMBOL;
	}
	public IFirstClassEntity getTargetEntity() {
		return this.targetEntity;
	}
	public void setTargetEntity(final IFirstClassEntity anEntity) {
	}
}
