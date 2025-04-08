package bomberman.model;

public class BloqueFactory {
    private static BloqueFactory miBloqueFactory;

    private BloqueFactory() {}

    public static BloqueFactory getBloqueFactory() {
        if(miBloqueFactory == null) {miBloqueFactory = new BloqueFactory();}
        return miBloqueFactory;
    }

    public Bloque generarBloque(String tipo) {
        Bloque bloque=null;
        if (tipo.equals("duro")){bloque=new BloqueDuro();}
        if (tipo.equals("blando")){bloque=new BloqueBlando();}
        return bloque;
    }
}
