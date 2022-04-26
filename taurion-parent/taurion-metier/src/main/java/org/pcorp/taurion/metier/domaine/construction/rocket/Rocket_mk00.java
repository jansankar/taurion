package org.pcorp.taurion.metier.domaine.construction.rocket;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.Timer;
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
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.domaine.vecteur.Vecteur;
import org.pcorp.taurion.metier.type.Tonne;

public class Rocket_mk00 extends Mobile implements Rocket {
	// information rocket
	private Reservoir reservoir;
	private Propulseur propulseur;
	private SourceEnergie batterie;
	private Charge charge;
	private EtatElement etatElement;

	//
	private long cibleId;

	public Rocket_mk00(Reservoir reservoir, Propulseur propulseur, SourceEnergie batterie, Charge charge) {
		super(100, TypeObjet.MIS);
		this.reservoir = reservoir;
		this.propulseur = propulseur;
		this.batterie = batterie;
		this.charge = charge;

		this.batterie.connecterElement(propulseur);
		
		float masseTotale = (reservoir.getMasse() + propulseur.getMasse() + batterie.getMasse())*2;
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
	public void miseAFeu(ObjetComplexe objetCible) {
		// check activation
		// TODO: check activation

		// activation propulseur jusqu'à la cible
		Float poussee = 0f;
		try {
			poussee = propulseur.activePoussee(Timer.INTERVAL, Propulseur.POUSSEE_TOUTE, this.reservoir);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Vecteur direction = getCoordonnees().getDifference(objetCible.getPosition());
		System.out.println(direction);
		calculVecteurVitesse(poussee, direction);
	}
	
	float atan(float y, float x) {
	    return (float) Math.atan(y/x);
	  }
	
	float cos(float a) {
	    return (float) Math.cos(a);
	  }
	
	float sin(float a) {
	    return (float) Math.sin(a);
	  }
	
	private Vecteur calculVecteurVitesse(float pousse, Vecteur direction) {
		float deplacementDisponible = pousse/getMasse().getTonne();
		float angleHorizontal = atan(direction.getY(), direction.getX());
		float angleVertical   = atan(direction.getZ(), direction.getX());
		System.out.println("angleHorizontal : " + angleHorizontal);
		System.out.println("angleVertical : " + angleVertical);
		float vx = deplacementDisponible * cos(angleHorizontal);
		float vy = deplacementDisponible * sin(angleHorizontal);
		float vz = deplacementDisponible * sin(angleVertical);
		System.out.println("deplacementDisponible : " + deplacementDisponible);
		System.out.println("vx : " + vx);
		System.out.println("vy : " + vy);
		System.out.println("vz : " + vz);
		System.out.println("vx : " + deplacementDisponible * cos(angleVertical));
		return new Vecteur(vx, vy, vz);
	}
}
