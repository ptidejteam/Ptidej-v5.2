package padl.motif.repository;

import padl.motif.models.BehaviouralMotifModel;
import util.multilingual.MultilingualManager;

public class Prototype extends BehaviouralMotifModel implements Cloneable {
	private static final char[] PROTOTYPE = "Prototype".toCharArray();
	private static final long serialVersionUID = 520438833419263601L;

	public Prototype() {
		super(Prototype.PROTOTYPE);
	}
	public String getIntent() {
		return MultilingualManager.getString("INTENT", Prototype.class);
	}
	public char[] getName() {
		return Prototype.PROTOTYPE;
	}
}
