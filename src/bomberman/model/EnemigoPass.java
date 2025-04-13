package bomberman.model;

public class EnemigoPass extends Enemigo {
    public EnemigoPass(int pX, int pY, int pId) {
        super(pX, pY, pId);
    }

    @Override
    public String getTipo() {
        return "pass";
    }
}
