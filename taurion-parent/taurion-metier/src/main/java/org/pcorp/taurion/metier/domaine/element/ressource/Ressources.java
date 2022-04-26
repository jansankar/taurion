package org.pcorp.taurion.metier.domaine.element.ressource;

public class Ressources {
	private Integer id;
	private Float masseVolumique;

	public Ressources(Integer id, Float masseVolumique) {
		super();
		this.id = id;
		this.masseVolumique = masseVolumique;
	}

	public Integer getId() {
		return id;
	}

	public Float getMasseVolumique() {
		return masseVolumique;
	}

}
