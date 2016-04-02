package org.pcorp.taurion.metier.element;

import java.util.logging.Logger;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.pcorp.taurion.metier.domaine.element.Code;
import org.pcorp.taurion.metier.domaine.element.Propulseur;
import org.pcorp.taurion.metier.domaine.element.TypeElement;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.exception.NonActiveException;
import org.pcorp.taurion.metier.element.carburant.Reservoir;
import org.pcorp.taurion.metier.element.ressource.Carburant;
import org.pcorp.taurion.metier.type.Newton;
import org.pcorp.taurion.metier.type.Tonne;

public class PropulsionTest extends TestCase {
	private static final Float INDICE_CARBURANT = 1f;
	private static final Float MASSE_VOLUMIQUE = 1f;
	private static final Float MASSE_RESERVOIR = Tonne.kg(500f);
	private static final Float CAPACITE_RESERVOIR = MASSE_RESERVOIR * 0.9f;
	private static final Float VIDE = 0f;
	private static final Integer INTEGRITE_RESERVOIR = 10;

	private static final Float MASSE_PROPULSEUR = Tonne.kg(1f);
	private static final Integer INTEGRITE_PROPULSEUR = 10;
	private static final Float TWR = 10f;
	private static final Float ISP = 200f;
	private static final Float CONSO_PROPULSEUR = 200f;

	private static final Float MASSE_GENERATEUR = Tonne.kg(0.5f);
	private static final Integer INTEGRITE_GENERATEUR = 10;
	private static final Float DEBIT_GENERATEUR = 500f;

	Logger log = Logger.getLogger("TEST");

	public void testGetPousseeMax() {

	}

	private Reservoir getReservoirTest() {
		return new Reservoir(0, new Code("RES-TST-00"), MASSE_RESERVOIR, CAPACITE_RESERVOIR, VIDE, INTEGRITE_RESERVOIR);
	}

	private Propulseur getPropulseurTest() {
		return new Propulseur(1, new Code("PROP-TST-00"), MASSE_PROPULSEUR, INTEGRITE_PROPULSEUR, TWR, ISP,
				CONSO_PROPULSEUR);
	}

	private SourceEnergie getSourceEnergie() {
		return new SourceEnergie(2, TypeElement.GENERATEUR, new Code("GEN-TST-00"), MASSE_GENERATEUR,
				INTEGRITE_GENERATEUR, DEBIT_GENERATEUR);
	}

	private Carburant getCarburantTest() {
		return new Carburant(2, MASSE_VOLUMIQUE, INDICE_CARBURANT);
	}

	public void testActivePropulseur() throws NonActiveException {
		Reservoir reservoir = getReservoirTest();
		Propulseur propulseur = getPropulseurTest();
		SourceEnergie generateur = getSourceEnergie();

		// remplissage reservoir
		Carburant carburant = getCarburantTest();
		reservoir.activer();
		reservoir.ajoute(500f, carburant);

		// activation propulseur
		float totalActivation = 0;
		float pTotal = 0;
		generateur.connecterElement(propulseur);
		propulseur.activer();
		Assert.assertTrue("erreur d'activation", propulseur.estActif());

		// 9 impulsions de 1s à 9s.
		for (float t = 0; t < 10; t++) {
			float puissance = propulseur.activePoussee(t, 1f, reservoir);
			if (puissance > 0)
				totalActivation = totalActivation + t;
			pTotal += puissance;
			System.out.println("t=" + t + " s\t\tP=" + Newton.toStringKn(puissance) + "  \tres="
					+ Float.valueOf(Math.round(reservoir.getReste() * 100) / 100) + "T");
		}
		System.out.println("tot=" + totalActivation + "s  \tP=" + Newton.toStringKn(pTotal));
	}

	public void testPousseeMax() throws NonActiveException {
		Reservoir reservoir = getReservoirTest();
		Propulseur propulseur = getPropulseurTest();
		SourceEnergie generateur = getSourceEnergie();

		// remplissage reservoir
		Carburant carburant = getCarburantTest();
		log.info("activation du reservoir");
		reservoir.activer();
		log.info("remplissage du reservoir");
		reservoir.ajoute(reservoir.getComplement(), carburant);

		// validation
		Assert.assertTrue("erreur lors du remplissage", reservoir.estRempli());
		log.info("reservoir rempli");
		Assert.assertTrue("erreur d'activation", reservoir.estActif());
		log.info("reservoir activé");
		Assert.assertEquals("erreur d'indice", reservoir.getCarburant().getIndice(), 1f);
		Assert.assertEquals("erreur de masse volumique", reservoir.getCarburant().getMasseVolumique(), 1f);
		log.info("carburant validé");

		// activation de la pousee
		log.info("branchement générateur");
		generateur.connecterElement(propulseur);
		// FIXME : brancher le generateur depuis l'élément
		// propulseur.connecterSourceEnergie(generateur);
		log.info("activation propulseur");
		propulseur.activer();
		Assert.assertTrue("erreur d'activation", propulseur.estActif());
		log.info("impulsion de 100s 1/3");
		Float p = propulseur.activePoussee(100f, Propulseur.POUSSEE_UN_TIERS, reservoir);

		// resultat du test
		StringBuilder resultat = new StringBuilder();
		resultat.append("Poussee totale=");
		resultat.append(Newton.toStringKn(p));
		resultat.append(" Reservoir=");
		resultat.append(Math.round(reservoir.getReste()));
		resultat.append("/");
		resultat.append(Math.round(reservoir.getCapacite()));
		log.info(resultat.toString());

	}
}
