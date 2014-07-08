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

package sad.codesmell.detection.repository.BaseClassShouldBeAbstract;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import padl.kernel.IAbstractLevelModel;
import padl.kernel.IClass;
import padl.kernel.IEntity;
import sad.codesmell.property.impl.ClassProperty;
import sad.codesmell.detection.ICodeSmellDetection;
import sad.codesmell.detection.repository.AbstractCodeSmellDetection;
import sad.kernel.impl.CodeSmell;

/**
 * @author Auto generated
 */

public class NotAbstractDetection extends AbstractCodeSmellDetection implements ICodeSmellDetection {

	// David and Yann 2013/10/24: Useless?
	// This variable and its accessor should probably removed. 
	private static final int bool = 0;

	public boolean getValueBoolean() {
		return NotAbstractDetection.bool == 1;
	}
	
	public String getName() {
		return "NotAbstract";
	}

	public void detect(final IAbstractLevelModel anAbstractLevelModel) {
		Set classesNotAbstract = new HashSet();

		final Iterator iter = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		while (iter.hasNext()) {
			final IEntity entity = (IEntity) iter.next();
			if (entity instanceof IClass) {
				final IClass aClass = (IClass) entity;
				boolean presence = getValueBoolean();
		
				if (presence == true) {
					if (aClass.isAbstract()) {
						classesNotAbstract =
							createCodeSmell(
								classesNotAbstract,
								aClass);
					}
				} else {
					if (!aClass.isAbstract()) {
						classesNotAbstract =
							createCodeSmell(
								classesNotAbstract,
								aClass);
					}
				}
			}
		}
		this.setSetOfSmells(classesNotAbstract);
	}
	
	private Set createCodeSmell(
		Set classesNotAbstract,
		final IClass aClass) {
		try {
			CodeSmell dc =
				new CodeSmell(
					"NotAbstract",
					"",
					new ClassProperty(aClass));

			classesNotAbstract.add(dc);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return classesNotAbstract;
	}
}
