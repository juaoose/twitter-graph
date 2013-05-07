package uniandes.cupi2.tweetSpy.mundo.Arbol23;

import uniandes.cupi2.tweetSpy.mundo.Compara.Compara;

public interface IArbol23<T extends Compara<? super T>>  
{
	/**
     * Retorna la altura del árbol.
     * @return La altura del árbol.
     */
    public int darAltura();
    
    /**
     * Retorna el número de elementos del árbol.
     * @return El número de elementos del árbol.
     */
    public int darPeso();
    
    /**
     * Busca un elemento en el árbol dado su modelo.
     * @param modelo Descripción del elemento que se va a buscar en el árbol. Debe contener por lo menos la información mínima necesaria para que el método de comparación del
     *        nodo pueda establecer una relación de orden.
     * @return T elemento del árbol que corresponde al modelo o <code>null</code> si este no existe.
     */
    public T buscar(T modelo);
	/**
     * Inserta un nuevo elemento en el árbol.
     * 
     * @param elem Elemento a insertar.
     * @throws ElementoExisteException Si el elemento a insertar ya se encuentra en el árbol
     */
    public void insertar( T elem ) throws YaExisteException;

    /**
     * Eliminar un elemento del árbol.
     * 
     * @param elem Elemento a eliminar del árbol.
     * @throws ElementoNoExisteException Si el elemento a eliminar no es encontrado en el árbol.
     */
    public void eliminar( T elem ) throws NoExisteException;


}
