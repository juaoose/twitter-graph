package uniandes.cupi2.tweetSpy.mundo.Lista;

public class NodoListaDobleEncadenada <T> 
{

	//-----------------------
	// Atributos
	//-----------------------
	
	private T elem;
	private NodoListaDobleEncadenada<T> siguiente;
	private NodoListaDobleEncadenada<T> anterior;
	
	
	
	public NodoListaDobleEncadenada(T p)
	{
		elem = p;
		siguiente = null;
		anterior = null;
	}
	
	//---------------------------
	// MŽtodos
	//---------------------------
	
	public T darNombre()
	{
		return elem;
	}
	
	/**
	 * 
	 * @return Retorna el siguiente elemento.
	 */
	public NodoListaDobleEncadenada<T> darSiguiente()
	{
		return siguiente;
	}
	
	/**
	 * 
	 * @return Retorna el elemento anterior.
	 */
	public NodoListaDobleEncadenada<T> darAnterior()
	{
		return anterior;
	}
	
	
	/**
	 * Elimina un nodo (No es el primero)
	 */
	public void eliminarNodo()
	{
		NodoListaDobleEncadenada<T> anteriorAntiguo = anterior;
		NodoListaDobleEncadenada<T> siguienteAntiguo = siguiente;
		anterior= null;
		siguiente = null;
		anteriorAntiguo.siguiente = siguienteAntiguo;
		
		if(siguienteAntiguo != null)
		{
			siguienteAntiguo.anterior = anteriorAntiguo;
		}
	}
	
	/**
	 * 
	 */
	public void eliminarPrimerElemento()
	{
		NodoListaDobleEncadenada<T> p = siguiente;
        siguiente = null;
        if( p != null )
        {
            p.anterior = null;
        }
	}
	
	public void insertarEnPosicionAnterior(NodoListaDobleEncadenada<T> nodo)
	{
		nodo.siguiente = this;
		nodo.anterior = anterior;
		if(anterior != null)
		{
			anterior.siguiente = nodo;
		}
		anterior = nodo;
	}
	
	public void insertarEnPosicionSiguiente(NodoListaDobleEncadenada<T> nodo)
	{
		nodo.siguiente = siguiente;
		nodo.anterior = this;
		if(siguiente != null)
		{
			siguiente.anterior = nodo;
		}
		siguiente = nodo;
	}
	
	
}
