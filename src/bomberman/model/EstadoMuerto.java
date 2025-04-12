package bomberman.model;

public class EstadoMuerto implements EstadoBomberman{
    @Override
    public void realizarMovimiento(Bomberman b, int pX, int pY) {
        System.out.println("No puede moverse, está muerto.");
    }

    @Override
    public void realizarPlantadoBomba(Bomberman b) {
        System.out.println("No puede plantar bombas, está muerto.");
    }

    @Override
    public String getNombreEstado() {
        return "muerto";
    }
}
