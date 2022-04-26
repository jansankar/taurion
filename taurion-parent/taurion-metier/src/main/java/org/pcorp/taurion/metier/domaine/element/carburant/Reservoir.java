package org.pcorp.taurion.metier.domaine.element.carburant;

import org.pcorp.taurion.metier.domaine.element.Code;
import org.pcorp.taurion.metier.domaine.element.Element;
import org.pcorp.taurion.metier.domaine.element.TypeElement;
import org.pcorp.taurion.metier.domaine.element.ressource.Carburant;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.type.Integrite;

public class Reservoir extends Element {
	private Float capacite;
	private Float reste;
	private Carburant carburant;

	// FIXME ajout d'une pompe

	public Reservoir( //
			Integer id, 
			Code elementCode, 
			Float masse, 
			Integrite integrite, 
			Float consoNrj,
			String description, 
			Integrite integriteActuelle, 
			EtatElement etatActuel,
			SourceEnergie sourceEnergie, 
			Float capacite, 
			Float reste) {
		super(TypeElement.RESERVOIR_CARBURANT, elementCode, masse, integrite, consoNrj, description, id, integriteActuelle, etatActuel, sourceEnergie);
		this.capacite = capacite;
		this.reste = reste;	
	}
	

	public Float getCapacite() {
		return capacite;
	}

	public Float getReste() {
		return reste;
	}

	public Float getComplement() {
		return capacite - reste;
	}

	/**
	 * Retourne faux s'il ne reste pas assez de carburant
	 * Retourne true s'il y en avait assez
	 * 
	 * @param consoNrj
	 * @return
	 */
	public boolean consomme(Float consoNrj) {
		if (!estActif()) {
			throw new RuntimeException("element inactif - demande non prise en compte");
		}

		if (consoNrj > reste)
			return false;

		reste -= consoNrj;

		return true;
	}

	public boolean estRempli() {
		return reste >= capacite;
	}

	/**
	 * Retourne faux si le reservoir n'est pas rempli
	 * Retourne True si maintenant il l'est
	 * Calcul les propriete du nouveau carburant s'il y a mélange
	 * 
	 * @param ajout
	 * @return
	 */
	public boolean ajoute(Float ajout, Carburant carburant) {
		Carburant carburantRes = getCarburant();

		if (carburantRes == null || carburantRes.getIndice() > carburant.getIndice()) {
			this.carburant = carburant;
		}

		reste += ajout;

		if (estRempli()) {
			reste = capacite;
			return true;
		}

		return false;
	}

	@Override
	protected Integer destruction(Integer valeurDommage) {
		super.destruction(valeurDommage);
		return Integer.valueOf(reste.intValue());
	}

	public Carburant getCarburant() {
		return carburant;
	}
}
