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

	public void quitarBomberman() {
		// TODO Auto-generated method stub
		this.bomberman=null;
		notificar();
	}

	public boolean tieneBloque() {
		// TODO Auto-generated method stub
		return bloque!=null;
	}

	public void notificar() {
		// TODO Auto-generated method stub
		setChanged();
		notifyObservers(new Object[] {(Object)this.bomberman,(Object)this.bomba,(Object)this.bloque,(Object)this.enemigo,(Object)this.explosion});
	}

}
