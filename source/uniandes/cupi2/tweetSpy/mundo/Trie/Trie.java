package uniandes.cupi2.tweetSpy.mundo.Trie;

import java.io.Serializable;

import uniandes.cupi2.tweetSpy.mundo.Arbol23.NoExisteException;
import uniandes.cupi2.tweetSpy.mundo.Arbol23.YaExisteException;
import uniandes.cupi2.tweetSpy.mundo.Iterador.Iterador;
import uniandes.cupi2.tweetSpy.mundo.Lista.ListaDoblementeEncadenada;

public class Trie<T> implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
	 * Constante para la serializaci�n
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	/**
     * Ra�z del trie
     */
    private NodoTrie<T> raiz;

    /**
     * Peso del trie
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un tree vac�o.<br>
     * <b> post: </b> Se construy� un trie vac�o.
     */
    public Trie( )
    {
        raiz = new NodoTrie<T>( ' ' );
    }

    /**
     * Construye un tree con la ra�z especificada. <br>
     * <b> pre: </b> El caracter de la raiz debe ser ' ' y su elemento==null. <br>
     * <b> post: </b> Se construy� un trie con la ra�z especificada.
     * @param r La ra�z del trie
     * @param p El peso del trie (n�mero de nodos que tiene el trie)
     */
    public Trie( NodoTrie<T> r, int p )
    {
        raiz = r;
        peso = p;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inserta un elemento dado en el trie. <br>
     * <b> pre: </b> elemento!=null. <br>
     * <b> post: </b> Se inserto el elemento dado en el trie.
     * @param elemento El elemento a ser insertado en el trie
     * @throws ElementoExisteException Si el elemento a ser insertado ya existe en el trie
     * @throws PalabraInvalidaException Si el elemento a ser insertado no es v�lido (su representaci�n en String es vac�a)
     */
    public void insertar( T elemento ) throws YaExisteException, PIException
    {
        String palabra = elemento.toString( );
        if( palabra.length( ) == 0 )
        {
            throw new PIException( "El elemento que se quiere ingresar no es v�lido" );

        }
        else
        {
            peso += raiz.insertar( palabra, elemento );
        }
    }

    /**
     * Elimina el elemento del trie cuya representaci�n en String corresponde a la palabra dada. <br>
     * <b> pre: </b> palabra!=null. <br>
     * <b> post: </b> Se elimin� el elemento del trie cuya representaci�n en String corresponde a la palabra dada. Si no existe tal elemento se retorna null.
     * @param palabra La palabra a ser eliminada
     * @return El elemento del trie cuya representaci�n en String corresponde a la palabra dada. Si no existe tal elemento se retorna null.
     * @throws PalabraInvalidaException Si el elemento a ser eliminado no es v�lido (su representaci�n en String es vac�a)
     * 
     */
    public T eliminar( String palabra ) throws NoExisteException, PIException
    {
        if( palabra.length( ) == 0 )
        {
            throw new PIException( "El elemento que quiere eliminar es inv�lido" );
        }
        T elemento = buscar( palabra );
        if( elemento == null )
        {
            throw new NoExisteException( "El elemento a eliminar no se encuentra en el �rbol" );
        }
        int eliminados = elemento != null ? raiz.eliminar( palabra ) : 0;
        peso -= eliminados;
        return elemento;
    }

    /**
     * Busca el elemento del trie cuya representaci�n en String corresponte a la palabra dada. <br>
     * <b> pre: </b> palabra!=null. <br>
     * <b> post: </b> Se retorn� el elemento del trie cuya representaci�n en String corresponde a la palabra dada. Si la palabra no corresponde a ning�n elemento se retorna
     * null.
     * @param palabra Palabra a ser buscada
     * @return El elemento del trie cuya representaci�n en String corresponde a la palabra dada. Si no existe tal elemento se retorna null.
     */
    public T buscar( String palabra )
    {
        return raiz.buscar( palabra );
    }

    /**
     * Busca todos los elementos asociados con el prefijo dado.<br>
     * <b> pre: </b> prefijo!=null. <br>
     * <b> post: </b> Se retorn� la lista de elementos correspondiente al prefijo dado.
     * @param prefijo El prefijo del que se desean los elementos asociados
     * @return La lista de elementos correspondiente al prefijo dado
     */
    public ListaDoblementeEncadenada<T> buscarPorPrefijo( String prefijo )
    {
        ListaDoblementeEncadenada<T> lista = new ListaDoblementeEncadenada<T>( );

        raiz.buscarPorPrefijo( prefijo, lista );

        return lista;
    }

    /**
     * Devuelve los elementos del trie en inorden. <br>
     * <b>pre: </b> resultado!=null. <br>
     * <b>post: </b> Se retorno un vector con el recorrido en inorden del trie.
     * 
     * @param resultado Vector con los elementos del trie en inorden
     */
    public Iterador<T> inorden( )
    {
        Iterador<T> resultado = new Iterador<T>( peso );
        raiz.inorden( resultado );

        return resultado;
    }

    /**
     * Retorna el peso (n�mero de nodos) del trie <b>post: </b> Se retorn� el peso del trie
     * @return El peso del trie
     */
    public int darPeso( )
    {
        return peso;
    }

    /**
     * Retorna la alturta del trie. <br>
     * <b>post: </b> Se retorn� la altura del trie.
     * @return La altura del trie
     */
    public int darAltura( )
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }

    /**
     * Retorna la ra�z del trie. <br>
     * <b>post: </b> Se retorn� la ra�z trie.
     * @return La ra�z del trie
     */
    public NodoTrie<T> darRaiz( )
    {
        return raiz;
    }
}