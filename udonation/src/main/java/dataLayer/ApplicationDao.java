package dataLayer;
import domainLayer.Application; 

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDao {

	private String jdbcURL = "jdbc:postgresql://localhost:5432/udonation";
	private String jdbcUsername = "postgres";
	private String jdbcPassword = "system";
	private String jdbcDriver = "org.postgresql.Driver";
	
	private static final String insert = "INSERT INTO APPLICATION"+"(applicationID,applicationDate,applicationTime,applicationStatus,applicantID,donationID,committeeID) VALUES"+"(?,?,?,?,?,?,?);";
	private static final String select = "select * from application where applicationID = ?;";
	private static final String selectAll = "select * from application";
	
	public ApplicationDao() {}
	
	protected Connection getConnection()
	{
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return connection;
	}
	
	//insert applicant
	public void insertApplication(Application application) throws SQLException
	{
		System.out.println(insert);
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insert)){
			
			preparedStatement.setInt(1, application.getApplicationID());
			preparedStatement.setDate(2, application.getApplicationDate());
			preparedStatement.setTime(3, application.getApplicationTime());
			preparedStatement.setString(4, application.getApplicationStatus());
			preparedStatement.setString(5, application.getApplicantID());
			preparedStatement.setInt(6, application.getDonationID());
			preparedStatement.setString(7, application.getCommitteeID());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e)
		{
			printSQLException(e);
		}
	}

	//select application by id

	public Application selectApplication(int id) {
		Application application = null;
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select);){
			preparedStatement.setInt(1,id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
						
				 Date applicationDate = rs.getDate("applicationDate");
				 Time applicationTime = rs.getTime("applicationTime");
				 String applicationStatus = rs.getString("applicationStatus");
				 String applicantID = rs.getString("applicantID");
				 int donationID = rs.getInt("donationID");
				 String committeeID = rs.getString("committeeID");
				
				application = new Application(id,applicationDate,applicationTime,applicationStatus,applicantID,donationID,committeeID);
			}
		    }
			catch (SQLException e) {
				printSQLException(e);
			}
		return application;
	}
	
	//select all application
	public List<Application> selectAllApplication(){
		List<Application> applications = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectAll);){
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				 int applicationID = rs.getInt("applicationID");
				 Date applicationDate = rs.getDate("applicationDate");
				 Time applicationTime = rs.getTime("applicationTime");
				 String applicationStatus = rs.getString("applicationStatus");
				 String applicantID = rs.getString("applicantID");
				 int donationID = rs.getInt("donationID");
				 String committeeID = rs.getString("committeeID");
				
				applications.add(new Application(applicationID,applicationDate,applicationTime,applicationStatus,applicantID,donationID,committeeID));
			}
			
			} catch(SQLException e) {
				printSQLException(e);
			}
			return applications;
	}
	
		private void printSQLException(SQLException ex) {
			for(Throwable e: ex) {
				if(e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: "+ ((SQLException) e).getSQLState());
					System.err.println("Error Code: "+ ((SQLException) e).getErrorCode());
					System.err.println("Message: "+ e.getMessage());
					Throwable t = ex.getCause();
					while(t != null) {
						System.out.println("Cause: "+t);
						t= t.getCause();
						}
					}
				}
			}		
}
