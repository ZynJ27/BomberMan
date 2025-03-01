package bomberman.model;

public class Casilla {
	private Bloque bloque;
	private Bomba bomba;
	private Enemigo enemigo;
	private Bomberman bomberman;
	
	public Casilla() {
		this.bloque=null;
		this.bomba=null;
		this.enemigo=null;
		this.bomberman=null;
	}

	public boolean estaVacio() {
        return bloque == null && bomba == null && enemigo == null && bomberman == null;
    }
	
	public void setBloque(Bloque bloque) {
		this.bloque=bloque;
	}
	
	public void setBomba(Bomba bomba) {
        this.bomba = bomba;
    }

    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
    }

    public void setBomberMan(Bomberman bomberMan) {
        this.bomberman = bomberMan;
    }
	
	
}
