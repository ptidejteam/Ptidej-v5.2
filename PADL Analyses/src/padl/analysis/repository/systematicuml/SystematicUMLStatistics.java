/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
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
package padl.analysis.repository.systematicuml;

import java.util.ArrayList;
import java.util.List;

import padl.kernel.IFirstClassEntity;
import padl.kernel.IMethod;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/05/20
 */
public class SystematicUMLStatistics {
	private final List listOfDataTypes;
	private final List listOfEnumerations;
	private final List listOfImplementationClasses;
	private final List listOfOperations;
	private final List listOfTemplateMethods;
	private final List listOfTypes;
	private final List listOfUtilityClasses;

	public SystematicUMLStatistics() {
		this.listOfDataTypes = new ArrayList();
		this.listOfTypes = new ArrayList();
		this.listOfImplementationClasses = new ArrayList();
		this.listOfOperations = new ArrayList();
		this.listOfTemplateMethods = new ArrayList();
		this.listOfUtilityClasses = new ArrayList();
		this.listOfEnumerations = new ArrayList();
	}
	public void dataTypeRecognized(final IFirstClassEntity anEntity) {
		this.listOfDataTypes.add(anEntity);
	}
	public void dataTypeUnRecognized(final IFirstClassEntity anEntity) {
		this.listOfDataTypes.remove(anEntity);
	}
	public void enumerationRecognized(final IFirstClassEntity anEntity) {
		this.listOfEnumerations.add(anEntity);
	}
	public void implementationClassRecognized(final IFirstClassEntity anEntity) {
		this.listOfImplementationClasses.add(anEntity);
	}
	public void operationRecognized(final IMethod aMethod) {
		this.listOfOperations.add(aMethod);
	}
	public void templateMethodRecognized(final IMethod aMethod) {
		this.listOfTemplateMethods.add(aMethod);
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("Number of types: ");
		buffer.append(this.listOfTypes.size());
		buffer.append("\nNumber of implementation classes: ");
		buffer.append(this.listOfImplementationClasses.size());
		buffer.append("\nNumber of utility classes: ");
		buffer.append(this.listOfUtilityClasses.size());
		buffer.append("\nNumber of enumeration classes: ");
		buffer.append(this.listOfEnumerations.size());
		buffer.append("\nNumber of data types: ");
		buffer.append(this.listOfDataTypes.size());
		buffer.append("\nNumber of operations: ");
		buffer.append(this.listOfOperations.size());
		buffer.append("\nNumber of template methods: ");
		buffer.append(this.listOfTemplateMethods.size());
		buffer.append('\n');
		return buffer.toString();
	}
	public void typeRecognized(final IFirstClassEntity anEntity) {
		this.listOfTypes.add(anEntity);
	}
	public void utilityClassRecognized(final IFirstClassEntity anEntity) {
		this.listOfUtilityClasses.add(anEntity);
	}
}
