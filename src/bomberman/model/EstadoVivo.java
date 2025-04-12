package bomberman.model;

public class EstadoVivo implements EstadoBomberman {

    @Override
    public void realizarMovimiento(Bomberman b, int pX, int pY) {
        b.mover(pX, pY);
    }

    @Override
    public void realizarPlantadoBomba(Bomberman b) {
        b.plantarBomba();
    }

    @Override
    public String getNombreEstado() {
        return "vivo";
    }
}
