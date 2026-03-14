public class Nodo<T>{
    private T elemento;
    private Nodo<T> siguiente;
    private Nodo<T> anterior; 

    public Nodo(T elemento){
      this.elemento = elemento;
      this.siguiente = null;
      this.anterior = null;
    }

    public T getElemento(){
      return this.elemento;
    }

    public Nodo<T> getSiguiente(){
      return this.siguiente;
    }

    public Nodo<T> getAnterior(){
      return this.anterior;
    }

    public void setElemento(T nuevoElemento){
      this.elemento = nuevoElemento;
    }

    public void setSiguiente(Nodo<T> siguiente){
      this.siguiente = siguiente;
    }

    public void setAnterior(Nodo<T> anterior){
      this.anterior = anterior;
    }

    @Override
    public booelan equals(Object objeto){
      /**
       * Si el objeto que manda a llamar el método equals se compara consigo mismo.
      **/
      if(this == objeto) return true;

      /** 
       * Si el objeto es un ejemplar de la clase Nodo, se crea un ejemplar 
       * temporal llamado otroNodo y no se ejecuta lo que esta dentro del 
       * if 
       **/
      if(!(objeto instanceof Nodo otroNodo)) return false;

      /**
       * Si paso la validación anterior solamente nos falta comparar si el 
       * elemento del nodo que manddo a llamar al método es igual al elemento
       * del nodo temporal creado anteriormente
       **/ 
      return this.elemento.equals(otroNodo.elemento);
    }

    @Override
    public String toString(){
      return " | " + this.elemento + " | ";
    }

}
