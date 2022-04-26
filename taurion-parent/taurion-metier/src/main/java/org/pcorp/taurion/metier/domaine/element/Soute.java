package org.pcorp.taurion.metier.domaine.element;

import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.type.Integrite;

public class Soute extends Element {

	public Soute(Code elementCode, Float masse, Integrite integrite, Float consoNrj,
			String description, Integer id, Integrite integriteActuelle, EtatElement etatActuel,
			SourceEnergie sourceEnergie) {
		super(TypeElement.SOUTE, elementCode, masse, integrite, consoNrj, description, id, integriteActuelle, etatActuel, sourceEnergie);
		// TODO Auto-generated constructor stub
	}



}
