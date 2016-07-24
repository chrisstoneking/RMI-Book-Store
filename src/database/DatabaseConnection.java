package database;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import task.Book;

public class DatabaseConnection {
	Connection conn = null;
	
	public DatabaseConnection()
	{
		this.LoadDriver();
	}
	
	private void LoadDriver()
	{
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/bookstore", "root", "" );
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Book> getBooksByCategory(String category) throws SQLException, RemoteException
	{
		ArrayList<Book> books = new ArrayList<>();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM buch AS b JOIN autor AS a ON a.aid = b.aid WHERE kategorie = ?");
		ps.setString(1, category);
		ResultSet rs = ps.executeQuery();
		Book b = null;
		while(rs.next())
		{
			b = new Book();
			b.author = rs.getString("nachname")+ ", " + rs.getString("vorname");
			b.category = category;
			b.price = rs.getDouble("preis");
			b.title = rs.getString("titel");
			b.coverImage = rs.getBytes("cover");
			b.description = rs.getString("beschreibung");
			books.add(b);
		}
		return books;
		
	}
}
