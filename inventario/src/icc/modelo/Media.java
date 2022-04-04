package icc.modelo;

import icc.colors.Colors;
/**
 * Clase abstracta que construye a los elementos de multi-media del programa.
 *
 * @author Laura Rodríguez.
 */
public abstract class Media{


    protected String titulo,genero,persona,atributo;
    protected String tipo_persona, tipo_atributo;
    /**
    * Método constructor de la clase. Cada elemento multimedia tiene titulo,
    * genero y 2 atributos que cambian dependiendo de la subclase.
    * @param titulo - Titulo
    * @param genero  - Genero
    * @param persona - {Autor,Interprete,Actor}
    * @param atributo - {Tema, pistas, año}
    */
    protected Media(String titulo, String genero, String persona, String atributo){
        this.titulo = titulo;
        this.genero = genero;
        this.persona = persona;
        this. atributo = atributo;
        tipo_persona = "Persona";
        tipo_atributo = "atributo";
    }

    /**
    * Método que devuleve el nombre del Titulo
    * @return String titulo
    */
    public String GetTitulo(){
        return titulo;
    }
    /**
    * Método que devuleve el nombre del Genero
    * @return String genero
    */
    public String GetGenero(){
        return genero;
    }
    /**
    * Método que devuleve el nombre de la Persona(Autor, Interprete,Actor)
    * @return String persona
    */
    public String GetPersona(){
        return persona;
    }
    /**
    * Método que devuleve el atributo de cada media {tema, #pistas, año}
    * @return String atributo
    */
    public String GetAtributo(){
        return atributo;
    }
    /**
    * Método que devuleve la linea de Información de cada articulo para escribirlo
    * en el archivo.
    * @return String linea del archivo
    */
    public String LineaArchivo(){
        return titulo + "," + genero + "," + persona + "," + atributo;
    }
    /**
    * Método que verifica que dos articulos tengan el mismo titulo.
    * @return boolean si dos titulos son iguales es verdadero
    * @param aux - titulo a verificar
    */
    public boolean titulo(String aux) {
        return titulo.equals(aux);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(Colors.HIGH_INTENSITY + "\t Titulo: " +Colors.RESTORE + titulo + "\n");
        sb.append(Colors.HIGH_INTENSITY + "\t Genero: " +Colors.RESTORE  + genero + "\n");
        sb.append(Colors.HIGH_INTENSITY + "\t " + tipo_persona + ": " +Colors.RESTORE  + ": " + persona + "\n");
        sb.append(Colors.HIGH_INTENSITY + "\t " + tipo_atributo + ": " +Colors.RESTORE + atributo + "\n");
        sb.append(Colors.RESTORE);
        return sb.toString();
    }
}
