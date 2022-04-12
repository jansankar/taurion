package org.pcorp.taurion.metier.domaine.element;

import java.time.LocalTime;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.domaine.vecteur.Acceleration;
import org.pcorp.taurion.metier.domaine.vecteur.Vitesse;

public abstract class Mobile {
	private LocalTime derniereActualisation;
	private Coordonnees position;
	private Vitesse vecteurVitesse;
	private Acceleration acceleration;

	public Mobile() {
		derniereActualisation = LocalTime.now();
		position = new Coordonnees(0, 0, 0);
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

		position.appliqueDeplacement(vMoy, secondes);
	}

	public void setPosition(Coordonnees position) {
		this.position = position;
	}

	public Coordonnees getPosition() {
		return position;
	}

	public Vitesse getVitesse() {
		return vecteurVitesse;
	}

	public void setAcceleration(Acceleration acceleration) {
		this.acceleration = acceleration;
	}
}
