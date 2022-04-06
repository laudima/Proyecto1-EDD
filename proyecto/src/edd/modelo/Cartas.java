package edd.modelo;

import edd.colors.Colors;

public class Cartas{

    protected int numero;
    protected char palo;
    protected int jugador;
    protected int turno;

    public Cartas(int numero, char palo){
        this.numero = numero;
        this.palo = palo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(Colors.HIGH_INTENSITY);
        sb.append(numero + " " + palo);
        sb.append(Colors.RESTORE);
        return sb.toString();
    }
}
