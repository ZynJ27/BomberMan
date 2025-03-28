package bomberman.model;

public class BombermanNegro extends Bomberman{

	public BombermanNegro(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.setMaxBombas(1);
	}

	@Override
	public String getTipo() {
		return "negro";
	}

}
