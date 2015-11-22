/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorruby;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author Jonathan
 */
class lexico {
    nodo cabeza=null, p;
    int estado= 0, columna, ValorMT;
    char caracter;
    String lexema = "";
    int numRenglon = 0;
    String archivo="C:\\Users\\Jonathan\\Documents\\Sem 6  compi\\compi.txt";//aqui va la direcccion del archivo   
    boolean error_encontrado=false;

    //aqui va la matriz de la tabla.
    int matriz[][]={
        //L   ,   D,   +,   -,   *,   /,   =,   #,   $,   (,   ),   >,   <,   .,   &,   |,   !,   ",   [,   ],   ;, eb,  rt,  nl, eof, tab,  oc
        //0   ,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,  16,  17,  18,  19,  20,  21,  22,  23,  24,  25, 26
   /*0*/ {   1,   2, 103, 104, 105, 106,   7, 118, 119, 116, 117,   5,   6, 107,  10,  11,   9,   8, 114, 115, 124,   0,   0,   0,   0,   0, 505},
   /*1*/ {   1,   1, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100},
   /*2*/ { 101,   2, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,   3, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101},
   /*3*/ { 500,   4, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500},
   /*4*/ { 102,   4, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102},
   /*5*/ { 109, 109, 109, 109, 109, 109, 112, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109},
   /*6*/ { 110, 110, 110, 110, 110, 110, 111, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110, 110},
   /*7*/ { 120, 120, 120, 120, 120, 120, 113, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120},
   /*8*/ {   8,   8,   8,   8,   8,   8,   8,   8,   8,   8,   8,   8,   8,   8,   8,   8,   8, 108,   8,   8, 501,   8,   8,   8, 501,   8,   8},
   /*9*/ { 502, 502, 502, 502, 502, 502, 123, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502},
  /*10*/ { 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 121, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503},
  /*11*/ { 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 122, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504},
};


    
    //la matriz de errores.
    String Errores[][]={
        
        {"Error: Falta Digitos"       , "500"},
        {"Error: Falta  Doble coma"   , "501"},
        {"Error: Falta     ="         , "502"},
        {"Error: Falta     &"         , "503"},
        {"Error: Falta     |"         , "504"},
        {"Error: Falta    ID"         , "505"}
    };
    //palabras Reservadas
    String PalReservadas [][]={
        {"prints",   "200"},
        {"do"  ,   "201"},
        {"for",    "202"},
        {"if",     "203"},
        {"while",  "204"},
        {"end",    "206"},
        {"true"  , "207"},
        {"false",  "208"},
        {"then",   "209"},
        {"and",    "210"},
        {"or",     "211"},
        {"else",   "212"},
        {"puts",   "213"}    
    };
    
    
    public lexico() throws FileNotFoundException, IOException{
        FileReader file = new FileReader(archivo);
        BufferedReader buffer= new BufferedReader(file);
        
        boolean eof =false;
        String linea="";
        
        while (!eof && linea != null){
            linea=buffer.readLine();
            numRenglon++;
            if(linea==null){
                InsertarNodo();
            }else{
           //inicia la busqueda aqui
            for (int i = 0; i < linea.length(); i++) {
                //lee linea por linea
                caracter=linea.charAt(i);
                if(Character.isLetter(caracter)){
                    columna=0; 
                }
                else if(Character.isDigit(caracter)){
                    columna=1;
                }
                else{
                    //aqui se inicia el caracter
                    switch (caracter){
                        case '+': columna=2;
                            break;
                        case '-': columna=3;
                            break;
                        case '*': columna=4;
                            break;
                        case '/': columna=5;
                            break;
                        case '=': columna=6;
                            break;
                        case '#': columna=7;
                            break;
                        case '$': columna=8;
                            break;
                        case '(': columna=9;
                            break;
                        case ')': columna=10;
                            break;
                        case '>': columna=11;
                            break;
                        case '<': columna=12;
                            break;
                        case '.': columna=13;
                            break;
                        case '&': columna=14;
                            break;
                        case '|': columna=15;
                            break;
                        case '!': columna=16;
                            break;
                        case '"': columna=17;
                            break;
                        case '[': columna=18;
                            break;
                        case ']': columna=19;
                            break;
                        case ';': columna=20;
                            break;
                        case ' ': columna=21;//espacio blanco
                            break;
                        case 13: columna=22;//Retono de carro
                            break;
                        case 10: columna=23;//Nueva Linea
                            break;
                        case 3: columna=24;//eof
                            break;
                        case 9: columna=25;//tab
                            break;
                        default : columna=26; // aqui va tu longitud del oc
                            break;
                            
                    }//switch
                    
                }//else
                ValorMT = matriz[estado][columna];
                if(ValorMT<100){
                    estado=ValorMT;
                   // lexema = lexema+ caracter;
                    if(estado==0){
                        lexema="";
                    }else{
                        lexema=lexema + caracter;
                    }
                    
                }else if (ValorMT >= 100 && ValorMT <500) {//estado final
                    if(ValorMT==100){
                      ValidarPalabraReservada();   
                    }else
                    if(ValorMT==100 ||ValorMT==101||ValorMT==102||ValorMT==109||ValorMT==110||ValorMT==120||ValorMT==112||ValorMT==111){
                        i--;
                    }else{
                        lexema=lexema+caracter;    
                    }
                                   
                  // System.out.println("Imprimir nodo: "+lexema+" "+ValorMT+" "+numRenglon+""+i);
                    //
                    
                    InsertarNodo();
                    estado=0;   
                    lexema="";
                }else{//estados de errores
                    //System.out.println("Error:  "+ValorMT);
                  ImprimirError();
                  break;
                }
            }//for
            if(error_encontrado){
                break;
            }
            }//else
        }//while
    
    }

    private void ImprimirError() {
        for (int i = 0; i < Errores.length; i++) {
            if(ValorMT==Integer.valueOf(Errores[i][1])){
                System.out.println(" "+Errores[i][0]+"  "+ValorMT+"  "+numRenglon);
                error_encontrado=true;
            }    
        }
    }

    private void ValidarPalabraReservada() {
        for (int i = 0; i < PalReservadas.length; i++) {
            if(lexema.equals(PalReservadas[i][0])){
                ValorMT=Integer.valueOf(PalReservadas[i][1]);
            }
        }
    }

    private void InsertarNodo() {
        nodo nodo=new nodo(lexema,ValorMT,numRenglon);
        if(cabeza==null){
            cabeza=nodo;
            p=cabeza;
        }else{
            p.sig=nodo;
            p=nodo;
        }
        
    }
    
}
