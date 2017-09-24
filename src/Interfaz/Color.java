
/* ******************************************************
 * Clase: Color
 *
 * @author Rodrigo Blanco - nro. 151251 - Programación II
 * ******************************************************
 */
package Interfaz;

public class Color {

    public static String getColor(String nombreColor) {

        String impColor;

        switch (nombreColor) {
            case "NEGRO":
                impColor = "[0;00m";
                break;
            case "ROJO":
                impColor = "[0;31m";
                break; //31
            case "VERDE":
                impColor = "[0;32m";
                break;  //32
            case "AMARILO":
                impColor = "[0;33m";
                break; //33 
            case "AZUL":
                impColor = "[0;34m";
                break; //34
            case "MAGENTA":
                impColor = "[0;35m";
                break;//35
            case "CYAN":
                impColor = "[0;36m";
                break;//36
            case "BLANCO":
                impColor = "[0;37m";
                break;//37
                
            //FONDOS COLOREADOS
            case "NEGROFDO":
                impColor = "[37;40m";
                break;//40 
            case "ROJOFDO":
                impColor = "[0;41m";
                break;//41
            case "VERDEFDO":
                impColor = "[0;42m";
                break; //42
            case "AMARILLOFDO":
                impColor = "[0;43m";
                break;//43 
            case "AZULFDO":
                impColor = "[0;44m";
                break; //44
            case "MAGENTAFDO":
                impColor = "[0;45m";
                break; //45
            case "CYANFDO":
                impColor = "[0;46m";
                break;//46
            case "BLANCOFDO":
                impColor = "[0;47m";
                break;//47
                
            //FORMATOS DE FUENTES
            case "NEGRITA":
                impColor = "[1";
                break; //1
            case "NORMAL":
                impColor = "[21";
                break; //21
            case "SUBRAYADO":
                impColor = "[4";
                break; //4
            case "NOSUBRAYADO":
                impColor = "";
                break;//24
            
            //FUENTE Y FONDO COLOREADOS
            case "AZULFDOBLANCO":
                impColor = "[34;47m";
                break;//; //Azul y fondo blanco
            case "ROJOFDOBLANCO":
                impColor = "[31;47m";
                break;//; //Rojo y fondo blanco
            case "NEGROFDOBLANCO":
                impColor = "[00;47m";
                break;//; //Negrozul y fondo blanco                
            case "VERDEFDOBLANCO":
                impColor = "[32;47m";
                break;//; //Rojo y fondo blanco
            case "MAGFDOBLANCO":                
                impColor = "[45;47m";
                break;//; //Rojo y fondo blanco                
            //SIN FORMATOS NI COLORES
            case "SINFORMATO": 
                impColor =  "[00;00m";
                break;//0
                
            default:
                throw new IllegalArgumentException("No existe el color: " + nombreColor);
        }
            //Código completo para alterar color
            impColor = (char) 27 + impColor;
            return impColor;
        }
    
}
