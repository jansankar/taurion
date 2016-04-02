package org.pcorp.taurion.metier.domaine.evenement;

import org.pcorp.taurion.metier.domaine.element.EtatElement;

public class ResultatDiminutionIntegrite {
	private Integer nouvelleIntegrite;
	private Integer dommagePropage;
	private EtatElement nouvelEtat;

	public ResultatDiminutionIntegrite(Integer nouvelleIntegrite, EtatElement nouvelEtat) {
		super();
		this.nouvelleIntegrite = nouvelleIntegrite;
		this.nouvelEtat = nouvelEtat;
	}

	public Integer getNouvelleIntegrite() {
		return nouvelleIntegrite;
	}

	public Integer getDommagePropage() {
		return dommagePropage;
	}

	public EtatElement getNouvelEtat() {
		return nouvelEtat;
	}

	public void setDommagePropage(Integer dommagePropage) {
		this.dommagePropage = dommagePropage;
	}

}
