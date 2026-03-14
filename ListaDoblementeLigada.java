public class ListaDoblementeLigada<T> {
  private Nodo<T> cabeza;
  private Nodo<T> rabo;
  private int elementos; //Este se refiere al tamaño de la lista

  public boolean isEmpty(){
    return this.cabeza == null && this.rabo == null;
  }

  public void add(int indice, T elemento){
    if(indice < 0 || indice > this.elementos){
      throw new IndexOutOfBoundsException("Índice fuera del rango permitido");
    }

    Nodo<T> nuevoNodo = new Nodo<>(elemento);
    if(this.isEmpty()){
      this.cabeza = nuevoNodo;
      this.rabo = nuevoNodo;
      nuevoNodo.setSiguiente(null);
      nuevoNodo.setAnterior(null);
      elementos++;
      return;
    }

    if(indice == 0){
      nuevoNodo.setSiguiente(cabeza);
      cabeza.setAnterior(nuevoNodo);
      nuevoNodo.setAnterior(null);
      cabeza = nuevoNodo;
      elementos++;
      return;
    }

    if(indice == elementos){
      nuevoNodo.setAnterior(rabo);
      rabo.setSiguiente(nuevoNodo);
      nuevoNodo.setSiguiente(null);
      rabo = nuevoNodo;
      elementos++;
      return;
    }
    
    Nodo<T> aux = cabeza;
    for(int i = 0; i < elementos; i++){
      aux = aux.getSiguiente();
    }
    nuevoNodo.setAnterior(aux.getAnterior());
    nuevoNodo.setSiguiente(aux);
    aux.getAnterior().setAnterior(nuevoNodo);
    aux.setAnterior(nuevoNodo);
    elementos++;
    return;
  }

  public boolean add(T elemento){
    Nodo<T> nuevoNodo = new Nodo<>(elemento);
    nuevoNodo.setAnterior(rabo);
    rabo.setSiguiente(nuevoNodo);
    nuevoNodo.setSiguiente(null);
    rabo = nuevoNodo;
    elementos++;
    return this.contains(nuevoNodo);
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
    return null;
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
    if(this.elementos == 0){
      return "cabeza -> null";
    }
    String cadena = "cabeza ->";
    Nodo<T> actual = this.cabeza;

    for(int i = 0; i < this.elementos; i++){
      if(actual.getSiguiente() == null){
        cadena += actual + " -> ";
        actual = actual.getSiguiente();
      }else{
        cadena += actual  + " <-> ";
        actual = actual.getSiguiente();
      }
    }
    return cadena + " null";
  }

  public static void main(String[] args){
    ListaDoblementeLigada<Integer> lista = new ListaDoblementeLigada<>();
    for(int i = 0; i < 10; i++){
      lista.add(0, i+1);
    }  
    System.out.println(lista);

    lista.add(99);
    System.out.println(lista);

    lista.add(11, 60);
    System.out.println(lista);
  }
}
