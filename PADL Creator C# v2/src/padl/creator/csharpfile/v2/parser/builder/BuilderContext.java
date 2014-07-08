package padl.creator.csharpfile.v2.parser.builder;

import padl.kernel.ICodeLevelModel;

/**
 * 
 */
public class BuilderContext {

	private final ICodeLevelModel model;
	private int blockCount;

	public BuilderContext(final ICodeLevelModel model) {
		this.model = model;
	}

	public void decrementBlockCount() {
		this.blockCount--;
	}

	public int getBlockCount() {
		return this.blockCount;
	}

	public ICodeLevelModel getModel() {
		return this.model;
	}

	public void incrementBlockCount() {
		this.blockCount++;
	}

}
