package edd.simulador;

import edd.estructuras.*;
import java.lang.Math.*;
import edd.colors.Colors;
import edd.modelo.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.plaf.synth.SynthStyleFactory; // BUeno se intentó :(


public class Wizard{

    protected Jugadores[] jugadores; //jugadores de la partida
    protected Cartas[] baraja; //tiene todas las cartas posibles
    protected int barajeador; // jugador que le toca barajear
    protected int empieza;  //jugador que empiza un turno

    protected String triunfo;//palo del triunfo
    protected String lider;  //palo lider

    protected Lista<Cartas> baraja_mezcla; // lista de cartas barajeadas

    /*Lista de cartas que se jugaron en un turno*/
    protected Lista<Cartas> juego = new Lista<Cartas>();

    protected int ronda_actual;
    protected int truco_actual;
    protected int rondas; //total de rondas
    protected int trucos; //total de trucos por ronda
    protected int total_jugadores;

    Random random = new Random();

    /**
    * Método constructor de la clase.
    * Inicializa algunas variables para comenzar el juego.
    * Inicializa a los jugadores
    * Mete todos los tipos de cartas a las barajas
    * Empieza las rondas de cada partida y al final se despide y da al ganador.
    * @param n - indica el número de jugadores en el juego
    */
    public Wizard(int n){

        total_jugadores = n;
        ronda_actual = 0;
        truco_actual = 0;
        triunfo = "";
        lider = "";
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

        for(ronda_actual=0;ronda_actual<rondas; ronda_actual++){
            EmpiezaRonda();
            trucos++;
        }
        Despedida();
    }

    /**
    * Método para Inicializar a los jugadores.
    * Jugador 0, Jugador 1, Jugador 2, ...
    */
    public void SetJugadores(){
        //Se inicializan los jugadores
        jugadores = new Jugadores[total_jugadores];
        for(int i=0; i< total_jugadores; i++){
            jugadores[i] = new Jugadores(i);
        }
    }

    /**
    * Método para Inicializar a la baraja .
    * 13 cartas de palo R (Rojo)
    * 13 cartas de palo G (Verde)
    * 13 cartas de palo P (Azul)
    * 13 cartas de palo A (Amarillo)
    * 4 Magos y 4 Bufones
    */
    public void SetBaraja(){
        baraja = new Cartas[60];
        for(int i=0; i<13;i++){
            baraja[i] = new Cartas(i+1,"R");
        }
        for(int i=13; i<26;i++){
            baraja[i] = new Cartas((i%13)+1,"G");
        }
        for(int i=26; i<39;i++){
            baraja[i] = new Cartas((i%13)+1,"P");
        }
        for(int i=39; i<52;i++){
            baraja[i] = new Cartas((i%13)+1,"A");
        }
        for(int i=52; i<56; i++){
            baraja[i] = new Cartas(0,"W");
        }
        for(int i=56; i<60; i++){
            baraja[i] = new Cartas(0,"B");
        }
    }

    /**
    * Método para Inicializar la mano de juego de los jugadores en cada ronda.
    * EL número de cartas que se les da es igual al número de la ronda
    * Imprime como se repartieron las manos.
    * @param baraja - mano de juego para cada jugador en ronda nueva
    */
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
        Colors.println("Quedan " + baraja.size() + " cartas", Colors.HIGH_INTENSITY);
    }

    /**
    * Mete todas las cartas de nuestro arreglo de baraja a la lista de cartas.
    * @return Lista<Cartas> - lista de cartas para barajear
    */
    public Lista<Cartas> MezclaBaraja(){
        Lista<Cartas> baraja_mezcla = new Lista<Cartas>();
        baraja_mezcla.empty();
        for(int i=0; i<60; i++){
            baraja_mezcla.add(baraja[i]);
        }
        return baraja_mezcla;
    }

    /**
    * Asigna el palo de triunfo sacando una letra random de nuestra lista.
    * Si salio un Mago el barajeador escoge el palo.
    * @return String - palo de triunfo
    * @param Lista<Cartas> - baraja de cartas
    */
    public String PaloTriunfo(Lista<Cartas> baraja){
        if(!baraja.isEmpty()){
            int index = random.nextInt(baraja.size());
            triunfo = baraja.get(index).GetPalo();
            Colors.println("El palo del triunfo para esta ronda será: " + triunfo, Colors.HIGH_INTENSITY);
            System.out.println("");
            baraja.delete(baraja.get(index));
            switch (triunfo){
                case "W": //Me salió mago el wey
                Colors.println("Barajeador (vaya nombre tan desafortunado), la suerte está de tu lado, por favor escribe qué palo deseas que sea el palo del triunfo...", Colors.HIGH_INTENSITY);
                triunfo = SetPaloTriunfo();
                break;
                case "B": //El bromas
                Colors.println("Bufón: Lo siento, durante esta ronda no habrá palo del triunfo...", Colors.HIGH_INTENSITY);
                break;
                case "R": //R de rapido
                Colors.println("El palo del triunfo es R... De Rosalía", Colors.RED + Colors.HIGH_INTENSITY);
                System.out.println();
                break;
                case "G":
                Colors.println("El palo del triunfo es G... De GUAPA", Colors.GREEN + Colors.HIGH_INTENSITY);
                System.out.println();
                break;
                case "P":
                Colors.println("El palo del triunfo es P... De Patrona", Colors.BLUE + Colors.HIGH_INTENSITY);
                System.out.println();
                break;
                case "A":
                Colors.println("El palo del triunfo es A... De Altura", Colors.YELLOW + Colors.HIGH_INTENSITY);
                System.out.println();
                break;
            }
        }else{
            Colors.println("Se han terminado las cartas así que no habrá palo del triunfo", Colors.HIGH_INTENSITY);
            triunfo = null;
        }
        return triunfo;
    }

    /**
    * Salio un Mago el barajeador escoge el palo.
    *
    * @return String - palo de triunfo
    * @param Lista<Cartas> - baraja de cartas
    */
    public String SetPaloTriunfo(){
        Scanner scn = new Scanner(System.in);
        String palo;
        while (true) {
            System.out.println("Escribe que palo de triunfo quires poner R,G,P,A");
            palo = scn.nextLine();
            if (!(palo.equals("R")) && !(palo.equals("G")) && !(palo.equals("P")) && !(palo.equals("A"))) {
                System.out.println("Ese no es un palo válido");
            } else {
                switch(palo){
                    case "R":
                    Colors.println("El palo del triunfo es R... De Rosalía", Colors.RED + Colors.HIGH_INTENSITY);
                    System.out.println();
                    break;
                    case "G":
                    Colors.println("El palo del triunfo es G... De GUAPA", Colors.GREEN + Colors.HIGH_INTENSITY);
                    System.out.println();
                    break;
                    case "P":
                    Colors.println("El palo del triunfo es P... De Patrona", Colors.BLUE + Colors.HIGH_INTENSITY);
                    System.out.println();
                    break;
                    case "A":
                    Colors.println("El palo del triunfo es A... De Altura", Colors.YELLOW + Colors.HIGH_INTENSITY);
                    System.out.println();
                    break;
                }
                return palo;
            }
        }
    }

    /**
    * Método que indica quien es el barejeador y quien empieza.
    * Reparte las cartas a los jugadores y pone el palo de triunfo.
    */
    public void EmpiezaTruco(){

        Lista<Cartas> baraja_mezcla = new Lista<Cartas>();
        String triunfo_actual;

        empieza = ronda_actual%total_jugadores;
        barajeador = (ronda_actual%total_jugadores)+1;

        Colors.println("Trucos " + (trucos+1), Colors.HIGH_INTENSITY);

        //Le asigna el turno que le toca para jugar a cada jugador
        for(int i =0; i<total_jugadores;i++){
            jugadores[(empieza+i)%total_jugadores].SetTurno(i);
        }

        Colors.println("El barajeador es el jugador " + barajeador, Colors.HIGH_INTENSITY);
        Colors.println("Empieza el jugador " + empieza, Colors.HIGH_INTENSITY);
        Colors.println("Se barajean las cartas...",Colors.HIGH_INTENSITY);

        //Se reparten las cartas a los jugadores
        baraja_mezcla = MezclaBaraja();
        SetManos(baraja_mezcla);

        Colors.println("Ahora se tomará una carta extra para determinar el palo del triunfo.", Colors.HIGH_INTENSITY);
        triunfo_actual = PaloTriunfo(baraja_mezcla);
    }

    /**
    * Método para empezar una ronda, llama a empieza truco y a que los Jugadores
    * hagan sus apuestas, hace todos los trucos de la ronda y al final da el puntaje.
    */
    public void EmpiezaRonda(){

        Colors.println("Ronda " + (ronda_actual+1), Colors.HIGH_INTENSITY);

        EmpiezaTruco();

        for(int i=0; i<total_jugadores; i++){
            Colors.println("Jugador " + i, Colors.HIGH_INTENSITY);
            SetApuesta(i);
        }
        ImprimeApuesta();

        Trucos();
        truco_actual=0;
        PuntajeRonda();
    }

    /**
    * Hace todos los trucos de la ronda.
    */
    public void Trucos(){
        for(int i=0; i<ronda_actual+1; i++){
            Colors.println("Ronda " + (ronda_actual+1), Colors.HIGH_INTENSITY);
            Colors.println("Truco " + (truco_actual+1) + "/" + (ronda_actual+1), Colors.HIGH_INTENSITY);
            Colors.println("El palo del triunfo para esta ronda es: " + triunfo, Colors.HIGH_INTENSITY);
            for(int j=0; j<total_jugadores; j++){
                TiraJugador((empieza+j)%total_jugadores);
            }
            //empieza = GanadorTruco(); FALTA
            GanadorTruco();
            lider="";
            empieza = (empieza+1)%total_jugadores;
            truco_actual++;
        }
    }
    /**
    * Método para que un jugador pueda tirar una carta en su turno.
    * @param index_jugador - jugador que va a tirar una carta.
    */
    public void TiraJugador(int index_jugador){
        Lista <Cartas> mano = new Lista<Cartas>();
        mano = jugadores[index_jugador].GetMano().clone();

        Colors.println("Tira el"+ Colors.RED + " jugador "+ index_jugador, Colors.HIGH_INTENSITY);
        String mensaje = Colors.HIGH_INTENSITY + "Jugador " + empieza +" por favor selecciona la carta con la que jugarás...\n escribe la posicion de la carta que quieres tirar empieza en 0" + Colors.RESTORE;
        String error="No seas tramposx! Elige una carta válida o muere en el castigo del loop infinito! \n";

        int min=0, max = mano.size()-1; //Solo puede lanzar una carta que este en su mano
        boolean valido=false;

        //Ciclo para poder elegir cartas válidas
        while(!valido){
            //Pide las cartas
            Colors.println("Aquí estan tus cartas: ", Colors.HIGH_INTENSITY);
            jugadores[index_jugador].PrintMano();
            int eleccion = getInt(mensaje, error, min, max);
            String palo = mano.get(eleccion).GetPalo();

            /*Primero asigna el palo líder, que se asígna hasta que haya una
            primer carta numerada*/
            if(lider.equals("")){
                if(palo!="W" && palo!="B"){
                    lider = palo;
                    Colors.println("El palo líder es " + lider, Colors.MAGENTA + Colors.HIGH_INTENSITY);
                }
            }
            /*Aquí nos aseguramos que si el jugador tiene el palo líder lo tire*/
            if(jugadores[index_jugador].hasPaloLider(lider)){
                if(!(palo.equals(lider)) && !(palo.equals("W")) && !(palo.equals("B"))){
                    System.out.println("Tienes palo líder no te hagas, tienes que tirar esa o un mago o bufon");
                    System.out.println("El palo líder es " + lider + " por si no te acordabas");
                }else{
                    Colors.println("Tiraste la carta " + mano.get(eleccion), Colors.HIGH_INTENSITY);
                    jugadores[index_jugador].SetCartaTurno(mano.get(eleccion));
                    juego.add(mano.get(eleccion));
                    mano.delete(mano.get(eleccion));
                    jugadores[index_jugador].SetMano(mano);
                    Colors.println("Buena eleccion supongo ...", Colors.HIGH_INTENSITY);
                    System.out.println("------------------------------------------------");
                    valido =true;
                }
            }else{
                Colors.println("Tiraste la carta " + mano.get(eleccion), Colors.HIGH_INTENSITY);
                jugadores[index_jugador].SetCartaTurno(mano.get(eleccion));
                juego.add(mano.get(eleccion));
                mano.delete(mano.get(eleccion));
                jugadores[index_jugador].SetMano(mano);
                Colors.println("Buena eleccion supongo ...", Colors.HIGH_INTENSITY);
                System.out.println("------------------------------------------------");
                valido =true;
            }
        }
    }

    /**
    * Método para pedir las apuestas a los jugadores.
    * @param index_jugador - el jugador que va a hacer su apuesta
    */
    public void SetApuesta(int index_jugador){
        String mensaje, error;
        int apuesta;

        mensaje = Colors.HIGH_INTENSITY + "Apuesta la cantidad de trucos que crees ganar [0," + (ronda_actual+1) + "]" + Colors.RESTORE;
        error = Colors.HIGH_INTENSITY + "Tiene que ser un número entre 0 y " + (ronda_actual+1) + Colors.RESTORE;

        apuesta = getInt(mensaje,error,0,ronda_actual+1);
        jugadores[index_jugador].SetApuesta(apuesta);
    }
    /**
    * Método para imprimir las apuuestas iniciales de todos los juagdores.
    */
    public void ImprimeApuesta(){
        Colors.println("Asi quedaron las apuestas", Colors.HIGH_INTENSITY);
        System.out.println("------------------------------------------------");
        for(int i=0; i<total_jugadores;i++){
            System.out.println("Jugador " + i + ":" + "\t" + jugadores[i].GetApuesta());
        }
        System.out.println("------------------------------------------------");
    }

    /**
    * FALTA
    */
    public void GanadorTruco(){
        Colors.println("El ganador es: ", Colors.BGD_GREEN + Colors.YELLOW + Colors.HIGH_INTENSITY);
    }
    /**
    * FALTA
    */
    public void PuntajeRonda(){
        Colors.println("Estos son los puntajes de la ronda: ", Colors.BGD_GREEN + Colors.YELLOW + Colors.HIGH_INTENSITY);
    }
    /**
    * FALTA
    */
    public void Despedida(){
        Colors.println("El ganador final es : ", Colors.YELLOW + Colors.HIGH_INTENSITY);
    }

    /**
    * Método que devuelve la opción del menu solo si es valida
    * @return int <code>opción</code>
    * @param mensaje -Texto del menu
    * @param error -Mensaje de que la opcion es invalida
    * @param min -La menor opcion valida
    * @param max -La opcion mas grande que podemos poner
    */
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
