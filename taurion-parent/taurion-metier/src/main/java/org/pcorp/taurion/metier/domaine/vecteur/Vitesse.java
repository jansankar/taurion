package org.pcorp.taurion.metier.domaine.vecteur;

public class Vitesse extends Vecteur {

	public Vitesse(Vitesse vecteurVitesse) {
		super(vecteurVitesse);
	}

	public Vitesse(Vecteur vecteurVitesse) {
		super(vecteurVitesse);
	}

	public Vitesse() {
		super();
	}

	public void accelere(Acceleration a) {
		augmenterVecteur(a.accelere());
	}

	public void accelere(Acceleration a, Float t) {
		augmenterVecteur(a.accelere(t));
	}

	public Float getVitesseX() {
		return getX();
	}

	public Float getVitesseY() {
		return getY();
	}

	public Float getVitesseZ() {
		return getZ();
	}
}
