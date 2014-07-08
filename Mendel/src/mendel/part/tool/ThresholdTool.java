/* (c) Copyright 2001 and following years, Simon Denier, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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
package mendel.part.tool;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import mendel.Util;
import mendel.part.AbstractPart;

import org.jfree.data.statistics.Statistics;

/**
 * Initializating Properties:
 *
 * Input: Map<metric name, metric result>
 * Output: Map<metric name, metric result>
 * 
 * @author Simon Denier
 * @since Mar 17, 2008
 *
 */
public class ThresholdTool extends AbstractPart {
	// TODO: implements BatchOnly

	private String key;
	
	private String tag;
	
	private double threshold;
	

	public void initialize(String key, String tag) {
		setKey(key);
		setTag(tag);
	}
	
	@Override
	public void initialize(Properties prop) {
		super.initialize(prop);
		setKey(prop.getProperty("key"));
		setTag(prop.getProperty("tag"));
	}
	
	
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}


	@Override
	public void run() {
		System.err.println("Warning! Should not run this tool until all input data is collected. False results");
		super.run();
	}

	
	@Override
	public void startMe() {
		super.startMe();
		// TODO: yet another bad-looking metadata management
		// shoud modify MapToArray to manage that
		// or use a Headers class as metadata
		String[] headers = (String[]) getDriver().getProperty("headers");
		String[] newHeaders = new String[headers.length + 1];
		System.arraycopy(headers, 0, newHeaders, 0, headers.length);
		newHeaders[headers.length] = "Name";
		getDriver().setProperty("headers", newHeaders);
	}


	public Object compute(Object data) {
		Map record = (Map) data;
		Double value = new Double((String) record.get(this.key));
		if( value.doubleValue() >= this.threshold ) {
			addTagToRecord(record);
			return record;
		} else
			return null;
	}

	public void addTagToRecord(Map record) {
		record.put("Name", this.tag);
	}

	
	@Override
	public void computeAll() {
		String[] data = selectDataByKey(getData());
		this.threshold = standardThreshold(Util.convertToNumbers(data));
		System.out.println(this.tag + " threshold: " + this.threshold); // TODO: add it as a record?
		super.computeAll();
	}
	
	public String[] selectDataByKey(Collection objects) {
		return (String[]) Util.selectDataByKey(objects, this.key).toArray(new String[0]);
	}

	
	public double standardThreshold(Number[] data) {
		double mean = Statistics.calculateMean(data);
		double dev = Statistics.getStdDev(data);
		return mean + dev;
	}

}
