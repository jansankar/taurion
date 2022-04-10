package org.pcorp.taurion.metier.domaine.element.calculateur;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.domaine.element.Code;
import org.pcorp.taurion.metier.domaine.element.Element;
import org.pcorp.taurion.metier.domaine.element.TypeElement;
import org.pcorp.taurion.metier.domaine.vecteur.Vecteur;

public class Calculateur extends Element {

	public Calculateur(Integer id, Code elementCode, Float masse, Integer integrite, Float consoNrj) {
		super(id, TypeElement.CALCULATEUR, elementCode, masse, integrite, consoNrj);
	}

	public Vecteur calculDirection(Coordonnees origine, Coordonnees destination) {
		Vecteur v = origine.getDifference(destination);
		return v;
	}

	// public Coordonnees avance(Distance distanceParcourue, Vecteur direction)
	// {

	// s}
}
