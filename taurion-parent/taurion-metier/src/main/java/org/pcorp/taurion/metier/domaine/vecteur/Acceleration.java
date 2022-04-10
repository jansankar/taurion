package org.pcorp.taurion.metier.domaine.vecteur;

public class Acceleration extends Vecteur {

	public Acceleration(Float x, Float y, Float z) {
		super(x, y, z);
	}

	public Acceleration(Vecteur v) {
		super(v);
	}

	public boolean acceleration() {
		return !vecteurNul();
	}
	
	public Vitesse accelere(Float t) {
		Vecteur v = new Vecteur(getX() * t, getY() * t, getZ() * t);
		return new Vitesse(v);
	}

	public Vitesse accelere() {
		return new Vitesse(this);
	}
}
