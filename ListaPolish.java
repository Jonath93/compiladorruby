
package compiladorruby;

/**
 *
 * @author Jonathan
 */
import java.util.Stack;

public class ListaPolish {
    NodoPolish Pri;
    NodoPolish Ult;
    String Nom;

    //Constructor de la lista
    public ListaPolish() {
        Pri = Ult = null;
    }

    //Constructor
    public boolean Lista_IDTipoVacia() {//EN CASO DE  QUE LA LISTA ESTE VACIA
        if (Pri == null) {
            return true;
        } else {
            return false;
        }
    }

    public void Insertar_alFinal(String lexema, String tipo) {
        if (Lista_IDTipoVacia()) {
            Pri = Ult = new NodoPolish(lexema,tipo);
        } else {
            Ult = Ult.sig = new NodoPolish(lexema,tipo);
        }
    }

    public void cambiarTipo(String xlexema, String nuevoTipo) {

        boolean encontrado = false;
        NodoPolish Actual = Pri;

        while (Actual != null) {
            if (Actual.lexema.equals(xlexema)) {
                Actual.tipo = nuevoTipo;
                encontrado = true;
                if (encontrado) {
                    break;
                }
                //break;

            }
            Actual = Actual.sig;
        }
        if (!encontrado) {
            Insertar_alFinal(xlexema, nuevoTipo);
        }
    }

    void Mostrar() {
        NodoPolish Actual = Pri;
        if (Lista_IDTipoVacia()) {
            System.out.println("La " + " esta vacia");
        }

        while (Actual != null) {
            System.out.print(Actual.lexema);
            System.out.print("\t");
            System.out.println(Actual.tipo);
            Actual = Actual.sig;
        }
    }

    void verificarTipo() {

//        listaTipos 
        NodoPolish Actual = Pri;
        String tipo = "";
        Stack mipila = new Stack();
        if (Lista_IDTipoVacia()) {
            System.out.println("La " + Nom + " esta vacia");
        }

        while (Actual != null) {
            System.out.println(Actual.tipo);
            Actual = Actual.sig;
            if (Actual.tipo.equals("operador")) {
                String tipo2 = (String) mipila.peek();
                mipila.pop();
                String tipo1 = (String) mipila.peek();
                mipila.pop();
//               listaTipos 

            } else {
                mipila.push(Actual.tipo);
            }
        }
    }
}
