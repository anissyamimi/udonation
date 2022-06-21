package udonation;
import java.sql.Connection;
import java.sql.DriverManager;

public class testDatabase {
	public static void main(String[]args) {
		
		try {
			//step 1 load the driver class
			Class.forName("org.postgresql.Driver");
			//step 2 create the connection object
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/ec2-23-23-182-238.compute-1.amazonaws.com/dafinkurrnupvh","lettugfccpthmf","06c5594146c3b74f83bc9d58dfcc00d66587b37fb6c5a0eb3d37a1a9aa1dd752");
			//step 3 create the statement object
			java.sql.Statement stmt = con.createStatement();
			String sql = "SELECT * FROM committee;";
			//Step 4 execute query
			stmt.executeUpdate(sql);
			//step 5 close the connection object
			con.close();
		}
		
		catch(Exception e) {
			System.out.print(e);
		}
	}
}