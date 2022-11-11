package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static src.Constants.COLUMN_LABEL_ID;

public class T4AddMinions {

    private static final String GET_TOWN_BY_NAME = "select * from towns t where t.name = ?";
    private static final String INSERT_TOWN = "insert into towns(name) values(?)";
    private static final String GET_VILLAIN_BY_NAME = "select * from villains v where v.name = ?";
    private static final String TOWN_ADDED_FORMAT = "Town %s was added to the database.%n";
    private static final String VILLAIN_ADDED_FORMAT = "Villain %s was added to the database.%n";

    private static final String EVILNESS_FACTOR = "evil";
    private static final String INSERT_VILLAIN = "insert into villains(name, evilness_factor) values(?, '" + EVILNESS_FACTOR + "')";
    private static final String INSERT_MINION = "insert into minions(name, age, town_id) values(?,?,?)";
    private static final String GET_LAST_MINION = "select m.id from minions m order by m.id desc limit 1";
    private static final String ADD_MINION_TO_VILLAIN = "insert into minions_villains(minion_id, villain_id) values(?,?)";
    private static final String FINAL_PRINT_FORMAT = "Successfully added %s to be minion of %s.";


    public static void main(String[] args) throws SQLException {

        final Scanner scanner = new Scanner(System.in);
        final Connection connection = Utils.getSQLConnection();

        final String[] minionInfo = scanner.nextLine().split("\\s+");
        final String minionName = minionInfo[1];
        final int minionAge = Integer.parseInt(minionInfo[2]);
        final String minionTown = minionInfo[3];
        String villainName = scanner.nextLine().split("\\s+")[1];

        final int townId = getId(connection, GET_TOWN_BY_NAME, INSERT_TOWN, TOWN_ADDED_FORMAT, minionTown);
        final int villainId = getId(connection, GET_VILLAIN_BY_NAME, INSERT_VILLAIN, VILLAIN_ADDED_FORMAT, villainName);

        final PreparedStatement insertMinionStatement = connection.prepareStatement(INSERT_MINION);
        insertMinionStatement.setString(1, minionName);
        insertMinionStatement.setInt(2, minionAge);
        insertMinionStatement.setInt(3, townId);
        int i = insertMinionStatement.executeUpdate();

        final PreparedStatement getMinionStatement = connection.prepareStatement(GET_LAST_MINION);
        final ResultSet lastMinionResultSet = getMinionStatement.executeQuery();
        lastMinionResultSet.next();
        final int minionId = lastMinionResultSet.getInt(COLUMN_LABEL_ID);

        final PreparedStatement addMinionToVillainStatement = connection.prepareStatement(ADD_MINION_TO_VILLAIN);
        addMinionToVillainStatement.setInt(1, minionId);
        addMinionToVillainStatement.setInt(2, villainId);
        addMinionToVillainStatement.executeUpdate();

        System.out.printf(FINAL_PRINT_FORMAT, minionName, villainName);
        connection.close();
    }


    private static int getId(Connection connection, String selectQuery, String insertQuery, String printFormat, String name) throws SQLException {
        final PreparedStatement getSelectStatement = connection.prepareStatement(selectQuery);
        getSelectStatement.setString(1, name);
        ResultSet getResultSet = getSelectStatement.executeQuery();

        if (!getResultSet.next()) {
            final PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, name);
            insertStatement.executeUpdate();
            System.out.printf(printFormat, name);
        }
        getResultSet = getSelectStatement.executeQuery();
        getResultSet.next();
        return getResultSet.getInt(COLUMN_LABEL_ID);
    }
}
