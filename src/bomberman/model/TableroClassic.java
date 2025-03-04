package bomberman.model;

public class TableroClassic extends Tablero{

	private static TableroClassic miTablero = null;
	
	private TableroClassic() {
		super();
	}
	
	public static Tablero getTablero() {
		if(miTablero==null) {
			miTablero = new TableroClassic();
		}
		return miTablero;
	}
	
}
