import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class DonorRegistration extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String contact_no = req.getParameter("contact_no");
		String donor_type = req.getParameter("donor_type");

		PrintWriter pw = res.getWriter();
		pw.println("<html><body><center>Thank you for registering with us "+name+"!");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");
			String sql="create table willing_donors(Name VARCHAR(20),Age INT, DOB VARCHAR(10),Gender VARCHAR(6),ContactNumber VARCHAR(10),DonorType VARCHAR(15))";
			PreparedStatement pstmt=null;
			try
			{
				//Statement stmt=conn.createStatement();
				//stmt.executeUpdate(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}
			catch(Exception e)
			{
				//table already exists
			}
			pstmt =conn.prepareStatement("insert into willing_donors values(?,?,?,?,?,?)");
			pstmt.setString(1,name);
			pstmt.setInt(2,age);
			pstmt.setString(3,dob);
			pstmt.setString(4,gender);
			pstmt.setString(5,contact_no);
			pstmt.setString(6,donor_type);
			pstmt.executeUpdate();
		}
		catch(Exception ex)
		{
			StringWriter sw = new StringWriter();
			PrintWriter pwr = new PrintWriter(sw);
			ex.printStackTrace(pwr);
			String stackTrace = sw.toString();
			pw.println(stackTrace);
		}

	}
}