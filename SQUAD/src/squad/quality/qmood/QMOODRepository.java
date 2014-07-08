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
package squad.quality.qmood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import squad.quality.IQualityAttribute;
import util.io.ProxyConsole;
import util.io.SubtypeLoader;
import util.repository.FileAccessException;
import util.repository.IRepository;
import util.repository.impl.FileRepositoryFactory;
import com.ibm.toad.cfparse.ClassFile;

public class QMOODRepository implements IRepository {
	private static QMOODRepository UniqueInstance;
	public static QMOODRepository getInstance() {
		if (QMOODRepository.UniqueInstance == null) {
			QMOODRepository.UniqueInstance = new QMOODRepository();
		}
		return QMOODRepository.UniqueInstance;
	}

	private final IQualityAttribute[] qualityAttributes;
	private final Map<String, IQualityAttribute> mapOfAttributes =
		new HashMap<String, IQualityAttribute>();

	private QMOODRepository() {
		final List<IQualityAttribute> listOfQualityAttributes =
			new ArrayList<IQualityAttribute>();
		try {
			final ClassFile[] classFiles =
				SubtypeLoader.loadSubtypesFromStream(
					"squad.quality.IQualityAttribute",
					FileRepositoryFactory
						.getInstance()
						.getFileRepository(this)
						.getFiles(),
					"squad.quality.qmood.repository",
					".class");

			for (int i = 0; i < classFiles.length; i++) {
				try {
					@SuppressWarnings("unchecked")
					final Class<IQualityAttribute> attributeClass =
						(Class<IQualityAttribute>) Class.forName(classFiles[i]
							.getName());
					final IQualityAttribute qualityAttribute =
						attributeClass.newInstance();

					this.mapOfAttributes.put(
						qualityAttribute.getName(),
						qualityAttribute);
					listOfQualityAttributes.add(qualityAttribute);
				}
				catch (final ClassNotFoundException e) {
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
				catch (final IllegalArgumentException e) {
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
				catch (final SecurityException e) {
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
				catch (final InstantiationException e) {
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
				catch (final IllegalAccessException e) {
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
			}
		}
		catch (final FileAccessException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		this.qualityAttributes =
			new IQualityAttribute[listOfQualityAttributes.size()];
		listOfQualityAttributes.toArray(this.qualityAttributes);
	}
	public IQualityAttribute getQualityAttribute(final String anAttributeName) {
		return (IQualityAttribute) this.mapOfAttributes.get(anAttributeName);
	}
	public IQualityAttribute[] getQualityAttributes() {
		return this.qualityAttributes;
	}
}
