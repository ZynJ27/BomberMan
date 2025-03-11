package bomberman.model;

public abstract class Bloque {;
	private boolean destructible;
	
	protected Bloque(boolean pDestructible) {
		this.destructible=pDestructible;
	}
	
	public boolean esDestructible() {
        return destructible;
    }
}
