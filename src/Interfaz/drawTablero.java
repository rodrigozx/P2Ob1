/**
 * ***************************************************
 * Clase: drawTablero
 *
 * @author Rodrigo Blanco - 151251 - Programación II
 * *************************************************
 */
package Interfaz;

import java.util.Arrays;
import Interfaz.Color;

public class drawTablero {

    //Recorro la matriz y dibujo
    public static void draw(int[][] matriz, boolean rotado) {

        //La matriz a dibujar debe ser impar, sino no puede tener arco.
        if(matriz.length % 2 !=0 ){

            String colorNegro = Color.getColor("NEGRO");
            String colorVerde = Color.getColor("VERDE");

            int[][] mat = new int[matriz.length][matriz[0].length];
            mat = matriz;
            boolean arco = false;
            int largoMatriz = matriz.length;
            // Tengo que alternar entre una fila con valores de la matriz
            // y una fila sólo con separadores
            boolean numFilas = false;
            int filaImp = largoMatriz;
            int largoDrawMat = (largoMatriz * 2) + 1;

            //Cambio de filas para vista
            mat = transformView(matriz);
            
            //Si hay que rotar la matriz
            if (rotado) {
                mat = rotarMat(matriz);
                filaImp = 1;
            }

            //Salto de línea
            System.out.println("");

            //Recorro las filas (son el doble de filas porque tengo de dibujar los separadores)
            for (int i = 0 ; i < largoDrawMat; i++) {

                // Si es una fila con valores
                if (numFilas) {

                    //Imprimo número de fila
                    System.out.print(colorNegro + filaImp + " ");

                    //Imprimo el numero de fila de mayor a menor
                    if(rotado){
                        filaImp++;
                    }else{
                        filaImp--;
                    }

                    numFilas = false;
                } else {
                    System.out.print(colorNegro + "  ");
                    numFilas = true;
                }

                //Recorro las columnas (son el doble de columnas porque tengo de dibujar los separadores)
                for (int j = 0; j < largoDrawMat; j++) {

                    //Me fijo si estoy imprimiendo el arco
                    arco = false;

                    if( ((i==0) || (i==2) || (i==largoDrawMat-1) || (i==largoDrawMat-3)) ){

                        if((j==largoDrawMat/2) || (j==(largoDrawMat/2)-1) || (j==(largoDrawMat/2)+1)){
                            arco = true;
                        }
                    }else{
                        if((i==1) || (i==largoDrawMat-2)){
                            if((j==(largoDrawMat/2)-1) || (j==(largoDrawMat/2)+1)){
                                arco = true;
                            }
                        }
                    }

                    if(arco){
                        System.out.print(colorVerde + "*");
                    }else{
                        /* Sólo paso el valor de la matriz si imprimo valor
                           - La columna tiene que ser impar
                           - la fila tiene que ser impar
                        */ 
                        if ( ( (j%2)!=0) && ((i%2)!=0)) {
                            impValor(i, j, mat[(i-1)/2][(j-1)/2]);
                        } else {
                            impValor(i, j, 0);
                        }
                    }
                }

                //Salto de fila
                System.out.println();
            }

            //Despues de recorrer la matriz tengo que imprimir las letras de las columnas
            System.out.print(colorNegro + "  ");//espacio por número de filas
            for (int i = 0; i < largoMatriz; i++) {

                if(rotado){
                    letrasColumna(i, largoMatriz);
                }else{
                    //Si no es una matriz rotada, entonces las letras son crecientes
                    letrasColumna(i, 0);
                }
            }
            System.out.println("");
        
        }else{
            
            System.out.println("La matiz debe tener un largo de filas impar");
        }
    }
    
    //Letras de columnas
    private static void letrasColumna(int i, int orden) {

        //Si el órden es creciente paso el parámetro como 0
        //Si el órden es decreciente entonces paso el largo de la matriz
        //Creo un string que contiene las letras del abecedario
        String abecedario = "ZYXWVUTSRQPONMLKJIHGFEDCBA";

        char[] ch;

        if (orden == 0) { //si el órden es creciente
            //Convierto el abecedario en un array de caracteres
            ch = abecedario.toCharArray();

            //Doy vuelta el orden de los caracteres
            Arrays.sort(ch);
            
        } else {
            //recorto el abecedario al largo de la matriz
            abecedario = abecedario.substring(abecedario.length()-(orden), abecedario.length());
            
            //Convierto el abecedario en un array de caracteres
            ch = abecedario.toCharArray();
        }

        //Imprimo la letra de la posición
        System.out.print(" " + ch[i]);
    }

    private static void impValor(int i, int j, int valor) {

        //Si i es par o es 0, entonces imprimo fila +-+-+-+-+-+
        if (i % 2 == 0 || i == 0) {

            if (j % 2 == 0) {
                System.out.print(Color.getColor("NEGRO") + "+");
            } else {
                System.out.print(Color.getColor("NEGRO") + "-");
            }

        } else { //Es una fila con valores

            //Si la columna es par se imprimen separadores
            if (j % 2 == 0) {
                System.out.print("|");
            } else {
                impFicha(valor);
            }
        }

    }

    public static void impFicha(int ficha) {
        //Este método es llamado por impTablero por cada ficha en la matriz.
        //Imprime dependiendo del valor.
        String charFicha = "";
        String colorNegro = Color.getColor("NEGRO");
        String colorJug1 = Color.getColor("ROJO");
        String colorJug2 = Color.getColor("AZUL");

        // Si el rango está entre 11 y 20, entonces es el jugador1
        // Si el rango está entre 21 y 30 es el jugador2
        int jugador = ficha/10;
        
        if(jugador == 1){
              charFicha = colorJug1;
              ficha -=10;
        }else{
            if(jugador==2){
                charFicha = colorJug2;
                ficha-=20;
            }else{
                charFicha = colorNegro;
            }
        }
        
        switch (ficha) {
            case 1: //es Torre
                charFicha += "T";
                break;
            case 2: //es Alfil
                charFicha += "A";
                break;
            default: //no tiene valor
                charFicha += " ";
                break;
        }
       
        System.out.print( charFicha);
    }

    private static int[][] rotarMat(int[][] matriz) {
        int[][] mat = new int[matriz.length][matriz[0].length];
        int col=0;
        int fila=0;

        for (int i = matriz.length-1; i >= 0; i--) {
            
            col=0;
            for (int j = matriz[i].length-1; j >= 0; j--) {
                mat[fila][col] = matriz[i][j];
                col++;
            }
            fila++;
        }
        return mat;
    }

    private static int[][] transformView(int[][] matriz) {
        //Roto solo los valores de la celda de la matriz
        // para poder mostrarlo como se solicita
        int[][] mat = new int[matriz.length][matriz[0].length];

        //Recorro la nueva matriz hacia delante
        for (int i = 0; i< mat.length; i++) {
                
               mat[i]= matriz[matriz.length-i-1];
        }
        
        return mat;
    }
    
}

