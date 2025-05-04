package bomberman.model;

import java.util.Random;
import java.util.stream.IntStream;

public class TableroEmpty extends Tablero {
    public TableroEmpty() {
        super();
    }

    public void inicializarTablero() {
        //inicializar todas las casillas
    	IntStream.range(0, getRows()) //stream de �ndices para filas
    	.forEach(i->IntStream.range(0, getCols()) //para cada fila crea un stream de �ndices para columnas
    			.forEach(j->getCasillas()[i][j]=new Casilla(i,j)));

        // Asegurarse de que no haya bloques ni enemigos en (0,0), (0,1) y (1,0)
        getCasillas()[0][1].setBloque(""); // Asegurar que (0,1) esta vacia
        getCasillas()[1][0].setBloque(""); // Asegurar que (1,0) esta vacia
    }

	@Override
	public String getTipoTablero() {
		return "empty";
	}

}
