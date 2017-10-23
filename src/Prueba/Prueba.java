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

        /*====================== DATOS DE PRUEBA =====================*/
        miSistema.agregarJugador(new Jugador("Rolo  ", "Rodrigo ", 33, 1, 1, 1));
        miSistema.agregarJugador(new Jugador("Viky  ", "Viky", 29, 2, 1, 0));
        miSistema.agregarJugador(new Jugador("Pepe  ", "Pepe", 29, 0, 1, 0));
        miSistema.agregarJugador(new Jugador("Pepo  ", "Pepo", 29, 0, 2, 2));        
        miSistema.agregarJugador(new Jugador("Chacho", "Chacho", 29, 3, 1, 0));
        miSistema.agregarJugador(new Jugador("Checho", "Checho", 29, 2, 2, 1));
        miSistema.agregarJugador(new Jugador("Pepete", "Pepete", 29, 0, 0, 1));
        miSistema.agregarJugador(new Jugador("Pancho", "Pancho", 29, 1, 5, 3));
        miSistema.agregarJugador(new Jugador("Pancha", "Pancha", 29, 2, 1, 1));        
        
        /*====================== DATOS DE PRUEBA =====================*/
        
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

        int largoMat;//Tamaño de la matriz
        boolean[] especial = new boolean[5]; /*  (0)Abandonar - 1)Rotar tablero - (2)Ofrecer empate - (3)Ayuda - (4)Historial */
               
        if (!miSistema.numJugadoresMinimos()) {
            //si existe menos de 2 jugadores registrados
            miMenu.mostrarMensaje("Error! la cantidad de jugadores registrados", "error");
            miMenu.mostrarMensaje("Es de al menos dos para este tipo de partida", "error");

        } else {

            //Selección de tablero
            largoMat = seleccionTablero(miMenu);

            //Selección de jugadores y arranque Partida. ******************************************
            Jugador jugador1 = miMenu.seleccionarJugadoresPartida("Primer", miSistema.getListaJugadores(), null);
            Jugador jugador2 = miMenu.seleccionarJugadoresPartida("Segundo",miSistema.getListaJugadores(), jugador1);
            Partida miPartida = new Partida(jugador1, jugador2, 1, largoMat);

            
            //CICLO DE PARTIDA ######################################################
            do {
                //Dibujo del Tablero
                miMenu.mostrarMensaje(" *** JUEGO  INVERSIONES ***", "");
                miMenu.mostrarMensaje("(X)Abandonar - (R)Rotar tablero - (E)Ofrecer empate - (Y)Ayuda - (H)Historial", "");
                drawTablero.draw(miPartida.getTablero().getMatrizTablero(), especial[1]);//Dibujo el tablero
                especial[1] = false;//Seteo el tablero normal nuevamente
                
                //Dibujo de status de la partida.
                drawPartida.datos(miPartida);

                //Se ingresa la jugada, si existe algo especial para motrar se devuelve.
                especial = pedirJugada(miSistema, miPartida, miMenu);

                //Si nadie Abandona, no hay empate o no hay ganador vuelve al "do"
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
    
     public static boolean[] pedirJugada(Sistema miSistema, Partida laPartida, drawMenu elMenu) {
        
        String jugada;
        boolean[] especial;  /*  (0)Abandonar - (1)Rotar tablero - (2)Ofrecer empate - (3)Ayuda - (4)Historial */

        jugada = elMenu.ingresarMovimiento();
        especial = validaEntradaJugada(jugada, laPartida, elMenu);
        
        return especial;
    }

    //Primera Varlidación de la entrada de la jugada.
    public static boolean[] validaEntradaJugada(String jugada, Partida laPartida, drawMenu elMenu) {
        
        boolean[] especial = new boolean[5];/*  (0)Abandonar - (1)Rotar tablero - (2)Ofrecer empate - (3)Ayuda - (4)Historial */
        
        //Opciones especiales válidas
        String[] listaOpcionesEspeciales = {"X","R","E","Y","H"};
        /*
        Tipos de entradas:
            - movimiento de fichas:
                                    A1 A5 //ejemplo
            - jugadas especiales:   
                                    (X)Abandonar 
                                    (R)Rotar tablero
                                    (E)Ofrecer empate
                                    (Y)Ayuda
                                    (H)Historial        
        */
        boolean opcionEspecialValida;
        String mensaje;
        //Si la jugada esta es de cualquier largo que no sea 1 o 5
        if ( (jugada.trim().length() != 5) && (jugada.trim().length() != 1) ) {
            mensaje = "Largo de jugada inválido";
            elMenu.mostrarMensaje(mensaje, "error");
        
        } else {

            //Verifico si es una opción especial
            if (jugada.trim().length() == 1){
                opcionEspecialValida = Arrays.asList(listaOpcionesEspeciales).contains(jugada);

                if (opcionEspecialValida){

                    //Jugadas especiales
                    switch(jugada){

                        case "X": //Abandonar
                            mensaje = "¿Está seguro que desea abandonar la partida?";
                            especial[0] = elMenu.confimaMensaje(mensaje, "");
                            
                            if(especial[0]){
                                mensaje = laPartida.abandonar();
                                elMenu.mostrarMensaje(mensaje,"error");
                            }
                            break;

                        case "R": //Rotar Tablero
                            especial[1] = true; //Seteo mostrarlo rotado
                            break;

                        case "E": //Ofrecer Empate
                            mensaje = "¿Está seguro que desea ofrecer un empate?";
                            especial[2] = elMenu.confimaMensaje(mensaje, "");
                            
                            //Si confirmó ofrecer empate
                            if(especial[2]){
                                laPartida.alternarTurno();
                                drawPartida.datos(laPartida);
                                mensaje = "¿Desea aceptar empatar la partida?";
                                especial[2] = elMenu.confimaMensaje(mensaje, "");
                                
                                //Si aceptó el empate
                                if(especial[2]){
                                    laPartida.setEmpate();
                                    mensaje = "La partida terminó empatada!!";
                                    elMenu.mostrarMensaje(mensaje, "");
                                }else{
                                    laPartida.alternarTurno();
                                    mensaje = "Empate rechazado!!!";
                                    elMenu.mostrarMensaje(mensaje, "error");
                                }
                            }
                            break;

                        case "Y": //Ayuda
                            especial[3] = true;
                            if (especial[3]){
                                elMenu.mostrarMensaje("PIDIO AYUDA","error");
                            }
                            break;

                        case "H": //Historial
                            especial[4] = true;
                            if (especial[4]){
                                drawPartida.mostrarHistorial(laPartida);
                            }
                            break;

                        default: //Si no es una opción especial, entonces es una jugada no válida
                            //No debería entrar nunca en ésta opción
                            elMenu.mostrarMensaje("Jugada no válida!!!", "error");
                    }
                }else{//Si no es una opción especial, entonces es una jugada no válida
                     elMenu.mostrarMensaje("Jugada no válida!!!", "error");
                }

            }else{

                if (jugada.trim().length() == 5){
                    mensaje = laPartida.ingresarJugada(jugada);
                    if(!mensaje.equals("Ok")){
                        elMenu.mostrarMensaje(mensaje, "error");
                    }
                }else{
                    //No debería entrar nunca en ésta opción
                    elMenu.mostrarMensaje("Jugada no válida!!!", "error");
                }
            }
        }
        return especial;
    }
    
    public static int seleccionTablero(drawMenu elMenu){//Selección de tablero

        int largoMat;
        String cabezal = "Por favor seleccione un tamaño de tablero";
        ArrayList<String> listaOpciones = new ArrayList();
        listaOpciones.add("Volver");//0 
        listaOpciones.add("Tablero 3x3");//1 
        listaOpciones.add("Tablero 5x5");//2

        //El último parámetro muestra o no la opción de volver.
        largoMat = elMenu.menuOpciones(cabezal, listaOpciones, true);
        if (largoMat == 1){
            largoMat = 3;
        }else{
            largoMat = 5;
        }
        return largoMat;
    }

}
