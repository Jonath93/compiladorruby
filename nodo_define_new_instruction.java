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
class nodo_define_new_instruction {
      String lexema;
      String tipo;
      int indice;
    nodo_define_new_instruction sig = null;

    public nodo_define_new_instruction(String lexema, String tipo, int indice) {
        this.lexema = lexema;
        this.tipo = tipo;
        this.indice=indice;
    }
}
