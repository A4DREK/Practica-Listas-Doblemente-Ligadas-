public class ListaDoblementeLigada<T> {
  private Nodo<T> cabeza;
  private Nodo<T> rabo;
  private int elementos; //Este se refiere al tamaño de la lista

  public void add(int indice, T elementos){
    if(indice < 0 || indice > this.elementos){
      throw new IndexOutOfBoundsException("Índice fuera del rango permitido");
    }
    return;
  }

  public boolean add(T elemento){
    return true;
  }

  public boolean contains(Nodo<T> objeto){
    if(objeto == null || this.elementos == 0) return false;

    Nodo<T> actual = this.cabeza;

    while(actual != null){
      if(actual.equals(objeto)){
        return true;
      } else{
          actual = actual.getSiguiente(); 
      }
    }
    return false;
  }

  public T remove(int indice){
    if(indice < 0 || indice > this.elementos-1){
      throw new IndexOutOfBoundsException("Indice fuera del rango permitido");
    }
    return null
  }

  public boolean remove(Nodo<T> objeto){
    if(objeto == null){
      return false;
    }
    return true;
  }

  public int size(){
    return elementos;
  }

  @Override
  public String toString(){
    if(this.elementos = 0){
      return "cabeza -> null";
    }
    String cadena = "cabeza ->";
    Nodo<T> actual = this.cabeza;

    for(int i = 0; i < this.elementos; i++){
      if(actual.getSiguiente() == null){
        cadena += actual + " -> ";
        actual.actual.getSiguiente();
      }else{
        cadena += actual  + " <-> ";
        actual = actual.getSiguiente();
      }
    }
    return cadena + " null";
  }
}
