package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static src.Constants.COLUMN_LABEL_AGE;
import static src.Constants.COLUMN_LABEL_NAME;

public class T3GetMinionNames {

    private static final String GET_MINIONS = "select m.name, m.age from minions as m join minions_villains mv on m.id = mv.minion_id where mv.villain_id = ?";
    private static final String NO_VILLAINS_FORMAT = "No villains with ID %d exist in the database.";
    private static final String GET_VILLAIN_BY_ID = "select v.name from villains v where v.id = ?";
    private static final String VILLAIN_FORMAT = "Villain: %s%n";
    private static final String MINION_FORMAT = "%d. %s %d%n";


    public static void main(String[] args) throws SQLException {

        int i = new Scanner(System.in).nextInt();

        Connection connection = Utils.getSQLConnection();

        PreparedStatement villainNameStatement = connection.prepareStatement(GET_VILLAIN_BY_ID);
        villainNameStatement.setInt(1, i);
        final ResultSet villainNameResult = villainNameStatement.executeQuery();

        if (!villainNameResult.next()) {
            System.out.printf(NO_VILLAINS_FORMAT, i);
            connection.close();
            return;
        }

        final String villainName = villainNameResult.getString(COLUMN_LABEL_NAME);
        System.out.printf(VILLAIN_FORMAT, villainName);

        PreparedStatement minionsStatement = connection.prepareStatement(GET_MINIONS);
        minionsStatement.setInt(1, i);
        final ResultSet minionsResultSet = minionsStatement.executeQuery();

        for (int j = 1; minionsResultSet.next(); j++) {
            final String minionName = minionsResultSet.getString(COLUMN_LABEL_NAME);
            final int minionAge = minionsResultSet.getInt(COLUMN_LABEL_AGE);
            System.out.printf(MINION_FORMAT,j, minionName, minionAge);
        }

        connection.close();
    }
}
