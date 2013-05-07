package uniandes.cupi2.tweetSpy.interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.TwitterException;
import uniandes.cupi2.tweetSpy.mundo.TweeSpy;
import uniandes.cupi2.tweetSpy.mundo.Usuario;
import uniandes.cupi2.tweetSpy.mundo.Lista.ListaDoblementeEncadenada;

public class MencionadosServlet extends TemplateServlet
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
		return nombre+" - Menciones";
	}

	@Override
	public void escribirContenido(HttpServletRequest request,
			HttpServletResponse response) throws IOException 
	{
		// TODO Auto-generated method stub
		
		PrintWriter respuesta = response.getWriter( );
		
		try{
			
		instancia.cargarMasMencionados();
		Usuario mencionadot1 = instancia.darMas();
		Usuario mencionadot2 = instancia.darMas();
		Usuario mencionadot3 = instancia.darMas();
		
		
		
		respuesta.write("<table class=\"table table-hover\">\r\n");
		
			
			
			respuesta.write("<tr>\r\n");
			respuesta.write("		<td width=\"28\">\r\n");
			respuesta.write("		<p align=\"middle\">\r\n");
			respuesta.write("		<td><img src=\""+mencionadot1.darUrl()+"\" width=\"100\" height=\"100\"></img></td>\r\n");
			respuesta.write("		<td><b>"+mencionadot1.darNombre()+"</b><p></td>\r\n");
			respuesta.write("	</tr>\r\n");
			
			respuesta.write("<tr>\r\n");
			respuesta.write("		<td width=\"28\">\r\n");
			respuesta.write("		<p align=\"middle\">\r\n");
			respuesta.write("		<td><img src=\""+mencionadot2.darUrl()+"\" width=\"100\" height=\"100\"></img></td>\r\n");
			respuesta.write("		<td><b>"+mencionadot2.darNombre()+"</b><p></td>\r\n");
			respuesta.write("	</tr>\r\n");
			
			respuesta.write("<tr>\r\n");
			respuesta.write("		<td width=\"28\">\r\n");
			respuesta.write("		<p align=\"middle\">\r\n");
			respuesta.write("		<td><img src=\""+mencionadot3.darUrl()+"\" width=\"100\" height=\"100\"></img></td>\r\n");
			respuesta.write("		<td><b>"+mencionadot3.darNombre()+"</b><p></td>\r\n");
			respuesta.write("	</tr>\r\n");
		
			respuesta.write("<tr>\r\n");
			respuesta.write("</table>\r\n");

			
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
			respuesta.println("		  <li><a href=\"crearindice\">Nuevo Indice</a></li>");
			respuesta.println("		<li class=\"active\"><a href=\"menciones\">Menciones</a></li>");
			respuesta.println(" <li><a href=\"palabras\">Palabras</a></li>");
			respuesta.println("");
			respuesta.println("        </ul>");
			respuesta.println("		");
			respuesta.println("		<br>");
		}catch(Exception e ){e.printStackTrace();}

	}	
			
}
