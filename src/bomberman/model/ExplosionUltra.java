package bomberman.model;

public class ExplosionUltra implements ExplosionStrategy {

    @Override
    public void explotar(int pX, int pY) {
        GestorTablero.getGestor().getTablero().explotarBomba(pX,pY,20);
    }
}
