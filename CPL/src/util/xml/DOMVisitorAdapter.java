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
package util.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/04/21
 */
public class DOMVisitorAdapter {
	private final Document document;

	public DOMVisitorAdapter(final Document document) {
		this.document = document;
	}
	public void accept(final DOMVisitor aDOMVisitor) {
		aDOMVisitor.open(this.document);
		this.accept(this.document, aDOMVisitor);
		aDOMVisitor.close(this.document);
	}
	private void accept(final Node aNode, final DOMVisitor aDOMVisitor) {
		aDOMVisitor.open(aNode);

		final NodeList nodeList = aNode.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			final Node node = nodeList.item(i);
			this.accept(node, aDOMVisitor);
		}

		aDOMVisitor.close(aNode);
	}
}
