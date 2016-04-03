package org.pcorp.taurion.metier.domaine.construction;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pcorp.taurion.metier.TestParent;
import org.pcorp.taurion.metier.domaine.construction.rocket.Rocket_mk00;
import org.pcorp.taurion.metier.domaine.element.armement.charge.Charge;
import org.pcorp.taurion.metier.domaine.element.armement.charge.ChargeInerte;
import org.pcorp.taurion.metier.domaine.etat.EtatArmement;
import org.pcorp.taurion.metier.type.Masse;

public class RocketTest01 extends TestParent {

	private Rocket rocket01;

	private Logger log = Logger.getLogger("RocketTest01");

	protected Charge getCharge() {
		return new ChargeInerte(new Masse(10f));
	}

	@Before
	public void setUp() throws Exception {
		rocket01 = new Rocket_mk00(getReservoirTest(), getPropulseurTest(), getSourceEnergie(), getCharge());
	}

	@Test
	public void testActiveRocket() {
		log.info("Activation roquette");
		rocket01.activer();
		Assert.assertEquals("activation échouée", true, rocket01.estActive());

		log.info("Armement charge");
		rocket01.armementCharge();
		Assert.assertEquals("charge armée", EtatArmement.ARME, rocket01.getEtatArmement());

		log.info("Mise à feu");
		rocket01.miseAFeu();
	}

	/*
	 * @BeforeClass - oneTimeSetUp
	 * 
	 * @Before - setUp
	 * 
	 * @Test - testEmptyCollection
	 * 
	 * @After - tearDown
	 * 
	 * @Before - setUp
	 * 
	 * @Test - testOneItemCollection
	 * 
	 * @After - tearDown
	 * 
	 * @AfterClass - oneTimeTearDown
	 */
}
