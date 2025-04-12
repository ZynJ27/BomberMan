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
    	if(this.explosion!=null&&(pEnemigo!=null||this.enemigo!=null)) {
    		if(pEnemigo!=null) {
    			pEnemigo.pararTimer();
    		}else {
    			this.enemigo.pararTimer();
    		}
    		this.enemigo=null;
    		win = !GestorTablero.getGestor().getTablero().comprobarEnemigosVivos();//true si hay enemigos vivos
    	}else {
        this.enemigo = pEnemigo;
    	}
    	notificar(win);
    }

    public void setBomberMan(Bomberman pBomberMan) {
    	this.bomberman=pBomberMan;
        notificar(false);
    }
    
    public void setExplosion(String pExplosion) {
    	boolean win = false;
    	if(this.explosion!=null) {
    		this.explosion.pararTimer();
    	}
    	if(!pExplosion.equals("")) {
			if (this.bomberman != null) {
				this.bomberman.changeState(new EstadoMuerto());
			}
    		if(this.enemigo!=null) {
    			this.enemigo.pararTimer();
    			this.enemigo = null;
    			win = !GestorTablero.getGestor().getTablero().comprobarEnemigosVivos();
    		}
    		this.explosion = new Explosion(x,y);
    	}else {
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
		Object[] array = new Object[7];
		array[0] = (this.bomberman!=null) ? this.bomberman.getTipo() : "";
		array[1] = (this.bomba!=null) ? "super" : "";
		array[2] = (this.bloque!=null) ? this.bloque.getTipo() : "";
		array[3] = (this.enemigo!=null) ? "globo" : "";
		array[4] = (this.explosion!=null) ? "explosion" : "";
		array[5] = win;
		array[6] = (this.bomberman!=null) ? this.bomberman.getEstadoActual() : "";;
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
		}
		
	}

	public void bombaExplotada() {
		this.bomberman.bombaExplotada();
	}

	public void crearBomberMan(String string) {
		// TODO Auto-generated method stub
		this.bomberman=BombermanGenerator.getBombermanGenerator().generarBomberman(string, 0, 0);
		notificar(false);
	}
	
	public boolean crearEnemigo(String string,int i, int j,int id) {
		// TODO Auto-generated method stub
		boolean creado= false;
		if (this.estaVacio()&& i+j>=2) {
			if(string.equals("globo")) {
				this.enemigo=new Enemigo1(i,j,id);
			}
			creado = true;
		}
		notificar(false);
		return creado;
	}

	public boolean tieneEnemigo() {
		return this.enemigo!=null;
	}
	
	public boolean tieneEsteEnemigo(int id) {
		// TODO Auto-generated method stub
		boolean tiene = false;
		if(this.enemigo!=null) {
			tiene = this.enemigo.eres(id);
		}
		return tiene;
	}

	public void moverEnemigo(int pX, int pY) {
		// TODO Auto-generated method stub
		Enemigo e = this.enemigo;
		this.setEnemigo(null);
		e.mover(pX, pY);
		GestorTablero.getGestor().getTablero().getCasilla(this.x+pX,this.y+pY).setEnemigo(e);
	}

	public boolean tieneExplosion() {
		// TODO Auto-generated method stub
		return this.explosion!=null;
	}

	public String getEstadoBomberman(){
		return this.bomberman.getEstadoActual();
	}

}
