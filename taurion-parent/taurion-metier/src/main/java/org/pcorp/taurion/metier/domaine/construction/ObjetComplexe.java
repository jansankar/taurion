package org.pcorp.taurion.metier.domaine.construction;

import org.pcorp.taurion.metier.Coordonnees;
import org.pcorp.taurion.metier.type.Tonne;

public abstract class ObjetComplexe {
	private long id;
	private Coordonnees position;
	private TypeObjet typeObjet;
	private Tonne masse;
	
	public ObjetComplexe(long id, TypeObjet typeObjet, Tonne masse) {
		this.id = id;
		this.typeObjet = typeObjet;
		position = new Coordonnees(0, 0, 0);
		this.masse = masse;
	}

	public ObjetComplexe(long id, TypeObjet typeObjet) {
		this(id, typeObjet, new Tonne(0f));
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
