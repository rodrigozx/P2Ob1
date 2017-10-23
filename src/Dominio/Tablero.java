
/* ******************************************************
 * Clase: Tablero
 *
 * @author Rodrigo Blanco - nro. 151251 - Programación II
 * ******************************************************
 */
package Dominio;

import java.util.*;

public class Tablero {

    //Matríz de int (cubos)
    /*
        Los valores en cada posición de las matrices corresponden a números.
        La codificación para identificar a que corresponde es la siguiente:
        - el primer dígito determina el color de la torre, donde:
            0 = no tiene color
            1 = corresponde al color del jugador 1
            2 = corresponde al color del jugador 2
        
        - el segundo digito determina la cantidad de cubos en la torre.
            0 =corresponde a que no existen cubos.
            5 es el valor máximo posible.
    
        Para la identificación de los dígitos se divide entre 10, resultando
        el entero = al primer dígito y el resto en el segundo.
     */
    private int[][] matrizTablero;
    private int cantFichasJug1;
    private int cantFichasJug2;

    /* CONSTRUCTOR POR PARAMETROS *************************************/
    public Tablero(int[][] matrizTablero) {
        this.matrizTablero = matrizTablero;

        //La cantidad de fichas de cada jugador es igual al largo de la matriz
        this.cantFichasJug1 = matrizTablero.length;
        this.cantFichasJug2 = matrizTablero.length;
        tableroInicial();
    }

    /* GETS Y SETS *************************************/
    public int[][] getMatrizTablero() {
        return matrizTablero;
    }

    public void setMatrizTablero(int[][] matrizTablero) {
        this.matrizTablero = matrizTablero;
    }

    public int getCantFichasJug1() {
        return this.cantFichasJug1;
    }

    public int getCantFichasJug2() {
        return this.cantFichasJug2;
    }

    public void setCantFichasJug1(int cantFichasJug1) {
        this.cantFichasJug1 = cantFichasJug1;
    }

    public void setCantFichasJug2(int cantFichasJug2) {
        this.cantFichasJug2 = cantFichasJug2;
    }

    /* METODOS *************************************/
    public void tableroInicial() {
        for (int i = 0; i < this.matrizTablero.length; i++) {
            for (int j = 0; j < this.matrizTablero[i].length; j++) {

                if (i == 0) {
                    // 11 Es torre de jugador 1
                    this.matrizTablero[i][j] = 11;
                } else if (i == this.matrizTablero.length - 1) {
                    // 21 Es torre de jugador 2
                    this.matrizTablero[i][j] = 21;
                } else {
                    // Sino esta vacío
                    this.matrizTablero[i][j] = 0;
                }
            }
        }
    }

    //Ingresar movimiento de ficha
    public String validarMovimiento(int iP1, int jP1, int iP2, int jP2, int turno) {

        String retorno = "OK";
        int pos1;
        int pos2;
        int jugPos1;
        int jugPos2;

        //Ya vienen validadas las coordenadas dentro del tablero.
        System.out.println("origen:" + iP1 + jP1);
        System.out.println("destino:" + iP2 + jP2);
        System.out.println("Turno:" + turno);

        pos1 = this.getMatrizTablero()[iP1][jP1];
        jugPos1 = pos1 / 10;
        System.out.println("Pos1" + pos1);
        System.out.println("jugPos1" + jugPos1);
        pos2 = this.getMatrizTablero()[iP2][jP2];
        jugPos2 = pos2 / 10;
        System.out.println("Pos2" + pos2);
        System.out.println("jugPos2" + jugPos2);

        if (jugPos1 != turno || jugPos2 == turno) {
            retorno = "Error:";
            if (jugPos1 == 0) {
                retorno += "La posición inicial está vacía";
            } else {
                retorno += "La ficha de la posición inicial no corresponde al jugador";
            }
            if (jugPos2 == turno) {
                retorno += "\nLa ficha en la posición final no puede ser del mismo jugador";
            }
        }

        System.out.println("");
        this.imprimir();

        return retorno;
    }

    public String moverFicha(int iP1, int jP1, int iP2, int jP2) {

        //Ya vienen validadas las coordenadas.
        String retorno = "OK";
        int valorFicha;
        int jug;
        int nuevoValor;

        valorFicha = this.getMatrizTablero()[iP1][jP1];
        System.out.println("valorFicha" + valorFicha);
        jug = valorFicha / 10;
        System.out.println("jug" + jug);
        valorFicha = valorFicha - (10* jug);
        System.out.println("valorFicha" + valorFicha);
        if (valorFicha == 1) {
            nuevoValor = 2;
        } else {
            nuevoValor = 1;
        }
        System.out.println("nuevoValor" + nuevoValor);
        nuevoValor = nuevoValor + (10 * jug);

        
        this.getMatrizTablero()[iP1][jP1] = 0;
        this.getMatrizTablero()[iP2][jP2] = nuevoValor;
        System.out.println("nuevovalor" + nuevoValor);

        return retorno;
    }

    public void imprimir() {
        for (int i = 0; i < this.getMatrizTablero().length; i++) {
            for (int j = 0; j < this.getMatrizTablero()[i].length; j++) {
                System.out.print(this.getMatrizTablero()[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
