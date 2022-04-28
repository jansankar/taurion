package org.pcorp.taurion.metier.domaine.construction;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.domaine.etat.EtatArmement;
import org.pcorp.taurion.metier.domaine.evenement.ResultatDeplacement;
import org.pcorp.taurion.metier.domaine.vecteur.Vitesse;

/**
 * Ciblage via position actualisé de la cible (pas d'anticipation)
 * @author jeanp
 *
 */
public interface Rocket {
	public long getId();
	public void setCible(long cibleId);
	public long getCible();
	public void positionneSurPasDeTir(Coordonnees pasDeTir);
	public Coordonnees getCoordonnees();
	public Vitesse getVitesse();
	public void activer();
	public void desactive();
	public boolean estActive();
	
	public void armementCharge();
	public void desarmementCharge();
	public EtatArmement getEtatArmement();
	
	public void miseAFeu(ObjetComplexe objetCible);
	public ResultatDeplacement deplace(ObjetComplexe objetCible);
	
	
}
