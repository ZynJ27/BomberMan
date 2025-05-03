package bomberman.model;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class TableroSoft extends Tablero{
    public TableroSoft() {
        super();
    }

    public void inicializarTablero() {
    	//inicializar todas las casillas
    	IntStream.range(0, getRows()) //stream de índices para filas
    	.forEach(i->IntStream.range(0, getCols()) //para cada fila crea un stream de índices para columnas
    			.forEach(j->getCasillas()[i][j]=new Casilla(i,j)));

        //colocar bloques blandos aleatoriamente
        Random r=new Random();
        Arrays.stream(getCasillas())
        .flatMap(fila->Arrays.stream(fila))
        .filter(c->c.estaVacio() && !esPosicionInicial(c.getX(), c.getY()) && r.nextDouble()<0.6)
        .forEach(c->c.setBloque("blando"));


        // Asegurarse de que no haya bloques ni enemigos en (0,0), (0,1) y (1,0)
        getCasillas()[0][1].setBloque(""); // Asegurar que (0,1) esta vacia
        getCasillas()[1][0].setBloque(""); // Asegurar que (1,0) esta vacia
    }

	@Override
	public String getTipoTablero() {
		return "soft";
	}
}
