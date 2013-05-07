package uniandes.cupi2.tweetSpy.mundo.Arbol23;

import java.io.Serializable;

import uniandes.cupi2.tweetSpy.mundo.Compara.Compara;
import uniandes.cupi2.tweetSpy.mundo.Iterador.Iterador;

public class Nodo23<T extends Compara<? super T>> implements Serializable 
{
	/**
     * Raíz izquierda del nodo
     */
    private T raizIzq;

    /**
     * Raíz derecha del nodo
     */
    private T raizDer;

    /**
     * Subárbol izquierdo
     */
    private Nodo23<T> hijoIzq;

    /**
     * Subárbol central
     */
    private Nodo23<T> hijoCent;

    /**
     * Subárbol derecho
     */
    private Nodo23<T> hijoDer;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo con el primer elemento. <br>
     * <b> post: </b> Se construyó el nodo con el elemento especificado.
     * @param obj Elemento a agregar al nodo
     */
    public Nodo23( T obj )
    {
        raizIzq = obj;
        raizDer = null;
        hijoIzq = null;
        hijoCent = null;
        hijoDer = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz izquierda del nodo. <br>
     * <b>post: </b> Se retornó la raíz izquierda del nodo.
     * @return Raíz izquierda del nodo.
     */
    public T darRaizIzq( )
    {
        return raizIzq;
    }

    /**
     * Devuelve la raíz derecha del nodo. <br>
     * <b>post: </b> Se retornó la raíz derecha del nodo.
     * @return Raíz derecha del nodo
     */
    public T darRaizDer( )
    {
        return raizDer;
    }

    /**
     * Devuelve el hijo izquierdo del nodo.<br>
     * <b>post: </b> Se retornó el hijo izquierdo del nodo.
     * @return Hijo izquierdo del nodo
     */
    public Nodo23<T> darHijoIzq( )
    {
        return hijoIzq;
    }

    /**
     * Devuelve el Hijo central del nodo. <br>
     * <b>post: </b> Se retornó el hijo central del nodo. <br>
     * @return Hijo central del nodo
     */
    public Nodo23<T> darHijoCent( )
    {
        return hijoCent;
    }

    /**
     * Devuelve el Hijo derecho del nodo. <br>
     * <b>post: </b> Se retornó el hijo 3 del nodo. <br>
     * @return Hijo 3 del nodo
     */
    public Nodo23<T> darHijoDer( )
    {
        return hijoDer;
    }

    /**
     * Indica si el nodo es una hoja.<br>
     * <b>post: </b> Se retornó true si el nodo es una hoja o false de lo contrario. Un nodo es una hoja si sus tres hijos se encuentran en null.
     * @return True si es hoja, False si no
     */
    public boolean esHoja( )
    {
        return hijoIzq == null && hijoCent == null && hijoDer == null;
    }

   /**
    * Retorna el elemento que es igual al de entrada.
    * @param modelo
    * @return
    */
    public T buscar( T modelo )
    {
      
        int resultado1 = modelo.compareTo0( raizIzq );
        if( resultado1 == 0 )
        {
            return raizIzq;
        }
        else if( resultado1 < 0 )
        {
            return ( hijoIzq == null ) ? null : hijoIzq.buscar( modelo );
        }
        else if( raizDer == null )
        {
            return ( hijoCent == null ) ? null : hijoCent.buscar( modelo );
        }
        else
        {
           
            int resultado2 = modelo.compareTo0( raizDer );
            if( resultado2 == 0 )
            {
                return raizDer;
            }
            else if( resultado2 < 0 )
            {
                return ( hijoCent == null ) ? null : hijoCent.buscar( modelo );
            }
            else
            {
                return ( hijoDer == null ) ? null : hijoDer.buscar( modelo );
            }
        }
    }

    /**
     * Calcula la altura del subarbol
     * @return
     */
    public int darAltura( )
    {
        if( esHoja( ) )
        {
            return 1;
        }
        else
        {
            return hijoIzq.darAltura( ) + 1;
        }
    }

    /**
     * Permite insertar un elemento al arbol.
     * @param obj
     * @return
     * @throws YaExisteException
     */
    public Nodo23<T> insertar( T obj ) throws YaExisteException
    {
        Retorno ret = new Retorno( );
        if( casosInsertar( obj, ret ) )
        {
            Nodo23<T> nodo = new Nodo23<T>( ret.val );
            nodo.hijoIzq = ret.izq;
            nodo.hijoCent = ret.der;
            return nodo;
        }
        else
        {
            return this;
        }
    }

   /**
    * Elimina el elemento que es igual al de entrada.
    * @param obj
    * @return
    * @throws NoExisteException
    */
    public Nodo23<T> eliminar( T obj ) throws NoExisteException
    {
        return auxEliminar( obj ) ? hijoIzq : this;
    }

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>pre: </b> resultado!=null. <br>
     * <b>post: </b> Se retorno un vector con el recorrido en inorden del árbol.
     * 
     * @param resultado Vector con los elementos del árbol en inorden
     */
    public void inorden( Iterador<T> resultado ) throws Exception
    {
        if( hijoIzq != null )
        {
            hijoIzq.inorden( resultado );
        }
        resultado.agregar( raizIzq );
        if( hijoCent != null )
        {
            hijoCent.inorden( resultado );
        }
        if( raizDer != null )
        {
            resultado.agregar( raizDer );
            if( hijoDer != null )
            {
                hijoDer.inorden( resultado );
            }
        }
    }

   /**
    * Revisa los casos de insercion.
    * @param elemento
    * @param ret
    * @return
    * @throws YaExisteException
    */
    private boolean casosInsertar( T elemento, Retorno ret ) throws YaExisteException
    {
        // Compara el elemento con los 2 elementos
        int resultado1 = elemento.compareTo0( raizIzq );
        int resultado2 = ( raizDer == null ) ? 0 : elemento.compareTo0( raizDer );

        // Verifica que el elemento que llega no se encuentre en el nodo
        if( resultado1 == 0 || ( raizDer != null && resultado2 == 0 ) )
        {
            throw new YaExisteException( "El elemento ya existe en el árbol" );
        }
        else if( esHoja( ) )
        {
            return insHoja( elemento, ret );
        }
        else if( resultado1 < 0 )
        {
            return hijoIzq.casosInsertar( elemento, ret ) ? subirInfoIzq( ret ) : false;
        }
        else if( raizDer == null || resultado2 < 0 )
        {
            return hijoCent.casosInsertar( elemento, ret ) ? subirInfoCent( ret ) : false;
        }
        else
        {
            return hijoDer.casosInsertar( elemento, ret ) ? subirInfoDer( ret ) : false;
        }
    }

    /**
     * Inserta un elemento en una hoja del árbol. Si debe aumentar de altura retorna true, y en la estructura de retorno envía los dos nodos en los que se dividió la hoja y la
     * raíz del nuevo nivel. <br>
     * <b>post: </b> Se insertó un elemento en el árbol si este no existía previamente en la estructura. Se retornó true si se debe aumentar la altura o false de lo contrario.
     * 
     * @param elemento Elemento a insertar
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False sino
     */
    private boolean insHoja( T elemento, Retorno ret )
    {
        if( raizDer == null )
        {
            // Caso 1: hay espacio en el nodo: no hay que aumentar un nivel.
            // Basta con ordenar las raíces
            if( elemento.compareTo0( raizIzq ) < 0 )
            {
                raizDer = raizIzq;
                raizIzq = elemento;
            }
            else
            {
                raizDer = elemento;
            }
            return false;
        }
        else
        {
            // Caso 2: no hay espacio en el nodo y se debe partir
            if( elemento.compareTo0( raizIzq ) < 0 )
            {
                // Sube la raíz 1
                ret.val = raizIzq;
                ret.izq = new Nodo23<T>( elemento );
                raizIzq = raizDer;
                raizDer = null;
                ret.der = this;
            }
            else if( elemento.compareTo0( raizDer ) < 0 )
            {
                // Sube el elemento nuevo
                ret.val = elemento;
                ret.izq = new Nodo23<T>( raizIzq );
                raizIzq = raizDer;
                raizDer = null;
                ret.der = this;
            }
            else
            {
                // Sube la raíz 2
                ret.val = raizDer;
                ret.der = new Nodo23<T>( elemento );
                raizDer = null;
                ret.izq = this;
            }
            return true;
        }
    }

    /**
     * La inserción se hizo sobre el primer subárbol, vienen subiendo en la estructura de retorno un elemento y dos subárboles. <br>
     * <b>post: </b> Se retornó true si se debe aumentar la altura del árbol o false de lo contrario.
     * 
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfoIzq( Retorno ret )
    {
        if( raizDer == null )
        {
            // Hay campo en este nodo: solo hay que reorganizar
            raizDer = raizIzq;
            hijoDer = hijoCent;
            raizIzq = ret.val;
            hijoIzq = ret.izq;
            hijoCent = ret.der;
            return false;
        }
        else
        {
            // No hay campo en el nodo: hay que partir y volver a subir
            Nodo23<T> nodo = new Nodo23<T>( raizDer );
            nodo.hijoIzq = hijoCent;
            nodo.hijoCent = hijoDer;
            T temp = raizIzq;
            raizIzq = ret.val;
            raizDer = null;
            hijoIzq = ret.izq;
            hijoCent = ret.der;
            hijoDer = null;
            ret.val = temp;
            ret.izq = this;
            ret.der = nodo;
            return true;
        }
    }

    /**
     * La inserción se hizo sobre el segundo subárbol, vienen subiendo en la estructura de retorno un elemento y dos subárboles. <br>
     * <b>post: </b> True si se debe aumentar la altura del árbol o false de lo contrario.
     * 
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfoCent( Retorno ret )
    {
        if( raizDer == null )
        {
            // Hay campo en este nodo: solo hay que reorganizar
            raizDer = ret.val;
            hijoCent = ret.izq;
            hijoDer = ret.der;
            return false;
        }
        else
        {
            // No hay campo en el nodo: hay que partir y volver a subir
            Nodo23<T> nodo = new Nodo23<T>( raizDer );
            nodo.hijoIzq = ret.der;
            nodo.hijoCent = hijoDer;
            hijoCent = ret.izq;
            hijoDer = null;
            raizDer = null;
            ret.izq = this;
            ret.der = nodo;
            return true;
        }
    }

    /**
     * La inserción se hizo sobre el tercer subárbol, vienen subiendo en la estructura de retorno un elemento y dos subárboles. <br>
     * <b>post: </b> Se retornó true si se debe aumentar la altura del árbol o false de lo contrario.
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfoDer( Retorno ret )
    {
        // No hay campo en el nodo: hay que partir y volver a subir
        Nodo23<T> nodo = new Nodo23<T>( ret.val );
        nodo.hijoIzq = ret.izq;
        nodo.hijoCent = ret.der;
        ret.val = raizDer;
        raizDer = null;
        hijoDer = null;
        ret.izq = this;
        ret.der = nodo;
        return true;
    }

    /**
     * Elimina un elemento del árbol 2-3. Retorna true si el árbol resultado ha perdido un nivel. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se retornó si la altura del árbol disminuye o false de lo contrario.
     * 
     * @param obj Elemento a eliminar
     * @return True si la altura cambió, False si no
     * @throws ElementoNoExisteException El elemento especificado no existe en el nodo
     */
    private boolean auxEliminar( T obj ) throws NoExisteException
    {
        int resultado1 = obj.compareTo0( raizIzq );
        int resultado2 = ( raizDer == null ) ? 0 : obj.compareTo0( raizDer );
        if( resultado1 == 0 )
        {
            if( esHoja( ) )
            {
                if( raizDer == null )
                {
                    return true;
                }
                else
                {
                    raizIzq = raizDer;
                    raizDer = null;
                    return false;
                }
            }
            else
            {
                T menor = hijoCent.calcularMenorElem( );
                raizIzq = menor;
                return hijoCent.auxEliminar( menor ) ? restaurarHijoCent( ) : false;
            }
        }
        else if( raizDer != null && resultado2 == 0 )
        {
            if( esHoja( ) )
            {
                raizDer = null;
                return false;
            }
            else
            {
                T menor = hijoDer.calcularMenorElem( );
                raizDer = menor;
                return hijoDer.auxEliminar( menor ) ? restaurarHijoDer( ) : false;
            }
        }
        else if( resultado1 < 0 && hijoIzq != null )
        {
            return hijoIzq.auxEliminar( obj ) ? restaurarHijoIzq( ) : false;
        }
        else if( ( raizDer == null || resultado2 < 0 ) && hijoCent != null )
        {
            return hijoCent.auxEliminar( obj ) ? restaurarHijoCent( ) : false;
        }
        else if( hijoDer != null )
        {
            return hijoDer.auxEliminar( obj ) ? restaurarHijoDer( ) : false;
        }
        else
            throw new NoExisteException( "El elemento especificado no existe en el árbol" );

    }

    /**
     * Retorna el menor elemento del árbol 2-3 cuya raíz es este nodo. <br>
     * <b>post: </b> Se retornó el menor elemento del árbol cuya raíz es este nodo.
     * 
     * @return Menor elemento del árbol
     */
    private T calcularMenorElem( )
    {
        Nodo23<T> aux = this;
        while( aux.hijoIzq != null )
            aux = aux.hijoIzq;
        return aux.raizIzq;
    }

    /**
     * Se ha eliminado un elemento del hijo 1, y por esta razón se ha perdido altura. Debe rebalancear la información del nodo actual. <br>
     * <b>post: </b> Se retornó true si es necesario rebalancear el árbol o false de lo contrario
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurarHijoIzq( )
    {
        hijoIzq.raizIzq = raizIzq;
        raizIzq = hijoCent.raizIzq;
        hijoIzq.hijoCent = hijoCent.hijoIzq;
        hijoCent.hijoIzq = hijoCent.hijoCent;
        hijoCent.hijoCent = null;
        if( hijoCent.raizDer != null )
        {
            hijoCent.raizIzq = hijoCent.raizDer;
            hijoCent.raizDer = null;
            hijoCent.hijoCent = hijoCent.hijoDer;
            hijoCent.hijoDer = null;
            return false;
        }
        else
            return restaurarHijoCent( );
    }

    /**
     * Se ha eliminado un elemento del hijo 2, y por esa razón se ha perdido altura. Debe rebalancear la información del nodo actual. <br>
     * <b>post: </b> Se retornó true si es necesario rebalancear el árbol o false de lo contrario.
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurarHijoCent( )
    {
        if( raizDer != null )
        {
            hijoCent.raizIzq = raizDer;
            hijoCent.hijoCent = hijoDer.hijoIzq;
            raizDer = hijoDer.raizIzq;
            hijoDer.hijoIzq = hijoDer.hijoCent;
            if( hijoDer.raizDer != null )
            {
                hijoDer.raizIzq = hijoDer.raizDer;
                hijoDer.raizDer = null;
                hijoDer.hijoCent = hijoDer.hijoDer;
                hijoDer.hijoDer = null;
                return false;
            }
            else
            {
                return restaurarHijoDer( );
            }
        }
        else if( hijoIzq.raizDer != null )
        {
            hijoCent.raizIzq = raizIzq;
            raizIzq = hijoIzq.raizDer;
            hijoIzq.raizDer = null;
            hijoCent.hijoCent = hijoCent.hijoIzq;
            hijoCent.hijoIzq = hijoIzq.hijoDer;
            hijoIzq.hijoDer = null;
            return false;
        }
        else
        {
            hijoIzq.raizDer = raizIzq;
            hijoIzq.hijoDer = hijoCent.hijoIzq;
            return true;
        }
    }

    /**
     * Se ha eliminado un elemento del hijo 3, y por esta razón se ha perdido altura. Debe rebalancear la información del nodo actual. <br>
     * <b>post: </b> Se retornó true si es necesario rebalancear el árbol o false de lo contrario.
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurarHijoDer( )
    {
        if( hijoCent.raizDer != null )
        {
            hijoDer.raizIzq = raizDer;
            raizDer = hijoCent.raizDer;
            hijoCent.raizDer = null;
            hijoDer.hijoCent = hijoDer.hijoIzq;
            hijoDer.hijoIzq = hijoCent.hijoDer;
            hijoCent.hijoDer = null;
        }
        else
        {
            hijoCent.raizDer = raizDer;
            hijoCent.hijoDer = hijoDer.hijoIzq;
            raizDer = null;
            hijoDer = null;
        }
        return false;
    }


 // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    /**
     * Contiene dos nodos de árbol 2-3, que vienen desplazándose como parte del proceso de modificación
     */
    private class Retorno
    {
        // -----------------------------------------------------------------
        // Atributos
        // -----------------------------------------------------------------
        /**
         * Valor que va subiendo
         */
        private T val;

        /**
         * Nodo izquierdo desplazándose
         */
        private Nodo23<T> izq;

        /**
         * Nodo derecho desplazándose
         */
        private Nodo23<T> der;

        // -----------------------------------------------------------------
        // Constructores
        // -----------------------------------------------------------------

        /**
         * Constructor del retorno
         */
        private Retorno( )
        {
            val = null;
            izq = null;
            der = null;

        }
    }
}
