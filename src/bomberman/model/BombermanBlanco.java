package bomberman.model;

public class BombermanBlanco extends Bomberman {

	public BombermanBlanco(int x, int y) {
		super(x, y);
		this.setMaxBombas(10);
	}

	@Override
	public String getTipo() {
		return "blanco";
	}

	@Override
	public String getBomba() {
		// TODO Auto-generated method stub
		return "super";
	}	

}
