package icc.Simulador;
import java.util.Random;
import java.util.Scanner;
import icc.Modelo.Equipos;
import icc.Modelo.EquipoFutbol;
import icc.colors.Colors;

/**
 * Clase que simula el compartamiento de un torneo de futbol.
 *
 * @author Laura Rodriguez
 */
public class Futbol extends Torneo{

    protected int id;

    protected EquipoFutbol[] equiposfut;

    protected int goles1,goles2;

    //opción para consultar el score de un equipo en particular.
    protected int opcion;

    protected int ganador, ganado, empatados;

    Random random = new Random();

    /**
    * Método constructor de la clase. Inicializa el arreglo del tamaño de equipos
    * de fútbol que se le pasa como prametro.
    * @param e - Número de equipos.
    */
    public Futbol(int e){
        super(e);
        equiposfut = new EquipoFutbol[e];
    }

    /**
    * Método que le da nombre y id a cada equipo.
    * @param nombre - Nombre del equipo.
    */
    public void SetEquipo(String nombre){
        equiposfut[id] = new EquipoFutbol(id,nombre);
        id++;
    }

    /**
    * Método para imprimer el Score de todos los equipos.
    */
    public void Score(){
        Colors.println("Resumen de la ronda " + r + " :", Colors.HIGH_INTENSITY);
        for(int i=0; i<e; i++){
            System.out.println(equiposfut[i]);
        }
    }

    /**
    * Método para imprimir el Score de un equipo en particular.
    */
    public void ScoreEquipo(){
        Colors.println("Elige el equipo a consultar", Colors.HIGH_INTENSITY);
        for(int i=0; i<e; i++){
            System.out.println(i + ". " + equiposfut[i].daNombre());
        }
        opcion = getInt("Escribe el ID del equipo", "Opcion invalida",0,n);
        System.out.println(equiposfut[opcion]);
    }

    /**
    * Método para inciar una ronda,  para la ultima ronda si es un número impar de equipos
    * se emprime el equipo que descansa. Si el npumero de equipos es par juega el equipo r
    * con el último equipo.
    */
    public void NuevaRonda(){
        for(k = 1; k <= partidos; k++){

            Juego(equiposfut[(r+k)%n], equiposfut[(r+n-k)%n]);
            System.out.println("--------------------------");
            System.out.println(equiposfut[(r+k)%n].daNombre() + " vs " + equiposfut[(r+n-k)%n].daNombre());
            System.out.println(Colors.BLUE +"Gol: " + Colors.RESTORE + goles1 + " vs " + goles2);
            System.out.println("--------------------------\n");
        }

        if(e%2 == 1){
            System.out.println("Equipo " + equiposfut[r].daNombre()
            + "("+ r + ")" + " descansa");
        }else if(e%2 == 0){

            System.out.println("--------------------------");
            Juego(equiposfut[r],equiposfut[e-1]);
            System.out.println(equiposfut[r].daNombre() + " vs " + equiposfut[n].daNombre());
            System.out.println(Colors.BLUE +"Gol: " + Colors.RESTORE + goles1 + " vs " + goles2);
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
    public void Juego(EquipoFutbol equipo1, EquipoFutbol equipo2){

        goles1 = random.nextInt(11);
        goles2 = random.nextInt(11);

        //Actualiza los valores de las variables en la clase equipos.
        equipo1.GolFavor(goles1);
        equipo1.GolContra(goles2);
        equipo1.TotalFavor();
        equipo1.TotalEncontra();
        equipo1.TotalDiff();

        equipo2.GolFavor(goles2);
        equipo2.GolContra(goles1);
        equipo2.TotalFavor();
        equipo2.TotalEncontra();
        equipo2.TotalDiff();


        if(goles1 > goles2){
            equipo1.gano();
            equipo2.perdio();
        }else if( goles1 == goles2){
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

        //Encuentra el primer equipo con más partidos ganados.
        for(int j=0; j<n;j++){
            for(int i=0; i<n;i++){
                if(equiposfut[i].GetGanados() > equiposfut[j].GetGanados()){
                    ganador = i;
                    ganado = 1;
                }
            }
        }

        for(int i=0; i<n; i++){
            //Si dos equipos tienen el mismo número de partidos ganados y
            //de diferencia de anotaciones se pasa el criterio 3. En caso de
            //que haya un empate se pasa al cirterio 4.
            if(equiposfut[ganador].GetGanados() == equiposfut[i].GetGanados() && equiposfut[i].GetTotalDiff() == equiposfut[ganador].GetTotalDiff() && ganador!= i){
                System.out.println("Hubo un empate en partidos ganados y diferencia de puntos");
                if(equiposfut[i].GetTotalFavor() > equiposfut[ganador].GetTotalFavor()){
                    ganador = i;
                    ganado = 3;
                }else if(equiposfut[ganador].GetTotalFavor() > equiposfut[i].GetTotalFavor()){
                    ganado = 3;
                }else if(equiposfut[ganador].GetTotalFavor() == equiposfut[i].GetTotalFavor() && ganador!= i){
                    ganado=4;
                }
            //Aqui se verifica el criterio 2.
            }else if(equiposfut[ganador].GetGanados() == equiposfut[i].GetGanados() && equiposfut[i].GetTotalDiff() > equiposfut[ganador].GetTotalDiff()){
                ganador = i;
                ganado = 2;
            }else if(equiposfut[ganador].GetGanados() == equiposfut[i].GetGanados() && equiposfut[ganador].GetTotalDiff() > equiposfut[i].GetTotalDiff()){
                ganado = 2;
            }
        }

        //Imprime al equipo ganador dependiendo del criterio por el que haya ganado.
        System.out.print("El ganador es el equipo " + Colors.RED + equiposfut[ganador].daNombre() + Colors.RESTORE);
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
