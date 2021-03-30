package REPORT;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Report {

	protected int ReportID;
	protected java.sql.Date Date;
	private String ReportData;

	// convert types
	public static List<List<String>> convertTypes(ArrayList<String[]> oldList){
		List<List<String>> list = new ArrayList<>();
		for (String[] strings : oldList){
			list.add(Arrays.asList(strings));
		}
		return list;
	}
}