
/* ******************************************************
 * Clase: Tablero
 *
 * @author Rodrigo Blanco - nro. 151251 - Programación II
 * ******************************************************
 */
package Dominio;

import java.util.*;

public class Tablero {

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

        int[] coordOrigen = {iP1, jP1};
        int[] coordDestino = {iP2, jP2};
        String retorno = "OK";
        int pos1;
        int pos2;
        int jugPos1;
        int jugPos2;
        int tipoFicha;

        //Ya vienen validadas las coordenadas dentro del tablero.
        System.out.println("origen:" + iP1 + jP1);
        System.out.println("destino:" + iP2 + jP2);
        System.out.println("Turno:" + turno);

        pos1 = this.getMatrizTablero()[coordOrigen[0]][coordOrigen[1]];
        jugPos1 = pos1 / 10;
        tipoFicha = pos1 - (jugPos1 * 10);
        pos2 = this.getMatrizTablero()[coordDestino[0]][coordDestino[1]];
        jugPos2 = pos2 / 10;

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
        } else {
            //valido jugada segun tipo de ficha        
            switch (tipoFicha) {

                case 1: // Es una Torre
                    retorno = validoMovimientoTorre(coordOrigen, coordDestino);
                    break;

                case 2: // Es un Alfil
                    retorno = validoMovimientoAlfil(coordOrigen, coordDestino);
                    break;
                default:
                    retorno = "Ficha en teblero desconocida";
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
        valorFicha = valorFicha - (10 * jug);
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

    private String validoMovimientoTorre(int[] coordOrigen, int[] coordDestino) {
        String retorno = "OK";

        //El movimiento de torre debe tener la misma fila o la misma columna en origen y destino
        if((coordOrigen[0] == coordDestino[0]) || (coordOrigen[1] == coordDestino[1])){
            
        }else{
            retorno = "El movimiento no es posible.";
        }
        
        
        return retorno;
    }

    private String validoMovimientoAlfil(int[] coordOrigen, int[] coordDestino) {
        String retorno = "OK";

         //El movimiento de alfil debe tener la distinta fila y distinta columna en origen y destino
        if((coordOrigen[0] != coordDestino[0]) && (coordOrigen[1] != coordDestino[1])){
            
        }else{
            retorno = "El movimiento no es posible.";
        }        
        return retorno;
    }

    private boolean existeFicha(int[] coordenadas){
        boolean retorno;
        int valor = this.getMatrizTablero()[coordenadas[0]][coordenadas[1]];
        if (valor !=0){
            retorno = true;
        }else{
            retorno = false;
        }  
        return retorno;
    }
    
    ////////////para prueba
    public void imprimir() {
        for (int i = 0; i < this.getMatrizTablero().length; i++) {
            for (int j = 0; j < this.getMatrizTablero()[i].length; j++) {
                System.out.print(this.getMatrizTablero()[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
