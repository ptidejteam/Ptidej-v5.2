package padl.example.methodInvocation;

public abstract class ImmutableCollection implements java.util.Collection, java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public boolean isEmpty() {
		size();
		return false;
	}

}
