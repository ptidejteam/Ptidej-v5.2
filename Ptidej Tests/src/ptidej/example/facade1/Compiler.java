package ptidej.example.facade1;

public class Compiler {
	private Parser parser;
	private Node node;
	private CodeGenerator codeGenerator;
	public void compile() {
		System.out.println("Compiling...");
		this.parser = new Parser();
		this.parser.parse();
		this.node = new StatementNode();
		this.node.print();
		this.node = new ExpressionNode();
		this.node.print();
		this.codeGenerator = new StackMachineCodeGenerator(this.node);
		this.codeGenerator.generate();
		System.out.println("Done.");
	}
}
