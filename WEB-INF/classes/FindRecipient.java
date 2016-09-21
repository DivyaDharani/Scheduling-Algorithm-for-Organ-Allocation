import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;
public class FindRecipient extends HttpServlet 
{
	PrintWriter pwriter;
	String sql="";
	PreparedStatement pstmt;
	ResultSet result;
	HttpSession session;
	String search_type;
	int id;
	String hospital_name,city,opo;
	Connection conn;
	// HttpServletRequest req;
	// HttpServletResponse res;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		// this.req = req;
		// this.res = res;
		pwriter = res.getWriter();
		session = req.getSession();
		id = Integer.parseInt(req.getParameter("id"));
		search_type = req.getParameter("search_type");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");
			findRecipient();
		}
		catch(Exception e)
		{
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			pwriter.println(sw.toString());
		}

	}

	public void findRecipient()
	{
		try
		{
			city = (String)session.getAttribute("city");
			if(search_type.equals("within_hospital"))
			{
				hospital_name = (String)session.getAttribute("hospital_name");

				sql = "select RecipientID from recipient_details where Hospital = '"+hospital_name+"' and City = '"+city+"'";
			}
			else if(search_type.equals("within_OPO"))
			{
				sql = "select RecipientID from recipient_details R left join city_opo_details C on R.city = C.city where OPO IN (select OPO from city_opo_details where city = '+city+')";

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
				int i=0;
				while(result.next())
				{
					recipients[i] = new Patient(result.getInt("RecipientID"),"recipient");
					i++;
				}
				Patient donor = new Patient(id,"donor");
				//assign points and select top 5 patients
				assignPoints(donor,recipients); //this updates the points
				chooseRecipients(recipients);
			}
			else
			{
				//no recipients in the selected category
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

	public void assignPoints(Patient donor, Patient[] recipients)
	{
		int i;
		Random random = new Random();
		for(i=0;i<recipients.length;i++)
			recipients[i].points = random.nextInt(101);
	}

	public void chooseRecipients(Patient[] recipients)
	{
		int n = recipients.length;
		int i,j,max = 0; //initially assigning first recipient in the list as the maximum point bearer
		Patient temp;

		for(i=0 ; i<n-1 ; i++)
		{
			for(j=i+1 ; j<n ; j++)
			{
				if(recipients[i].points < recipients[j].points)
				{
					temp = recipients[i];
					recipients[i] = recipients[j];
					recipients[j] = temp;
				}	
			}
		}
		for(i=0;i<n;i++)
		{
			pwriter.print("Recipient ID: "+recipients[i].id);
			pwriter.println("\t Name: "+recipients[i].name);
		}

	}
}