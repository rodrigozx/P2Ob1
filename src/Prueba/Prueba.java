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

        
//        int [][] mat = {{22,21,21,0,21},{0,0,12,0,0},{12,12,0,12,21},{0,0,22,11,22},{0,11,22,0,0}};
//        int [][] mat = {{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}};
//        int [][] mat = {{2,2,2,2,2},{2,2,2,2,2},{2,2,2,2,2},{2,2,2,2,2},{2,2,2,2,2}};
//        int [][] mat = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};

        int [][] mat = {{12,11,12},{0,0,0},{21,22,21}};
//        int [][] mat = {{1,1,1},{1,1,1},{1,1,1}};
//        int [][] mat = {{2,2,2},{2,2,2},{2,2,2}};
//        int [][] mat = {{0,0,0},{0,0,0},{0,0,0}};


        drawTablero.draw(mat,false);
        System.out.println("");
        drawTablero.draw(mat,true); 
        
//        //Se crea el menu a mostrar
//        showMenu elMenu = new showMenu(0, true);
//
//        int dato = 0; //Por defecto se ejecuta el menú princiapl.
//
//        //Carga del Menu Principal
//        elMenu.showLogo();
//        boolean salir = false;
//
//        Jugador jugador1 = new Jugador("Rolo", "Rodrigo", 33);
//        Jugador jugador2 = new Jugador("Viky", "Virginia", 29);        
//        miSistema.agregarJugador(jugador1);
//        miSistema.agregarJugador(jugador2);
//        
//        do {
//            dato = elMenu.showMenuPrincipal(4); //existen 4 opciones
//            switch (dato) {
//
//                //Registro de Jugador **********************************************************************
//                case 1:
//                    seleccionOpcionesRegistroJugador(miSistema, elMenu);
//                    break;
//
//                //Jugar 1 vs 1 **********************************************************************
//                case 2:
//                    iniciarPartida(2, miSistema, elMenu); //Son dos jugadores.
//                    break;
//
//                //Jugar 1 vs CPU **********************************************************************
//                case 3:
//                    iniciarPartida(1, miSistema, elMenu); //Es un sólo jugador.
//                    break;
//
//                //Muestra el Ranking de jugadores *****************************************************
//                case 4:
//                    elMenu.showMenuRanking(miSistema.getListaJugadores());
//                    break;
//
//                //Sale del programa **********************************************************************                    
//                case 0:
//                    elMenu.showSaludo();
//                    salir = true;
//                    break;
//
//                default:
//                //No hace nada
//            }
//        } while (salir != true);
    }
//
//    public static void seleccionOpcionesRegistroJugador(Sistema miSistema, showMenu elMenu) {
//        String cabezal = "OPCIONES DE REGISTRO DE JUGADOR";
//        ArrayList<String> listaOpciones = new ArrayList();
//        int opcSelect;
//
//        listaOpciones.add("Volver");//0 
//        listaOpciones.add("Registrar Nuevo Jugador"); //1
//        listaOpciones.add("Eliminar Jugador");        //2
//
//        //El último parámetro muestra o no la opción de volver.
//        opcSelect = elMenu.menuOpciones(cabezal, listaOpciones, true);
//        switch (opcSelect) {
//            case 1:
//                registroDeJugador(miSistema, elMenu);
//                break;
//            case 2:
//                eliminarJugador(miSistema, elMenu);
//                break;
//            default: //no hace nada y vuelve al menu principal
//        }
//    }
//
//    public static void registroDeJugador(Sistema miSistema, showMenu elMenu) {
//        //Se ejecuta el metodo mostrar Cabecera de Menú.
//        elMenu.showCabeceraMenu(true, "Menú Registrar Jugador");
//
//        //Se crea el objeto Jugador con los parametros.
//        //Luego se agrega al ArrayList de Jugadores
//        Jugador elJugador = new Jugador(elMenu.registrarJugadorAlias(miSistema.getListaJugadores()),
//                elMenu.registrarJugadorNombre(), elMenu.registrarJugadorEdad());
//
//        elMenu.mostrarMensaje("Jugador Registrado!", "");
//        miSistema.agregarJugador((Jugador) elJugador);
//        System.out.println("\n" + elJugador);
//    }
//
//    public static void eliminarJugador(Sistema miSistema, showMenu elMenu) {
//
//        //Se muestra la lista de jugadores y se elimina el seleccionado;
//        Jugador elJugador = elMenu.seleccionarJugador(miSistema.getListaJugadores());
//        miSistema.eliminarJugador((Jugador) elJugador);
//        elMenu.mostrarMensaje("Jugador  " + elJugador.getAlias() + "  eliminado!", "correcto");
//    }
//
//    public static void iniciarPartida(int cantJugadores, Sistema miSistema, showMenu elMenu) {
//
//        //Creo una partida.
//        boolean vsCpu;
//
//        //Veo si es un jugaor sólo
//        vsCpu = cantJugadores != 2;
//
//        //Selección de jugadores y arranque Partida. ******************************************
//        //si existe menos de un jugador registrado
//        if (!miSistema.numJugadoresMinimos() && (vsCpu == false)) {
//            elMenu.mostrarMensaje("Error! la cantidad de jugadores registrados", "error");
//            if (!miSistema.numJugadoresMinimos() && (vsCpu == true)) {
//                elMenu.mostrarMensaje("es de al menos uno para ésta partida", "error");
//            } else {
//                elMenu.mostrarMensaje("es de al menos dos para este tipo de partida", "error");
//            }
//
//        } else {
//            if (miSistema.numJugadoresRegistrados() == 1 && (vsCpu == false)) {
//                elMenu.mostrarMensaje("Error! la cantidad de jugadores registrados", "error");
//                elMenu.mostrarMensaje("es de al menos dos para este tipo de partida", "error");
//            }
//            Jugador jugador1 = elMenu.seleccionarJugadoresPartida("Primer",
//                    miSistema.getListaJugadores(), null);
//            Jugador jugador2 = elMenu.seleccionarJugadoresPartida("Segundo",
//                    miSistema.getListaJugadores(), jugador1);
//            Partida laPartida = new Partida(jugador1, jugador2, 1, vsCpu, 25);
//
//
//            //Mostrar tablero y datos de los jugadores.
//            do {
//                //CICLO DE PARTIDA ##################################################
//                //Dibujo del Tablero
//                elMenu.mostrarMensaje("Tablero - Esquinas ","");                
//                drawTablero.show(laPartida.getTablero().getMatrizTablero());//muestro la estadística
//
//                //Dibujo de status de la partida.
//                showPartida.datos(laPartida);
//
//                //Se ingresa la jugada.
//                elMenu.ingresarMovimiento();
//                pedirJugada(miSistema, laPartida, elMenu);
//
//                //Si un jugador quiere abandonar
//                //Si nadie Abandona o no hay ganador vuelve al "do"
//            } while ((laPartida.terminoPartida() == 0)); //mientra haya jugadores con fichas.
//
//            //FIN PARTIDA #############################################################
//            /*
//             Asignamos el resultado al ranking.
//             */
//            if (laPartida.terminoPartida() == 1) {
//                laPartida.getJugador1().setPartidas(1, 0);
//                laPartida.getJugador2().setPartidas(0, 1);
//                elMenu.showCabeceraMenu(true, "EL GANADOR ES: " + (laPartida.getJugador1().getAlias()));
//            }
//            if (laPartida.terminoPartida() == 2) {
//                laPartida.getJugador2().setPartidas(1, 0);
//                laPartida.getJugador1().setPartidas(0, 1);
//                System.out.println("\n");
//                elMenu.showCabeceraMenu(true, "EL GANADOR ES: " + (laPartida.getJugador2().getAlias()));
//                System.out.println("\n");
//            }
//
//        }
//        /*
//         Fin Partida retorno menú principal
//         */
//
//    }
//    
//    public static void pedirJugada(Sistema miSistema, Partida laPartida, showMenu elMenu){
//        String jugada;
//        jugada = elMenu.ingresarMovimiento();
//    }
}
 
