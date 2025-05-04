package bomberman.model;

public class BombermanAzul extends Bomberman {

	public BombermanAzul(int x, int y) {
		super(x, y);
		this.setMaxBombas(1);
	}

	@Override
	public String getTipo() {
		return "azul";
	}

	@Override
	public String getBomba() {
		return "x";
	}

}
