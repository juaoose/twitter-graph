package uniandes.cupi2.tweetSpy.mundo.Arbol23;

import uniandes.cupi2.tweetSpy.mundo.Compara.Compara;

public interface IArbol23<T extends Compara<? super T>>  
{
	/**
     * Retorna la altura del �rbol.
     * @return La altura del �rbol.
     */
    public int darAltura();
    
    /**
     * Retorna el n�mero de elementos del �rbol.
     * @return El n�mero de elementos del �rbol.
     */
    public int darPeso();
    
    /**
     * Busca un elemento en el �rbol dado su modelo.
     * @param modelo Descripci�n del elemento que se va a buscar en el �rbol. Debe contener por lo menos la informaci�n m�nima necesaria para que el m�todo de comparaci�n del
     *        nodo pueda establecer una relaci�n de orden.
     * @return T elemento del �rbol que corresponde al modelo o <code>null</code> si este no existe.
     */
    public T buscar(T modelo);
	/**
     * Inserta un nuevo elemento en el �rbol.
     * 
     * @param elem Elemento a insertar.
     * @throws ElementoExisteException Si el elemento a insertar ya se encuentra en el �rbol
     */
    public void insertar( T elem ) throws YaExisteException;

    /**
     * Eliminar un elemento del �rbol.
     * 
     * @param elem Elemento a eliminar del �rbol.
     * @throws ElementoNoExisteException Si el elemento a eliminar no es encontrado en el �rbol.
     */
    public void eliminar( T elem ) throws NoExisteException;


}
