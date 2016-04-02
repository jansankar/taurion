package org.pcorp.taurion.metier.domaine.vecteur;

public class Vecteur {
	private Float x;
	private Float y;
	private Float z;

	@SuppressWarnings("unused")
	private Vecteur() {

	}

	private void init(Float x, Float y, Float z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
