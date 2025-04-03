package bomberman.model;

public class BombermanGenerator {
    private static BombermanGenerator miBG;

    private BombermanGenerator() {}

    public static BombermanGenerator getBombermanGenerator() {
        if (miBG==null) { miBG=new BombermanGenerator(); }
        return miBG;
    }

    public Bomberman generarBomberman(String tipo, int pX, int pY) {
        Bomberman bomberman=BombermanFactory.getBombermanFactory().generarBomberman(tipo,pX,pY);
        return bomberman;
    }
}
