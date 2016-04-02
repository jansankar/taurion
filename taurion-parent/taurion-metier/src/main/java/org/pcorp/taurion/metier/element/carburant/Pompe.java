package org.pcorp.taurion.metier.element.carburant;

import org.pcorp.taurion.metier.domaine.element.Code;
import org.pcorp.taurion.metier.domaine.element.Element;
import org.pcorp.taurion.metier.domaine.element.TypeElement;

public class Pompe extends Element {
	private Float debitMaximum;

	public Pompe(Integer id, Code elementCode, Float masse, Integer integrite, Float debitMaximum, Float consoNrj) {
		super(id, TypeElement.POMPE, elementCode, masse, integrite, consoNrj);
	}

	public Float getDebitMaximum() {
		return debitMaximum;
	}

}
