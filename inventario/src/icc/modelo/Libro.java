package icc.modelo;

import icc.colors.Colors;
/**
 * Clase contruye y da los atributos de los libros.
 * @author Laura Rodríguez.
 * @version Enero 2022
 */
public class Libro extends Media{

    String autor,tema;
    /**
    * Método constructor de la clase. Cada elemento multimedia tiene titulo,
    * genero y 2 atributos que cambian dependiendo de la subclase.
    * @param titulo - Titulo
    * @param genero  - Genero
    * @param persona -  Autor
    * @param atributo - Tema
    */
    public Libro(String titulo, String genero, String persona, String atributo){
        super(titulo,genero,persona, atributo);
        tipo_persona = "Autor";
        tipo_atributo = "Tema";
        autor = persona;
        tema = atributo;
    }
    /**
    * Método que verifica que dos libros tengan el mismo autor.
    * @return boolean si dos autores son iguales es verdadero
    * @param aux - autor a verificar
    */
    public boolean autor(String aux) {
        return autor.equals(aux);
    }
    /**
    * Método que verifica que dos libros tengan el mismo tema.
    * @return boolean si dos temas son iguales es verdadero
    * @param aux - tema a verificar
    */
    public boolean tema(String aux) {
        return tema.equals(aux);
    }

}
