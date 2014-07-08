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
package ptidej.solver.occurrence.merging;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClassRolesHolder {
	private final String className;
	private final Set setOfNegativeRoles;
	private final Set setOfPositiveRoles;
	public ClassRolesHolder(final String aClassName) {
		this.className = aClassName;
		this.setOfNegativeRoles = new HashSet();
		this.setOfPositiveRoles = new HashSet();
	}
	public String getClassName() {
		return this.className;
	}
	public void addDPRole(final String aPatternName, final String aRole) {
		this.addRole(aPatternName, aRole, true);
	}
	public void addAPRole(final String aPatternName, final String aRole) {
		this.addRole(aPatternName, aRole, false);
	}
	private void addRole(
		final String aPatternName,
		final String aRole,
		boolean isPositive) {

		final StringBuffer buffer = new StringBuffer();
		buffer.append('"');
		buffer.append(aPatternName);
		buffer.append("\"_");
		final int index;
		if ((index = aRole.indexOf('-')) == -1) {
			buffer.append(aRole);
		}
		else {
			buffer.append(aRole.substring(0, index));
		}
		if (isPositive) {
			this.setOfPositiveRoles.add(buffer.toString());
		}
		else {
			this.setOfNegativeRoles.add(buffer.toString());
		}
	}
	public Set getDPRoles() {
		return this.setOfPositiveRoles;
	}
	public Set getAPRoles() {
		return this.setOfNegativeRoles;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.className);
		buffer.append("\n\tAntipatterns:    ");
		{
			final Iterator iterator = this.setOfNegativeRoles.iterator();
			while (iterator.hasNext()) {
				final String role = (String) iterator.next();
				buffer.append(role);
				if (iterator.hasNext()) {
					buffer.append("; ");
				}
			}
		}
		buffer.append("\n\tDesign patterns: ");
		{
			final Iterator iterator = this.setOfPositiveRoles.iterator();
			while (iterator.hasNext()) {
				final String role = (String) iterator.next();
				buffer.append(role);
				if (iterator.hasNext()) {
					buffer.append("; ");
				}
			}
		}
		buffer.append('\n');
		return buffer.toString();
	}
}
