package comm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	boolean status=false;
	public void init(ServletConfig config) throws ServletException {	  
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/ey","root","sanj21");
			ps=conn.prepareStatement("select * from user where name=? and password=?");		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		response.setContentType("text/html");
		int f=0;
		try {
			ps.setString(1,name);
			ps.setString(2,password);  		      
			rs=ps.executeQuery();  
		    status=rs.next(); 
		    
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}  
		if(status)
		{
			try
			{
				ps=conn.prepareStatement("update user set flag=1 where name=?");
				ps.setString(1,name);
				f=ps.executeUpdate();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			System.out.println(f);
			RequestDispatcher rd=request.getRequestDispatcher("WelcomeServlet");  
	        rd.forward(request,response); 
		}
		else
		{
			
	        RequestDispatcher rd=request.getRequestDispatcher("login.html");  
	        rd.include(request,response);
		}
		
		
	}

}
