package bomberman.model;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class Explosion {
	private Timer timer=null;
	
	public Explosion() {
		timer=new Timer();
	}
	
	public void iniciarExplosion(Runnable onFinish) {
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				onFinish.run();
			}
		}, 2000);
	}

}
