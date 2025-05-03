package bomberman.model;

import java.util.Random;

public class GestorTablero {
	private static GestorTablero miGestor =null;
	private Tablero t;
	private String bomberman;
	
	
	private GestorTablero ()
	{}
	
	public static GestorTablero getGestor()
	{
		if(miGestor==null)
		{
			miGestor=new GestorTablero();
		}
		return miGestor;
	}
	
	public void inicializarTablero(String pBomberman, String tipoTablero)
	{
		this.bomberman=pBomberman;
		t=TableroGenerator.getTableroGenerator().generarTablero(tipoTablero);
		t.inicializarTablero();
		if (bomberman.equals("Bomberman1"))
		{
			System.out.println("Bomberman blanco");
			t.crearBomberMan("blanco");
		}
		else if (bomberman.equals("Bomberman2"))
		{
			System.out.println("Bomberman negro");
			t.crearBomberMan("negro");
		}
		else if (bomberman.equals("Bomberman3"))
		{
			System.out.println("Bomberman azul");
		}
		else if (bomberman.equals("Bomberman4"))
		{
			System.out.println("Bomberman rojo");
		}
		for (int i = 0; i < 6; i++) {
			if (tipoTablero.equals("classic")) {
				crearEnemigosAleatorio("baloon");
			} else if (tipoTablero.equals("soft")) {
				crearEnemigosAleatorio("doria");
			} else { // empty
				String[] tipos = {"baloon", "doria", "pass"};
				Random r = new Random();
				String randomTipo = tipos[r.nextInt(tipos.length)];
				crearEnemigosAleatorio(randomTipo);
			}
		}
	}
	
	public Tablero getTablero()
	{
		return this.t;
	}
	
	private void crearEnemigosAleatorio(String tipo) {
		Random r = new Random();
		int x,y;
		int id=-1; //da igual que se salte numero el id siempre es distinto
		do {
			id=id+1;
			x = r.nextInt(Tablero.getRows());
			y = r.nextInt(Tablero.getCols());
		} while (!t.crearEnemigo(tipo, x, y, id));
		}

	public String getTipoTablero() {
		// TODO Auto-generated method stub
		return this.getTablero().getTipoTablero();
	}

	public String getTipoBomberman() {
		// TODO Auto-generated method stub
		return this.bomberman;
	}
}