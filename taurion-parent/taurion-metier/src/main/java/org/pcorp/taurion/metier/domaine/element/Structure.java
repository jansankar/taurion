package org.pcorp.taurion.metier.domaine.element;

public class Structure extends Element {

	public Structure(Integer id, TypeElement typeCode, Code elementCode, Float masse, Integer integrite) {
		super(id, typeCode, elementCode, masse, integrite, 0f);
	}

}
