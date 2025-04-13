package bomberman.model;

public class BombaUltra extends Bomba {

	public BombaUltra(int x, int y) {
		super(x, y);
		this.setRadioExplosion(20);
	}

	@Override
	public String getTipo() {
		return "ultra";
	}

}
