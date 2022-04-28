package org.pcorp.taurion.metier.domaine.element;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.pcorp.taurion.metier.AppTimer;
import org.pcorp.taurion.metier.domaine.construction.ObjetComplexe;
import org.pcorp.taurion.metier.domaine.construction.TypeObjet;
import org.pcorp.taurion.metier.domaine.vecteur.Acceleration;
import org.pcorp.taurion.metier.domaine.vecteur.Vitesse;
import org.pcorp.taurion.metier.type.Tonne;

public abstract class Mobile extends ObjetComplexe {
	private LocalDateTime derniereActualisation;
	private Vitesse vecteurVitesse;
	private long dla = 5;

	public Mobile(long id, TypeObjet typeObjet, Tonne masse) {
		super(id, typeObjet, masse, null);
		vecteurVitesse = new Vitesse();
	}
	
	public Mobile(long id, TypeObjet typeObjet) {
		super(id, typeObjet);
		vecteurVitesse = new Vitesse();
	}

	public void actualisePosition() {
		LocalDateTime heureActualisation = LocalDateTime.now();
		// si l'actualisation est plus recente on ne fait rien (erreur...)
		if (derniereActualisation != null && derniereActualisation.isAfter(heureActualisation))
			return;

		// si immobile on ne fait rien
		if (vecteurVitesse.getVitesse() == 0f)
			return;

		long secondesEcoulees = Long.MAX_VALUE;
		if (derniereActualisation != null)
			secondesEcoulees = ChronoUnit.SECONDS.between(derniereActualisation, heureActualisation);
		
		if (secondesEcoulees < dla) {
			return;
		}
		
		getPosition().appliqueDeplacement(vecteurVitesse, AppTimer.TEMPS_ACTIF);
		derniereActualisation = heureActualisation;
	}

	
	public void deplace(Vitesse vecteurVitesse) {
		this.vecteurVitesse = vecteurVitesse;
		actualisePosition();
	}
	
	public void miseAZeroVitesse() {
		vecteurVitesse = new Vitesse();
	}


	public Vitesse getVitesse() {
		return vecteurVitesse;
	}

}
