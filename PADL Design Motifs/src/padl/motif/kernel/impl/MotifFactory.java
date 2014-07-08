/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * (c) Copyright 2012-2012 Sébastien Colladon,
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
package padl.motif.kernel.impl;

import padl.kernel.IFactory;
import padl.kernel.impl.Factory;
import padl.motif.IDesignMotifModel;
import padl.motif.kernel.IDesignLevelModel;
import padl.motif.kernel.IMotifFactory;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since 2013/07/11
 */
public class MotifFactory extends Factory implements IMotifFactory {
	private static final long serialVersionUID = 3342247491732965777L;

	private static IFactory UniqueInstance;
	public static IFactory getInstance() {
		if (MotifFactory.UniqueInstance == null) {
			MotifFactory.UniqueInstance = new MotifFactory();
		}
		return MotifFactory.UniqueInstance;
	}

	private MotifFactory() {
	}

	public IDesignLevelModel createDesignLevelModel(char[] anID) {
		final IDesignLevelModel designLevelModel = new DesignLevelModel(anID);
		((DesignLevelModel) designLevelModel).setFactory(this);
		((DesignLevelModel) designLevelModel).setEventGenerator(this
			.getEventGenerator());
		return designLevelModel;
	}
	public IDesignMotifModel createDesignMotifModel(char[] anID) {
		final IDesignMotifModel designMotifModel = new DesignMotifModel(anID);
		((DesignMotifModel) designMotifModel).setFactory(this);
		// TODO Shouldn't it be implemented? 
		//	((DesignMotifModel) designMotifModel).setEventGenerator(this.getEventGenerator());
		return designMotifModel;
	}
}
