/*
 * (c) Copyright 2001, 2002 Hervé Albin-Amiot and Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * Soft-Maint S.A.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.kernel.impl;

import util.io.ProxyConsole;
import util.io.SubtypeLoader;
import util.repository.FileAccessException;
import util.repository.IFileRepository;
import util.repository.IRepository;
import util.repository.impl.FileRepositoryFactory;
import com.ibm.toad.cfparse.ClassFile;

public class ConstituentsRepository implements IRepository {
	private static ConstituentsRepository UniqueInstance;
	public static ConstituentsRepository getInstance() {
		if (ConstituentsRepository.UniqueInstance == null) {
			ConstituentsRepository.UniqueInstance = new ConstituentsRepository();
		}

		return ConstituentsRepository.UniqueInstance;
	}

	private ClassFile[] elements;
	private ClassFile[] entities;
	private ConstituentsRepository() {
	}
	public ClassFile[] getElements() {
		if (this.elements == null) {
			try {
				this.elements =
					SubtypeLoader.loadSubtypesFromStream(
						"padl.kernel.IElementMarker",
						this.getFileRepository().getFiles(),
						"padl.kernel.impl",
						".class");
			}
			catch (final FileAccessException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
		return this.elements;
	}
	public ClassFile[] getEntities() {
		if (this.elements == null) {
			try {
				this.entities =
					SubtypeLoader.loadSubtypesFromStream(
						"padl.kernel.IEntityMarker",
						this.getFileRepository().getFiles(),
						"padl.kernel.impl",
						".class");
			}
			catch (final FileAccessException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
		return this.entities;
	}
	private IFileRepository getFileRepository() {
		return FileRepositoryFactory.getInstance().getFileRepository(this);
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("Type Repository:\n\tEntities\n");
		for (int x = 0; x < this.getEntities().length; x++) {
			buffer.append("\t\t");
			buffer.append(this.getEntities()[x].getName());
			buffer.append('\n');
		}
		buffer.append("\tElements\n");
		for (int x = 0; x < this.getElements().length; x++) {
			buffer.append("\t\t");
			buffer.append(this.getElements()[x].getName());
			buffer.append('\n');
		}
		return buffer.toString();
	}
}