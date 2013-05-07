package uniandes.cupi2.tweetSpy.test.mundo.Arbol23;

import uniandes.cupi2.tweetSpy.mundo.Indice;
import uniandes.cupi2.tweetSpy.mundo.Usuario;
import uniandes.cupi2.tweetSpy.mundo.Arbol23.Arbol23;
import uniandes.cupi2.tweetSpy.mundo.Arbol23.NoExisteException;
import junit.framework.TestCase;

public class Arbol23Test extends TestCase 
{
	private Arbol23<Indice> arbol; 
	
	int numeroElementos ;
	
	public void setupEscenario1()
	{
		arbol = new Arbol23<Indice>();
		numeroElementos = 0;
	}
	
	public void testInsercion()
	{
		Indice nuevoIndice = new Indice("Probando");
		Indice nuevoIndice1 = new Indice("Test");
		
		setupEscenario1();
		try{
		arbol.insertar(nuevoIndice);
		arbol.insertar(nuevoIndice1);
		}
		catch(Exception e)
		{
			
		}
		
		assertTrue("No se agregan elementos", arbol.darPeso() > 0);
		assertEquals("No se agrego correctamente", arbol.buscar(nuevoIndice1) , nuevoIndice1);
		
		
	}
	
	public void testEliminacion()
	{
		Indice nuevoIndice = new Indice("Probando");
		Indice nuevoIndice1 = new Indice("Test");
		
		setupEscenario1();
		try{
			arbol.insertar(nuevoIndice);
			arbol.insertar(nuevoIndice1);
			}
			catch(Exception e)
			{
				
			}
		int peso = arbol.darPeso();
		Indice nulo = null;
		assertTrue("No se agregan elementos", arbol.darPeso() > 0);
		try {
			arbol.eliminar(nuevoIndice);
		} catch (NoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue("NO se elimina correctamente", peso >= arbol.darPeso());
		assertEquals("No se agrego correctamente", arbol.buscar(nuevoIndice),  nulo);
	}
	
	
}
