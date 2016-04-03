package org.pcorp.taurion.metier.domaine.element.armement.charge;

import org.pcorp.taurion.metier.domaine.etat.EtatArmement;
import org.pcorp.taurion.metier.domaine.vecteur.Vitesse;

public interface Charge {
	public void armement();

	public void desarmement();

	public EtatArmement getEtatArmement();

	public Integer impact(Vitesse vitesse);

	public Integer activation();
}
