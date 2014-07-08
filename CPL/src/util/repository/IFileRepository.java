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
package util.repository;

import util.io.NamedInputStream;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/07/21
 * 
 * It must be possible to seamlessly deal with the
 * files of the meta-model, either from disk or from
 * a jar file (for the Eclipse plug-in). From now on,
 * all meta-model file are first stored into a unique
 * instance of FileRepository, then the other repositories
 * (constituents, patterns, visitors) use this instance
 * to find the files they need.
 */
public interface IFileRepository {
	NamedInputStream[] getFiles() throws FileAccessException;
}