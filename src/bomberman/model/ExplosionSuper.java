package bomberman.model;

public class ExplosionSuper implements ExplosionStrategy {
    @Override
    public void explotar(int pX,int pY) {
        GestorTablero.getGestor().getTablero().explotarBomba(pX,pY,1);
    }
}
