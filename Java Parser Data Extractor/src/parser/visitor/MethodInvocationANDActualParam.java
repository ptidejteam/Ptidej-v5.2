package parser.visitor;

import org.eclipse.jdt.core.dom.MethodInvocation;
import parser.wrapper.ExtendedASTVisitor;
import parser.wrapper.NamedCompilationUnit;

public class MethodInvocationANDActualParam extends ExtendedASTVisitor {

	@Override
	public boolean visit(final MethodInvocation node) {
		System.out.println("arguments(): " + node.arguments());
		System.out.println("getExpression(): " + node.getExpression());
		System.out.println("getName(): " + node.getName());
		return super.visit(node);
	}

	@Override
	public boolean visit(final NamedCompilationUnit aNamedCompilationUnit) {
		// TODO Auto-generated method stub
		return super.visit(aNamedCompilationUnit);
	}

}
