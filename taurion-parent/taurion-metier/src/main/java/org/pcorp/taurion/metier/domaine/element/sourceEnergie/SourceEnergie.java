package org.pcorp.taurion.metier.domaine.element.sourceEnergie;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pcorp.taurion.metier.domaine.element.Code;
import org.pcorp.taurion.metier.domaine.element.Element;
import org.pcorp.taurion.metier.domaine.element.TypeElement;
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.domaine.exception.FournitureEnergieDepasseeException;
import org.pcorp.taurion.metier.type.Integrite;

public class SourceEnergie extends Element {
	private static Logger log = LogManager.getLogger(SourceEnergie.class);

	public static Float TAUX_URGENCE = 0.25f;
	public static Float TAUX_DESTRUCTION = 0.10f;

	public static enum Evenement {
		NORMAL, URGENCE, INCIDENT, DESTRUCTION
	};

	private Float generationdEnergie;
	private Float consommationActuelle;

	private Float energieStockable;
	private Float energieRestante;

	private Set<Element> consommateurs;

	
	
	public SourceEnergie(Integer id, 
			TypeElement typeCode, 
			Code elementCode, 
			Float masse, 
			Integrite integrite, 
			String description, 
			Integrite integriteActuelle, 
			Float debitEnergie) {
		super(typeCode, elementCode, masse, integrite, 0f, description, id, integriteActuelle, EtatElement.INACTIF, null);
		generationdEnergie = debitEnergie;
		consommationActuelle = 0f;
	}

	public void refresh(Element e, Evenement event) {
		if (event == null || e == null) {
			refresh();
		}

		switch (event) {
			case URGENCE:
				deconnexionUrgence(e);
			case DESTRUCTION:
			default:
				break;
		}

		refresh();
	}

	public void deconnexionUrgence(Element e) {
		Float consoNrj = e.getConsoNrj();

		if (getGenerationdEnergie() * TAUX_URGENCE < consoNrj) {
			Integer degatsPropages = Math.round(consoNrj * (1 - TAUX_URGENCE));
			degatElectrique(degatsPropages);
		}
	}

	public void refresh() {

		this.consommationActuelle = 0f;
		try {
			for (Element element : consommateurs) {
				if (element.estActif()) {
					ajouteElementActif(element);
				}
			}
		} catch (FournitureEnergieDepasseeException fedex) {
			log.info("Depassement de la capacité du générateur");
			desactiveTout();
		}
		;
	}

	private void ajouteElementActif(Element actif) throws FournitureEnergieDepasseeException {
		this.consommationActuelle += actif.getConsoNrj();

		if (this.consommationActuelle <= this.generationdEnergie)
			return;

		throw new FournitureEnergieDepasseeException(this);
	}

	private void degatElectrique(Integer degatsPropages) {
		Float degatsRepartis = 0f;

		for (Element element : consommateurs) {
			Float proportion = element.getConsoNrj() / getGenerationdEnergie();
			degatsRepartis += degatsPropages * proportion;
			element.diminueIntegrite(Math.round(degatsPropages * proportion));
		}

		diminueIntegrite(Math.round(degatsPropages - degatsRepartis));
	}

	// TODO: ajouter un synchro la dessus
	public void connecterElement(Element e) {
		if (consommateurs == null) {
			consommateurs = new HashSet<Element>();
		}

		consommateurs.add(e);
		e.connecterSourceEnergie(this);
		refresh();
	}

	public void deconnecterElement(Element e) {
		consommateurs.remove(e);
	}

	public Float getGenerationdEnergie() {
		return generationdEnergie;
	}

	public Float getEnergieStockable() {
		return energieStockable;
	}

	public Float getEnergieRestante() {
		return energieRestante;
	}

	@Override
	protected Integer destruction(Integer valeurDommage) {
		Integer retour = super.destruction(valeurDommage);
		desactiveTout();
		return retour;
	}

	private void desactiveTout() {
		for (Element element : consommateurs) {
			element.inactiver();
		}
	}

}
