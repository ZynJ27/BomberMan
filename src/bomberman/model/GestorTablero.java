package bomberman.model;

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
			t.setBomberMan(new BombermanBlanco(0, 0));
		}
		else if (bomberman.equals("Bomberman2"))
		{
			System.out.println("Bomberman negro");
			t.setBomberMan(new BombermanNegro(0, 0));
		}
		else if (bomberman.equals("Bomberman3"))
		{
			System.out.println("Bomberman azul");
		}
		else if (bomberman.equals("Bomberman4"))
		{
			System.out.println("Bomberman rojo");
		}
	}
	
	public Tablero getTablero()
	{
		return this.t;
	}
}