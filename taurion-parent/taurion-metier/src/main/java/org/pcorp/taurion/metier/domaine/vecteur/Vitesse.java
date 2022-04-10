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

	public Float getVitesse() {
		return (float) Math.sqrt(((getX() * getX()) + (getY() * getY()) + (getZ() * getZ())));
	}

	public Vitesse clone() {
		return new Vitesse(this);
	}
	
	public Vitesse moyenne(Vitesse vitesse) {
		float vx = (vitesse.getVitesseX()+getVitesseX())/2;
		float vy = (vitesse.getVitesseY()+getVitesseY())/2;
		float vz = (vitesse.getVitesseZ()+getVitesseZ())/2;
		
		return new Vitesse(new Vecteur(vx, vy, vz));
	}

}
