package bomberman.model;

import java.util.Observable;

import bomberman.viewcontroller.JLabel2;

public class Casilla extends Observable{
	private Bloque bloque;
	private Bomba bomba;
	private Enemigo enemigo;
	private Bomberman bomberman;
	private Explosion explosion;
	private int x;
	private int y;
	
	public Casilla(int pX, int pY) {
		x = pX;
		y = pY;
		this.bloque=null;
		this.bomba=null;
		this.enemigo=null;
		this.bomberman=null;
		
	}

	public boolean estaVacio() {
        return bloque == null && bomba == null && enemigo == null && bomberman == null;
    }
	
	public Bomberman getBomberman() {
		return this.bomberman;
	}
	
	public Bloque getBloque() {
		return this.bloque;
	}
	
	public void setBloque(Bloque bloque) {
		this.bloque=bloque;
		notificar();
	}
	
	public void setBomba(Bomba bomba) {
        this.bomba = bomba;
        notificar();
    }

    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
        notificar();
    }

    public void setBomberMan(Bomberman bomberMan) {
        this.bomberman = bomberMan;
        notificar();
    }

    public void setExplosion(Explosion e) {
		this.explosion=e;
		notificar();
		
	}
    
	public void quitarBomberman() {
		this.bomberman=null;
		notificar();
	}
	
	public void quitarExplosion() {
		this.explosion=null;
		notificar();
	}

	public void quitarBomba() {
		this.bomba=null;
		notificar();
	}

	public boolean tieneBloque() {
		return bloque!=null;
	}

	public void notificar() {
		setChanged();
		notifyObservers(new Object[] {(Object)this.bomberman,(Object)this.bomba,(Object)this.bloque,(Object)this.enemigo,(Object)this.explosion});
	}	
}
