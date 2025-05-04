package bomberman.model;

public class BombaX extends Bomba {

	public BombaX(int x, int y) {
		super(x, y);
		this.setRadioExplosion(-20);
	}

	@Override
	public String getTipo() {
		
		return "x";
	}

}
