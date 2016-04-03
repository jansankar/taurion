package org.pcorp.taurion.metier.domaine.construction;

import java.util.logging.Logger;

import org.pcorp.taurion.metier.TestParent;
import org.pcorp.taurion.metier.domaine.construction.Rocket;
import org.pcorp.taurion.metier.domaine.construction.rocket.Rocket_mk00;
import org.pcorp.taurion.metier.domaine.element.armement.charge.Charge;
import org.pcorp.taurion.metier.domaine.element.armement.charge.ChargeInerte;
import org.pcorp.taurion.metier.domaine.etat.EtatArmement;
import org.junit.*;

public class RocketTest01 extends TestParent {

	private Rocket rocket01;
	
	private Logger log = Logger.getLogger("RocketTest01");
	
	protected Charge getCharge() {
		return new ChargeInerte();
	}
	
	@Before
	protected void setUp() throws Exception {
		rocket01 = new Rocket_mk00(getReservoirTest(), getPropulseurTest(), getSourceEnergie(), getCharge());
	}

	@Test
	public void testActiveRocket() {
		log.info("Activation rockette");
		rocket01.active();
		Assert.assertEquals("activation échouée", true, rocket01.estActive());
		
		log.info("Armement charge");
		rocket01.armementCharge();
		Assert.assertEquals("charge armée", EtatArmement.ARME, rocket01.getEtatArmement());
		
		
		
		log.info("Armement de la charge");
		
	}
	
	/*
@BeforeClass - oneTimeSetUp
@Before - setUp
@Test - testEmptyCollection
@After - tearDown
@Before - setUp
@Test - testOneItemCollection
@After - tearDown
@AfterClass - oneTimeTearDown
	 */
}
