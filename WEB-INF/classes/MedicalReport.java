import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class MedicalReport extends HttpServlet
{
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://kidney_transplantation","root","root");
				PreparedStatement pstmt = null;
				String sql;
				HttpSession session = req.getSession();
				String type = session.getParameter("type");
				String table_name = type+"_medical_report"
				sql="create table "+table_name+"()";
			}
		}
}