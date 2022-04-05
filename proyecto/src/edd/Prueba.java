
package edd;

import java.util.Scanner;
import edd.colors.Colors;
import edd.estructuras;
import edd.modelo.*;
import edd.simulador.*;

/**
 * Clase principal, incluye a la interfaz y llama a las clases de la gestión de
 * archivos para empezar el programa.
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

    /**
    *Método para ingresar los datos para crear un nuevo libro. Lanza las excepciones
    *para checar si no hay un título repetido o si no se excede el maximo nnúmero de
    *elementos.
    *@return Libro
    *@param g - Gestor paara poder invocar los métodos de la clase.
    *@throws IllegalSizeException - Verifica que no se sobrepase el limite de peliculas que se puede guardar.
    *@throws RepeatedTitleExeception - Verifica que no se haya dos titulos repetidos
    */
    public static Libro getLibro(Gestor g) throws RepeatedTitleExeception, IllegalSizeException{
        Scanner scn = new Scanner(System.in);
        String titulo,genero, autor, tema;
        System.out.println("Ingrese el titulo del libro");
        titulo = scn.next();
        g.newLibro(titulo);
        System.out.println("Ingrese el genero del libro");
        genero = scn.next();
        System.out.println("Ingrese el autor del libro");
        autor = scn.next();
        System.out.println("Ingrese el tema  del libro");
        tema = scn.next();

        return new Libro(titulo,genero,autor,tema);
    }
    /**
    *Método para ingresar los datos para crear un nuevo disco. Lanza las excepciones
    *para checar si no hay un título repetido o si no se excede el maximo nnúmero de
    *elementos.
    *@return Disco
    *@param g - Gestor paara poder invocar los métodos de la clase.
    *@throws IllegalSizeException - Verifica que no se sobrepase el limite de peliculas que se puede guardar.
    *@throws RepeatedTitleExeception - Verifica que no se haya dos titulos repetidos
    */
    public static Disco getDisco(Gestor g) throws RepeatedTitleExeception, IllegalSizeException{
        Scanner scn = new Scanner(System.in);
        String titulo,genero, interprete, pistas;
        System.out.println("Ingrese el titulo del disco");
        titulo = scn.next();
        g.newDisco(titulo);
        System.out.println("Ingrese el genero del disco");
        genero = scn.next();
        System.out.println("Ingrese el interprete del disco");
        interprete = scn.next();
        System.out.println("Ingrese el numero de pistas  del disco");
        pistas = scn.next();
        return new Disco(titulo,genero,interprete,pistas);
    }
    /**
    *Método para ingresar los datos para crear una nueva pelicula. Lanza las excepciones
    *para checar si no hay un título repetido o si no se excede el maximo nnúmero de
    *elementos.
    *@return Pelicula
    *@param g - Gestor paara poder invocar los métodos de la clase.
    *@throws IllegalSizeException - Verifica que no se sobrepase el limite de peliculas que se puede guardar.
    *@throws RepeatedTitleExeception - Verifica que no se haya dos titulos repetidos
    */
    public static Pelicula getPelicula(Gestor g) throws RepeatedTitleExeception, IllegalSizeException{
        Scanner scn = new Scanner(System.in);
        String titulo,genero, actor, ano;
        System.out.println("Ingrese el titulo del pelicula");
        titulo = scn.next();
        g.newPelicula(titulo);
        System.out.println("Ingrese el genero del pelicula");
        genero = scn.next();
        System.out.println("Ingrese el actor/actriz princial de la pelicula");
        actor = scn.next();
        System.out.println("Ingrese el año de la pelicula");
        ano = scn.next();
        return new Pelicula(titulo,genero,actor,ano);
    }
    public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);

        Gestor g;
        //Menus
        StringBuilder sb,sb2,sb3,sb4;
        String menu_inicio, menu_libro, menu_disco, menu_pelicula, error,aux;

        //Opciones del GetInt para cada menu
        int opcion1,opcion2;

        System.out.println("Bienvenido al gestor de artículos de entretenimiento");

        /*
        * Menú de inicio
        */
        sb = new StringBuilder();
        sb.append(Colors.HIGH_INTENSITY);
        sb.append("1. Gestionar Libros\n");
        sb.append("2. Gestionar Discos\n");
        sb.append("3. Gestionar Peliculas\n");
        sb.append("0.Salir");
        sb.append(Colors.RESTORE);
        menu_inicio = sb.toString();

        /*
        * Menú Libro
        */
        sb2 = new StringBuilder();
        sb2.append(Colors.HIGH_INTENSITY);
        sb2.append("\n1.Consultar por autor \n");
        sb2.append("2.Consultar por tema \n");
        sb2.append("3.Consultar por titulo \n");
        sb2.append("4.Consultar todos \n");
        sb2.append("5.Añadir Libro \n");
        sb2.append("0.Regresar");
        sb2.append(Colors.RESTORE);
        menu_libro = sb2.toString();

        /*
        * Menú Disco
        */
        sb3 = new StringBuilder();
        sb3.append(Colors.HIGH_INTENSITY);
        sb3.append(Colors.HIGH_INTENSITY);
        sb3.append("\n1.Consultar por interprete \n");
        sb3.append("2.Consultar por genero \n");
        sb3.append("3.Consultar por mas de 10 pistas \n");
        sb3.append("4.Consultar todos \n");
        sb3.append("5.Añadir Disco \n");
        sb3.append("0.Regresar");
        sb3.append(Colors.RESTORE);
        menu_disco = sb3.toString();

        /*
        * Menú Pelicula
        */
        sb4 = new StringBuilder();
        sb4.append(Colors.HIGH_INTENSITY);
        sb4.append(Colors.HIGH_INTENSITY);
        sb4.append("\n1.Consultar por año especifico o posterior \n");
        sb4.append("2.Consultar los actores/actrizes repetidos\n");
        sb4.append("3.Consultar por genero \n");
        sb4.append("4.Consultar todos \n");
        sb4.append("5.Añadir Pelicula \n");
        sb4.append("0.Regresar");
        sb4.append(Colors.RESTORE);
        menu_pelicula = sb4.toString();

        /*Mensaje de error*/
        error = "Por favor ingresa una opcion valida.";


        /*
        * Ciclo para hacer al menú concurrente
        */
        try{
            g = new Gestor();
            do {
            opcion1 = getInt(menu_inicio,error,0,3);
            switch(opcion1){
                case 1:
                    //libro
                    do{
                        opcion2 = getInt(menu_libro,error,0,5);
                        switch(opcion2){
                            case 1:
                                System.out.println("Ingrese el autor a buscar");
                                aux = scn.next();
                                g.autor(aux);
                                break;
                            case 2:
                                System.out.println("Ingrese el tema a buscar");
                                aux = scn.next();
                                g.tema(aux);
                                break;
                            case 3:
                                System.out.println("Ingrese el titulo a buscar");
                                aux = scn.next();
                                g.titulo(aux);
                                break;
                            case 4:
                                g.libros();
                                break;
                            case 5:
                                g.addLibro(getLibro(g));
                                break;
                        }
                    }while(opcion2 != 0);
                    break;
                case 2:
                    //disco
                    do{
                        opcion2 = getInt(menu_disco,error,0,5);
                        switch(opcion2){
                            case 1:
                            System.out.println("Ingrese el interprete a buscar");
                            aux = scn.next();
                            g.interprete(aux);
                            break;
                            case 2:
                            System.out.println("Ingrese el genero a buscar");
                            aux = scn.next();
                            g.generoDisco(aux);
                            break;
                            case 3:
                            g.pistas();
                            break;
                            case 4:
                            g.discos();
                            break;
                            case 5:
                            g.addDisco(getDisco(g));
                            break;
                        }
                    }while(opcion2 != 0);
                    break;
                case 3:
                    //pelicula
                    do{
                        opcion2 = getInt(menu_pelicula,error,0,5);
                        switch(opcion2){
                            case 1:
                            System.out.println("Ingrese el año a buscar");
                            aux = scn.next();
                            g.ano(aux);
                            break;
                            case 2:
                            g.actores();
                            break;
                            case 3:
                            System.out.println("Ingrese el genero a buscar");
                            aux = scn.next();
                            g.generoPelicula(aux);
                            break;
                            case 4:
                            g.peliculas();
                            break;
                            case 5:
                            g.addPelicula(getPelicula(g));
                            break;
                        }
                    }while(opcion2 !=0);
                    break;
            }
        } while (opcion1 != 0);
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
