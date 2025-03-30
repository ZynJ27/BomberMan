package bomberman.model;

public class TableroFactory {
    private static TableroFactory miTableroFactory;

    private TableroFactory(){}

    public static TableroFactory getTableroFactory(){
        if (miTableroFactory==null) {miTableroFactory=new TableroFactory(); }
        return miTableroFactory;
    }

    public Tablero generarTablero(String tipo){
        Tablero tablero=null;
        if (tipo.equals("classic")){tablero=new TableroClassic();}
        if (tipo.equals("soft")){tablero=new TableroSoft();}
        if (tipo.equals("empty")){tablero=new TableroEmpty();}
        return tablero;
    }
}
