package padl.serialiser.dummy;

import java.util.ArrayList;
import java.util.List;

public class Car {
	private String model;
	private List listOfPilots = new ArrayList();

	public Car(String model) {
		this.model = model;
	}

	//	public Pilot getPilot() {
	//		return (Pilot) listOfPilots.get(0);
	//	}

	public void setPilot(Pilot pilot) {
		this.listOfPilots.add(pilot);
	}

	public String getModel() {
		return this.model;
	}

	public String toString() {
		return this.model + "[" + this.listOfPilots.get(0) + "]";
	}
}
