package bomberman.model;

public class EnemigoDoria extends Enemigo {
    public EnemigoDoria(int pX, int pY, int pId) {
        super(pX, pY, pId);
    }

    @Override
    public String getTipo() {
        return "doria";
    }
}
