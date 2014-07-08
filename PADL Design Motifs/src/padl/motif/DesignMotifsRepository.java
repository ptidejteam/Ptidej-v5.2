/*
 * (c) Copyright 2001-2003 Yann-Gaël Guéhéneuc,
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
package padl.motif;

import java.util.ArrayList;
import java.util.List;
import util.io.ProxyConsole;
import util.io.SubtypeLoader;
import util.repository.FileAccessException;
import util.repository.IRepository;
import util.repository.impl.FileRepositoryFactory;
import com.ibm.toad.cfparse.ClassFile;

public class DesignMotifsRepository implements IRepository {
	private static DesignMotifsRepository UniqueInstance;
	public static DesignMotifsRepository getInstance() {
		if (DesignMotifsRepository.UniqueInstance == null) {
			DesignMotifsRepository.UniqueInstance =
				new DesignMotifsRepository();
		}

		return DesignMotifsRepository.UniqueInstance;
	}
	//	public static void main(final String args[]) {
	//		final PatternRepository aPatternRepository =
	//			PatternRepository.getCurrentPatternRepository();
	//		OutputManager.getCurrentOutputManager().getNormalOutput().println(
	//			aPatternRepository.toString());
	//	}

	private IDesignMotifModel[] designMotifs;
	private DesignMotifsRepository() {
		ClassFile[] classFiles;
		try {
			classFiles =
				SubtypeLoader.loadSubtypesFromStream(
					"padl.motif.IDesignMotifModel",
					FileRepositoryFactory
						.getInstance()
						.getFileRepository(this)
						.getFiles(),
					"padl.motif.repository",
					".class");

			// Yann 2003/10/14: Demo!
			// I must catch the AccessControlException
			// thrown when attempting loading patterns
			// from the applet viewer.

			// Yann 2004/07/28: Demo!
			// From now on, using the IFileRepository class,
			// I only need to check if the list is empty.

			// Yann 2004/12/04: FileRepository.
			// The ConstraintViewerPanel from the Applet demo now provides its
			// own and dedicated implementation of IFileRepository to give
			// access to the Composite design motif.

			//	if (classFiles.length == 0) {
			//		// Yann 2002/11/08: Applet!
			//		// I load hard-coded pattern if I cannot access the file-system.
			//		// Yann 2003/12/08: Bad!
			//		// We should be client-independent.
			//		try {
			//			this.listOfPatterns =
			//				new IPatternModel[] {
			//					(IPatternModel) Class
			//						.forName("padl.pattern.repository.Composite")
			//						.getConstructor(null)
			//						.newInstance(null)};
			//		}
			//		catch (final ClassNotFoundException e) {
			//			e.printStackTrace(
			//				OutputManager.getCurrentOutputManager().getErrorOutput());
			//		}
			//		catch (final InstantiationException e) {
			//			e.printStackTrace(
			//				OutputManager.getCurrentOutputManager().getErrorOutput());
			//		}
			//		catch (final IllegalAccessException e) {
			//			e.printStackTrace(
			//				OutputManager.getCurrentOutputManager().getErrorOutput());
			//		}
			//		catch (final InvocationTargetException e) {
			//			e.printStackTrace(
			//				OutputManager.getCurrentOutputManager().getErrorOutput());
			//		}
			//		catch (final NoSuchMethodException e) {
			//			e.printStackTrace(
			//				OutputManager.getCurrentOutputManager().getErrorOutput());
			//		}
			//	}
			//	else {

			final List listOfPatterns = new ArrayList(classFiles.length);
			for (int i = 0; i < classFiles.length; i++) {
				try {
					listOfPatterns.add((IDesignMotifModel) Class.forName(
						classFiles[i].getName()).newInstance());
				}
				catch (final ClassNotFoundException cnfe) {
					cnfe.printStackTrace(ProxyConsole
						.getInstance()
						.errorOutput());
				}
				catch (final InstantiationException ie) {
					ie
						.printStackTrace(ProxyConsole
							.getInstance()
							.errorOutput());
				}
				catch (final IllegalAccessException iae) {
					iae.printStackTrace(ProxyConsole
						.getInstance()
						.errorOutput());
				}
			}

			this.designMotifs = new IDesignMotifModel[classFiles.length];
			listOfPatterns.toArray(this.designMotifs);
		}
		catch (final FileAccessException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			this.designMotifs = new IDesignMotifModel[0];
		}
	}
	public IDesignMotifModel[] getDesignMotifs() {
		return this.designMotifs;
	}
	//	public void resetAbstractModel(int modelIndex) {
	//		try {
	//			this.listOfPatterns[modelIndex] =
	//				(IDesignMotifModel) this.listOfPatterns[modelIndex]
	//					.getClass()
	//					.getConstructor(null)
	//					.newInstance(null);
	//		}
	//		catch (final Exception e) {
	//			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
	//		}
	//	}
	//	public void resetAbstractModel(String aName) {
	//		for (int x = 0; x < this.listOfPatterns.length; x++)
	//			if (this.listOfPatterns[x].getName().equals(aName)) {
	//				this.resetAbstractModel(x);
	//				return;
	//			}
	//	}
	//	public void resetModels() {
	//		for (int x = 0; x < this.listOfPatterns.length; this
	//			.resetAbstractModel(x++))
	//			;
	//	}
	public String toString() {
		final StringBuffer stringEq = new StringBuffer();
		stringEq.append("Patterns Repository:\n");
		for (int x = 0; x < this.designMotifs.length; x++) {
			stringEq.append("\t");
			stringEq.append(this.designMotifs[x].getName());
			stringEq.append("\n");
		}
		return stringEq.toString();
	}
}