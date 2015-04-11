package padl.creator.xmiclassdiagram;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.ICodeLevelModelCreator;
import padl.kernel.IConstituent;
import padl.kernel.IContainer;
import padl.kernel.IField;
import padl.kernel.IInterface;
import padl.kernel.IMethod;
import padl.kernel.IPackage;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.path.Finder;
import padl.path.FormatException;
import padl.path.IConstants;
import util.lang.Modifier;
import com.sdmetrics.model.MetaModel;
import com.sdmetrics.model.MetaModelElement;
import com.sdmetrics.model.Model;
import com.sdmetrics.model.ModelElement;
import com.sdmetrics.model.XMIReader;
import com.sdmetrics.model.XMITransformations;
import com.sdmetrics.util.XMLParser;

public final class XMICreator implements ICodeLevelModelCreator {
	private static final String DIRECTORY_BASE =
		"../PADL Creator XMI/lib/SDMetricsOpenCore/test/com/sdmetrics/metrics/";
	private static final String UNDEFINED_TYPE = "UNDEFINED_TYPE";

	private interface IBuilder {
		String getTypeOfConstituent();
		IConstituent instantiateTypeOfConstituent(
			final String elementID,
			final String elementName);
	}

	private final String xmiFile;
	public XMICreator(final String aPathToSomeXMIFile) {
		this.xmiFile = aPathToSomeXMIFile;
	}

	@Override
	public void create(final ICodeLevelModel aCodeLevelModel)
			throws CreationException {

		try {
			final XMLParser parser = new XMLParser();
			final MetaModel metaModel = new MetaModel();
			parser.parse(
				XMICreator.DIRECTORY_BASE + "testMetaModel04.xml",
				metaModel.getSAXParserHandler());

			final XMITransformations trans = new XMITransformations(metaModel);
			parser.parse(
				XMICreator.DIRECTORY_BASE + "testXMITransformations04.xml",
				trans.getSAXParserHandler());

			final Model model = new Model(metaModel);
			final XMIReader xmiReader = new XMIReader(trans, model);
			parser.parse(this.xmiFile, xmiReader);

			// this.print(metaModel, model);

			final MetaModelElement metaModelElement =
				metaModel.getType("model");
			final List<ModelElement> elements =
				model.getAcceptedElements(metaModelElement);
			String modelName = "";
			for (final ModelElement me : elements) {
				final String elementName = me.getName();
				if (!elementName.isEmpty()) {
					modelName = elementName;
					break;
				}
			}
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel(modelName);

			this.build(metaModel, model, codeLevelModel, new IBuilder() {
				@Override
				public IConstituent instantiateTypeOfConstituent(
					final String elementID,
					final String elementName) {

					final IPackage packaje =
						Factory.getInstance().createPackage(
							elementName.toCharArray());
					return packaje;
				}
				@Override
				public String getTypeOfConstituent() {
					return "package";
				}
			});
			this.build(metaModel, model, codeLevelModel, new IBuilder() {
				@Override
				public IConstituent instantiateTypeOfConstituent(
					final String elementID,
					final String elementName) {

					final IInterface interfaze =
						Factory.getInstance().createInterface(
							elementName.toCharArray(),
							elementName.toCharArray());
					return interfaze;
				}
				@Override
				public String getTypeOfConstituent() {
					return "interface";
				}
			});
			this.build(metaModel, model, codeLevelModel, new IBuilder() {
				@Override
				public IConstituent instantiateTypeOfConstituent(
					final String elementID,
					final String elementName) {

					final IClass clazz =
						Factory.getInstance().createClass(
							elementName.toCharArray(),
							elementName.toCharArray());
					return clazz;
				}
				@Override
				public String getTypeOfConstituent() {
					return "class";
				}
			});
			this.build(metaModel, model, codeLevelModel, new IBuilder() {
				@Override
				public IConstituent instantiateTypeOfConstituent(
					final String elementID,
					final String elementName) {

					final IField field =
						Factory.getInstance().createField(
							elementID.toCharArray(),
							elementName.toCharArray(),
							XMICreator.UNDEFINED_TYPE.toCharArray(),
							1);
					return field;
				}
				@Override
				public String getTypeOfConstituent() {
					return "attribute";
				}
			});
			this.build(metaModel, model, codeLevelModel, new IBuilder() {
				@Override
				public IConstituent instantiateTypeOfConstituent(
					final String elementID,
					final String elementName) {

					final IMethod method =
						Factory.getInstance().createMethod(
							elementID.toCharArray(),
							elementName.toCharArray());
					return method;
				}
				@Override
				public String getTypeOfConstituent() {
					return "operation";
				}
			});

			final Iterator<?> iterator =
				codeLevelModel.getConcurrentIteratorOnConstituents();
			while (iterator.hasNext()) {
				final IConstituent constituent = (IConstituent) iterator.next();
				codeLevelModel.removeConstituentFromID(constituent.getID());
				aCodeLevelModel.addConstituent(constituent);
			}
		}
		catch (final Exception e) {
			throw new CreationException(e);
		}
	}

	private void build(
		final MetaModel aMetaModel,
		final Model aModel,
		final ICodeLevelModel aCodeLevelModel,
		final IBuilder aBuilder) throws FormatException {

		final MetaModelElement metaModelElement =
			aMetaModel.getType(aBuilder.getTypeOfConstituent());
		final List<ModelElement> elements =
			aModel.getAcceptedElements(metaModelElement);
		for (final ModelElement me : elements) {
			final ModelElement elementContext = me.getRefAttribute("context");
			final String elementID = me.getPlainAttribute("id");
			final String elementName = me.getPlainAttribute("name");
			String elementVisibility;
			try {
				elementVisibility = me.getPlainAttribute("visibility");
			}
			catch (final IllegalArgumentException e) {
				elementVisibility = "public";
			}

			final StringBuffer elementContextPADLised = new StringBuffer();
			elementContextPADLised.append(IConstants.ABSTRACT_MODEL_SYMBOL);
			elementContextPADLised.append(elementContext
				.getFullName()
				.substring(1)
				.replace('.', IConstants.ELEMENT_SYMBOL));
			elementContextPADLised.append(IConstants.ELEMENT_SYMBOL);

			final IContainer container =
				Finder.findContainer(
					elementContextPADLised.toString(),
					aCodeLevelModel);

			final IConstituent constituent =
				aBuilder.instantiateTypeOfConstituent(elementID, elementName);
			constituent.setVisibility(Modifier.fromString(elementVisibility));
			container.addConstituent(constituent);
		}
	}

	private void print(final MetaModel metaModel, final Model model) {
		// Iterate over all model element types in the metamodel
		for (final MetaModelElement type : metaModel) {
			System.out.println("Elements of type: " + type.getName());

			// iterate over all model elements of the current type
			final List<ModelElement> elements = model.getAcceptedElements(type);
			for (final ModelElement me : elements) {
				System.out.println("  Element: " + me.getFullName() + " ");

				// write out the value of each attribute of the element
				final Collection<String> attributeNames =
					type.getAttributeNames();
				for (final String attr : attributeNames) {
					System.out.print("     Attribute '" + attr);
					if (type.isSetAttribute(attr)) {
						System.out.println("' has set value "
								+ me.getSetAttribute(attr));
					}
					else if (type.isRefAttribute(attr)) {
						System.out.print("' references ");
						ModelElement referenced = me.getRefAttribute(attr);
						System.out.println((referenced == null) ? "nothing"
								: referenced.getFullName());
					}
					else {
						System.out.println("' has value: "
								+ me.getPlainAttribute(attr));
					}
				}
			}
		}
	}
}
