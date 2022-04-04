
package icc.exceptions;

/**
 * Clase para crear la excepción cuando se repiten titulos.
 * @author Laura Rodríguez.
 * @version Enero 2022
 */
public class RepeatedTitleExeception extends Exception {
    /**
    * Constructor
    */
    public RepeatedTitleExeception(){
        super();
    }
    /**
    * Contructor
    * @param message Información sobre el contexto en el que se invoca.
    */
    public RepeatedTitleExeception(String message){
        super(message);
    }
}
