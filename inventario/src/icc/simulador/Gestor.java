
package icc.simulador;

import java.util.Set;
import java.util.HashSet;
import icc.modelo.*;
import icc.exceptions.IllegalSizeException;
import icc.exceptions.RepeatedTitleExeception;
import icc.io.ReaderWriter;

public class Gestor {

    /**Tamaño máximo de artículos que se pueden guardar*/
    private static final int SIZE = 32;


    private String file_libros;
    private Libro col_libros[];
    private int index_libros;

    private String file_peliculas;
    private Pelicula col_peliculas[];
    private int index_peliculas;

    private String file_discos;
    private Disco col_discos[];
    private int index_discos;

    /**
    *Constructor de la clase, da nombre a los archivos que guardan los
    *articulos y manda a leer los articulos que ya se habían escrito.
    */
    public Gestor() {
        file_libros = "libros.csv";
        file_peliculas = "peliculas.csv";
        file_discos = "discos.csv";
        ReadLibros();
        ReadPeliculas();
        ReadDiscos();
    }

    /**
    * Método para leer los libros del documento. Inizializa la colección de libros
    * y agrega a la colleción todos los libros que se encuentren en el archivo.
    */
    private void ReadLibros() {
        String[] libros = ReaderWriter.read(file_libros,SIZE);
        String[] array;

        index_libros = 0;
        col_libros = new Libro[SIZE];

        while(index_libros < SIZE && libros[index_libros] != null){
            array = libros[index_libros].split(",");
            col_libros[index_libros++] = new Libro(array[0],array[1],array[2],array[3]);
        }
    }

    /**
    * Método para leer los discos del documento. Inizializa la colección de discos
    * y agrega a la colleción todos los discos que se encuentren en el archivo.
    */
    private void ReadDiscos() {
        String[] discos = ReaderWriter.read(file_discos,SIZE);
        String[] array;
        index_discos= 0;
        col_discos= new Disco[SIZE];
        while(index_discos < SIZE && discos[index_discos] != null){
            array = discos[index_discos].split(",");
            col_discos[index_discos++] = new Disco(array[0],array[1],array[2],array[3]);
        }
    }
    /**
    * Método para leer las peliculas del documento. Inizializa la colección de peliculas
    * y agrega a la colleción todas las peliculas que se encuentren en el archivo.
    */
    private void ReadPeliculas() {
        String[] peliculas = ReaderWriter.read(file_peliculas,SIZE);
        String[] array;
        index_peliculas= 0;
        col_peliculas= new Pelicula[SIZE];
        while(index_peliculas < SIZE && peliculas[index_peliculas] != null){
            array = peliculas[index_peliculas].split(",");
            col_peliculas[index_peliculas++] = new Pelicula(array[0],array[1],array[2],array[3]);
        }
    }

    /**
    *Método para imprimir a todos los libros con el mismo autor.
    *@param aux - el autor a buscar, se manda a traves del usuario por scanner.
    */
    public void autor(String aux){
        for(int i = 0; i< index_libros; i++){
            if(col_libros[i].autor(aux)){
                System.out.println(col_libros[i]);
            }
        }
    }
    /**
    *Método para imprimir a todos los libros con el mismo tema.
    *@param aux - el tema a buscar, se manda a traves del usuario por scanner.
    */
    public void tema(String aux){
        for(int i = 0; i< index_libros; i++){
            if(col_libros[i].tema(aux)){
                System.out.println(col_libros[i]);
            }
        }
    }
    /**
    *Método para imprimir a todos los libros con el mismo titulo.
    *@param aux - el titulo a buscar, se manda a traves del usuario por scanner.
    */
    public void titulo(String aux){
        for(int i = 0; i< index_libros; i++){
            if(col_libros[i].titulo(aux)){
                System.out.println(col_libros[i]);
            }
        }
    }
    /**
    *Método para imprimir a todos los discos con el mismo interprete.
    *@param aux - el tema a buscar, se manda a traves del usuario por scanner.
    */
    public void interprete(String aux){
        for(int i = 0; i< index_discos; i++){
            if(col_discos[i].interprete(aux)){
                System.out.println(col_discos[i]);
            }
        }
    }
    /**
    *Método para imprimir a todos los discos con el mismo genero.
    *@param aux - el genero a buscar, se manda a traves del usuario por scanner.
    */
    public void generoDisco(String aux){
        for(int i = 0; i< index_discos; i++){
            if(col_discos[i].genero(aux)){
                System.out.println(col_discos[i]);
            }
        }
    }

    /**
    *Método para imprimir a todos los discos con mas de 10 pistas.
    */
    public void pistas(){
        for(int i = 0; i< index_discos; i++){
            if(col_discos[i].pistas()){
                System.out.println(col_discos[i]);
            }
        }
    }
    /**
    *Método para imprimir a todas las peliculas con el mismo genero.
    *@param aux - el genero a buscar, se manda a traves del usuario por scanner.
    */
    public void generoPelicula(String aux){
        for(int i = 0; i< index_peliculas; i++){
            if(col_peliculas[i].genero(aux)){
                System.out.println(col_peliculas[i]);
            }
        }
    }
    /**
    *Método para imprimir a todas las peliculas con el mismo año posterior a este.
    *@param aux - el año a buscar, se manda a traves del usuario por scanner.
    */
    public void ano(String aux){
        for(int i = 0; i< index_peliculas; i++){
            if(col_peliculas[i].ano(aux)){
                System.out.println(col_peliculas[i]);
            }
        }
    }
    /**
    *Método para imprimir a todos los actores que aparecen mas de 2 veces en las
    *peliculas.
    */
    public void actores(){
        Set<String> actores_rep = new HashSet<String>();
        //Ingresa los actores repetidos a un conjunto.
        for(int i = 0; i< index_peliculas; i++){
            for(int j = i+1; j< index_peliculas; j++){
                if(col_peliculas[i].actor(col_peliculas[j].GetPersona())){
                    actores_rep.add(col_peliculas[j].GetPersona());
                }
            }
        }
        //Imprime el conjunto de actores repetidos
        System.out.println("Actores repetidos");
        for(String actor : actores_rep){
            System.out.println(actor);
        }
    }
    /**
    *Método para imprimir a todos los libros de la colección.
    */
    public void libros(){
        System.out.println();
        for(int i = 0; i< index_libros; i++){
            System.out.println(col_libros[i]);
        }
    }
    /**
    *Método para imprimir a todos los discos de la colección.
    */
    public void discos(){
        System.out.println();
        for(int i = 0; i< index_discos; i++){
            System.out.println(col_discos[i]);
        }
    }
    /**
    *Método para imprimir a todas las peliculas de la colección.
    */
    public void peliculas(){
        System.out.println();
        for(int i = 0; i< index_peliculas; i++){
            System.out.println(col_peliculas[i]);
        }
    }
    /**
    *Método para verificar que no se agreguen libros con titulo repetidos.
    *En caso contrario, se manda una excepción.
    *@param aux - <code>titulo</code> a verificar si existe en la colecion.
    *@throws RepeatedTitleExeception - Verifica que no se haya dos titulos repetidos
    */
    public void newLibro(String aux) throws RepeatedTitleExeception{
        for(int i=0; i < index_libros; i++){
            if (col_libros[i].titulo(aux)) {
                throw new RepeatedTitleExeception("Titulo repetido");
            }
        }
    }
    /**
    *Método para verificar que no se agreguen discos con titulo repetidos.
    *En caso contrario, se manda una excepción.
    *@param aux - <code>titulo</code> a verificar si existe en la colecion.
    *@throws RepeatedTitleExeception - Verifica que no se haya dos titulos repetidos
    */
    public void newDisco(String aux) throws RepeatedTitleExeception{
        for(int i=0; i < index_discos; i++){
            if (col_discos[i].titulo(aux)) {
                throw new RepeatedTitleExeception("Titulo repetido");
            }
        }
    }
    /**
    *Método para verificar que no se agreguen peliculas con titulo repetidos.
    *En caso contrario, se manda una excepción.
    *@param aux - <code>titulo</code> a verificar si existe en la colecion.
    *@throws RepeatedTitleExeception - Verifica que no se haya dos titulos repetidos
    */
    public void newPelicula(String aux) throws RepeatedTitleExeception{
        for(int i=0; i < index_peliculas; i++){
            if (col_peliculas[i].titulo(aux)) {
                throw new RepeatedTitleExeception("Titulo repetido");
            }
        }
    }
    /**
    *Método para agregar nuevos Libros a la coleccion y escribirlo en el archivo.
    *Verifica que no se sobrepase el limite de libros que se puede guardar.
    *@param aux - <code>Libro</code> que se va a agregar.
    *@throws IllegalSizeException - Verifica que no se sobrepase el limite de peliculas que se puede guardar.
    */
    public void addLibro(Libro aux) throws IllegalSizeException{
        if (index_libros == SIZE) throw new IllegalSizeException("Capacidad maxima alcanzada");
        col_libros[index_libros++] = aux;
        ReaderWriter.write(aux.LineaArchivo(),file_libros);
    }
    /**
    *Método para agregar nuevos Discos a la coleccion y escribirlo en el archivo.
    *Verifica que no se sobrepase el limite de discos que se puede guardar.
    *@param aux - <code>Disco</code> que se va a agregar.
    *@throws IllegalSizeException - Verifica que no se sobrepase el limite de peliculas que se puede guardar.
    */
    public void addDisco(Disco aux) throws IllegalSizeException{
        if (index_discos == SIZE) throw new IllegalSizeException("Capacidad maxima alcanzada");
        col_discos[index_discos++] = aux;
        ReaderWriter.write(aux.LineaArchivo(),file_discos);
    }
    /**
    *Método para agregar nuevas Peliculas a la coleccion y escribirla en el archivo.
    *@param aux - <code>Pelicula</code> que se va a agregar.
    *@throws IllegalSizeException - Verifica que no se sobrepase el limite de peliculas que se puede guardar.
    */
    public void addPelicula(Pelicula aux) throws IllegalSizeException{
        if (index_peliculas == SIZE) throw new IllegalSizeException("Capacidad maxima alcanzada");
        col_peliculas[index_peliculas++] = aux;
        ReaderWriter.write(aux.LineaArchivo(),file_peliculas);
    }



}
