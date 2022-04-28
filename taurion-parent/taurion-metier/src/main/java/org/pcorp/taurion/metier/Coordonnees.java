package org.pcorp.taurion.metier;

import org.pcorp.taurion.metier.domaine.vecteur.Vecteur;
import org.pcorp.taurion.metier.domaine.vecteur.Vitesse;
import org.pcorp.taurion.metier.util.MathUtil;

public class Coordonnees {
	private Float x;
	private Float y;
	private Float z;

	@SuppressWarnings("unused")
	private Coordonnees() {

	}

	public Coordonnees(Float x, Float y, Float z) {
		super();
		init(x, y, z);
	}

	public Coordonnees(Integer x, Integer y, Integer z) {
		super();
		init(x.floatValue(), y.floatValue(), z.floatValue());
	}

	public Coordonnees(Double x, Double y, Double z) {
		super();
		init(x.floatValue(), y.floatValue(), z.floatValue());
	}

	public Coordonnees(Long x, Long y, Long z) {
		super();
		init(x.floatValue(), y.floatValue(), z.floatValue());
	}

	private void init(Float x, Float y, Float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void appliqueDeplacement(Vitesse v, Float t) {
		x = x + v.getVitesseX() * t;
		y = y + v.getVitesseY() * t;
		z = z + v.getVitesseZ() * t;
	}
	
	public Vecteur getDifference(Coordonnees destination) {
		Float diffX = destination.getX() - this.x;
		Float diffY = destination.getY() - this.y;
		Float diffZ = destination.getZ() - this.z;

		return new Vecteur(diffX, diffY, diffZ);
	}

	@Override
	public Coordonnees clone() {
		return new Coordonnees(x, y, z);
	}

	public Float getX() {
		return x;
	}

	public Float getY() {
		return y;
	}

	public Float getZ() {
		return z;
	}

	@Override
	public String toString() {
		return "Coordonnees [x=" + MathUtil.round(x, 2) + ", y=" + MathUtil.round(y, 2) + ", z=" + MathUtil.round(z, 2) + "]";
	}
	
	
}
