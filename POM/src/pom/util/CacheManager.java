/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
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
package pom.util;

import java.util.HashMap;
import java.util.Map;
import padl.kernel.IConstituent;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IBinaryMetric;
import pom.metrics.IMetric;

public class CacheManager {
	private static CacheManager UniqueInstance;
	public static CacheManager getInstance() {
		if (CacheManager.UniqueInstance == null) {
			CacheManager.UniqueInstance = new CacheManager();
		}
		return CacheManager.UniqueInstance;
	}

	private final Map cache;
	private CacheManager() {
		this.cache = new HashMap();
	}
	public void cacheBinaryMetricValue(
		final IBinaryMetric aMetric,
		final IFirstClassEntity anEntity,
		final IFirstClassEntity anotherEntity,
		final double aValue) {

		if (aMetric.isSymmetrical()) {
			if (!this.isBinaryMetricValueInCache(
				aMetric,
				anotherEntity,
				anEntity)) {

				this.cache.put(
					this.getBinaryMetricKey(aMetric, anEntity, anotherEntity),
					new Double(aValue));
			}
		}
		else {
			//	this.cache.put(this.getBinaryMetricKey(
			//		aMetric,
			//		anEntity,
			//		anotherEntity), new Double(aValue));
		}
	}
	public void cachePrimitiveResult(
		final String aMethodName,
		final IConstituent aConstituent,
		final IConstituent anotherConstituent,
		final Object aValue) {

		//	this.cache.put(this.getPrimitiveKey(
		//		aMethodName,
		//		aConstituent,
		//		anotherConstituent), aValue);
	}
	public void cacheUnaryMetricValue(
		final IMetric aMetric,
		final IFirstClassEntity anEntity,
		final double aValue) {

		this.cache.put(this.getUnaryMetricKey(aMetric, anEntity), new Double(
			aValue));
	}
	private String getBinaryMetricKey(
		final IBinaryMetric aMetric,
		final IFirstClassEntity anEntity,
		final IFirstClassEntity anotherEntity) {

		final StringBuffer buffer = new StringBuffer();
		buffer.append(aMetric.getClass().getName());
		buffer.append(anEntity.getID());
		buffer.append(anotherEntity.getID());
		return buffer.toString();
	}
	private String getPrimitiveKey(
		final String aMethodName,
		final IConstituent aConstituent,
		final IConstituent anotherConstituent) {

		final StringBuffer buffer = new StringBuffer();
		buffer.append(aMethodName);
		buffer.append(aConstituent.getID());
		buffer.append(anotherConstituent.getID());
		return buffer.toString();
	}
	private String getUnaryMetricKey(
		final IMetric aMetric,
		final IFirstClassEntity anEntity) {

		final StringBuffer buffer = new StringBuffer();
		buffer.append(aMetric.getClass().getName());
		buffer.append(anEntity.getID());
		return buffer.toString();
	}
	//	public boolean isAsymetricalBinaryMetricValueInCache(
	//		final Class aMetric,
	//		final IEntity anEntity,
	//		final IEntity anotherEntity) {
	//
	//		// TODO: It would be better if the client of the 
	//		// cache did not have to know whether the metric 
	//		// is symmetrical or not using reflection to test 
	//		// isSymmetrical()
	//		
	//		return this.cache.containsKey(this.getBinaryMetricKey(
	//			aMetric,
	//			anEntity,
	//			anotherEntity));
	//	}
	public boolean isBinaryMetricValueInCache(
		final IBinaryMetric aMetric,
		final IFirstClassEntity anEntity,
		final IFirstClassEntity anotherEntity) {

		if (aMetric.isSymmetrical()) {
			return this.cache.containsKey(this.getBinaryMetricKey(
				aMetric,
				anEntity,
				anotherEntity))
					|| this.cache.containsKey(this.getBinaryMetricKey(
						aMetric,
						anotherEntity,
						anEntity));
		}
		else {
			return this.cache.containsKey(this.getBinaryMetricKey(
				aMetric,
				anEntity,
				anotherEntity));
		}
	}
	public boolean isUnaryMetricValueInCache(
		final IMetric aMetric,
		final IFirstClassEntity anEntity) {

		return this.cache
			.containsKey(this.getUnaryMetricKey(aMetric, anEntity));
	}
	public double retrieveBinaryMetricValue(
		final IBinaryMetric aMetric,
		final IFirstClassEntity anEntity,
		final IFirstClassEntity anotherEntity) {

		if (aMetric.isSymmetrical()) {
			try {
				return this.retrieveMetricValueFromCache(this
					.getBinaryMetricKey(aMetric, anEntity, anotherEntity));
			}
			catch (final NoSuchValueInCacheException e) {
				return this.retrieveMetricValueFromCache(this
					.getBinaryMetricKey(aMetric, anotherEntity, anEntity));
			}
		}
		else {
			return this.retrieveMetricValueFromCache(this.getBinaryMetricKey(
				aMetric,
				anEntity,
				anotherEntity));
		}
	}
	private double retrieveMetricValueFromCache(final String aKey) {
		final Double value = (Double) this.cache.get(aKey);
		if (value != null) {
			return value.doubleValue();
		}
		else {
			throw new NoSuchValueInCacheException(aKey);
		}
	}
	public Object retrievePrimitiveResult(
		final String aMethodName,
		final IConstituent aConstituent,
		final IConstituent anotherConstituent) {

		return this.retrievePrimitiveResultFromCache(this.getPrimitiveKey(
			aMethodName,
			aConstituent,
			anotherConstituent));
	}
	private Object retrievePrimitiveResultFromCache(final String aKey) {
		final Object value = this.cache.get(aKey);
		if (value != null) {
			return value;
		}
		else {
			throw new NoSuchValueInCacheException(aKey);
		}
	}
	public double retrieveUnaryMetricValue(
		final IMetric aMetric,
		final IFirstClassEntity anEntity) {

		return this.retrieveMetricValueFromCache(this.getUnaryMetricKey(
			aMetric,
			anEntity));
	}
}
