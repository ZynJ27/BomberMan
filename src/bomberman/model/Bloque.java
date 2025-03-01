package bomberman.model;

public abstract class Bloque {;
	protected boolean destructible;
	
	public Bloque(boolean pDestructible) {
		this.destructible=pDestructible;
	}
	
	public boolean isDestructible() {
        return destructible;
    }
}
