package org.pcorp.taurion.metier.domaine.element;

import org.pcorp.taurion.metier.domaine.element.carburant.Reservoir;
import org.pcorp.taurion.metier.domaine.element.ressource.Carburant;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.domaine.exception.NonActiveException;
import org.pcorp.taurion.metier.type.Integrite;
import org.pcorp.taurion.metier.type.Tonne;

public class Propulseur extends Element {
	public final static float POUSSEE_TOUTE = 1f;
	public final static float POUSSEE_UN_TIERS = 1 / 3f;
	public final static float POUSSEE_DEMIE = 0.5f;
	public final static float POUSSEE_DEUX_TIERS = 2 / 3f;

	private Float twr;
	private Float isp;

	
	
	public Propulseur(
			Integer id,
			Code elementCode, Float masse, Integrite integrite, Float consoNrj,
			String description,  Integrite integriteActuelle,
			SourceEnergie sourceEnergie, Float twr, Float isp) {
		super(TypeElement.PROPULSION, elementCode, masse, integrite, consoNrj, description, id, integriteActuelle, EtatElement.INACTIF, sourceEnergie);
		this.isp = isp;
		this.twr = twr;
	}

	public Float getPousseeMax() {
		return getRatioPoidsPousse() * getMasse();
	}

	/**
	 * declenche l'activation du propulseur pour t seconde
	 * en utilisant x pourcent de puissance et le carburant du reservoir passé
	 * en parametre
	 * 
	 * @param t
	 * @param pourcentagePuissance
	 * @param reservoir
	 * @return
	 * @throws NonActiveException
	 */
	public Float activePoussee(Float t, Float pourcentagePuissance, Reservoir reservoir) throws NonActiveException {
		if (!estActif()) {
			throw new NonActiveException(this);
		}

		// calcul de la quantite de carburant utilisée
		Float unitesCarburantsConsommees = (t / (isp / (getMasse()/twr))) * pourcentagePuissance;
		boolean activationPossible = reservoir.consomme(unitesCarburantsConsommees);

		if (!activationPossible) {
			return 0f;
		}

		return getPousseeLineraire(t, pourcentagePuissance, reservoir.getCarburant());
	}

	private Float getPousseeLineraire(Float t, Float pourcentagePuissance, Carburant carburant) {
		return getPousseeMax() * t * pourcentagePuissance * carburant.getIndice();
	}

	private Float getRatioPoidsPousse() {
		return twr;
	}

}
