package bomberman.model;

public abstract class Bomberman {
	protected int x, y;
	
	public Bomberman(int x, int y) {
		this.x=x;
		this.y=y;
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
	
	public abstract void plantarBomba();
	
	public abstract boolean puedePlantarBomba();
	
	public abstract void bombaExplotada();
	
	public abstract Bomba crearBomba();
	
	
}
