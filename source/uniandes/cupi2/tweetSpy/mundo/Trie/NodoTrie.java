package uniandes.cupi2.tweetSpy.mundo.Trie;

import java.io.Serializable;

import uniandes.cupi2.tweetSpy.mundo.Arbol23.YaExisteException;
import uniandes.cupi2.tweetSpy.mundo.Iterador.Iterador;
import uniandes.cupi2.tweetSpy.mundo.Lista.ListaDoblementeEncadenada;

public class NodoTrie<T> implements Serializable
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
     * Carácter que contiene el nodo
     */
    private char caracter;

    /**
     * Elemento del nodo
     */
    private T elemento;

    /**
     * Subárbol izquierdo
     */
    private NodoTrie<T> izqNodo;

    /**
     * Subárbol correspondiente al hermano derecho del nodo actual
     */
    private NodoTrie<T> hermanoDerNodo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del trie. <br>
     * <b> post: </b> Se construyó el nodo con el caracter especificado, elemento= null, izqNodo= null y hermanoDerNodo= null.
     * @param carac Caracter que va a contener el nodo
     */
    public NodoTrie( char carac )
    {
        caracter = carac;
        izqNodo = null;
        hermanoDerNodo = null;
        elemento = null;
    }

    /**
     * Constructor del trie. <br>
     * <b> post: </b> Se construyó el nodo con el caracter y elemento especificados, izqNodo= null y hermanoDerNodo= null.
     * @param carac Caracter que va a contener el nodo
     * @param elem Elemento que contiene el nodo
     */
    public NodoTrie( char carac, T elem )
    {
        caracter = carac;
        izqNodo = null;
        hermanoDerNodo = null;
        elemento = elem;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Ingresa el elemento dado asociado con la palabra especificada en el trie del que el nodo actual es la raíz. <br>
     * <b> pre: </b> palabra!=null, elem!=null<br>
     * <b> post: </b> Se insertó el elemento asociado con la palabra especificada en el trie.
     * @param palabra Palabra con la que está asociada el elemento
     * @param elem El elemento a ser insertado
     * @return El número de nuevos nodos adicionados
     * @throws ElementoExisteException Si el elemento a ser insertado ya existe en el trie
     */
    public int insertar( String palabra, T elem ) throws YaExisteException
    {

        // Si la palabra solo tiene 1 carácter y el elemento del hijo izquierdo es igual al elemento a insertar, entonces la palabra es repetida
        if( palabra.length( ) == 1 && izqNodo != null && izqNodo.elemento != null && izqNodo.elemento.equals( elem ) )
        {
            throw new YaExisteException( "El elemento que se quiere ingresar ya existe en el trie" );
        }

        int elementosInsertados = 0;
        char nCaracter = palabra.charAt( 0 );

        if( izqNodo != null && izqNodo.caracter == nCaracter )// Si el carácter del hijo izquierdo es igual al que se quiere insertar
        {
            if( palabra.length( ) != 1 )
                return izqNodo.insertar( palabra.substring( 1 ), elem );// El hijo izquierdo es responsable de insertar el nuevo elemento
            else
                izqNodo.elemento = elem;// Se agregó un prefijo de una palabra existente
        }
        else if( izqNodo != null && izqNodo.caracter > nCaracter )
        {
            NodoTrie<T> nuevoNodo = null;
            if( palabra.length( ) == 1 )
            {
                nuevoNodo = new NodoTrie<T>( nCaracter, elem );
            }
            else
            {
                nuevoNodo = new NodoTrie<T>( nCaracter );// Nodo solo con el caracter
                elementosInsertados += nuevoNodo.insertar( palabra.substring( 1 ), elem );
            }
            // El carácter a insertar debería ser el nuevo hijo izquierdo del nodo actual
            NodoTrie<T> temp = izqNodo;
            izqNodo = nuevoNodo;
            izqNodo.hermanoDerNodo = temp;
            elementosInsertados++;
        }
        else if( izqNodo != null && izqNodo.hermanoDerNodo != null )// Se debe agregar el nodo sobre los hermanos del nodo actual
        {
            // Se mantiene el anterior por si se deben correr los nodos hermanos
            NodoTrie<T> iterador = izqNodo.hermanoDerNodo;
            NodoTrie<T> anterior = izqNodo;
            // Ubicar el lugar donde deba ir el nuevo hermano
            while( iterador != null && iterador.caracter < nCaracter )
            {
                anterior = iterador;
                iterador = iterador.hermanoDerNodo;
            }
            if( iterador != null && iterador.caracter == nCaracter )// Encontró el nodo que deba agregar el resto de la palabra
            {
                if( palabra.length( ) == 1 )
                {
                    // En caso que la palabra termine y solo deba agregar el elemento a ese nodo
                    iterador.elemento = elem;
                    elementosInsertados++;
                }
                else
                    elementosInsertados += iterador.insertar( palabra.substring( 1 ), elem );
            }
            else
            {
                NodoTrie<T> nuevoNodo = null;
                if( palabra.length( ) == 1 )
                {
                    nuevoNodo = new NodoTrie<T>( nCaracter, elem );
                }
                else
                {
                    nuevoNodo = new NodoTrie<T>( nCaracter );// Nodo solo con el caracter
                    elementosInsertados += nuevoNodo.insertar( palabra.substring( 1 ), elem );
                }
                // Se debe insertar un nuevo nodo entre dos hermanos
                // Debe crear un nuevo nodo y colocarlo
                anterior.hermanoDerNodo = nuevoNodo;// El nuevo hermanoDerecho del anterior es el nuevo nodo
                nuevoNodo.hermanoDerNodo = iterador;// El hermanoDerecho del nuevo nodo es el nodo que se tenia en iterador
                elementosInsertados++;
            }
        }
        else if( izqNodo != null && izqNodo.hermanoDerNodo == null )// No existe un hermano del nodo izquierdo
        {
            NodoTrie<T> nuevoNodo = null;
            if( palabra.length( ) == 1 )
            {
                nuevoNodo = new NodoTrie<T>( nCaracter, elem );
            }
            else
            {
                nuevoNodo = new NodoTrie<T>( nCaracter );// Nodo solo con el caracter
                elementosInsertados += nuevoNodo.insertar( palabra.substring( 1 ), elem );
            }
            izqNodo.hermanoDerNodo = nuevoNodo;
            elementosInsertados++;
        }
        else
        {
            NodoTrie<T> nuevoNodo = null;
            if( palabra.length( ) == 1 )
            {
                nuevoNodo = new NodoTrie<T>( nCaracter, elem );
            }
            else
            {
                nuevoNodo = new NodoTrie<T>( nCaracter );// Nodo solo con el caracter
                elementosInsertados += nuevoNodo.insertar( palabra.substring( 1 ), elem );
            }
            // Se agrega un nodo como el hijo izquierdo del nodo actual
            izqNodo = nuevoNodo;
            elementosInsertados++;
        }
        return elementosInsertados;
    }

    /**
     * Elimina la palabra del trie del que el nodo actual es raíz. <br>
     * <b> pre: </b> palabra!=null, longitud palabra es mayor a cero. <br>
     * <b> post: </b> Se elimino la palabra si esta existía en el trie. Si la palabra no existe se retorna false.
     * @param palabra La palabra a ser eliminada
     * @return Entero con la cantidad de nodos que fueron eliminados
     */
    public int eliminar( String palabra )
    {
        int eliminados = 0;
        char c = palabra.charAt( 0 ); // Primer carácter de la palabra

        if( izqNodo != null && izqNodo.caracter == c ) // Sólo se continúa la eliminación si el primer carácter de la palabra es igual al del nodo actual
        {
            if( palabra.length( ) > 1 )
            {
                eliminados += izqNodo.eliminar( palabra.substring( 1 ) );
                if( izqNodo.izqNodo == null && eliminados > 0 && izqNodo.elemento == null )
                {
                    izqNodo = izqNodo.hermanoDerNodo;
                    eliminados++;
                }
            }
            else
            {
                if( izqNodo.elemento != null )
                {
                    izqNodo.elemento = null;
                    if( izqNodo.izqNodo == null )
                    {
                        izqNodo = izqNodo.hermanoDerNodo;
                        eliminados++;
                    }
                }
            }
        }
        else if( izqNodo != null && izqNodo.caracter < c && izqNodo.hermanoDerNodo != null ) // Buscar si la palabra existe en otro nodo a la derecha y eliminarla
        {
            // Buscar el nodo en el que se debe realizar la eliminación
            NodoTrie<T> hermanoIzquierdo = izqNodo;
            NodoTrie<T> nodo = izqNodo.hermanoDerNodo;
            while( nodo != null && nodo.caracter != c )
            {
                hermanoIzquierdo = nodo;
                nodo = nodo.hermanoDerNodo;
            }

            if( nodo != null && palabra.length( ) > 1 )
            {
                eliminados = nodo.eliminar( palabra.substring( 1 ) );
                if( nodo.izqNodo == null && eliminados > 0 && nodo.elemento == null )
                {
                    hermanoIzquierdo.hermanoDerNodo = nodo.hermanoDerNodo;
                    eliminados++;
                }
            }
            else if( nodo != null && nodo.elemento != null )
            {
                nodo.elemento = null;
                if( nodo.izqNodo == null )
                {
                    hermanoIzquierdo.hermanoDerNodo = nodo.hermanoDerNodo;
                    eliminados++;
                }
            }
        }
        return eliminados;
    }

    /**
     * Busca el elemento del trie cuya representación en String corresponde a una palabra dada. <br>
     * <b> pre: </b> palabra!=null. <br>
     * <b> post: </b> Se retornó el elemento del trie cuya representación en String corresponde a la palabra dada. Si la palabra no corresponde a ningún elemento se retorna
     * null.
     * @param palabra Palabra a ser buscada
     * @return El elemento del trie cuya representación en String corresponde a la palabra dada. Si no existe tal elemento se retorna null.
     */
    public T buscar( String palabra )
    {
        T retorno = null;
        NodoTrie<T> aux = this.izqNodo;
        while( aux != null && palabra.length( ) != 0 )
        {
            char nCaracter = palabra.charAt( 0 );
            if( aux.caracter == nCaracter )
            {
                palabra = palabra.substring( 1 );
                if( palabra.length( ) == 0 )
                {
                    retorno = aux.elemento;
                }
                else
                {
                    aux = aux.izqNodo;
                }
            }
            else if( aux.hermanoDerNodo != null )
            {
                while( aux != null && aux.caracter != nCaracter )
                {
                    aux = aux.hermanoDerNodo;
                }
            }
            else
                aux = null;
        }
        return retorno;
    }

    /**
     * Retorna la altura del trie del que el nodo actual es raíz. <br>
     * <b>post: </b> Se retornó la altura del trie.
     * @return La altura del trie
     */
    public int darAltura( )
    {
        int altura = ( izqNodo == null ) ? 1 : izqNodo.darAltura( ) + 1;
        int aux = ( hermanoDerNodo == null ) ? 0 : hermanoDerNodo.darAltura( );

        if( aux > altura )
        {
            altura = aux;
        }
        return altura;
    }

    /**
     * Retorna el hijo izquierdo del nodo. <br>
     * <b>post: </b> Se retornó el hijo izquierdo del nodo.
     * @return Hijo izquierdo del nodo
     */
    public NodoTrie<T> darHijoIzquierdo( )
    {
        return izqNodo;
    }

    /**
     * Retorna el hermano derecho del nodo. <br>
     * <b>post: </b> Se retornó el hermano derecho del nodo.
     * @return Hermano derecho del nodo
     */
    public NodoTrie<T> darHermanoDerecho( )
    {
        return hermanoDerNodo;
    }

    /**
     * Retorna el carácter del nodo. <br>
     * <b>post: </b> Se retornó el carácter del nodo.
     * @return Carácter del nodo
     */
    public char darCaracter( )
    {
        return caracter;
    }

    /**
     * Retorna el elemento del nodo. <br>
     * <b>post: </b> Se retornó el elemento del nodo.
     * @return El elemento del nodo
     */
    public T darElemento( )
    {
        return elemento;
    }

    /**
     * Busca los elementos que tienen como prefijo la palabra especificada. <br>
     * <b> pre: </b> prefijo!= null, lista!=null. <br>
     * <b> post: </b> La lista contiene los elementos que tienen como prefijo la palabra especificada.
     * @param prefijo Prefijo de la palabra
     * @param lista La lista en la que se van a adicionar los elementos
     */
    public void buscarPorPrefijo( String prefijo, ListaDoblementeEncadenada<T> lista )
    {
        NodoTrie<T> aux = this.izqNodo;
        // Se ubica el nodo a partir del cual se deben recolectar los elementos
        while( aux != null && prefijo.length( ) != 0 )
        {
            char nCaracter = prefijo.charAt( 0 );
            if( aux.caracter == nCaracter )
            {
                prefijo = prefijo.substring( 1 );
                if( prefijo.length( ) != 0 )
                {
                    aux = aux.izqNodo;
                }
            }
            else if( aux.hermanoDerNodo != null )
            {
                while( aux != null && aux.caracter != nCaracter )
                {
                    aux = aux.hermanoDerNodo;
                }
            }
            else
                aux = null;
        }
        if( aux != null )
        {
            aux.darElementosTrie( lista );
        }

    }

    /**
     * Retorna los elementos que se encuentran en el trie, siendo la raíz el nodo actual. <br>
     * <b> pre: </b> lista!= null. <br>
     * <b> post: </b> La lista contiene los elementos del nodo del trie cuyo raíz es el nodo actual
     * @param lista La lista en la que se van a adicionar los elementos
     */
    private void darElementosTrie( ListaDoblementeEncadenada<T> lista )
    {
        if( elemento != null )
        {
            lista.agregarFinal( elemento );
        }

        if( izqNodo != null )
        {
            izqNodo.darElementosTrie( lista );
            NodoTrie<T> nodo = izqNodo.hermanoDerNodo;

            while( nodo != null )
            {
                nodo.darElementosTrie( lista );
                nodo = nodo.hermanoDerNodo;
            }
        }
    }

    /**
     * Devuelve los elementos del trie en inorden. <br>
     * <b>pre: </b> resultado!=null. <br>
     * <b>post: </b> Se retorno un iterador que contiene los elementos organizados en inorden
     * @param resultado Iterador que va a contener el resultado del recorrido.
     */
    public void inorden( Iterador<T> resultado )
    {

        if( izqNodo != null )
        {
            izqNodo.inorden( resultado );
            if( elemento != null )
            {
                try
                {
                    resultado.agregar( elemento );
                }
                catch( Exception e )
                {
                    // No debería ocurrir esta excepción
                }
            }
            NodoTrie<T> aux = izqNodo.hermanoDerNodo;
            while( aux != null )
            {
                aux.inorden( resultado );
                aux = aux.hermanoDerNodo;
            }
        }
        else
        {
            if( elemento != null )
            {
                try
                {
                    resultado.agregar( elemento );
                }
                catch( Exception e )
                {
                    // No debería ocurrir esta excepción
                }
            }
        }
    }
}

