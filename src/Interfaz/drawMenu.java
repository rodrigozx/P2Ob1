/**
 * ***************************************************
 * Clase: drawMenu
 *
 * @author Rodrigo Blanco - 151251 - Programación II
 * *************************************************
 */
package Interfaz;

import Dominio.Jugador;
import Dominio.Tablero;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class drawMenu {

    Scanner teclado = new Scanner(System.in);
    Scanner tecladoS = new Scanner(System.in);

    //Definición de variables
    private int menuNav; //Referencia el menú seleccionado
    private boolean menuEnable; //Controla Si el menú puede mostrarse o no.
    /*
     Importo las definiciones de los colores de la clase Color.
     */
    private Color color;
    String colorBlue = color.getColor("AZULFDOBLANCO"); //Azul y fondo blanco
    String colorRed = color.getColor("ROJOFDOBLANCO");//Rojo y fondo blanco
    String colorGreen = color.getColor("VERDEFDOBLANCO");//Rojo y fondo blanco
    String colorBlack = color.getColor("NEGROFDOBLANCO");//Negro y fondo blanco
    String colorMag = color.getColor("MAGFDOBLANCO");//Negro y fondo blanco
    String resetColor = color.getColor("SINFORMATO");//Sin formato ni color

    //Defino Constructores
    //Constructor sin parametros
    public drawMenu() {
        this.setMenuNav(00);//Menú princiapl por defecto
        this.setMenuEnable(true);
    }

    //Constructor con parametros
    public drawMenu(int unMenuNav, boolean isEnable) {
        this.setMenuNav(unMenuNav);
        this.setMenuEnable(isEnable);
    }

    public void setMenuNav(int unMenuNav) {
        unMenuNav = menuNav;
    }

    public int getMenuBrousing() {
        return menuNav;
    }

    public void setMenuEnable(boolean unMenuEnable) {
        unMenuEnable = menuEnable;
    }

    public boolean getMenuEnable() {
        return menuEnable;
    }

    //Este metodo repite el menú en caso de que la opción no sea valida
    //Parametros:
    //a-Resultado del metodo que verifica que la opción ingresada
    //este en el rango
    //b-Inidica que menú deseamos repetir
    public void repetirMenu(boolean inOpcionOk, int menuNav, String banner) {
        if (inOpcionOk = false) {
            mostrarMensaje("¡¡¡ La opción ingresada es INCORRECTA !!!", "error");
            System.out.println(banner);

        }
    }

    //Metodo lectura de enteros
    public int leerInt(String texto) {
        int retorno = Integer.MIN_VALUE;
        int flag = 0;
        while (flag == 0) {
            try {
                System.out.print(colorBlack + texto + resetColor);
                retorno = teclado.nextInt();
                flag = 1;
            } catch (Exception e) {
                System.out.println(colorRed + "Debe ingresar un dato numérico." + resetColor);
                teclado.next();
            }
        }
        return retorno;
    }

    //Metodo lectura de enteros
    public String leerString() {
        String retorno;
        retorno = tecladoS.nextLine().replaceAll("\\s+", "");
        while (retorno.isEmpty()) {

            System.out.println(colorRed + "Debe ingresar un dato." + resetColor);
            retorno = tecladoS.nextLine().replaceAll("\\s+", "");
        }
        return retorno;
    }

    public void showLogo() {

        System.out.println(""
                + "\n" + colorBlue + "#################################################################################################"
                + "\n" + colorBlue + "##                                                                                             ##"
                + "\n" + colorBlue + "##                                   I N V E R S I O N E S                                     ##"
                + "\n" + colorBlue + "##                                                                                             ##"
                + "\n" + colorBlue + "#################################################################################################"
                + "\n" + colorBlue + "##                          by Rodrigo Blanco(151251) - Set/2017 - v.1.0.0                     ##"
                + "\n" + colorBlue + "#################################################################################################"
                + "\n" + resetColor + "");
    }

    public void showSaludo() {
        //Saludo al Salir del Juego.
        System.out.println(""
                + "\n" + colorBlue + "    #############################################################################"
                + "\n" + colorBlue + "   #" + colorMag + "                      ES UNA LASTIMA QUE TE VAYAS!!!                         #"
                + "\n" + colorBlue + "   ###############################################################################"
                + "\n" + colorBlue + " ##                                                                               ##"
                + "\n" + colorBlue + "##" + colorGreen + "            #####    #######   #######  #######  ###   #######    ###### " + colorBlue + "        ##"
                + "\n" + colorBlue + "##" + colorGreen + "          ###   ##   ##   ##   ##   ##  ###      ###   ##   ##   ##      " + colorBlue + "        ##"
                + "\n" + colorBlue + "##" + colorGreen + "          ###        ## ##     ##   ##  ###            ##   ##    ###    " + colorBlue + "        ##"
                + "\n" + colorBlue + "##" + colorGreen + "          ###  ###   ######    #######  ###      ###   #######      ###  " + colorBlue + "        ##"
                + "\n" + colorBlue + "##" + colorGreen + "          ###   ##   ##   ##   ##   ##  ###      ###   ##   ##        ## " + colorBlue + "        ##"
                + "\n" + colorBlue + "##" + colorGreen + "           #######   ##   ##   ##   ##  #######  ###   ##   ##   ######  " + colorBlue + "        ##"
                + "\n" + colorBlue + " ##                                                                               ##"
                + "\n" + colorBlue + "   ###############################################################################"
                + "\n" + colorBlue + "   #" + colorMag + "                              HASTA LA PROXIMA :D                            #"
                + "\n" + colorBlue + "    #############################################################################"
                + "\n" + resetColor + "");
    }

    //Cabecera Menu.
    public void showCabeceraMenu(boolean isEnable, String elTitulo) {

        //Color del título
        if (isEnable == true) {
            mostrarMensaje(elTitulo, colorBlue);
        }
    }

    public void mostrarMensaje(String mensaje, String msjColor) {

        //Centrar mensaje
        mensaje = centrarTituloMenu(mensaje, 62);
        mensaje = mensaje.replaceAll("#", " ");

        switch (msjColor) {
            case "error":
                msjColor = colorRed;
                ;
                break;
            case "":
                msjColor = colorBlue;
                break;
            case "correcto":
                msjColor = colorGreen;
                break;
            case "negrita":
                msjColor = colorBlack;
                break;
            default:
                msjColor = colorBlue;
        }

        System.out.print(""
                + "\n" + msjColor + ""
                + "\n" + msjColor + "##################################################################"
                + "\n" + msjColor + "##" + mensaje + msjColor + "##"
                + "\n" + msjColor + "##################################################################");
    }

    public int showMenuPrincipal(int cantOpciones) {
        //Se define la cantidad de opciones que tiene el menú.

        String cabezal = "MENU PRINCIPAL";
        ArrayList<String> listaOpciones = new ArrayList();
        int opcSelect;

        listaOpciones.add("Salir del Juego");//0 
        listaOpciones.add("Registro de Jugador"); //1
        listaOpciones.add("Jugar");//2
        listaOpciones.add("Ranking de Jugadores");//3

        //El último parámetro muestra o no la opción de volver.
        opcSelect = menuOpciones(cabezal, listaOpciones, true);

        return opcSelect;
    }

    /*
     Registrar Jugador
     */
    //El metodo recibe el arraylist de Jugadores para validar el alias.
    public String registrarJugadorAlias(ArrayList<Jugador> unaListaJugadores) {
        String alias;
        boolean resultado = false;

        do {
            resultado = false;
            System.out.print("\n" + colorBlack + "Ingrese un Alias:");
            alias = leerString();
            for (int i = 0; i < unaListaJugadores.size(); i++) {
                if (unaListaJugadores.get(i).getAlias().equalsIgnoreCase(alias) == true) {
                    resultado = true;
                    mostrarMensaje(alias + " ya está registrado!!!", "error");
                }
            }
        } while (resultado == true);
        return alias;
    }

    //El metodo recibe el arraylist de Jugadores para validar el alias.
    public String registrarJugadorNombre() {
        String nombre;
        do {
            System.out.print(colorBlack + "Ingrese su Nombre:");
            nombre = tecladoS.nextLine();;
        } while (nombre == null);
        return nombre;
    }

    public int registrarJugadorEdad() {
        int edad = 0;
        boolean resultado = false;
        do {
            edad = this.leerInt("Ingrese su Edad: ");
            if (edad >= 1 && edad <= 100) {
                resultado = true;
                System.out.println("");//salto de línea
            } else {
                System.out.println(colorRed + "Edades permitidas de 1 a 100 !!!");
            }
        } while (resultado != true);
        return edad;
    }

    public void drawMenuRanking(ArrayList<Jugador> unaListaJugadores) {
        //Ranking de Jugadores

        System.out.println(""
                + "\n" + colorBlue + "   #################################################################################"
                + "\n" + colorBlue + " ##                                                                                 ##"
                + "\n" + colorBlue + "##" + colorGreen + "         #######   #######   ##    ##   ##   ##  ###   ##    ##    ######   " + colorBlue + "       ##"
                + "\n" + colorBlue + "##" + colorGreen + "         ##   ##   ##   ##   ###   ##   ##  ##   ###   ###   ##   ###       " + colorBlue + "       ##"
                + "\n" + colorBlue + "##" + colorGreen + "         ## ##     ##   ##   ## #  ##   ## ##          ## #  ##   ###       " + colorBlue + "       ##"
                + "\n" + colorBlue + "##" + colorGreen + "         ######    #######   ##  # ##   #####    ###   ##  # ##   ###  ###  " + colorBlue + "       ##"
                + "\n" + colorBlue + "##" + colorGreen + "         ##   ##   ##   ##   ##   ###   ##  ##   ###   ##   ###   ###   ##  " + colorBlue + "       ##"
                + "\n" + colorBlue + "##" + colorGreen + "         ##   ##   ##   ##   ##    ##   ##   ##  ###   ##    ##    #######  " + colorBlue + "       ##"
                + "\n" + colorBlue + " ##                                                                                 ##"
                + "\n" + colorBlue + "   #################################################################################"
                + resetColor + "");

        if (unaListaJugadores.isEmpty()) {
            mostrarMensaje("No hay jugadores ingresados en el sistema !!!", "error");

        } else {
            mostrarListaJugadores(unaListaJugadores);
        }

    }

    public void mostrarListaJugadores(ArrayList<Jugador> unaListaJugadores) {

        String pos;
        String linea;
        int espacios;

        mostrarMensaje(padLString(" Posición", 3) //12 
                + padLString(" Alias", 10)//21
                + padLString(" Ganadas", 2) //10
                + padLString(" Perdidas", 2)//11
                + padLString(" Empates", 2)//11
                , "");
        System.out.println("");

        for (int i = 0; i < unaListaJugadores.size(); i++) {

            pos = "-    " + Integer.toString(i + 1);

            linea = (""
                    /*Posición*/
                    + padLStringGuion(pos, 16 - pos.length())
                    
                    /*Alias*/
                    + padLStringGuion(unaListaJugadores.get(i).getAlias(), 20 - unaListaJugadores.get(i).getAlias().length())
                    
                    /*Ganadas*/
                    + padLStringGuion(Integer.toString(unaListaJugadores.get(i).getPartidas()[0]), (11 - Integer.toString(unaListaJugadores.get(i).getPartidas()[0]).length()))
                    /*Perdidas*/
                    + padLStringGuion(Integer.toString(unaListaJugadores.get(i).getPartidas()[1]), (11 - Integer.toString(unaListaJugadores.get(i).getPartidas()[1]).length()))
                    /*Empatadas*/
                    + padLStringGuion(Integer.toString(unaListaJugadores.get(i).getPartidas()[2]), (8 - Integer.toString(unaListaJugadores.get(i).getPartidas()[2]).length()))
                    );
            
            espacios = (62 - linea.length() + 2);
            linea = padLString(linea, espacios);
            linea = (linea.substring(1, linea.length()));
            linea = (colorBlue + "##" + linea + "#" + resetColor);
            System.out.println(linea);
        }

        linea = (colorBlue + "##                                                              ##"
                + "\n" + colorBlue + "##################################################################"
                + colorBlack);
        System.out.print(linea);
    }

    /*
     Seleccionar Jugadores para Partida:
     -----------------------------------
     Si no tengo al cantidad minima de jugadores en el sismtea da error y devuelve jugador=null
     si tengo el minimo o mas retorno el jugador seleccionado.
     */
    public Jugador seleccionarJugadoresPartida(String turno, ArrayList unaListaJugadores, Jugador otroJugador) {
        int numJugador = Integer.MIN_VALUE;//Numero del Jugador en la lista
        Jugador unJugador = null;

        mostrarListaJugadores(unaListaJugadores);

        do {
            mostrarMensaje("Digite el número del " + turno + " Jugador:", colorBlack);
            numJugador = this.leerInt("\nOpción: ");
            unJugador = (Jugador) unaListaJugadores.get(numJugador - 1);

            if (otroJugador != null && unJugador.equals(otroJugador)) {
                System.out.println("El Jugador " + otroJugador.getAlias() + "ya fue elegido!!!");
                numJugador = 0;
            }

        } while ((numJugador < 1) || numJugador > unaListaJugadores.size());

        return unJugador;
    }

    /*
     Seleccionar Jugador:
     -----------------------------------
     Selección de jugador del sistema;
     */
    public Jugador seleccionarJugador(ArrayList<Jugador> unaListaJugadores) {
        Jugador unJugador;
        int numJugador;
        mostrarListaJugadores(unaListaJugadores);
        boolean error = false;
        String mensaje;
        mostrarMensaje("Digite el número del Jugador a eliminar: ", colorBlack);
        mensaje = "\n" + colorBlack + "Opción: ";

        do {
            error = false;
            numJugador = this.leerInt(mensaje);
            if (numJugador < 1 || numJugador > unaListaJugadores.size()) {
                mostrarMensaje("Opción inválida", "error");
                error = true;
            }
        } while (error);

        unJugador = (Jugador) unaListaJugadores.get(numJugador - 1);
        return unJugador;
    }

    /*
     Método para pedir datos de jugada.
     */
    public String ingresarMovimiento() {
        String retorno = null;
        System.out.println(colorBlue + "Ingrese su Jugada:" + resetColor);
        retorno = this.tecladoS.nextLine();
        return retorno;
    }

    public String centrarTituloMenu(String elTitulo, int cantCaracteres) {

        elTitulo = elTitulo.trim();

        int largo;
        String tituloCentrado = elTitulo;

        largo = elTitulo.length();
        int faltaCaracteres = (cantCaracteres - largo);
        faltaCaracteres = faltaCaracteres / 2;
        if (largo < cantCaracteres) {

            tituloCentrado = padLString(tituloCentrado, faltaCaracteres);
            tituloCentrado = padRString(tituloCentrado, faltaCaracteres);
        }

        return tituloCentrado.trim();
    }

    public String padRString(String elTitulo, int espacios) {

        for (int i = 1; i < espacios; i++) {
            //termino uno antes por el punto
            elTitulo = " ".concat(elTitulo);
        }
        elTitulo = "#".concat(elTitulo);
        return elTitulo;
    }

    public String padLStringGuion(String linea, int espacios) {

        for (int i = 1; i < espacios; i++) {
            //termino uno antes por el punto
            linea = linea.concat(" ");
        }
        return linea;
    }

    public String padLString(String elTitulo, int espacios) {

        for (int i = 1; i < espacios; i++) {
            //termino uno antes por el punto
            elTitulo = elTitulo.concat(" ");
        }
        elTitulo = elTitulo.concat("#");
        return elTitulo;
    }

    public boolean confimaMensaje(String mensaje, String msjColor) {
        boolean confirmacion;
        int respuesta;
        String mensajeOpciones;
        showCabeceraMenu(true, mensaje);

        do {
            mensajeOpciones = (""
                    + "\n" + colorBlue + "##                                                              ##"
                    + "\n" + colorBlue + "##  1- Confirmar                                                ##"
                    + "\n" + colorBlue + "##  2- Cancelar                                                 ##"
                    + "\n" + colorBlue + "##                                                              ##"
                    + "\n" + colorBlue + "##################################################################"
                    + "\n" + colorBlack + "Opción: ");

            respuesta = leerInt(mensajeOpciones);

        } while (respuesta != 1 && respuesta != 2);

        confirmacion = (respuesta == 1);// si es 1 es true
        return confirmacion;
    }

    public int menuOpciones(String cabezal, ArrayList<String> opciones, boolean salir) {

        int opcion;
        boolean error = false;
        String lineaOpcion;
        String mensaje;
        int cantOpciones = opciones.size();
        int espacios;

        //Muestro el cabezal
        if (cabezal.length() > 0) {
            showCabeceraMenu(true, cabezal);
        }

        //Se crea el texto con los datos del ArrayList
        mensaje = (""
                + "\n" + colorBlue + "##                                                              ##");

        for (int i = 1; i < cantOpciones; i++) {
            lineaOpcion = " " + Integer.toString(i) + " - " + opciones.get(i).toString();
            espacios = (62 - lineaOpcion.length() + 1);
            lineaOpcion = padLString(lineaOpcion, espacios);
            mensaje += ("\n" + colorBlue + "##" + lineaOpcion + "#");
        }

        if (salir) {
            mensaje += ("\n" + colorBlue + "##                                                              ##");
            lineaOpcion = " " + Integer.toString(0) + " - " + opciones.get(0).toString();
            espacios = (62 - (lineaOpcion.length()) + 1);
            lineaOpcion = padLString(lineaOpcion, espacios);
            mensaje += ("\n" + colorBlue + "##" + lineaOpcion + "#");
        }
        mensaje += ("\n" + colorBlue + "##                                                              ##");
        mensaje += ("\n" + colorBlue + "##################################################################"
                + "\n" + colorBlack + "Opción: ");

        do {
            error = false;
            opcion = this.leerInt(mensaje);
            if (opcion < 1 || opcion > cantOpciones - 1) {
                if (salir && opcion == 0) {
                    //salir
                } else {
                    mostrarMensaje("Opción inválida", "error");
                    error = true;
                }
            }
        } while (error);
        return opcion;
    }

}
