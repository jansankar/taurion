package org.pcorp.taurion.metier.type;

public class Tonne {
	private static final Float KILO_DANS_TONNE = 1000f;
	private Float tonne;

	public Tonne(Float tonne) {
		this.tonne = tonne;
	}

	public Float getTonne() {
		return this.tonne;
	}

	public Float getKg() {
		return this.tonne * KILO_DANS_TONNE;
	}

	public static Float enKg(Float t) {
		return t * KILO_DANS_TONNE;
	}

}
