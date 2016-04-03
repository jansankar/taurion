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

	public Rocket_mk00(Reservoir reservoir, Propulseur propulseur,
			SourceEnergie batterie, Charge charge) {
		super();
		this.vitesseActuelle = new Vitesse();
		this.reservoir = reservoir;
		this.propulseur = propulseur;
		this.batterie = batterie;
		this.charge = charge;
	}

	public void setCible(Coordonnees coordonnes) {
		coordonnesCible = coordonnes;
	}

	public void positionneSurPasDeTir(Coordonnees pasDeTir) {
	// FIXME : passer un sabord
		this.positionActuelle = pasDeTir.clone();
	}
	
	public Coordonnees getCoordonnees() {
		return positionActuelle;
	}

	public Coordonnees getCoordonneesCible() {
		return coordonnesCible;
	}

	public Vitesse getVitesse() {
		return vitesseActuelle;
	}

	public void active() {
		etatElement = EtatElement.ACTIF;
	}

	public void desactive() {
		etatElement = EtatElement.INACTIF;
	}

	public boolean estActive() {
		return etatElement == EtatElement.ACTIF ;
	}

	public void armementCharge() {
		if (charge != null)
			charge.armement();
	}

	public void desarmementCharge() {
		if (charge != null)
			charge.desarmement();
	}

	public EtatArmement getEtatArmement() {
		if (charge != null)
			return charge.getEtatArmement();
		return EtatArmement.DESARME;
	}

	public void miseAFeu() {
		// TODO Auto-generated method stub
		
	}

}
