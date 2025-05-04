package bomberman.model;

public class BombaFactory {
    private static BombaFactory miBombaFactory;

    private BombaFactory(){}

    public static BombaFactory getBombaFactory(){
        if (miBombaFactory==null) { miBombaFactory=new BombaFactory(); }
        return miBombaFactory;
    }

    public Bomba generarBomba(String tipo, int pX, int pY) {
        Bomba bomba=null;
        if (tipo.equals("super")){bomba=new BombaSuper(pX,pY);}
        if (tipo.equals("ultra")){bomba=new BombaUltra(pX,pY);}
        if(tipo.equals("diagonal")) {bomba=new BombaDiagonal(pX, pY);}
        if(tipo.equals("x")) {bomba=new BombaX(pX, pY);}
        return bomba;
    }
}