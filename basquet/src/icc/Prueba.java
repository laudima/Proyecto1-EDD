
package icc;

import java.util.Scanner;
import icc.colors.Colors;
import icc.Modelo.*;
import icc.Simulador.*;

/**
 * Clase principal, incluye a la interfaz y llama a las clases de simulación.
 * @author Laura Itzel Rodríguez Dimayuga
 * @version Enero 2022
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

        //Menu de incio | menu de simulación | menu de torneo terminado.
        StringBuilder sb, sb2,sb3;

        String mensaje1, mensaje2, mensaje3, error;

        int opcion,opcion2,opcion3,equipos;

        String nombre_equipo;

        boolean termina=false;

        /*
        * Menú de inicio
        */
        sb = new StringBuilder();
        sb.append(Colors.HIGH_INTENSITY);
        sb.append("Bienvenido este programa es un simulador de un torneo\n");
        sb.append("Round-Robin de futbol o basquetbol.\n");
        sb.append("\n Escoge el deporte que quieres simular. \n");
        sb.append("\t 1.Fútbol\n");
        sb.append("\t 2.Básquetbol\n");
        sb.append(Colors.RESTORE);
        mensaje1 = sb.toString();
        error = "Por favor ingresa una opcion valida.";

        /*
        * Menú simulación
        */
        sb2 = new StringBuilder();
        sb2.append(Colors.HIGH_INTENSITY);
        sb2.append("\n1.Consultar puntuaciones \n");
        sb2.append("2.Continuar Simulación \n");
        sb2.append("0.Salir");
        sb2.append(Colors.RESTORE);
        mensaje2 = sb2.toString();

        /*
        * Menú torneo terminado
        */
        sb3 = new StringBuilder();
        sb3.append(Colors.HIGH_INTENSITY);
        sb3.append("\n1. Consultar todas las puntuaciones \n");
        sb3.append("2. Consultar alguna puntación en particular \n");
        sb3.append("3. Ver campeon\n");
        sb3.append("0. Salir");
        sb2.append(Colors.RESTORE);
        mensaje3 = sb3.toString();

        /*
        * Ciclo para hacer al menú concurrente
        */
        do {
            opcion = getInt(mensaje1,error,1,2);
            equipos = getInt("¿De cuantos equipos es tu torneo?",error,2,30000);

            //Futbol.
            if (opcion == 1){
                Futbol fut = new Futbol(equipos);

                //Da nombre a cada uno de los equipos.
                while(equipos >0){
                    Colors.println("Ingresa el nombre de algun equipo:", Colors.HIGH_INTENSITY);
                    nombre_equipo = scn.nextLine();
                    fut.SetEquipo(nombre_equipo);
                    equipos--;
                }
                //Menu de simulación.
                do{
                    opcion2 = getInt(mensaje2, error,0,2);
                    if(opcion2 == 0){
                        termina = true;
                    }else if(opcion2 == 1){
                        fut.Score();
                    }else if(opcion2 == 2){
                        fut.NuevaRonda();
                    }
                }while(!termina && !fut.Termina());

                //Menu de torneo finalizado.
                if(fut.Termina()){
                    do{
                        opcion3 = getInt(mensaje3,error,0,3);
                        if(opcion3 == 0){
                            termina = true;
                        }else if(opcion3 == 1){
                            fut.Score();
                        }else if(opcion3 == 2){
                            fut.ScoreEquipo();
                        }else if(opcion3 ==3){
                            fut.Ganador();
                        }
                    }while(!termina);
                }
            }
            //Basquet.
            else if (opcion ==2){
                Basquet bas = new Basquet(equipos);
                //Da nombre a los equipos.
                while(equipos >0){
                    Colors.println("Ingresa el nombre de algun equipo:", Colors.HIGH_INTENSITY);
                    nombre_equipo = scn.nextLine();
                    bas.SetEquipo(nombre_equipo);
                    equipos--;
                }
                //Menu de simulacion.
                do{
                    opcion2 = getInt(mensaje2, error,0,2);
                    if(opcion2 == 0){
                        termina = true;
                    }else if(opcion2 == 1){
                        bas.Score();
                    }else if(opcion2 == 2){
                        bas.NuevaRonda();
                    }
                }while(!termina && !bas.Termina());

                //Menu de torno finalizado.
                if(bas.Termina()){
                    do{
                        opcion3 = getInt(mensaje3,error,0,3);
                        if(opcion3 == 0){
                            termina = true;
                        }else if(opcion3 == 1){
                            bas.Score();
                        }else if(opcion3 == 2){
                            bas.ScoreEquipo();
                        }else if(opcion3 ==3){
                            bas.Ganador();
                        }
                    }while(!termina);
                }
            }
        } while (!termina);

    }
}
