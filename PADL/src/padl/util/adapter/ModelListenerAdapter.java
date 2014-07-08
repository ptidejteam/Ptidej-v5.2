/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package padl.util.adapter;

import padl.event.AnalysisEvent;
import padl.event.ElementEvent;
import padl.event.EntityEvent;
import padl.event.IModelListener;
import padl.event.IdentificationEvent;
import padl.event.InvokeEvent;

public class ModelListenerAdapter implements IModelListener {
	public void elementAdded(final ElementEvent elementEvent) {
	}
	public void elementAnalyzed(final AnalysisEvent analisysEvent) {
	}
	public void elementIdentified(final IdentificationEvent recognitionEvent) {
	}
	public void elementRemoved(final ElementEvent elementEvent) {
	}
	public void elementSkipped(final AnalysisEvent analisysEvent) {
	}
	public void entityAdded(final EntityEvent entityEvent) {
	}
	public void entityAnalyzed(final AnalysisEvent analisysEvent) {
	}
	public void entityIdentified(final IdentificationEvent recognitionEvent) {
	}
	public void entityRemoved(final EntityEvent entityEvent) {
	}
	public void entitySkipped(final AnalysisEvent analisysEvent) {
	}
	public void invokeAdded(final InvokeEvent invokeEvent) {
	}
	public void invokeAnalyzed(final AnalysisEvent analisysEvent) {
	}
	public void invokeIdentified(final IdentificationEvent recognitionEvent) {
	}
	public void invokeRemoved(final InvokeEvent invokeEvent) {
	}
	public void invokeSkipped(final AnalysisEvent analisysEvent) {
	}
}
