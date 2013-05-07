package uniandes.cupi2.tweetSpy.mundo;

import uniandes.cupi2.tweetSpy.mundo.Compara.Compara;

public class Palabra implements Compara<Palabra>
{
	String palabra;
	int contador;
	
	public Palabra(String word)
	{
		palabra = word;
		contador = 0;
	}
	
	public String darPalabra()
	{
		return palabra;
	}
	
	public int darConteo()
	{
		return contador;
	}
	
	public void aumentarContador()
	{
		contador++;
	}

	@Override
	public int compareTo0(Palabra elem) 
	{
		if (palabra.compareTo(elem.darPalabra())<0)
		{
			return -1;
		}
		else if (palabra.compareTo(elem.darPalabra())>0)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public int compareTo1(Palabra elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo2(Palabra elem) {
		// TODO Auto-generated method stub
		return 0;
	}

}
