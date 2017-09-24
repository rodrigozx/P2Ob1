
/* ******************************************************
 * Clase: Sistema
 *
 * @author Rodrigo Blanco - nro. 151251 - Programación II
 * ******************************************************
 */
package Dominio;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

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
     * @return 
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
    public ArrayList getRanking() {
        //Utiliza el criterio definido en la clase OrdenarJugadoresPorRanking que implemante Comparable
        Collections.sort(this.getListaJugadores(), new OrdenarJugadoresPorRanking());
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
    
    //El sistema ordena su lista de jugadores según criterio
    private class OrdenarJugadoresPorRanking implements Comparator<Jugador> {
     /*
     Orden de ordenación:
     1 - Mayor cantidad de partidas ganadas
     2 - Menor cantidad de partidas perdidas
     3 - Mayor cantidad de partidas Empatadas
     4 - Alfabético
     */
        @Override
        public int compare(Jugador j1, Jugador j2) {

            //Comparo las partidas ganadas
            int resultado = j2.getPartidas()[0] - j1.getPartidas()[0];

            //Si tienen la misma cantidad comparo las partidas perdidas
            if (resultado == 0) {
                resultado = j1.getPartidas()[1] - j2.getPartidas()[1];

                if (resultado == 0) {
                    //Si tienen la misma cantidad de partidas ganadas y perdidas
                    //Comparo las partidas empatadas
                    resultado = j2.getPartidas()[2] - j1.getPartidas()[2];

                    //Si tienen la misma cantidad de partidas empatadas
                    //Se ordena por alias ascendente;
                    if (resultado == 0) {
                        resultado = j1.getAlias().compareTo(j2.getAlias());
                    }
                }
            }
            return resultado;
        }
    }
}
