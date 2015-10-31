/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*comiplador sabrosongo
 */
package compiladorruby;

import java.io.IOException;

/**
 *
 * @author Jonathan
 */


public class CompiladorRuby {
    static lexico lexico;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        lexico lexicos =new lexico();
        //ImprimirListaNodos();
        lexicos.p=lexicos.cabeza;
        while(lexicos.p.sig!=null){
            System.out.println("Imprimir nodo Lexema:"+lexicos.p.lexema+" Token: "+lexicos.p.token+"  Renglon: "+lexicos.p.renglon);
            lexicos.p=lexicos.p.sig;
           
        }
        if (!lexicos.error_encontrado) {
            System.out.println("Lexico terminado");
            sintaxis sintaxis = new sintaxis(lexicos.cabeza);
            
        }
//        if(!lexicos.error_encontrado){
//            System.out.println("Semantico");
//            Semantico semantico= new Semantico(lexicos.cabeza);
//        }
//        
        
        
        
        //pruba de almacenar Lista
        
            
//            if (lexico.error_encontrado=false) {
//            System.out.println("ANALISIS LEXICO TERMINADO");
//            
//        }
//        else{
//           System.out.println("LEXICO NO TERMINADO");
//        }
//       
    } 
    
    
}
