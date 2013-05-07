package uniandes.cupi2.tweetSpy.mundo;

import uniandes.cupi2.tweetSpy.mundo.Compara.Compara;

public class Indice implements Compara<Indice>
{
	private String palabra;
	
	public Indice(String palabras)
	{ 	
		palabra = palabras;
	}
	
	public String darPalabra()
	{
		return palabra;
	}
	
	//Comparable

	@Override
	public int compareTo0(Indice elem) 
	{
		if (palabra.compareTo(elem.darPalabra())>0)
		{
			return 1;
		}
		else if (palabra.compareTo(elem.darPalabra())<0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public int compareTo1(Indice elem) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo2(Indice elem) {
		// TODO Auto-generated method stub
		return 0;
	}
}
