package dataLayer;
import domainLayer.Applicant;  

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicantDao {

	private String jdbcURL = "jdbc:postgresql://localhost:5432/udonation";
	private String jdbcUsername = "postgres";
	private String jdbcPassword = "system";
	private String jdbcDriver = "org.postgresql.Driver";
	
	private static final String insert = "INSERT INTO APPLICANT"+"(applicantID,applicantName,applicantPhoneNumber,applicantEmail,applicantAddress,applicantCity,applicantPostcode,applicantState,applicantEmploymentType) VALUES"+"(?,?,?,?,?,?,?,?,?);";
	private static final String select = "select * from applicant where applicantID = ?;";
	private static final String selectAll = "select * from applicant";
	private static final String update = "update applicant set applicantID = ?, applicantName = ?, applicantAddress = ?, applicantPhoneNumber = ?, applicantSalary = ?, applicantEmail = ?, applicantAge = ? applicantMaritalStatus = ?, applicantEmployementType = ?, applicantDateOfBirth = ?, applicantPlaceOfBirth = ?, spouseID = ? where id = ?;";
	private static final String delete = "delete from applicant where applicantID = ?;";
	
	public ApplicantDao() {}
	
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
	public void insertApplicant(Applicant applicant) throws SQLException
	{
		System.out.println(insert);
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insert)){
			
			preparedStatement.setString(1, applicant.getApplicantID());
			preparedStatement.setString(2, applicant.getApplicantName());
			preparedStatement.setString(3, applicant.getApplicantPhoneNumber());
			preparedStatement.setString(4, applicant.getApplicantEmail());
			preparedStatement.setString(5, applicant.getApplicantAddress());
			preparedStatement.setString(6, applicant.getApplicantCity());
			preparedStatement.setInt(7, applicant.getApplicantPostcode());
			preparedStatement.setString(8, applicant.getApplicantState());
			preparedStatement.setString(9, applicant.getApplicantEmploymentType());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e)
		{
			printSQLException(e);
		}
	}

	//select Applicant by id
	public Applicant selectApplicant(String id) {
		Applicant applicant = null;
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select);){
			preparedStatement.setString(1,id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				String applicantName = rs.getString("applicantName");
				String applicantPhoneNumber = rs.getString("applicantPhoneNumber");
				String applicantEmail = rs.getString("applicantEmail");
				String applicantAddress = rs.getString("applicantAddress");
				String applicantCity = rs.getString("applicantCity");
				int applicantPostcode = rs.getInt("applicantPostcode");
				String applicantState = rs.getString("applicantState");
				String applicantEmploymentType = rs.getString("applicantEmploymentType");
				
				applicant = new Applicant(id,applicantName,applicantPhoneNumber,applicantAddress,applicantEmail,applicantCity,applicantPostcode,applicantState,applicantEmploymentType);
			}
			}catch (SQLException e) {
				printSQLException(e);
			}
		return applicant;
	}
	
	//select all Applicants
	public List<Applicant> selectAllApplicant(){
		List<Applicant> applicants = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectAll);){
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String applicantID = rs.getString("applicantID");
				String applicantName = rs.getString("applicantName");
				String applicantPhoneNumber = rs.getString("applicantPhoneNumber");
				String applicantEmail = rs.getString("applicantEmail");
				String applicantAddress = rs.getString("applicantAddress");
				String applicantCity = rs.getString("applicantCity");
				int applicantPostcode = rs.getInt("applicantPostcode");
				String applicantState = rs.getString("applicantState");
				String applicantEmploymentType = rs.getString("applicantEmploymentType");
				
				applicants.add(new Applicant(applicantID,applicantName,applicantPhoneNumber,applicantAddress,applicantEmail,applicantCity,applicantPostcode,applicantState,applicantEmploymentType));
			}
			
			} catch(SQLException e) {
				printSQLException(e);
			}
			return applicants;
	}
 	
	//update Applicant
	public boolean updateApplicant(Applicant applicant) throws SQLException{
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(update);){
		
			System.out.println("Updated Applicant: "+statement);
			
			statement.setString(1, applicant.getApplicantName());
			statement.setString(2, applicant.getApplicantPhoneNumber());
			statement.setString(3, applicant.getApplicantEmail());
			statement.setString(4, applicant.getApplicantAddress());
			statement.setString(5, applicant.getApplicantCity());
			statement.setInt(6, applicant.getApplicantPostcode());
			statement.setString(7, applicant.getApplicantState());
			statement.setString(8, applicant.getApplicantEmploymentType());
			statement.setString(9, applicant.getApplicantID());
			
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	//delete Applicant
	public boolean deleteApplicant(String id) throws SQLException{
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(delete);){
			statement.setString(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
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

