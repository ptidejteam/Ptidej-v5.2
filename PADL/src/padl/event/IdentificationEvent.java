/*
 * (c) Copyright 2001-2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
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
package padl.event;

import java.util.List;
import padl.kernel.IAbstractLevelModel;

public final class IdentificationEvent implements IEvent {
	private final String recognizedConstituentName;

	public IdentificationEvent(final String recognizedConstituentName) {
		this.recognizedConstituentName = recognizedConstituentName;
	}
	public String getConstituentName() {
		return this.recognizedConstituentName;
	}
	public IAbstractLevelModel getAbstractModel() {
		return null;
	}
	public List getSubmittedConstituents() {
		return null;
	}
}
