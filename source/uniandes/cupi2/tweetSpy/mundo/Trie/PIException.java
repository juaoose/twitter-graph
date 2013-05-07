package uniandes.cupi2.tweetSpy.mundo.Trie;

public class PIException extends Exception
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

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
    public PIException( String mensaje )
    {
        super( mensaje );
    }
}

