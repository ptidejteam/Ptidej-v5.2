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

import java.util.Map;
import java.util.Properties;

import mendel.Util;
import mendel.part.AbstractPart;

import org.jfree.data.statistics.Statistics;

/**
 * Initializating Properties:
 * - keys: comma-separated list of metric names to retrieve
 * - tags: comma-separated list of tags to apply to entity above threshold
 * keys and tags are paired (one key links to the corresponding tag in list order)
 *
 * Input: Map<metric name, metric result>
 * Output: Map<metric name, metric result>
 * 
 * @author Simon Denier
 * @since Mar 17, 2008
 *
 */
public class ThresholdTagTool extends AbstractPart {
	// TODO: implements BatchOnly

	private String[] keys;
	
	private String[] tags;
	
	private double[] thresholds;
	

	public void initialize(String[] keys, String[] tags) {
		setKeys(keys);
		setTags(tags);
	}
	
	@Override
	public void initialize(Properties prop) {
		super.initialize(prop);
		setKeys(Util.extractValues(prop, "keys"));
		setTags(Util.extractValues(prop, "tags"));
		this.thresholds = new double[getKeys().length];
	}
	
	public int keysSize() {
		return getKeys().length;
	}
	
	public String key(int i) {
		return getKeys()[i];
	}
	
	public String[] getKeys() {
		return this.keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
	public String tag(int i) {
		return getTags()[i];
	}

	public String[] getTags() {
		return this.tags;
	}

	public void setTags(String[] tag) {
		this.tags = tag;
	}
	
	public double[] getThresholds() {
		return this.thresholds;
	}
	
	public double threshold(int i) {
		return this.thresholds[i];
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
		String[] newHeaders = new String[headers.length + keysSize()];
		System.arraycopy(headers, 0, newHeaders, 0, headers.length);
		for (int i = 0; i < keysSize(); i++) {
			newHeaders[headers.length + i] = tag(i);	
		}
		getDriver().setProperty("headers", newHeaders);
	}


	public Object compute(Object data) {
		Map record = (Map) data;
		for (int i = 0; i < getKeys().length; i++) {
			tagRecord(record, i);	
		}
		return record;
	}

	private void tagRecord(Map record, int i) {
		Double value = new Double((String) record.get(key(i)));
		if( value.doubleValue() >= threshold(i) ) {
			record.put(tag(i), "Y");
		} else {
			record.put(tag(i), "N");
		}
	}

//	public void addTagToRecord(Map record, String tag) {
//		record.put("Name", this.tag);		
//	}

	
	@Override
	public void computeAll() {
		for (int i = 0; i < getKeys().length; i++) {
			computeThreshold(i);
		}
		super.computeAll();
	}

	public void computeThreshold(int i) {
		String[] data = (String[]) Util.selectDataByKey(getData(), key(i)).toArray(new String[0]);
		this.thresholds[i] = standardThreshold(Util.convertToNumbers(data));
		System.out.println(tag(i) + " threshold: " + threshold(i)); // TODO: log
	}

	
//	public String[] selectDataByKey(Collection objects) {
//		Collection data = selectDataByKey(objects, this.keys);
//		return (String[]) data.toArray(new String[0]);
//	}

	public double standardThreshold(Number[] data) {
		double mean = Statistics.calculateMean(data);
		double dev = Statistics.getStdDev(data);
		return mean + dev;
	}

}
