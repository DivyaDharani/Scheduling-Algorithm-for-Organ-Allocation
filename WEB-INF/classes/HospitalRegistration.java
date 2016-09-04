import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class HospitalRegistration extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter pwriter = res.getWriter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");

			Statement stmt = conn.createStatement();

			String hosp_name = req.getParameter("name");
			String city = req.getParameter("city");
			String opo = req.getParameter("opo");
			String phone = req.getParameter("phone");
			String user_name = req.getParameter("user_name");
			String password = req.getParameter("password");

			String sql = "create table hospital_details (ID int AUTO_INCREMENT PRIMARY KEY, HospitalName varchar(30), City varchar(20), OPO varchar(20), Phone varchar(10), UserName varchar(20), Password varchar(10))";
			
			try
			{
				stmt.executeUpdate(sql);
			}
			catch(Exception e)
			{
				//table already exists

				/*StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				pwriter.println(sw.toString());*/
			}

			HttpSession session = req.getSession(true);
			sql = "select * from hospital_details where HospitalName = '"+hosp_name+"' and City = '"+city+"'";
			ResultSet result = stmt.executeQuery(sql);
			if(result.next())
			{
				session.setAttribute("reg_error","Already registered hospital !");
				res.sendRedirect("hospital_registration.jsp");
			}
			else
			{
				sql = "select * from hospital_details where UserName ='"+user_name+"'";
				result = stmt.executeQuery(sql);
				if(result.next())
				{
					session.setAttribute("reg_error","User name already exists!");
					res.sendRedirect("hospital_registration.jsp");
				}
				else
				{
					session.setAttribute("reg_error","");
					sql = "insert into hospital_details(HospitalName, City, OPO, Phone, UserName, Password) values(?,?,?,?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,hosp_name);
					pstmt.setString(2,city);
					pstmt.setString(3,opo);
					pstmt.setString(4,phone);
					pstmt.setString(5,user_name);
					pstmt.setString(6,password);
					pstmt.executeUpdate();

					res.sendRedirect("hospital_profile.html");
				}
			}
			
		}
		catch(Exception e)
			{
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				pwriter.println(sw.toString());
			}
	}
}