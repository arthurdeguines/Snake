import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {
	static Connection connection = null;
	public Connexion() {
		long debut = System.currentTimeMillis();

	    System.out.println(System.currentTimeMillis()-debut);

		try {
	    	connection = DriverManager.getConnection("jdbc:mysql://mysql-arthurdeguines-projets.alwaysdata.net/arthurdeguines-projets_snake","150193","azerty44");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(System.currentTimeMillis()-debut);

	}
}
