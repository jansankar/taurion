package org.pcorp.taurion.metier;

import org.pcorp.taurion.metier.domaine.element.Code;
import org.pcorp.taurion.metier.domaine.element.Propulseur;
import org.pcorp.taurion.metier.domaine.element.TypeElement;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.element.carburant.Reservoir;
import org.pcorp.taurion.metier.element.ressource.Carburant;
import org.pcorp.taurion.metier.type.Tonne;

public class TestParent {
	protected static final Float INDICE_CARBURANT = 1f;
	protected static final Float MASSE_VOLUMIQUE = 1f;
	protected static final Float MASSE_RESERVOIR = Tonne.kg(500f);
	protected static final Float CAPACITE_RESERVOIR = MASSE_RESERVOIR * 0.9f;
	protected static final Float VIDE = 0f;
	protected static final Integer INTEGRITE_RESERVOIR = 10;

	protected static final Float MASSE_PROPULSEUR = Tonne.kg(1f);
	protected static final Integer INTEGRITE_PROPULSEUR = 10;
	protected static final Float TWR = 10f;
	protected static final Float ISP = 200f;
	protected static final Float CONSO_PROPULSEUR = 200f;

	protected static final Float MASSE_GENERATEUR = Tonne.kg(0.5f);
	protected static final Integer INTEGRITE_GENERATEUR = 10;
	protected static final Float DEBIT_GENERATEUR = 500f;

	
	protected Reservoir getReservoirTest() {
		return new Reservoir(0, new Code("RES-TST-00"), MASSE_RESERVOIR, CAPACITE_RESERVOIR, VIDE, INTEGRITE_RESERVOIR);
	}

	protected Propulseur getPropulseurTest() {
		return new Propulseur(1, new Code("PROP-TST-00"), MASSE_PROPULSEUR, INTEGRITE_PROPULSEUR, TWR, ISP,
				CONSO_PROPULSEUR);
	}

	protected SourceEnergie getSourceEnergie() {
		return new SourceEnergie(2, TypeElement.GENERATEUR, new Code("GEN-TST-00"), MASSE_GENERATEUR,
				INTEGRITE_GENERATEUR, DEBIT_GENERATEUR);
	}

	protected Carburant getCarburantTest() {
		return new Carburant(2, MASSE_VOLUMIQUE, INDICE_CARBURANT);
	}
}
