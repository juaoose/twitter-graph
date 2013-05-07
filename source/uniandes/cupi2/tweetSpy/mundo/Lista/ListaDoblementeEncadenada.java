package uniandes.cupi2.tweetSpy.mundo.Lista;

import java.util.ArrayList;

import uniandes.cupi2.tweetSpy.mundo.Iterador.*;

public class ListaDoblementeEncadenada<T> implements IListaDoblementeEncadenada<T> 
{
	/**
	 * 
	 */
	private NodoListaDobleEncadenada<T> primero;
	
	/**
	 * 
	 */
	private NodoListaDobleEncadenada<T> ultimo;
	
	/**
	 * 
	 */
	private int numeroElementos;
	
	/**
	 * Constructor
	 */
	public ListaDoblementeEncadenada()
	{
		primero = null;
		ultimo = null;
		numeroElementos = 0;
	}
	
	/**
	 * Retorna el tamaño de la estructura.
	 * @return
	 */
	public int darTamanio()
	{
		return numeroElementos;
	}
	
	/**
	 * Retorna el primer elemento en la estructura.
	 * @return
	 */
	public NodoListaDobleEncadenada<T> darPrimerElemento()
	{
		return primero;
	}
	
	/**
	 * Retorna el ultimo elemento de la estructura.
	 * @return
	 */
	public NodoListaDobleEncadenada<T> darUltimoElemento()
	{
		return ultimo;
	}
	
	
	
	/**
	 * Agrega un elemento en el comienzo de la lista.
	 */
	public void agregarComienzo(T elemento) 
	{
		NodoListaDobleEncadenada<T> nodo = new NodoListaDobleEncadenada<T>(elemento);
		if (primero == null)
		{
			primero = nodo;
			ultimo = nodo;
		}
		else
		{
			primero.insertarEnPosicionAnterior(nodo);
			primero = nodo;
		}
		numeroElementos++;
		
	}

	/**
	 * Agrega un elemento al final de la estructura.
	 */
	public void agregarFinal(T elemento) 
	{
		NodoListaDobleEncadenada<T> nodo = new NodoListaDobleEncadenada<T>(elemento);
		if (primero == null)
		{
			primero = nodo;
			ultimo = nodo;
		}
		else
		{
			ultimo.insertarEnPosicionSiguiente(nodo);
			ultimo = nodo;
		}
		numeroElementos++;
		
	}

	/**
	 *  Corre el elemento una posicion hacia el final de la estructura.
	 */
	public void posicionMasUno(T elemento) 
	{
		//No se mira si es el ultimo ! OJO!
		NodoListaDobleEncadenada<T> busqueda = (NodoListaDobleEncadenada<T>) elemento;
		if (busqueda.darSiguiente() != null)
		{
			NodoListaDobleEncadenada<T> siguienteAhoraAnterior = busqueda.darSiguiente();
			busqueda.darSiguiente().eliminarNodo();
			busqueda.insertarEnPosicionAnterior(siguienteAhoraAnterior);
		}
	}

	/**
	 * Corre el elemento una posisicion hacia el primer elemento de la estructura.
	 */
	public void posicionMenosUno(T elemento) 
	{
		//No se mira si es el primero! OJO!
		NodoListaDobleEncadenada<T> busqueda = (NodoListaDobleEncadenada<T>) elemento;
		if (busqueda.darAnterior() != null)
		{
			NodoListaDobleEncadenada<T> anteriorAhoraSiguiente = busqueda.darAnterior();
			busqueda.darAnterior().eliminarNodo();
			busqueda.insertarEnPosicionSiguiente(anteriorAhoraSiguiente);
		}
		
	
		
	}

	/**
	 * Elimina un elemento de la lista.
	 * @param elemento a eliminar
	 */
	//Revisar.
	public void eliminar(T elemento) 
	{
		if (primero == null)
		{
			//ÀException?
		}
		else if(primero ==(elemento))
		{
			if (primero ==(ultimo))
			{
				ultimo = null;
			}
			primero = primero.darSiguiente();
			primero.eliminarPrimerElemento();
			numeroElementos = numeroElementos -1;
		}
		else
		{
		       for( NodoListaDobleEncadenada<T> p = primero.darSiguiente( ); p != null; p = p.darSiguiente( ) )
	            {
	                if( p.darNombre( ).equals( elemento ) )
	                {
	                    if( p.equals( ultimo ) )
	                    {
	                        ultimo = p.darAnterior( );
	                    }
	                    p.eliminarNodo();
	                    numeroElementos--;
	                }
	            }
		}
		
		
	}


	/**
	 * Metodo que busca un elemento dentro de la estructura.
	 */
	public T buscar(T elemento) 
	{
		
		for (NodoListaDobleEncadenada<T> p = primero; p != null ; p = p.darSiguiente())
		{
			
			if (p.darNombre().equals(elemento))
			{
				return p.darNombre();
				
			}
		}
		return null;
	}
	
	/**
	 * M?todo que retorna el arreglo de elementos de la estructura.
	 * @return arreglo Arreglo de elementos.
	 */
	public Object[] darArreglo()
	{
		Object[] arreglo = new Object[numeroElementos];
		int i = 0; 
	    for (NodoListaDobleEncadenada<T> n = primero; n != null; n = n.darSiguiente(), i++)
	      arreglo[i] = n.darNombre();
	    return arreglo;
	}
	

	
	/**
	 * Metodo que retorna el elemento en la posicion.
	 */
	public T dar( int pos )
    {
        if( pos >= numeroElementos || pos < 0 )
        {
            return null;
        }
        else
        {
            NodoListaDobleEncadenada<T> aux = primero;

            for( int cont = 0; cont < pos; cont++ )
            {
                aux = aux.darSiguiente( );
            }

            return aux.darNombre();
        }
		
    }
	
    public Iterador<T> darIterador( )
    {
        Iterador<T> respuesta = new Iterador<T>( numeroElementos );
        NodoListaDobleEncadenada<T> iterador = primero;
        while( iterador != null )
        {
         
                respuesta.agregar( iterador.darNombre( ) );
                iterador = iterador.darSiguiente( );
            
         
        }
        return respuesta;
    }
	
}
