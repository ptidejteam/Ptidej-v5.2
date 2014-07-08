/*
 * (c) Copyright 2003-2006 Jean-Yves Guyomarc'h,
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
package padl.creator.aspectjlst.util;

import java.util.Iterator;
import org.aspectj.asm.IProgramElement;
import org.aspectj.asm.IRelationship;
import org.aspectj.asm.IRelationshipMap;

/**
 * @author Jean-Yves Guyomarc'h
 * @since 2005-10-18
 */
public abstract class AjcInfo {

	public static String getInfoOnNode(
		final IProgramElement node,
		final IRelationshipMap imap) {
		final StringBuffer buffer = new StringBuffer();

		buffer.append("Infos on node: " + node.getName() + "\n");
		buffer.append("\tAccessibility= " + node.getAccessibility() + "\n");
		buffer.append("\tHandleIdentifier= " + node.getHandleIdentifier()
				+ "\n");
		buffer.append("\tBytecodeName= " + node.getBytecodeName() + "\n");
		buffer.append("\tBytecodeSignature = " + node.getBytecodeSignature()
				+ "\n");
		buffer.append("\tCorrespondingType = " + node.getCorrespondingType()
				+ "\n");
		buffer.append("\tDeclaringType = " + node.getDeclaringType() + "\n");
		buffer.append("\tDetails = " + node.getDetails() + "\n");
		buffer.append("\tPackageName = " + node.getPackageName() + "\n");
		buffer
			.append("\tSourceSignature = " + node.getSourceSignature() + "\n");
		buffer.append("\tExtraInformation = " + node.getExtraInfo() + "\n");
		buffer.append("\tKind = " + node.getKind() + "\n");
		buffer.append("\tMessage = " + node.getMessage() + "\n");
		buffer.append("\tSourceLocation = "
				+ node.getSourceLocation().getContext() + "\n");
		buffer.append("\tToStrings:\n");
		buffer.append("\tLabelString: " + node.toLabelString() + "\n");
		buffer.append("\tLinkLabelString: " + node.toLinkLabelString() + "\n");
		buffer.append("\tLongString: " + node.toLongString() + "\n");
		buffer.append("\tSignatureString: " + node.toSignatureString() + "\n");
		buffer.append("Parameters Type: ");
		final Iterator ite_param_type = node.getParameterTypes().iterator();
		while (ite_param_type.hasNext()) {
			buffer.append(ite_param_type.next() + ", ");
		}

		buffer.append("Parameters Name: ");
		final Iterator ite_param_name = node.getParameterNames().iterator();
		while (ite_param_name.hasNext()) {
			buffer.append(ite_param_name.next() + ", ");
		}
		buffer.append("Childrens: \n");
		buffer.append("\t");
		final Iterator ite = node.getChildren().iterator();
		while (ite.hasNext()) {
			final IProgramElement child = (IProgramElement) ite.next();
			buffer.append(child.getName() + ", ");
		}
		buffer.append("RelationShips: \n");
		buffer.append("\t");

		if (imap != null && imap.get(node) != null) {
			final Iterator iteRel = imap.get(node).iterator();
			while (iteRel.hasNext()) {
				final IRelationship rel = (IRelationship) iteRel.next();
				buffer.append(rel.getKind().toString() + " " + rel.getName()
						+ ": ");
				final Iterator iteTargets = rel.getTargets().iterator();
				while (iteTargets.hasNext()) {
					final String target = (String) iteTargets.next();
					buffer.append(target + ", ");
				}
			}
		}
		return buffer.toString();
	}

	public static String getLightInfoOnNode(
		final IProgramElement node,
		final IRelationshipMap imap) {
		final StringBuffer buffer = new StringBuffer();

		buffer.append("Infos on node: " + node.getName() + "\n");
		buffer.append("\tAccessibility= " + node.getAccessibility() + "\n");
		buffer.append("\tHandleIdentifier= " + node.getHandleIdentifier()
				+ "\n");
		buffer.append("\tBytecodeName= " + node.getBytecodeName() + "\n");
		buffer.append("\tBytecodeSignature = " + node.getBytecodeSignature()
				+ "\n");
		buffer.append("\tCorrespondingType = " + node.getCorrespondingType()
				+ "\n");
		buffer.append("\tDeclaringType = " + node.getDeclaringType() + "\n");
		buffer.append("\tDetails = " + node.getDetails() + "\n");
		buffer.append("\tPackageName = " + node.getPackageName() + "\n");
		buffer
			.append("\tSourceSignature = " + node.getSourceSignature() + "\n");
		buffer.append("\tExtraInformation = " + node.getExtraInfo() + "\n");
		buffer.append("\tKind = " + node.getKind() + "\n");
		buffer.append("\tMessage = " + node.getMessage() + "\n");
		buffer.append("\tSourceLocation = " + node.getSourceLocation() + "\n");
		buffer.append("\tToStrings:\n");
		buffer.append("\tLabelString: " + node.toLabelString() + "\n");
		buffer.append("\tLinkLabelString: " + node.toLinkLabelString() + "\n");
		buffer.append("\tLongString: " + node.toLongString() + "\n");
		buffer.append("\tSignatureString: " + node.toSignatureString() + "\n");
		buffer.append("Childrens: \n");
		buffer.append("\t");
		final Iterator ite = node.getChildren().iterator();
		while (ite.hasNext()) {
			final IProgramElement child = (IProgramElement) ite.next();
			buffer.append(child.getName() + ", ");
		}
		//		buffer.append("RelationShips: \n");
		//		buffer.append("\t");
		//		Iterator iteRel = imap.get(node).iterator();
		//		while(iteRel.hasNext()){
		//			IRelationship rel = (IRelationship)iteRel.next();
		//			buffer.append(rel.getKind().toString() + " " + rel.getName() + ": ");
		//			Iterator iteTargets = rel.getTargets().iterator();
		//			while(iteTargets.hasNext()){
		//				String target = (String)iteTargets.next();
		//				buffer.append(target + ", ");
		//			}
		//		}
		return buffer.toString();
	}
}
