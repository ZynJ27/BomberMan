package bomberman.model;

public abstract class Bomberman {
	
	private int x, y;
	private int numBombas;
	private int maxBombas;
	
	protected Bomberman(int x, int y) {
		this.x=x;
		this.y=y;
		this.numBombas=0;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int pX) {
		this.x=pX;
	}
	
	public void setY(int pY) {
		this.y=pY;
	}

	public void mover(int pX, int pY) {
		this.x+=pX;
		this.y+=pY;
	}
	
	public void plantarBomba() {
		if (puedePlantarBomba()) {
			this.numBombas++;
			System.out.println(numBombas);
		}
	}
	
	public boolean puedePlantarBomba() {
		return numBombas<maxBombas;
	}
	
	public  void bombaExplotada() {
		this.numBombas--;
		System.out.println(numBombas);
	}

	protected void setMaxBombas(int maxBombas) {
		this.maxBombas = maxBombas;
	}
	
	
}
