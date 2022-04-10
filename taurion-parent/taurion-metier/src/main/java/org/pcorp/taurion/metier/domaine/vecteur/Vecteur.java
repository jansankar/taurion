package org.pcorp.taurion.metier.domaine.vecteur;

public class Vecteur {
	private Float x;
	private Float y;
	private Float z;

	protected Vecteur() {
		this.x = 0f;
		this.y = 0f;
		this.z = 0f;
	}

	private void init(Float x, Float y, Float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vecteur(Integer x, Integer y, Integer z) {
		super();
		init(x.floatValue(), y.floatValue(), z.floatValue());
	}

	public Vecteur(Double x, Double y, Double z) {
		super();
		init(x.floatValue(), y.floatValue(), z.floatValue());
	}

	public Vecteur(Long x, Long y, Long z) {
		super();
		init(x.floatValue(), y.floatValue(), z.floatValue());
	}

	public Vecteur(Vecteur v) {
		init(v.getX(), v.getY(), v.getZ());
	}

	public Vecteur(Float x, Float y, Float z) {
		super();
		init(x, y, z);
	}

	public Vecteur resultatSommeVecteur(Vecteur ajout) {
		if (ajout == null) {
			return new Vecteur(this.x, this.y, this.z);
		}

		Float nX = this.x + ajout.getX();
		Float nY = this.y + ajout.getY();
		Float nZ = this.z + ajout.getZ();

		return new Vecteur(nX, nY, nZ);
	}

	public void augmenterVecteur(Vecteur augmentation) {
		Vecteur v = resultatSommeVecteur(augmentation);
		copieVecteur(v);
	}

	private void copieVecteur(Vecteur aCopier) {
		this.x = aCopier.getX();
		this.y = aCopier.getY();
		this.z = aCopier.getZ();
	}

	public boolean vecteurNul() {
		return x == 0 && y == 0 &&  z == 0;
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

}
