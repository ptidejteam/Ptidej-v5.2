package padl.micropattern;

import java.util.Set;
import padl.kernel.IFirstClassEntity;
import util.help.IHelpURL;

/**
 * @author tanterij
 */
public interface IMicroPatternDetection extends IHelpURL {
	public void addEntities(final IFirstClassEntity anEntity);
	public long getNumberOfEntities();
	public Set getEntities();
	public boolean detect(final IFirstClassEntity anEntity);
	public String getName();
}
