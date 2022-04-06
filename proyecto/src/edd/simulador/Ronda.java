package edd.simulador;

import edd.estructuras.*;
import java.lang.Math.*;
import edd.colors.Colors;
import edd.modelo.*;
import java.util.Random;
import java.util.Scanner;


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
        ronda_actual = 6;
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
            baraja[i] = new Cartas((i%13)+1,'B');
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

    public void SetManos(Lista<Cartas> baraja_mezcla){
        Lista<Cartas> mano = new Lista<Cartas>();
        int index;
        for(int j=0; j<total_jugadores; j++){
            for(int i=0; i<=trucos;i++){
                index = random.nextInt(baraja_mezcla.size());
                mano.add(baraja_mezcla.get(index));
                baraja_mezcla.delete(baraja[index]);
            }
            System.out.println(mano);
            System.out.println("---------------");
            jugadores[j].SetMano(mano);
            mano.empty();
        }

    }
    public void EmpiezaTurno(){

        Lista<Cartas> baraja_mezcla = new Lista<Cartas>();

        empieza = ronda_actual%total_jugadores;
        barajeador = (ronda_actual%total_jugadores)+1;

        Colors.println("Ronda " + (ronda_actual+1), Colors.HIGH_INTENSITY);

        Colors.println("El barajeador es el jugador " + barajeador, Colors.HIGH_INTENSITY);
        Colors.println("Se barajean las cartas...",Colors.HIGH_INTENSITY);

        baraja_mezcla = MezclaBaraja();
        SetManos(baraja_mezcla);

        Colors.println("Empieza el jugador " + empieza, Colors.HIGH_INTENSITY);
        Colors.println("Aquí estan tus cartas: ", Colors.HIGH_INTENSITY);

        jugadores[empieza].PrintMano();

        // TiraJugador(jugadores[empieza].)

    }

    // public void TiraJugador(Jugador jugador){
    //     String menu, error;
    //     int opcion;
    //
    //     /*Mensaje del menú*/
    //     menu = "Escoge la posicion de la carta que quieres tirar: ";
    //     error = "Por favor ingresa una opcion valida.";
    //
    //     opcion = getInt(menu,error,0,(jugador.GetMano()).size());
    // }

    public Lista<Cartas> MezclaBaraja(){
        Lista<Cartas> baraja_mezcla = new Lista<Cartas>();
        baraja_mezcla.empty();
        for(int i=0; i<60; i++){
            baraja_mezcla.add(baraja[i]);
        }
        return baraja_mezcla;
    }

    public void PaloTriunfo(){

    }
    public void Barajear(){

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
