package uniandes.cupi2.tweetSpy.mundo.Iterador;

public class Iterador<T> implements IIterador<T>
{
	
	private final static int NADA = -1;
    /**
     * Elementos sobre los que se está iterando
     */
    private T[] elems;

    /**
     * Posición que del próximo elemento a ser visitado
     */
    private int posActual;

    /**
     * La siguiente posición libre en elems. Corresponde en realidad al número de elementos sobre los que se está iterando
     */
    private int sigPosLibre;
    
    /**
     * Constructor
     * @param tamano
     */
    public Iterador(int tamano)
    {
    	  elems = ( T[] )new Object[tamano];
          sigPosLibre = 0;
          posActual = NADA;
    }

	/**
	 * Retorna el elemento anterior.
	 */
	public T darAnterior() 
	{
		T resp = null;
		if (hayAnterior()== true)
		{
			resp = elems[posActual-1];
		}
		return resp;
		
	}

	/**
	 * Retorna el siguiente elemento.
	 */
	public T darSiguiente() 
	{
		T resp = null;
		if(haySiguente()== true)
		{
			resp = elems[posActual+1];
		}
		return resp;
		
	}

	/**
	 * Retorna si hay o no anterior.
	 */
	public boolean hayAnterior() 
	{
		return elems.length > 0 && posActual > 0;
		
	}

	/**
	 * Retorna si hay o no siguiente.
	 */
	public boolean haySiguente() 
	{
		return elems.length > 0 && ( posActual + 1 ) < sigPosLibre;
		
	}

	/**
	 * Reinicia el iterador.
	 */
	public void reiniciar() 
	{
		 posActual = NADA;
		
	}
	
	/**
	 * Retorna la longitud del iterador.
	 * @return
	 */
	public int darLongitud( )
	{
		return elems.length;
	}
	
	/**
	 * Inserta.
	 * @param elem
	 */
    public void insertar( T elem ) 
    {
        if( sigPosLibre >= elems.length )
        {}
        // Abre espacio para el nuevo elemento
        for( int i = sigPosLibre; i > 0; i-- )
        {
            elems[ i ] = elems[ i - 1 ];
        }
        sigPosLibre++;
        elems[ 0 ] = elem;
    }
    
    /**
     * Agrega un elemento.
     * @param elem
     */
    public void agregar( T elem )
    {
        if( sigPosLibre <= elems.length - 1 )
        {
            elems[ sigPosLibre++ ] = elem;
        }
      
           
    }
    
    /**
     * Retorna el numero de elementos del iterador.
     * @return
     */
    public T[] darElementos()
    {
    	return elems;
    }

	
}
