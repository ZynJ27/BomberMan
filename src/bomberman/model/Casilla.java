package bomberman.model;
import java.util.Observable;

import bomberman.viewcontroller.JLabel2;

public class Casilla extends Observable{
	private Bloque bloque;
	private Bomba bomba;
	private Enemigo enemigo;
	private Bomberman bomberman;
	private Explosion explosion;
	
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
		notificar();
	}
	
	public void setBomba(int pX, int pY, String tipo) {
		if ((this.bomba==null && !tipo.equals(""))||tipo.equals("")) {
			if (tipo.equals("super")) {
				this.bomba = new BombaSuper(pX,pY);
				this.bomberman.plantarBomba();
			}else if(tipo.equals("ultra")) {
				this.bomba = new BombaUltra(pX,pY);
				this.bomberman.plantarBomba();
			} else {
				this.bomba = null;
			}
			notificar();
		}
        
    }

    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
        notificar();
    }

    public void setBomberMan(Bomberman bomberMan) {
        this.bomberman = bomberMan;
        notificar();
    }
    
    public void setExplosion(Explosion explosion) {
    	if(this.explosion!=null) {
    		this.explosion.pararTimer();
    	}
    	this.explosion = explosion;
    	notificar();
    }

	public boolean tieneBloque() {
		// TODO Auto-generated method stub
		return bloque!=null;
	}

	private void notificar() {
		// TODO Auto-generated method stub
		setChanged();
		Object[] array = new Object[5];
		if (this.bomberman!=null) {
			if (this.bomberman instanceof BombermanBlanco) {
				array[0] = (Object) "blanco";
			} else if (this.bomberman instanceof BombermanNegro) {
				array[0] = (Object) "negro";
			}
		} else {
			array[0] = "";
		}
		
		if (this.bomba!=null) {
			array[1] = (Object) "super";
		} else {
			array[1] = "";
		}
		
		if (this.bloque!=null) {
			if (this.bloque instanceof BloqueBlando) {
				array[2] = (Object) "blando";
			} else if (this.bloque instanceof BloqueDuro) {
				array[2] = (Object) "duro";
			}
		} else {
			array[2] = "";
		}
		
		if (this.enemigo!=null) {
			array[3] = (Object) "globo";
		} else {
			array[3] = "";
		}
		
		if (this.explosion!=null) {
			array[4] = (Object) "explosion";
		} else {
			array[4] = "";
		}
		notifyObservers(array);
	}

	public boolean tieneBloqueDuro() {
		// TODO Auto-generated method stub
		boolean a = false;
		if (this.bloque instanceof BloqueDuro) {
			a=true;
		}
		return a;
	}

	public void actualizar() {
		// TODO Auto-generated method stub
		this.notificar();
	}

}
