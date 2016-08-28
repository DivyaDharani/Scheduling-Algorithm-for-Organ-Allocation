import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class MedicalReport extends HttpServlet
{
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
		{
			PrintWriter pw = res.getWriter();
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");
				PreparedStatement pstmt = null;
				String sql;
				HttpSession session = req.getSession();
				String type = (String)session.getAttribute("type");
				int id = (Integer)session.getAttribute("ID");
				String table_name = type+"_medical_report";
				String field = "";
				if(type.equals("donor"))
					field = "DonorID";
				else if(type.equals("recipient"))
					field = "RecipientID";
				sql = "create table donor_medical_report ("+field+" int, DOB date, BloodGroup varchar(2), HLA_A1 int,HLA_A2 int, HLA_B1 int, HLA_B2 int, HLA_DR1 int, HLA_DR2 int, MedicalHistory varchar(1000), FOREIGN KEY("+field+") REFERENCES "+type+"_credentials(ID))";				
				conn.prepareStatement(sql);
				pstmt.executeUpdate();
				//include medical report fields
			}
			catch(Exception ex)
			{
				StringWriter sw = new StringWriter();
				PrintWriter pwr = new PrintWriter(sw);
				ex.printStackTrace(pwr);
				pw.println(sw.toString());
			}
		}
}