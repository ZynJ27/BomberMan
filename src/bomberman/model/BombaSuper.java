package bomberman.model;

public class BombaSuper extends Bomba {

	public BombaSuper(int x, int y) {
		super(x, y);
		this.setRadioExplosion(1);
	}

	@Override
	public String getTipo() {
		return "super";
	}

}
