/*
 * (c) Copyright 2001-2007 Yann-Gaël Guéhéneuc,
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
package epi.test.helper;

import java.io.PrintWriter;
import padl.kernel.IIdiomLevelModel;
import util.io.ProxyDisk;
import epi.MyMain;
import epi.solver.Solution;
import epi.solver.StringBuilder;
import epi.ui.EPIDialog;
import epi.ui.SolutionDialog;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/11/20
 */
public class Adapter {
	public static void main(final String[] args) {
		final IIdiomLevelModel model =
			MyMain.createModel(
				"../Ptidej Tests/bin/ptidej/example/composite2/",
				null);

		//	try {
		//		final IClass client = Factory.getInstance().createClass("Client");
		//		final IClass target = Factory.getInstance().createClass("Target");
		//		final IClass adapter =
		//			Factory.getInstance().createClass("Adapter");
		//		final IClass adaptee =
		//			Factory.getInstance().createClass("Adaptee");
		//	
		//		final IMethod specificRequest =
		//			Factory.getInstance().createMethod("specificRequest");
		//		adaptee.addConstituent(specificRequest);
		//	
		//		final IAssociation associationAdapterAdaptee =
		//			Factory.getInstance().createAssociationRelationship(
		//				"request",
		//				adaptee,
		//				1);
		//		adapter.addConstituent(associationAdapterAdaptee);
		//		final IDelegatingMethod request =
		//			Factory.getInstance().createDelegatingMethod(
		//				"request",
		//				associationAdapterAdaptee,
		//				specificRequest);
		//		adapter.addConstituent(request);
		//		adapter.addInheritedEntity(target);
		//	
		//		final IMethod abstractRequest =
		//			Factory.getInstance().createMethod("request");
		//		abstractRequest.setAbstract(true);
		//		target.setAbstract(true);
		//		target.addConstituent(abstractRequest);
		//	
		//		final IAssociation associationClientTarget =
		//			Factory.getInstance().createAssociationRelationship(
		//				"request",
		//				target,
		//				1);
		//		client.addConstituent(associationClientTarget);
		//		final IDelegatingMethod method =
		//			Factory.getInstance().createDelegatingMethod(
		//				"foo",
		//				associationClientTarget,
		//				abstractRequest);
		//		client.addConstituent(method);
		//	
		//		model.addConstituent(client);
		//		model.addConstituent(target);
		//		model.addConstituent(adapter);
		//		model.addConstituent(adaptee);
		//	}
		//	catch (final ModelDeclarationException e) {
		//		e.printStackTrace();
		//	}

		final String programString = StringBuilder.buildModelString(model);
		final EPIDialog inst = new EPIDialog(null, true, model, programString);
		inst.setVisible(true);

		//TODO: if(closewindow et pas solve!)
		Solution[] sol = inst.getSolutions();
		PrintWriter out =
			new PrintWriter(ProxyDisk.getInstance().fileTempOutput(
				"rsc/" + inst.getLogger().getIdentificationName() + ".txt"));
		Solution.print(sol, out);

		SolutionDialog inst2 = new SolutionDialog(null, false);
		inst2.setData(inst.getLogger());
		inst2.show();
	}
}
