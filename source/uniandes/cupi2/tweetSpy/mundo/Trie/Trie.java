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
	 * Constante para la serialización
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	/**
     * Raíz del trie
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
     * Construye un tree vacío.<br>
     * <b> post: </b> Se construyó un trie vacío.
     */
    public Trie( )
    {
        raiz = new NodoTrie<T>( ' ' );
    }

    /**
     * Construye un tree con la raíz especificada. <br>
     * <b> pre: </b> El caracter de la raiz debe ser ' ' y su elemento==null. <br>
     * <b> post: </b> Se construyó un trie con la raíz especificada.
     * @param r La raíz del trie
     * @param p El peso del trie (número de nodos que tiene el trie)
     */
    public Trie( NodoTrie<T> r, int p )
    {
        raiz = r;
        peso = p;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inserta un elemento dado en el trie. <br>
     * <b> pre: </b> elemento!=null. <br>
     * <b> post: </b> Se inserto el elemento dado en el trie.
     * @param elemento El elemento a ser insertado en el trie
     * @throws ElementoExisteException Si el elemento a ser insertado ya existe en el trie
     * @throws PalabraInvalidaException Si el elemento a ser insertado no es válido (su representación en String es vacía)
     */
    public void insertar( T elemento ) throws YaExisteException, PIException
    {
        String palabra = elemento.toString( );
        if( palabra.length( ) == 0 )
        {
            throw new PIException( "El elemento que se quiere ingresar no es válido" );

        }
        else
        {
            peso += raiz.insertar( palabra, elemento );
        }
    }

    /**
     * Elimina el elemento del trie cuya representación en String corresponde a la palabra dada. <br>
     * <b> pre: </b> palabra!=null. <br>
     * <b> post: </b> Se eliminó el elemento del trie cuya representación en String corresponde a la palabra dada. Si no existe tal elemento se retorna null.
     * @param palabra La palabra a ser eliminada
     * @return El elemento del trie cuya representación en String corresponde a la palabra dada. Si no existe tal elemento se retorna null.
     * @throws PalabraInvalidaException Si el elemento a ser eliminado no es válido (su representación en String es vacía)
     * 
     */
    public T eliminar( String palabra ) throws NoExisteException, PIException
    {
        if( palabra.length( ) == 0 )
        {
            throw new PIException( "El elemento que quiere eliminar es inválido" );
        }
        T elemento = buscar( palabra );
        if( elemento == null )
        {
            throw new NoExisteException( "El elemento a eliminar no se encuentra en el árbol" );
        }
        int eliminados = elemento != null ? raiz.eliminar( palabra ) : 0;
        peso -= eliminados;
        return elemento;
    }

    /**
     * Busca el elemento del trie cuya representación en String corresponte a la palabra dada. <br>
     * <b> pre: </b> palabra!=null. <br>
     * <b> post: </b> Se retornó el elemento del trie cuya representación en String corresponde a la palabra dada. Si la palabra no corresponde a ningún elemento se retorna
     * null.
     * @param palabra Palabra a ser buscada
     * @return El elemento del trie cuya representación en String corresponde a la palabra dada. Si no existe tal elemento se retorna null.
     */
    public T buscar( String palabra )
    {
        return raiz.buscar( palabra );
    }

    /**
     * Busca todos los elementos asociados con el prefijo dado.<br>
     * <b> pre: </b> prefijo!=null. <br>
     * <b> post: </b> Se retornó la lista de elementos correspondiente al prefijo dado.
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
     * Retorna el peso (número de nodos) del trie <b>post: </b> Se retornó el peso del trie
     * @return El peso del trie
     */
    public int darPeso( )
    {
        return peso;
    }

    /**
     * Retorna la alturta del trie. <br>
     * <b>post: </b> Se retornó la altura del trie.
     * @return La altura del trie
     */
    public int darAltura( )
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }

    /**
     * Retorna la raíz del trie. <br>
     * <b>post: </b> Se retornó la raíz trie.
     * @return La raíz del trie
     */
    public NodoTrie<T> darRaiz( )
    {
        return raiz;
    }
}