package icc.Modelo;

import icc.colors.Colors;
/**
 * Clase que pone las caracteristicas del equipo de basquetbol.
 *
 * @author Laura Rodríguez.
 */
public class EquipoBasquet extends Equipos {

    protected int canasta_favor, canasta_contra;

    protected int tiro_favor, tiro_contra;

    protected int libre_favor, libre_contra;

    /**
    * Método constructor de la clase. Cada equipo tiene nombre y un Id único.
    * @param id - Id del equipo
    * @param nombre - Nombre del equipo
    */
    public EquipoBasquet(int id, String nombre){
        super(id,nombre);
    }

    /**
    * Método para asignar el número de canastas a favor.
    * @param n - número de canastas a favor
    */
    public void CanastaFavor(int n){
        canasta_favor+=n;
    }

    /**
    * Método para asignar el número de canastas a favor.
    * @param n - número de canastas encontra
    */
    public void CanastaEncontra(int n){
        canasta_contra+=n;
    }

    /**
    * Método para asignar el número de tiros de tres puntos a favor.
    * @param n - número de tiros de tres puntos a favor
    */
    public void TiroFavor(int n){
        tiro_favor+= n;
    }

    /**
    * Método para asignar el número de tiros de tres puntos encontra.
    * @param n - número de tiros de tres puntos encontra
    */
    public void TiroEncontra(int n){
        tiro_contra+=n;
    }

    /**
    * Método para asignar el número de tiros libres a favor.
    * @param n - número de tiros libres a favor
    */
    public void LibreFavor(int n){
        libre_favor+=n;
    }

    /**
    * Método para asignar el número de tiros libres encontra
    * @param n - número de tiros libres a encontra
    */
    public void LibreEncontra(int n){
        libre_contra+=n;
    }

    /**
    * Método para calcular el total de puntos a favor.
    */
    public void TotalFavor(){
        afavor = canasta_favor*2 + tiro_favor*3 + libre_favor;
    }

    /**
    * Método para calcular el total de puntos encontra.
    */
    public void TotalEncontra(){
        encontra = canasta_contra*2 + tiro_contra*3 + libre_contra;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append(Colors.HIGH_INTENSITY);
        sb.append("\t Canasta: \n");
        sb.append(Colors.GREEN);
        sb.append("\t\t Favor:" + canasta_favor + "\n");
        sb.append(Colors.RED);
        sb.append("\t\t Contra:" + canasta_contra + "\n");
        sb.append(Colors.RESTORE);
        sb.append(Colors.HIGH_INTENSITY);
        sb.append("\t Tiros de tres puntos: \n");
        sb.append(Colors.GREEN);
        sb.append("\t\t Favor:" + tiro_favor + "\n");
        sb.append(Colors.RED);
        sb.append("\t\t Contra:" + tiro_contra + "\n");
        sb.append(Colors.RESTORE);
        sb.append(Colors.HIGH_INTENSITY);
        sb.append("\t Tiro libre: \n");
        sb.append(Colors.GREEN);
        sb.append("\t\t Favor:" + libre_favor + "\n");
        sb.append(Colors.RED);
        sb.append("\t\t Contra:" + libre_contra + "\n");
        sb.append(Colors.RESTORE);
        sb.append("-----------------------------------------\n");
        sb.append("-----------------------------------------\n");
        return sb.toString();
    }
}
