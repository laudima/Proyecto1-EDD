package edd.simulador;

import edd.estructuras.*;
import java.lang.Math.*;
import edd.colors.Colors;
import edd.modelo.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.plaf.synth.SynthStyleFactory; // BUeno se intentó :(


public class Ronda{

    protected Jugadores[] jugadores;
    protected Cartas[] baraja;
    protected int barajeador;
    protected int empieza;

    //protected Cartas palo_triunfo;
    //protected Cartas palo_líder;

    protected Lista<Cartas> baraja_mezcla;

    protected int ronda_actual;
    protected int truco_actual;
    protected int rondas;
    protected int trucos;
    protected int total_jugadores;

    Random random = new Random();

    public Ronda(int n){

        total_jugadores = n;
        ronda_actual = 9;
        truco_actual = 0;

        SetJugadores();
        SetBaraja();
        trucos = ronda_actual;
        //Se ponen el número total de rondas dependiendo del número de jugadores
        switch (total_jugadores){
            case 3: rondas = 20; break;
            case 4: rondas = 15; break;
            case 5: rondas = 12; break;
            case 6: rondas = 10; break;
        }

        EmpiezaTurno();
    }

    public void SetJugadores(){
        //Se inicializan los jugadores
        jugadores = new Jugadores[total_jugadores];
        for(int i=0; i< total_jugadores; i++){
            jugadores[i] = new Jugadores(i);
        }
    }

    public void SetBaraja(){
        baraja = new Cartas[60];
        for(int i=0; i<13;i++){
            baraja[i] = new Cartas(i+1,'R');
        }
        for(int i=13; i<26;i++){
            baraja[i] = new Cartas((i%13)+1,'G');
        }
        for(int i=26; i<39;i++){
            baraja[i] = new Cartas((i%13)+1,'P');
        }
        for(int i=39; i<52;i++){
            baraja[i] = new Cartas((i%13)+1,'A');
        }
        for(int i=52; i<56; i++){
            baraja[i] = new Cartas(0,'W');
        }
        for(int i=56; i<60; i++){
            baraja[i] = new Cartas(0,'B');
        }
    }

    public void SetManos(Lista<Cartas> baraja){
        Lista<Cartas> mano = new Lista<Cartas>();
        int index;
        for(int j=0; j<total_jugadores; j++){
            for(int i=0; i<=trucos;i++){
                index = random.nextInt(baraja.size());
                mano.add(baraja.get(index));
                baraja.delete(baraja.get(index));
            }
            System.out.println(mano);
            System.out.println("---------------");
            jugadores[j].SetMano(mano);
            mano.empty();
        }

    }
    public void EmpiezaTurno(){

        Lista<Cartas> baraja_mezcla = new Lista<Cartas>();
        String triunfo_actual;

        empieza = ronda_actual%total_jugadores;
        barajeador = (ronda_actual%total_jugadores)+1;
        jugadores[empieza].set_Turno(0);

        for(int i =0; i<total_jugadores;i++){
            jugadores[(empieza+i)%total_jugadores].set_Turno(i);
        }

        Colors.println("Ronda " + (ronda_actual+1), Colors.HIGH_INTENSITY);

        Colors.println("El barajeador es el jugador " + barajeador, Colors.HIGH_INTENSITY);
        Colors.println("Se barajean las cartas...",Colors.HIGH_INTENSITY);

        baraja_mezcla = MezclaBaraja();
        SetManos(baraja_mezcla);

        Colors.println("Ahora se tomará una carta extra para determinar el palo del triunfo.", Colors.HIGH_INTENSITY);
        triunfo_actual = PaloTriunfo(baraja_mezcla);



        Colors.println("Empieza el jugador " + empieza, Colors.HIGH_INTENSITY);
        Colors.println("Aquí estan tus cartas: ", Colors.HIGH_INTENSITY);
        jugadores[empieza].PrintMano();
        
        
        //Para más al ratón vaquero:
        //System.out.println();
        //Colors.println("Jugador " + empieza +" por favor selecciona la carta con la que jugarás...", Colors.HIGH_INTENSITY);
        //System.out.println();
        //TiraJugador(empieza);

    }

    public void TiraJugador(int index_jugador){
        String mensaje="Buena elección...Supongo.";
        String error="No seas tramposx! Elige una carta válida o muere en el castigo del loop infinito!";
        int min=1, max = jugadores[index_jugador].tamano_mano();

        int eleccion = getInt(mensaje, error, min, max);
    }

    public Lista<Cartas> MezclaBaraja(){
        Lista<Cartas> baraja_mezcla = new Lista<Cartas>();
        baraja_mezcla.empty();
        for(int i=0; i<60; i++){
            baraja_mezcla.add(baraja[i]);
        }
        return baraja_mezcla;
    }

    public String PaloTriunfo(Lista<Cartas> baraja){
        String triunfo;
        if(!baraja.isEmpty()){
            int index = random.nextInt(baraja.size());
            System.out.println(index); //BORRAR
            triunfo = baraja.get(index).toString_palo();
            //Colors.println("El palo del triunfo para este truco será: " + triunfo, Colors.HIGH_INTENSITY);
            System.out.println("");
            baraja.delete(baraja.get(index));
            System.out.println(baraja.size());
            switch (triunfo){
                case "w": //Me salió mago el wey
                Colors.println("Barajeador (vaya nombre tan desafortunado), la suerte está de tu lado, por favor escribe qué palo deseas que sea el palo del triunfo...", Colors.HIGH_INTENSITY);
                //set_palo(); TODAVÍA NO IMPLEMENTO ESTA FUNCIÓN LOL
                break;
                case "B": //El bromas
                Colors.println("Bufón: Lo siento, durante esta ronda no habrá palo del triunfo...", Colors.HIGH_INTENSITY);
                break;
                case "R":
                Colors.println("El palo del triunfo es R... De Rosalía", Colors.HIGH_INTENSITY);
                break;
                case "G":
                Colors.println("El palo del triunfo es G... De GUAPA", Colors.HIGH_INTENSITY);
                break;
                case "P":
                Colors.println("El palo del triunfo es P... De Patrona", Colors.HIGH_INTENSITY);
                break;
            }
        }else{
            Colors.println("Se han terminado las cartas así que no habrá palo del triunfo", Colors.HIGH_INTENSITY);
            triunfo = null;
        }
        
        return triunfo;
    }

    public static int getInt(String mensaje, String error, int min, int max) {
        int val;
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println(mensaje);
            if (scn.hasNextInt()) {
                val = scn.nextInt();
                // (-infinito, min) || (max, infinito)
                if (val < min || max < val) {
                    System.out.println(error);
                } else {
                    return val;
                }
            } else {
                scn.next();
                System.out.println(error);
            }
        }
    }



}
