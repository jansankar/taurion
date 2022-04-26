package org.pcorp.taurion.metier.domaine.element.carburant;

import org.pcorp.taurion.metier.domaine.element.Code;
import org.pcorp.taurion.metier.domaine.element.Element;
import org.pcorp.taurion.metier.domaine.element.TypeElement;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.type.Integrite;

public class Pompe extends Element {
	private Float debitMaximum;

	

	public Pompe(Code elementCode, Float masse, Integrite integrite, Float consoNrj,
			String description, Integer id, Integrite integriteActuelle, EtatElement etatActuel,
			SourceEnergie sourceEnergie) {
		super(TypeElement.POMPE, elementCode, masse, integrite, consoNrj, description, id, integriteActuelle, etatActuel, sourceEnergie);
	}



	public Float getDebitMaximum() {
		return debitMaximum;
	}

}
