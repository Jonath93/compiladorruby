package compiladorruby;

import java.util.Stack;


class sintaxis {

    
    boolean error=false;
    //variable de nodo
    nodo p;
    nodo aux2 = p;
    nodo auxtipo=p;
    //Llamado de lista
    String tipo;
    Stack pila = new Stack();
    ListaPolish polisher = new ListaPolish();
    ListaTipos listatipos = new ListaTipos();
    ListaNodo ListaNuevo = new ListaNodo();
    //variables
    int indice=1;
    
    String Errores[][]={
        {"Error: Falta Digitos"       , "500"},
        {"Error: Falta  Doble coma"   , "501"},
        {"Error: Falta     ="         , "502"},
        {"Error: Falta     &"         , "503"},
        {"Error: Falta     |"         , "504"},
        {"Error: Falta    ID"         , "505"},
        {"Error: Falta    Sentencia"         , "506"},
        {"Error: Falta    Asignacion"         , "507"},
        {"Error: Falta    $"         , "508"},
        {"Error: Falta    if"         , "509"},
        {"Error: Falta    BloquePrincipal"         , "510"},
        {"Error: Falta    else"         , "511"},
        {"Error: Falta    then"         , "512"},
        {"Error: Falta    end"         , "513"},
        {"Error: Falta    ;"         , "514"},
        {"Error: Falta    ="         , "515"},
        {"Error: Falta    Operacion_arit"  , "516"},
        {"Error: Falta    Digitos"         , "517"},
        {"Error: Falta    Literal"         , "518"},
        {"Error: Falta    Expresiones"     , "519"},
        {"Error: Falta    digito o ID"     , "520"},
        {"Error: Falta    else o end"     , "521"},
        {"Error: Falta    do"     , "522"},
        {"Error: Falta    ID o cadena"     , "523"},
        {"Error: ID duplicado"     , "600"},
        {"Error: Valor No Inicializado"     , "601"},
        {"Error: Tipo no Compatible", "602"}
          
        
    };
    
     private void ImprimirErrores(int numError){
         for (int i = 0; i < Errores.length; i++) {
            if(numError==Integer.valueOf(Errores[i][1])){
                System.out.println(" "+Errores[i][0]+"  Renglon: "+p.renglon);
            }    
        }
    }
    sintaxis(nodo cabeza){
        p=cabeza;
        BloquePrincipal();
        
//        Lista_externals.Mostrar();
//        pila.push("Tope");
        
//        polisher.Mostrar();
//        mostrarPostFijo();
 //      ListaNuevo.Mostrar();
      CompatibilidadTipos();
      mostrarPila();
    }

    private boolean BloquePrincipal() {
        
      if(p.token==100){//id
          aux2=p;
          p=p.sig;
            if(p.token==120){//=
                insertar_Postfijo(p.token);
                p=p.sig;
                if(p.token==100||p.token==108 || p.token==102 || p.token==101){//id,
                    Variables();
                    if(p.token==100 || p.token==203 || p.token==200|| p.token==204){//id
                        SENTENCIA();
                        //return true;
                    }else{
                      ImprimirErrores(506);
                    }
                }else{
                    ImprimirErrores(520);
                }
            }else{
                ImprimirErrores(515);
            }
      }else{
          ImprimirErrores(505);
      }

      return false;
    }
    private boolean Variables(){
            

                if(p.token==108 || p.token==101 || p.token==102){//String,d,d.d
                    switch (p.token){
                        case 108 : tipo="String";
                            break;
                        case 101 : tipo="int";
                            break;
                        case 102 : tipo="float";
                        break;
                    }
                    //auxtipo=p;
                    if(ListaNuevo.nodoEncontrado(aux2.lexema)){
                        indice++;
                         ListaNuevo.Insertar_Nodo_Final(aux2.lexema+indice,tipo,indice);
                    } else{
                        ListaNuevo.Insertar_Nodo_Final(aux2.lexema,tipo,indice);
                    }
                    //polisher.Insertar_alFinal(aux2.lexema, tipo);
                    //polisher.Insertar_alFinal(p.lexema, tipo);
                    p=p.sig;
                    if(p.token==124){//;
                        //insertar_Postfijo(p.token);
                        p=p.sig;
                        return true;
                    }else if(p.token==103 || p.token==104||p.token==106||p.token==105){
                        bloqueAritmetico();
                    }
                }if(p.token==100){//id
                    polisher.Insertar_alFinal(aux2.lexema, tipo);
                    bloqueAritmetico();
                }else{
                    ImprimirErrores(520);//digito o id
                }
//            }else{
//                ImprimirErrores(502);
//            }
        
        return false;
    }
    private boolean SENTENCIA() {
        
        if(p.token==100){//id
            BloquePrincipal();
        }else if(p.token==203){//if
            p=p.sig;
            if(Expresion()){
//                if(OP_LOG()){
                         if(p.token==209){//then
                             THEN();
                            if(p.token==200||p.token==203||p.token==204||p.token==100){//prints
                                if(SENTENCIA()){
                                    if(p.token==206){//end
                                        p=p.sig;
                                        if(p.token==124){//;
                                            p=p.sig;
                                            return true;
                                        }else{
                                            ImprimirErrores(514);//error ;
                                        }
                                    }else
                                    if(p.token==212){//else
                                        p=p.sig;
                                        if(SENTENCIA()){
                                            if(p.token==206){//end
                                                p=p.sig;
                                                if(p.token==124){//;
                                                    p=p.sig;
                                                     return true;
                                                }else{
                                                    ImprimirErrores(514);
                                                }
                                            }else{
                                                ImprimirErrores(513);//end
                                            }
                                        }else{
                                            ImprimirErrores(506);
                                        }
                                    }else{
                                        ImprimirErrores(521);
                                    }
                                }else{
                                    ImprimirErrores(506);
                                }
                            }else{
                                ImprimirErrores(506);
                            }
                        }else if(p.token==210||p.token==211||p.token==121||p.token==122){
                            OP_LOG();
                            if(p.token==200||p.token==203||p.token==204||p.token==100){//prints
                                if(SENTENCIA()){
                                    if(p.token==206){//end
                                        p=p.sig;
                                        if(p.token==124){//;
                                            p=p.sig;
                                            return true;
                                        }else{
                                            ImprimirErrores(514);//error ;
                                        }
                                    }else
                                    if(p.token==212){//else
                                        p=p.sig;
                                        if(SENTENCIA()){
                                            if(p.token==206){//end
                                                p=p.sig;
                                                if(p.token==124){//;
                                                    p=p.sig;
                                                     return true;
                                                }else{
                                                    ImprimirErrores(514);
                                                }
                                            }else{
                                                ImprimirErrores(513);//end
                                            }
                                        }else{
                                            ImprimirErrores(506);
                                        }
                                    }else{
                                        ImprimirErrores(521);
                                    }
                                }else{
                                    ImprimirErrores(506);
                                }
                            }else{
                                ImprimirErrores(506);
                            }
                        }
                        
//                }//op_log
            }else{
                ImprimirErrores(519);
            }
        }else if(p.token==200){//prints sentence
            p=p.sig;
            if(p.token==100){//id
                p=p.sig;
                if(p.token==124){//;
                    p=p.sig;
                    return true;
                }else{
                    ImprimirErrores(514);
                }
            }else if (p.token==108){//cadena
                p=p.sig;
                if(p.token==124){//;
                    p=p.sig;
                    return true;
                }else{
                   ImprimirErrores(514); 
                }
            }else{
                ImprimirErrores(523);
            }
        }else if(p.token==204){//while sentence
            p=p.sig;
            if(Expresion()){
//                if(OP_LOG()){
                    if(DO()){
                       if(p.token==100){//id
                           if(SENTENCIA()){
                               if(p.token==206){//end
                                   p=p.sig;
                                   if(p.token==124){//;
                                       p=p.sig;
                                       return true;
                                   }else{
                                       ImprimirErrores(514);
                                   }
                               }else{
                                   ImprimirErrores(513);
                               }
                           }
                       } else if(p.token==203){//if
                           if(SENTENCIA()){
                               if(p.token==206){//end
                                   p=p.sig;
                                   if(p.token==124){//;
                                       p=p.sig;
                                       return true;
                                   }else{
                                       ImprimirErrores(514);
                                   }
                               }else{
                                   ImprimirErrores(513);
                               }
                           }
                       }else if(p.token==200){//prints
                           if(SENTENCIA()){
                               if(p.token==206){//end
                                   p=p.sig;
                                   if(p.token==124){//;
                                       p=p.sig;
                                       return true;
                                   }else{
                                       ImprimirErrores(514);
                                   }
                               }else{
                                   ImprimirErrores(513);
                               }
                           }
                       }else if(p.token==204){//while
                           if(SENTENCIA()){
                               if(p.token==206){//end
                                   p=p.sig;
                                   if(p.token==124){//;
                                       p=p.sig;
                                       return true;
                                   }else{
                                       ImprimirErrores(514);
                                   }
                               }else{
                                   ImprimirErrores(513);
                               }
                           }else{
                               ImprimirErrores(506);
                           }
                       }else{
                           ImprimirErrores(506);
                       }   
                    }else{
                    ImprimirErrores(522);
                }
//            }
             
            }else{
                ImprimirErrores(519);
            }
        }else{
            ImprimirErrores(514);
        }    
        return false;
    }  
    private boolean Expresion() {
        if(p.token==100){//id
            p=p.sig;
            if(p.token==110 || p.token==109 || p.token==111||p.token==112||p.token==113 || p.token==123){//<
                p=p.sig;
                if(p.token==100 || p.token==101 || p.token==102){//id
                    p=p.sig;
                    return true;
                }
                else{
                    ImprimirErrores(520);
                }
            }else{
                ImprimirErrores(519);
            }
        }else{
            ImprimirErrores(505);
        }
        return false;
    }
    private boolean THEN() {
        if(p.token==209){//then
            p=p.sig;
            return true;
        }else{
            ImprimirErrores(512);
        }
        return false;
    }
    private boolean DO() {
        if(p.token==201){//do
            p=p.sig;
            return true;
        }else{
            ImprimirErrores(522);
        }
        return false;
    }
    private boolean OP_LOG() {
        if(p.token==210||p.token==211||p.token==121||p.token==122){//and,or,&&,||
            p=p.sig;
            if(p.token==100){//id
                Expresion();
                if(p.token==209){
                    THEN();
                }else{
                    ImprimirErrores(512);
                }
            }else{
                ImprimirErrores(519);
            }
        }
        return false;
    }
    private boolean bloqueAritmetico() {
        nodo aux =p;
        if(p.token==100|| p.token==101||p.token==102 || p.token==108){//id
            if(p.token==100){
                if(!ListaNuevo.nodoEncontrado(aux.lexema)){
                    ImprimirErrores(601);
                } 
            }
            switch (p.token){
                        case 108 : tipo="String";
                            break;
                        case 101 : tipo="int";
                            break;
                        case 102 : tipo="float";
                        break;
            }
            polisher.Insertar_alFinal(aux.lexema, tipo);
            p=p.sig;
            if(p.token==103 || p.token==104||p.token==106||p.token==105){//+ - * /
                insertar_Postfijo(p.token);
                p=p.sig;
                bloqueAritmetico();
            }else if(p.token==124){//;
                insertar_Postfijo(p.token);
                p=p.sig;
                return true;
            }else{
                ImprimirErrores(514);
                
            }
       }else{
            ImprimirErrores(505);
        
            }
            
        return false;
    }
    
  // metodo de la pila
    private void limpiarPila() {
        while (true) {
            String auxPila = pila.lastElement().toString();
            polisher.Insertar_alFinal(auxPila, "operador");
            pila.pop();
            if (pila.empty()) {
                System.out.println("PILA VACIA");
                break;
            }

        }
        //mostrarPostFijo();
//        verificarTipos();

    }

    private void mostrarPila() {
        if (pila.empty()) {
            System.out.println("Alto Pila vacia");
        }
        System.out.println(pila.toString());
    }
    private void mostrarPostFijo() {
        polisher.Mostrar();

        polisher.Pri = null;
    }
    
     private void CompatibilidadTipos(){
         String Operador="";
         ListaPolish polish = polisher;
         NodoPolish Actual= polish.Pri;
         
         while(!Actual.tipo.equals("operador")){
             pila.push(Actual.tipo);
             Actual=Actual.sig;
         }
         Operador=Actual.lexema;
         String tipo2=(String) pila.peek();
         System.out.println(tipo2);
         pila.pop();
         String tipo1 = (String) pila.peek();
         System.out.println(tipo1);
         pila.pop();
         
         if(listatipos.tipoEncontrado(tipo1, Operador, tipo2)){
             System.out.println("tipo compatible");
         }
         listatipos.tipoResultante(tipo1, Operador, tipo2);
                 
         //listatipos.tipoEncontrado(tipo2,Operador ,tipo3 );
         
         
        
    }
    
    
     private void insertar_Postfijo(int tokenPolish) {
        // System.out.println("operador: " + tokenPolish);
        if (pila.empty()) {
            pila.push(tokenPolish);
        } else {
            

            int auxToken = (int) pila.peek();

            //cuando recibimos un * o / 
            if ((tokenPolish == 105) || (tokenPolish == 106)) { // *  /
                if (auxToken == 120) { // =
                    pila.push(tokenPolish);

                }
                if (auxToken == 103 || auxToken == 104) { // +  -
                    pila.push(tokenPolish);
                }
                if (auxToken == 105 || auxToken == 106) {// * /
                    if (auxToken == 105) {
                        polisher.Insertar_alFinal("*", "operador");
                        pila.pop();
                        pila.push(tokenPolish);
                    }
                    if (auxToken == 106) {
                        polisher.Insertar_alFinal("/", "operador");
                        pila.pop();
                        pila.push(tokenPolish);
                    }

                }
            }
            //cuando recibimos un + o -
            if ((tokenPolish == 103) || (tokenPolish == 104)) {
                if (auxToken == 120) { // =
                    pila.push(tokenPolish);

                }
                if (auxToken == 105 || auxToken == 106) {// * /
                    if (auxToken == 105) {
                        polisher.Insertar_alFinal("*", "operador");
                        pila.pop();
                        pila.push(tokenPolish);
                    }
                    if (auxToken == 106) {
                        polisher.Insertar_alFinal("/", "operador");
                        pila.pop();
                        pila.push(tokenPolish);
                    }
                }
                if (auxToken == 103 || auxToken == 104) {// +  - /
                    if (auxToken == 103) {
                        polisher.Insertar_alFinal("+", "operador");
                        pila.pop();
                        pila.push(tokenPolish);
                    }
                    if (auxToken == 104) {
                        polisher.Insertar_alFinal("-", "operador");
                        pila.pop();
                        pila.push(tokenPolish);
                    }
                }

            }
            if(tokenPolish == 124){//;
                
                while(!pila.empty()){
                    auxToken= (int) pila.peek();
                    if(auxToken==103){
                        polisher.Insertar_alFinal("+", "operador");
                        pila.pop();
                    }
                    if(auxToken==104){
                        polisher.Insertar_alFinal("-", "operador");
                        pila.pop();
                    }
                    if(auxToken==105){
                        polisher.Insertar_alFinal("*", "operador");
                        pila.pop();
                    }
                    if(auxToken==106){
                        polisher.Insertar_alFinal("/", "operador");
                        pila.pop();
                    }
                    if(auxToken==120){
                        polisher.Insertar_alFinal("=", "operador");
                        pila.pop();
                    }
                }
            }

        }

    }//fin del metodo

  

   
       
}
