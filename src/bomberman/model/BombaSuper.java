package bomberman.model;

public class BombaSuper extends Bomba {

	public BombaSuper(int x, int y) {
		super(x, y, new ExplosionSuper());
	}

}
