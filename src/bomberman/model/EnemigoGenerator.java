package bomberman.model;

public class EnemigoGenerator {
	private static EnemigoGenerator miEnemigoGenerator;

    private EnemigoGenerator() {}

    public static EnemigoGenerator getEnemigoGenerator() {
        if(miEnemigoGenerator == null) {miEnemigoGenerator = new EnemigoGenerator();}
        return miEnemigoGenerator;
    }

    public Enemigo generarEnemigo(String tipo, int pX, int pY, int pId) {
    	Enemigo enemigo=EnemigoFactory.getEnemigoFactory().generarEnemigo(tipo, pX, pY, pId);
        return enemigo;
    }
}
