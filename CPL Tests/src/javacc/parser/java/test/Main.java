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
package javacc.parser.java.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import util.parser.java.v14.JavaParser;
import util.parser.java.v14.ParseException;
import util.parser.java.v14.nodes.BlockStatement;
import util.parser.java.v14.nodes.CompilationUnit;
import util.parser.java.v14.visitors.DepthFirstVisitor;

/**
 * @author Yann-Gaël Guéhéneuc
 */
public class Main {
	public static void main(final String[] args) {
		try {
			new JavaParser(new FileInputStream(
				"src/javacc/parser/java/test/Main.java"));
			final CompilationUnit cu = JavaParser.CompilationUnit();
			cu.accept(new DepthFirstVisitor() {
				//		public void visit(NodeList n) {
				//		}
				//		public void visit(NodeListOptional n) {
				//		}
				//		public void visit(NodeOptional n) {
				//		}
				//		public void visit(NodeSequence n) {
				//		}
				//		public void visit(NodeToken n) {
				//		}
				//		public void visit(CompilationUnit n) {
				//		}
				//		public void visit(PackageDeclaration n) {
				//			System.out.println("Package!");
				//		}
				//		public void visit(ImportDeclaration n) {
				//		}
				//		public void visit(TypeDeclaration n) {
				//		}
				//		public void visit(ClassDeclaration n) {
				//		}
				//		public void visit(UnmodifiedClassDeclaration n) {
				//		}
				//		public void visit(ClassBody n) {
				//		}
				//		public void visit(NestedClassDeclaration n) {
				//		}
				//		public void visit(ClassBodyDeclaration n) {
				//		}
				//		public void visit(MethodDeclarationLookahead n) {
				//		}
				//		public void visit(InterfaceDeclaration n) {
				//		}
				//		public void visit(NestedInterfaceDeclaration n) {
				//		}
				//		public void visit(UnmodifiedInterfaceDeclaration n) {
				//		}
				//		public void visit(InterfaceMemberDeclaration n) {
				//		}
				//		public void visit(FieldDeclaration n) {
				//		}
				//		public void visit(VariableDeclarator n) {
				//		}
				//		public void visit(VariableDeclaratorId n) {
				//		}
				//		public void visit(VariableInitializer n) {
				//		}
				//		public void visit(ArrayInitializer n) {
				//		}
				//		public void visit(MethodDeclaration n) {
				//		}
				//		public void visit(MethodDeclarator n) {
				//		}
				//		public void visit(FormalParameters n) {
				//		}
				//		public void visit(FormalParameter n) {
				//		}
				//		public void visit(ConstructorDeclaration n) {
				//		}
				//		public void visit(ExplicitConstructorInvocation n) {
				//		}
				//		public void visit(Initializer n) {
				//		}
				//		public void visit(Type n) {
				//		}
				//		public void visit(PrimitiveType n) {
				//		}
				//		public void visit(ResultType n) {
				//		}
				//		public void visit(Name n) {
				//		}
				//		public void visit(NameList n) {
				//		}
				//		public void visit(Expression n) {
				//		}
				//		public void visit(AssignmentOperator n) {
				//		}
				//		public void visit(ConditionalExpression n) {
				//		}
				//		public void visit(ConditionalOrExpression n) {
				//		}
				//		public void visit(ConditionalAndExpression n) {
				//		}
				//		public void visit(InclusiveOrExpression n) {
				//		}
				//		public void visit(ExclusiveOrExpression n) {
				//		}
				//		public void visit(AndExpression n) {
				//		}
				//		public void visit(EqualityExpression n) {
				//		}
				//		public void visit(InstanceOfExpression n) {
				//		}
				//		public void visit(RelationalExpression n) {
				//		}
				//		public void visit(ShiftExpression n) {
				//		}
				//		public void visit(AdditiveExpression n) {
				//		}
				//		public void visit(MultiplicativeExpression n) {
				//		}
				//		public void visit(UnaryExpression n) {
				//		}
				//		public void visit(PreIncrementExpression n) {
				//		}
				//		public void visit(PreDecrementExpression n) {
				//		}
				//		public void visit(UnaryExpressionNotPlusMinus n) {
				//		}
				//		public void visit(CastLookahead n) {
				//		}
				//		public void visit(PostfixExpression n) {
				//		}
				//		public void visit(CastExpression n) {
				//		}
				//		public void visit(PrimaryExpression n) {
				//		}
				//		public void visit(PrimaryPrefix n) {
				//		}
				//		public void visit(PrimarySuffix n) {
				//		}
				//		public void visit(Literal n) {
				//		}
				//		public void visit(BooleanLiteral n) {
				//		}
				//		public void visit(NullLiteral n) {
				//		}
				//		public void visit(Arguments n) {
				//		}
				//		public void visit(ArgumentList n) {
				//		}
				//		public void visit(AllocationExpression n) {
				//		}
				//		public void visit(ArrayDimsAndInits n) {
				//		}
				//		public void visit(Statement n) {
				//		}
				//		public void visit(LabeledStatement n) {
				//		}
				//		public void visit(final Block n) {
				//		}
				public void visit(BlockStatement n) {
					System.out.println("A block statement!");
					System.out.println(n.f0.choice);
					n.f0.accept(this);
				}
				//		public void visit(LocalVariableDeclaration n) {
				//		}
				//		public void visit(EmptyStatement n) {
				//		}
				//		public void visit(StatementExpression n) {
				//		}
				//		public void visit(SwitchStatement n) {
				//		}
				//		public void visit(SwitchLabel n) {
				//		}
				//		public void visit(IfStatement n) {
				//		}
				//		public void visit(WhileStatement n) {
				//		}
				//		public void visit(DoStatement n) {
				//		}
				//		public void visit(ForStatement n) {
				//		}
				//		public void visit(ForInit n) {
				//		}
				//		public void visit(StatementExpressionList n) {
				//		}
				//		public void visit(ForUpdate n) {
				//		}
				//		public void visit(BreakStatement n) {
				//		}
				//		public void visit(ContinueStatement n) {
				//		}
				//		public void visit(ReturnStatement n) {
				//		}
				//		public void visit(ThrowStatement n) {
				//		}
				//		public void visit(SynchronizedStatement n) {
				//		}
				//		public void visit(TryStatement n) {
				//		}
				//		public void visit(AssertStatement n) {
				//		}
			});
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (final ParseException e) {
			e.printStackTrace();
		}
	}
}
