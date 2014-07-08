package padl.serialiser.dummy;

public class Pilot {
	private String name;
	private int points;

	public Pilot(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public int getPoints() {
		return this.points;
	}

	public void addPoints(int points) {
		this.points += points;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return this.name + "/" + this.points;
	}
}
