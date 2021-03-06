package ADMIN;

import java.sql.Connection;

public abstract interface I_DatabaseConnectivity {

	/**
	 * 
	 * @param SQL
	 */
	abstract Connection Connect(String SQL);

	/**
	 * 
	 * @param SQL
	 * @param conn
	 */
	abstract void Read(String SQL, Connection conn);

	/**
	 * 
	 * @param SQL
	 */
	abstract int Write(String SQL);

	/**
	 * 
	 * @param Connection
	 */
	abstract boolean CloseConnection(Connection Connection);

}