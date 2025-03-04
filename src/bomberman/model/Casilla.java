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
		setChanged();
		notifyObservers(new Object[] {(Object)this.bomberman,(Object)this.bomba,(Object)this.bloque,(Object)this.enemigo,(Object)this.explosion});
	}

	public boolean estaVacio() {
        return bloque == null && bomba == null && enemigo == null && bomberman == null;
    }
	
	public void setBloque(Bloque bloque) {
		this.bloque=bloque;
		setChanged();
		notifyObservers(new Object[] {(Object)this.bomberman,(Object)this.bomba,(Object)this.bloque,(Object)this.enemigo,(Object)this.explosion});
	}
	
	public void setBomba(Bomba bomba) {
        this.bomba = bomba;
        setChanged();
		notifyObservers(new Object[] {(Object)this.bomberman,(Object)this.bomba,(Object)this.bloque,(Object)this.enemigo,(Object)this.explosion});
    }

    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
        setChanged();
		notifyObservers(new Object[] {(Object)this.bomberman,(Object)this.bomba,(Object)this.bloque,(Object)this.enemigo,(Object)this.explosion});
    }

    public void setBomberMan(Bomberman bomberMan) {
        this.bomberman = bomberMan;
        setChanged();
		notifyObservers(new Object[] {(Object)this.bomberman,(Object)this.bomba,(Object)this.bloque,(Object)this.enemigo,(Object)this.explosion});
    }

	public void quitarBomberman() {
		// TODO Auto-generated method stub
		this.bomberman=null;
		setChanged();
		notifyObservers(new Object[] {(Object)this.bomberman,(Object)this.bomba,(Object)this.bloque,(Object)this.enemigo,(Object)this.explosion});
	}

}
