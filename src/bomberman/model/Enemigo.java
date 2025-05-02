package bomberman.model;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Enemigo {

	private int x, y; 
	private int id;
	private Timer timer;

    public Enemigo(int pX, int pY, int pId) {
        this.x = pX;
        this.y = pY;
        this.id = pId;
        TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				moverAleatorio();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void moverAleatorio() {
    	GestorTablero.getGestor().getTablero().moverEnemigo(x,y,id);
    }


	public boolean eres(int id2) {
		// TODO Auto-generated method stub
		return this.id==id2;
	}


	public void mover(int pX, int pY) {
		// TODO Auto-generated method stub
		this.x+=pX;
		this.y+=pY;
	}


	public void pararTimer() {
		// TODO Auto-generated method stub
			this.timer.cancel();
	}
	
	public abstract String getTipo();
	
}
