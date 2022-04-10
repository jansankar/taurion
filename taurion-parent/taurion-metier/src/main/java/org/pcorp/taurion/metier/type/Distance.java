package org.pcorp.taurion.metier.type;

public class Distance {
	private Float distance;

	public Distance(Float distance) {
		this.distance = distance;
	}

	public Distance(Integer distance) {
		this.distance = distance.floatValue();
	}

	public Distance(Double distance) {
		this.distance = distance.floatValue();
	}

	public Distance(Long distance) {
		this.distance = distance.floatValue();
	}

	public Float getDistance() {
		return distance;
	}
}
