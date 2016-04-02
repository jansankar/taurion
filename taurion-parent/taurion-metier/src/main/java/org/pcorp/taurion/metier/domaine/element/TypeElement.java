package org.pcorp.taurion.metier.domaine.element;

public enum TypeElement {
	PROPULSION("PROP"), //
	SHIELD("SHLD"), //
	BLINDAGE("BLIN"), //
	RADAR("RADR"), //
	RESERVOIR_CARBURANT("RESV"), //
	STRUCTURE("STRU"), //
	POMPE("POMP"), //
	GENERATEUR("GENR"), //
	BATTERIE("BATT");

	private TypeElement(String prefixe) {
		this.prefixe = prefixe;
	}

	private String prefixe;

	public String getPrefixe() {
		return prefixe;
	}
}
