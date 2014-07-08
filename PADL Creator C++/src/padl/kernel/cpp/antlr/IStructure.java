/*
 * (c) Copyright 2004 Sébastien Robidoux, Ward Flores,
 * Universite de Montreal
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
 * Created 2004/08/10
 */
package padl.kernel.cpp.antlr;

import padl.kernel.IFirstClassEntity;

/**
 * @author floresvw
 */

//Ward 2004/08/19: Hierarchy level
//For now IStructure is created at the same level as IClass.
//So, it has all the inheritance properties.
//Implementation to be checked ...

public interface IStructure extends IFirstClassEntity {
	String LOGO = "\"S\"";

	boolean isForceAbstract();
	void setAbstract(final boolean aBoolean);
	void setVisibility(final int aVisibility);
}
