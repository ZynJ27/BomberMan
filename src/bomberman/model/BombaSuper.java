package bomberman.model;

import java.util.Timer;
import java.util.TimerTask;

public class BombaSuper extends Bomba {

	public BombaSuper(int x, int y) {
		super(x, y);
		this.tiempoExplosion=3;
		this.radioExplosion=1;
		
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                explotar();
            }
        }, tiempoExplosion * 1000);
    }

	@Override
	public void explotar() {
	    Tablero tablero = TableroClassic.getTablero();
	    int x = super.getX();
	    int y = super.getY();
	    Casilla actual = tablero.getCasilla(x, y);

	    actual.quitarBomba(); 
	    crearYMostrarExplosion(actual); 

	    verificarYDestruir(tablero, x - 1, y); 
	    verificarYDestruir(tablero, x + 1, y); 
	    verificarYDestruir(tablero, x, y - 1); 
	    verificarYDestruir(tablero, x, y + 1); 
	}

	private void verificarYDestruir(Tablero tablero, int x, int y) {
	    if (x >= 0 && x < Tablero.ROWS && y >= 0 && y < Tablero.COLS) {
	        Casilla casilla = tablero.getCasilla(x, y);

	        if (casilla.tieneBloque() && casilla.getBloque().esDestructible()) {
	            casilla.setBloque(null); 
	            crearYMostrarExplosion(casilla); 
	        } else if (casilla.getBomberman() != null) {
	            casilla.quitarBomberman(); 
	            crearYMostrarExplosion(casilla);
	        } else if (casilla.estaVacio()) {
	            crearYMostrarExplosion(casilla); 
	        }
	    }
	}

	private void crearYMostrarExplosion(Casilla casilla) {
	    Explosion e = new Explosion();
	    casilla.setExplosion(e);
	    e.iniciarExplosion(() -> {
	        casilla.quitarExplosion();
	        casilla.notificar();
	    });
	    casilla.notificar();
	}

}
