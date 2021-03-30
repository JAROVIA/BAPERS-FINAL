package ADMIN;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;



public class DBConfiguration {

	static String url = "jdbc:mysql://localhost:3306/Bapers";
	static String username = "jaroviadb";
	static String password = "Jarovia123#@!";
	static Connection connection;

	static {
		try {
			connection = DriverManager.getConnection(
					url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Date AutoTimeDBBackup;
	private Date AutoTimeDBRestore;

	public static void main(String[] args) throws IOException {
		DBConfiguration.MakeBackup();
		DBConfiguration.GetListOfFiles();
	}


	// should run when db backup button is clicked
	public static void MakeBackup() throws IOException {
		String string = "mysqldump -u jaroviadb -pJarovia123#@! Bapers -r BAPERS3/GENERATED/DATABASES/Backup" + Calendar.getInstance().getTimeInMillis() + ".sql";
		Process process = Runtime.getRuntime().exec(string);

	}

	public static void RestoreListedBackup(String FileName) throws IOException, SQLException {

		/**
		 * This code was written following this guide
		 * https://softorks.com/en/java/how-to-execute-shell-command-java-runtimeExec.php
		 * */

		String q = "";
		File f = new File("../BAPERS-FINAL/BAPERS3/GENERATED/DATABASES/" + FileName + ".sql"); // source path is the absolute path of dumpfile.

		BufferedReader bf = new BufferedReader(new FileReader(f));
		String line = null;
		line = bf.readLine();
		while (line != null) {
			q = q + line + "\n";
			line = bf.readLine();
		}

		String[] commands = q.split(";");

		Statement statement = connection.createStatement();
		for (String s : commands) {
			statement.execute(s);
		}
	}

	public static ArrayList<String> GetListOfFiles(){
		File file = new File("../BAPERS-FINAL/BAPERS3/GENERATED/DATABASES"); //file location
		String[] files = file.list();
		// file.list method returns all the files and puts it in a list.
		ArrayList<String> backups = new ArrayList<>();
		for (String name : files) { // outputting the contents of list.
			backups.add(name.split("\\.")[0]);
		}

		return backups;
	}

	public static void RestoreBackup() throws IOException, SQLException {


		/**
		 * This code was written following this guide
		 * https://softorks.com/en/java/how-to-execute-shell-command-java-runtimeExec.php
		 * */

		String q = "";
		File f = new File("/home/javonne/IdeaProjects/BAPERS-FINAL/BAPERS3/GENERATED/DATABASES/Backup1616954237524.sql");
		BufferedReader bf = new BufferedReader(new FileReader(f));
		String line = null;
		line = bf.readLine();

		while (line != null) {
			q = q + line + "\n";
			line = bf.readLine();
		}

		String[] commands = q.split(";");
		Statement statement = connection.createStatement();

		for (String s : commands) {
			statement.execute(s);
			}
		}


	public Date getAutoTimeDBBackup() {
		// TODO - implement DBConfiguration.getAutoTimeDBBackup
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AutoTimeDBBackup
	 */
	public void setAutoTimeDBBackup(Date AutoTimeDBBackup) {
		// TODO - implement DBConfiguration.setAutoTimeDBBackup
		throw new UnsupportedOperationException();
	}

	public Date getAutoTimeDBRestore() {
		// TODO - implement DBConfiguration.getAutoTimeDBRestore
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AutoTimeDBRestore
	 */
	public void setAutoTimeDBRestore(Date AutoTimeDBRestore) {
		// TODO - implement DBConfiguration.setAutoTimeDBRestore
		throw new UnsupportedOperationException();
	}

	public static DBConfiguration DBConfiguration() {
		// TODO - implement DBConfiguration.DBConfiguration
		throw new UnsupportedOperationException();
	}

}
