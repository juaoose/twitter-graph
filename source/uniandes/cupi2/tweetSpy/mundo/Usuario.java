package uniandes.cupi2.tweetSpy.mundo;

import java.io.Serializable;

import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import uniandes.cupi2.tweetSpy.mundo.Compara.Compara;

public class Usuario implements Compara<Usuario>, Serializable
{
	private String nombre;
	
	private String urlImg;
	
	private String descripcion;
	
	private ResponseList<Status> estatuses;
	
	private QueryResult result;
	
	private int cuenta;
	
	/**
	 * Representa la franja del usuario.
	 * SOLO SE UTILIZA PARA LA GENERACION DEL XML.
	 */
	private String franja;
	
	public Usuario ( String nombreN, String urlImgN, String nDescripcion)
	{
		nombre = nombreN;
		urlImg = urlImgN;
		descripcion = nDescripcion;
		estatuses = null;
		cuenta = 1;
		franja = "ERROR_FRANJA";
	}
	
	public String darNombre()
	{
		return nombre;
	}
	
	public String darUrl()
	{
		return urlImg;
	}
	
	public String darDescripcion()
	{
		return descripcion;
	}
	
	
	int test = 0;
	public boolean tweeteoPalabra(String palabra)
	{
		boolean resp = false;
		for (int i = 0; i < estatuses.size() ; i++)
		{
			
			Status estatus = estatuses.get(i);
			String textillo = estatus.getText().toUpperCase();
			if (textillo.contains(palabra.toUpperCase()))
			{
				resp = true;
			}
		}test = estatuses.size();
		return resp;
	}
	
	public int darTam()
	{
		return test;
	}
	
	public void recibirTimeline(ResponseList<Status> estatusesN)
	{
		estatuses = estatusesN;
	}
	
	public void recibirQueryResult(QueryResult query)
	{
		result = query;
	}

	//Implementacion comparador
	public int compareTo0(Usuario elem) {
		if (nombre.compareTo(elem.darNombre())>0)
		{
			return 1;
		}
		else if (nombre.compareTo(elem.darNombre())<0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
		
	}
	
	/**
	 * Metodo que retorna la franja.
	 * ESTE METODO SOLO SE UTILIZA PARA LA GENERACION DEL XML.
	 * @return
	 */
	public String darFranja()
	{
		return franja;
	}
	
	/**
	 * Modifica la franja del usuario.
	 * ESTE METODO SOLO SE UTILIZA PARA LA GENERACION DEL XML.
	 * @param nueva
	 */
	public void cambiarFranja(String nueva)
	{
		franja = nueva;
	}

	@Override
	public int compareTo1(Usuario elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo2(Usuario elem) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void aumentarContador()
	{
		cuenta++;
	}
	
	public int darConteo()
	{
		return cuenta;
	}
	
	
	


}
