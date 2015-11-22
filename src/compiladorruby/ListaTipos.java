/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorruby;

/**
 *
 * @author Jonathan
 */
public class ListaTipos {
     public String matriz [] []= { //
        //tipo operador   tipo resultado
        {"int","+","int","int"},
        {"int","-","int","int"},
        {"int","*","int","int"},
        {"int","/","int","float"},
        {"int","==","int","int"},
        {"int","!=","int","int"},
        {"int","<=","int","int"},
        {"int",">=","int","int"},
        {"int","<","int","int"},
        {"int",">","int","int"},
        {"int","=","int","int"},
        
        {"float","+","float","float"},
        {"float","-","float","float"},
        {"float","*","float","float"},
        {"float","/","float","float"},
        {"float","==","float","float"},
        {"float","!=","float","float"},
        {"float","<=","float","float"},
        {"float",">=","float","float"},
        {"float","<","float","float"},
        {"float",">","float","float"},
        {"float","=","float","float"},
        
        
        {"string","+","string","string"},
        {"string","+","char","string"},
        {"char","+","string","string"},
        {"string","=","string","string"},
        
        {"char","+","char","string"},
        {"char","=","char","char"},
        
        {"int","+","float","float"},
        {"int","-","float","float"},
        {"int","*","float","float"},
        {"int","/","float","float"},
        {"int","==","float","float"},
        {"int","!=","float","float"},
        {"int","<=","float","float"},
        {"int",">=","float","float"},
        {"int","<","float","float"},
        {"int",">","float","float"},
        
        
        {"float","+","int","float"},
        {"float","-","int","float"},
        {"float","*","int","float"},
        {"float","/","int","float"},
        {"float","==","int","float"},
        {"float","!=","int","float"},
        {"float","<=","int","float"},
        {"float",">=","int","float"},
        {"float","<","int","float"},
        {"float",">","int","float"},
        {"float","=","int","float"}
        
    };
    
    public String tipoResultante(String tipo1, String operador, String tipo2){
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0].equals(tipo1)) {
                if (matriz[i][1].equals(operador)) {
                    if (matriz[i][2].equals(tipo2)) {
                        return matriz[i][3].toString();
                    }
                }
            }
            
        }
        return "";
    }
    
    public boolean tipoEncontrado(String tipo1, String operador, String tipo2){
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0].equals(tipo1)) {
                if (matriz[i][1].equals(operador)) {
                    if (matriz[i][2].equals(tipo2)) {
                        return true;
                    }
                }
            }
            
        }
        return false;
    }
}
