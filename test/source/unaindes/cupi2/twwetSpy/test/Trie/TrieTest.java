package unaindes.cupi2.twwetSpy.test.Trie;

import uniandes.cupi2.tweetSpy.mundo.Arbol23.YaExisteException;
import uniandes.cupi2.tweetSpy.mundo.Trie.PIException;
import uniandes.cupi2.tweetSpy.mundo.Trie.Trie;
import junit.framework.TestCase;

public class TrieTest extends TestCase 
{
	Trie<String> a ;
	
	public void setupScenario()
	{
		a = new Trie<String>();
	}
	
	public void testInsertar()
	{
		setupScenario();
		String enterito = "lol";
		try {
			a.insertar(enterito);
		} catch (YaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(enterito.toString());
		assertEquals("No se agrega correctamente", a.buscar(enterito.toString()),enterito.toString() );
	}

}
