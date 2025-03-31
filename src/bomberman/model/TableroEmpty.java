package bomberman.model;

import java.util.Random;

public class TableroEmpty extends Tablero {
    public TableroEmpty() {
        super();
    }

    public void inicializarTablero() {
        //inicializar todas las casillas
        for (int i=0;i<getRows();i++) {
            for (int j=0;j<getCols();j++) {
                getCasillas()[i][j]=new Casilla(i,j);
            }
        }
        
        // Asegurarse de que no haya bloques ni enemigos en (0,0), (0,1) y (1,0)
        getCasillas()[0][1].setBloque(""); // Asegurar que (0,1) esta vacia
        getCasillas()[1][0].setBloque(""); // Asegurar que (1,0) esta vacia
    }

	@Override
	public String getTipoTablero() {
		return "empty";
	}

}
