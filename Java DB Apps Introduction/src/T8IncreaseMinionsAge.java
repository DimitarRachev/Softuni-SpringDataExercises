package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static src.Constants.COLUMN_LABEL_AGE;
import static src.Constants.COLUMN_LABEL_NAME;

public class T8IncreaseMinionsAge {
    private static final String LIST_ALL_MINIONS = "select m.name, m.age from minions m";
    ;

    public static void main(String[] args) throws SQLException {
        final String ids = String.join(", ", new Scanner(System.in).nextLine().split("\\s+"));
        final String UPDATE_MINIONS_AGE = "update minions set age = age + 1, name = lower(name) where id in (" + ids + ")";

        final Connection connection = Utils.getSQLConnection();
        final PreparedStatement statement = connection.prepareStatement(UPDATE_MINIONS_AGE);
        statement.executeUpdate();
        final PreparedStatement statement1 = connection.prepareStatement(LIST_ALL_MINIONS);
        ResultSet resultSet = statement1.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString(COLUMN_LABEL_NAME);
            int age = resultSet.getInt(COLUMN_LABEL_AGE);
            System.out.println(name + " " + age);
        }
        connection.close();
    }
}
