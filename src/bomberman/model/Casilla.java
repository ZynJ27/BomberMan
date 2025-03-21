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
		
		if (pBloque.equals("duro")) {
			this.bloque=new BloqueDuro();
		}else if (pBloque.equals("blando")) {
			this.bloque=new BloqueBlando();
		}else {
		this.bloque=null;
		}
		notificar();
	}
	
	public void setBomba(String tipo) {
		if ((this.bomba==null && !tipo.equals(""))||tipo.equals("")) {
			if (tipo.equals("super")) {
				this.bomba = new BombaSuper(x,y);
				this.bomberman.plantarBomba();
			}else if(tipo.equals("ultra")) {
				this.bomba = new BombaUltra(x,y);
				this.bomberman.plantarBomba();
			} else {
				this.bomba = null;
			}
			notificar();
		}
        
    }

    public void setEnemigo(Enemigo pEnemigo) {
        this.enemigo = pEnemigo;
        notificar();
    }

    public void setBomberMan(Bomberman pBomberMan) {
        this.bomberman = pBomberMan;
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

	public void actualizar() { //Para actualizar la vista al iniciar la partida.
		this.notificar();
	}

}
