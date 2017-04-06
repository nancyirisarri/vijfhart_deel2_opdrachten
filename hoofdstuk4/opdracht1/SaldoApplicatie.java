import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mydrivermanager.MyDriverManager;

public class SaldoApplicatie {

	public static void main(String[] args) throws SQLException {
		try (Connection conn = new MyDriverManager().connect();
				Statement stMut = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rsMut = stMut
						.executeQuery("select id, klantnr, mutatie, uitgevoerd from j_saldomutatie for update");
				PreparedStatement psKlan = conn
						.prepareStatement("update j_klanten set opensaldo = opensaldo + ? where klantnr = ?");) {
			// Verwerk de gegevens uit de resultset rsMut
			// met behulp van het PreparedStatement psKlan

			rsMut.beforeFirst();
			while (rsMut.next()) {
				psKlan.setDouble(1, rsMut.getDouble("mutatie"));
				psKlan.setInt(2, rsMut.getInt("klantnr"));

				boolean result = psKlan.execute();

				rsMut.updateInt("uitgevoerd", 1);
				rsMut.updateRow();
			}

		}
	}
}