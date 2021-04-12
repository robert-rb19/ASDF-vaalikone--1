import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {

	//private static  Connection jdbcConnection;

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/vaalikonedb";	
		String username = "root";
		String dbpassword = "Spindem18!";
		
		
		String fullname= "";
		String password= "";
		String email= "";
		String questions= "";
		int answers = 0;
		String comment= "";
		
		Candidate candidate = new Candidate();
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			
			if(connection != null)
			{
				System.out.println("connected to the database");
				
				connection.close();
		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				 
		try {
			
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			
			
			String sql = "INSERT INTO candidate (fullname, password, email) VALUES(?,?,?)";
					
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,candidate.get(fullname));
			statement.setString(2, candidate.get(password));
			statement.setString(3, candidate.get(email));
			
			int rows = statement.executeUpdate();
			
			if (rows > 0)
			{
				System.out.println("A new user has been inserted successfully.");
				
			}
			
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			String sql = "INSERT INTO questions (questions) VALUES(?)";
					
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, questions);
			
			int rows = statement.executeUpdate();
			
			if (rows > 0)
			{
				System.out.println("A new user has been inserted successfully.");
				
			}
			
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			String sql = "INSERT INTO answers (answers, comment) VALUES(?,?)";
					
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, answers);
			statement.setString(2, comment);
			
			int rows = statement.executeUpdate();
			
			if (rows > 0)
			{
				System.out.println("A new user has been inserted successfully.");
				
			}
			
			connection.close();
			
		     
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			
			String sql = "SELECT * FROM candidate";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			
			
			while (result.next())
			{
				int candidate_id = result.getInt("candidate_id");
				 fullname =  result.getString("fullname");
				 password = result.getString("password");
				 email = result.getString("email");
				 
				 System.out.println(candidate_id + "," + fullname + "," + password + "," + email);
				
				
				
				
				
			}
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			
			String sql = "SELECT * FROM questions";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			
			
			while (result.next())
			{
				 questions = result.getString("questions");
				
				
			}
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			
			String sql = "SELECT * FROM answers";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			
			
			while (result.next())
			{
				 answers = result.getInt("answers");
				 comment = result.getString("comment");
				
				
			}
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			String sql = "UPDATE candidate SET fullname = ?, password = ?, email = ?";
			
			 
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, fullname);
			 statement.setString(2, password);
			 statement.setString(3, email);
			 
			
			 
			 
			int rowUpdated = statement.executeUpdate();
			
			if(rowUpdated > 0)
			{
				System.out.println(" The candidate's infomation has been updated ");
			}
			
			 
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			String sql = "UPDATE questions SET questions = ?";
			
			 
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, questions);
			 
			 
			
			 
			 
			int rowUpdated = statement.executeUpdate();
			
			if(rowUpdated > 0)
			{
				System.out.println(" The question has been updated ");
			}
			
			 
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			String sql = "UPDATE answers SET answers = ? , comment = ?";
			
			 
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setInt(1, answers);
			 statement.setNString(2, comment);
			 
			 
			
			 
			 
			int rowUpdated = statement.executeUpdate();
			
			if(rowUpdated > 0)
			{
				System.out.println(" The answer has been updated ");
			}
			
			 
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			String sql = "DELETE FROM candidate WHERE fullname = ? ";
			
			 
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, fullname);
			
			
			 
			 
			int rowDeleted = statement.executeUpdate();
			
			if(rowDeleted > 0)
			{
				System.out.println(" The candidate's infomation has been deleted ");
			}
			
			 
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			String sql = "DELETE FROM questions WHERE questions = ?";
			
			 
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, questions);
			 
			
			 
			 
			int rowDeleted = statement.executeUpdate();
			
			if(rowDeleted > 0)
			{
				System.out.println(" The question has been deleted ");
			}
			
			 
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, dbpassword);
			String sql = "DELETE FROM answers WHERE answers = ? ";
			
			 
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, comment);
			 
			 
			 
			
			 
			 
			int rowDeleted = statement.executeUpdate();
			
			if(rowDeleted > 0)
			{
				System.out.println(" The answer has been deleted ");
			}
			
			 
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		

	}

	
		
	}

	
	

	
	
	


