package icc.Simulador;
import java.util.Random;
import java.util.Scanner;
import icc.Modelo.Equipos;
import icc.Modelo.EquipoBasquet;
import icc.colors.Colors;

/**
 * Clase que simula el compartamiento de un torneo de basquetbol.
 *
 * @author Laura Rodriguez
 */

public class Basquet extends Torneo{

    protected int id;

    protected EquipoBasquet[] equiposbas;

    protected int canasta1,canasta2,tiro1,tiro2,libre1,libre2;

    protected int opcion;

    protected int ganador=0, ganado, empatados;

    Random random = new Random();

    /**
    * Método constructor de la clase. Inicializa el arreglo del tamaño de equipos
    * de basquet que se le pasa como parametro.
    * @param  e  - Número de equipos.
    */
    public Basquet(int e){
        super(e);
        equiposbas = new EquipoBasquet[e];
    }

    /**
    * Método que le da nombre y id a cada equipo.
    * @param nombre - Nombre del equipo.
    */
    public void SetEquipo(String nombre){
        equiposbas[id] = new EquipoBasquet(id,nombre);
        id++;
    }

    /**
    * Método para imprimer el Score de todos los equipos.
    */
    public void Score(){
        Colors.println("Resumen de la ronda " + r + " :", Colors.HIGH_INTENSITY);
        for(int i=0; i<e; i++){
            System.out.println(equiposbas[i]);
        }
    }

    /**
    * Método para imprimir el Score de un equipo en particular.
    */
    public void ScoreEquipo(){
        Colors.println("Elige el equipo a consultar", Colors.HIGH_INTENSITY);
        for(int i=0; i<e; i++){
            System.out.println(i + ". " + equiposbas[i].daNombre());
        }
        opcion = getInt("Escribe el ID del equipo", "Opcion invalida",0,e);
        System.out.println(equiposbas[opcion]);
    }

    /**
    * Método para inciar una ronda,  para la ultima ronda si es un número impar de equipos
    * se emprime el equipo que descansa. Si el npumero de equipos es par juega el equipo r
    * con el último equipo.
    */
    public void NuevaRonda(){
        for(k = 1; k <= partidos; k++){

            Juego(equiposbas[(r+k)%n], equiposbas[(r+n-k)%n]);
            System.out.println("--------------------------");
            System.out.println(Colors.HIGH_INTENSITY + equiposbas[(r+k)%n].daNombre() + " vs " + equiposbas[(r+n-k)%n].daNombre() + Colors.RESTORE);
            System.out.println(Colors.BLUE +"Canasta: " + Colors.RESTORE + canasta1 + " vs " + canasta2);
            System.out.println(Colors.BLUE +"Tiro de 3: " + Colors.RESTORE + tiro1 + " vs " + tiro2);
            System.out.println(Colors.BLUE + "Libre: " + Colors.RESTORE + libre1 + " vs " + libre2);
            System.out.println("--------------------------\n");
        }

        if(e%2 == 1){
            System.out.println("Equipo " + equiposbas[r].daNombre() + "("+ r + ")" + " descansa");
        }else if(e%2 == 0){

            Juego(equiposbas[r],equiposbas[e-1]);
            System.out.println("--------------------------");
            System.out.println(Colors.HIGH_INTENSITY + equiposbas[r].daNombre() + " vs " + equiposbas[n].daNombre() + Colors.RESTORE);
            System.out.println(Colors.BLUE +"Canasta: " + Colors.RESTORE + canasta1 + " vs " + canasta2);
            System.out.println(Colors.BLUE +"Tiro de 3: " + Colors.RESTORE + tiro1 + " vs " + tiro2);
            System.out.println(Colors.BLUE +"Libre: "+ Colors.RESTORE +libre1 + " vs " + libre2);
            System.out.println("--------------------------\n");
        }
        r++;

        if( r >= n){
            System.out.println("\nEl torneo termino, los puntajes quedaron asi: ");
            Score();
            termina = true;
        }
    }

    /**
    * Método que simula un partido de fútbol entre dos equipos, genera los goles
    * de manera pseudoaleatoria en un rango de [0,10].
    * @param  equipo1 - Equipo 1 a jugar
    * @param  equipo2 - Equipo 2 a jugar
    */
    public void Juego(EquipoBasquet equipo1, EquipoBasquet equipo2){

        canasta1 = random.nextInt(31);
        canasta2 = random.nextInt(31);
        tiro1 = random.nextInt(21);
        tiro2 = random.nextInt(21);
        libre1 = random.nextInt(16);
        libre2 = random.nextInt(16);

        //Actualiza los valores de las variables en la clase equipos.
        equipo1.CanastaFavor(canasta1);
        equipo1.CanastaEncontra(canasta2);
        equipo1.TiroFavor(tiro1);
        equipo1.TiroEncontra(tiro2);
        equipo1.LibreFavor(libre1);
        equipo1.LibreEncontra(libre2);
        equipo1.TotalFavor();
        equipo1.TotalEncontra();
        equipo1.TotalDiff();

        equipo2.CanastaFavor(canasta1);
        equipo2.CanastaEncontra(canasta2);
        equipo2.TiroFavor(tiro1);
        equipo2.TiroEncontra(tiro2);
        equipo2.LibreFavor(libre1);
        equipo2.LibreEncontra(libre2);
        equipo2.TotalFavor();
        equipo2.TotalEncontra();
        equipo2.TotalDiff();

        if(equipo1.GetTotalFavor() > equipo2.GetTotalEncontra()){
            equipo1.gano();
            equipo2.perdio();
        }else if(equipo1.GetTotalFavor() > equipo2.GetTotalEncontra()){
            equipo1.empato();
            equipo2.empato();
        }else{
            equipo1.perdio();
            equipo2.gano();
        }
    }

    /**
    * Método para indicar al ganador del Torneo. Bajo los siguientes criterios.
    * 1. Juegos Ganados
    * 2. Diferencia entre anotaciones a favor y encontra.
    * 3. Anotaciones a favor
    * 4. Gana el equipo1 por un "volado".
    */
    public void Ganador(){

        //Encuentra el equipo con más partidos ganados y con el mayor ID.
        for(int j=0; j<n;j++){
            for(int i=0; i<n;i++){
                if(equiposbas[i].GetGanados() > equiposbas[j].GetGanados()){
                    ganador = i;
                    ganado = 1;
                }
            }
        }

        for(int i=0; i<n; i++){
            //Si dos equipos tienen el mismo número de partidos ganados y
            //de diferencia de anotaciones se pasa el criterio 3. En caso de
            //que haya un empate se pasa al cirterio 4.
            if(equiposbas[ganador].GetGanados() == equiposbas[i].GetGanados() && equiposbas[i].GetTotalDiff() == equiposbas[ganador].GetTotalDiff() && ganador!= i){
                System.out.println("Hubo un empate en partidos ganados y diferencia de puntos");
                if(equiposbas[i].GetTotalFavor() > equiposbas[ganador].GetTotalFavor()){
                    ganador = i;
                    ganado = 3;
                }else if(equiposbas[ganador].GetTotalFavor() > equiposbas[i].GetTotalFavor()){
                    ganado = 3;
                }else if(equiposbas[ganador].GetTotalFavor() == equiposbas[i].GetTotalFavor() && ganador!= i){
                    ganado=4;
                }
            //Aqui se verifica el criterio 2.
            }else if(equiposbas[ganador].GetGanados() == equiposbas[i].GetGanados() && equiposbas[i].GetTotalDiff() > equiposbas[ganador].GetTotalDiff()){
                ganador = i;
                ganado = 2;
            }else if(equiposbas[ganador].GetGanados() == equiposbas[i].GetGanados() && equiposbas[ganador].GetTotalDiff() > equiposbas[i].GetTotalDiff()){
                ganado = 2;
            }
        }
        //Imprime al equipo ganador dependiendo del criterio por el que haya ganado.
        System.out.print("El ganador es el equipo " + Colors.RED + equiposbas[ganador].daNombre() + Colors.RESTORE);
        switch(ganado){
            case 1:
                System.out.println(Colors.HIGH_INTENSITY + " por partidos ganados" + Colors.RESTORE);
                break;
            case 2:
                System.out.println(Colors.HIGH_INTENSITY + " por diferencia de puntos"+ Colors.RESTORE);
                break;
            case 3:
                System.out.println(Colors.HIGH_INTENSITY + " por total de puntos a favor"+ Colors.RESTORE);
                break;
            case 4:
                System.out.println(Colors.HIGH_INTENSITY + " por volado ya que estaba empatado con otro equipo"+ Colors.RESTORE);
                break;
        }
    }
}
