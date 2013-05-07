package uniandes.cupi2.tweetSpy.interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.TwitterException;
import uniandes.cupi2.tweetSpy.mundo.Indice;
import uniandes.cupi2.tweetSpy.mundo.TweeSpy;

public class CrearIndiceServlet extends TemplateServlet
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
		return nombre+" - Crear Índice";
	}

	@Override
	public void escribirContenido(HttpServletRequest request,
			HttpServletResponse response) throws IOException 
			
	{
		PrintWriter respuesta = response.getWriter( );
		
		respuesta.write("<form method=\"POST\" name=\"input\" action=\"indicecreado\" >");
		respuesta.println("<div class=\"input-append\">");
		respuesta.write("Nuevo Índice <input type=\"text\" class=\"span2\" name=\"indice\" maxlength=\"140\">");
		respuesta.println("<input class=\"btn \" align=\"right\" type=\"submit\" value=\"Enviar!\"></input><br>");
		respuesta.println("</div>");
		respuesta.write("</form>");

		
		//navbar
		
		respuesta.println("	<!-- Aca termina el conenido -->");
		respuesta.println("	<hr>");
		respuesta.println("	<ul class=\"nav nav-pills pull-right\">");
		respuesta.println("          <li><a href=\"logueado\">Home</a></li>");
		respuesta.println("          <li><a href=\"trinar\">Tweet</a></li>");
		respuesta.println("          <li><a href=\"indices\">Indexes</a></li>");
		respuesta.println("		  <li><a href=\"amigos\">Friends</a></li>");
		respuesta.println("		  <li><a href=\"seguidores\">Followers</a></li>");
		respuesta.println("		  <li><a href=\"newpre\">Prefijo</a></li>");
		respuesta.println("		  <li class=\"active\"><a href=\"crearindice\">Nuevo Indice</a></li>");
		respuesta.println("<li><a href=\"menciones\">Menciones</a></li>");
		respuesta.println(" <li><a href=\"palabras\">Palabras</a></li>");
		respuesta.println("");
		respuesta.println("        </ul>");
		respuesta.println("		");
		respuesta.println("		<br>");

		
		
	}
}
