package ADMIN;

import java.sql.Connection;

public class DatabaseConnectivity implements I_DatabaseConnectivity {

	/**
	 * 
	 * @param SQL
	 */

	public Connection Connect(String SQL) {
		// TODO - implement DatabaseConnectivity.Connect
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param SQL
	 * @param conn
	 */
	public void Read(String SQL, Connection conn) {
		// TODO - implement DatabaseConnectivity.Read
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param SQL
	 */
	public int Write(String SQL) {
		// TODO - implement DatabaseConnectivity.Write
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Connection
	 */
	public boolean CloseConnection(Connection Connection) {
		// TODO - implement DatabaseConnectivity.CloseConnection
		throw new UnsupportedOperationException();
	}

	public static DatabaseConnectivity DatabaseConnectivity() {
		// TODO - implement DatabaseConnectivity.DatabaseConnectivity
		throw new UnsupportedOperationException();
	}

}