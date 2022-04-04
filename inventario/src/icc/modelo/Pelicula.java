package icc.modelo;

import icc.colors.Colors;
/**
 * Clase contruye y da los atributos de las peliculas.
 * @author Laura Rodríguez.
 * @version Enero 2022
 */
public class Pelicula extends Media{

    String actor;
    int ano;
    /**
    * Método constructor de la clase. Cada elemento multimedia tiene titulo,
    * genero y 2 atributos que cambian dependiendo de la subclase.
    * @param titulo - Titulo
    * @param genero  - Genero
    * @param persona - Actor
    * @param atributo - Año
    */
    public Pelicula(String titulo, String genero, String persona, String atributo){
        super(titulo,genero,persona, atributo);
        tipo_persona = "Actor/Actriz";
        tipo_atributo = "Año";
        actor = persona;
        ano = Integer.valueOf(atributo);
    }
    /**
    * Método que verifica que dos peliculas tengan el mismo genero.
    * @return boolean si dos generos son iguales es verdadero
    * @param aux - genero a verificar
    */
    public boolean genero(String aux) {
        return genero.equals(aux);
    }
    /**
    * Método que verifica una pelicula sea de un año o posterior a este.
    * @return boolean si el año es posterior o igual es verdadero
    * @param aux - año a verificar
    */
    public boolean ano(String aux) {
        int aux2;
        aux2 = Integer.valueOf(aux);
        return ano >= aux2;
    }
    /**
    * Método que verifica que dos peliculas tengan el mismo actor.
    * @return boolean si dos actores son iguales es verdadero
    * @param aux - actor a verificar
    */
    public boolean actor(String aux) {
        return actor.equals(aux);
    }


}
