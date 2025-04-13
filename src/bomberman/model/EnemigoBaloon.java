package bomberman.model;

public class EnemigoBaloon extends Enemigo {
    public EnemigoBaloon(int pX, int pY, int pId) {
        super(pX, pY, pId);
    }

    @Override
    public String getTipo() {
        return "baloon";
    }
}
