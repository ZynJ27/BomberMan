package bomberman.model;

public class BloqueBlando extends Bloque {

	public BloqueBlando() {
		super(true);
	}

	@Override
	protected Object getTipo() {
		return "blando";
	}

}
