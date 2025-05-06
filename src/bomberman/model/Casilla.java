package bomberman.model;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

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
		}else {
		this.bloque=null;
		}
		notificar(false);
	}
	
	public void setBomba(String tipo) {
		if ((this.bomba==null && !tipo.equals(""))||tipo.equals("")) {
			if (!tipo.equals("")) {
				this.bomba=BombaGenerator.getBombaGenerator().generarBomba(tipo, x, y);
				this.bomberman.realizarPlantadoBomba();
			} else {
				this.bomba.pararTimer();
				this.bomba = null;
			}
			notificar(false);
		}
    }

	public void setEnemigo(Enemigo pEnemigo) {
	    boolean win = false;
	    if(this.explosion != null && (pEnemigo != null || this.enemigo != null)) {
	        if(pEnemigo != null) {
	            pEnemigo.pararTimer();
	        } else {
	            this.enemigo.pararTimer();
	        }
	        this.enemigo = null;

	        win = !GestorTablero.getGestor().getTablero().comprobarEnemigosVivos();
	        
	        if(win) {
	            GestorSonidos.getGestorSonidos().detenerMusica("partida");
	            GestorSonidos.getGestorSonidos().sonido("ganar");
	            GestorTablero.getGestor().getTablero().notificarVictoria();
	        }
	    } 
	    else {
	        this.enemigo = pEnemigo;
	    }
	    
	    if(this.bomberman != null && (pEnemigo != null || this.enemigo != null)) {
	        this.bomberman.changeState(new EstadoMuerto());
	        GestorSonidos.getGestorSonidos().detenerMusica("partida");
	        GestorSonidos.getGestorSonidos().sonido("perder");
	        GestorTablero.getGestor().getTablero().avisoMuertoBomberman();
	    }
	    
	    notificar(win);
	}

    public void setBomberMan(Bomberman pBomberMan) {
    	this.bomberman=pBomberMan;
    	if (pBomberMan!=null&&(this.enemigo!=null||this.explosion!=null)) {
    		this.bomberman.changeState(new EstadoMuerto());
    	}
        notificar(false);
    }
    
    public void setExplosion(String pExplosion) {
        boolean win = false;
        if(this.explosion != null) {
            this.explosion.pararTimer();
        }  
        if(!pExplosion.equals("")) {
            if (this.bomberman != null) {
                this.bomberman.changeState(new EstadoMuerto());
                GestorSonidos.getGestorSonidos().detenerMusica("partida");
                GestorSonidos.getGestorSonidos().sonido("perder");
                GestorTablero.getGestor().getTablero().avisoMuertoBomberman();
            }
            if(this.enemigo != null) {
                this.enemigo.pararTimer();
                this.enemigo = null;
                win = !GestorTablero.getGestor().getTablero().comprobarEnemigosVivos();
                if (win) {
                    GestorTablero.getGestor().getTablero().notificarVictoria();
                    GestorSonidos.getGestorSonidos().detenerMusica("partida");
                    GestorSonidos.getGestorSonidos().sonido("ganar");
                }
            }
            this.explosion = new Explosion(x, y);
            if (win && this.bomberman != null && "muerto".equals(this.bomberman.getEstadoActual())) {
                win = false; 
            }
        } else {
            this.explosion = null;
        }
        notificar(win);
    }

	public boolean tieneBloque() {
		// TODO Auto-generated method stub
		return bloque!=null;
	}

	private void notificar(boolean win) {
		setChanged();
		// array[i] = (condicion) ? valor_si_verdadero : valor_si_falso;
		Object[] array = new Object[8];
		array[0] = (this.bomberman!=null) ? this.bomberman.getTipo() : "";
		array[1] = (this.bomba!=null) ? this.bomba.getTipo() : "";
		array[2] = (this.bloque!=null) ? this.bloque.getTipo() : "";
		array[3] = (this.enemigo!=null) ? this.enemigo.getTipo() : "";
		array[4] = (this.explosion!=null) ? "explosion" : "";
		array[5] = win;
		array[6] = (this.bomberman!=null) ? this.bomberman.getEstadoActual() : "";;
		array[7] = (this.bomberman!=null) ? GestorTablero.getGestor().getTablero().getDir() : "";
		notifyObservers(array);
	}
	
	public boolean tieneBloqueDuro() {
		return (bloque != null) && bloque.getTipo().equals("duro");
	}

	public void actualizar() { //Para actualizar la vista al iniciar la partida.
		this.notificar(false);
	}
	
	public boolean tieneBomberman() {
		return bomberman!=null;
	}

	public void moverBomberman(int pX, int pY) {
		Bomberman b = this.bomberman;
		this.setBomberMan(null);
		b.realizarMovimiento(pX, pY);
		GestorTablero.getGestor().getTablero().getCasilla(this.x+pX,this.y+pY).setBomberMan(b);
	}

	public void ponerBomba() {
		if(this.bomberman.puedePlantarBomba()) {
			this.setBomba(this.bomberman.getBomba());
			GestorSonidos.getGestorSonidos().sonido("bombaPuesta");
		}
	}

	public void bombaExplotada() {
		this.bomberman.bombaExplotada();
	}

	public void crearBomberMan(String string) {
		this.bomberman=BombermanGenerator.getBombermanGenerator().generarBomberman(string, 0, 0);
		notificar(false);
	}
	
	public boolean crearEnemigo(String string,int i, int j, int id) {
		boolean creado= false;
		if (this.estaVacio()&& i+j>=2) {
			this.enemigo=EnemigoGenerator.getEnemigoGenerator().generarEnemigo(string, i, j, id);
			creado = true;
		}
		notificar(false);
		return creado;
	}

	public boolean tieneEnemigo() {
		return this.enemigo!=null;
	}
	
	public boolean tieneEsteEnemigo(int id) {
		boolean tiene = false;
		if(this.enemigo!=null) {
			tiene = this.enemigo.eres(id);
		}
		return tiene;
	}

	public void moverEnemigo(int pX, int pY) {
		if(this.enemigo!=null) {
			Enemigo e = this.enemigo;
			this.setEnemigo(null);
			e.mover(pX, pY);
			GestorTablero.getGestor().getTablero().getCasilla(this.x+pX,this.y+pY).setEnemigo(e);
		}
	}

	public boolean tieneExplosion() {
		return this.explosion!=null;
	}

	public String getEstadoBomberman(){
		return this.bomberman.getEstadoActual();
	}

	public void pararTimers() {
		// TODO Auto-generated method stub
		if (this.bomba!=null) {
			this.bomba.pararTimer();
		}
		if (this.enemigo!=null) {
			this.enemigo.pararTimer();
		}
		if (this.explosion!=null) {
			this.explosion.pararTimer();
		}
	}
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
