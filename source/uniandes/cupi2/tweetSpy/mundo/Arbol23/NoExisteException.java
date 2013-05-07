package uniandes.cupi2.tweetSpy.mundo.Arbol23;

public class NoExisteException extends Exception {

	
	/**
	* Constante para la serializaci�n
	*/
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	* Constructor con mensaje
	* 
	* @param mensaje Mensaje de error
	*/
	public NoExisteException( String mensaje )
	{
	super( mensaje );
	}
}

