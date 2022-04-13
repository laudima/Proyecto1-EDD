package edd.modelo;

import edd.estructuras.*;
import java.lang.Math.*;
import edd.modelo.*;

public class Jugadores {

    protected String nombre;
    protected int id;
    protected int puntos = 0;
    protected int apuesta;
    protected int ganados = 0;
    protected int turno;
    protected Cartas carta_turno;
    protected Lista<Cartas> mano;

    public Jugadores(int Id){
        this.nombre = "Jugador " + Id;
        this.id = id;
        puntos = 0;
        ganados = 0;
        apuesta = 0;
        turno = 0;
        Lista<Cartas> mano = new Lista<Cartas>();
    }

    public void SetGanados(int ganados){
        this.ganados = ganados;
    }

    public void AumentaGanados(){
        this.ganados++;
    }

    public void SetApuesta(int apuesta){
        this.apuesta = apuesta;
    }

    public void SetMano(Lista<Cartas> mano){
         this.mano = mano.clone();
    }

    public void SetPuntos(int puntaje){
        this.puntos+=puntaje;
    }

    public void PrintMano(){
        System.out.println(mano);
    }

    public Lista<Cartas> GetMano(){
        return mano;
    }

    public int GetPuntos(){
        return puntos;
    }

    public String GetNombre(){
        return nombre;
    }

    public int GetApuesta(){
        return apuesta;
    }

    public int GetId(){
        return id;
    }
    
    public int SetTurno(int turno){
        this.turno = turno;
        return turno;
    }

    public boolean hasPaloLider(String triunfo){
        for(int i=0; i<mano.size(); i++){
            String palo = mano.get(i).GetPalo();
            if(palo.equals(triunfo)){
                return true;
            }
        }
        return false;
    }

    public void SetCartaTurno(Cartas turno){
        this.carta_turno = turno;
    }

    public Cartas GetCartaTurno(){
        return carta_turno;
    }

    public int GetTurno(){
        return turno;
    }

    public int GetGanados(){
        return ganados;
    }
}
