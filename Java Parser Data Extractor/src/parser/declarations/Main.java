package parser.declarations;

import java.util.Arrays;
import parser.input.SourceInputsHolder;
import parser.input.impl.FileListJavaProject;
import parser.visitor.FromalAndActualParameter;
import parser.wrapper.JavaParser;

public class Main {

	public static void main(final String[] args) {
		final String[] classpathEntries = new String[] { "" };
		final String[] sourcepathEntries =
			new String[] { "./rsc/Declarations/" };
		//final String[] sourcepathEntries = new String[] { "./repository/software/FileLevelCommits/tomcat/" };

		SourceInputsHolder javaProject = null;
		try {
			javaProject =
				new FileListJavaProject(
					Arrays.asList(classpathEntries),
					Arrays.asList(sourcepathEntries),
					args[0]);
		}
		catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Start parsing and visiting .....");
		//new JavaParser(javaProject).parse(new Declaration(false));

		//	new JavaParser(javaProject).parse(new VariableAssignment());
		//	try {
		//		VariableAssignment.out.close();
		//	}
		//	catch (IOException e) {
		//		System.out.println("problem with VariableAssignment.out.close()");
		//	}

		//new JavaParser(javaProject).parse(new ComparisonStatement());
		//new JavaParser(javaProject).parse(new MethodInvocationANDActualParam());

		//new JavaParser(javaProject).parse(new ReturnStatementAndReturnTypeAndMethodName());
		new JavaParser(javaProject).parse(new FromalAndActualParameter());

	}
}