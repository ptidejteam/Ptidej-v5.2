/*
 * (c) Copyright 2000-2003 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.analysis;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import util.io.ProxyConsole;
import util.io.SubtypeLoader;
import util.multilingual.MultilingualManager;
import util.repository.FileAccessException;
import util.repository.IRepository;
import util.repository.impl.FileRepositoryFactory;
import com.ibm.toad.cfparse.ClassFile;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/05/20
 */
public class AnalysesRepository implements IRepository {
	private static AnalysesRepository UniqueInstance;
	public static AnalysesRepository getInstance() {
		if (AnalysesRepository.UniqueInstance == null) {
			AnalysesRepository.UniqueInstance = new AnalysesRepository();
		}
		return AnalysesRepository.UniqueInstance;
	}
	//	public static void main(final String args[]) {
	//		final Repository analysisManager =
	//			Repository.getCurrentAnalysisManager();
	//		System.out.println(analysisManager);
	//	}

	private IAnalysis[] analyses;
	private AnalysesRepository() {
		// Yann 2003/10/14: Demo!
		// I must catch the AccessControlException
		// thrown when attempting loading analyses
		// from the applet viewer.
		try {
			final ClassFile[] classFiles =
				SubtypeLoader.loadSubtypesFromStream(
					"padl.analysis.IAnalysis",
					FileRepositoryFactory
						.getInstance()
						.getFileRepository(this)
						.getFiles(),
					"padl.analysis.repository",
					".class");
			final List listOfAnalyses = new ArrayList(classFiles.length);
			for (int i = 0; i < classFiles.length; i++) {
				try {
					listOfAnalyses.add((IAnalysis) Class.forName(
						classFiles[i].getName()).newInstance());
				}
				// Yann 2003/10/07: Protection!
				// I want to make sure that any problem in this
				// section won't break the program...
				//	catch (final ClassNotFoundException cnfe) {
				//		// cnfe.printStackTrace();
				//	}
				//	catch (final InstantiationException ie) {
				//		// ie.printStackTrace();
				//	}
				//	catch (final IllegalAccessException iae) {
				//		// iae.printStackTrace();
				//	}
				//	catch (final NoClassDefFoundError ncdfe) {
				catch (final Throwable t) {
					System.err.println(MultilingualManager
						.getString(
							"LOAD_ANALYSIS",
							AnalysesRepository.class,
							new Object[] { classFiles[i].getName(),
									t.getMessage() }));
				}
			}

			this.analyses = new IAnalysis[listOfAnalyses.size()];
			listOfAnalyses.toArray(this.analyses);
		}
		catch (final AccessControlException ace) {
			this.analyses = new IAnalysis[0];
		}
		catch (final FileAccessException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			this.analyses = new IAnalysis[0];
		}
	}
	public IAnalysis[] getAnalyses() {
		return this.analyses;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("Analysis Repository:\n");
		for (int x = 0; x < this.getAnalyses().length; x++) {
			buffer.append('\t');
			buffer.append(this.getAnalyses()[x].getName());
			buffer.append('\n');
		}
		return buffer.toString();
	}
}
