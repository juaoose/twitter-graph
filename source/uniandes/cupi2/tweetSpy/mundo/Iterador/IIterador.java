package uniandes.cupi2.tweetSpy.mundo.Iterador;

public interface IIterador<T> 
{

	/**
	 * Retorna si hay o no siguiente.
	 */
	public boolean haySiguente();
	
	/**
	 * Retorna si hay o no anterior.
	 */
	public boolean hayAnterior();
	
	/**
	 * Retorna el siguiente elemento.
	 */
	public T darSiguiente();
	
	/**
	 * Retorna el elemento anterior.
	 * @return
	 */
	public T darAnterior();
	
	/**
	 * Reinicia el iterador.
	 */
	public void reiniciar();
}
