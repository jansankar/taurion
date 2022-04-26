package org.pcorp.taurion.metier.domaine.construction;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.TestParent;
import org.pcorp.taurion.metier.domaine.construction.rocket.Rocket_mk00;
import org.pcorp.taurion.metier.domaine.element.armement.charge.Charge;
import org.pcorp.taurion.metier.domaine.element.armement.charge.ChargeInerte;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatArmement;
import org.pcorp.taurion.metier.type.Masse;

public class RocketTest01 extends TestParent {

	private Rocket rocket01;

	private Logger log = LogManager.getLogger(RocketTest01.class);

	private Rocket cible;
	
	protected Charge getCharge() {
		return new ChargeInerte(new Masse(10f));
	}

	@BeforeEach
	public void setUp() throws Exception {
		SourceEnergie batterie = getSourceEnergie();
		rocket01 = new Rocket_mk00(getReservoirTest(batterie), getPropulseurTest(batterie), batterie, getCharge());
		((Rocket_mk00)rocket01).init();
		
		batterie = getSourceEnergie();
		cible = new Rocket_mk00(getReservoirTest(batterie), getPropulseurTest(batterie), batterie, getCharge());
		cible.positionneSurPasDeTir(new Coordonnees(10, 20, 30));
	}

	@Test
	public void testActiveRocket() {
		log.info("Position pas de tir");
		rocket01.positionneSurPasDeTir(new Coordonnees(0f, 0f, 0f));
		
		log.info("Activation roquette");
		rocket01.activer();
		Assertions.assertEquals(true, rocket01.estActive(), "activation échouée");

		log.info("Armement charge");
		rocket01.armementCharge();
		Assertions.assertEquals(EtatArmement.ARME, rocket01.getEtatArmement(), "charge armée");

		log.info("saisie coordonnee");
		rocket01.setCible(50);
		
		log.info("Mise à feu");
		rocket01.miseAFeu((ObjetComplexe) cible);
	}
}
