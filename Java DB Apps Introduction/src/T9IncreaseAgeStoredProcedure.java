package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static src.Constants.COLUMN_LABEL_AGE;
import static src.Constants.COLUMN_LABEL_NAME;

public class T9IncreaseAgeStoredProcedure {

    static final String UPDATE_AGE_OF_MINION_WITH_ID = "CALL usp_get_older(?)";
    static final String NAME_AND_AGE_OF_MINION_WITH_ID = "select m.name, m.age from minions m where m.id = ?";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();
        final int id = new Scanner(System.in).nextInt();
        final PreparedStatement updateStatement = connection.prepareStatement(UPDATE_AGE_OF_MINION_WITH_ID);
        updateStatement.setInt(1, id);
        updateStatement.executeUpdate();
        final PreparedStatement selectStatement = connection.prepareStatement(NAME_AND_AGE_OF_MINION_WITH_ID);
        selectStatement.setInt(1, id);
        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        System.out.println(resultSet.getString(COLUMN_LABEL_NAME) + " " + resultSet.getInt(COLUMN_LABEL_AGE));
        connection.close();

    }
}
