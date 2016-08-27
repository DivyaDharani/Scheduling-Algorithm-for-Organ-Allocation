import java.sql.*;
class Donor 
{
	private int id;
	private String user_name=null;
	private Connection conn=null;
	private PreparedStatement pstmt = null;
	private ResultSet result_set = null;
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
