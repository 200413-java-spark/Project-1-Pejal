package spark_p1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.ipc.Transceiver;

public class SqlSparkRepository {
	private SqlDataSource dataSource;
	private List<Transaction_info> his;
	
	public SqlSparkRepository(SqlDataSource data)
	{
		this.dataSource=data;
		this.his=new ArrayList<>();
	}
	public void insertAll(List<Transaction_info> data)
	{
		String command="insert into transaction(transaction_date,product,price,payment_type,name,city,state,country,account_create,last_login,latitude,longitude) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try(Connection connection=this.dataSource.getConnection();PreparedStatement statement=connection.prepareStatement(command);)
		{
			for(Transaction_info d:data)
			{
				statement.setString(1, d.getTranDate());
				statement.setString(2, d.getProduct());
				statement.setString(3, d.getPrice());
				statement.setString(4, d.getPayType());
				statement.setString(5, d.getName());
				statement.setString(6, d.getCity());
				statement.setString(7, d.getState());
				statement.setString(8, d.getCountry());
				statement.setString(9, d.getCreate());
				statement.setString(10, d.getLogin());
				statement.setString(11, d.getLatitude());
				statement.setString(12, d.getLongitude());
				
				statement.addBatch();
			}
			statement.executeBatch();
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	

}
