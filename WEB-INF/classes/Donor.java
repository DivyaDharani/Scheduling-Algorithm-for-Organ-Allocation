import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
class Donor 
{
	private int id;
	private String user_name=null;
	private Connection conn=null;
	private PreparedStatement pstmt = null;
	private ResultSet result_set = null;
	String name, gender, contact_no, dob, blood_group, medical_history;
	int hla_a1, hla_a2, hla_b1, hla_b2, hla_dr1, hla_dr2, age;
	Donor(int id)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");
			this.id = id;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	void getAllValuesFromDB() 
	{
		try
		{
			pstmt = conn.prepareStatement("select * from donor_details where DonorID = "+id);
			ResultSet result = pstmt.executeQuery();
			if(result.next())
			{
				name = result.getString("Name");
				gender = result.getString("Gender");
				contact_no = result.getString("ContactNumber");
			}

			pstmt = conn.prepareStatement("select * from donor_medical_report where DonorID = "+id);
			result = pstmt.executeQuery();
			if(result.next())
			{
				dob = result.getString("DOB");
				//Calculate age
				int year = Integer.parseInt(dob.split("-")[0]);
				Date date = new Date();
	    		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy");
	    		int cur_year = Integer.parseInt(dateformat.format(date));

	    		age = cur_year - year;

				blood_group = result.getString("BloodGroup");
				hla_a1 = result.getInt("hla_a_1");
				hla_a2 = result.getInt("hla_a_2");
				hla_b1 = result.getInt("hla_b_1");
				hla_b2= result.getInt("hla_b_2");
				hla_dr1 = result.getInt("hla_dr_1");
				hla_dr2 = result.getInt("hla_dr_2");
				medical_history = result.getString("MedicalHistory");

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	int getID()
	{
		return id; 
	}
	String getName()
	{
		if(user_name!=null)
			return user_name;
		try
		{
			pstmt = conn.prepareStatement("select UserName from donor_credentials where ID="+id);
			result_set = pstmt.executeQuery();
			if(result_set.next())
				user_name = result_set.getString("UserName");
		}
		catch(Exception e)
		{
			e.printStackTrace();		
		}
		return user_name;
	}
}
