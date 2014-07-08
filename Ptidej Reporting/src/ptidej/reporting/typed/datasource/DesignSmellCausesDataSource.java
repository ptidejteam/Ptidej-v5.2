package ptidej.reporting.typed.datasource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import ptidej.reporting.typed.occurrence.DesignSmellCause;

/**
 * @author Yann
 * @since  2008/09/27
 */
public class DesignSmellCausesDataSource implements JRDataSource {
	private final DesignSmellCause[] designSmellCauses;
	private int index = -1;

	public DesignSmellCausesDataSource(
		final DesignSmellCause[] someDesignSmellCauses) {

		this.designSmellCauses = someDesignSmellCauses;
	}

	public boolean next() throws JRException {
		this.index++;
		return this.index < this.designSmellCauses.length;
	}

	public Object getFieldValue(final JRField field) throws JRException {
		final Object value;

		final String fieldName = field.getName();
		if (fieldName.equals("Name")) {
			value = this.designSmellCauses[this.index].getName();
		}
		else if (fieldName.equals("Value")) {
			value = this.designSmellCauses[this.index].getValue();
		}
		else {
			value = "Unknown field: " + fieldName;
			System.err.println(value);
		}

		return value;
	}
}
