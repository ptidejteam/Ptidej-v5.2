package padl.motif.repository;

import padl.motif.models.BehaviouralMotifModel;
import util.multilingual.MultilingualManager;

public class Singleton extends BehaviouralMotifModel implements Cloneable {
	private static final long serialVersionUID = -1693694069786129452L;
	private static final char[] SINGLETON = "Singleton".toCharArray();

	public Singleton() {
		super(Singleton.SINGLETON);
	}
	public String getIntent() {
		return MultilingualManager.getString("INTENT", Singleton.class);
	}
	public char[] getName() {
		return Singleton.SINGLETON;
	}
}
