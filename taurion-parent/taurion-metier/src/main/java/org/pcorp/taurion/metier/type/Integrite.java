package org.pcorp.taurion.metier.type;

import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.domaine.evenement.ResultatDiminutionIntegrite;

public class Integrite implements Comparable<Integrite>{
	public Integer integrite;

	
	public Integrite diminueIntegrite(Integer valeur) {
		integrite = integrite - valeur;
		if (integrite <= 0)
			integrite = 0;

		return new Integrite(integrite);
	}
	
	public boolean isNulle() {
		return integrite <= 0;
	}
	
	public int compare(Integer valeur) {
		return valeur - integrite;
	}
	
	public int compareTo(Integrite integrite) {
		return integrite.getIntegrite() - this.integrite;
	}
	
	public Integrite(Integer integrite) {
		super();
		this.integrite = integrite;
	}

	public Integer getIntegrite() {
		return integrite;
	}

	public void setIntegrite(Integer integrite) {
		this.integrite = integrite;
	}
}
