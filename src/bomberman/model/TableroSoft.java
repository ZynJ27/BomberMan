package bomberman.model;

import java.util.Random;

public class TableroSoft extends Tablero{
    public TableroSoft() {
        super();
    }

    public void inicializarTablero() {
        //inicializar todas las casillas
        for (int i=0;i<getRows();i++) {
            for (int j=0;j<getCols();j++) {
                getCasillas()[i][j]=new Casilla(i,j);
            }
        }

        //colocar bloques blandos aleatoriamente
        Random r=new Random();
        for (int i=0;i<getRows();i++) {
            for (int j=0;j<getCols();j++) { //random()
                if (getCasillas()[i][j].estaVacio() &&  !esPosicionInicial(i, j) && r.nextDouble()<0.6) {
                    getCasillas()[i][j].setBloque("blando");
                }
            }
        }

        //colocar BomberMan en (0, 0)
        this.setBomberMan(new BombermanBlanco(0,0));
        getCasillas()[0][0].setBomberMan(getBomberMan());

        // Asegurarse de que no haya bloques ni enemigos en (0,0), (0,1) y (1,0)
        getCasillas()[0][1].setBloque(""); // Asegurar que (0,1) est� vac�a
        getCasillas()[1][0].setBloque(""); // Asegurar que (1,0) est� vac�a
    }

	@Override
	public String getTipoTablero() {
		return "soft";
	}
}
