package edu.uwec.cs.ericsosm5070.migration;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Migration {

	static final String DB_URL = "jdbc:oracle:thin:@dario.cs.uwec.edu:1521:csdev";
	
	static final String USER = "ERICSOSM5070";
	static final String PASS = "KWPI9MDP";
	
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
////////////////////////////////////////////////////////////////////////////////	
		/////THIS IS FOR READING IN THE MAIN TABLE
//		try {
//		conn = DriverManager.getConnection(DB_URL, USER, PASS);
//		String sql = "INSERT INTO Features_Master (feature_id, feature_name, feature_class, state_alpha, state_numeric, county_name, county_numeric,  primary_lat_dms, prim_long_dms, prim_lat_dec, prim_long_dec, source_lat_dms, source_long_dms, source_lat_dec, source_long_dec, elev_in_m, elev_in_ft, map_name, date_created, date_edited) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		PreparedStatement preparedStatement = conn.prepareStatement(sql);
//		preparedStatement.setString(1, "Value");	
//			
//		Scanner in = new Scanner( new File("WY_Features_20111005.txt"));
//		in.useDelimiter(Pattern.compile("[|\n]"));
//		// 12, 13, 14, 15, 20 can be null
//		
//		in.nextLine();
//		int i = 0;
//		while (in.hasNextLine()) { 
//			i++;
//			if (in.hasNextInt()) {
//				preparedStatement.setInt (1, in.nextInt()); //1
//			} else {
//				preparedStatement.setString(1, null);
//				in.next();
//			}
//			preparedStatement.setString(2, in.next()); //2
//			preparedStatement.setString(3, in.next()); //3
//			preparedStatement.setString(4, in.next()); //4
//			preparedStatement.setInt(5, in.nextInt()); //5
//			preparedStatement.setString(6, in.next()); //6
//			if (in.hasNextInt()) {
//				preparedStatement.setInt(7, in.nextInt());//7
//			} else {
//				preparedStatement.setString(7, null);
//				in.next();
//			}
//			preparedStatement.setString(8, in.next()); //8
//			preparedStatement.setString(9, in.next()); //9
//			preparedStatement.setDouble(10, in.nextDouble()); //10
//			preparedStatement.setDouble(11, in.nextDouble()); //11
//			String twelve = in.next();
//			if (!twelve.equals("")) {
//				preparedStatement.setString(12, twelve); //12
//			} else {
//				preparedStatement.setString(12, null); 
//			}
//			String thirteen = in.next();
//			if (!thirteen.equals("")) {
//				preparedStatement.setString(13, thirteen); //13
//			} else {
//				preparedStatement.setString(13, null); //13
//			}
//			if (in.hasNextDouble()) {
//				preparedStatement.setDouble(14, in.nextDouble()); //14
//			} else {
//				preparedStatement.setString(14, null); 
//				in.next();
//			}
//			if (in.hasNextDouble()) {
//				preparedStatement.setDouble(15, in.nextDouble()); //15
//			} else {
//				preparedStatement.setString(15, null); //15
//				in.next();
//			}
//			preparedStatement.setInt(16, in.nextInt()); //16
//			preparedStatement.setInt(17, in.nextInt()); //17
//			preparedStatement.setString(18, in.next()); //18
//			preparedStatement.setString(19, in.next()); //19
//			String twenty = in.next();
//			if (!twenty.equals("")) {
//				preparedStatement.setString(20, twenty); //20
//			} else {
//				preparedStatement.setString(20, null); //18
//			}
//			ADD BATCH will make this faster
//			preparedStatement.executeUpdate();
//			System.out.println("Executing row " + i + "...");
//		}		
//		
//		in.close();
//
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		} finally {
//		try{
//			if(rs != null) {
//				rs.close();
//			}
//		} catch(SQLException sqle) {
//			sqle.printStackTrace();
//		}
//		try {
//			if (stmt != null) {
//				stmt.close();
//			}	
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		}
//		try {
//			if (conn != null) {
//				conn.close();
//			}	
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		}
//	}
//	}
////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////
		///// THIS IS FOR READING INTO THE NORMALIZED TABLES
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			
			// feature_state table
			String query = "SELECT DISTINCT state_numeric, state_alpha FROM Features_Master";
			rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				int state_numeric = rs.getInt("state_numeric");
//				String state_alpha = rs.getString("state_alpha");
//				String sqlInsertIntoFeatureState = "INSERT INTO feature_state (state_numeric, state_alpha) VALUES (?, ?)";
//				PreparedStatement preparedStatement2 = conn.prepareStatement(sqlInsertIntoFeatureState);
//				preparedStatement2.setInt(1, state_numeric);
//				preparedStatement2.setString(2, state_alpha);
//				preparedStatement2.executeUpdate();
//			}
//			
//			
//			// feature_county table
//			String query2 = "SELECT DISTINCT county_numeric, state_numeric, county_name FROM Features_Master";
//			rs = stmt.executeQuery(query2);
//			while (rs.next()) {
//				int county_numeric = rs.getInt("county_numeric");
//				String county_name = rs.getString("county_name");
//				int state_numeric = rs.getInt("state_numeric");
//				String sqlInsertIntoFeatureCounty = "INSERT INTO feature_county (county_numeric, county_name, state_numeric) VALUES (?, ?, ?)";
//				PreparedStatement preparedStatement3 = conn.prepareStatement(sqlInsertIntoFeatureCounty);
//				preparedStatement3.setInt(1, county_numeric);
//				preparedStatement3.setString(2, county_name);
//				preparedStatement3.setInt(3, state_numeric);
//				preparedStatement3.executeUpdate();
//			}			
//			
//
//			
//			// feature_info table
//			// FIX THIS TABLE
//			String query3 = "SELECT DISTINCT feature_id, feature_name, feature_class, state_numeric, map_name, elev_in_ft, date_created FROM Features_Master";
//			rs = stmt.executeQuery(query3);
//			String sqlInsertIntoFeatureInfo = "INSERT INTO feature_info (feature_id, feature_name, feature_class, state_numeric, map_name, elev_in_ft, date_created) VALUES (?, ?, ?, ?, ?, ?, ?)";
//			PreparedStatement preparedStatement4 = conn.prepareStatement(sqlInsertIntoFeatureInfo);
//			while (rs.next()) {
//				int feature_id = rs.getInt("feature_id");
//				String feature_name = rs.getString("feature_name");
//				String feature_class = rs.getString("feature_class");
//				int state_numeric = rs.getInt("state_numeric");
//				String map_name = rs.getString("map_name");
//				int elev_in_ft = rs.getInt("elev_in_ft");
//				String date_created = rs.getString("date_created");
//			
//				preparedStatement4.setInt(1, feature_id);
//				preparedStatement4.setString(2, feature_name);
//				preparedStatement4.setString(3, feature_class);
//				preparedStatement4.setInt(4, state_numeric);
//				preparedStatement4.setString(5, map_name);
//				preparedStatement4.setInt(6, elev_in_ft);
//				preparedStatement4.setString(7, date_created);
//				preparedStatement4.executeUpdate();
//			}
			
			// feature_date_seq sequence 
//			stmt.executeUpdate("CREATE SEQUENCE feature_date_seq START WITH 1 INCREMENT BY 1");
		
			//feature_date table
			// needs feature_info table to work before it can work
//			String query10 = "SELECT feature_date_seq.nextval, feature_id, date_edited FROM Features_Master, DUAL";
//			rs = stmt.executeQuery(query10);
//			String sqlInsertIntoFeatureDate = "INSERT INTO feature_date (feature_date_id, feature_id, date_edited) VALUES (feature_date_seq.nextval, ?, ?)";
//			PreparedStatement preparedStatement6 = conn.prepareStatement(sqlInsertIntoFeatureDate);
//			while (rs.next()) {
//				int feature_id = rs.getInt("feature_id"); 
//				String date_edited = rs.getString("date_edited");
//				preparedStatement6.setInt(1, feature_id);
//				preparedStatement6.setString(2, date_edited); //date_edited can be null
//				preparedStatement6.executeUpdate();
//			}
			
//			 feature_source_seq sequence
//			stmt.executeUpdate("CREATE SEQUENCE feature_source_seq START WITH 1 INCREMENT BY 1");
			
			// feature_source table
			// needs other tables before it can work
			String query4 = "SELECT feature_source_seq.nextval, feature_id, source_lat_dms, source_long_dms FROM Features_Master, DUAL";
			rs = stmt.executeQuery(query4);
			String sqlInsertIntoFeatureSource = "INSERT INTO feature_source (feature_source_id, feature_id, source_lat_dms, source_long_dms) VALUES (feature_source_seq.nextval, ?, ?, ?)";
			PreparedStatement preparedStatement8 = conn.prepareStatement(sqlInsertIntoFeatureSource);
			while (rs.next()) {
				int feature_id = rs.getInt("feature_id"); 
				String source_lat_dms = rs.getString("source_lat_dms");
				String source_long_dms = rs.getString("source_long_dms");
				preparedStatement8.setInt(1, feature_id);
				preparedStatement8.setString(2, source_lat_dms);
				preparedStatement8.setString(3, source_long_dms);
				preparedStatement8.executeUpdate();
			}
			//source_lat_dms can be null
			//source_long_dms can be null
			
			// 1 = feature_id
			// 2 = feature_name
			// 3 = feature_class
			// 4 = state_alpha
			// 5 = state_numeric
			// 6 = county_name
			// 7 = county_numeric
			// 8 = primary_lat_dms
			// 9 = prim_long_dms
			//10 = prim_late_dec
			//11 = prim_long_dec
			//12 = source_lat_dms
			//13 = source_long_dms
			//14 = source_lat_dec
			//15 = source_long_dec
			//16 = elev_in_m
			//17 = elev_in_f
			//18 = map_name
			//19 = date_created
			//20 = date_edited
				
			//NOT USING: # 10, 11, 14, 15, 16 when creating tables
			// 12, 13, 20 can be null
			
			
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException sqle) {
				sqle.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	
	


	}
}

