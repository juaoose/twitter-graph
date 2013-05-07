package uniandes.cupi2.tweetSpy.interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public abstract class TemplateServlet extends HttpServlet 
{
	  /**
     * Maneja un pedido GET de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        procesarPedido( request, response );
    }

    /**
     * Maneja un pedido POST de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        procesarPedido( request, response );
    }
    
    /**
     * Procesa el pedido de igual manera para todos
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    private void procesarPedido( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        //
        // Comienza con el Header del template
        imprimirHeader( request, response );
        //
        // Escribe el contenido de la página
        escribirContenido( request, response );
        //
        // Termina con el footer del template
        imprimirFooter( response );

    }
    
    /**
     * Imprime el Header del diseño de la página
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción al imprimir en el resultado
     */
    private void imprimirHeader( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        //
        // Saca el printer de la repuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el header
        respuesta.println("<!DOCTYPE html>");
        respuesta.println("<html lang=\"es-co\">");
        respuesta.println("  <head>");
       // respuesta.println("    <meta charset=\"utf-8\">");
        respuesta.write( "<meta http-equiv=\"Content-Language\" content=\"es-co\">\r\n" );
        respuesta.write( "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">\r\n" );
        respuesta.println("    <title>Tweet &middot; Spy</title>");
        respuesta.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        respuesta.println("    <meta name=\"description\" content=\"\">");
        respuesta.println("    <meta name=\"author\" content=\"\">");
        respuesta.println("");
        respuesta.println("    <!-- Le styles -->");
        respuesta.println("    <link href=\"./css/bootstrap.css\" rel=\"stylesheet\">");
        respuesta.println("    <style type=\"text/css\">");
        respuesta.println("      body {");
        respuesta.println("        padding-top: 20px;");
        respuesta.println("        padding-bottom: 40px;");
        respuesta.println("      }");
        respuesta.println("");
        respuesta.println("      /* Custom container */");
        respuesta.println("      .container-narrow {");
        respuesta.println("        margin: 0 auto;");
        respuesta.println("        max-width: 700px;");
        respuesta.println("      }");
        respuesta.println("      .container-narrow > hr {");
        respuesta.println("        margin: 30px 0;");
        respuesta.println("      }");
        respuesta.println("");
        respuesta.println("      /* Main marketing message and sign up button */");
        respuesta.println("      .jumbotron {");
        respuesta.println("        margin: 60px 0;");
        respuesta.println("        text-align: center;");
        respuesta.println("      }");
        respuesta.println("      .jumbotron h1 {");
        respuesta.println("        font-size: 72px;");
        respuesta.println("        line-height: 1;");
        respuesta.println("      }");
        respuesta.println("      .jumbotron .btn {");
        respuesta.println("        font-size: 21px;");
        respuesta.println("        padding: 14px 24px;");
        respuesta.println("      }");
        respuesta.println("");
        respuesta.println("      /* Supporting marketing content */");
        respuesta.println("      .marketing {");
        respuesta.println("        margin: 60px 0;");
        respuesta.println("      }");
        respuesta.println("      .marketing p + h4 {");
        respuesta.println("        margin-top: 28px;");
        respuesta.println("      }");
        respuesta.println("    </style>");
        respuesta.println("    <link href=\"./css/bootstrap-responsive.css\" rel=\"stylesheet\">");
        respuesta.println("");
        respuesta.println("    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->");
        respuesta.println("    <!--[if lt IE 9]>");
        respuesta.println("      <script src=\"../js/html5shiv.js\"></script>");
        respuesta.println("    <![endif]-->");
        respuesta.println("");
        respuesta.println("    <!-- Fav and touch icons -->");
        respuesta.println("    ");
        respuesta.println("  </head>");
        respuesta.println("");
        respuesta.println("  <body>");
        respuesta.println("	");
        respuesta.println("    <div class=\"container-narrow\">");
        respuesta.println("	");
        respuesta.println("      <div class=\"masthead\">");
        respuesta.println("		<table class=\"table table-condensed\">");
        respuesta.println("  			<td><h3 class=\"text-left text-info\">TweetSpy</h3></td> ");
        respuesta.println("			<td></td>");
        respuesta.println("			<td><img align=\"right\" class=\"img-rounded\" src=\"./imagenes/LOGO.png\" width=\"300\" ></img><br></td>");
        respuesta.println("		</table>");
        respuesta.println("");
        respuesta.println("      </div>");
        respuesta.println("      <hr>");
    }

    /**
     * Imprime el Footer del diseño de la página
     * @param response Respuesta
     * @throws IOException Excepción al escribir en la respuesta
     */
    private void imprimirFooter( HttpServletResponse response ) throws IOException
    {
        //
        // Saca el writer de la respuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el footer
        respuesta.println("<div class=\"footer\">");
        respuesta.println("        <p> Juan Camilo Munoz & Juan Jose Rodriguez </p>");
        respuesta.println("		<p>&copy; Cupi2 </p>");
        respuesta.println("		<p> 2013 </p>");
        respuesta.println("      </div>");
        respuesta.println("");
        respuesta.println("    </div> <!-- /container -->");
        respuesta.println("");
        respuesta.println("    <!-- Le javascript");
        respuesta.println("    ================================================== -->");
        respuesta.println("    <!-- Placed at the end of the document so the pages load faster -->");
        respuesta.println("    <script src=\"./js/jquery.js\"></script>");
        respuesta.println("	<script src=\"http://code.jquery.com/jquery.js\"></script>");
        respuesta.println("	<script src=\"./js/bootstrap.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap.min.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-transition.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-alert.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-modal.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-dropdown.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-scrollspy.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-tab.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-tooltip.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-popover.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-button.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-collapse.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-carousel.js\"></script>");
        respuesta.println("    <script src=\"./js/bootstrap-typeahead.js\"></script>");
        respuesta.println("");
        respuesta.println("  </body>");
        respuesta.println("</html>");
    }
    
    /**
     * Devuelve el título de la página para el Header
     * @param request Pedido del cliente
     * @return Título de la página para el Header
     */
    public abstract String darTituloPagina( HttpServletRequest request );

    /**
     * Devuelve el nombre de la imagen para el título de la página en el Header
     * @param request Pedido del cliente
     * @return Nombre de la imagen para el título de la página en el Header
     */
    public abstract String darImagenTitulo( HttpServletRequest request );

    /**
     * Escribe el contenido de la página
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    public abstract void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException;



}
