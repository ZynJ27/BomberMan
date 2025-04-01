package bomberman.model;

public class Enemigo {

    private int x, y; 

    public Enemigo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

   
    public void mover(int pX, int pY) {
        this.x += pX;
        this.y += pY;
    }
}

