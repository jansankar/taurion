package org.pcorp.taurion.metier.domaine.construction;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.domaine.etat.EtatElement;
import org.pcorp.taurion.metier.ordonanceur.IA;
import org.pcorp.taurion.metier.type.Tonne;

public abstract class ObjetComplexe {
	private long id;
	private Coordonnees position;
	private TypeObjet typeObjet;
	private Tonne masse;
	private EtatElement etat;
	private IA ia;

	public ObjetComplexe(long id, TypeObjet typeObjet, Tonne masse, IA ia) {
		this.id = id;
		this.typeObjet = typeObjet;
		position = new Coordonnees(0, 0, 0);
		this.masse = masse;
		this.ia = ia;
	}

	public ObjetComplexe(long id, TypeObjet typeObjet) {
		this(id, typeObjet, new Tonne(0f), null);
	}

	public void activer() {
		etat = EtatElement.ACTIF;
	}

	public void desactive() {
		etat = EtatElement.INACTIF;
	}

	public boolean estActive() {
		return etat == EtatElement.ACTIF;
	}

	public long getId() {
		return id;
	}

	public Coordonnees getPosition() {
		return position;
	}

	public void setPosition(Coordonnees position) {
		this.position = position;
	}

	public TypeObjet getTypeObjet() {
		return typeObjet;
	}

	public void setMasse(Tonne t) {
		masse = t;
	}

	public Tonne getMasse() {
		return masse;
	}

}
