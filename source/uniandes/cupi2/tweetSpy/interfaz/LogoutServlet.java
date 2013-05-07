package uniandes.cupi2.tweetSpy.interfaz;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import uniandes.cupi2.tweetSpy.mundo.TweeSpy;

public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = -4433102460849019660L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+ "/");
        TweeSpy instancia = TweeSpy.darInstancia();
        Twitter twit = instancia.darTwitter();
        
        twit.shutdown();
        instancia.wipe();
        
    }
}

