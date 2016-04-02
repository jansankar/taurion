package org.pcorp.taurion.metier.element;

import java.util.logging.Logger;

import org.pcorp.taurion.metier.domaine.construction.Rocket;

import junit.framework.Assert;
import junit.framework.TestCase;

public class RocketTest01 extends TestCase {

	private Rocket rocket01;
	
	private Logger log = Logger.getLogger("RocketTest01");
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void activeRocket() {
		log.info("Activation rockette");
		rocket01.active();
		Assert.assertEquals("activation échouée", rocket01.estActive(), true);
		
		log.info("Armement de la charge");
		
	}
}
