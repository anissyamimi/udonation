package dataLayer;

import java.sql.Connection;  
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domainLayer.Donation;

public class DonationDao {

	private String jdbcURL = "jdbc:postgresql://localhost:5432/udonation";
	private String jdbcUsername = "postgres";
	private String jdbcPassword = "system";
	private String jdbcDriver = "org.postgresql.Driver";
		
	private static final String insert = "INSERT INTO DONATION"+"(donationID,donationName,donationDescription,donationOpenDate,donationCloseDate) VALUES"+"(?,?,?,?,?);";
	private static final String select = "select * from donation where donationID = ?;";
	private static final String selectAll = "select * from donation";
	private static final String update = "update donation set donationName = ?, donationDescription = ?, donationOpenDate = ?, donationCloseDate = ? where donationID = ?;";
	private static final String delete = "delete from donation where donationID = ?;";
	
	public DonationDao() {}
	
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
	
	//insert donation
	public void insertDonation(Donation donation) throws SQLException
	{
		System.out.println(insert);
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insert)){
			
			preparedStatement.setInt(1, donation.getDonationID());
			preparedStatement.setString(2, donation.getDonationName());
			preparedStatement.setString(3, donation.getDonationDescription());
			preparedStatement.setDate(4, donation.getDonationOpenDate());
			preparedStatement.setDate(5, donation.getDonationCloseDate());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e)
		{
			printSQLException(e);
		}
	}
	
	//select donation by id
	public Donation selectDonation(int id) {
		Donation donation = null;
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select);){
			preparedStatement.setInt(1,id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				
				String donationName = rs.getString("donationName");
				String donationDescription = rs.getString("donationDescription");
				Date donationOpenDate = rs.getDate("donationOpenDate");
				Date donationCloseDate = rs.getDate("donationCloseDate");
				
				donation = new Donation(id,donationName,donationDescription,donationOpenDate,donationCloseDate);
			}
			}catch (SQLException e) {
				printSQLException(e);
			}
		return donation;
	}
	//select all donations
	public List<Donation> selectAllDonation(){
		List<Donation> donations = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectAll);){
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int donationID = rs.getInt("donationID");
				String donationName = rs.getString("donationName");
				String donationDescription = rs.getString("donationDescription");
				Date donationOpenDate = rs.getDate("donationOpenDate");
				Date donationCloseDate = rs.getDate("donationCloseDate");
				
				donations.add(new Donation(donationID,donationName,donationDescription,donationOpenDate,donationCloseDate));
			}
			
			} catch(SQLException e) {
				printSQLException(e);
			}
			return donations;
	}
 	
	//update donation
	public boolean updateDonation(Donation donation) throws SQLException{
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(update);){
		
			System.out.println("Updated donation: "+statement);
			statement.setString(1, donation.getDonationName());
			statement.setString(2, donation.getDonationDescription());
			statement.setDate(3, donation.getDonationOpenDate());
			statement.setDate(4, donation.getDonationCloseDate());
			statement.setInt(5, donation.getDonationID());
			
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	//delete donation
	public boolean deleteDonation(int id) throws SQLException{
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(delete);){
			statement.setInt(1, id);
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

