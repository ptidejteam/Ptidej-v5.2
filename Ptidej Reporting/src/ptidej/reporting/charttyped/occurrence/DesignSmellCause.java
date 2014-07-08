/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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
package ptidej.reporting.charttyped.occurrence;

/**
 * 
 * @author Yann
 * @since  2008/10/03
 *
 */
public class DesignSmellCause {
	private final String codeSmellName;
	private final String codeSmellValue;

	public DesignSmellCause(
		final String aCodeSmellName,
		final String aCodeSmellValue) {

		this.codeSmellName = aCodeSmellName;
		this.codeSmellValue = aCodeSmellValue;
	}
	public String getName() {
		return this.codeSmellName;
	}
	public String getValue() {
		return this.codeSmellValue;
	}
	public String toString() {
		return this.codeSmellName + " = " + this.codeSmellValue;
	}
}
