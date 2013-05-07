package uniandes.cupi2.tweetSpy.interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import uniandes.cupi2.tweetSpy.mundo.Usuario;
import uniandes.cupi2.tweetSpy.mundo.TweeSpy;
import uniandes.cupi2.tweetSpy.mundo.Lista.ListaDoblementeEncadenada;

public class AmigosServlet extends TemplateServlet
{

	//------------------------------------------------
	// Instancia de el TweetSpy
	//------------------------------------------------
	
	TweeSpy instancia = TweeSpy.darInstancia();

	@Override
	public String darImagenTitulo(HttpServletRequest request) 
	{
		String nombre = "";
		try {
			nombre = instancia.darImagenUsuario();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
	}

	@Override
	public String darTituloPagina(HttpServletRequest request) 
	{
		String nombre = "";
		try {
			nombre = instancia.darUsername();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre+" - Amigos";
	}

	@Override
	public void escribirContenido(HttpServletRequest request,
			HttpServletResponse response) throws IOException 
	{
		// TODO Auto-generated method stub
		
		PrintWriter respuesta = response.getWriter( );
		
		
		ListaDoblementeEncadenada<Usuario> lista;
		try{
		lista = instancia.darListaAmigos();
		
		respuesta.write("<table class=\"table table-hover\">\r\n");
		for (int i = 0; i<lista.darTamanio(); i++)
		{
			Usuario amig = lista.dar(i);
			String n = amig.darNombre();
			String url = amig.darUrl();
			String descr = amig.darDescripcion();
			
			
			respuesta.write("<tr>\r\n");
			respuesta.write("		<td width=\"28\">\r\n");
			respuesta.write("		<p align=\"middle\">\r\n");
			respuesta.write("		<td><img src=\""+url+"\" width=\"100\" height=\"100\"></img></td>\r\n");
			respuesta.write("		<td><b>"+n+"</b><p>Descripcion</p><p>"+descr+"</p></td>\r\n");
			respuesta.write("	</tr>\r\n");
		}
			respuesta.write("<tr>\r\n");
			respuesta.write("</table>\r\n");

			//navbar
			respuesta.println("	<!-- Aca termina el conenido -->");
			respuesta.println("	<hr>");
			respuesta.println("	<ul class=\"nav nav-pills pull-right\">");
			respuesta.println("          <li><a href=\"logueado\">Home</a></li>");
			respuesta.println("          <li><a href=\"trinar\">Tweet</a></li>");
			respuesta.println("          <li><a href=\"indices\">Indexes</a></li>");
			respuesta.println("		  <li class=\"active\"><a href=\"amigos\">Friends</a></li>");
			respuesta.println("		  <li><a href=\"seguidores\">Followers</a></li>");
			respuesta.println("		  <li><a href=\"newpre\">Prefijo</a></li>");
			respuesta.println("		  <li><a href=\"crearindice\">Nuevo Indice</a></li>");
			respuesta.println("<li><a href=\"menciones\">Menciones</a></li>");
			respuesta.println(" <li><a href=\"palabras\">Palabras</a></li>");
			respuesta.println("");
			respuesta.println("        </ul>");
			respuesta.println("		");
			respuesta.println("		<br>");
		}catch(Exception e ){e.printStackTrace();}

	}	
			
	

		
	

}
