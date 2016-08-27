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
					session.setAttribute("error","");
					if(type.equals("donor"))
					{
						int donor_id = result_set.getInt("ID");
						Donor donor = new Donor(donor_id);
						session.setAttribute("donor",donor);
						session.setAttribute("type","donor");
						session.setAttribute("ID",donor_id);
						session.setAttribute("name",donor.getName());	
						RequestDispatcher dispatcher = req.getRequestDispatcher("donor_page.jsp");
						dispatcher.forward(req,res);
					}
					else if(type.equals("recipient"))
					{
						/*int recipient_id = result_set.getInt("ID");
						Recipient recipient = new Recipient(recipient_id);
						session.setAttribute("type","recipient");
						session.setAttribute("ID",recipient_id);
						session.setAttribute("name",recipient.getName());*/
						RequestDispatcher dispatcher = req.getRequestDispatcher("recipient_page.html");
					}

				}
				else
				{	
					//pw.println("<html><body><center>Wrong password "+user_name+"!");
					session.setAttribute("error","Wrong password!");
					RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
					dispatcher.forward(req,res);
				}
			}
			else
			{
				session.setAttribute("error","No registered user in the name "+user_name+"!");
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