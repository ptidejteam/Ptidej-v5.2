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
import java.util.LinkedList;
import java.util.Properties;

import mendel.Driver;
import mendel.IPart;
import mendel.MendelProject;

/**
 * Initializating Properties:
 *  - see subclasses for specific properties
 *  
 * @author deniersi
 *
 */
public abstract class AbstractPart implements IPart {
	
	private Driver driver;
	
	private IPart nextPart;
	
	private LinkedList data;
	
	
	public AbstractPart() {
		this.nextPart = NullPart.instance();
		this.data = new LinkedList();
	}
	
	
	public void initialize(Properties prop) {

	}
	
	public void initialize(Driver driver) {
		this.driver = driver;
	}
	
	public Driver getDriver() {
		return this.driver;
	}
	
	public MendelProject getProject() {
		return getDriver().getProject();
	}
	
	public void setNextPart(IPart part) {
		this.nextPart = part;
	}
	
	public IPart next() {
		return this.nextPart;
	}

	
	public void add(Object data) {
		this.data.add(data);
	}
	
	public void addAll(Collection data) {
		this.data.addAll(data);
	}
	
	protected Collection getData() {
		return this.data;
	}
	
	public void compute() {
		if( !this.data.isEmpty() ) {
			Object result = compute(this.data.removeFirst());
			if( result!=null ) {
				next().add(result);			
			}			
		}
	}
	
	public void computeAll() {
		while( !this.data.isEmpty() ) {
			compute();
		}
	}
	
	public void run() { // TODO: do not call/override if BatchOnly (ex: for threshold computation)
		compute();
		// TODO: we could check for return value and stop if null result
		next().run();
	}
	
	public void runAll() {
		computeAll();
		next().runAll();
	}
	
	
	public void start() {
		startMe();
		next().start();
	}

	public void end() {
		endMe();
		next().end();
	}


	public void endMe() {	}


	public void startMe() {	}
	
}
