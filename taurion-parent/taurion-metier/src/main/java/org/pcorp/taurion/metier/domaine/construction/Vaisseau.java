package org.pcorp.taurion.metier.domaine.construction;

import java.util.List;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.domaine.element.Composant;
import org.pcorp.taurion.metier.domaine.element.Controlleur;
import org.pcorp.taurion.metier.domaine.element.Propulseur;
import org.pcorp.taurion.metier.domaine.element.Soute;
import org.pcorp.taurion.metier.domaine.element.Structure;
import org.pcorp.taurion.metier.domaine.vecteur.Acceleration;
import org.pcorp.taurion.metier.domaine.vecteur.Vitesse;
import org.pcorp.taurion.metier.element.carburant.Reservoir;

/**
 * Structure de base d'un vaisseau.
 * Un vaisseau est composé d'au moins un propulseur et d'une zone de
 * controlle/passerelle
 * 
 * @author macbookair
 *
 */
public class Vaisseau {
	private Integer id;
	private String nom;
	private String description;

	// information position/vitesse
	private Vitesse vecteurVitesse;
	private Acceleration accCourante;
	private Coordonnees coordonnees;

	// information structure
	private List<Reservoir> reservoirs;
	private Structure structure;
	private List<Controlleur> controlleur;
	private List<Propulseur> propulsion;
	private List<Soute> soutes;

	// composants
	private List<Composant> composants;

	public Vaisseau(Integer id, String nom, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

}
