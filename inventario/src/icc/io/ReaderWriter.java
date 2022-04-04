
package icc.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
/**
 * Clase para leer y escibir archivos.
 * @author Laura Rodríguez.
 * @version Enero 2022
 */
public class ReaderWriter {
    /**
    *Método para escribir en un archivo.
    *@param line - Linea que contiene los atributos de los articulos
    *@param fileName - nombre del archivo en el que se escribe
    */
    public static void write(String line,String fileName){
        FileWriter out = null;
        try{
            out = new FileWriter(fileName, true);
            out.write(line);
            out.write("\n");
        } catch(IOException e) {
            System.out.println(e);
        }finally{
            try {
                if (out != null) {
                    out.close();
                }
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
    /**
    *Método para leer un archivo.
    *@return String[] arreglo de cada linea del archivo
    *@param fileName - nombre del archivo en el que se escribe
    *@param size - tamaño del número máximo dde articulo qeu se podría leer
    */
    public static String[] read (String fileName, int size){
        BufferedReader in = null;
        String[] array = new String[size];

        try{
            in = new BufferedReader(new FileReader(fileName));
            for(int i=0; i<size; i++){
                array[i] = in.readLine();
                if(array[i] == null) break;
            }
        }catch(IOException e){
            System.out.println(e);
        }finally {
            try{
                if (in != null){
                    in.close();
                }
            }catch (IOException e){
                System.out.println(e);
            }
        }
        return array;
    }
}
