package ptidej.reporting.simple.datasource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import ptidej.solver.Occurrence;

/**
 * @author Yann
 * @since  2008/09/26
 */
public class OccurrencesDataSource implements JRDataSource {
	private final Occurrence[] occurrences;
	private int index = -1;

	public OccurrencesDataSource(final Occurrence[] someOccurrences) {
		this.occurrences = someOccurrences;
	}

	public boolean next() throws JRException {
		this.index++;
		return this.index < this.occurrences.length;
	}

	public Object getFieldValue(final JRField field) throws JRException {
		final Object value;

		final String fieldName = field.getName();
		if (fieldName.equals("DesignSmellName")) {
			value = this.occurrences[this.index].getName();
		}
		else if (fieldName.equals("Occurrence")) {
			return this.occurrences[this.index];
		}
		else {
			value = "Unknown field: " + fieldName;
			System.out.println(value);
		}

		return value;
	}
}
