import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class Registration extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		String registration_option = req.getParameter("registration_option");
		String table_name = registration_option+"_credentials";
		String user_name = req.getParameter("user_name");
		String password = req.getParameter("password");
		
		PrintWriter pw = res.getWriter();
		
		HttpSession session = req.getSession();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");
			
			PreparedStatement pstmt=null;
			String sql="create table "+table_name+"(ID INT AUTO_INCREMENT PRIMARY KEY,RegistrationType VARCHAR(15),UserName VARCHAR(20) NOT NULL ,Password VARCHAR(20) NOT NULL)";
		
			try
			{
				pstmt=conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}
			catch(Exception e)
			{
				//table already exists
			}

			pstmt = conn.prepareStatement("select * from "+table_name+" where UserName='"+user_name+"'");
			ResultSet result = pstmt.executeQuery();
			if(result.next())
			{

				session.setAttribute("reg_error","User already exists!");
				res.sendRedirect("register.jsp");
			}
			else
			{
				pstmt = conn.prepareStatement("insert into "+table_name+"(RegistrationType,UserName,Password) values(?,?,?)");
				pstmt.setString(1,registration_option);
				pstmt.setString(2,user_name);
				pstmt.setString(3,password);
				pstmt.executeUpdate();
				session.setAttribute("reg_error","");
				pstmt = conn.prepareStatement("select * from "+table_name+" where UserName='"+user_name+"'");
				result = pstmt.executeQuery();
				result.next();
				session.setAttribute("ID",result.getInt("ID"));
				session.setAttribute("type",registration_option);
				RequestDispatcher dispatcher = req.getRequestDispatcher("patient_page.jsp");
				dispatcher.forward(req,res);
			}
		}	
		catch(Exception ex)
		{
			StringWriter swr = new StringWriter();
			PrintWriter pwr = new PrintWriter(swr);
			ex.printStackTrace(pwr);
			String stackTrace = swr.toString();
			pw.println(stackTrace);
		}

	}
}