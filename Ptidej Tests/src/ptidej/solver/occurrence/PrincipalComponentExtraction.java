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
package ptidej.solver.occurrence;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import util.xml.DOMVisitor;
import util.xml.DOMVisitorAdapter;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/0/07
 */
public class PrincipalComponentExtraction {
	public static void main(final String[] args) {
		final PrincipalComponentExtraction extractor =
			new PrincipalComponentExtraction();
		PrincipalComponents.print(extractor.xmlExtraction(
			"../SAD Tests/rsc/Antipatterns in Xerces v2.7.0.xml",
			new char[][] { "blob".toCharArray() }));
	}
	public ReducedOccurrence[] xmlExtraction(
		final String anXMLFilePath,
		final char[][] somePrincipalComponents) {

		try {
			final DocumentBuilderFactory factory =
				DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File(anXMLFilePath));

			final SortedSet principalComponents = new TreeSet();
			new DOMVisitorAdapter(document).accept(new DOMVisitor() {
				public void open(final Document aDocument) {
				}
				public void close(final Document aDocument) {
				}
				public void open(final Node aNode) {
					for (int i = 0; i < somePrincipalComponents.length; i++) {
						final char[] name = somePrincipalComponents[i];
						if (Arrays.equals(
							aNode.getNodeName().toCharArray(),
							name)) {
							principalComponents.add(aNode
								.getFirstChild()
								.getFirstChild()
								.getNodeValue());
						}
					}
				}
				public void close(final Node aNode) {
				}
			});

			final ReducedOccurrence[] sortedPrincipalComponents =
				new ReducedOccurrence[principalComponents.size()];
			principalComponents.toArray(sortedPrincipalComponents);
			return sortedPrincipalComponents;
		}
		catch (final ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (final SAXException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}

		return new ReducedOccurrence[0];
	}
}
