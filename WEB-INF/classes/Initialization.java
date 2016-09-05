import java.sql.*;
public class Initialization
{
	Connection conn;
	PreparedStatement pstmt = null;
	String sql = "";
	String table_name = "";
	int i,j;
	public Initialization()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/kidney_transplantation","root","root");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	public void fill_OPO_details()
	{
		try
		{
			sql = "create table city_OPO_details(City varchar(20),OPO varchar(20), PRIMARY KEY(City))";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

			String[][] city = new String[4][5];
			city[0] = new String[]{"Chennai","Thiruvallur","Vellore","Viluppuram","Thiruvannamalai"};
			city[1] = new String[]{"Coimbatore","Nilgris","Erode","Salem","Tiruppur"};
			city[2] = new String[]{"Madurai","Kanyakumari","Tuticorin","Tirunelveli","Dindigul"};
			city[3] = new String[]{"Trichy","Namakkal","Tanjore","Perambalur","Karur"};

			String[] opo = {"Chennai", "Coimbarore","Madurai","Trichy"};

			pstmt = conn.prepareStatement("insert into city_OPO_details values(?,?)");

			for(i=0; i<4; i++)
			{
				pstmt.setString(2,opo[i]);
				for(j=0; j<5; j++)
				{
					pstmt.setString(1,city[i][j]);
					pstmt.executeUpdate();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Initialization init = new Initialization();
		init.fill_OPO_details();
	}
}