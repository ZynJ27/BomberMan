package bomberman.model;

public class BombermanNegro extends Bomberman{

	public BombermanNegro(int x, int y) {
		super(x, y);
		this.setMaxBombas(1);
	}

	@Override
	public String getTipo() {
		return "negro";
	}

	@Override
	public String getBomba() {
		return "ultra";
	}

}
