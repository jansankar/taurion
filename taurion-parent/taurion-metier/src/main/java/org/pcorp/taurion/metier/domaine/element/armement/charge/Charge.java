package org.pcorp.taurion.metier.domaine.element.armement.charge;

import org.pcorp.taurion.metier.domaine.etat.EtatArmement;

public interface Charge {
	public void armement();
	public void desarmement();
	public EtatArmement getEtatArmement();
	public void impact();
	public void activation();
}
