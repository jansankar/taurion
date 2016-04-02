package org.pcorp.taurion.metier.domaine.element;

import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.domaine.evenement.ResultatDiminutionIntegrite;

public class Element {
	private Integer id;
	private TypeElement typeCode;
	private Code elementCode;
	private Float masse;
	private Integer integrite;
	private Integer integriteActuelle;
	private EtatElement etatActuel;
	private Float consoNrj;
	private SourceEnergie sourceEnergie;

	public Element(Integer id, TypeElement typeCode, Code elementCode, Float masse, Integer integrite, Float consoNrj) {
		super();
		this.id = id;
		this.typeCode = typeCode;
		this.elementCode = elementCode;
		this.masse = masse;
		this.integrite = integrite;
		this.integriteActuelle = integrite;
		this.consoNrj = consoNrj;
	}

	/**
	 * Application d'une perte d'integrite
	 * 
	 * @param valeur
	 * @return
	 */
	public ResultatDiminutionIntegrite diminueIntegrite(Integer valeur) {
		Integer nouvelleIntegrite = integriteActuelle - valeur;

		ResultatDiminutionIntegrite resultatDiminutionIntegrite = null;

		if (nouvelleIntegrite < integrite) {
			setEtatActuel(EtatElement.ENDOMMAGE);
			setIntegriteActuelle(nouvelleIntegrite);
		}

		if (nouvelleIntegrite <= 0) {
			setEtatActuel(EtatElement.DETRUIT);
			setIntegriteActuelle(0);
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
				getIntegriteActuelle(), getEtatActuel());

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
		if (consoNrj > 0 && sourceEnergie == null)
			throw new RuntimeException("element non alimenté - activation impossible");

		if (consoNrj <= 0 && sourceEnergie == null) {
			setEtatActuel(EtatElement.ACTIF);
			return;
		}

		if (getIntegriteActuelle() > 0) {
			setEtatActuel(EtatElement.ACTIF);
			sourceEnergie.refresh();
		} else {
			throw new RuntimeException("element endommagé - activation impossible");
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

	public TypeElement getTypeCode() {
		return typeCode;
	}

	public Code getElementCode() {
		return elementCode;
	}

	public Float getMasse() {
		return masse;
	}

	public Integer getIntegrite() {
		return integrite;
	}

	public Integer getIntegriteActuelle() {
		return integriteActuelle;
	}

	private void setIntegriteActuelle(Integer integriteActuelle) {
		this.integriteActuelle = integriteActuelle;
	}

	public EtatElement getEtatActuel() {
		return etatActuel;
	}

	public Float getConsoNrj() {
		return consoNrj;
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
		return true;
	}
}
