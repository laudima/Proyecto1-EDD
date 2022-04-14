package edd.modelo;

import edd.colors.Colors;

/**
 * Clase de modelo de las cartas
 * @author Laura Itzel Rodríguez Dimayuga
 * @author Anshar Dominguef
 * @version Apr 2022
 */
public class Cartas{

    protected int numero;
    protected String palo;
    protected int jugador;
    protected int turno;
    protected String color;

    public Cartas(int numero, String palo){
        this.numero = numero;
        this.palo = palo;
        switch (palo){
            case "W": color = Colors.MAGENTA; break;
            case "B": color = Colors.CYAN; break;
            case "R": color = Colors.RED; break;
            case "G": color = Colors.GREEN; break;
            case "P": color = Colors.BLUE; break;
            case "A": color = Colors.YELLOW; break;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(color);
        sb.append(Colors.HIGH_INTENSITY);
        sb.append(numero + " " + palo);
        sb.append(Colors.RESTORE);
        return sb.toString();
    }

    public int GetNumero(){
        return numero;
    }
    public String GetPalo(){
        return palo;
    }
}
