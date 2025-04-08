package bomberman.model;

public class BloqueGenerator {
    private static BloqueGenerator miBloqueGenerator;

    private BloqueGenerator() {}

    public static BloqueGenerator getBloqueGenerator() {
        if(miBloqueGenerator == null) {miBloqueGenerator = new BloqueGenerator();}
        return miBloqueGenerator;
    }

    public Bloque generarBloque(String tipo) {
        Bloque bloque=BloqueFactory.getBloqueFactory().generarBloque(tipo);
        return bloque;
    }
}
