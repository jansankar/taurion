package org.pcorp.taurion.metier.domaine.construction;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.domaine.etat.EtatArmement;

public interface Rocket {
	public void setCible(Coordonnees coordonnes);
	public void active();
	public void armementCharge();
	public void desarmementCharge();
	public void desactive();
	public void miseAFeu();
	public boolean estActive();
	public EtatArmement getEtatArmement();
}
