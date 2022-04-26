package org.pcorp.taurion.metier.domaine.element.ressource;

public class Carburant extends Ressources {
	private Float indice;

	public Carburant(Integer id, Float masseVolumique, Float indice) {
		super(id, masseVolumique);
		this.indice = indice;
	}

	public Float getIndice() {
		return indice;
	}

}
