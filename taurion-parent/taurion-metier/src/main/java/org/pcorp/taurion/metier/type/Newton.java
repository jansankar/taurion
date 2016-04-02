package org.pcorp.taurion.metier.type;

public class Newton {
	private static final String UNITE_KILO_NEWTON = "kN";

	public static Float kn(Float value) {
		return value * 1000;
	}

	public static Float mn(Float value) {
		return value * 1000 * 1000;
	}

	public static String toStringKn(Float value) {
		return Float.toString((Math.round(value * 1000) / 1000000f)).concat(UNITE_KILO_NEWTON);
	}
}
