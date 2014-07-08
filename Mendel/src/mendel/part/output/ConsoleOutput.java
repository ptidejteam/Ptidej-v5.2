/**
 * Copyright (c) 2008 Simon Denier
 */
package mendel.part.output;

import mendel.part.AbstractPart;

/**
 * 
 * Initializating Properties:
 * 
 * Input: Object
 * Output: Object
 * 
 * @author Simon Denier
 * @since Feb 15, 2008
 * 
 */
public class ConsoleOutput extends AbstractPart {
	
	public ConsoleOutput() {}


	public Object compute(Object data) {
		System.out.println(data.toString());
		return data;
	}

}
