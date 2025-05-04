package bomberman.model;

public class BombermanRojo extends Bomberman {

	public BombermanRojo(int x, int y) {
		super(x, y);
		this.setMaxBombas(10);
	}

	@Override
	public String getTipo() {
		return "rojo";
	}

	@Override
	public String getBomba() {
		return "diagonal";
	}

}
