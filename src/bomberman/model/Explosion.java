package bomberman.model;

import java.util.Timer;
import java.util.TimerTask;

public class Explosion {
	
	private int x;
	private int y;
	private Timer timer;
	private int tiempoExplosion;
	
	public Explosion(int pX,int pY) {
		
		tiempoExplosion=2;
		x=pX;
		y=pY;
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				actualizarCont();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
	
	private void actualizarCont() {
		tiempoExplosion--;
		if (tiempoExplosion==0) {
			GestorTablero.getGestor().getTablero().quitarExplosion(x,y);
		}
		
	}
	
	public void pararTimer() {
		this.timer.cancel();
	}
	
}
