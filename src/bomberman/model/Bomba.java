package bomberman.model;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomba {
	private int x, y;
	private int tiempoExplosion; //tiempo en segundos para que explote
	private int radioExplosion;
	private Timer timer;
	private ExplosionStrategy explosionStrategy;

	protected Bomba(int x, int y, ExplosionStrategy explosionStrategy) {
		this.x=x;
		this.y=y;
		this.tiempoExplosion=3;
		this.explosionStrategy=explosionStrategy;
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				actualizarCont();
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
	
	protected void actualizarCont() {
		tiempoExplosion--;
		if (tiempoExplosion==0) {
			this.explotar();
		}
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void explotar() {
		explosionStrategy.explotar(x, y);
	}

}
