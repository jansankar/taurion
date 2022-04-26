package org.pcorp.taurion.metier.domaine.element;

import org.pcorp.taurion.metier.configuration.ElementConfiguration;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.domaine.evenement.ResultatDiminutionIntegrite;
import org.pcorp.taurion.metier.type.Integrite;

public class Element extends ElementConfiguration {
	private Integer id;
	private Integrite integriteActuelle;
	private EtatElement etatActuel;
	private SourceEnergie sourceEnergie;

	
	public Element(TypeElement typeCode, Code elementCode, Float masse, Integrite integrite, Float consoNrj,
			String description, Integer id, Integrite integriteActuelle, EtatElement etatActuel,
			SourceEnergie sourceEnergie) {
		super(typeCode, elementCode, masse, integrite, consoNrj, description);
		this.id = id;
		this.integriteActuelle = integriteActuelle;
		this.etatActuel = etatActuel;
		this.sourceEnergie = sourceEnergie;
		if (sourceEnergie != null)
			sourceEnergie.connecterElement(this);
	}



	/**
	 * Application d'une perte d'integrite
	 * 
	 * @param valeur
	 * @return
	 */
	public ResultatDiminutionIntegrite diminueIntegrite(Integer valeur) {
		Integrite nouvelleIntegrite = integriteActuelle.diminueIntegrite(valeur);

		ResultatDiminutionIntegrite resultatDiminutionIntegrite = null;

		if (nouvelleIntegrite.compareTo(getIntegrite()) < 0) {
			setEtatActuel(EtatElement.ENDOMMAGE);
		}

		if (nouvelleIntegrite.isNulle()) {
			setEtatActuel(EtatElement.DETRUIT);
		}

		return resultatDiminutionIntegrite;
	}

	/**
	 * Calculer le resultat d'une perte d'integrite
	 * 
	 * @param nouvelleIntegrite
	 * @return
	 */
	protected ResultatDiminutionIntegrite calculResultatDiminutionIntegrite(Integer nouvelleIntegrite) {
		ResultatDiminutionIntegrite resultatDiminutionIntegrite = new ResultatDiminutionIntegrite(
				integriteActuelle.getIntegrite(), getEtatActuel());

		if (resultatDiminutionIntegrite.getNouvelEtat() == EtatElement.DETRUIT) {
			resultatDiminutionIntegrite.setDommagePropage(destruction(nouvelleIntegrite));
		}

		return resultatDiminutionIntegrite;
	}

	/**
	 * Evenement suite destruction de l'element
	 * 
	 * @param valeurDommage
	 * @return
	 */
	protected Integer destruction(Integer valeurDommage) {
		setEtatActuel(EtatElement.DETRUIT);
		sourceEnergie.refresh();
		if (valeurDommage < 0)
			return valeurDommage * -1;
		return 0;
	}

	public void activer() {
		if (getConsoNrj() > 0 && sourceEnergie == null)
			throw new RuntimeException("element non alimenté - activation impossible");

		if (getConsoNrj() <= 0 && sourceEnergie == null) {
			setEtatActuel(EtatElement.ACTIF);
			return;
		}

		if (!integriteActuelle.isNulle()) {
			setEtatActuel(EtatElement.ACTIF);
			sourceEnergie.refresh();
		} else {
			throw new RuntimeException("element détruit/endommagé - activation impossible");
		}
	}

	public void inactiver() {
		if (getEtatActuel() == EtatElement.ACTIF || getEtatActuel() == EtatElement.STANDBY) {
			setEtatActuel(EtatElement.INACTIF);
			sourceEnergie.refresh();
		}
	}

	public void connecterSourceEnergie(SourceEnergie sourceEnergie) {
		this.sourceEnergie = sourceEnergie;
	}

	protected void setEtatActuel(EtatElement etat) {
		this.etatActuel = etat;
	}

	public boolean estActif() {
		return getEtatActuel() == EtatElement.ACTIF;
	}

	public Integer getId() {
		return id;
	}

	public EtatElement getEtatActuel() {
		return etatActuel;
	}

	public SourceEnergie getSourceEnergie() {
		return sourceEnergie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Element other = (Element) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return getTypeCode().equals(other.getTypeCode());
	}
}
