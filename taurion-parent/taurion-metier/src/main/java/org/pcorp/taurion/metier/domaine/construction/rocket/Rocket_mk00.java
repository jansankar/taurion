package org.pcorp.taurion.metier.domaine.construction.rocket;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.AppTimer;
import org.pcorp.taurion.metier.domaine.construction.ObjetComplexe;
import org.pcorp.taurion.metier.domaine.construction.Rocket;
import org.pcorp.taurion.metier.domaine.construction.TypeObjet;
import org.pcorp.taurion.metier.domaine.element.Mobile;
import org.pcorp.taurion.metier.domaine.element.Propulseur;
import org.pcorp.taurion.metier.domaine.element.armement.charge.Charge;
import org.pcorp.taurion.metier.domaine.element.carburant.Reservoir;
import org.pcorp.taurion.metier.domaine.element.ressource.Carburant;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatArmement;
import org.pcorp.taurion.metier.domaine.evenement.ResultatDeplacement;
import org.pcorp.taurion.metier.domaine.vecteur.Vecteur;
import org.pcorp.taurion.metier.domaine.vecteur.Vitesse;
import org.pcorp.taurion.metier.type.Tonne;
import org.pcorp.taurion.metier.util.MathUtil;

public class Rocket_mk00 extends Mobile implements Rocket {
	// information rocket
	private Reservoir reservoir;
	private Propulseur propulseur;
	private SourceEnergie batterie;
	private Charge charge;

	//
	private long cibleId;

	public Rocket_mk00(long id, Reservoir reservoir, Propulseur propulseur, SourceEnergie batterie, Charge charge) {
		super(id, TypeObjet.MIS);
		this.reservoir = reservoir;
		this.propulseur = propulseur;
		this.batterie = batterie;
		this.charge = charge;

		this.batterie.connecterElement(propulseur);

		float masseTotale = (reservoir.getMasse() + propulseur.getMasse() + batterie.getMasse()) * 2;
		setMasse(new Tonne(masseTotale));
	}

	public void init() {
		reservoir.ajoute(reservoir.getCapacite(), new Carburant(1, 1f, 1f));
	}

	@Override
	public void setCible(long cibleId) {
		this.cibleId = cibleId;
	}

	@Override
	public void positionneSurPasDeTir(Coordonnees pasDeTir) {
		// FIXME : passer un sabord
		setPosition(pasDeTir.clone());
	}

	@Override
	public Coordonnees getCoordonnees() {
		return getPosition();
	}

	@Override
	public void activer() {
		reservoir.activer();
		batterie.activer();
		propulseur.activer();
		super.activer();
	}

	@Override
	public void desactive() {
		reservoir.inactiver();
		batterie.inactiver();
		propulseur.inactiver();
		super.desactive();
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
	public void miseAFeu(ObjetComplexe objetCible) {
		// check activation
		// TODO: check activation

		// activation propulseur jusqu'à la cible
		deplace(objetCible);

		
	}
	
	@Override
	public ResultatDeplacement deplace(ObjetComplexe objetCible) {
		Float poussee = 0f;
		try {
			poussee = propulseur.activePoussee(AppTimer.TEMPS_ACTIF, Propulseur.POUSSEE_TOUTE, this.reservoir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Vecteur direction = getCoordonnees().getDifference(objetCible.getPosition());
		Vecteur vitesse = calculVecteurVitesse(poussee, direction);
		deplace(new Vitesse(vitesse));
		return new ResultatDeplacement();
	}
	
	@Override
	public long getCible() {
		return cibleId;
	}

	private Vecteur calculVecteurVitesse(float pousse, Vecteur direction) {
		float deplacementMaximumAttendu = MathUtil.hypo(direction.getX(), direction.getY(), direction.getZ());
		
		float deplacementDisponible = pousse / getMasse().getTonne();
		if(deplacementMaximumAttendu < deplacementDisponible)
			deplacementDisponible = deplacementMaximumAttendu;
		
		float angleHorizontal = MathUtil.atan(direction.getY(), direction.getX());
		float angleVertical = MathUtil.atan(direction.getZ(), MathUtil.hypo(direction.getX(), direction.getY()));
		
		float vz = Math.abs(deplacementDisponible * MathUtil.sin(angleVertical));
		float dxy = deplacementDisponible * MathUtil.cos(angleVertical);
		float vx = Math.abs(dxy * MathUtil.cos(angleHorizontal));
		float vy = Math.abs(dxy * MathUtil.sin(angleHorizontal));
		
		if(direction.getX() < 0) 
			vx = vx*-1;
		if(direction.getY() < 0) 
			vy = vy*-1;
		if(direction.getZ() < 0) 
			vz = vz*-1;
		
		return new Vecteur(vx, vy, vz);
	}

	
}
