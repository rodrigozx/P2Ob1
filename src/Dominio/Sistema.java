
/* ******************************************************
 * Clase: Sistema
 *
 * @author Rodrigo Blanco - nro. 151251 - Programación II
 * ******************************************************
 */
package Dominio;

import java.util.*;

public class Sistema {

    //Atributos del Sistema
    private ArrayList<Jugador> listaJugadores;
    private Partida partida;

    //Constructor del Sistema
    public Sistema() {
        this.listaJugadores = new ArrayList();

        //La partida se crea al seleccionar nueva partida
        //this.partida = new Partida();
    }

    /**
     * *****************************************************
     */
    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public void agregarJugador(Jugador unJugador) {
        this.getListaJugadores().add(unJugador);
    }

    public void eliminarJugador(Jugador unJugador) {
        this.getListaJugadores().remove(unJugador);
    }

    /*
     **********************************************
     Método para ordenar Lista Jugadores según su ranking
     */
    public ArrayList ordenarPorRanking() {
        //Internamente, utiliza el criterio definido en la instancia pasada como parámetro (CriterioDecdreciente)
        return this.getListaJugadores();

    }

    public boolean validarAliasJugador(Jugador unJugador) {
        boolean noExisteJugador = true;

        return noExisteJugador;
    }

    //Método que valida que el sistema tenga al menos 1 Jugador.
    public boolean numJugadoresMinimos() {
        boolean retorno = false;
        if (this.getListaJugadores().size() >= 2) {
            retorno = true;
        }
        return retorno;
    }

    //Método que devuelve cantidad de Jugadores registrados.
    public int numJugadoresRegistrados() {
        int retorno = this.getListaJugadores().size();
        return retorno;
    }
}
