package comm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	PreparedStatement ps;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
   @Override
	public void init(ServletConfig config) throws ServletException {
	  
		
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");	
				conn=DriverManager.getConnection("jdbc:mysql://localhost/ey","root","sanj21");
				System.out.println(conn);
				ps=conn.prepareStatement("insert into user values(?,?,?,0)");
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		int i=Integer.parseInt(id);
		response.setContentType("text/html");
        try {
           ps.setInt(1,i);
           ps.setString(2,name);
           ps.setString(3,password);
           ps.executeUpdate();
           RequestDispatcher rd=request.getRequestDispatcher("LoginForm.html");  
	        rd.forward(request,response); 
          }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
       }
		
		}
		
	


