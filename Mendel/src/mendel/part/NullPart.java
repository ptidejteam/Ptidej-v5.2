/* (c) Copyright 2001 and following years, Simon Denier, Yann-Gaël Guéhéneuc,
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
package mendel.part;

import java.util.Collection;
import java.util.Properties;

import mendel.Driver;
import mendel.IPart;

public class NullPart implements IPart {

	private static IPart instance = new NullPart();
	
	public static IPart instance() {
		return instance;
	}

	
	private NullPart() {	}

	public void add(Object result) {	}

	public void addAll(Collection data) {	}

	public void compute() {	}

	public Object compute(Object data) {
		return null;
	}

	public void computeAll() {	}

	public void end() {	}

	public void initialize(Properties prop) {	}

	public void initialize(Driver driver) {	}

	public IPart next() {
		return instance();
	}

	public void run() {	}

	public void runAll() {	}

	public void setNextPart(IPart part) {	}

	public void start() {	}

	public void endMe() {	}

	public void startMe() {	}

	public Driver getDriver() {
		return null; // TODO: return the real driver?
	}

}
