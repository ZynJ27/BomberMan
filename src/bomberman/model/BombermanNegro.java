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
	
	public String getBomba() {
		// TODO Auto-generated method stub
		return "ultra";
	}

}
