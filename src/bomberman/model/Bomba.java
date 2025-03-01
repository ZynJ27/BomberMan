package bomberman.model;

public abstract class Bomba {
	protected int x, y;
	protected int tiempoExplosion; //tiempo en segundos para que explote
	protected int radioExplosion;
	
	public Bomba(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public abstract void explotar();

}
