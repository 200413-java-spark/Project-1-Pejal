package spark_p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDataSource {
	private static SqlDataSource instance;
	private String url;
	private String user_name;
	private String password;
	
	private SqlDataSource()
	{
		url=System.getProperty("database.url","jdbc:postgresql://localhost:5432/mydata");
		user_name=System.getProperty("database.username", "mydata");
		password=System.getProperty("database.password", "mydata");
	}
	public static SqlDataSource getInstance()
	{
		if(instance==null)
			instance=new SqlDataSource();
		return instance;
	}
	public Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url,user_name,password);
	}

}
