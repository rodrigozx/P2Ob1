
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
                    //No debería entrar en ésta opción
                    retorno = "Ficha en tablero desconocida";
            }
            //Valido que si el destino tiene ficha se pueda comer porque está en el arco
            if(jugPos2!=0){
               retorno = validoComer(coordDestino);
            }
        }
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
        if ((coordOrigen[0] == coordDestino[0]) || (coordOrigen[1] == coordDestino[1])) {
            int valorAbs;
            boolean salir = false;
            int[] coordConsulta = {0,0};
            
            if((coordOrigen[0] == coordDestino[0])){
                valorAbs = Math.abs(coordOrigen[0] - coordDestino[0]);
                
                for (int i = 1; (i <= valorAbs) && !salir; i++) {

                    if (coordOrigen[1] > coordDestino[1]){
                        coordConsulta[0] = coordOrigen[0];
                        coordConsulta[1] = coordOrigen[1] - i;
                        salir = existeFicha(coordConsulta);
                    }else{
                        //(coordOrigen[1] < coordDestino[1])
                        coordConsulta[0] = coordOrigen[0];
                        coordConsulta[1] = coordOrigen[1] + i;
                        salir = existeFicha(coordConsulta);
                    }
                }
            }else{
                //(coordOrigen[1] == coordDestino[1])
                valorAbs = Math.abs(coordOrigen[0] - coordDestino[0]);
                
                for (int i = 1; (i < valorAbs) && !salir; i++) {

                    if (coordOrigen[0] > coordDestino[0]){
                        coordConsulta[0] = coordOrigen[0] -i;
                        coordConsulta[1] = coordOrigen[1];
                        salir = existeFicha(coordConsulta);
                    }else{
                        //(coordOrigen[1] < coordDestino[1])
                        coordConsulta[0] = coordOrigen[0] +i;
                        coordConsulta[1] = coordOrigen[1];
                        salir = existeFicha(coordConsulta);
                    }
                }
            }
            if (salir) {
                retorno = "Existe ficha bloqueando el movimiento";
            }
        } else {
            retorno = "El movimiento no es horizontal o vertical.";
        }

        return retorno;
    }

    private String validoMovimientoAlfil(int[] coordOrigen, int[] coordDestino) {
        String retorno = "OK";

        //El movimiento de alfil debe tener distinta fila y la columna en origen y destino
        if ((coordOrigen[0] != coordDestino[0]) && (coordOrigen[1] != coordDestino[1])) {

            //El valor absoluto de la diferencia entre fila origen y fila destino
            //debe ser igual al valor absoluto de la diferencia dentre columna de origen
            //y columna de destino
            int valorAbsFila;
            int valorAbsColumna;

            valorAbsFila = Math.abs(coordOrigen[0] - coordDestino[0]);
            valorAbsColumna = Math.abs(coordOrigen[1] - coordDestino[1]);
            System.out.println("valorAbsFila " + valorAbsFila);
            System.out.println("valorAbsColumna" + valorAbsColumna);
            //Si son iguales es diagonal
            //Este valor también es la cantidad de posiciones intermedias.
            if (valorAbsFila == valorAbsColumna) {

                boolean salir = false;
                int[] coordConsulta = {0, 0};

                //Recorro las posiciones intermedias de origen y destino
                for (int i = 1; (i < valorAbsFila) && !salir; i++) {

                    //fila y columna de origen mayor a las de destino
                    if ((coordOrigen[0] > coordDestino[0]) && (coordOrigen[1] > coordDestino[1])) {

                        coordConsulta[0] = coordOrigen[0] - i;
                        coordConsulta[1] = coordOrigen[1] - i;
                        salir = existeFicha(coordConsulta);

                        //fila de origen mayor a la de destino
                    } else if ((coordOrigen[0] > coordDestino[0]) && (coordOrigen[1] < coordDestino[1])) {

                        coordConsulta[0] = coordOrigen[0] - i;
                        coordConsulta[1] = coordOrigen[1] + i;
                        salir = existeFicha(coordConsulta);
                        //columna de origen mayor a la de destino
                    } else if ((coordOrigen[0] < coordDestino[0]) && (coordOrigen[1] > coordDestino[1])) {

                        coordConsulta[0] = coordOrigen[0] + i;
                        coordConsulta[1] = coordOrigen[1] - i;
                        salir = existeFicha(coordConsulta);
                        //fila y columna de destino mayores a la de origen
                    } else if ((coordOrigen[0] < coordDestino[0]) && (coordOrigen[1] < coordDestino[1])) {

                        coordConsulta[0] = coordOrigen[0] + i;
                        coordConsulta[1] = coordOrigen[1] + i;
                        salir = existeFicha(coordConsulta);
                    } else {
                        //No debería entrar en esta opción.
                        retorno = "Error: inconsistencia de filas y columnas en movimiento de alfil.";
                    }
                }
                if (salir) {
                    retorno = "Existe ficha bloqueando el movimiento";
                }
            } else {
                retorno = "El movimiento no es diagonal.";
            }
        } else {
            retorno = "El movimiento no es posible.";
        }
        return retorno;
    }

    private boolean existeFicha(int[] coordenadas) {
        boolean retorno;
        int valor = this.getMatrizTablero()[coordenadas[0]][coordenadas[1]];

        retorno = (valor != 0);
        return retorno;
    }

    private String validoComer(int[] destino) {
        String retorno = "Error: No se pueden comer fichas fuera de los arcos";
        int largoMatriz;
        int columnaArco;
        int filaArcoJug1 = 0;
        int filaArcoJug2;

        //Solo se puede comer en los arcos.
        largoMatriz = this.getMatrizTablero().length;
        filaArcoJug2 = largoMatriz - 1;

        //Al ser impar siempre es la mitad.
        //La división se queda con el entero correcto porque 
        //el largo ya tiene el +1.
        columnaArco = (largoMatriz / 2);

        if (destino[0] == filaArcoJug1 || destino[0] == filaArcoJug2) {
            if (destino[1] == columnaArco) {
                retorno = "OK";
            }
        }

        return retorno;
    }

}
