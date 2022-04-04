package icc.Modelo;

import icc.colors.Colors;
/**
 * Clase que pone las caracteristicas del equipos de futbol.
 *
 * @author Laura Rodríguez.
 */
public class EquipoFutbol extends Equipos {

    public int gol_favor, gol_contra;

    /**
    * Método constructor de la clase. Cada equipo tiene nombre y un Id único.
    * @param id - Id del equipo
    * @param nombre - Nombre del equipo 
    */
    public EquipoFutbol(int id, String nombre){
        super(id,nombre);
    }

    /**
    * Método para asignar el número de goles a favor.
    * @param n - número de goles a favor
    */
    public void GolFavor(int n){
        gol_favor += n;
    }

    /*
    * Método para asignar el número de goles encontra.
    * @param n - número de goles encontra
    */
    public void GolContra(int n){
        gol_contra += n;
    }

    /**
    * Método para calcular el total de puntos a favor.
    */
    public void TotalFavor(){
        afavor = gol_favor;
    }

    /**
    * Método para calcular el total de puntos encontra.
    */
    public void TotalEncontra(){
        encontra = gol_contra;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append(Colors.HIGH_INTENSITY);
        sb.append("\t Gol: ");
        sb.append(Colors.GREEN);
        sb.append("\t Favor:" + gol_favor + "\n");
        sb.append(Colors.RED);
        sb.append("\t\t Contra:" + gol_contra + "\n");
        sb.append(Colors.RESTORE);
        sb.append("-----------------------------------------\n");
        sb.append("-----------------------------------------\n");

        return sb.toString();
    }

}
