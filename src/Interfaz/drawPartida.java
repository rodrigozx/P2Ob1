/**
 * ***************************************************
 * Clase: drawPartida
 *
 * @author Rodrigo Blanco - 151251 - Programación II
 * *************************************************
 */
package Interfaz;

import Dominio.Partida;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class drawPartida {

    Scanner teclado = new Scanner(System.in);

    //Defino Constructores
    //Constructor sin parametros
    public drawPartida() {

    }

    //Recorro la matriz y dibujo
    public static void datos(Partida unaPartida) {
        drawPartida.datosJugadores(unaPartida);

    }

    //Muestra Datos Jugadores
    private static void datosJugadores(Partida unaPartida) {

        System.out.print("\nJUEGA: ");
        if (unaPartida.getTurno() == 1) {
            System.out.println(Color.getColor("ROJO") + unaPartida.getJugador1().getAlias());
        }
        if (unaPartida.getTurno() == 2) {
            System.out.println(Color.getColor("AZUL") + unaPartida.getJugador2().getAlias());
        }
    }
    
     //Muestra Datos Jugadores
    public static void mostrarHistorial(Partida unaPartida) {

        ArrayList<String> historial;
        System.out.print("\nHistorial de partidas: \n");
        historial = unaPartida.getHitorial();
        boolean jug1 = true;
        
        if(historial.size() > 0){
            
            for (int i = 1; i < historial.size(); i++) {
                
                if(jug1){
                    System.out.print("Jugador 1 ");
                    jug1 =false;
                }else{
                    System.out.print("Jugador 2 "); 
                    jug1 =true;
                }
                System.out.println("- jugada " + i + ": " + historial.get(i).trim());
            }
            
        }else{
            System.out.println("No existen jugadas anteriores.");
        }
        System.out.println("");
    }

}
