/**
 * ***************************************************
 * Clase: Prueba
 *
 * @author Rodrigo Blanco - nro. 151251 - Programación II
 * *************************************************
 */
package Prueba;

import Dominio.*;
import Interfaz.*;
import java.util.*;

public class Prueba {

    public static void main(String[] args) {

        //Se crea el sistema del juego
        Sistema miSistema = new Sistema();

        //Se crea el menu a mostrar
        drawMenu miMenu = new drawMenu(0, true);

        int dato; //Por defecto se ejecuta el menú princiapl.

        //Carga del Menu Principal
        miMenu.showLogo();
        boolean salir = false;

        miSistema.agregarJugador(new Jugador("Rolo  ", "Rodrigo ", 33, 1, 1, 1));
        miSistema.agregarJugador(new Jugador("Viky  ", "Virginia", 29, 2, 1, 0));
        miSistema.agregarJugador(new Jugador("Pepe  ", "Virginia", 29, 0, 1, 0));
        miSistema.agregarJugador(new Jugador("Pepo  ", "Virginia", 29, 0, 2, 2));        
        miSistema.agregarJugador(new Jugador("Chacho", "Virginia", 29, 3, 1, 0));
        miSistema.agregarJugador(new Jugador("Checho", "Virginia", 29, 2, 2, 1));
        miSistema.agregarJugador(new Jugador("Pepete", "Virginia", 29, 0, 0, 1));
        miSistema.agregarJugador(new Jugador("Pancho", "Virginia", 29, 1, 5, 3));
        miSistema.agregarJugador(new Jugador("Pancha", "Virginia", 29, 2, 1, 1));        

        do {
            dato = miMenu.showMenuPrincipal(4); //existen 4 opciones
            switch (dato) {

                //Registro de Jugador **********************************************************************
                case 1:
                    seleccionOpcionesRegistroJugador(miSistema, miMenu);
                    break;

                //Jugar ************************************************************************************
                case 2:
                    iniciarPartida(2, miSistema, miMenu); //Son dos jugadores.
                    break;

                //Muestra el Ranking de jugadores **********************************************************
                case 3:
                    miMenu.drawMenuRanking(miSistema.getRanking());
                    break;

                //Sale del programa ************************************************************************                  
                case 0:
                    miMenu.showSaludo();
                    salir = true;
                    break;

                default:
                //No hace nada
            }
        } while (salir != true);
    }

    public static void seleccionOpcionesRegistroJugador(Sistema miSistema, drawMenu miMenu) {
        String cabezal = "OPCIONES DE REGISTRO DE JUGADOR ";
        ArrayList<String> listaOpciones = new ArrayList();
        int opcSelect;

        listaOpciones.add("Volver");//0 
        listaOpciones.add("Registrar Nuevo Jugador"); //1
        listaOpciones.add("Eliminar Jugador");        //2

        //El último parámetro muestra o no la opción de volver.
        opcSelect = miMenu.menuOpciones(cabezal, listaOpciones, true);
        switch (opcSelect) {
            case 1:
                registroDeJugador(miSistema, miMenu);
                break;
            case 2:
                eliminarJugador(miSistema, miMenu);
                break;
            default: //no hace nada y vuelve al menu principal
        }
    }

    public static void registroDeJugador(Sistema miSistema, drawMenu miMenu) {
        //Se ejecuta el metodo mostrar Cabecera de Menú.
        miMenu.showCabeceraMenu(true, "Menú Registrar Jugador");

        //Se crea el objeto Jugador con los parametros.
        //Luego se agrega al ArrayList de Jugadores
        Jugador elJugador = new Jugador(miMenu.registrarJugadorAlias(miSistema.getListaJugadores()), miMenu.registrarJugadorNombre(), miMenu.registrarJugadorEdad(), 0, 0, 0);
        miMenu.mostrarMensaje("Jugador Registrado!", "");
        miSistema.agregarJugador((Jugador) elJugador);
        System.out.println("\n" + elJugador);
    }

    public static void eliminarJugador(Sistema miSistema, drawMenu miMenu) {

        //Se muestra la lista de jugadores y se elimina el seleccionado;
        Jugador elJugador = miMenu.seleccionarJugador(miSistema.getListaJugadores());
        miSistema.eliminarJugador((Jugador) elJugador);
        miMenu.mostrarMensaje("Jugador  " + elJugador.getAlias() + "  eliminado!", "correcto");
    }

    public static void iniciarPartida(int cantJugadores, Sistema miSistema, drawMenu miMenu) {

        int largoMat = 5;
        String jugada;
        boolean tableroRotado = false;

        //Veo si es un jugaor sólo
        //Selección de jugadores y arranque Partida. ******************************************
        //si existe menos de un jugador registrado
        if (!miSistema.numJugadoresMinimos()) {
            miMenu.mostrarMensaje("Error! la cantidad de jugadores registrados", "error");
            miMenu.mostrarMensaje("es de al menos dos para este tipo de partida", "error");

        } else {

            Jugador jugador1 = miMenu.seleccionarJugadoresPartida("Primer", miSistema.getListaJugadores(), null);
            Jugador jugador2 = miMenu.seleccionarJugadoresPartida("Segundo",miSistema.getListaJugadores(), jugador1);
            Partida miPartida = new Partida(jugador1, jugador2, 1, largoMat);

            //Mostrar tablero y datos de los jugadores.
            do {
                //CICLO DE PARTIDA ##################################################
                //Dibujo del Tablero
                miMenu.mostrarMensaje(" *** JUEGO  INVERSIONES ***", "");
                miMenu.mostrarMensaje("(X)Abandonar - (R)Rotar tablero - (E)Ofrecer empate - (Y)Ayuda - (H)Historial", "");
                drawTablero.draw(miPartida.getTablero().getMatrizTablero(), tableroRotado);//Dibujo el tablero
                tableroRotado = false;
                
                //Dibujo de status de la partida.
                drawPartida.datos(miPartida);

                //Se ingresa la jugada.
                jugada = miMenu.ingresarMovimiento();

                //Si un jugador quiere abandonar
                switch(jugada){

                    case "X": //Abandonar
                        boolean abandonar;
                        abandonar = miMenu.confimaMensaje("¿Está seguro que desea abandonar la partida?", "");
                        if(abandonar){
                            miMenu.mostrarMensaje(miPartida.abandonar(),"error");
                        }
                        break;

                    case "R": //Rotar Tablero
                        tableroRotado = true;
                        break;
                        
                    case "E": //Ofrecer empate
                        boolean empate;
                        empate = miMenu.confimaMensaje("¿Está seguro que desea ofrecer un empate?", "");
                        if(empate){
                            miPartida.alternarTurno();
                            drawPartida.datos(miPartida);
                            empate = miMenu.confimaMensaje("¿Desea aceptar empatar la partida?", "");
                            if(empate){
                                miPartida.setEmpate();
                                miMenu.mostrarMensaje("La partida terminó empatada!!", "");
                            }else{
                                miPartida.alternarTurno();
                                miMenu.mostrarMensaje("Empate rechazado!!!", "error");
                            }
                        }
                        break;
                        
                    case "Y": //Ayuda
                        break;
                        
                    case "H": //Historial
                        break;
                        
                    default: //Si no es una opción especial, entonces es una jugada
                        miPartida.ingresarJugada(jugada);
                        //si la jugada no es válida, se muestra el error y vuelve al "do" sin cambiar el turno
                }
                
                //Si nadie Abandona o no hay ganador vuelve al "do"
            } while (!miPartida.terminoPartida());

            //FIN PARTIDA #############################################################
            if (miPartida.terminoPartida()) {
                
                //Si hay ganador
                if (miPartida.getEstado() !=3){
                    System.out.println("\n");
                    miMenu.showCabeceraMenu(true, "EL GANADOR ES: " + (miPartida.getGanador().getAlias()));
                    System.out.println("\n");
                    
                //Si es empate
                }else{
                    System.out.println("\n");
                    miMenu.showCabeceraMenu(true, "PARTIDA EMPATADA !!!");
                    System.out.println("\n");
                }
            }
        }
        /*
         Fin Partida retorno menú principal
         */

    }

}
