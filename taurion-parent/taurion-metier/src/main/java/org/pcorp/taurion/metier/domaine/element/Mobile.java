package org.pcorp.taurion.metier.domaine.element;

import java.time.LocalTime;

import org.pcorp.taurion.metier.domaine.construction.ObjetComplexe;
import org.pcorp.taurion.metier.domaine.construction.TypeObjet;
import org.pcorp.taurion.metier.domaine.vecteur.Acceleration;
import org.pcorp.taurion.metier.domaine.vecteur.Vitesse;
import org.pcorp.taurion.metier.type.Tonne;

public abstract class Mobile extends ObjetComplexe {
	private LocalTime derniereActualisation;
	private Vitesse vecteurVitesse;
	private Acceleration acceleration;

	public Mobile(long id, TypeObjet typeObjet, Tonne masse) {
		super(id, typeObjet, masse);
		derniereActualisation = LocalTime.now();
		vecteurVitesse = new Vitesse();
		acceleration = new Acceleration(0f, 0f, 0f);
	}
	
	public Mobile(long id, TypeObjet typeObjet) {
		super(id, typeObjet);
		derniereActualisation = LocalTime.now();
		vecteurVitesse = new Vitesse();
		acceleration = new Acceleration(0f, 0f, 0f);
	}

	public void actualisePosition(LocalTime heureActualisation) {
		// si l'actualisation est plus recente on ne fait rien (erreur...)
		if (derniereActualisation.isAfter(heureActualisation))
			return;

		// si immobile et aucune acceleration on ne fait rien
		if (vecteurVitesse.getVitesse() == 0f && !acceleration.acceleration())
			return;

		LocalTime difference = heureActualisation.minusNanos(derniereActualisation.toNanoOfDay());
		Float secondes = Float.valueOf(difference.toSecondOfDay());

		Vitesse vitesseOriginale = vecteurVitesse.clone();
		vecteurVitesse.accelere(acceleration, secondes);
		Vitesse vMoy = vecteurVitesse.moyenne(vitesseOriginale);

		getPosition().appliqueDeplacement(vMoy, secondes);
	}

	
	public void miseAZeroVitesse() {
		vecteurVitesse = new Vitesse();
	}


	public Vitesse getVitesse() {
		return vecteurVitesse;
	}

	public void setAcceleration(Acceleration acceleration) {
		this.acceleration = acceleration;
	}
}
