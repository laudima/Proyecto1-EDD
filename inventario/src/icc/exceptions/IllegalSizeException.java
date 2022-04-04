
package icc.exceptions;

/**
 * Clase para crear la excepción cuando se sobrepasa el tamaño máximo de articulos.
 * @author Laura Rodríguez.
 * @version Enero 2022
 */
public class IllegalSizeException extends Exception {

    /**
    * Constructor
    */
    public IllegalSizeException(){
        super();
    }
    /**
    * Constructor
    * @param message Información sobre el contexto en el que se invoca.
    */
    public IllegalSizeException(String message){
        super(message);
    }

}
