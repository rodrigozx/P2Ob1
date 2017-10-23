
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

    /* CONSTRUCTOR POR PARAMETROS *************************************/
    public Tablero(int[][] matrizTablero) {
        this.matrizTablero = matrizTablero;

        //La cantidad de fichas de cada jugador es igual al largo de la matriz
        tableroInicial();
    }

    /* GETS Y SETS *************************************/
    public int[][] getMatrizTablero() {
        return matrizTablero;
    }

    public void setMatrizTablero(int[][] matrizTablero) {
        this.matrizTablero = matrizTablero;
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

    //Ingresar movimiento de ficha.
    public String validarMovimiento(int iP1, int jP1, int iP2, int jP2, int turno) {

        int[] coordOrigen = {iP1, jP1};
        int[] coordDestino = {iP2, jP2};
        int pos1;
        int pos2;
        int jugPos1;
        int jugPos2;
        int tipoFicha;
        String retorno;
        boolean defenderOk;

        //Ya vienen validadas las coordenadas dentro del tablero.
        pos1 = this.getMatrizTablero()[coordOrigen[0]][coordOrigen[1]];
        jugPos1 = pos1 / 10;
        tipoFicha = pos1 - (jugPos1 * 10);
        pos2 = this.getMatrizTablero()[coordDestino[0]][coordDestino[1]];
        jugPos2 = pos2 / 10;

        defenderOk = movDefenderOk(turno, coordDestino);        
        if(defenderOk){
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
                if ( (retorno.equals("OK")) && (jugPos2 != 0)) {
                    retorno = validoComer(coordDestino);
                }
            }
            
        }else{
            retorno="Error: Debe realizar un movimiento para defender su arco.";
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
        jug = valorFicha / 10;
        valorFicha = valorFicha - (10 * jug);
        if (valorFicha == 1) {
            nuevoValor = 2;
        } else {
            nuevoValor = 1;
        }
        nuevoValor = nuevoValor + (10 * jug);

        this.getMatrizTablero()[iP1][jP1] = 0;
        this.getMatrizTablero()[iP2][jP2] = nuevoValor;

        return retorno;
    }

    private String validoMovimientoTorre(int[] coordOrigen, int[] coordDestino) {
        String retorno = "OK";

        //El movimiento de torre debe tener la misma fila o la misma columna en origen y destino
        if ((coordOrigen[0] == coordDestino[0]) || (coordOrigen[1] == coordDestino[1])) {
            int valorAbs;
            boolean salir = false;
            int[] coordConsulta = {0, 0};

            if ((coordOrigen[0] == coordDestino[0])) {
                valorAbs = Math.abs(coordOrigen[0] - coordDestino[0]);

                for (int i = 1; (i <= valorAbs) && !salir; i++) {

                    if (coordOrigen[1] > coordDestino[1]) {
                        coordConsulta[0] = coordOrigen[0];
                        coordConsulta[1] = coordOrigen[1] - i;
                        salir = existeFicha(coordConsulta);
                    } else {
                        //(coordOrigen[1] < coordDestino[1])
                        coordConsulta[0] = coordOrigen[0];
                        coordConsulta[1] = coordOrigen[1] + i;
                        salir = existeFicha(coordConsulta);
                    }
                }
            } else {
                //(coordOrigen[1] == coordDestino[1])
                valorAbs = Math.abs(coordOrigen[0] - coordDestino[0]);

                for (int i = 1; (i < valorAbs) && !salir; i++) {

                    if (coordOrigen[0] > coordDestino[0]) {
                        coordConsulta[0] = coordOrigen[0] - i;
                        coordConsulta[1] = coordOrigen[1];
                        salir = existeFicha(coordConsulta);
                    } else {
                        //(coordOrigen[1] < coordDestino[1])
                        coordConsulta[0] = coordOrigen[0] + i;
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
    

    public boolean movDefenderOk(int turno, int[]coordDestino){
        boolean retorno = false;
        int valorArco = 0;
        int filaArco =0;
        int columnaArco = this.getMatrizTablero().length/2;
        
        switch(turno){
            
            case 1:
                filaArco = 0;
                valorArco = this.getMatrizTablero()[filaArco][columnaArco];
                break;
                
            case 2:
                filaArco = this.getMatrizTablero().length-1;
                valorArco = this.getMatrizTablero()[filaArco][columnaArco];
                break;
                
            default:
                break;
        }

        //Si el arco tiene ficha y no es del jugador
        if(valorArco!= 0 && (valorArco/10 != turno)){
            //Tiene que defender, asi que veo si el movimiento tiene como destino el arco
            if ((coordDestino[0] == filaArco) && (coordDestino[1] == columnaArco)){
                //Si esta ok porque la jugada tiene como destino el arco devuelve true
                retorno = true;
            }
        }else{//sino no importa si el destino es el arco
            retorno=true;
        }
        
        return retorno;
    }
    
    public ArrayList<String> movimientosPosibles(int turno){
        
        ArrayList<String> listaMovimientos = new ArrayList();
        int valorCasillero;
        String retorno;
        String coordenadas;
        
        for (int i = 0; i < this.getMatrizTablero().length; i++) {
            for (int j = 0; j<this.getMatrizTablero()[i].length; j++) {
                
                valorCasillero = this.getMatrizTablero()[i][j];
                if(valorCasillero/10 == turno){

                    for (int k = 0; k < this.getMatrizTablero().length; k++) {
                        for (int l = 0; l < this.getMatrizTablero()[k].length; l++) {
                            retorno = this.validarMovimiento(i, j, k, l, turno);
                            coordenadas = ""+i+""+j+""+k+""+l;
                            if(retorno.equals("OK")){
                                listaMovimientos.add(coordenadas.trim());
                            }
                        }
                    }

                }
                
            }
        }
        
        //Reviso el arco del jugador a ver si hay una ficha
        int largoMatriz;
        int columnaArco;
        int filaArco;
        int valorArco;
        
        largoMatriz = this.getMatrizTablero().length;
        if (turno == 1){
            filaArco = 0;
        }else{
            filaArco = largoMatriz - 1;
        }
        columnaArco = (largoMatriz / 2);
        
        valorArco = this.getMatrizTablero()[filaArco][columnaArco];

        //Si no tiene una ficha del jugador o está vacía
        if ( (valorArco/10 != turno) && (valorArco/10 !=0) ){
            int filaMov;
            int columnaMov;
            
            //Hay una ficha contraria en el arco, debo revisar que entre la 
            //lista de movimientos posibles haya un movimiento con ese destino.
            for (int i = 0; i < listaMovimientos.size(); i++) {
                
                filaMov = Integer.parseInt(listaMovimientos.get(i).substring(2,3));
                columnaMov = Integer.parseInt(listaMovimientos.get(i).substring(3,4));
                
                //Si no es un movimiento para defender el arco, lo quito.
                if ( (filaMov != filaArco) || (columnaArco != columnaMov)){
                    listaMovimientos.remove(i);
                }
            }
        }
        return listaMovimientos;
    }
    
}
