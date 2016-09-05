import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class FindRecipient extends HttpServlet 
{
	PrintWriter pwriter = res.getWriter();
	String sql="";
	PreparedStatement pstmt;
	ResultSet result;
	HttpSession session = req.getSession();
	String search_type;
	int id;
	String hospital_name,city,opo;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		id = req.getParameter("id");
		search_type = req.getParameter("search_type");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");
		}
		catch(Exception e)
		{
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			pwriter.println(sw.toString());
		}

	}

	public void FindRecipient()
	{
		try
		{
			if(search_type.equals("within_hospital"))
			{
				hospital_name = session.getAttribute("hospital_name");
				city = session.getAttribute("city");

				sql = "select RecipientID from recipient_details where Hospital = '"+hospital_name+"' and City = '"+city+"'";
			}
			else if(search_type.equals("within_OPO"))
			{
				sql = "select OPO from city_opo_details where City = "+city;
				pstmt = conn.prepareStatement(sql);
				result = pstmt.executeQuery();
				if(result.next())
				{
					opo = result.getString("OPO");
					//get the list of hospitals in this OPO 
					//then get the recipients in those hospitals
				}
				else
				{
					//the city in which this hospital lies does not have an associated OPO in the db
					return;
				}
			}
			else if(search_type.equals("other_OPOs"))
			{

			}
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeQuery();
			if(result.last())
			{
				int rowcount = result.getRow();
				result.beforeFirst();
				Patient[] recipients = new Patient[rowcount];
				i=0;
				while(result.next())
				{
					recipients[i] = new Patient(result.getInt("RecipientID"));
					i++;
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