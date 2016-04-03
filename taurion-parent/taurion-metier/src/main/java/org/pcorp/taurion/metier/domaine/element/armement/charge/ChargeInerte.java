package org.pcorp.taurion.metier.domaine.element.armement.charge;

import org.pcorp.taurion.metier.domaine.etat.EtatArmement;
import org.pcorp.taurion.metier.domaine.vecteur.Vitesse;
import org.pcorp.taurion.metier.type.Masse;

public class ChargeInerte implements Charge {
	private EtatArmement etatArmement;
	private Masse masse;

	public ChargeInerte(Masse masse) {
		this.masse = masse;
	}

	@Override
	public void armement() {
		this.etatArmement = EtatArmement.ARME;

	}

	@Override
	public void desarmement() {
		this.etatArmement = EtatArmement.DESARME;

	}

	@Override
	public EtatArmement getEtatArmement() {
		return etatArmement;
	}

	@Override
	public Integer impact(Vitesse vitesse) {
		Float energie = masse.getMasse() * vitesse.getVitesse();
		return Math.round(energie / 10);
	}

	@Override
	public Integer activation() {
		// TODO Auto-generated method stub
		return null;
	}

}
