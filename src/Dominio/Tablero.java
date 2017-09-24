
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
    public boolean moverFicha(String jugada, int turno) {
        /*
        Coloco la ficha en la posición solicitada
        Devuelvo false si da error
        */
        
        boolean jugadaOk;
        
        //valido la jugada 
        jugadaOk = validarJugada(jugada, turno);

        return jugadaOk;
    }

    public boolean validarJugada(String jugada, int turno) {
        /*Este método valida la jugada contolando:
             - que no exista ganador.
             - que quien juega tenga cubos.
             - que la posición dónde se quiere colocar el cubo esté vacía.
             - que la posición tenga cubos adyascentes (excepto 1ra. jugada).
             - que no se forme un cuadrado de 2x2.
         */
        if (this.getCantFichasJug1()>0 && this.getCantFichasJug2()>0){
            int fila=0;
            int col=0;
            
            if (this.getMatrizTablero()[fila][col] == 0){
            
        }
            
        }
        boolean jugadaOk;
        jugadaOk = false;
        int[] coordenadas1;
        int[] coordenadas2;
        return jugadaOk;
    }

    public void tableroInicial(){
        for (int i = 0; i < this.matrizTablero.length; i++) {
            for (int j = 0; j < this.matrizTablero[i].length; j++) {
                
                if(i == 0){
                    // 21 Es torre de jugador 2
                    this.matrizTablero[i][j] = 21;
                }else if (i == this.matrizTablero.length-1){
                    // 11 Es torre de jugador 11
                    this.matrizTablero[i][j] = 11;
                }else{
                    // Sino esta vacío
                    this.matrizTablero[i][j] = 0;    
                }
                
            }
        }
    }
    
}
