package bomberman.model;

public class BloqueDuro extends Bloque {

	public BloqueDuro() {
		super(false);
	}

	@Override
	public String getTipo() {
		return "duro";
	}

}
