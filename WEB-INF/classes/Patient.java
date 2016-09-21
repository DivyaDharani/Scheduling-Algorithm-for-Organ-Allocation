import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
class Patient
{
	int id;
	String user_name=null;
	Connection conn=null;
	PreparedStatement pstmt = null;
	ResultSet result_set = null;
	String type;
	String name, gender, contact_no, dob, blood_group, medical_history;
	int hla_a1, hla_a2, hla_b1, hla_b2, hla_dr1, hla_dr2, age;
	int points = 0;
	Patient(int id,String type)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");
			this.id = id;
			this.type = type;
			getAllValuesFromDB();
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
			pstmt = conn.prepareStatement("select * from "+type+"_details where "+type+"ID = "+id);
			ResultSet result = pstmt.executeQuery();
			if(result.next())
			{
				name = result.getString("Name");
				gender = result.getString("Gender");
				contact_no = result.getString("ContactNumber");
			}

			pstmt = conn.prepareStatement("select * from "+type+"_medical_report where "+type+"ID = "+id);
			result = pstmt.executeQuery();
			if(result.next())
			{
				dob = result.getString("DOB");
				//Calculate age
				int year = Integer.parseInt(dob.split("-")[0]);
				Date date = new Date(); //current date
	    		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy");
	    		int cur_year = Integer.parseInt(dateformat.format(date));

	    		age = cur_year - year;

				blood_group = result.getString("BloodGroup");
				hla_a1 = result.getInt("hla_a1");
				hla_a2 = result.getInt("hla_a2");
				hla_b1 = result.getInt("hla_b1");
				hla_b2= result.getInt("hla_b2");
				hla_dr1 = result.getInt("hla_dr1");
				hla_dr2 = result.getInt("hla_dr2");
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
