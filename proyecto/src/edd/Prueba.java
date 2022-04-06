
package edd;

import java.util.Scanner;
import edd.colors.Colors;
import edd.estructuras.*;
import edd.modelo.*;
import edd.simulador.*;

/**
 * Clase principal, incluye a la interfaz y llma a las clases del simulador
 * @author Laura Itzel Rodríguez Dimayuga
 * @version Apr 2022
 */
public class Prueba {
    /**
    * Método que devuelve la opción del menu solo si es valida
    * @return int <code>opción</code>
    * @param mensaje -Texto del menu
    * @param error -Mensaje de que la opcion es invalida
    * @param min -La menor opcion valida
    * @param max -La opcion mas grande que podemos poner
    */
    public static int getInt(String mensaje, String error, int min, int max) {
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

    public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Bienvenido al simulador de Wizard");
        String menu, error;
        int opcion;

        /*Mensaje del menú*/
        menu = "Ingresa la cantidad de personas a jugar:";
        error = "Por favor ingresa una opcion valida.";

        opcion = getInt(menu,error,3,6);

        Ronda torneo = new Ronda(opcion);
    }
}
