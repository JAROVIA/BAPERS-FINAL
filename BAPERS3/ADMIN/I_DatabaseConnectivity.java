package BAPERS3.ADMIN;

public abstract interface I_DatabaseConnectivity {

	/**
	 * 
	 * @param SQL
	 */
	abstract Connection Connect(string SQL);

	/**
	 * 
	 * @param SQL
	 * @param conn
	 */
	abstract void Read(string SQL, connection conn);

	/**
	 * 
	 * @param SQL
	 */
	abstract int Write(string SQL);

	/**
	 * 
	 * @param Connection
	 */
	abstract boolean CloseConnection(Connection Connection);

}