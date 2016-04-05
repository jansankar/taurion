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

	public Coordonnees(Integer x, Integer y, Integer z) {
		super();
		init(x.floatValue(), y.floatValue(), z.floatValue());
	}

	public Coordonnees(Double x, Double y, Double z) {
		super();
		init(x.floatValue(), y.floatValue(), z.floatValue());
	}

	public Coordonnees(Long x, Long y, Long z) {
		super();
		init(x.floatValue(), y.floatValue(), z.floatValue());
	}

	private void init(Float x, Float y, Float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
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
