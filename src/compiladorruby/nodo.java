package compiladorruby;

class nodo{
    String lexema;
    int renglon;
    int token;
    nodo sig=null;
    
    
    nodo(String lexema,int token, int renglon){
        this.lexema=lexema;
        this.token=token;
        this.renglon=renglon;
        
    }
}