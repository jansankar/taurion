package org.pcorp.taurion.metier.type;

public class Masse {
	private Float masse;

	public Masse(Float masse) {
		super();
		this.masse = masse;
	}

	public Masse(Tonne t) {
		this.masse = t.getKg();
	}

	public Float getMasse() {
		return this.masse;
	}

}
