import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class PersonalDetails extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String contact_no = req.getParameter("contact_no");
		String emergency_contact = req.getParameter("emergency_contact");
		HttpSession session = req.getSession();
		String type = (String) session.getAttribute("type");
		PrintWriter pw = res.getWriter();
		String field="";
		int id = (Integer)session.getAttribute("ID");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");
			if(type.equals("donor"))
				field = "DonorID";
			else if (type.equals("recipient"))
				field = "RecipientID";
			String sql="create table "+type+"_details ("+field+" int NOT NULL,Name VARCHAR(20),Age INT, DOB VARCHAR(10),Gender VARCHAR(6),ContactNumber VARCHAR(10),EmergencyContact VARCHAR(10), Type VARCHAR(15),FOREIGN KEY("+field+") REFERENCES "+type+"_credentials(ID))";
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
			pstmt = conn.prepareStatement("select * from "+type+"_details where "+field+" = "+id);
			ResultSet result = pstmt.executeQuery();
			if(result.next())
			{
				pstmt = conn.prepareStatement("delete from "+type+"_details where "+field+" = "+id);
				pstmt.executeUpdate();
			}
			
			pstmt = conn.prepareStatement("insert into "+type+"_details values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1,id);
			pstmt.setString(2,name);
			pstmt.setInt(3,age);
			pstmt.setString(4,dob);
			pstmt.setString(5,gender);
			pstmt.setString(6,contact_no);
			pstmt.setString(7,emergency_contact);
			pstmt.setString(8,type);
			pstmt.executeUpdate();
			res.sendRedirect("patient_page.jsp");
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