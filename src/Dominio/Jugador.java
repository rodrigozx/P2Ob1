
/* ******************************************************
 * Clase: Jugador
 *
 * @author Rodrigo Blanco - nro. 151251 - Programación II
 * ******************************************************
 */
package Dominio;

import java.util.*;

public class Jugador {

    private String alias;
    private String nombre;
    private int edad;
    private int[] partidas = new int[3];

    /*
        Significado de posisiones en array de Partidas:
        pos = 0 -Partidas Ganadas
        pos = 1 -Partidas Perdidas
        pos = 2 -Partidas Empatadas
    */
    
    /* CONSTRUCTOR POR DEFECTO *************************************/
    public Jugador() {
        this.alias = "Sin Alias";
        this.nombre = "Sin Nombre";
        this.edad = 0;
    }

    /* CONSTRUCTOR POR PARAMETROS *************************************/
    public Jugador(String elAlias, String elNombre, int laEdad) {
        this.setAlias(elAlias);
        this.setNombre(elNombre);
        this.setEdad(laEdad);
        this.setPartidas(0, 0, 0);
    }

    /* GETS Y SETS *************************************/
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int unaEdad) {
        this.edad = unaEdad;
    }


    /* METODOS *************************************/
    public int[] getPartidas() {
        return this.partidas;
    }

    public void setPartidas(int ganada, int perdida, int empatada) {
        int[] ranking = new int[3];
        ranking[0] = ganada;
        ranking[1] = perdida;
        ranking[2] = empatada;
        this.partidas = ranking;
    }

    @Override //Dos jugadores son iguales si tienen el mismo alias.
    public boolean equals(Object obj) {
        boolean retorno;
        if (obj == null) {
            retorno = false;
        } else {
            retorno = (this.getAlias().equalsIgnoreCase(((Jugador) obj).getAlias()));
        }
        return retorno;

    }

    public String getPartidasGanadasToString() {
        int[] partidasGan = this.getPartidas();
        return Integer.toString(partidasGan[0]);
    }

    public String getPartidasPerdidasToString() {
        int[] partidasGan = this.getPartidas();
        return Integer.toString(partidasGan[1]);
    }
    
    public String getPartidasEmpatadasToString() {
        int[] partidasGan = this.getPartidas();
        return Integer.toString(partidasGan[2]);
    }    

    //Defino el toString del Jugador.
    @Override
    public String toString() {
        return "Datos de Jugador: "
                + "\n" + "Alias : " + this.getAlias()
                + "\n" + "Nombre: " + this.getNombre()
                + "\n" + "Edad  : " + this.getEdad()
                + "\n" + "Partidas Ganadas: " + this.getPartidasGanadasToString()
                + "\n" + "Partidas Perdidas: " + this.getPartidasPerdidasToString()
                + "\n" + "Partidas Empatadas: " + this.getPartidasEmpatadasToString();
    }
}
