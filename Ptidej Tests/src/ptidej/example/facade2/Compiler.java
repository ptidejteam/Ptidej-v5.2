package ptidej.example.facade2;

public class Compiler {
	private Parser parser;
	private CodeGenerator codeGenerator;
	public void compile() {
		System.out.println("Compiling...");
		this.parser = new Parser();
		this.parser.parse();
		new ProgramNodeBuilder();
		this.codeGenerator = new CodeGenerator();
		this.codeGenerator.generate();
		System.out.println("Done.");
	}
}
