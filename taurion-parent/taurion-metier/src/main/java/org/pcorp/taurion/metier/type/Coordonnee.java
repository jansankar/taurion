package org.pcorp.taurion.metier.type;

public class Coordonnee {
	private Float position;

	public Coordonnee(Float position) {
		this.position = position;
	}

	public Coordonnee(Integer position) {
		this.position = position.floatValue();
	}

	public Coordonnee(Double position) {
		this.position = position.floatValue();
	}

	public Coordonnee(Long position) {
		this.position = position.floatValue();
	}

	public Float getPosition() {
		return position;
	}
}
