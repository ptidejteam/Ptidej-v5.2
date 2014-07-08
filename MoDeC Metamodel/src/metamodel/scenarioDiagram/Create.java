package metamodel.scenarioDiagram;

import java.util.Iterator;
import java.util.List;

public class Create extends metamodel.scenarioDiagram.Message {

	/**
	 * Constructor
	 * @param sd
	 * @param header
	 * @param info
	 */
	public Create(
		String signature,
		List arguments,
		Classifier sourceClassifier,
		Classifier destinationClassifier) {
		super(signature, arguments, sourceClassifier, destinationClassifier);
	}

	//	public Create (String info)
	//	{
	//		super(info);
	//	}
	//	
	public String toString() {
		String info = super.toString() + "<CREATE>" + this.signature + " (";
		Iterator lt = (Iterator) this.arguments.iterator();
		while (lt.hasNext())
			info += (Argument) lt.next() + ", ";

		if (info.lastIndexOf(",") != -1)
			info = info.substring(0, info.lastIndexOf(","));

		return info
			+ ") CALLEE "
			+ this.destinationClassifier
			+ " CALLER "
			+ this.sourceClassifier
			+ "\n";
	}

}
