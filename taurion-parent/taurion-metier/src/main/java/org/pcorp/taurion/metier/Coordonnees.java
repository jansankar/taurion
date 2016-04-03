package org.pcorp.taurion.metier;

public class Coordonnees {
	private Float x;
	private Float y;
	private Float z;

	@SuppressWarnings("unused")
	private Coordonnees() {

	}

	public Coordonnees(Float x, Float y, Float z) {
		super();
		init(x, y, z);
	}

	private void init(Float x, Float y, Float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Coordonnees clone() {
		return new Coordonnees(x, y, z);
	}
	
	public Float getX() {
		return x;
	}

	public Float getY() {
		return y;
	}

	public Float getZ() {
		return z;
	}

}
