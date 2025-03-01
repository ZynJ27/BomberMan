package bomberman.model;

public class BombermanBlanco extends Bomberman {
	private int numBombas;
	private int maxBombas;

	public BombermanBlanco(int x, int y) {
		super(x, y);
		this.numBombas=0;
		this.maxBombas=10;
	}

	@Override
	public void plantarBomba() {
		if (puedePlantarBomba()) {
			this.numBombas++;
		}
		
	}

	@Override
	public boolean puedePlantarBomba() {
		return numBombas<maxBombas;
	}

	@Override
	public void bombaExplotada() {
		this.numBombas--;
	}

	@Override
	public Bomba crearBomba() {
		return new BombaSuper(getX(),getY());
	}
	

}
