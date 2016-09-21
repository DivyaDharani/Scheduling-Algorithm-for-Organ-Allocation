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
				HttpSession session = req.getSession(true);
				String type = (String)session.getAttribute("type");
				int id = (Integer)session.getAttribute("ID");
				String table_name = type+"_medical_report";
				String field = "";
				if(type.equals("donor"))
					field = "DonorID";
				else if(type.equals("recipient"))
					field = "RecipientID";
				sql = "create table "+table_name+" ("+field+" int, DOB date, BloodGroup varchar(2), HLA_A1 int,HLA_A2 int, HLA_B1 int, HLA_B2 int, HLA_DR1 int, HLA_DR2 int, MedicalHistory varchar(1000), FOREIGN KEY("+field+") REFERENCES "+type+"_credentials(ID))";				
				try
				{
					pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate();
				}
				catch(Exception ex)
				{
					//table already exists
				}

				pstmt = conn.prepareStatement("delete from "+table_name+" where "+field+" = "+id);
				pstmt.executeUpdate();

				String dob = req.getParameter("dob");
				String blood_group = req.getParameter("blood_group");
				String hla_a_1 = req.getParameter("hla_a_1");
				String hla_a_2 = req.getParameter("hla_a_2");
				String hla_b_1 = req.getParameter("hla_b_1");
				String hla_b_2 = req.getParameter("hla_b_2");
				String hla_dr_1 = req.getParameter("hla_dr_1");
				String hla_dr_2 = req.getParameter("hla_dr_2");
				String medical_history = req.getParameter("medical_history");

				sql = "insert into "+table_name+" values(?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,id);
				pstmt.setString(2,dob);
				pstmt.setString(3,blood_group);
				pstmt.setString(4,hla_a_1);
				pstmt.setString(5,hla_a_2);
				pstmt.setString(6,hla_b_1);
				pstmt.setString(7,hla_b_2);
				pstmt.setString(8,hla_dr_1);
				pstmt.setString(9,hla_dr_2);
				pstmt.setString(10,medical_history);

				pstmt.executeUpdate();
					
				res.sendRedirect("patient_page.jsp");
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