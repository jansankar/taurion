package org.pcorp.taurion.metier.ordonanceur;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.pcorp.taurion.metier.AppTimer;
import org.pcorp.taurion.metier.domaine.construction.ObjetComplexe;
import org.pcorp.taurion.metier.domaine.construction.Rocket;
import org.pcorp.taurion.metier.domaine.element.Mobile;
import org.pcorp.taurion.metier.domaine.evenement.ResultatDeplacement;

public class OrdonanceurCommun {
	private List<Mobile> mobiles = new LinkedList<>();
	private List<Rocket> rockets = new LinkedList<>();;
	
	private Map<Long, ObjetComplexe> listeMemoire = new HashMap<Long, ObjetComplexe>();
	
	public void timer() {
	    TimerTask task = new TimerTask() {
	        public void run() {
	            actualiseRockets();
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = Math.round(AppTimer.TICK) *  1000L;
	    timer.schedule(task, 0, delay);
	}
	
	public void actualiseRockets() {
		for (Rocket rocket : rockets) {
			ObjetComplexe cible = getObj(rocket.getCible());
			ResultatDeplacement resultat = rocket.deplace(cible);
			displayInformations(rocket);
			
		}
	}
	
	
	public void displayInformations(Rocket rocket) {
		System.out.println("rocket : " + rocket.getId() + " vitesse " + rocket.getVitesse() + " coordonnees " + rocket.getCoordonnees());
		
	}
	
	public void addRock(Rocket r) {
		listeMemoire.put(Long.valueOf(r.getId()), (ObjetComplexe)r);
		rockets.add(r);
	}
	
	public void addMobile(Mobile r) {
		listeMemoire.put(Long.valueOf(r.getId()), (ObjetComplexe)r);
		mobiles.add(r);
	}
	
	public void actualiseMobile() {

		for (Mobile mobile : mobiles) {
			mobile.actualisePosition();
		}
	}

	private ObjetComplexe getObj(long id) {
		return listeMemoire.get(id);
	}
}
	
