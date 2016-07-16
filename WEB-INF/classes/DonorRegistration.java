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

		PrintWriter print = res.getWriter();
		print.println("<html><body><center>Thank you for registering with us "+name+"!");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");
			String sql="create table willing_donors(Name VARCHAR(20),Age INT, DOB VARCHAR(10),Gender VARCHAR(6),Contact_number VARCHAR(10),Donor_type VARCHAR(15))";
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
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String stackTrace = sw.toString();
			print.println(stackTrace);
		}

	}
}