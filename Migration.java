package edu.uwec.cs.ericsosm5070.migration;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Migration {

	static final String DB_URL = "jdbc:oracle:thin:@dario.cs.uwec.edu:1521:csdev";
	
	static final String USER = "ERICSOSM5070";
	static final String PASS = "KWPI9MDP";
	
	public static void main(String[] args) throws FileNotFoundException {
		Connection conn = null;
			
		try {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String sql = "INSERT INTO Features_Master (feature_id, feature_name, feature_class, state_alpha, state_numeric, county_name, county_numeric,  primary_lat_dms, prim_long_dms, prim_lat_dec, prim_long_dec, source_lat_dms, source_long_dms, source_lat_dec, source_long_dec, elev_in_m, elev_in_ft, map_name, date_created, date_edited) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, "Value");	
			
		Scanner in = new Scanner( new File("WY_Features_20111005.txt"));
		in.useDelimiter(Pattern.compile("[|\n]"));
		// 12, 13, 14, 15, 20 can be null
		
		in.nextLine();
		int i = 0;
		while (in.hasNextLine()) { 
			i++;
			if (in.hasNextInt()) {
				preparedStatement.setInt (1, in.nextInt()); //1
			} else {
				preparedStatement.setString(1, null);
				in.next();
			}
			preparedStatement.setString(2, in.next()); //2
			preparedStatement.setString(3, in.next()); //3
			preparedStatement.setString(4, in.next()); //4
			preparedStatement.setInt(5, in.nextInt()); //5
			preparedStatement.setString(6, in.next()); //6
			if (in.hasNextInt()) {
				preparedStatement.setInt(7, in.nextInt());//7
			} else {
				preparedStatement.setString(7, null);
				in.next();
			}
			preparedStatement.setString(8, in.next()); //8
			preparedStatement.setString(9, in.next()); //9
			preparedStatement.setDouble(10, in.nextDouble()); //10
			preparedStatement.setDouble(11, in.nextDouble()); //11
			String twelve = in.next();
			if (!twelve.equals("")) {
				preparedStatement.setString(12, twelve); //12
			} else {
				preparedStatement.setString(12, null); 
			}
			String thirteen = in.next();
			if (!thirteen.equals("")) {
				preparedStatement.setString(13, thirteen); //13
			} else {
				preparedStatement.setString(13, null); //13
			}
			if (in.hasNextDouble()) {
				preparedStatement.setDouble(14, in.nextDouble()); //14
			} else {
				preparedStatement.setString(14, null); 
				in.next();
			}
			if (in.hasNextDouble()) {
				preparedStatement.setDouble(15, in.nextDouble()); //15
			} else {
				preparedStatement.setString(15, null); //15
				in.next();
			}
			preparedStatement.setInt(16, in.nextInt()); //16
			preparedStatement.setInt(17, in.nextInt()); //17
			preparedStatement.setString(18, in.next()); //18
			preparedStatement.setString(19, in.next()); //19
			String twenty = in.next();
			if (!twenty.equals("")) {
				preparedStatement.setString(20, twenty); //20
			} else {
				preparedStatement.setString(20, null); //18
			}
			preparedStatement.executeUpdate();
			System.out.println("Executing row " + i + "...");
		}		
		
		in.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	
}
