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
package epi.test.quickuml;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author guehene
 */
public class CompositeTool implements Tool {
  private Vector tools = new Vector();

  /**
   * Add a new listener to this tool.
   *
   * @param ToolListener
   */
  public void addToolListener(Tool l) {

    for(Iterator i = this.tools.iterator(); i.hasNext();)
      ((Tool)i.next()).removeToolListener(l);

  }

  /**
   * Remove a listener to this tool.
   *
   * @param ToolListener
   */
  public void removeToolListener(Tool l) {

    for(Iterator i = this.tools.iterator(); i.hasNext();)
      ((Tool)i.next()).removeToolListener(l);

  }

  public void install(Tool diagram) {
    
    for(Iterator i = this.tools.iterator(); i.hasNext();)
      ((Tool)i.next()).install(diagram);

  }

  public void uninstall(Tool diagram) {

    for(Iterator i = this.tools.iterator(); i.hasNext();)
      ((Tool)i.next()).uninstall(diagram);

  }

  public void add(Tool tool) {
    if(!this.tools.contains(tool))
      this.tools.add(tool);
  }

  public void remove(Tool tool) {
    this.tools.remove(tool);
  }

}
