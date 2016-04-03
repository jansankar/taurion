package org.pcorp.taurion.metier.domaine.construction;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.domaine.etat.EtatArmement;
import org.pcorp.taurion.metier.domaine.vecteur.Vitesse;

public interface Rocket {
	public void setCible(Coordonnees coordonnes);
	public void positionneSurPasDeTir(Coordonnees pasDeTir);
	public Coordonnees getCoordonnees();
	public Coordonnees getCoordonneesCible();
	public Vitesse getVitesse();
	public void active();
	public void desactive();
	public boolean estActive();
	
	public void armementCharge();
	public void desarmementCharge();
	public EtatArmement getEtatArmement();
	
	public void miseAFeu();
	
	
}
