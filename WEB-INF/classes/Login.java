import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class Login extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		String user_name = req.getParameter("user_name");
		String password = req.getParameter("password");
		String type = req.getParameter("type");

		String table_name = type+"_credentials";

		PrintWriter pw = res.getWriter();
		HttpSession session = req.getSession(true);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");
			ResultSet result_set = null;

			PreparedStatement pstmt=null;
			String sql = "select * from "+table_name+" where UserName='"+user_name+"'";
		
			pstmt = conn.prepareStatement(sql);
			result_set = pstmt.executeQuery();
			if(result_set.next())
			{
				if(password.equals(result_set.getString("Password")))
				{
					session.setAttribute("login_error","");
					if(type.equals("donor") || type.equals("recipient"))
					{
						int id = result_set.getInt("ID");
						session.setAttribute("ID",id);
						session.setAttribute("type",type);	
						RequestDispatcher dispatcher = req.getRequestDispatcher("patient_page.jsp");
						dispatcher.forward(req,res);
					}
					else 
					{
						RequestDispatcher dispatcher = req.getRequestDispatcher("hospital_profile.html");
						dispatcher.forward(req,res);
					}

				}
				else
				{	
					session.setAttribute("login_error","Wrong password!");
					RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
					dispatcher.forward(req,res);
				}
			}
			else
			{
				session.setAttribute("login_error","No registered user in the name "+user_name+"!");
				//RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
				//dispatcher.forward(req,res);
				res.sendRedirect("login.jsp");
			}
			//create donor/recipient instance and pass it to next code
			
			
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