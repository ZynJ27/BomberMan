package bomberman.model;

public interface EstadoBomberman {
    public void realizarMovimiento(Bomberman b, int pX, int pY);
    public void realizarPlantadoBomba(Bomberman b);
    public String getNombreEstado();
}
