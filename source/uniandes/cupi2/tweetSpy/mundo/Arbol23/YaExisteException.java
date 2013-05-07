package uniandes.cupi2.tweetSpy.mundo.Arbol23;

public class YaExisteException extends Exception {

	
	/**
	* Constante para la serialización
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
	public YaExisteException( String mensaje )
	{
	super( mensaje );
	}
}
