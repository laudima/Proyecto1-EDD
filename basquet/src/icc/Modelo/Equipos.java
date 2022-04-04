package icc.Modelo;

import icc.colors.Colors;
/**
 * Clase que pone las caracteristicas de los equipos y las almacena.
 *
 * @author Laura Rodríguez.
 */
public abstract class Equipos{


    protected int id;

    protected String nombre;

    protected int ganados, perdidos, empatados, afavor, encontra,diferencia;

    /**
    * Método constructor de la clase. Cada equipo tiene nombre y un Id único.
    * @param id - ID del equipo
    * @param nombre - Nombre del equipo
    */
    protected Equipos(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    /**
    * Método que devuleve el nombre del equipo.
    * @return String nombre
    */
    public String daNombre(){
        return nombre;
    }
    /**
    * Método que devuelve el id del equipo.
    * @return int id.
    */
    public int daID(){
        return id;
    }

    /**
    * Método que agrega un partido ganado.
    */
    public void gano(){
        ganados++;
    }
    /**
    * Método que agrega un partido perdido.
    */
    public void perdio(){
        perdidos++;
    }
    /**
    * Método que agrega un partido empatado.
    */
    public void empato(){
        empatados++;
    }

    /**
    * Método que devuelve los partidos ganados.
    * @return int ganados
    */
    public int GetGanados(){
        return ganados;
    }

    /**
    * Método que devuelve los puntos totales a favor.
    * @return int afavor
    */
    public int GetTotalFavor(){
        return afavor;
    }

    /*
    * Método que devuelve los puntos totales encontra.
    * @return int encontra
    */
    public int GetTotalEncontra(){
        return encontra;
    }

    /**
    * Método que devuelve la diferencia entre puntos totales a favor y encontra.
    * @return int diferencia
    */
    public int GetTotalDiff(){
        diferencia = afavor-encontra;
        return diferencia;
    }

    /**
    * Método que calcula la diferencia entre puntos totales a favor y encontra.
    */
    public void TotalDiff(){
        diferencia = afavor-encontra;
    }

    /**
    * Método abstracto para calcular el total de puntos a favor.
    */
    protected abstract void TotalFavor();

    /**
    * Método abstracto para calcular el total de puntos encontra.
    */
    protected abstract void TotalEncontra();


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(Colors.HIGH_INTENSITY);
        sb.append("\t ID: " + id + "\n");
        sb.append("\t Nombre: " + nombre + "\n");
        sb.append(Colors.GREEN);
        sb.append("\t Total a favor: " + afavor + "\n");
        sb.append(Colors.RED);
        sb.append("\t Total en contra: " + encontra + "\n");
        sb.append(Colors.BLUE);
        sb.append("\t Diferencia: " + diferencia + "\n");
        sb.append(Colors.GREEN);
        sb.append("\t Juegos Ganados: " + ganados + "\n");
        sb.append(Colors.RED);
        sb.append("\t Juegos Perdidos: " + perdidos + "\n");
        sb.append(Colors.BLUE);
        sb.append("\t Juegos Empatados: " + empatados + "\n");
        sb.append(Colors.RESTORE);
        sb.append("-----------------------------------------\n");
        sb.append(Colors.HIGH_INTENSITY);
        sb.append(Colors.BLUE);
        sb.append("\t Anotaciones: \n");
        sb.append(Colors.RESTORE);
        return sb.toString();
    }
}
