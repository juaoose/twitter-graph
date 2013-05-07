/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n16_tweetSpy
 * Autor: Juan Camilo Munoz - Juan José Rodríguez - 22-abr-2013 (v1) - 30-abr-2013(v2)
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.tweetSpy.mundo;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import twitter4j.IDs;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.examples.oauth.GetAccessToken;
import uniandes.cupi2.tweetSpy.mundo.Arbol23.Arbol23;
import uniandes.cupi2.tweetSpy.mundo.Arbol23.NoExisteException;
import uniandes.cupi2.tweetSpy.mundo.Arbol23.YaExisteException;
import uniandes.cupi2.tweetSpy.mundo.Iterador.Iterador;
import uniandes.cupi2.tweetSpy.mundo.Lista.ListaDoblementeEncadenada;

@SuppressWarnings("deprecation")
public class TweeSpy
{
	
	 // -----------------------------------------------------------------
    // Singleton
    // -----------------------------------------------------------------

    /**
     * Instancia única de la clase
     */
    private static TweeSpy instancia;
    
    // -----------------------------------------------------------------
    // Atributos y Constantes
    // -----------------------------------------------------------------
    /**
     * Representa la ruta para el archivo XML.
     * NOTA: Por la configuracion de JBoss, la ruta sera el escritorio.
     */
    private static String PATH_XML = "";
    
    /**
     * Representa la ruta de serializacion.
     * NOTA: Por la configuracion de JBoss, la ruta sera el escritorio.
     */
    private static String PATH_SERIAL = "";
    
    /**
     * Usuario sobre el que se guarda la autenticacion.
     */
    public static Twitter user;
    
    /**
     * Aqui se guardaran los ultimos status del usuario autenticado.
     */
    public ResponseList<Status> timeline ;
    
    /**
     * Representa el archivo donde se guarda la informacion de las franjas.
     */
    private File archivoXml;
    
    /**
     * Arbol que contiene los usuario que se han autenticado en la aplicacion. (Es serializado)
     */
    private static Arbol23<Usuario> listaUsuarios;

    /**
     * Representa la lista de amigos del usuario.
     */
    private ListaDoblementeEncadenada<Usuario> listaAmigos = null;
    
    /**
     * Representa la lista de seguidores del usuario.
     */
    private ListaDoblementeEncadenada<Usuario> listaSeguidores = null;
    
    /**
     * Representa el arbol de los indices que han sido creados.
     */
    private Arbol23<Indice> arbolIndices;
    
    /**
     * Representa el arbol que contiene los usuarios que han sido mencionados.
     */
    private Arbol23<Usuario> arbolMenciones ;
    
    /**
     * Representa el arbol donde se almacenan las palabras usadas.
     */
    private Arbol23<Palabra> arbolPalabras;
    
    /**
     * Representa el arbol que contiene la informacion de las franjas.
     */
    private Arbol23<Palabra> arbolFranjas;
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     *  Constructor de la clase.
     *  Inicializa los arboles y contenedores, los archivos de serializacion y XML.
     */
    private TweeSpy(Twitter usuario )
    {
    	user = usuario;
    	arbolIndices = new Arbol23<Indice>();
    	arbolMenciones =  new Arbol23<Usuario>();
    	arbolPalabras = new Arbol23<Palabra>();
    	arbolFranjas = new Arbol23<Palabra>();
    	timeline = null;
    	
    	String desktopPath = System.getProperty("user.home") + "/Desktop";
    	 String ruta = desktopPath.replace("\\", "/");
    	 PATH_XML =  ruta+"/archivoXml.xml";
    	 PATH_SERIAL = ruta+"/serialFile";
    	 System.out.println(ruta);
    	
    	archivoXml = new File(PATH_XML);
    	try {
			archivoXml.createNewFile();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println(archivoXml.getAbsolutePath());
    }


    /**
	 * Devuelve la instancia única de la clase
	 * @return Instancia única de la clase
	 * @throws DatosException Excepción al crear la instancia del colegio
	 */
	public static TweeSpy darInstancia( ) 
	{
	    if( instancia == null )
	    {
	    	Twitter nuevo = null;
	        instancia = new TweeSpy( nuevo );
	    }
	    return instancia;
	}

	/**
	 * Cambia la instancia del singleton por la de entrada.
	 * @param nuevo
	 */
	public static void cambioInicialInstancia(Twitter nuevo)
	{
		user = nuevo;
		serialUsuarios();
	}

	/**
	 * Recupera el timeline del usuario.
	 * @return
	 */
	public ResponseList<Status> darTimeLine()
    {
    	
    	if (timeline == null)
    	{
    		ResponseList<Status> resp = null;
    		try {
				resp = user.getUserTimeline();
			} catch (TwitterException e) {
				// 
				e.printStackTrace();
			}
			return resp;
    	}
    	else
    	{
    		return timeline;
    	}
    }
	
    /**
     * Permite enviar un tweet.
     * @param trino
     * @throws TwitterException
     */
    public void enviarTrino(String trino) throws TwitterException 
    {
    	Status status = user.updateStatus(trino);
    }
    
    /**
     * Retorna la URL de la imagen de usuario.
     * @return
     * @throws IllegalStateException
     * @throws TwitterException
     */
    public String darImagenUsuario() throws IllegalStateException, TwitterException
    {
    	String ruta = "";
    	User user1 = null;
    	user1 = user.showUser(user.getId());
    	ruta = user1.getProfileImageURL().toString();
    	return ruta;
    }
    /**
     * Recupera la informacion de  las horas en las que se ha twitteado.
     */
    @SuppressWarnings("deprecation")
	public void cargarFranjas()
    {
    	ResponseList<Status> timeline = darTimeLine();
    	
    	//Creo la franjas y su contenedor para contarlas.
    	Palabra madrugada = new Palabra("Madrugador");
    	Palabra dia = new Palabra("Mañanero");
    	Palabra tarde = new Palabra("Poco productivo");
    	Palabra noche = new Palabra("Trasnochador");
    	
    	for ( int i = 0 ; i < timeline.size() ; i++)
    	{
    		Status estadoCheck = timeline.get(i);
    		int hora = estadoCheck.getCreatedAt().getHours();
    		
    		//iniciarcontadores
    		try{
    		arbolFranjas.insertar(madrugada);
    		arbolFranjas.insertar(dia);
    		arbolFranjas.insertar(noche);
    		arbolFranjas.insertar(tarde);}
    		catch(Exception e)
    		{
    			
    		}
    		
    		if (hora < 5)
    		{
    			try
    			{
    				arbolFranjas.insertar(madrugada);
    			}
    			catch (YaExisteException e)
    			{
    				Palabra madruga = arbolFranjas.buscar(madrugada);
    				madruga.aumentarContador();
    			}
    		}
    		else if ( hora >= 5 && hora < 12)
    		{
    			try
    			{
    				arbolFranjas.insertar(dia);
    			}
    			catch (YaExisteException e)
    			{
    				Palabra day = arbolFranjas.buscar(dia);
    				day.aumentarContador();
    			}
    		}
    		else if ( hora >= 12 && hora < 19)
    		{
    			try
    			{
    				arbolFranjas.insertar(tarde);
    			}
    			catch (YaExisteException e)
    			{
    				Palabra tard = arbolFranjas.buscar(tarde);
    				tard.aumentarContador();
    			}
    		}
    		else
    		{
    			try
    			{
    				arbolFranjas.insertar(noche);
    			}
    			catch (YaExisteException e)
    			{
    				Palabra nit = arbolFranjas.buscar(noche);
    				nit.aumentarContador();
    			}
    		}
    		
    		
    	}
    }
    
    /**
     * Recupera la informacion de las palabras que el usuario ha twitteado.
     */
    public void cargarPalabras()
    {
    	ResponseList<Status> menciones = darTimeLine();
    	for (int i = 0 ; i < menciones.size() ; i++)
    	{
    		Status status = menciones.get(i);
    		String[] texto = status.getText().split(" ");
    		for (int ju = 0; ju < texto.length ; ju ++)
    		{
    			String palabra = texto[ju];
    			if ( !palabra.contains("@"))
    			{
    			Palabra nuevaP = new Palabra ( palabra);
    			try 
    			{
					arbolPalabras.insertar(nuevaP);
					System.out.println("Se agrega "+nuevaP.darPalabra());
				} 
    			catch (YaExisteException e) 
    			{
    				
					//nuevaP.aumentarContador();
    				Palabra existente = arbolPalabras.buscar(nuevaP);
    				existente.aumentarContador();
    				System.out.println("Aumenta el contador para" +existente.darPalabra());
					System.out.println("el contador va en :" + existente.darConteo());
				}
    			}
    		}
    	}
    }
    
    /**
	 * Retorna la franja en la que mas se twittea (la primera vez que se llama despues de cargar
	 * ), despues retorna el segundo y asi sucesivamente.
	 * @return
	 */
	public Palabra darFranja()
	{
		//Iterador
		Iterador<Palabra> iter = arbolFranjas.inorden();
		Object[] listaa = iter.darElementos();
		Palabra[] list = new Palabra[listaa.length];
		for (int j = 0; j< list.length ; j++)
		{
			Palabra classcast = (Palabra) listaa[j];
			list[j] = classcast;
		}
		//
		Palabra respuesta = null;
		//
		int mayor = 0;
		Palabra mayorU = null;
		
		for (int i = 0; i<list.length ; i++)
		{
			Palabra a = list[i];
			if (mayorU != null)
			{
				if (a.darConteo() > mayorU.darConteo())
				{
					mayorU = a;
					mayor = a.darConteo();
				}
			}
			else
			{
				Palabra first = list[0];
				mayorU =  first;
				mayor = first.darConteo();
			}
		}
		Palabra resp = mayorU;
		try {
			arbolFranjas.eliminar(mayorU);
		} catch (NoExisteException e) {
			
			e.printStackTrace();
		}
		return resp;
	}


	/**
     * Retorna la palabra mas mencionada (la primera vez que se llama despues de cargar
     * ), despues retorna el segundo y asi sucesivamente.
     * @return
     */
    public Palabra darPalabraMasUsada()
	{
		Iterador<Palabra> iter = arbolPalabras.inorden();
		Object[] listaa = iter.darElementos();
		Palabra[] list = new Palabra[listaa.length];
		for (int j = 0; j< list.length ; j++)
		{
			Palabra classcast = (Palabra) listaa[j];
			list[j] = classcast;
		}
		//
		Palabra respuesta = null;
		//
		int mayor = 0;
		Palabra mayorU = null;
		
		for (int i = 0; i<list.length ; i++)
		{
			Palabra a = list[i];
			//System.out.println(a.darPalabra()+" "+a.darConteo());
			if (mayorU != null)
			{
				if (a.darConteo() > mayorU.darConteo())
				{
					mayorU = a;
					mayor = a.darConteo();
				}
			}
			else
			{
				Palabra first = list[0];
				mayorU =  first;
				mayor = first.darConteo();
			}
		}
		Palabra resp = mayorU;
		try {
			arbolPalabras.eliminar(mayorU);
		} catch (NoExisteException e) {
			
			e.printStackTrace();
		}
		return resp;
	}


	/**
     * Retorna el usuario mas mencionado (la primera vez que se llama despues de cargar
     * los mencionados), despues retorna el segundo y asi sucesivamente.
     * @return
     */
    public Usuario darMas()
	{
		//Iterador
		Iterador<Usuario> iter = arbolMenciones.inorden();
		Object[] listaa = iter.darElementos();
		Usuario[] list = new Usuario[listaa.length];
		for (int j = 0; j< list.length ; j++)
		{
			Usuario classcast = (Usuario) listaa[j];
			list[j] = classcast;
		}
		//
		Usuario respuesta = null;
		//
		int mayor = 0;
		Usuario mayorU = null;
		
		for (int i = 0; i<list.length ; i++)
		{
			Usuario a = list[i];
			if (mayorU != null)
			{
				if (a.darConteo() > mayorU.darConteo())
				{
					mayorU = a;
					mayor = a.darConteo();
				}
			}
			else
			{
				Usuario first = list[0];
				mayorU =  first;
				mayor = first.darConteo();
			}
		}
		Usuario resp = mayorU;
		try {
			arbolMenciones.eliminar(mayorU);
		} catch (NoExisteException e) {
			// 
			e.printStackTrace();
		}
		return resp;
	}
    /**
     * Recupera la informacion de los usuarios mencionados.
     * @throws TwitterException
     */
	public void cargarMasMencionados() throws TwitterException
    {
    	
    	ResponseList<Status> menciones = darTimeLine();
    	for (int i = 0 ; i < menciones.size() ; i++)
    	{
    		Status status = menciones.get(i);
    		UserMentionEntity[] nombreM = status.getUserMentionEntities();
    		for (int calmese = 0; calmese < nombreM.length ; calmese++)
    		{
    			String usuarioMencionado = nombreM[calmese].getScreenName();
    			User u = user.showUser(usuarioMencionado);
    			Usuario nuevo = new Usuario(u.getName(), u.getOriginalProfileImageURL(), "");
    			
    			try 
    			{
					arbolMenciones.insertar(nuevo);
				} 
    			catch (YaExisteException e) 
    			{
					nuevo.aumentarContador();
				}
    		}
    	}
    }
    
    
    /**
     * Retorna el nombre para mostrar del usuario.
     * @return
     * @throws IllegalStateException
     * @throws TwitterException
     */
    public String darUsername() throws IllegalStateException, TwitterException
    {
    	String resp = "";
    	resp = user.getScreenName();
    	return resp;
    }
    
    /**
     * Retorna la lista de amigos del usuario (menor uso de banda)
     * @return
     */
    public ListaDoblementeEncadenada<Usuario> darAmigos()
    {
    	ListaDoblementeEncadenada<Usuario> resp = null;
    	if (listaAmigos != null)
    	{
    		return listaAmigos;
    	}
    	else
    	{
    		try {
				resp = darListaAmigos();
			} catch (TwitterException e) {
				
				e.printStackTrace();
			}
    		return resp;
    	}
    }
    
    /**
     * Retorna la lista de usuarios cuyo nombre tiene el prefijo indicado.
     * @param prefijo
     * @return
     */
    public ListaDoblementeEncadenada<Usuario> darPrefijo(String prefijo)
    {
    	ListaDoblementeEncadenada<Usuario> respuesta = new ListaDoblementeEncadenada<Usuario>();
    	ListaDoblementeEncadenada<Usuario> amigos = darAmigos();
    	
    	for (int i = 0; i < amigos.darTamanio() ; i++)
    	{
    		Usuario compara = amigos.dar(i);
    		if (compara.darNombre().toUpperCase().startsWith(prefijo.toUpperCase()))
    		{
    			respuesta.agregarFinal(compara);
    		}
    	}
    	return respuesta;
    }
    
    /**
     * Retorna la lista de amigos del usuario.
     * @return
     * @throws TwitterException
     */
    public ListaDoblementeEncadenada<Usuario> darListaAmigos() throws TwitterException
    {
    	listaAmigos = new ListaDoblementeEncadenada<Usuario>();
    	 User u1 = null ;
         long cursor = -1;
         IDs ids;
         String resp = user.getScreenName();
         do {
        	
                 ids = user.getFriendsIDs(resp, cursor);
             for (long id : ids.getIDs()) {
                 //System.out.println(id);
                 User useri = user.showUser(id);
                 String nombre = useri.getName();
                 String name = useri.getScreenName();
                 String url = useri.getOriginalProfileImageURL();
                 String desc = useri.getDescription();
                 //Para buscaar por indices
                 ResponseList<Status> estatuses = user.getUserTimeline(name);
            
                 
                 Usuario nuevoAmigo = new Usuario(nombre, url, desc);
                 nuevoAmigo.recibirTimeline(estatuses);
                 listaAmigos.agregarFinal(nuevoAmigo);
                 // contenedor?
             }
         } while ((cursor = ids.getNextCursor()) != 0);
         
         return listaAmigos;
    }
    
    /**
     * Retorna la lista de seguidores del usuario.
     */
    public ListaDoblementeEncadenada<Usuario> darListaSeguidores() throws TwitterException
    {
    	listaSeguidores = new ListaDoblementeEncadenada<Usuario>();
    	 User u1 = null ;
         long cursor = -1;
         IDs ids;
         String resp = user.getScreenName();
         
         do {
        	
                 ids = user.getFollowersIDs(resp, cursor);
             for (long id : ids.getIDs()) {
                 //System.out.println(id);
                 User useri = user.showUser(id);
                 String nombre = useri.getName();
                 String url = useri.getOriginalProfileImageURL();
                 String desc = useri.getDescription();
                 Usuario nuevoAmigo = new Usuario(nombre, url, desc);
                 listaSeguidores.agregarFinal(nuevoAmigo);
                 // contenedor?
             }
         } while ((cursor = ids.getNextCursor()) != 0);
         
         return listaSeguidores;
    }
    
    /**
     * Retorna la instancia autenticada.
     * @return
     */
    public Twitter darTwitter()
    {
    	return user;
    }
    
    /**
     * Crea un indice.
     * @param indices
     */
    public void crearIndices(String indices)
    {
    	
    		Indice nuevo = new Indice(indices);
    		try 
    		{
				arbolIndices.insertar(nuevo);
			} 
    		catch (YaExisteException e) 
    		{
				// 
				e.printStackTrace();
			}
    	
    	
    }
    
    /**
     * Retorna los indices que han sido creados.
     * @return
     */
    public Indice[] darIndices()
    {
    	
        Iterador<Indice> iteratore = arbolIndices.inorden();
        Object[] nose = iteratore.darElementos();
        
        Indice[] pruebaaaa = new Indice[nose.length];
		for (int i = 0 ; i < nose.length; i++)
		{
			if (nose[i] != null)
			pruebaaaa[i] =(Indice) nose[i];
			
			
		}
		return pruebaaaa;
    }
    
    /**
     * Retorna la lista de Usuarios que se encuantran en cierto indice.
     * @param indice
     * @return
     */
    public ListaDoblementeEncadenada<Usuario> darUsuariosEnIndice(String indice)
    {
	
    	ListaDoblementeEncadenada<Usuario> listaIndice = new ListaDoblementeEncadenada<Usuario>();
    	try{ 
	

            //Para buscaar por indices
            
            Query query = new Query(indice);
            QueryResult result ;
            
            
				result = user.search(query);
			

            
            
            List<Status> tweets = result.getTweets();
            for (int i=0;i<tweets.size();i++)
            {
            	Status tweet =(Status) tweets.get(i);
            	String nombre = tweet.getUser().getName();
            	Usuario nuevoAmigo = new Usuario(nombre, "" , "");
            	listaIndice.agregarFinal(nuevoAmigo);
            }
            
            // contenedor?
        
   
    	}catch(Exception e){}
    	return listaIndice;
    }

//-----------------------------------------------------------------
// Métodos
//-----------------------------------------------------------------
/**
 * Serializa la lista de usuarios.
 */
public static void serialUsuarios()
{
	File archivo = new File( PATH_SERIAL );
    if( archivo.exists( ) )
    {
        // El archivo existe: se debe recuperar de allí el estado del modelo del mundo
        try
        {
            ObjectInputStream ois = new ObjectInputStream( new FileInputStream( archivo ) );
            listaUsuarios = ( Arbol23<Usuario> )ois.readObject( );
            ois.close( );
        }
        catch( Exception e )
        {
            
        }
    }
    else
    {
        // El archivo no existe: es la primera vez que se ejecuta el programa
        listaUsuarios = new Arbol23<Usuario>();
    }
}

/**
 * Guarda un usuario autenticado en la lista que sera serializada.
 * SOLO SE UTILIZA PARA LA SERIALIZACION DE USUARIOS Y GENERACION DE XML
 * @param nombre
 * @param franja
 */
public void guardarUsuario(String nombre, String franja)
{
	String nom = nombre;
	String fr = franja;
	Usuario n = new Usuario(nombre, "", "");
	n.cambiarFranja(fr);
	try
	{
		listaUsuarios.insertar(n);
	} catch (YaExisteException e) {
		
		
	}
	
	
}

/**
 * Genera el documento XML con la informacion de las categorias de usuario y los usuarios que pertenecen a cada una 
 * (Solo aquellos que han iniciado sesion en la aplicacion)
 */
public void escribirXML()
{
	try{
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    Element el;
    DocumentBuilder db = null;
    Document doc = null;
    Element root = null;
    
    XMLSerializer xmlSer;
    OutputFormat out;
    
    //Try to create the document builder 
    try
    {
         db = dbf.newDocumentBuilder();
         doc = db.newDocument();
    }
    catch (Exception e)
    {
         e.printStackTrace();
         System.exit(1);
    }
    
    //Creat the root class, called TEst
    root = doc.createElement("franjas");
    doc.appendChild(root);
    
    // 
    Iterador<Usuario> iter = listaUsuarios.inorden();
	Object[] listaa = iter.darElementos();
	Usuario[] list = new Usuario[listaa.length];
	for (int j = 0; j< list.length ; j++)
	{
		Usuario classcast = (Usuario) listaa[j];
		list[j] = classcast;
	}
    
   
  	  Element root1 = doc.createElement("franja");
  	  root.appendChild(root1);
  	  root1.setAttribute("nombre", "Madrugador");
  	  
  	  
  	  Element root2 = doc.createElement("franja");
	  root.appendChild(root2);
	  root2.setAttribute("nombre", "Mañanero");
	  

	  Element root3 = doc.createElement("franja");
	  root.appendChild(root3);
	  root3.setAttribute("nombre", "Poco Productivo");
	  

	  
	  Element root4 = doc.createElement("franja");
	  root.appendChild(root4);
	  root4.setAttribute("nombre", "Trasnochador");
	  
	  for (int j = 0 ; j< list.length ; j++)
  	  {
  		  Usuario u = list[j];
  		  if (u.darFranja().equals("Mañanero"))
  		  {
  			  Element usuario = doc.createElement("usuario");
  			  root2.appendChild(usuario);
  			  usuario.setAttribute("name", u.darNombre());
  		  }
  		  else if(u.darFranja().equals("Madrugador"))
  		  {
  			Element usuario = doc.createElement("usuario");
			  root1.appendChild(usuario);
			  usuario.setAttribute("name", u.darNombre());
  		  }//No agarra bien
  		 else if(u.darFranja().contains("roductivo"))
 		  {
 			Element usuario = doc.createElement("usuario");
			  root3.appendChild(usuario);
			  usuario.setAttribute("name", u.darNombre());
 		  }
  		 else
  		 {
  			Element usuario = doc.createElement("usuario");
			  root4.appendChild(usuario);
			  usuario.setAttribute("name", u.darNombre());
  		 }
  		  
  	  }
  	  
  	 
    
        
    //Write out to file using the serializer
    try
    {
         out = new OutputFormat(doc);
         out.setIndenting(true);
         FileOutputStream fos = new FileOutputStream( archivoXml);
         xmlSer = new XMLSerializer(
         fos, out);
         xmlSer.serialize(doc);
    }
    catch(Exception e)
    {
         e.printStackTrace();
    }
	}catch(Exception e){}
}

/**
 * Limpia la informacion del usuario al "cerrar sesion".
 */
public void wipe()
{
	instancia = null;
//	user = null;
//	timeline = null;
//	listaAmigos = null;
//	listaSeguidores = null;
//	arbolFranjas = null;
//	arbolIndices = null;
//	arbolMenciones = null;
//	arbolPalabras = null;
	
	//Serializacion lista usuarios.
	try
    {
        ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( PATH_SERIAL ) );
        oos.writeObject( listaUsuarios );
        oos.close( );
    }
    catch( Exception e )
    {
        //Donothing
    	e.printStackTrace();
    }
}
  


}