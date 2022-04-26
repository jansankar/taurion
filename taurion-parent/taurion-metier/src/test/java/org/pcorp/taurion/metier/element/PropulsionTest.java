package org.pcorp.taurion.metier.element;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pcorp.taurion.metier.TestParent;
import org.pcorp.taurion.metier.domaine.element.Propulseur;
import org.pcorp.taurion.metier.domaine.element.carburant.Reservoir;
import org.pcorp.taurion.metier.domaine.element.ressource.Carburant;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.exception.NonActiveException;
import org.pcorp.taurion.metier.type.Newton;

public class PropulsionTest extends TestParent {

	private Logger log = LogManager.getLogger(PropulsionTest.class);

	public void testGetPousseeMax() {

	}

	@Test
	public void testActivePropulseur() throws NonActiveException {
		SourceEnergie generateur = getSourceEnergie();
		Reservoir reservoir = getReservoirTest(generateur);
		Propulseur propulseur = getPropulseurTest(generateur);
		

		// remplissage reservoir
		log.info("P01 - activation du reservoir et remplissage");
		Carburant carburant = getCarburantTest();
		reservoir.activer();
		reservoir.ajoute(500f, carburant);

		// activation propulseur
		log.info("P02 - connexion generateur externe");
		float totalActivation = 0;
		float pTotal = 0;
		log.info("P03 - activation propulseur");
		propulseur.activer();
		Assertions.assertTrue(propulseur.estActif(), "erreur d'activation");
		log.info("V01 - propulseur actif");

		// 9 impulsions de 1s à 9s.
		log.info("A1 - activation poussee");
		for (float t = 0; t < 10; t++) {
			float puissance = propulseur.activePoussee(t, Propulseur.POUSSEE_TOUTE, reservoir);
			if (puissance > 0)
				totalActivation = totalActivation + t;
			pTotal += puissance;
			log.info("t=" + t + " s\t\tP=" + Newton.toStringKn(puissance) + "  \tres="
					+ Float.valueOf(Math.round(reservoir.getReste() * 1000) / 1000f) + "T");
		}
		log.info("tot=" + totalActivation + "s  \tP=" + Newton.toStringKn(pTotal));
	}

	@Test
	public void testPousseeMax() throws NonActiveException {
		SourceEnergie generateur = getSourceEnergie();
		Reservoir reservoir = getReservoirTest(generateur);
		Propulseur propulseur = getPropulseurTest(generateur);
		

		// remplissage reservoir
		Carburant carburant = getCarburantTest();
		log.info("activation du reservoir");
		reservoir.activer();
		log.info("remplissage du reservoir");
		reservoir.ajoute(reservoir.getComplement(), carburant);

		// validation
		Assertions.assertTrue(reservoir.estRempli(), "erreur lors du remplissage");
		log.info("reservoir rempli");
		Assertions.assertTrue(reservoir.estActif(), "erreur d'activation");
		log.info("reservoir activé");
		Assertions.assertEquals(reservoir.getCarburant().getIndice(), 1f, 0f, "erreur d'indice");
		Assertions.assertEquals(reservoir.getCarburant().getMasseVolumique(), 1f, 0f, "erreur de masse volumique");
		log.info("carburant validé");

		// activation de la poussee
		log.info("branchement générateur");
		// propulseur.connecterSourceEnergie(generateur);
		log.info("activation propulseur");
		propulseur.activer();
		Assertions.assertTrue(propulseur.estActif(), "erreur d'activation");
		log.info("impulsion de 100s 1/3");
		Float p = propulseur.activePoussee(100f, Propulseur.POUSSEE_TOUTE, reservoir);

		// resultat du test
		StringBuilder resultat = new StringBuilder();
		resultat.append("Poussee totale=");
		resultat.append(Newton.toStringKn(p));
		resultat.append(" Reservoir=");
		resultat.append(Math.round(reservoir.getReste()*1000f)/1000f);
		resultat.append("/");
		resultat.append(Math.round(reservoir.getCapacite()*1000f)/1000f);
		log.info(resultat.toString());

	}
}
