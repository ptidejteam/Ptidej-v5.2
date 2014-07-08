/**
 * Copyright (c) 2008 Simon Denier
 */
package mendel.part.output;

import mendel.model.IEntity;

/**
 * @author Simon Denier
 * @since May 16, 2008
 *
 */
public class VersoVersionOutput extends VersoOutput {

	/* (non-Javadoc)
	 * @see mendel.part.output.VersoOutput#getObjectId(mendel.model.IEntity)
	 */
	@Override
	public String getObjectId(IEntity entity) {
		return getProject().getProjectname() + "." + super.getObjectId(entity);
	}
	
	

}
