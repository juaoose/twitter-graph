package uniandes.cupi2.tweetSpy.interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.TwitterException;
import uniandes.cupi2.tweetSpy.mundo.Indice;
import uniandes.cupi2.tweetSpy.mundo.TweeSpy;
import uniandes.cupi2.tweetSpy.mundo.Usuario;
import uniandes.cupi2.tweetSpy.mundo.Lista.ListaDoblementeEncadenada;

public class IndicesServlet extends TemplateServlet
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
		return nombre+" - Índices";
	}

	@Override
	public void escribirContenido(HttpServletRequest request,
			HttpServletResponse response) throws IOException 
			
	{
		PrintWriter respuesta = response.getWriter( );
		Indice[] lista;
		try{
			lista = instancia.darIndices();
			if (lista.length >= 1)
				
			{
			respuesta.println("<ul>");
			for (int i = 0; i<lista.length; i++)
			{
				Indice indice = lista[i];
				String n = indice.darPalabra();

				
				respuesta.println("				<li><h3>"+n+"</h3></li>");
				////////////////////////////////////////
				ListaDoblementeEncadenada<Usuario> list = instancia.darAmigos();
				for (int z=0; z<list.darTamanio() ; z++)
				{
					Usuario a = list.dar(z);
					//respuesta.println("<p>"+a.tweeteoPalabra(n)+"</p>");
					//respuesta.println("<p>"+a.darTam()+"</p>");
					if (a.tweeteoPalabra(n))
					{
					respuesta.println("<p>"+a.darNombre()+"</p>");
					}
					
					
				}
				//////////////////////////////////////
//				ListaDoblementeEncadenada<Usuario> users = instancia.darUsuariosEnIndice(n);
//				if (users != null && users.darTamanio()>=1)
//				{
//					for (int j=0; j < users.darTamanio(); j++)
//					{
//						Usuario use = users.dar(j);
//						respuesta.println("				<li>"+use.darNombre()+"</li>");
//					}
//				}

			
				
				
				
			}
			respuesta.println("				</ul>");	
			}
			else
			{
				respuesta.println("		<div class=\"alert alert-error\">");
				respuesta.println("			<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
				respuesta.println("			<h4>No se ha creado ningun indice</h4>");
				respuesta.println("		</div>");
			}
				
				//navbar
				
				respuesta.println("	<!-- Aca termina el conenido -->");
				respuesta.println("	<hr>");
				respuesta.println("	<ul class=\"nav nav-pills pull-right\">");
				respuesta.println("          <li><a href=\"logueado\">Home</a></li>");
				respuesta.println("          <li><a href=\"trinar\">Tweet</a></li>");
				respuesta.println("          <li class=\"active\"><a href=\"indices\">Indexes</a></li>");
				respuesta.println("		  <li><a href=\"amigos\">Friends</a></li>");
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
