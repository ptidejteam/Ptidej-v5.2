package parser.visitor;

import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRefParameter;
import parser.wrapper.ExtendedASTVisitor;
import parser.wrapper.NamedCompilationUnit;

public class FromalAndActualParameter extends ExtendedASTVisitor {

	@Override
	public boolean visit(final MethodInvocation node) {

		System.out.println(node.getExpression() + " " + node.getName() + "  "
				+ node.arguments());
		System.out.println("resolveMethodBinding: "
				+ node.resolveMethodBinding());
		return super.visit(node);
	}

	@Override
	public boolean visit(final MethodRefParameter node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public boolean visit(final NamedCompilationUnit aNamedCompilationUnit) {
		aNamedCompilationUnit.getCompilationUnit();
		return super.visit(aNamedCompilationUnit);
	}

}
