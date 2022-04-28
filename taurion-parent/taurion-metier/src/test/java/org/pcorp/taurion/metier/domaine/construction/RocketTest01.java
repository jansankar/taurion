package org.pcorp.taurion.metier.domaine.construction;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.TestParent;
import org.pcorp.taurion.metier.domaine.construction.rocket.Rocket_mk00;
import org.pcorp.taurion.metier.domaine.element.Mobile;
import org.pcorp.taurion.metier.domaine.element.armement.charge.Charge;
import org.pcorp.taurion.metier.domaine.element.armement.charge.ChargeInerte;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatArmement;
import org.pcorp.taurion.metier.ordonanceur.OrdonanceurCommun;
import org.pcorp.taurion.metier.type.Masse;

public class RocketTest01 extends TestParent {

	private Rocket rocket01;

	private Logger log = LogManager.getLogger(RocketTest01.class);

	private Rocket cible;
	
	private OrdonanceurCommun ordonnanceur;
	
	protected Charge getCharge() {
		return new ChargeInerte(new Masse(0.02f));
	}

	@BeforeEach
	public void setUp() throws Exception {
		ordonnanceur = new OrdonanceurCommun();
		SourceEnergie batterie = getSourceEnergie();
		rocket01 = new Rocket_mk00(1, getReservoirTest(batterie), getPropulseurTest(batterie), batterie, getCharge());
		((Rocket_mk00)rocket01).init();
		
		batterie = getSourceEnergie();
		cible = new Rocket_mk00(150, getReservoirTest(batterie), getPropulseurTest(batterie), batterie, getCharge());
		cible.positionneSurPasDeTir(new Coordonnees(10, 20, 30));
		
		ordonnanceur.addRock(rocket01);
		ordonnanceur.addMobile((Mobile)cible);
	}

	@Test
	public void testActiveRocket() throws InterruptedException {
		log.info("Position pas de tir");
		rocket01.positionneSurPasDeTir(new Coordonnees(0f, 0f, 0f));
		
		log.info("Activation roquette");
		rocket01.activer();
		Assertions.assertEquals(true, rocket01.estActive(), "activation échouée");

		log.info("Armement charge");
		rocket01.armementCharge();
		Assertions.assertEquals(EtatArmement.ARME, rocket01.getEtatArmement(), "charge armée");

		log.info("saisie coordonnee");
		rocket01.setCible(cible.getId());
		
		log.info("Mise à feu");
		rocket01.miseAFeu((ObjetComplexe) cible);
		
		ordonnanceur.timer();
	    Thread.sleep(50000);		
		
	}
}
