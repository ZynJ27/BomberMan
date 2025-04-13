package bomberman.model;

public class EnemigoFactory {
	private static EnemigoFactory miEnemigoFactory;

    private EnemigoFactory() {}

    public static EnemigoFactory getEnemigoFactory() {
        if(miEnemigoFactory == null) {miEnemigoFactory = new EnemigoFactory();}
        return miEnemigoFactory;
    }

    public Enemigo generarEnemigo(String tipo, int pX, int pY, int pId) {
        Enemigo enemigo=null;
        if (tipo.equals("baloon")){enemigo=new EnemigoBaloon(pX, pY, pId);}
        if (tipo.equals("doria")){enemigo=new EnemigoDoria(pX, pY, pId);}
        if (tipo.equals("pass")){enemigo=new EnemigoPass(pX, pY, pId);}
        return enemigo;
    }
}
