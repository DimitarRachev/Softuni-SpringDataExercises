package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static src.Constants.COLUMN_LABEL_NAME;

public class T5ChangeTownNamesCasing {

    private static final String UPDATE_TOWN_NAME = "update towns t set name = upper(name) where t.country = ?";
    private static final String GET_ALL_TOWN_NAMES_BY_COUNTRY = "select t.name from towns t where t.country = ?";
    private static final String NO_TOWNS_AFFECTED = "No town names were affected.";
    private static final String TOWNS_AFFECTED = "%d town names were affected. %n[%s]";

    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getSQLConnection();

        final String countryName = new Scanner(System.in).nextLine();

        final PreparedStatement statement = connection.prepareStatement(UPDATE_TOWN_NAME);
        statement.setString(1, countryName);
        int i = statement.executeUpdate();
        if (i == 0) {
            System.out.println(NO_TOWNS_AFFECTED);
        } else {
            final PreparedStatement allTowns = connection.prepareStatement(GET_ALL_TOWN_NAMES_BY_COUNTRY);
            allTowns.setString(1, countryName);
            ResultSet resultSet = allTowns.executeQuery();
            List<String> towns = new ArrayList<>();
            while (resultSet.next()) {
                towns.add(resultSet.getString(COLUMN_LABEL_NAME));
            }

            System.out.printf(TOWNS_AFFECTED, i, String.join(", ", towns));

        }
        connection.close();
    }
}
