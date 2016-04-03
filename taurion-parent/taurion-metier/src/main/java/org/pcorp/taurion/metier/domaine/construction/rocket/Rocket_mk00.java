package org.pcorp.taurion.metier.domaine.construction.rocket;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.domaine.construction.Rocket;
import org.pcorp.taurion.metier.domaine.element.Propulseur;
import org.pcorp.taurion.metier.domaine.element.armement.charge.Charge;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatArmement;
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.domaine.vecteur.Vitesse;
import org.pcorp.taurion.metier.element.carburant.Reservoir;

public class Rocket_mk00 implements Rocket {
	// information rocket
	private Coordonnees positionActuelle;
	private Vitesse vitesseActuelle;
	private Reservoir reservoir;
	private Propulseur propulseur;
	private SourceEnergie batterie;
	private Charge charge;
	private EtatElement etatElement;

	//
	private Coordonnees coordonnesCible;

	public Rocket_mk00(Reservoir reservoir, Propulseur propulseur, SourceEnergie batterie, Charge charge) {
		super();
		this.vitesseActuelle = new Vitesse();
		this.reservoir = reservoir;
		this.propulseur = propulseur;
		this.batterie = batterie;
		this.charge = charge;

		this.batterie.connecterElement(propulseur);
	}

	@Override
	public void setCible(Coordonnees coordonnes) {
		coordonnesCible = coordonnes;
	}

	@Override
	public void positionneSurPasDeTir(Coordonnees pasDeTir) {
		// FIXME : passer un sabord
		this.positionActuelle = pasDeTir.clone();
	}

	@Override
	public Coordonnees getCoordonnees() {
		return positionActuelle;
	}

	@Override
	public Coordonnees getCoordonneesCible() {
		return coordonnesCible;
	}

	@Override
	public Vitesse getVitesse() {
		return vitesseActuelle;
	}

	@Override
	public void activer() {
		reservoir.activer();
		batterie.activer();
		propulseur.activer();
		etatElement = EtatElement.ACTIF;
	}

	@Override
	public void desactive() {
		reservoir.inactiver();
		batterie.inactiver();
		propulseur.inactiver();

		etatElement = EtatElement.INACTIF;
	}

	@Override
	public boolean estActive() {
		return etatElement == EtatElement.ACTIF;
	}

	@Override
	public void armementCharge() {
		if (charge != null)
			charge.armement();
	}

	@Override
	public void desarmementCharge() {
		if (charge != null)
			charge.desarmement();
	}

	@Override
	public EtatArmement getEtatArmement() {
		if (charge != null)
			return charge.getEtatArmement();
		return EtatArmement.DESARME;
	}

	@Override
	public void miseAFeu() {
		// check activation
		// TODO: check activation

		// activation propulseur jusqu'à la cible
		try {
			propulseur.activePoussee(1f, 1f, this.reservoir);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
