import java.sql.*;

public class MySQLDataBaseConnection
{
	static Connection con;
	static Statement st;
	static ResultSet rs;
	
	public static void main(String args[])
	{
		MySQLConnect();
	}
	
	public static void MySQLConnect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/desktop","root","root");
		    st = con.createStatement();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("MySQL  Connection Succesful..");
		String value = "";
		try
		{
				rs = st.executeQuery("select * from login where username = 'admin'");
				if(rs.next())
				{
					value = rs.getString(2);
				}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		
	
		System.out.println(value);
	}
	
	
	
}

