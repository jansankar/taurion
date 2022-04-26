package org.pcorp.taurion.metier;

import org.pcorp.taurion.metier.domaine.element.Code;
import org.pcorp.taurion.metier.domaine.element.Propulseur;
import org.pcorp.taurion.metier.domaine.element.TypeElement;
import org.pcorp.taurion.metier.domaine.element.carburant.Reservoir;
import org.pcorp.taurion.metier.domaine.element.ressource.Carburant;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.type.Integrite;
import org.pcorp.taurion.metier.type.Tonne;

public class TestParent {
	protected static final Float INDICE_CARBURANT = 1f;
	protected static final Float MASSE_VOLUMIQUE = 1f;
	protected static final Float MASSE_RESERVOIR = 0.5f;
	protected static final Float CAPACITE_RESERVOIR = MASSE_RESERVOIR * 0.9f;
	protected static final Float VIDE = 0f;
	protected static final Integrite INTEGRITE_RESERVOIR = new Integrite(10);

	protected static final Float MASSE_PROPULSEUR = 1f;
	protected static final Integrite INTEGRITE_PROPULSEUR = new Integrite(10);
	protected static final Float TWR = 16f;
	protected static final Float ISP = 450f;
	protected static final Float CONSO_PROPULSEUR = 200f;

	protected static final Float MASSE_GENERATEUR = 0.005f;
	protected static final Integrite INTEGRITE_GENERATEUR = new Integrite(10);
	protected static final Float DEBIT_GENERATEUR = 500f;

	
	protected Reservoir getReservoirTest(SourceEnergie sourceEnergie) {
		Reservoir reservoir = new Reservoir(0, new Code("RES-TST-00"), MASSE_RESERVOIR , INTEGRITE_RESERVOIR, 0f, "RES-TEST", INTEGRITE_RESERVOIR, EtatElement.INACTIF, sourceEnergie,CAPACITE_RESERVOIR, CAPACITE_RESERVOIR);

		return reservoir;
	}

	protected Propulseur getPropulseurTest(SourceEnergie sourceEnergie) {
		return new Propulseur(1, new Code("PROP-TST-00"), MASSE_PROPULSEUR, INTEGRITE_PROPULSEUR, CONSO_PROPULSEUR, "PROP TEST", INTEGRITE_PROPULSEUR, sourceEnergie, TWR, ISP);
	}

	protected SourceEnergie getSourceEnergie() {
		return new SourceEnergie(2, 
				TypeElement.GENERATEUR, 
				new Code("GEN-TST-00"), 
				MASSE_GENERATEUR,
				INTEGRITE_GENERATEUR,
				"GEN  TEST",
				INTEGRITE_GENERATEUR,
				DEBIT_GENERATEUR);
	}

	protected Carburant getCarburantTest() {
		return new Carburant(2, MASSE_VOLUMIQUE, INDICE_CARBURANT);
	}
}
