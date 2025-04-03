package bomberman.model;

public class BombaGenerator {
    private static BombaGenerator miBG;

    private BombaGenerator() {}

    public static BombaGenerator getBombaGenerator() {
        if (miBG == null) { miBG = new BombaGenerator(); }
        return miBG;
    }

    public Bomba generarBomba(String tipo, int pX, int pY) {
        Bomba bomba = BombaFactory.getTableroFactory().generarBomba(tipo,pX,pY);
        return bomba;
    }
}
