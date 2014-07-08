package padl.micropattern.repository;

import java.util.HashSet;
import java.util.Set;
import padl.kernel.IFirstClassEntity;

/**
 * @author tanterij
 */
abstract class AbstractMicroPatternDetection {
	private Set entities = new HashSet();

	public void addEntities(final IFirstClassEntity anEntity) {
		this.entities.add(anEntity);
	}
	public abstract boolean detect(final IFirstClassEntity anEntity);
	public Set getEntities() {
		return this.entities;
	}
	public String getHelpURL() {
		return "http://dl.acm.org/citation.cfm?id=1094819";
	}
	public long getNumberOfEntities() {
		return this.entities.size();
	}
}
