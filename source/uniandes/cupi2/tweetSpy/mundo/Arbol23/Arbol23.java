package uniandes.cupi2.tweetSpy.mundo.Arbol23;

import java.io.Serializable;

import uniandes.cupi2.tweetSpy.mundo.Compara.Compara;
import uniandes.cupi2.tweetSpy.mundo.Iterador.Iterador;

public class Arbol23 <T extends Compara<? super T>> implements Serializable, IArbol23<T>
{
	/**
     * Raíz del árbol 2-3
     */
    private Nodo23<T> raiz;

    /**
     * Peso del árbol 2-3
     */
    private int peso;

    public Arbol23( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz del árbol para ser navegada. <br>
     * <b>post: </b> Se retornó la raíz del árbol.
     * @return Raíz del árbol
     */
    public Nodo23<T> darRaiz( )
    {
        return raiz;
    }

    
    /**
     * Retorna el elemento que concuerda.
     */
    public T buscar( T elem )
    {
        return ( raiz != null ) ? raiz.buscar( elem ) : null;
    }

    /**
     * Retorna la altura del arbol.
     */
    public int darAltura( )
    {
        return raiz == null ? 0 : raiz.darAltura( );
    }

    /**
     * Retorna el peso del arbol.
     */
    public int darPeso( )
    {
        return peso;
    }

    
    /**
     * Permite insertar un elemento al arbol.
     */
    public void insertar( T elemento ) throws YaExisteException
    {
        if( raiz == null )
        {
            
            raiz = new Nodo23<T>( elemento );
        }
        else
        {
           
            raiz = raiz.insertar( elemento );
        }
        peso++;
    }

    
    /**
     * Permite eliminar el elemento que concuerda con la busqueda
     */
    public void eliminar( T elemento ) throws NoExisteException
    {
        if( raiz != null )
        {
            
            raiz = raiz.eliminar( elemento );
            peso--;
        }
        else
        {
            
            throw new NoExisteException( "El elemento especificado no existe en el árbol" );
        }
    }

    /**
     * Retorna el iterador en inorden del arbol.
     * @return
     */
    public Iterador<T> inorden( )
    {
        Iterador<T> resultado = new Iterador<T>( peso );
        if( raiz != null )
        {
            try
            {
                raiz.inorden( resultado );
            }
            catch( Exception e )
            {
              
            }
        }
        return resultado;
    }
}
