package bomberman.model;

import java.util.Random;

public class GestorTablero {
	private static GestorTablero miGestor =null;
	private Tablero t;
	
	
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
	
	public void inicializarTablero(String bomberman, String tipoTablero)
	{	
		
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
		for (int i = 0; i < 10; i++) {
			crearEnemigosAleatorio();
		}

	}
	
	private void crearEnemigosAleatorio() {
		Random r = new Random();
		int x,y;
		do {
			x = r.nextInt(t.getRows());
			y = r.nextInt(t.getCols());
		} while (!t.esPosicionValida(x, y) || (x == 0 & y == 0) || t.getCasilla(x,y).tieneBloque());
		Enemigo enemigo = new Enemigo(x,y);
		t.getCasilla(x,y).setEnemigo(enemigo);
		t.getEnemigos().add(enemigo);
		}
	
	public Tablero getTablero()
	{
		return this.t;
	}
}