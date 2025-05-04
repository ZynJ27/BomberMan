package bomberman.model;

public class BombermanFactory {
    private static BombermanFactory miBombermanFactory;

    private BombermanFactory() {}

    public static BombermanFactory getBombermanFactory() {
        if (miBombermanFactory == null) { miBombermanFactory = new BombermanFactory(); }
        return miBombermanFactory;
    }

    public Bomberman generarBomberman(String tipo, int pX, int pY){
        Bomberman bomberman=null;
        if (tipo.equals("blanco")) {bomberman=new BombermanBlanco(pX,pY);}
        if (tipo.equals("negro")) {bomberman=new BombermanNegro(pX,pY);}
        if (tipo.equals("rojo")) {bomberman=new BombermanRojo(pX,pY);}
        if (tipo.equals("azul")) {bomberman=new BombermanAzul(pX,pY);}
        return bomberman;
    }
}
