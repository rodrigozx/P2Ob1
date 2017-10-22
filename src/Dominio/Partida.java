
/* ******************************************************
 * Clase: Partida
 *
 * @author Rodrigo Blanco - nro. 151251 - Programación II
 * ******************************************************
 */
package Dominio;

import java.util.*;

public class Partida {

    /*Estado de la partida.
     0- No hay estado
     1- Ganador Jugador1
     2- Ganador Jugador2
     3- Hay Empate
     */
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private int estado;
    private int turno;


    /* CONSTRUCTOR POR PARAMETROS *************************************/
    public Partida(Jugador jugador1, Jugador jugador2, int turno, int largoMat) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.turno = turno;
        int[][] mat = new int[largoMat][largoMat];
        this.tablero = new Tablero(mat);
        this.estado = 0;
    }

    /* GETS Y SETS *************************************/
    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public int getTurno() {
        return this.turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public int getEstado(){
        return this.estado;
    }
    
    public void setEstado(int estadoP) {
        this.estado = estadoP;
    }

    /* METODOS *************************************/
    public String ingresarJugada(String jugada) {

        String jugadaOk;

        jugadaOk = this.getTablero().moverFicha(jugada, this.getTurno());

        //Si la jugada fue correcta cambio de turno
        if (jugadaOk.equals("OK")) {
            this.alternarTurno();
        }

        return jugadaOk;
    }

    public void alternarTurno() {
        if (this.getTurno() == 1) {
            this.setTurno(2);
        } else {
            this.setTurno(1);
        }
    }

    public boolean terminoPartida() {
         /*Valida el estado de la partida.
         0- No hay estado
         1- Ganador Jugador1
         2- Ganador Jugador2
         3- Hay Empate
         */
        boolean termino;
        if (this.getEstado()!= 0){
            termino = true;
        }else{
            termino = false;
        }
        return termino;
    }

    public void setEmpate() {
        
        this.setEstado(3);//Empate
        
        //A cada jugador le seteo un empate más...
        this.getJugador1().setPartidas(this.getJugador1().getPartidas()[0], this.getJugador1().getPartidas()[1], this.getJugador1().getPartidas()[0]+1);
        this.getJugador2().setPartidas(this.getJugador2().getPartidas()[0], this.getJugador2().getPartidas()[1], this.getJugador2().getPartidas()[0]+1);
    }
    
    //Abandona siempre el jugador que está de turno
    public String abandonar() {
        String retorno;
        
        if (this.getTurno() == 1) {
            //Jugador 1 Abandona.
            retorno = this.setGanador(2);
            if(retorno.equals("OK")){
                retorno = "El jugador " + this.getJugador1().getAlias() + "abandona la partida.";
            }
        
        }else{
            //Jugador 2 Abandona.
            retorno = this.setGanador(1);
            if(retorno.equals("OK")){
                retorno = "El jugador " + this.getJugador2().getAlias() + "abandona la partida.";
            }
        }
        return retorno;
    }
    
    public String setGanador(int ganador) {
        String retorno;
        
        if(ganador == 1){
            this.getJugador1().setPartidas(this.getJugador1().getPartidas()[0]+1, this.getJugador1().getPartidas()[1], this.getJugador1().getPartidas()[0]);
            this.getJugador2().setPartidas(this.getJugador2().getPartidas()[0], this.getJugador2().getPartidas()[1]+1, this.getJugador2().getPartidas()[0]);
            this.setEstado(1);            
        }else if(ganador == 2){
            this.getJugador1().setPartidas(this.getJugador1().getPartidas()[0], this.getJugador1().getPartidas()[1]+1, this.getJugador1().getPartidas()[0]);
            this.getJugador2().setPartidas(this.getJugador2().getPartidas()[0]+1, this.getJugador2().getPartidas()[1], this.getJugador2().getPartidas()[0]);
            this.setEstado(2);
        }else{
            retorno = "Error. No hay ganador";
        }
        retorno = "OK";
        return retorno;
    }

    public Jugador getJugadorDeTurno(int turno) {
        Jugador elJugador;
        if (turno == 1) {
            elJugador = this.getJugador1();
        } else {
            elJugador = this.getJugador2();
        }

        return elJugador;
    }
    
    public Jugador getGanador() {
        
        Jugador ganador;
        
        if(this.estado == 1 ){
            ganador = this.getJugador1();
        }else if(this.estado == 2){
            ganador = this.getJugador2();
        }else{
            ganador = null;
        }
        return ganador;
    }

    private int[] ingresarCoordenadas(String coordenada) {
        /*Posiciones:
         0-Filas
         1-Columnas
         */
        int[] lasCoordendas = new int[2];
        int cantFilas = this.getTablero().getMatrizTablero().length;
        int cantColumnas = this.getTablero().getMatrizTablero()[0].length;
        //Tomo el primero caracter y si es letra lo referencio a la Fila.
        String retorno = "Coordenadas OK\n"; //Mensaje de salida.
        String laFila = null;
        int laColumna = Integer.MIN_VALUE;
        int numFila = 0;
        boolean letraOk = false;
        HashMap<Integer, String> mapLetras = new HashMap<>();
        mapLetras.put(0, "A");
        mapLetras.put(1, "B");
        mapLetras.put(2, "C");
        mapLetras.put(3, "D");
        mapLetras.put(4, "E");
        mapLetras.put(5, "F");

        laFila = coordenada.trim().substring(0, 1);
        laFila = laFila.toUpperCase();

        //Valido el primer Caracter de la Fila que sea una letra valida
        for (int i = 0; i < cantFilas; i++) {
            if (mapLetras.get(i).equalsIgnoreCase(laFila)) {
                letraOk = true;
                numFila = i;
            }
        }
        if (!letraOk) {
            retorno = "!!! El valor de la fila es Incorrecto !!! \n";
        }

        //Valido el segundo caracter sea un número y que sea valido.
        try {
            laColumna = Integer.parseInt(coordenada.trim().substring(1, 2));

        } catch (Exception e) {
            retorno = retorno + " !!! El valor de la columna debe ser númerico !!! \n";

        }
        if ((laColumna < 1) || (laColumna > cantColumnas)) {
            retorno = " !!! El valor de la columna esta fuera de rango !!! \n";
        }

        lasCoordendas[0] = numFila;
        lasCoordendas[1] = laColumna - 1;
        return lasCoordendas;

    }
    
    
    public String validarJugada(String movimiento) {
        String salida = "";
        String aux;
        int[] coordenadas1;
        int[] coordenadas2;
        char ficha;
        char ficha2;
        /*
         Tipos de entradas:
            - movimiento de fichas:
                                    A1 A5
            - jugadas especiales:   
                                    (X)Abandonar 
                                    (R)Rotar tablero
                                    (E)Ofrecer empate
                                    (Y)Ayuda
                                    (H)Historial        
        */
        
        //Quito los espacios en caso que existan
        movimiento = movimiento.replaceAll("\\s+", "");

        //Si la jugada es de 4 caracteres, entonces es un movimiento de fichas
        
        if (movimiento.length() == 5) {
            boolean movOk;

            //Coordenadas de primera posición 
            coordenadas1 = this.ingresarCoordenadas(movimiento.substring(1, 2));
            
            //Coordenadas de segunda posición
            coordenadas2 = this.ingresarCoordenadas(movimiento.substring(4, 5));
            
            movOk = moverFicha(coordenadas1[0], coordenadas1[1], coordenadas2[0], coordenadas2[1]);

        } else {

            
      
        }
        return salida;
    }

    //Ingresar movimiento de ficha
    private boolean moverFicha(int iP1, int jP1, int iP2, int jP2) {

        boolean retorno = false;

        //Ya vienen validadas las coordenadas.
        
        System.out.println(this.getTablero().getMatrizTablero()[iP1][jP1]);
        System.out.println(this.getTablero().getMatrizTablero()[iP2][jP2]);

        return retorno;
    }
}
