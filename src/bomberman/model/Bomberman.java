package bomberman.model;

public abstract class Bomberman {
	
	private int x, y;
	private int numBombas;
	private int maxBombas;
	private EstadoBomberman estado;

	protected Bomberman(int x, int y) {
		this.x=x;
		this.y=y;
		this.numBombas=0;
		this.estado=new EstadoVivo();
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
		}
	}
	
	public boolean puedePlantarBomba() {
		return numBombas<maxBombas;
	}
	
	public  void bombaExplotada() {
		this.numBombas--;
	}

	protected void setMaxBombas(int maxBombas) {
		this.maxBombas = maxBombas;
	}
	
	public abstract String getTipo();
	
	public abstract String getBomba();

	public void changeState(EstadoBomberman nuevoEstado) {
		this.estado=nuevoEstado;
	}

	public void realizarMovimiento(int pX, int pY) {
		this.estado.realizarMovimiento(this,pX,pY);
	}

	public void realizarPlantadoBomba() {
		this.estado.realizarPlantadoBomba(this);
	}

	public String getEstadoActual() {
		return estado.getNombreEstado();
	}

}
