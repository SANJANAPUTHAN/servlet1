package comm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public void init(ServletConfig config) throws ServletException {	  
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/ey","root","sanj21");
			System.out.println(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String name=(String) session.getAttribute("name");
		PrintWriter out=response.getWriter();
		System.out.println(name);
		try
		{
			ps=conn.prepareStatement("update user set flag=0 where name=?");
			ps.setString(1,name);
			int r=ps.executeUpdate();
			if(r==1)
			{
				response.sendRedirect("LoginForm.html");
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		}


}
