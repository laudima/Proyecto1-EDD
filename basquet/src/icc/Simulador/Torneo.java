package icc.Simulador;
import java.util.Random;
import java.util.Scanner;
import icc.Modelo.Equipos;
import icc.colors.Colors;

/**
 * Clase que simula un Torneo de un deporte.
 *
 * @author Laura Rodriguez
 */
public abstract class Torneo{


    protected Equipos[] equipos;

    protected int e;//numero de equipos
    protected int r=0;//ronda actual
    protected int n;//rondas totales
    protected int k;//partido actual

    protected int partidos; // numero de partidos durante el Torneo
    protected boolean termina;//Indica si ya se realizaron todas las rondas.

    /**
    * Método constructor de la clase.Inicializa un arreglo con la cantidad de
    * equipos que se le pasa cómo parametro.Si el número de equipos es impar o par,
    * cambia el número de rondas y de partidos.
    * @param e - indica el número de equipos en el torneo.
    */
    protected Torneo(int e){
        this.e = e;
        equipos = new Equipos[e];

        if (e % 2 == 1){
            partidos = (e-1)/2;
            n = e;
        } else{
            partidos = e/2 - 1;
            n = e-1;
        }
    }
    /**
    * Indica si el torneo ha terminado.
    * @return boolean termina.
    */
    public boolean Termina(){
        return termina;
    }

    /**
    * Método abstracto que le da nombre y id acada equipo.
    * @param nombre - Nombre del equipo.
    */
    protected abstract void SetEquipo(String nombre);

    /**
    * Método abstracto para imprimer el Score.
    */
    protected abstract void Score();

    /**
    * Método abstracto para imprimer el Score de un equipo en particular.
    */
    protected abstract void ScoreEquipo();
    /**
    * Método abstracto para iniciar una ronda.
    */
    protected abstract void NuevaRonda();

    /**
    * Método que devuelve la opción solo si es valida. Se utiliza para pedir el
    * equipo al que se quiere saber el score en particular.
    * @return int <code>opción</code>
    * @param mensaje -Texto del menu
    * @param error -Mensaje de que la opcion es invalida
    * @param min -La menor opcion valida
    * @param max -La opcion mas grande que podemos poner
    */
    public int getInt(String mensaje, String error, int min, int max) {
        int val;
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println(mensaje);
            if (scn.hasNextInt()) {
                val = scn.nextInt();
                // (-infinito, min) || (max, infinito)
                if (val < min || max < val) {
                    System.out.println(error);
                } else {
                    return val;
                }
            } else {
                scn.next();
                System.out.println(error);
            }
        }
    }
}
