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
public class ListaTemporal {
    	//Atributos de la lista
    nodoListaTemporal Pri;
    nodoListaTemporal Ult;
    String Nom;

    //Constructor de la lista
    public ListaTemporal(String n) {
        Pri = Ult = null;
        Nom = n;
    }

    //Constructor
    public boolean Lista_nodo_TemporalVacia() {//EN CASO DE  QUE LA LISTA ESTE VACIA
        if (Pri == null) {
            return true;
        } else {
            return false;
        }
    }
    //Metodo para insertar por la parte posterior de la lista
    public void Insertar_Nodo_Final(String lexema) {
        if (Lista_nodo_TemporalVacia()) {
            Pri = Ult = new nodoListaTemporal(lexema);
        } else {
            Ult = Ult.sig = new nodoListaTemporal(lexema);
        }
    }

    //Metodo para buscar en la lista de inicion a fin
    public boolean nodoEncontrado(String xlexema) {
        boolean encontrado = false;
        if (!Lista_nodo_TemporalVacia()) {
             nodoListaTemporal Actual = Pri;

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
