import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDoblementeLigada<T> implements Iterable<T> {
  private Nodo<T> cabeza;
  private Nodo<T> rabo;
  private int elementos; //Este se refiere al tamaño de la lista

  @Override
  public Iterator<T> iterator(){
    return new IteradorLista();
  }

  public boolean isEmpty(){
    return this.cabeza == null && this.rabo == null;
  }

  public void addNull(T elemento){
    Nodo<T> nuevoNodo = new Nodo<>(elemento);
    this.cabeza = nuevoNodo;
    this.rabo = nuevoNodo;
    nuevoNodo.setSiguiente(null);
    nuevoNodo.setAnterior(null);
    elementos++;
    return; 
  }

  public void add(int indice, T elemento){
    if(indice < 0 || indice > this.elementos){
      throw new IndexOutOfBoundsException("Índice fuera del rango permitido");
    }
    if(this.isEmpty()){
      this.addNull(elemento);
      return;
    }
    
    Nodo<T> nuevoNodo = new Nodo<>(elemento);
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
    for(int i = 0; i < indice; i++){
      aux = aux.getSiguiente();
    }
    nuevoNodo.setAnterior(aux.getAnterior());
    nuevoNodo.setSiguiente(aux);
    aux.getAnterior().setSiguiente(nuevoNodo);
    aux.setAnterior(nuevoNodo);
    elementos++;
    return;
  }

  public boolean add(T elemento){
    if(this.isEmpty()){
      this.addNull(elemento);
      return this.contains(new Nodo<>(elemento));
    }

    //Si la lista es no vaica
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
    if(this.isEmpty()){
      throw new NullPointerException("La lista debe de tener elementos");
    }

    if(indice == 0){
      T elementoBorrado = cabeza.getElemento();
      cabeza = cabeza.getSiguiente();

      if(cabeza != null){
        cabeza.setAnterior(null);
      }else{
        rabo = null;
      }
      elementos--;
      return elementoBorrado;
    }

    if(indice == this.elementos-1){
      T nodoBorrado = rabo.getElemento();
      rabo = rabo.getAnterior();
      rabo.setSiguiente(null);
      elementos--;
      return nodoBorrado;
    }

    Nodo<T> aux = cabeza;
    for(int i = 0; i < indice; i++){
      aux = aux.getSiguiente();
    }
    T nodoBorrado = aux.getElemento();
    aux.getAnterior().setSiguiente(aux.getSiguiente());
    aux.getSiguiente().setAnterior(aux.getAnterior());
    elementos--;
    return nodoBorrado;
  }
  
  public boolean remove(Nodo<T> objeto) {
    if(objeto == null || cabeza == null) return false;
    if(!(this.contains(objeto))) return false;

    Nodo<T> aux = cabeza;
    while(aux != null && !(aux.equals(objeto)) ){
      aux = aux.getSiguiente();
    }
    
    if(aux == cabeza){
      cabeza = cabeza.getSiguiente();
      if(cabeza != null){
        cabeza.setAnterior(null);
      }else{
        rabo = null;
      }
      elementos--;
      return true;
    }

    if(aux == rabo){
      rabo = rabo.getAnterior();
      if(rabo != null){
        rabo.setSiguiente(null);
      }
      elementos--;
      return true;
    }

    aux.getAnterior().setSiguiente(aux.getSiguiente());
    aux.getSiguiente().setAnterior(aux.getAnterior());
    elementos--;
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

    System.out.println(lista.remove(0));
    System.out.println(lista + "\n");

    System.out.println(lista.remove(new Nodo<>(7)));
    System.out.println(lista + "\n");

    System.out.println(lista.remove(new Nodo<>(60)));
    System.out.println("\n" + lista + "\n");

    System.out.println(lista.remove(6));
    System.out.println("\n" + lista + "\n");

    System.out.println(lista.remove(7));
    System.out.println(lista);

    // ListaDoblementeLigada<Integer> lista2 = new ListaDoblementeLigada<>();
    // lista2.add(1);
    // System.out.println(lista2);
    // System.out.println(lista2.remove(0));
    // System.out.println(lista2);

  }

  private class IteradorLista implements Iterator<T>{
  private Nodo<T> actual;

  public IteradorLista(){
    this.actual = cabeza;
  }

  @Override
  public boolean hasNext() {
    return actual != null;
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No hay más elementos en la lista");
    }
    
    T dato = actual.getElemento();
    actual = actual.getSiguiente();
    return dato;
  }
}
}



