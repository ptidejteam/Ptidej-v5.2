package parser.visitor;

import java.util.Stack;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ReturnStatement;
import parser.wrapper.ExtendedASTVisitor;
import parser.wrapper.NamedCompilationUnit;

public class ReturnStatementAndReturnTypeAndMethodName extends
		ExtendedASTVisitor {

	private final Stack<String> ReturnStatementInfo;
	private CompilationUnit c;

	public ReturnStatementAndReturnTypeAndMethodName() {
		this.ReturnStatementInfo = new Stack<String>();
	}

	@Override
	public void endVisit(final MethodDeclaration node) {
		final int methodStartPosition =
			this.c.getLineNumber(node.getStartPosition());
		final int methodEndPosition =
			this.c.getLineNumber(node.getStartPosition() + node.getLength());
		int returnStatementNmber = 0;
		for (final String info : this.ReturnStatementInfo) {
			final String[] detailInfo = info.split(";");
			if (detailInfo.length > 0) {
				if (Integer.parseInt(detailInfo[0]) >= methodStartPosition
						&& Integer.parseInt(detailInfo[0]) <= methodEndPosition) {
					System.out.println(node.getReturnType2() + ";"
							+ node.getName() + ";" + node.parameters() + ";"
							+ detailInfo[1] + ";" + detailInfo[0]);
					returnStatementNmber++;
				}
				else {
					System.out.println(node.getReturnType2() + ";"
							+ node.getName() + ";" + node.parameters() + ";"
							+ "void");
				}
			}
		}
		for (int i = 0; i < returnStatementNmber; i++) {
			this.ReturnStatementInfo.pop();
		}
		super.endVisit(node);
	}

	@Override
	public boolean visit(final NamedCompilationUnit aNamedCompilationUnit) {
		this.c = aNamedCompilationUnit.getCompilationUnit();
		return super.visit(aNamedCompilationUnit);
	}

	@Override
	public boolean visit(final ReturnStatement node) {
		this.ReturnStatementInfo.push(this.c.getLineNumber(node
			.getExpression()
			.getStartPosition()) + ";" + node.getExpression());
		return super.visit(node);
	}

}
