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
	
	/**
	 * Get all books by Category
	 * @param category - Category NAme
	 * @return Book List
	 * @throws SQLException
	 * @throws RemoteException
	 */
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
			b.isbn = rs.getString("isbn_nr");
			b.author = rs.getString("nachname")+ ", " + rs.getString("vorname");
			b.category = category;
			b.price = rs.getDouble("preis");
			b.title = rs.getString("titel");
			b.coverImage = rs.getBytes("cover");
			b.description = rs.getString("beschreibung");
			books.add(b);
		}
		books.sort((b1, b2) -> b1.title.compareToIgnoreCase(b2.title));
		return books;
	}
	
	/**
	 * Send order to database
	 * @param books - Book List
	 * @throws SQLException
	 */
	public void SendOrder(ArrayList<Book> books) throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement("INSERT INTO kauf (kid, isb_nr) VALUES(1, ?);");
		for (Book book : books) {
			ps.setString(1, book.isbn);
			ps.execute();
		}
	}
}
