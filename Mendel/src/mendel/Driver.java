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
package mendel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

/**
 * Initializating Properties:
 * - driver: qualified classname of the IDriver class to use
 * - parts: comma-separated list of qualified classnames of IPart2
 * - see subclasses and IPart classes for specific properties
 * 
 * @author deniersi
 *
 */
public class Driver {
	
	private MendelProject project;
	
	private IPart firstPart;
	
	// Basically an easy but bad way to pass metadata around (like headers)
	private Map properties;
	
	
	public Driver() {
		this.properties = new HashMap();
	}
	
	public void initialize(MendelProject project, Properties prop) {
		String[] partNames = Util.extractValues(prop, "parts");
		Vector parts = new Vector();
		for (int i = 0; i < partNames.length; i++) {
			IPart part = (IPart) Util.createInstance(partNames[i]);
			part.initialize(prop);
			parts.add(part);
		}
		initialize(project, parts);
	}
	
	public void initialize(MendelProject project, List parts) {
		this.project = project;
		IPart previous = null;
		for (Iterator it = parts.iterator(); it.hasNext();) {
			IPart part = (IPart) it.next();
			part.initialize(this);
			previous = addPart(part, previous);
		}
	}
	
	public IPart addPart(IPart part, IPart previous) {
		if( firstPart()==null ) {
			this.firstPart = part;
		} else {
			previous.setNextPart(part);
		}
		return part;
	}
	
	public MendelProject getProject() {
		return this.project;
	}
	
	public void setProperty(Object key, Object value) {
		this.properties.put(key, value);
	}
	
	public Object getProperty(Object key) {
		return this.properties.get(key);
	}
	

	public IPart firstPart() {
		return this.firstPart;
//		return ((AbstractTool) this.parts.getFirst());
	}
	
	
	public void start() {
		firstPart().start();
//		for (Iterator it = this.parts.iterator(); it.hasNext();) {
//			((AbstractTool) it.next()).start();
//		}
	}

	public void end() {
		firstPart().end();
//		for (Iterator it = this.parts.iterator(); it.hasNext();) {
//			((AbstractTool) it.next()).end();
//		}		
	}

	
	public void setInput(Collection data) {
		firstPart().addAll(data);
	}

	public void run() {
		firstPart().run();
	}

	public void runAll() {
		firstPart().runAll();
	}
	
//	public void run() {
//		for (Iterator it = this.parts.iterator(); it.hasNext();) {
//			((AbstractTool) it.next()).compute();
//		}
//	}
//	
//	public void runAll() {
//		for (Iterator it = this.parts.iterator(); it.hasNext();) {
//			((AbstractTool) it.next()).computeAll();
//		}
//	}

}
