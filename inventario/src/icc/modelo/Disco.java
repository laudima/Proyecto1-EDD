package icc.modelo;

import icc.colors.Colors;
/**
 * Clase contruye y da los atributos de los discos.
 * @author Laura Rodríguez.
 * @version Enero 2022
 */
public class Disco extends Media{

    String interprete;
    int pistas;

    /**
    * Método constructor de la clase. Cada elemento multimedia tiene titulo,
    * genero y 2 atributos que cambian dependiendo de la subclase.
    * @param titulo - Titulo
    * @param genero  - Genero
    * @param persona - Interprete
    * @param atributo - Pistas
    */
    public Disco(String titulo, String genero, String persona, String atributo){
        super(titulo,genero,persona, atributo);
        tipo_persona = "Interprete";
        tipo_atributo = "Número de pistas";
        interprete = persona;
        pistas = Integer.valueOf(atributo);
    }
    /**
    * Método que verifica que dos discos tengan el mismo interprete.
    * @return boolean si dos interpretes son iguales es verdadero
    * @param aux - interprete a verificar
    */
    public boolean interprete(String aux) {
        return interprete.equals(aux);
    }
    /**
    * Método que verifica que dos discos tengan el mismo genero.
    * @return boolean si dos generos son iguales es verdadero
    * @param aux - genero a verificar
    */
    public boolean genero(String aux) {
        return genero.equals(aux);
    }
    /**
    * Método que verifica que un disco tenga mas de 10 pistas.
    * @return boolean si tiene mas de 10 pistas es verdadero.
    */
    public boolean pistas() {
        return pistas>10;
    }


}
