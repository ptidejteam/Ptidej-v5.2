package parser.properties;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class EnclosingTypeProperty implements IProperty {

	public boolean isInterestingNode(final ASTNode node) {

		return node instanceof TypeDeclaration
				|| node instanceof MethodDeclaration
				|| node instanceof FieldDeclaration
				|| node instanceof AnonymousClassDeclaration
				|| node instanceof Initializer
				|| node instanceof VariableDeclarationFragment
				|| node instanceof VariableDeclarationStatement
				|| node instanceof VariableDeclarationExpression
				|| node instanceof SingleVariableDeclaration;
	}

}
