import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class SaldoApplicatie {

	public static void main(String[] args) throws SQLException {
		try (JdbcRowSet jrsMutatie = RowSetProvider.newFactory().createJdbcRowSet();
				JdbcRowSet jrsUpdate = RowSetProvider.newFactory().createJdbcRowSet()) {
			String query = "select klantnr, mutatie, uitgevoerd from j_saldomutatie";
			String url = "jdbc:mysql://localhost:3306/javacursus";
			String user = "cursist";
			String pwd = "vh_cursus";

			jrsMutatie.setCommand(query);
			jrsMutatie.setUrl(url);
			jrsMutatie.setUsername(user);
			jrsMutatie.setPassword(pwd);
			jrsMutatie.execute();

			while (jrsMutatie.next()) {
				String querySaldo = "select klantnr, opensaldo from j_klanten where klantnr ="
						+ jrsMutatie.getInt("klantnr");
				jrsUpdate.setCommand(querySaldo);
				jrsUpdate.setUrl(url);
				jrsUpdate.setUsername(user);
				jrsUpdate.setPassword(pwd);
				jrsUpdate.execute();

				jrsUpdate.absolute(-1);

				jrsUpdate.updateDouble("opensaldo", jrsUpdate.getDouble("opensaldo") + jrsMutatie.getDouble("mutatie"));

				jrsUpdate.updateRow();
			}

		}
	}
}