package org.pcorp.taurion.metier.element;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.pcorp.taurion.metier.TestParent;
import org.pcorp.taurion.metier.domaine.element.Propulseur;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.exception.NonActiveException;
import org.pcorp.taurion.metier.element.carburant.Reservoir;
import org.pcorp.taurion.metier.element.ressource.Carburant;
import org.pcorp.taurion.metier.type.Newton;

public class PropulsionTest extends TestParent {

	Logger log = Logger.getLogger("TEST");

	public void testGetPousseeMax() {

	}

	@Test
	public void testActivePropulseur() throws NonActiveException {
		Reservoir reservoir = getReservoirTest();
		Propulseur propulseur = getPropulseurTest();
		SourceEnergie generateur = getSourceEnergie();

		// remplissage reservoir
		log.info("P01 - activation du reservoir et remplissage");
		Carburant carburant = getCarburantTest();
		reservoir.activer();
		reservoir.ajoute(500f, carburant);

		// activation propulseur
		log.info("P02 - connexion generateur externe");
		float totalActivation = 0;
		float pTotal = 0;
		generateur.connecterElement(propulseur);
		log.info("P03 - activation propulseur");
		propulseur.activer();
		Assert.assertTrue("erreur d'activation", propulseur.estActif());
		log.info("V01 - propulseur actif");

		// 9 impulsions de 1s à 9s.
		log.info("A1 - activation poussee");
		for (float t = 0; t < 10; t++) {
			float puissance = propulseur.activePoussee(t, 1f, reservoir);
			if (puissance > 0)
				totalActivation = totalActivation + t;
			pTotal += puissance;
			log.info("t=" + t + " s\t\tP=" + Newton.toStringKn(puissance) + "  \tres="
					+ Float.valueOf(Math.round(reservoir.getReste() * 100) / 100) + "T");
		}
		log.info("tot=" + totalActivation + "s  \tP=" + Newton.toStringKn(pTotal));
	}

	@Test
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
		Assert.assertEquals("erreur d'indice", reservoir.getCarburant().getIndice(), 1f, 0f);
		Assert.assertEquals("erreur de masse volumique", reservoir.getCarburant().getMasseVolumique(), 1f, 0f);
		log.info("carburant validé");

		// activation de la poussee
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
