package uniandes.cupi2.tweetSpy.interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.TwitterException;
import uniandes.cupi2.tweetSpy.mundo.Palabra;
import uniandes.cupi2.tweetSpy.mundo.TweeSpy;

public class LogueadoServlet extends TemplateServlet
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
		return nombre;
	}

	@Override
	public void escribirContenido(HttpServletRequest request,
			HttpServletResponse response) throws IOException 
			{
		
		PrintWriter respuesta = response.getWriter( );
		try{
		String url = instancia.darImagenUsuario();
		String name = instancia.darUsername();
		
		respuesta.println("	");
		respuesta.println("      <div class=\"jumbotron\">");
		respuesta.println("        ");
		respuesta.println("        ");
		respuesta.println("				<!-- Alerta -->");
		respuesta.println("<!-- Imagen de perfil -->");
		respuesta.println("		<table class=\"table table-condensed\">");
		respuesta.println("  			<td><h3 class=\"text-left text-info\">"+name+"</h3></td> ");
		respuesta.println("			<td></td>");
		respuesta.println("			<td><img align=\"right\" class=\"img-rounded\" src=\""+url+"\" width=\"64\" height=\"64\" width=\"300\" ></img><br></td>");
		respuesta.println("		</table>");
		respuesta.println("		</div>");
		respuesta.println("");
		respuesta.println("     ");
		respuesta.println("	<!-- Aca comienza el conenido -->");
		//contenido cambiante
		respuesta.println("		<div class=\"alert alert-block\">");
		respuesta.println("			<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
		respuesta.println("			<h4>Inicio de sesion exitoso!</h4>");
		respuesta.println("			En esta pagina podrás ver cierta información de tu perfil.");
		respuesta.println("");
		respuesta.println("		</div>");
		respuesta.println("      <div class=\"row-fluid l\">");
		respuesta.println("		");
		respuesta.println("");
		respuesta.println("		        ");
		respuesta.println("      </div>");
		
		
		//franjas
		try
		{
		instancia.cargarFranjas();
		Palabra franja0 = instancia.darFranja();
		Palabra franja1 = instancia.darFranja();
		Palabra franja2 = instancia.darFranja();
		Palabra franja3 = instancia.darFranja();
		
		//para el xml
		String fr = franja0.darPalabra();
		instancia.guardarUsuario(instancia.darUsername(), fr);
		instancia.escribirXML();
		//end xml
		
		
		
		respuesta.println("<p class=\"text-error\">"+"Usted es "+franja0.darPalabra()+"</p>");
		
		//cuantas
		respuesta.println("<table class=\"table table-hover\">");
		respuesta.println(" <tr class=\"success\">");
		respuesta.println("    <td>"+franja0.darPalabra()+"</td>");
		respuesta.println("    <td>"+franja0.darConteo()+"</td>");
		respuesta.println("  </tr>");
		respuesta.println(" <tr class=\"warning\">");
		respuesta.println("    <td>"+franja1.darPalabra()+"</td>");
		respuesta.println("    <td>"+franja1.darConteo()+"</td>");
		respuesta.println("  </tr>");
		respuesta.println(" <tr class=\"info\">");
		respuesta.println("    <td>"+franja2.darPalabra()+"</td>");
		respuesta.println("    <td>"+franja2.darConteo()+"</td>");
		respuesta.println("  </tr>");
		respuesta.println(" <tr class=\"error\">");
		respuesta.println("    <td>"+franja3.darPalabra()+"</td>");
		respuesta.println("    <td>"+franja3.darConteo()+"</td>");
		respuesta.println("  </tr>");
		respuesta.println("</table>");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		//fin contenido
		respuesta.println("<a class=\"btn btn-small btn-danger\" align=\"right\" href=\"logout\">Salir</a><br>");
		respuesta.println("	<!-- Aca termina el conenido -->");
		respuesta.println("	<hr>");
		respuesta.println("	<ul class=\"nav nav-pills pull-right\">");
		respuesta.println("          <li class=\"active\"><a href=\"logueado\">Home</a></li>");
		respuesta.println("          <li><a href=\"trinar\">Tweet</a></li>");
		respuesta.println("          <li><a href=\"indices\">Índices</a></li>");
		respuesta.println("		  <li><a href=\"amigos\">Amigos</a></li>");
		respuesta.println("		  <li><a href=\"seguidores\">Seguidores</a></li>");
		respuesta.println("		  <li><a href=\"newpre\">Prefijo</a></li>");
		respuesta.println("		  <li><a href=\"crearindice\">Nuev Índice</a></li>");
		respuesta.println("<li><a href=\"menciones\">Menciones</a></li>");
		respuesta.println(" <li><a href=\"palabras\">Palabras</a></li>");
		respuesta.println("");
		respuesta.println("        </ul>");
		respuesta.println("		");
		respuesta.println("		<br>");
		}
		catch(Exception e)
		{
			
		}
		
	}

}
