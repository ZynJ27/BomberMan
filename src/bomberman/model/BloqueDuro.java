package bomberman.model;

public class BloqueDuro extends Bloque {

	public BloqueDuro() {
		super(false);
	}

	@Override
	protected Object getTipo() {
		return "duro";
	}

}
