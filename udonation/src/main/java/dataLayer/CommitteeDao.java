package dataLayer;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domainLayer.Committee;

public class CommitteeDao {

	private String jdbcURL = "jdbc:postgresql://localhost:5432/udonation";
	private String jdbcUsername = "postgres";
	private String jdbcPassword = "system";
	private String jdbcDriver = "org.postgresql.Driver";
	
	private static final String insert = "INSERT INTO COMMITTEE"+"(committeeID,committeeName,committeePhoneNumber,committeeEmail,committeeAddress,committeeCity,committeePostcode,committeeState) VALUES"+"(?,?,?,?,?,?,?,?);";
	private static final String select = "select * from committee where committeeID = ?;";
	private static final String selectAll = "select * from committee";
	private static final String update = "update committee set committeeName = ?, committeePhoneNumber = ?, committeeEmail = ?, committeeAddress = ?, committeeCity = ?, committeePostcode = ?, committeeState = ? where committeeID = ?;";
	private static final String delete = "delete from committee where committeeID = ?;";
	
	public CommitteeDao() {}
	
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

	//insert committee
	public void insertCommittee(Committee committee) throws SQLException
	{
		System.out.println(insert);
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insert)){
			
			preparedStatement.setString(1, committee.getCommitteeID());
			preparedStatement.setString(1, committee.getCommitteeName());
			preparedStatement.setString(3, committee.getCommitteePhoneNumber());
			preparedStatement.setString(4, committee.getCommitteeEmail());
			preparedStatement.setString(5, committee.getCommitteeAddress());
			preparedStatement.setString(5, committee.getCommitteeCity());
			preparedStatement.setInt(2, committee.getCommitteePostcode());
			preparedStatement.setString(5, committee.getCommitteeState());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e)
		{
			printSQLException(e);
		}
	}
	
	//select committee by id
	public Committee selectCommittee(String id) {
		Committee committee = null;
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select);){
			preparedStatement.setString(1,id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				String committeeName = rs.getString("committeeName");
				String committeePhoneNumber = rs.getString("committeePhoneNumber");
				String committeeEmail = rs.getString("committeeEmail");
				String committeeAddress = rs.getString("committeeAddress");
				String committeeCity = rs.getString("committeeCity");
				int committeePostcode = rs.getInt("committeePostcode");
				String committeeState = rs.getString("committeeState");
				
	
				
				committee = new Committee(id,committeeName,committeePhoneNumber,committeeEmail,committeeAddress,committeeCity,committeePostcode,committeeState);
			}
			}catch (SQLException e) {
				printSQLException(e);
			}
		return committee;
	}
	
	//select all committee
	public List<Committee> selectAllCommittee(){
		List<Committee> committees = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectAll);){
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				String committeeID = rs.getString("committeeID");
				String committeeName = rs.getString("committeeName");
				String committeePhoneNumber = rs.getString("committeePhoneNumber");
				String committeeEmail = rs.getString("committeeEmail");
				String committeeAddress = rs.getString("committeeAddress");
				String committeeCity = rs.getString("committeeCity");
				int committeePostcode = rs.getInt("committeePostcode");
				String committeeState = rs.getString("committeeState");
				
				committees.add(new Committee(committeeID,committeeName,committeePhoneNumber,committeeEmail,committeeAddress,committeeCity,committeePostcode,committeeState));
			}
			
			} catch(SQLException e) {
				printSQLException(e);
			}
			return committees;
	}
 	
	//update committee
	public boolean updateCommittee(Committee committee) throws SQLException{
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(update);){
		
			System.out.println("Updated committee: "+statement);
			statement.setString(1, committee.getCommitteeName());
			statement.setString(2, committee.getCommitteePhoneNumber());
			statement.setString(3, committee.getCommitteeEmail());
			statement.setString(4, committee.getCommitteeAddress());
			statement.setString(5, committee.getCommitteeCity());
			statement.setInt(6, committee.getCommitteePostcode());
			statement.setString(7, committee.getCommitteeState());
			statement.setString(8, committee.getCommitteeID());
			
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	//delete committee
	public boolean deleteCommittee(String id) throws SQLException{
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
