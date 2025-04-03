package bomberman.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BombaUltra extends Bomba {

	public BombaUltra(int x, int y) {
		super(x, y,new ExplosionUltra());
	}

}