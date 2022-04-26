package org.pcorp.taurion.metier.domaine.element.calculateur;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.domaine.element.Code;
import org.pcorp.taurion.metier.domaine.element.Element;
import org.pcorp.taurion.metier.domaine.element.TypeElement;
import org.pcorp.taurion.metier.domaine.element.sourceEnergie.SourceEnergie;
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.domaine.vecteur.Vecteur;
import org.pcorp.taurion.metier.type.Integrite;

public class Calculateur extends Element {

	

	public Calculateur(TypeElement typeCode, Code elementCode, Float masse, Integrite integrite, Float consoNrj,
			String description, Integer id, Integrite integriteActuelle, EtatElement etatActuel,
			SourceEnergie sourceEnergie) {
		super(TypeElement.CALCULATEUR, elementCode, masse, integrite, consoNrj, description, id, integriteActuelle, etatActuel, sourceEnergie);
		// TODO Auto-generated constructor stub
	}

	public Vecteur calculDirection(Coordonnees origine, Coordonnees destination) {
		Vecteur v = origine.getDifference(destination);
		return v;
	}

	// public Coordonnees avance(Distance distanceParcourue, Vecteur direction)
	// {

	// s}
}
