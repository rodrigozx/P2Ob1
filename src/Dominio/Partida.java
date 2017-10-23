
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

    public String ingresarJugada(String jugada) {

        String retorno;

        retorno = this.validarFormatoJugada(jugada);

        //Valido todo el formato de la jugada
        if (retorno.substring(0, 2).equals("OK")) {
            int iP1 = Integer.parseInt(retorno.substring(2,3));
            int jP1 = Integer.parseInt(retorno.substring(3,4));
            int iP2 = Integer.parseInt(retorno.substring(4,5));
            int jP2 = Integer.parseInt(retorno.substring(5,6));
            
            // Si el formato está correcto, tengo que validar el movimiento
            // También valido que el movimiento de la ficha sea del jugador, por eso paso el turno.
            retorno = this.getTablero().validarMovimiento(iP1, jP1, iP2, jP2, this.getTurno());
            
            if (retorno.substring(0, 2).equals("OK")){
                System.out.println("Retorno ingresarJugada:" + retorno);
                //Validar movimiento
                retorno = this.getTablero().validarMovimiento(iP1, jP1, iP2, jP2, this.getTurno());
                
                if (retorno.substring(0, 2).equals("OK")){
                    retorno = this.getTablero().moverFicha(iP1, jP1, iP2, jP2);
                }else{
                    //Devuelvo el retorno con el error;    
                }
                this.alternarTurno();
            }else{
                //Devuelvo el retorno con el error;
            }
        }

        return retorno;
    }
 
    private String validarFormatoCoordenadas(String coordenada) {
        /*Posiciones:
        0-Filas
        1-Columnas
        */
        
        String retorno="";
        int[] lasCoordenadas = new int[2];
        int cantFilas = this.getTablero().getMatrizTablero().length;
        int cantColumnas = this.getTablero().getMatrizTablero()[0].length;
        //Tomo el primero caracter y si es letra lo referencio a la Fila.
        String laColumna = null;
        int laFila = Integer.MIN_VALUE;
        String laFilaStr = null;
        int numColumn = 0;
        boolean letraOk = false;
        
        //El tablero tiene un largo máximo de 5
        HashMap<Integer, String> mapLetras = new HashMap<>();
        mapLetras.put(0, "A");
        mapLetras.put(1, "B");
        mapLetras.put(2, "C");
        mapLetras.put(3, "D");
        mapLetras.put(4, "E");
        mapLetras.put(5, "F");

        laColumna = coordenada.trim().substring(0, 1);
        laFilaStr = coordenada.trim().substring(1, 2);
        
        //Valido el primer Caracter de la Fila que sea una letra valida
        for (int i = 0; (i < cantColumnas) && !letraOk; i++) {
            if (mapLetras.get(i).equals(laColumna)) {
                letraOk = true;
                numColumn = i;
            }
        }
        if (!letraOk) {
            retorno = "Error: El valor de la columna esta fuera de rango.\n";
        }else{
            
            //Valido el primer caracter sea un número y que sea valido.
            try {
                laFila = Integer.parseInt(laFilaStr);

            } catch (NumberFormatException e) {
                retorno = retorno + "Error: El valor de la fila esta fuera de rango.\n";

            }
        }
        if ((laFila < 1) || (laFila > cantFilas)) {
            retorno = "Error: El valor de la fila esta fuera de rango.\n";
        
        }else{
            //Si las cordenadas están OK, las devuelvo traducidas en as reales del tablero
            lasCoordenadas[0] = laFila-1;
            lasCoordenadas[1] = numColumn;
            retorno = Integer.toString(lasCoordenadas[0]) + Integer.toString(lasCoordenadas[1]);
        }
        return retorno;

    }
    
    
    public String validarFormatoJugada(String movimiento) {
        
        String retorno = "";
        String aux;
        
        //Valido el espacio en el 3er caracter
        //Ya validé que movimiento tenga largo 5.
        if (movimiento.substring(2, 3).equals(" ")){
        
            //Quito el espacio
            movimiento = movimiento.replaceAll("\\s+", "");

            //Si la jugada es de 4 caracteres, entonces es un movimiento correcto de fichas
            if (movimiento.length() == 4) {
                boolean movOk = true;

                //Coordenadas de primera posición 
                aux = this.validarFormatoCoordenadas(movimiento.substring(0, 2));
                if(aux.trim().length()> 2){ //Si el largo es mayor a las coordenadas entonces trae un error
                    movOk = false;
                }else{

                    retorno = aux.trim().substring(0,1) + aux.trim().substring(1,2);

                    //Coordenadas de segunda posición
                    aux = this.validarFormatoCoordenadas(movimiento.substring(2, 4));

                    if(aux.trim().length()> 2){ //Si el largo es mayor a las coordenadas entonces trae un error
                        movOk = false;
                        retorno = aux;
                    }else{
                        retorno= "OK" + retorno + aux.trim().substring(0,1) + aux.trim().substring(1,2);
                    }
                }

            } else {
                retorno = "Error: Jugada inválida";
            }
        }else{
            retorno = "Error: No existe espacio entre coordenadas";
        }
        
        //Si el ingreso esta bien, retorno la jugada traducida
        return retorno;
    }
}

