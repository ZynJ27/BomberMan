package bomberman.model;

public class TableroGenerator {
    private static TableroGenerator miTG;
    private TableroGenerator() {}

    public static TableroGenerator getTableroGenerator(){
        if (miTG==null){miTG=new TableroGenerator();}
        return miTG;
    }

    public Tablero generarTablero(String tipo) {
        Tablero tablero=TableroFactory.getTableroFactory().generarTablero(tipo);
        return tablero;
    }
}
