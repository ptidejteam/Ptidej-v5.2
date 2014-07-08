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
package padl.example.composite1;


/**
 * @author  Yann-Gaël Guéhéneuc
 * @version 0.1
 * 
 * One use relationship with String through the
 * parameter of method main().
 * 
 * One association relationship with Document thought the
 * local variable declaration final Document document and
 * the call to method addComponent() on variable document
 * of type Document.
 * 
 * TODO: Remove the creation of an association relationship
 * with Element thourgh the use of method addComponent().
 * 
 * Six creation relationships with Document, Title,
 * Paragraph, IndentedParagraph.
 */
public class Main {
	public static void main(final String[] args) {
		final Document document = new Document();
		document.addComponent(new Title());
		document.addComponent(new Paragraph());
		document.addComponent(new Title());
		document.addComponent(new IndentedParagraph());
		document.addComponent(new IndentedParagraph());

		document.printAll();
	}
}
