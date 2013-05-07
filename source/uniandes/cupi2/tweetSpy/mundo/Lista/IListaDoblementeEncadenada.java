package uniandes.cupi2.tweetSpy.mundo.Lista;

public interface IListaDoblementeEncadenada <T> 
{
	/**
	 * 
	 * @param elemento
	 */
	public void agregarComienzo(T elemento);
	
	/**
	 * 
	 * @param elemento
	 */
	public void agregarFinal(T elemento);
	
	/**
	 * 
	 * @param elemento
	 */
	public void posicionMasUno(T elemento);
	
	/**
	 * 
	 * @param elemento
	 */
	public void posicionMenosUno(T elemento);
	
	/**
	 * 
	 * @param elemento
	 */
	public void eliminar(T elemento);
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public T buscar(T elemento);
	
	public Object[] darArreglo();
	
	public T dar (int pos);
	
	public int darTamanio();
}