package bomberman.model;

public class BombaDiagonal extends Bomba {

	protected BombaDiagonal(int x, int y) {
		super(x, y);
		this.setRadioExplosion(-1);
	}

	@Override
	public String getTipo() {
		return "diagonal";
	}

}
