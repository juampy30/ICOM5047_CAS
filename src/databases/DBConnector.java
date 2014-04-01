package databases;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	String url;
	Connection connection;

	public DBConnector(String url){
		this.url = url;
	}

	public Connection connect() throws SQLException{
		connection = DriverManager.getConnection(url, "postgres", "manzor321A");	
		System.out.println("Connect to DB!");
		return connection;

	}
	public boolean disconnect() throws SQLException{
		connection.close();
		return true;

	}

}

