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
package padl.micropattern;

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
 * @since  2007/02/13
 */
public class MicroPatternDetectionsRepository implements IRepository {
	private static MicroPatternDetectionsRepository UniqueInstance;

	public static MicroPatternDetectionsRepository getInstance() {
		if (MicroPatternDetectionsRepository.UniqueInstance == null) {
			MicroPatternDetectionsRepository.UniqueInstance =
				new MicroPatternDetectionsRepository();
		}
		return MicroPatternDetectionsRepository.UniqueInstance;
	}

	private IMicroPatternDetection[] detections;
	private MicroPatternDetectionsRepository() {
	}
	public IMicroPatternDetection[] getMicroPatternDetections() {
		if (this.detections == null) {
			// Yann 2003/10/14: Demo!
			// I must catch the AccessControlException
			// thrown when attempting loading analyses
			// from the applet viewer.
			try {
				final ClassFile[] classFiles =
					SubtypeLoader.loadSubtypesFromStream(
						"padl.micropattern.IMicroPatternDetection",
						FileRepositoryFactory
							.getInstance()
							.getFileRepository(this)
							.getFiles(),
						"padl.micropattern.repository",
						".class");
				final List listOfDetections = new ArrayList(classFiles.length);
				for (int i = 0; i < classFiles.length; i++) {
					try {
						listOfDetections.add((IMicroPatternDetection) Class
							.forName(classFiles[i].getName())
							.newInstance());
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
						System.err.println(MultilingualManager.getString(
							"LOAD_ANALYSIS",
							MicroPatternDetectionsRepository.class,
							new Object[] { classFiles[i].getName(),
									t.getMessage() }));
					}
				}

				this.detections =
					new IMicroPatternDetection[listOfDetections.size()];
				listOfDetections.toArray(this.detections);
			}
			catch (final AccessControlException ace) {
				this.detections = new IMicroPatternDetection[0];
			}
			catch (final FileAccessException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				this.detections = new IMicroPatternDetection[0];
			}
		}
		return this.detections;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("Micro-pattern Detection Repository:\n");
		for (int x = 0; x < this.getMicroPatternDetections().length; x++) {
			buffer.append('\t');
			buffer.append(this.getMicroPatternDetections()[x].getName());
			buffer.append('\n');
		}
		return buffer.toString();
	}
}
