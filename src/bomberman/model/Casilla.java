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
		this.bloque=null;
		this.bomba=null;
		this.enemigo=null;
		this.bomberman=null;
		this.x=pX;
		this.y=pY;
	}

	public boolean estaVacio() {
        return bloque == null && bomba == null && enemigo == null && bomberman == null;
    }
	
	public void setBloque(String pBloque) {
		if (!pBloque.equals("")) {
			this.bloque=BloqueGenerator.getBloqueGenerator().generarBloque(pBloque);
		} else {
			this.bloque=null;
		}
		notificar();
	}
	
	public void setBomba(String tipo) {
		if ((this.bomba==null && !tipo.equals(""))||tipo.equals("")) {
			if (tipo.equals("")) {
				this.bomba = null;
			} else {
				this.bomba = BombaGenerator.getBombaGenerator().generarBomba(tipo, x, y);
				this.bomberman.plantarBomba();
			}
			notificar();
		}
    }

    public void setEnemigo(Enemigo pEnemigo) {
        this.enemigo = pEnemigo;
        notificar();
    }

    public void setBomberMan(Bomberman pBomberMan) {
    	this.bomberman=pBomberMan;
        notificar();
    }
    
    public void setExplosion(String pExplosion) {
    	if(this.explosion!=null) {
    		this.explosion.pararTimer();
    	}
    	if(!pExplosion.equals("")) {
    		this.explosion = new Explosion(x,y);
    	}else {
    		this.explosion = null;
    	}
    	notificar();
    }

	public boolean tieneBloque() {
		return bloque!=null;
	}

	private void notificar() {
		setChanged();
		// array[i] = (condicion) ? valor_si_verdadero : valor_si_falso;
		Object[] array = new Object[5];
		array[0] = (this.bomberman!=null) ? this.bomberman.getTipo() : "";
		array[1] = (this.bomba!=null) ? "super" : "";
		array[2] = (this.bloque!=null) ? this.bloque.getTipo() : "";
		array[3] = (this.enemigo!=null) ? "globo" : "";
		array[4] = (this.explosion!=null) ? "explosion" : "";
		notifyObservers(array);
	}
	
	public boolean tieneBloqueDuro() {
		return (bloque != null) && bloque.getTipo().equals("duro");
	}

	public void actualizar() { //Para actualizar la vista al iniciar la partida.
		this.notificar();
	}
	
	public boolean tieneBomberman() {
		return bomberman!=null;
	}

	public void moverBomberman(int pX, int pY) {
		Bomberman b = this.bomberman;
		this.setBomberMan(null);
		b.mover(pX, pY);
		GestorTablero.getGestor().getTablero().getCasilla(this.x+pX,this.y+pY).setBomberMan(b);
		
		
	}

	public void ponerBomba() {
		if(this.bomberman.puedePlantarBomba()) {
			this.setBomba(this.bomberman.getBomba());
		}
		
	}

	public void bombaExplotada() {
		this.bomberman.bombaExplotada();
	}

	public void crearBomberMan(String tipo) {
		this.bomberman=BombermanGenerator.getBombermanGenerator().generarBomberman(tipo,0,0);
	}

}