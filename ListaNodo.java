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
public class ListaNodo {
     //Atributos de la lista
    nodo_define_new_instruction Pri;
    nodo_define_new_instruction Ult;
    String Nom;

    //Constructor de la lista
    public ListaNodo(String n) {
        Pri = Ult = null;
        Nom = n;
    }

    //Constructor
    public boolean ListaNodoVacia() {//EN CASO DE  QUE LA LISTA ESTE VACIA
        if (Pri == null) {
            return true;
        } else {
            return false;
        }
    }

    //Nombre de la lista
    public ListaNodo() {
        this("ListaNodo");
    }

    //Metodo para insertar por el frente de la lista
    public void Instertar_Nodo_Inicio(String lexema, String tipo,int indice) {
        if (ListaNodoVacia()) {
            Pri = Ult = new nodo_define_new_instruction(lexema, tipo,indice);
        } else {
            Pri = new nodo_define_new_instruction(lexema, tipo, indice);
        }
    }

    //Metodo para insertar por la parte posterior de la lista
    public void Insertar_Nodo_Final(String lexema, String tipo,int indice) {
        if (ListaNodoVacia()) {
            Pri = Ult = new nodo_define_new_instruction(lexema,tipo,indice);
        } else {
            Ult = Ult.sig = new nodo_define_new_instruction(lexema, tipo,indice);
        }
    }

    //Metodo para mostrar los datos de a lista
    public void Mostrar() {
        nodo_define_new_instruction Actual = Pri;
        if (ListaNodoVacia()) {
            System.out.println("La " + Nom + " esta vacia");
        }
        System.out.println("Lexema"+"\t"+"Parametro");
        while (Actual != null) {
            System.out.print(Actual.lexema);
            System.out.print("\t");
            System.out.print(Actual.indice);
            System.out.print("\t");
            System.out.println(Actual.tipo);
            Actual = Actual.sig;
        }
    }

    //Metodo para eliminar el frente de la lista
    public void Eliminar_Nodo_Inicio() {
        if (ListaNodoVacia()) {
            Pri = Pri.sig;
        } else if (Pri.equals(Ult)) {
            Pri = Ult = null;
        } else {
            Pri = Pri.sig;
        }
    }

    //Metodo para eliminar el posterior de la lista
    public void Eliminar_Nodo_Final() {
        if (ListaNodoVacia()) {
            Pri = Pri.sig;
        } else if (Pri.equals(null)) {
            Ult = null;
        } else {
            Pri = Pri.sig;
        }
    }

    //Metodo para buscar en la lista de inicion a fin
    public boolean nodoEncontrado(String xlexema) {
        boolean encontrado = false;
        if (!ListaNodoVacia()) {
            nodo_define_new_instruction Actual = Pri;

            while (Actual != null) {

                if (Actual.lexema.equals(xlexema)) {

                    encontrado = true;
                    break;

                }
                Actual = Actual.sig;
            }

        }
        return encontrado;
    }
}