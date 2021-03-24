package comm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		String name=request.getParameter("name");
		HttpSession session=request.getSession();
		session.setAttribute("name", name);
		PrintWriter pw=response.getWriter();
		pw.println("<form action=\"LogOutServlet\" >");
		pw.println("<h1>"+"WELCOME, "+name+"</h1>");
		pw.println("<input type=\"submit\" value=\"logout\" >");
		
		
	}


}
