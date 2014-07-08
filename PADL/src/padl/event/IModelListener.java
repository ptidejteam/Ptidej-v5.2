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

public interface IModelListener {
	String ELEMENT_ADDED = "elementAdded";
	String ELEMENT_ANALYZED = "elementAnalyzed";
	String ELEMENT_RECOGNIZED = "elementRecognized";
	String ELEMENT_REMOVED = "elementRemoved";
	String ELEMENT_SKIPPED = "elementSkipped";

	String ENTITY_ADDED = "entityAdded";
	String ENTITY_ANALYZED = "entityAnalyzed";
	String ENTITY_RECOGNIZED = "entityRecognized";
	String ENTITY_REMOVED = "entityRemoved";
	String ENTITY_SKIPPED = "entitySkipped";

	String INVOKE_ADDED = "invokeAdded";
	String INVOKE_ANALYZED = "invokeAnalyzed";
	String INVOKE_RECOGNIZED = "invokeRecognized";
	String INVOKE_REMOVED = "invokeRemoved";
	String INVOKE_SKIPPED = "invokeSkipped";

	void elementAdded(final ElementEvent elementEvent);
	void elementAnalyzed(final AnalysisEvent analisysEvent);
	void elementIdentified(final IdentificationEvent recognitionEvent);
	void elementRemoved(final ElementEvent elementEvent);
	void elementSkipped(final AnalysisEvent analisysEvent);

	void entityAdded(final EntityEvent entityEvent);
	void entityAnalyzed(final AnalysisEvent analisysEvent);
	void entityIdentified(final IdentificationEvent recognitionEvent);
	void entityRemoved(final EntityEvent entityEvent);
	void entitySkipped(final AnalysisEvent analisysEvent);

	void invokeAdded(final InvokeEvent invokeEvent);
	void invokeAnalyzed(final AnalysisEvent analisysEvent);
	void invokeIdentified(final IdentificationEvent recognitionEvent);
	void invokeRemoved(final InvokeEvent invokeEvent);
	void invokeSkipped(final AnalysisEvent analisysEvent);
}
