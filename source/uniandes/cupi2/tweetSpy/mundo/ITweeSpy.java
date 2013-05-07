package uniandes.cupi2.tweetSpy.mundo;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import uniandes.cupi2.tweetSpy.mundo.Lista.ListaDoblementeEncadenada;

public interface ITweeSpy 
{
	/**
	 * Envia un tweet o actualiza el estado de el usuario por el parametro de entrada.
	 * @param trino
	 * @throws TwitterException
	 */
	public void enviarTrino(String trino) throws TwitterException;
	
	/**
	 * Retorna la url en forma de string de la imagen de el usuario que se encuentre autenticado.
	 * @return
	 * @throws IllegalStateException
	 * @throws TwitterException
	 */
	public String darImagenUsuario() throws IllegalStateException, TwitterException;
	
	/**
	 * Retorna el nombre para mostrar de la cuenta del usuario autenticado.
	 * @return
	 * @throws IllegalStateException
	 * @throws TwitterException
	 */
	public String darUsername() throws IllegalStateException, TwitterException;
	
	/**
	 * Retorna la lista de Usuarios que figuran como amigos de el usuario autenticado.
	 * @return
	 * @throws TwitterException
	 */
	public ListaDoblementeEncadenada<Usuario> darListaAmigos()  throws TwitterException;
	
	/**
	 * Retorna la lista de Usuarios que figuran como seguidores del usuario autenticado.
	 * @return
	 * @throws TwitterException
	 */
	public ListaDoblementeEncadenada<Usuario> darListaSeguidores() throws TwitterException;
    
	/**
	 * Crea un nuevo indice por palabra, frase o hashtag.
	 * @param indices
	 */
	public void crearIndices(String indices);
	
	/**
	 * Retorna el arreglo de indices creados.
	 * @return
	 */
	public Indice[] darIndices();
	
	/**
	 * Retorna la lista de usuario que tienen en su timeline el indice por entrada 
	 * @param indice
	 * @return
	 */
	public ListaDoblementeEncadenada<Usuario> darUsuariosEnIndice(String indice);
	
	/**
	 * Recupera el timeline del usuario.
	 * @return
	 */
	public ResponseList<Status> darTimeLine();
	
	 /**
     * Recupera la informacion de  las horas en las que se ha twitteado.
     */
	public void cargarFranjas();
	
	 /**
     * Recupera la informacion de las palabras que el usuario ha twitteado.
     */
	public void cargarPalabras();
	
	
	   /**
	 * Retorna la franja en la que mas se twittea (la primera vez que se llama despues de cargar
	 * ), despues retorna el segundo y asi sucesivamente.
	 * @return
	 */
	public Palabra darFranja();
	
	/**
     * Retorna la palabra mas mencionada (la primera vez que se llama despues de cargar
     * ), despues retorna el segundo y asi sucesivamente.
     * @return
     */
	public Palabra darPalabraMasUsada();
	
	/**
     * Retorna el usuario mas mencionado (la primera vez que se llama despues de cargar
     * los mencionados), despues retorna el segundo y asi sucesivamente.
     * @return
     */
	public Usuario darMas();
	
    /**
     * Recupera la informacion de los usuarios mencionados.
     * @throws TwitterException
     */
	public void cargarMasMencionados() throws TwitterException;
	
	/**
	 * Guarda un usuario autenticado en la lista que sera serializada.
	 * SOLO SE UTILIZA PARA LA SERIALIZACION DE USUARIOS Y GENERACION DE XML
	 * @param nombre
	 * @param franja
	 */
	public void guardarUsuario(String nombre, String franja);
	
	/**
	 * Genera el documento XML con la informacion de las categorias de usuario y los usuarios que pertenecen a cada una 
	 * (Solo aquellos que han iniciado sesion en la aplicacion)
	 */
	public void escribirXML();
	
	/**
	 * Limpia la informacion del usuario al "cerrar sesion".
	 */
	public void wipe();
	
	
}
