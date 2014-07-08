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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class VersionClassRolesHolder {
	private final Map versionClassRoles = new HashMap();
	public void addDPRole(
		final String aClassName,
		final String aPatternName,
		final String aRole) {

		this.addRole(aClassName, aPatternName, aRole, true);
	}
	public void addAPRole(
		final String aClassName,
		final String aPatternName,
		final String aRole) {

		this.addRole(aClassName, aPatternName, aRole, false);
	}
	private void addRole(
		final String aClassName,
		final String aPatternName,
		final String aRole,
		final boolean isPositive) {

		ClassRolesHolder classRolesHolder =
			(ClassRolesHolder) this.versionClassRoles.get(aClassName);
		if (classRolesHolder == null) {
			classRolesHolder = new ClassRolesHolder(aClassName);
			this.versionClassRoles.put(aClassName, classRolesHolder);
		}
		if (isPositive) {
			classRolesHolder.addDPRole(aPatternName, aRole);
		}
		else {
			classRolesHolder.addAPRole(aPatternName, aRole);
		}
	}
	public int getNumberOfClassesWithPositiveAndNegativeRoles() {
		int count = 0;
		final Iterator iteratorOnClasses =
			this.versionClassRoles.keySet().iterator();
		while (iteratorOnClasses.hasNext()) {
			final String className = (String) iteratorOnClasses.next();
			final ClassRolesHolder classRoles =
				(ClassRolesHolder) this.versionClassRoles.get(className);
			final Set setOfNegativeRoles = classRoles.getAPRoles();
			final Set setOfPositiveRoles = classRoles.getDPRoles();
			if (setOfNegativeRoles.size() > 0 && setOfPositiveRoles.size() > 0) {
				count++;
			}
		}
		return count;
	}
	public String getClassesWithPositiveAndNegativeRoles() {
		final StringBuffer buffer = new StringBuffer();
		final Iterator iteratorOnClasses =
			this.versionClassRoles.keySet().iterator();
		while (iteratorOnClasses.hasNext()) {
			final String className = (String) iteratorOnClasses.next();
			final ClassRolesHolder classRoles =
				(ClassRolesHolder) this.versionClassRoles.get(className);
			final Set setOfNegativeRoles = classRoles.getAPRoles();
			final Set setOfPositiveRoles = classRoles.getDPRoles();
			if (setOfNegativeRoles.size() > 0 && setOfPositiveRoles.size() > 0) {
				buffer.append(classRoles.getClassName());
				buffer.append(';');
				Iterator iterator = setOfNegativeRoles.iterator();
				while (iterator.hasNext()) {
					String role = (String) iterator.next();
					buffer.append("Has_");
					buffer.append(role);
					if (iterator.hasNext()) {
						buffer.append(';');
					}
				}
				buffer.append(';');
				iterator = setOfPositiveRoles.iterator();
				while (iterator.hasNext()) {
					String role = (String) iterator.next();
					buffer.append("PlaysRoleIn_");
					buffer.append(role);
					if (iterator.hasNext()) {
						buffer.append(';');
					}
				}
				buffer.append('\n');
			}
		}
		return buffer.toString();
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		final Iterator iteratorOnClasses =
			this.versionClassRoles.keySet().iterator();
		while (iteratorOnClasses.hasNext()) {
			final String className = (String) iteratorOnClasses.next();
			final ClassRolesHolder classRoles =
				(ClassRolesHolder) this.versionClassRoles.get(className);
			buffer.append(classRoles.toString());
			buffer.append('\n');
		}
		return buffer.toString();
	}
}
